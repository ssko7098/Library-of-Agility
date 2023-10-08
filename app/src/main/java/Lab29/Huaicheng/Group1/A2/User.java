package Lab29.Huaicheng.Group1.A2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class User {

    private String username;
    private String password;
    private Long phoneNumber;
    private String emailAddress;
    private String fullName;
    private boolean isAdmin = false;

    public User(String username, String password, String phoneNumber, String emailAddress, String fullname) {
        this.username = username;
        this.password = password;
        this.phoneNumber = Long.parseLong(phoneNumber);
        this.emailAddress = emailAddress;
        this.fullName = fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean setUsername(String username) {
        // the username is meant to be a primary key, so we need to check
        // whether it already exists.

        JSONParser parser = new JSONParser();
        JSONArray users;

        try {
            users = (JSONArray) parser.parse(new FileReader("users.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        boolean alreadyExists = false;
        for(int i=0; i<users.size(); i++) {
            JSONObject iUser = (JSONObject) users.get(i);

            if(iUser.get("username").toString().equals(username)) {
                alreadyExists = true;
            }
        }

        if(alreadyExists) {
            // if username already exists, do nothing
            System.out.println("This username is already taken. Please choose a different one.");
        }
        else {
            this.username = username;
        }

        return !alreadyExists;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAdmin() {
        isAdmin = true;
    }
}
