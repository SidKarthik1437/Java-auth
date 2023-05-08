package org.auth;


import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

     class Login{

         Login(){
             System.out.println("Login Initiated");
         }

         Login(String email) throws IOException {
             System.out.println("Enter password");
             String password = buf.readLine();

             System.out.println(password);

         }

     }

    public void main(String[] args) throws Exception {

        Utils util = new Utils();
        UserOperations userOps = new UserOperations();

//        User user = userOps.createUser("sid", "john.doe@example.com", "abc123", "abc123");
//
//        System.out.println(user.getEmail() + "\n" + user.getPass());

//        userOps.fetchUserByEmail(user.getEmail());

        Login login = new Login("john.doe@example.com");
    }
}