package ua.nure.dao;

import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import ua.nure.dao.entetity.Display;
import ua.nure.dao.entetity.Phone;
import ua.nure.dao.entetity.Processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MongoDBDAO implements DomainDao {
    private static MongoDBDAO instance;
    private MongoDatabase database;

    private static String phoneCollectionName = "phone";
    private static String displayCollectionName = "display";
    private static String processorCollectionName = "processor";

    public MongoDBDAO(String URL, String databaseName) throws Exception {
        if (instance == null) {
            database = MongoClients.create(URL).getDatabase(databaseName);
            instance = this;
        }
    }


    @Override
    public List<Phone> getPhones() throws Exception {
        FindIterable<Document> fi = database.getCollection(phoneCollectionName).find();
        MongoCursor<Document> cursor = fi.iterator();

        List<Phone> result = new ArrayList<>();

        while(cursor.hasNext()) {
            result.add(recordToPhone(cursor.next()));
        }
        cursor.close();

        return result;
    }

    private Display recordToDisplay(Document itemDisplay) throws Exception {
        return new Display.Builder()
                .setScreenRefreshRate(itemDisplay.getInteger("screen_refresh_rate"))
                .setMatrixType(itemDisplay.getString("matrix_type"))
                .build();
    }

    private Processor recordToProcessor(Document itemProcessor) throws Exception {
        return new Processor.Builder()
                .setModel(itemProcessor.getString("processor_model"))
                .setCores(itemProcessor.getString("cores"))
                .setFrequency(itemProcessor.getString("frequency"))
                .build();
    }
    private Phone recordToPhone(Document queryResult) throws Exception {
        return new Phone.Builder()
                .setModel(queryResult.getString("model"))
                .setObjId(queryResult.getObjectId("_id"))
                .setProcessor(recordToProcessor((Document) queryResult.get("processor")))
                .setDisplay(recordToDisplay((Document) queryResult.get("display")))
                .build();
    }


    @Override
    public void deletePhone(String id) throws Exception {
        database.getCollection(phoneCollectionName).deleteOne(new Document("_id", new ObjectId(id)));
    }

    @Override
    public void addPhone(Phone phone) throws Exception {
        MongoCollection<Document> col = database.getCollection(phoneCollectionName);

        col.insertOne(new Document("model", phone.getModel())
                .append("processor",
                        new Document("processor_model", phone.getProcessor().getModel())
                                .append("cores", phone.getProcessor().getCores())
                                .append("frequency", phone.getProcessor().getFrequency()))
                .append("display",
                        new Document("screen_refresh_rate", phone.getDisplay().getScreenRefreshRate())
                                .append("matrix_type", phone.getDisplay().getMatrixType()))
        );
    }
    @Override
    public void updatePhone(Phone phone) throws Exception {
        MongoCollection<Document> col = database.getCollection(phoneCollectionName);

        col.updateOne(
                Filters.eq("_id", phone.getObjId()),
                Updates.combine(
                        Updates.set("model", phone.getModel()),
                        Updates.set("processor", new Document("processor_model", phone.getProcessor().getModel())
                                .append("cores", phone.getProcessor().getCores())
                                .append("frequency", phone.getProcessor().getFrequency())),
                        Updates.set("display", new Document("screen_refresh_rate", phone.getDisplay().getScreenRefreshRate())
                                .append("matrix_type", phone.getDisplay().getMatrixType()))
                )
        );
    }

    @Override
    public List<Phone> getPhonesByModel(String phoneModel) throws Exception {
        FindIterable<Document> fi = database
                .getCollection(phoneCollectionName)
                .find(Filters.eq("model", phoneModel));
        MongoCursor<Document> cursor = fi.iterator();

        List<Phone> result = new ArrayList<>();

        while(cursor.hasNext()) {
            result.add(recordToPhone(cursor.next()));
        }
        cursor.close();

        return result;
    }

    @Override
    public List<Phone> getPhonesById(int id) throws Exception {
        return null;
    }
    @Override
    public void addDisplay(int screenRefreshRate, String matrix_type) throws Exception {
        MongoCollection<Document> col = database.getCollection(displayCollectionName);

        col.insertOne(new Document("screen_refresh_rate", screenRefreshRate).append("matrix_type", matrix_type));
    }
    @Override
    public void addProcessor(String model, String cores, String frequency) throws Exception {
        MongoCollection<Document> col = database.getCollection(processorCollectionName);

        col.insertOne
                (new Document("processor_model", model)
                        .append("cores", cores)
                        .append("frequency", frequency));
    }


    @Override
    public List<Display> getDisplays() throws Exception {
        FindIterable<Document> fi = database.getCollection(displayCollectionName).find();
        MongoCursor<Document> cursor = fi.iterator();

        List<Display> result = new ArrayList<>();

        while(cursor.hasNext()) {
            result.add(recordToDisplay(cursor.next()));
        }
        cursor.close();

        return result;
    }

    @Override
    public List<Processor> getProcessors() throws Exception {
        FindIterable<Document> fi = database.getCollection(processorCollectionName).find();
        MongoCursor<Document> cursor = fi.iterator();

        List<Processor> result = new ArrayList<>();

        while(cursor.hasNext()) {
            result.add(recordToProcessor(cursor.next()));
        }
        cursor.close();

        return result;
    }

    @Override
    public void clearDB() throws Exception {
        database.getCollection(phoneCollectionName).drop();
        database.getCollection(displayCollectionName).drop();
        database.getCollection(processorCollectionName).drop();
    }

    @Override
    public HashMap<String, Integer> getDisplaysAmount(String processorModel) {
        AggregateIterable<Document> fi = database.getCollection(phoneCollectionName).aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.eq("processor.processor_model", processorModel)),
                        Aggregates.project(Filters.and(
                                        Projections.include("model"),
                                        Projections.computed("display_matrix_type", "$display.matrix_type"),
                                        Projections.computed("amnt", new Document("$add", 1))
                                )
                        ),
                        Aggregates.group(
                                "$display_matrix_type", Accumulators.sum("amnt", "$amnt")
                        )
                )
        );

        MongoCursor<Document> cursor = fi.iterator();
        HashMap<String, Integer> result = new HashMap<>();

        while(cursor.hasNext()) {
            Document item = cursor.next();
            result.put(item.getString("_id"), item.getInteger("amnt"));
        }
        cursor.close();
        return result;
    }

    @Override
    public HashMap<String, Integer> getProcessorsAmount(String displayMatrixType) throws Exception {
        AggregateIterable<Document> fi = database.getCollection(phoneCollectionName).aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.eq("display.matrix_type", displayMatrixType)),
                        Aggregates.project(Filters.and(
                                        Projections.include("model"),
                                        Projections.computed("processor_model", "$processor.processor_model"),
                                        Projections.computed("amnt", new Document("$add", 1))
                                )
                        ),
                        Aggregates.group(
                                "$processor_model", Accumulators.sum("amnt", "$amnt")
                        )
                )
        );

        MongoCursor<Document> cursor = fi.iterator();
        HashMap<String, Integer> result = new HashMap<>();

        while(cursor.hasNext()) {
            Document item = cursor.next();
            result.put(item.getString("_id"), item.getInteger("amnt"));
        }
        cursor.close();
        return result;
    }

    @Override
    public List<Phone> getPhonesByModelProcessorAndDisplayMatrixType(
            String processorModel,
            String matrixType)
            throws Exception {

        AggregateIterable<Document> fi = database.getCollection(phoneCollectionName).aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.and(
                                Filters.eq("display.matrix_type", matrixType),
                                Filters.eq("processor.processor_model", processorModel)
                        ))
                )
        );
        MongoCursor<Document> cursor = fi.iterator();

        List<Phone> phones = new ArrayList<>();

        while(cursor.hasNext()) {
            phones.add(recordToPhone(cursor.next()));
        }
        cursor.close();
        return phones;
    }

    @Override
    public List<Phone> getPhonesByModelWithPaging(String model, int from, int to) throws Exception {
        AggregateIterable<Document> fi = database.getCollection(phoneCollectionName).aggregate(
                Arrays.asList(
                        Aggregates.match(Filters.and(
                                Filters.regex("model", "/"+model+"/")
                        )),
                        Aggregates.skip(from),
                        Aggregates.limit(to-from)
                )
        );
        MongoCursor<Document> cursor = fi.iterator();
        List<Phone> phones = new ArrayList<>();
        while(cursor.hasNext()) {
            System.out.println("in while");
            phones.add(recordToPhone(cursor.next()));
        }
        cursor.close();
        return phones;
    }

    @Override
    public Display getTopDisplay() throws Exception {
        AggregateIterable<Document> fi =
                database.getCollection(phoneCollectionName).aggregate(
                        Arrays.asList(
                                Aggregates.project(Filters.and(
                                                Projections.include("display"),
                                                Projections.computed("amnt", new
                                                        Document("$add", 1))
                                        )
                                ),
                                Aggregates.group(
                                        "$display", Accumulators.sum("amnt", "$amnt")
                                ),
                                Aggregates.sort(new Document("amnt", -1)),
                                Aggregates.limit(1)
                        )
                );
        MongoCursor<Document> cursor = fi.iterator();
        Display result = recordToDisplay((Document)
                cursor.next().get("_id"));
        cursor.close();
        return result;
    }
}
