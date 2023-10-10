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
        boolean isUserDeleted = false;

        try {
            users = (JSONArray) parser.parse(new FileReader("users.json"));
            for (int i = 0; i < users.size(); i++) {
                JSONObject user = (JSONObject) users.get(i);

                if (username.equals(user.get("username").toString()) && !(boolean) user.get("isAdmin")) {
                    users.remove(i);
                    isUserDeleted = true;
                    break;
                }
            }

            // Writing back to file if user is deleted
            if (isUserDeleted) {
                try (FileWriter file = new FileWriter("users.json")) {
                    file.write(users.toJSONString());
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        return isUserDeleted;
    }
}
