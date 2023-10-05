package Lab29.Huaicheng.Group1.A2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Admin {

    public static void viewAllUsers() {
        JSONParser parser = new JSONParser();
        JSONArray users = null;

        try {
            users = (JSONArray) parser.parse(new FileReader("users.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        for(int i=0; i<users.size(); i++) {
            JSONObject user = (JSONObject) users.get(i);

            for(Object entry: user.entrySet()) {
                System.out.println(entry.toString());
            }

            System.out.println("\n");
        }
    }

}
