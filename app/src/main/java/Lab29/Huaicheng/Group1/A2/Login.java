package Lab29.Huaicheng.Group1.A2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.NoSuchPaddingException;

public class Login {
    private static String name;
    private static String userType;
    private static User user;

    public static boolean login(String username, String password){

        JSONParser parser = new JSONParser();
        JSONArray users;

        try {
            users = (JSONArray) parser.parse(new FileReader("users.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        for(int i=0; i<users.size(); i++) {
            JSONObject iUser = (JSONObject) users.get(i);

            if(username.equals(iUser.get("username").toString())) {
                user = new User(iUser.get("username").toString(), iUser.get("password").toString(),
                        iUser.get("phone number").toString(), iUser.get("email address").toString(),
                        iUser.get("full name").toString());

                if(Boolean.parseBoolean(iUser.get("isAdmin").toString())) {
                    user.setAdmin();
                }

                if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    name = user.getFullName();

                    if(user.isAdmin()) {
                        userType = "admin";
                    }
                    else {
                        userType = "non-admin";
                    }

                    return true;
                }
            }
        }

        return false;
    }


    public static boolean checkUsernameExists(String username) {
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

            if(username.equals(iUser.get("username").toString())) {
                alreadyExists = true;
                break;
            }
        }

        if(alreadyExists) {
            // if username already exists, do nothing
            System.out.println("This username is already taken. Please choose a different one.");
        }

        return !alreadyExists;
    }

    public static String getName() {
        return name;
    }

    public static User getUser() {
        return user;
    }

    @SuppressWarnings("unchecked")
    public static void createNewUser(String username, String password, String name, String email, String phone, boolean isAdmin) throws IOException {
        JSONParser parser = new JSONParser();
        JSONArray userList;
        try {
            userList = (JSONArray) parser.parse(new FileReader("users.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject userDetails = new JSONObject();

        userDetails.put("password", password);
        userDetails.put("email address", email);
        userDetails.put("isAdmin", isAdmin);
        userDetails.put("phone number", phone);
        userDetails.put("full name", name);
        userDetails.put("username", username);

        userList.add(userDetails);

        FileWriter file = new FileWriter("users.json");
        file.write(userList.toJSONString());
        file.flush();
        file.close();
    }
}
