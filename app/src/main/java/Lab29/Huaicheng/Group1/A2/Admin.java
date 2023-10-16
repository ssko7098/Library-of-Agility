package Lab29.Huaicheng.Group1.A2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Admin {
    public static boolean checkDelete(String username) {
        JSONParser parser = new JSONParser();
        JSONArray users;

        try {
            users = (JSONArray) parser.parse(new FileReader("users.json"));
            for (int i = 0; i < users.size(); i++) {
                JSONObject user = (JSONObject) users.get(i);

                if (username.equals(user.get("username").toString()) && !(boolean) user.get("isAdmin")) {
                    return true;
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        // User does not exist or is an admin, so cannot be deleted.
        return false;
    }

    public static boolean checkUsername(String username) {
        JSONParser parser = new JSONParser();
        JSONArray users;

        try {
            users = (JSONArray) parser.parse(new FileReader("users.json"));
            for (int i = 0; i < users.size(); i++) {
                JSONObject user = (JSONObject) users.get(i);

                if (username.equals(user.get("username").toString())) {
                    return true;
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        // User does not exist or is an admin, so cannot be deleted.
        return false;
    }

    public static void deleteUser(String username) {
        JSONParser parser = new JSONParser();
        JSONArray users;

        try {
            users = (JSONArray) parser.parse(new FileReader("users.json"));
            for (int i = 0; i < users.size(); i++) {
                JSONObject user = (JSONObject) users.get(i);

                if (username.equals(user.get("username").toString())) {
                    users.remove(i);

                    try (FileWriter file = new FileWriter("users.json")) {
                        file.write(users.toJSONString());
                    }
                    return;
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static void updatePassword(String username, String password) {
        JSONParser parser = new JSONParser();
        JSONArray users;

        try {
            users = (JSONArray) parser.parse(new FileReader("users.json"));
            for (int i = 0; i < users.size(); i++) {
                JSONObject user = (JSONObject) users.get(i);

                if (username.equals(user.get("username").toString())) {
                    String newpassword = Encryptor.encryptString(password);
                    user.replace("password", newpassword);
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
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
