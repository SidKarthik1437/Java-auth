package org.auth;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Database {
    public MongoDatabase GetDB() {
        String url = "mongodb://admin:admin@localhost:27017/";

        MongoDatabase db;
        try (MongoClient mongoClient = MongoClients.create(url)) {
            db = mongoClient.getDatabase("java");
        }
        System.out.println("Connection Successful");
        return db;
    }

    public MongoCollection<Document> getCol(MongoDatabase database, String collectionName ) {
        return  database.getCollection("java");
    }

    public Boolean newUser(User user) throws Exception {
        try (MongoClient mongoClient = MongoClients.create("mongodb://admin:admin@localhost:27017/")) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("java");
            MongoCollection<Document> col = sampleTrainingDB.getCollection("java");

            col.insertOne(
                    new Document("name", user.getName())
                            .append("email", user.getEmail())
                            .append("password", user.getPass())
            );

            return true;
        }
        catch (Exception e) {
            System.out.println("User Not Created");
        }
        return false;
    }
}
