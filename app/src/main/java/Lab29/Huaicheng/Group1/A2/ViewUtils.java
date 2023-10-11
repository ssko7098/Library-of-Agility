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

    static String getStringOnSameLine(String prompt) {
        Scanner s = new Scanner(System.in);
        String response = null;

        do {
            System.out.print(prompt);
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

    public static void checkNumberInput() {

        boolean check;
        String test = null;

        do {
            boolean validNumber = false;

            while(!validNumber) {
                test = ViewUtils.getString("Your current phone number is " + Login.getUser().getPhoneNumber() +
                        ". Please enter your new Phone Number:");

                validNumber = Login.getUser().setPhoneNumber(test);
            }

            check = getBoolean("Are you sure you want your new Phone Number to be: " + test + "?");

        }while(!check);

        Login.getUser().setPhoneNumber(test);
    }

    public static void checkEmailInput() {

        boolean check;
        String test = null;

        do {
            boolean validEmail = false;

            while(!validEmail) {
                test = ViewUtils.getString("Your current email address is " + Login.getUser().getEmailAddress() +
                        ". Please enter your new Email Address:");

                validEmail = Login.getUser().setEmailAddress(test);
            }

            check = getBoolean("Are you sure you want your new Email to be: " + test + "?");

        }while(!check);

        Login.getUser().setEmailAddress(test);
    }

    public static void checkNameInput() {

        boolean check;
        String test;

        do {
            test = ViewUtils.getString("Your current name is " + Login.getUser().getFullName() +
                    ". Please enter your new name:");

            check = getBoolean("Are you sure you want your new Fullname to be: " + test + "?");

        }while(!check);

        Login.getUser().setFullName(test);
    }

    public static void viewAllUsers(int displayKey) {
        JSONParser parser = new JSONParser();
        JSONArray users;
        System.out.println("\n\nList Of Users: ");

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

            String extra = null;
            if (isAdmin) {
                extra = "Admin";
            } else {
                extra = "Not Admin";
            }

            if (displayKey == 2) {
                extra = email;
            } else if (displayKey == 3) {
                extra = phoneNumber;
            } else if (displayKey == 4){
                extra = password;
            }

            System.out.println(name + " (" + username + "): " + extra);

//            for(Object entry: user.entrySet()) {
//                System.out.println(entry.toString());
//            }

//            System.out.println("\n");
        }
    }
}
