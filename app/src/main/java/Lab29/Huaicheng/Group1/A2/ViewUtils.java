package Lab29.Huaicheng.Group1.A2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ViewUtils {
    static int displayMenu(String header, String[] options, String prompt) {
        System.out.println("\n" + header);

        for (int i = 0; i < options.length; i++) {
            System.out.println((i+1) + ". " + options[i]);
        }

        while (true) {
            int selection = getInt(prompt);

            if (selection > 0 && selection <= options.length) {
                return selection;
            } else {
                System.out.println("Invalid menu selection");
            }
        }
    }

    static String getString(String prompt) {
        Scanner s = new Scanner(System.in);
        String response = null;

        do {
            System.out.println(prompt);
            if(s.hasNextLine()) {
                response = s.nextLine();

                if ("".equals(response)) {
                    response = null;
                    System.out.println("Blank entry is not allowed.");
                }
            }

        } while (null == response);

        return response;
    }

    static int getInt(String prompt) {

        int response;
        do {
            String str = getString(prompt);
            try {
                response = Integer.parseInt(str);
                return response;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input - number required");
            }

        } while (true);
    }

    static boolean getBoolean(String prompt) {
        prompt = prompt + " (y/n) ";
        Boolean response = null;

        do {
            String str = getString(prompt);
            if ("y".equalsIgnoreCase(str)) {
                response = true;
            }
            else if ("n".equalsIgnoreCase((str))) {
                response = false;
            }
            else {
                System.out.println("Invalid input - must be y or n");
            }

        } while (null == response);

        return response;
    }

    public static String checkUsernameInput() {

        boolean check;
        String test = null;

        do {
            boolean validUsername = false;

            while(!validUsername) {

                test = ViewUtils.getString("Your current username is " + Login.getUser().getUsername() +
                        ". Please enter your new Username:");

                validUsername = Login.checkUsernameExists(test);
            }

            check = getBoolean("Are you sure you want your new Username to be: " + test + "?");

        }while(!check);

        return test;
    }

    public static void checkPasswordInput() {

        boolean check;
        String test;

        do {
            test = ViewUtils.getString("Your current password is " + Login.getUser().getPassword() +
                    ". Please enter your new Password:");

            check = getBoolean("Are you sure you want your new Password to be: " + test + "?");

        }while(!check);

        Login.getUser().setPassword(test);
    }

    public static void viewAllUsers() {
        JSONParser parser = new JSONParser();
        JSONArray users;

        try {
            users = (JSONArray) parser.parse(new FileReader("users.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        for(int i=0; i<users.size(); i++) {
            JSONObject user = (JSONObject) users.get(i);
            String name = (String) user.get("full name");
            String password = (String) user.get("password");
            String email = (String) user.get("email address");
            boolean isAdmin = (boolean) user.get("isAdmin");
            String phoneNumber = (String) user.get("phone number");
            String username = (String) user.get("username");

            if (isAdmin) {
                System.out.println(name + " (Username: " + username + ") ");
            } else {
                System.out.println(name + " (Username: " + username + ") ");
            }

//            for(Object entry: user.entrySet()) {
//                System.out.println(entry.toString());
//            }

//            System.out.println("\n");
        }
    }
}
