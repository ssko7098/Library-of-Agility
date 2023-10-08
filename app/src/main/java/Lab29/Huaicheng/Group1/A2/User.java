package Lab29.Huaicheng.Group1.A2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class User {

    private String username;
    private String password;
    private String phoneNumber;
    private String emailAddress;
    private String fullName;
    private boolean isAdmin = false;

    public User(String username, String password, String phoneNumber, String emailAddress, String fullname) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.fullName = fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
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

    public void setUsername(String username) {
        boolean check = Login.checkUsernameExists(username);

        if(check) {
            updateUsernameToJSONFile(this.username);
            this.username = username;
        }
    }

    public void setPassword(String password) {
        this.password = password;
        updateUserToJSONFile();
    }

    public boolean setPhoneNumber(String phoneNumber) {
        // phone numbers can only have 10 digits --> e.g. 0401 033 232
        if(phoneNumber.split("").length == 10) {
            this.phoneNumber = phoneNumber;
            updateUserToJSONFile();
            return true;
        }
        else {
            System.out.println("Phone number may have too many digits. Please try again");
            return false;
        }
    }

    public boolean setEmailAddress(String emailAddress) {
        // email address must contain '@' character
        if(emailAddress.contains("@")) {
            this.emailAddress = emailAddress;
            updateUserToJSONFile();
            return true;
        }
        else {
            System.out.println("Your email address must contain the '@' symbol. Please try again");
            return false;
        }
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
        updateUserToJSONFile();
    }

    public void setAdmin() {
        isAdmin = true;
        updateUserToJSONFile();
    }

    @SuppressWarnings("unchecked")
    public void updateUsernameToJSONFile(String username) {
        JSONParser parser = new JSONParser();
        JSONArray users;

        try {
            users = (JSONArray) parser.parse(new FileReader("users.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        for(int i=0; i<users.size(); i++) {
            JSONObject iUser = (JSONObject) users.get(i);

            if(iUser.get("username").toString().equals(this.username)) {
                iUser.replace("username", username);
                this.username = username;
            }
        }

        try {
            FileWriter file = new FileWriter("users.json");
            file.write(users.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public void updateUserToJSONFile() {
        JSONParser parser = new JSONParser();
        JSONArray users;

        try {
            users = (JSONArray) parser.parse(new FileReader("users.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        for(int i=0; i<users.size(); i++) {
            JSONObject iUser = (JSONObject) users.get(i);

            if(iUser.get("username").toString().equals(this.username)) {
                iUser.replace("password", password);
                iUser.replace("email address", emailAddress);
                iUser.replace("phone number", phoneNumber);
                iUser.replace("full name", fullName);
                iUser.replace("isAdmin", isAdmin);
            }
        }

        try {
            FileWriter file = new FileWriter("users.json");
            file.write(users.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
