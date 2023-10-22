package Lab29.Huaicheng.Group1.A2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ViewUtils {
    static int displayMenu(String header, String[] options, String prompt) {
        System.out.print("\033[H\033[2J");
        System.out.println("\n" + header);

        for (int i = 0; i < options.length; i++) {
            System.out.println((i) + ". " + options[i]);
        }

        while (true) {
            int selection = getInt(prompt);

            if (selection >= 0 && selection <= options.length) {
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

    static Boolean isBinary(String s) {
        for (int i = 0; i != s.length(); i++) {
            char c = s.charAt(i);
            // issue with Binary Check
            if (c != '1' && c != '0') {
                return false;
            }
        }
        return true;
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

        } while(!check);

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
            }

            System.out.println(name + " (" + username + "): " + extra);
        }
    }

    public static String[] viewAllScrollsNames() {
        List<String> scrollNames = new ArrayList<>();
        scrollNames.add("GO BACK");

        JSONParser parser = new JSONParser();
        JSONArray scrolls;
        try {
            scrolls = (JSONArray) parser.parse(new FileReader("scrolls.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for (int i=0; i<scrolls.size(); i++) {
            JSONObject scroll = (JSONObject) scrolls.get(i);
            scrollNames.add(scroll.get("scrollName").toString());
        }
        return scrollNames.toArray(new String[0]);
    }

    //code which is used in scroll seeker to display file names and details in a string
    public static String[] viewAllScrollsDetails() {
        List<String> scrollDetails = new ArrayList<>();
        scrollDetails.add("GO BACK");

        JSONParser parser = new JSONParser();
        JSONArray scrolls;
        try {
            scrolls = (JSONArray) parser.parse(new FileReader("scrolls.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for (int i=0; i<scrolls.size(); i++) {
            JSONObject scroll = (JSONObject) scrolls.get(i);
            String scrollString;

            scrollString = scroll.get("scrollName").toString() + ". Uploaded by "
                    + scroll.get("Uploader").toString() + " on " + scroll.get("Date");

            scrollDetails.add(scrollString);
        }
        return scrollDetails.toArray(new String[0]);
    }

    public static void readScroll(String scrollName, String username) {
        try {
            File myObj = new File("src/main/resources/" + username + "/" + scrollName + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Scroll not found. Please enter a valid scroll name from the list above");
        }

    }

    public static Boolean checkDirectory(String directoryPathway) {
        File f = new File(directoryPathway);
        return f.exists();
    }

    public static boolean checkScrollNameExists(String scrollName) {
        // the scroll name is meant to be a primary key, so we need to check
        // whether it already exists.
        File f = new File("src/main/resources/" + Login.getUser().getUsername() + "/" + scrollName + ".txt");
        return f.exists();
    }

    public static String readUploadedScroll(String scrollPath) {
        File f = new File(scrollPath);
        ArrayList<String> result = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(f);
            while(scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(!checkBinary(result)) {
            return null;
        }

        return String.join("\n", result);

    }

    public static boolean checkBinary(ArrayList<String> contents) {

        for(String line : contents) {
            char[] charArray = line.toCharArray();

            for(char c : charArray) {
                if(c != '1' && c != '0') {
                    return false;
                }
            }
        }

        return true;

    }

    public static void addScroll(String scrollName, String scrollContent, String scrollPath) {
        String directory = "src/main/resources/" + Login.getUser().getUsername();

        try {

            Path path = Paths.get(directory);

            Files.createDirectories(path);

            System.out.println("Directory is created!");

        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }

        File f = new File(directory + "/" + scrollName + ".txt");

        if (checkScrollNameExists(scrollName)) {
            System.out.println("File with that name already exists");
        } else {
            try {
                boolean success = f.createNewFile();
                if (success) {
                    addScrollToJSON(scrollName, Login.getUser().getUsername());
                    System.out.printf("New Scroll Created: ", f);
                } else {
                    System.out.printf("New Scroll Failed: ", f);
                }
            } catch (IOException e) {
                System.out.println("Scroll creation cannot occur");
            }
            try {
                FileWriter fileToWrite = new FileWriter(directory + "/" + scrollName + ".txt");
                fileToWrite.write(scrollContent);
                fileToWrite.close();
                System.out.println("Successfully wrote to the scroll.");
            } catch (IOException e) {
                System.out.println("Scroll writing cannot occur");
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void addScrollToJSON(String scrollName, String uploaderUsername){
        JSONParser parser = new JSONParser();
        JSONArray scrolls;

        try {
            scrolls = (JSONArray) parser.parse(new FileReader("scrolls.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject scrollDetails = new JSONObject();

        scrollDetails.put("scrollName", scrollName);
        scrollDetails.put("Uploader", uploaderUsername);
        scrollDetails.put("Date", java.time.LocalDate.now().toString());

        scrolls.add(scrollDetails);

        try {
            FileWriter file = new FileWriter("scrolls.json");
            file.write(scrolls.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void editFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String[] lines = reader.lines().toArray(String[]::new);

            System.out.println("Current content:");
            for (int i = 0; i < lines.length; i++) {
                System.out.println(i + 1 + ": " + lines[i]);
            }

            System.out.println("\nEnter the line number to edit (type 'exit' to save and exit):");
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while (!(userInput = userInputReader.readLine()).equals("exit")) {
                try {
                    int lineNumber = Integer.parseInt(userInput);
                    if (lineNumber > 0 && lineNumber <= lines.length) {
                        System.out.println("Enter new content for line " + lineNumber + ":");
                        String newContent = userInputReader.readLine();
                        lines[lineNumber - 1] = newContent;
                    } else {
                        System.out.println("Invalid line number. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number or 'exit'.");
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static String[] viewAllScrollsFromUser(String username) {
        List<String> scrollNames = new ArrayList<>();
        scrollNames.add("GO BACK");

        JSONParser parser = new JSONParser();
        JSONArray scrolls;
        try {
            scrolls = (JSONArray) parser.parse(new FileReader("scrolls.json"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for (int i=0; i<scrolls.size(); i++) {
            JSONObject scroll = (JSONObject) scrolls.get(i);
            if(scroll.get("Uploader").toString().equals(username)) {
                scrollNames.add(scroll.get("scrollName").toString());
            }
        }
        return scrollNames.toArray(new String[0]);
    }
}
