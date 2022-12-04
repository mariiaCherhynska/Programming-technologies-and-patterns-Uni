package ua.nure.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class TestClass {
    public static void main(String[] args)  {
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoDatabase db = mongoClient.getDatabase("phones");
        MongoCollection<Document> collection = db.getCollection("phone");
        System.out.println("----------");

        Document newDoc = new Document("model", "Samsung")
                .append("processor",
                        new Document("model", "'Qualcomm'")
                                .append("cores", "4")
                                .append("frequency", "2")
                )
                .append("display",
                        new Document("model", "'Qualcomm'")
                                .append("screen_refresh_rate", "90")
                                .append("matrix_type", "LCD")
                );

        collection.insertOne(newDoc);

        for (Document doc : collection.find()){
            System.out.println(doc.toJson());
            System.out.println("----------");
        }
    }
}
