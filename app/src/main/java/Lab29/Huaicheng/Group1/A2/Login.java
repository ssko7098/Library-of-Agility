package Lab29.Huaicheng.Group1.A2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Login {
    private static String name;
    private static String userType;
    private static User user;

    public static boolean askForLogin(){
        Scanner scanner = new Scanner(System.in);
        boolean isUser = false;

        System.out.println("\nWelcome back user!");
        System.out.print("Enter username: ");
        String username = null;
        if(scanner.hasNextLine()) {
            username = scanner.nextLine();
        }

        System.out.print("Enter password: ");
        String password = null;
        if(scanner.hasNextLine()) {
            password = scanner.nextLine();
        }

        if (username != null && password != null && login(username, password)) {
            System.out.println("Login successful!");
            isUser = true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }

        return isUser;
    }

    private static boolean login(String username, String password){

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
            JSONObject iUser = (JSONObject) users.get(i);

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

        return false;
    }

    public static String getName() {
        return name;
    }

    public static User getUser() {
        return user;
    }
}
