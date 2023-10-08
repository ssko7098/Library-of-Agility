package Lab29.Huaicheng.Group1.A2;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    public static String getName() {
        return name;
    }

    public static User getUser() {
        return user;
    }
}
