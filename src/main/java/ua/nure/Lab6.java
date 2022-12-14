package ua.nure;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import ua.nure.dao.DAOVariant;
import ua.nure.dao.DaoFactory;
import ua.nure.dao.DomainDao;
import ua.nure.dao.entetity.Display;
import ua.nure.dao.entetity.Phone;
import ua.nure.dao.entetity.Processor;

import java.util.*;
import java.util.stream.Collectors;

public class Lab6 {

    public static void main(String[] args) throws Exception {
        DomainDao dao = DaoFactory.getDAOInstance(DAOVariant.MongoDBDAO);

        Long startTime = System.currentTimeMillis();

        //fill(dao);

        System.out.println("=================================");
       // System.out.println(dao.getDisplaysAmount("Snapdragon 8 Gen 2"));
        //System.out.println(getDisplaysAmount("Snapdragon 8 Gen 2", dao));

       // System.out.println(dao.getProcessorsAmount("IPS-LCD"));
        //System.out.println(getProcessorsAmount("IPS-LCD", dao));

       /* System.out.println(dao.getPhonesByModelProcessorAndDisplayMatrixType(
                "Dimensity 9200",
                "OLED"));*/

        /*System.out.println(getPhonesByModelProcessorAndDisplayMatrixType(
                "Dimensity 9200",
                "OLED", dao));*/


        //System.out.println(getTopDisplay(dao));

       System.out.println("It took " + (System.currentTimeMillis() - startTime) + " millis");
    }

    public static HashMap<String, Integer> getDisplaysAmount(String processorModel, DomainDao dao) throws Exception {
        List<Phone> monitors = dao.getPhones();
        HashMap<String, Integer> result = new HashMap<>();

        for (Phone phone: monitors) {
            if (!phone.getProcessor().getModel().equals(processorModel)) {
                continue;
            }
            if (result.containsKey(phone.getDisplay().getMatrixType())) {
                result.put(phone.getDisplay().getMatrixType(), result.get(phone.getDisplay().getMatrixType()) + 1);
            } else {
                result.put(phone.getDisplay().getMatrixType(), 1);
            }
        }
        return result;
    }

    public static HashMap<String, Integer> getProcessorsAmount(String displayMatrixType, DomainDao dao) throws Exception {
        List<Phone> monitors = dao.getPhones();
        HashMap<String, Integer> result = new HashMap<>();

        for (Phone phone: monitors) {
            if (!phone.getDisplay().getMatrixType().equals(displayMatrixType)) {
                continue;
            }
            if (result.containsKey(phone.getProcessor().getModel())) {
                result.put(phone.getProcessor().getModel(), result.get(phone.getProcessor().getModel()) + 1);
            } else {
                result.put(phone.getProcessor().getModel(), 1);
            }
        }
        return result;
    }
    public static Display getTopDisplay(DomainDao dao) throws Exception {
        List<Phone> phones = dao.getPhones();
        HashMap<Display, Integer> group = new HashMap<>();
        for (Phone phone: phones) {
            if (group.containsKey(phone.getDisplay())) {
                group.put(phone.getDisplay(), group.get(phone.getDisplay()) + 1);
            } else {
                group.put(phone.getDisplay(), 1);
            }
        }
        Display topKey = null;
        int topKeyCount = 0;
        for (Display groupKey: group.keySet()) {
            if (group.get(groupKey) > topKeyCount) {
                topKey = groupKey;
            }
        }
        return topKey;
    }

    public static List<Phone> getPhonesByModelProcessorAndDisplayMatrixType(String processorModel,
                                                                      String matrixType,
                                                                     DomainDao dao) throws Exception {
        List<Phone> phones = dao.getPhones();

        List<Phone> result = new ArrayList<>();


        for (Phone phone: phones) {
            if (!phone.getProcessor().getModel().equals(processorModel) ||
                    !phone.getDisplay().getMatrixType().equals(matrixType)) {

                continue;
            }
            result.add(phone);
        }
        return result;

    }

    public static List<Phone> getPhonesByModelWithPaging(String model, int from, int to,  DomainDao dao) throws Exception {
        List<Phone> phones = dao.getPhones();
        List<Phone> result = new ArrayList<>();
        for (Phone phone: phones) {
            if (!phone.getModel().contains(model)) {
                continue;
            }
            result.add(phone);
        }
        return result.stream().skip(from).limit(to-from).collect(Collectors.toList());
    }

    public static void fill(DomainDao dao) throws Exception {
        int amount = 100000;
        Random rand = new Random();

        List<Processor> processors = dao.getProcessors();

        List<Display> displays = dao.getDisplays();

        for (int i = 0; i < amount; i++) {
            dao.addPhone(
                    new Phone.Builder()
                            .setProcessor(processors.get(rand.nextInt(processors.size())))
                            .setDisplay(displays.get(rand.nextInt(displays.size())))
                            .setModel("TestModel - " + i).build()
            );
        }
    }


}
