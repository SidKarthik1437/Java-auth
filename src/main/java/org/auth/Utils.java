package org.auth;
import java.security.MessageDigest;
public class Utils {

    public Boolean verifyData(String email, String password1, String password2){

        boolean flag = !email.isEmpty() && email.matches("^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$");

        if (!password1.equals(password2)){
            flag = !flag;
        }
        return flag;
    }

    public String hashPassword(String password, String key) throws Exception {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes());

        return new String(messageDigest.digest());
    }


}


