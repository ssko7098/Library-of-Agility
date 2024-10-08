package Lab29.Huaicheng.Group1.A2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    private static String[] nonAdmin = new String[]{
            "LOG OUT",
            "Manage my Profile",
            "Digital Scroll Management",
            "Scroll Seeker",
    };

    private static String[] admin = new String[]{
            "LOG OUT",
            "Manage my Profile",
            "Digital Scroll Management",
            "Scroll Seeker",
            "Special admin access"
    };

    private static String[] guest = new String[]{
            "LOG OUT",
            "Scroll Seeker",
    };

    private static String userType = "non-admin";

    public static void main(String[] args) throws IOException, InterruptedException {
        AsciiArt art = new AsciiArt();
        art.draw("Welcome To");
        Thread.sleep(3000);
        art.draw("The Library");
        Thread.sleep(1500);
        art.draw("Of Agility");
        Thread.sleep(1500);
        System.out.print("\033[H\033[2J");
        Thread.sleep(200);
        existingUserMenu();
    }

    private static void existingUserMenu() throws IOException {
        int selection = -1;
        do {
            selection = ViewUtils.displayMenu("Welcome to the Library of Agility! \nPlease select from the following options:",
                    new String[]{
                            "EXIT",
                            "Existing User",
                            "New User",
                            "Guest User",
                    },
                    "Are you an existing user or a new user?");

            switch (selection) {
                case 0:
                    // exit the application
                    System.exit(0);
                    return;
                case 1:
                    loginMenu();
                    break;
                case 2:
                    createUserMenu(false);
                    break;
                case 3:
                    Login.login("guest", "guest");
                    initMenu();

                default:
                    // Can't get here
            }
        } while (-1 == selection);
    }

    private static void loginMenu() throws IOException {
        boolean isUser = askForLogin();

        while (!isUser) {
            isUser = askForLogin();
        }

        initMenu();
    }


    private static void createUserMenu(boolean isAdmin) throws IOException {
        boolean userCreated = createUser(isAdmin);

        while (!userCreated) {
            userCreated = createUser(isAdmin);
        }

        existingUserMenu();
    }

    private static void viewUsers(int displayKey) throws IOException {
        ViewUtils.viewAllUsers(displayKey);

        int selection = -1;
        do {
            selection = ViewUtils.displayMenu("\nMake Selection",
                    new String[]{
                            "GO BACK",
                            "Show Email Addresses",
                            "Show Phone Numbers",
                            "Change User Password",
                    },
                    "Please enter a selection");

            switch (selection) {
                case 0:
                    adminMenu();
                case 1:
                    viewUsers(2);
                    break;
                case 2:
                    viewUsers(3);
                    break;
                case 3:
                    changePassword();
                    break;

                default:
                    // Can't get here
            }
        } while (-1 == selection);

    }


    public static boolean createUser(boolean isAdmin) throws IOException {
        boolean userCreated = false;

        System.out.println("\nUser Registration");
        String username = ViewUtils.getStringOnSameLine("Enter Username: ");
        User user = new User(username);

        if (Login.checkUsernameExists(username)) {
            //enter password prompt
            user.setPassword(ViewUtils.getStringOnSameLine("Enter Password: "));

            //enter name prompt
            user.setFullName(ViewUtils.getStringOnSameLine("Enter Full Name: "));

            //enter email prompt
            String email = ViewUtils.getStringOnSameLine("Enter Email: ");
            while (!user.setEmailAddress(email)) {
                email = ViewUtils.getStringOnSameLine("Enter Email: ");
            }

            //enter phone prompt
            String phone = ViewUtils.getStringOnSameLine("Enter Phone Number: ");
            while (!user.setPhoneNumber(phone)) {
                phone = ViewUtils.getStringOnSameLine("Enter Phone Number: ");
            }

            //update user as per admin
            user.setAdmin(isAdmin);

            System.out.println("Registration Successful");

            Login.createNewUser(user);

            userCreated = true;
        }

        return userCreated;
    }

    public static boolean askForLogin() {
        boolean isUser = false;

        System.out.println("\nWelcome back user!");
        String username = ViewUtils.getStringOnSameLine("Enter username: ");
        String password = ViewUtils.getStringOnSameLine("Enter password: ");

        if (username != null && password != null && Login.login(username, password)) {
            System.out.println("Login successful!");
            isUser = true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }

        return isUser;
    }

    private static void initMenu() throws IOException {
        int selection = -1;
        String[] access;

        if (Login.getUser().isAdmin()) {
            access = admin;
            userType = "admin";
        }
        else if(Login.getUser().getUsername().equals("guest")) {
            access = guest;
            userType = "guest";
        }
        else {
            access = nonAdmin;
            userType = "non-admin";
        }

        do {
            selection = ViewUtils.displayMenu("\nWelcome " + Login.getName() + " (" + userType + ")",
                    access,
                    "Please select what you would like to do");

            switch (selection) {
                case 0:
                    existingUserMenu();

                case 1:
                    if(userType.equals("guest")) {
                        ScrollMenu();
                    }
                    else {
                        updateUserMenu();
                    }
                    break;
                case 2:
                    digitalScrollManagementMenu();
                    break;
                case 3:
                    ScrollMenu();
                    break;
                case 4:
                    adminMenu();
                    break;

                default:
                    // Can't get here
            }
        } while (-1 == selection);
    }

    private static void digitalScrollManagementMenu() throws IOException {
        int selection;

        do {
            selection = ViewUtils.displayMenu("\nMake Selection:",
                    new String[]{
                            "GO BACK",
                            "Add Scroll",
                            "Edit Scroll",
                            "Delete Scroll",
                    },
                    "Please enter a selection");

            switch (selection) {
                case 0:
                    initMenu();
                    break;

                case 1:
                    uploadScrollMenu();
                    break;

                case 2:
                    editScrollMenu();
                    break;

                case 3:
                    deleteScrollMenu();
                    break;

                default:
                    // Can't get here
            }
        } while (-1 == selection);
    }

    private static void editScrollMenu() throws IOException {
        int selection;

        do {
            selection = ViewUtils.displayMenu("\nMake Selection:",
                    ViewUtils.viewAllScrollsFromUser(Login.getUser().getUsername()),
                    "Please enter a selection");

            if (selection == 0) {
                digitalScrollManagementMenu();
                break;
            }
            else {
                String scrollName = ViewUtils.viewAllScrollsFromUser(Login.getUser().getUsername())[selection];
                ViewUtils.editFile("src/main/resources/" + Login.getUser().getUsername() + "/" + scrollName + ".txt");
                digitalScrollManagementMenu();
                break;
            }

        } while (-1 == selection);
    }

    private static void deleteScrollMenu() throws IOException {
        int selection;

        do {
            selection = ViewUtils.displayMenu("\nMake Selection:",
                    ViewUtils.viewAllScrollsFromUser(Login.getUser().getUsername()),
                    "Please enter a selection");

            if (selection == 0) {
                digitalScrollManagementMenu();
                break;
            }
            else {
                if (ViewUtils.getBoolean("Are you sure you want to delete this scroll?")){
                    String scrollName = ViewUtils.viewAllScrollsFromUser(Login.getUser().getUsername())[selection];
                    ViewUtils.deleteScroll("src/main/resources/" + Login.getUser().getUsername() + "/" + scrollName + ".txt", scrollName);
                    System.out.println("Scroll deleted successfully!");
                    digitalScrollManagementMenu();
                } else {
                    System.out.println("Scroll deletion cancelled.");
                    digitalScrollManagementMenu();
                }

            }

        } while (-1 == selection);
    }

    private static void uploadScrollMenu() throws IOException {
        int selection = -1;

        do{
            selection = ViewUtils.displayMenu("\nMake Selection:",
                    new String[]{
                            "GO BACK",
                            "Upload Scroll",
                            "Write Scroll Here",
                    }, "Please enter a selection");

            switch(selection) {
                case 0:
                    digitalScrollManagementMenu();

                case 1:
                    String fileName = ViewUtils.getStringOnSameLine("Enter Scroll Name: ");
                    boolean invalidDirectory = true;
                    String directoryAdress = "";

                    while (invalidDirectory) {
                        directoryAdress = FileSaveAndDownload.chooseFile();
                        invalidDirectory = false;
                        if(directoryAdress == null) {
                            selection = -1;
                            break;
                        }
                        if (!ViewUtils.checkDirectory(directoryAdress)) {
                            invalidDirectory = true;
                        }
                    }

                    if (fileName != null) {
                        String fileText;

                        try{
                            fileText = ViewUtils.readUploadedScroll(directoryAdress);
                        }catch(NullPointerException e) {
                            System.out.println("The file you have chosen is not a binary file. Please try again");
                            selection = -1;
                            break;
                        }

                        if(fileText == null) {
                            System.out.println("The file you have chosen is not a binary file. Please try again");
                            selection = -1;
                            break;
                        }

                        ViewUtils.addScroll(fileName, fileText, directoryAdress);

                    } else {
                        System.out.println("Invalid Input");
                        digitalScrollManagementMenu();
                        break;
                    }
                    digitalScrollManagementMenu();

                case 2:
                    String name = ViewUtils.getStringOnSameLine("Enter Scroll Name: ");
                    String directory = "src/main/resources/";

                    if (name != null) {
                        String fileText = "";
                        String fileLine = "!";
                        fileLine = ViewUtils.getStringOnSameLine("\n\nEnter Scroll Text Line by Line!\nEnter a '?' on a new line to indicate cancellation of scroll upload\nEnter a '!' on a new line to indicate input is complete:\n");
                        while (!fileLine.equals("!") && !fileLine.equals("?")) {
                            if (ViewUtils.isBinary(fileLine)) {
                                fileText = fileText + fileLine;
                                fileLine = ViewUtils.getStringOnSameLine("");
                                fileText = fileText + "\n";
                            } else {
                                if (!fileLine.equals("!") && !fileLine.equals("?")) {
                                    System.out.println("\n\nNon Binary Line Entered");
                                    fileLine = "?";
                                }
                            }
                        }
                        if (fileLine.equals("!")) {
                            ViewUtils.addScroll(name, fileText, directory);
                        } else {
                            System.out.println("Scroll Upload Ceased");
                        }
                    } else {
                        System.out.println("Invalid Input");
                        digitalScrollManagementMenu();
                        break;
                    }
                    digitalScrollManagementMenu();
            }

        }while(selection == -1);
    }

    private static void updateUserMenu() throws IOException {
        int selection;

        do {
            selection = ViewUtils.displayMenu("\nMake Selection:",
                    new String[]{
                            "GO BACK",
                            "Update my username",
                            "Update my password",
                            "Update my phone number",
                            "Update my email address",
                            "Update my name",
                    },
                    "Please enter a selection");

            switch (selection) {
                case 0:
                    initMenu();
                    break;

                case 1:
                    String username = ViewUtils.checkUsernameInput();
                    Login.getUser().setUsername(username);

                    selection = -1;
                    break;
                case 2:
                    ViewUtils.checkPasswordInput();
                    selection = -1;
                    break;
                case 3:
                    ViewUtils.checkNumberInput();
                    selection = -1;
                    break;

                case 4:
                    ViewUtils.checkEmailInput();
                    selection = -1;
                    break;

                case 5:
                    ViewUtils.checkNameInput();
                    selection = -1;
                    break;

                default:
                    // Can't get here
            }
        } while (-1 == selection);
    }

    private static void adminMenu() throws IOException {
        int selection;

        do {
            selection = ViewUtils.displayMenu("\nMake Selection:",
                    new String[]{
                            "GO BACK",
                            "View all users",
                            "Add another user",
                            "Delete a user",
                            "View scroll statistics",
                    },
                    "Please enter a selection");

            switch (selection) {
                case 0:
                    initMenu();
                    break;

                case 1:
                    viewUsers(1);

                    selection = -1;
                    break;
                case 2:
                    adminOrUser();
                    selection = -1;
                    break;
                case 3:
                    deleteUser();
                    selection = -1;
                    break;

                case 4:
                    viewStats();
                    selection = -1;
                    break;

                default:
                    // Can't get here
            }
        } while (-1 == selection);
    }

    private static void viewStats() throws IOException {
        int selection;
        do {
            selection = ViewUtils.displayMenu("\nMake Selection:",
                    new String[]{
                            "GO BACK",
                    },
                    "Total Scrolls: " + Admin.getScrollNumber() + "\nTotal Downloads: " + Admin.getDownloadNumber() +
                    "\nTotal Uploads: " + Admin.getUploadNumber());

            switch (selection) {
                case 0:
                    adminMenu();
                    break;
                default:
                    if (selection != -1) {
                        System.out.println("Invalid selection, please choose 0 to go back.");
                    }
            }
        } while (true);
    }



    private static void adminOrUser() throws IOException {
        int selection;

        do {
            selection = ViewUtils.displayMenu("\nMake Selection:",
                    new String[]{
                            "GO BACK",
                            "Create Regular User",
                            "Create Admin",
                    },
                    "Please enter a selection");

            switch (selection) {
                case 0:
                    adminMenu();
                    break;

                case 1:
                    createUserMenu(false);

                    selection = -1;
                    break;
                case 2:
                    createUserMenu(true);

                default:
                    // Can't get here
            }
        } while (-1 == selection);

    }

    private static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Username For Deletion: ");
        String username = null;
        if (scanner.hasNextLine()) {
            username = scanner.nextLine();
        }

        if (username != null) {
            boolean deletable = Admin.checkDelete(username);

            if (!deletable) {
                System.out.println("\nUser not found or is an admin, and cannot be deleted!");
                deleteUser();
            } else {
                if (confirmDeletion(username)) {
                    Admin.deleteUser(username);
                    System.out.println("User deleted successfully!");
                } else {
                    System.out.println("User deletion cancelled.");
                }
            }
        }
    }

    private static boolean confirmDeletion(String username) {
        int selection;
        do {
            selection = ViewUtils.displayMenu("\nMake Selection:",
                    new String[]{
                            "Yes",
                            "No",
                    },
                    "Are you sure you want to delete user: '" + username + "'?");

            switch (selection) {
                case 0:
                    return true;
                case 1:
                    return false;
                default:
                    System.out.println("Invalid selection, please choose 0 for Yes or 1 for No.");
            }
        } while (true);
    }

    private static void changePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Username to update password: ");
        String username = null;
        if (scanner.hasNextLine()) {
            username = scanner.nextLine();
        }
        if (username != null) {
            boolean deletable = Admin.checkUsername(username);

            if (!deletable) {
                System.out.println("\nUser not found!");
                changePassword();
            } else {
                System.out.println("Enter new password for user: " + username);
                String password = null;
                if (scanner.hasNextLine()) {
                    password = scanner.nextLine();
                }
                if (ViewUtils.getBoolean("Are you sure you want to update " + username + "'s password?")) {
                    Admin.updatePassword(username, password);
                    System.out.println("User password updated successfully!");
                } else {
                    System.out.println("User password update cancelled.");
                }
            }
        }

    }

    private static void ScrollMenu() {
        int selection;

        do {
            selection = ViewUtils.displayMenu("\nMake Selection:",
                    ViewUtils.viewAllScrollsDetails(),
                    "Please enter a selection");

            if (selection == 0) {
                try {
                    initMenu();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (selection == 1) {
                searchScrolls();
            } else {
                System.out.println("Scroll Content for " + ViewUtils.viewAllScrollsNames()[selection - 1] + ":");

                String username = ViewUtils.viewAllScrollsDetails()[selection].split(" ")[3];

                ViewUtils.readScroll(ViewUtils.viewAllScrollsNames()[selection - 1], username);

                if(!userType.equals("guest")) {
                    boolean download = ViewUtils.getBoolean("Do you want to download this scroll?");

                    if(download) {
                        FileSaveAndDownload.downloadFile(ViewUtils.viewAllScrollsNames()[selection - 1], username);
                    }
                }
                //System.out.println();
                System.out.println("Once you have finished previewing the scroll, enter '!' on a separate line to return to the selection menu");
                boolean temp = false;
                while(!temp) {
                    String searchTerm = ViewUtils.getStringOnSameLine("");
                    if (searchTerm.contains("!")) {
                        temp = true;
                    }
                }

                selection = -1;
            }
        } while (-1 == selection);
    }
    private static void searchScrolls() {
        int selection;

        do {
            selection = ViewUtils.displayMenu("\nMake Selection:",
                    new String[]{
                            "GO BACK",
                            "Search using Scroll Name",
                            "Search using Scroll Uploader",
                            "Search using Date",
                    },
                    "Please enter a selection");

            switch (selection) {
                case 0:
                    ScrollMenu();
                    break;

                case 1:
                    searchUsingName();
                    selection = -1;
                    break;
                case 2:
                    searchUsingUploader();
                    selection = -1;
                    break;
                case 3:
                    searchUsingDate();
                    selection = -1;
                    break;
                default:
            }
        } while (-1 == selection);
    }
    private static void searchUsingName() {
        int selection;

        do {
            System.out.println("Enter '!' to quit search");
            String searchTerm = ViewUtils.getStringOnSameLine("Search: ");

            if(searchTerm.equals("!")) {
                searchScrolls();
            }

            String[] searchResult = ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "name");

            if(searchResult == null) {
                System.out.println("No scrolls found with that name.");
                searchUsingName();
            }

            selection = ViewUtils.displayMenu("\nMake Selection:",
                        searchResult, "Please enter a selection");

            if (selection == 0) {
                searchScrolls();
            } else {
                String username = ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "name")[selection].split(" ")[4];
                System.out.println("Scroll Content for " + username + "'s " + ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "name")[selection].split(" ")[0] + ":");
                ViewUtils.readScroll(ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "name")[selection].split(" ")[0], username);

                if(!userType.equals("guest")) {
                    boolean download = ViewUtils.getBoolean("Do you want to download this scroll?");

                    if(download) {
                        FileSaveAndDownload.downloadFile(ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "name")[selection], username);
                    }
                }

                selection = -1;
                System.out.println();
            }
        } while (-1 == selection);
    }

    private static void searchUsingUploader() {int selection;

        do {
            System.out.println("Enter '!' to quit search");
            String searchTerm = ViewUtils.getStringOnSameLine("Search: ");

            if(searchTerm.equals("!")) {
                searchScrolls();
            }

            String[] searchResult = ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "uploader");

            if(searchResult == null) {
                System.out.println("No scrolls found uploaded by that user.");
                searchUsingUploader();
            }

            selection = ViewUtils.displayMenu("\nMake Selection:",
                    searchResult,"Please enter a selection");

            if (selection == 0) {
                searchScrolls();
            } else {
                String username = ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "uploader")[selection].split(" ")[4];
                System.out.println("Scroll Content for " + username + "'s " + ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "uploader")[selection].split(" ")[0] + ":");
                ViewUtils.readScroll(ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "uploader")[selection].split(" ")[0], username);

                if(!userType.equals("guest")) {
                    boolean download = ViewUtils.getBoolean("Do you want to download this scroll?");

                    if(download) {
                        FileSaveAndDownload.downloadFile(ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "uploader")[selection], username);
                    }
                }

                selection = -1;
                System.out.println();
            }
        } while (-1 == selection);}
    private static void searchUsingDate() {int selection;

        do {
            System.out.println("Enter '!' to quit search");
            String searchTerm = ViewUtils.getStringOnSameLine("Search: ");

            if(searchTerm.equals("!")) {
                searchScrolls();
            }

            String[] searchResult = ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "date");

            if(searchResult == null) {
                System.out.println("No scrolls found with that date.");
                searchUsingDate();
            }

            selection = ViewUtils.displayMenu("\nMake Selection:",
                    searchResult,
                    "Please enter a selection");

            if (selection == 0) {
                searchScrolls();
            } else {
                String username = ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "date")[selection].split(" ")[4];
                System.out.println("Scroll Content for " + username + "'s " + ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "date")[selection].split(" ")[0] + ":");
                ViewUtils.readScroll(ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "date")[selection].split(" ")[0], username);

                if(!userType.equals("guest")) {
                    boolean download = ViewUtils.getBoolean("Do you want to download this scroll?");

                    if(download) {
                        FileSaveAndDownload.downloadFile(ViewUtils.viewScrollsDetailsUsingSearch(searchTerm, "date")[selection], username);
                    }
                }
                selection = -1;
                System.out.println();
            }
        } while (-1 == selection);}
}
