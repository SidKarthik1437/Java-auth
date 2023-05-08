package org.auth;


import com.mongodb.client.*;
import org.bson.Document;

import java.util.stream.StreamSupport;

public class User {

    String name, email, password;

    User(String name,String email,String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
        System.out.println("User Creation Started");
    }

    public String getName(){return this.name;}
    public String getEmail(){return this.email;}
    public String getPass(){return this.password;}
}
class UserOperations extends User{

    Utils util = new Utils();
    Database db = new Database();

    public User createUser(String name, String email, String password1, String password2) throws Exception {

        if (userExists(email)) {
            System.out.println("User Exists");
            System.out.println("fetched");
            return fetchUserByEmail(email);
        }
        else {
            if (util.verifyData(email, password1, password2)) {
                throw new Exception("User data invalid");
            }
            String hashedPass = util.hashPassword(password1, email);
            User user = new User(name, email, hashedPass);
            db.newUser(user);

            System.out.println("created");
            return user;
        }
    }

    public Boolean userExists(String email) throws Exception {
        return fetchUserByEmail(email) != null;
    }

    public User fetchUserByEmail(String email) throws Exception {

        Document doc = null;

        try (MongoClient mongoClient = MongoClients.create("mongodb://admin:admin@localhost:27017/")) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("java");
            MongoCollection<Document> col = sampleTrainingDB.getCollection("java");
//
            try {
                doc = col.find(new Document("email", email)).first();

                assert doc != null;
                return new User((String) doc.get("name"), (String) doc.get("email"), (String) doc.get("password"));
            } catch (Exception e) {
                System.out.println("User not found");
            }
        }


        return null;
    }


}