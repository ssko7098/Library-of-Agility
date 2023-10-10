package Lab29.Huaicheng.Group1.A2;

import javax.swing.text.View;
import java.io.IOException;
import java.util.Scanner;

public class App {
    private static String[] nonAdmin = new String[]{
            "Manage my Profile",
            "Digital Scroll Management",
            "Scroll Seeker",
            "Log Out"
    };

    private static String[] admin = new String[]{
            "Manage my Profile",
            "Digital Scroll Management",
            "Scroll Seeker",
            "Log Out",
            "User Management (Special admin access)"
    };

    private static String userType = "non-admin";

    public static void main(String[] args) throws IOException {
        existingUserMenu();
    }

    private static void existingUserMenu() throws IOException {
        int selection = -1;
        do {
            selection = ViewUtils.displayMenu("Welcome to the Library of Agility!",
                    new String[]{
                            "Existing User",
                            "New User",
                            "EXIT",
                    },
                    "Are you an existing user or a new user?");

            switch (selection) {
                case 1:
                    loginMenu();
                    break;
                case 2:
                    createUserMenu(false);

                    //TODO change to new user registration screen
                    break;
                case 3:
                    // exit the application
                    return;

                default:
                    // Can't get here
            }
        } while (-1 == selection);
    }

    private static void loginMenu() throws IOException {
        boolean isUser = askForLogin();

        while(!isUser) {
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
                            "Show Email Adresses",
                            "Show Phone Numbers",
                            "Show Passwords",
                            "Go Back",
                    },
                    "Please enter a selection");

            switch (selection) {
                case 1:
                    viewUsers(2);
                    break;
                case 2:
                    viewUsers(3);
                    break;
                case 3:
                    viewUsers(4);
                    return;
                case 4:
                    adminMenu();
                    return;

                default:
                    // Can't get here
            }
        } while (-1 == selection);

    }

    public static boolean createUser(boolean isAdmin) throws IOException {
        Scanner scanner = new Scanner(System.in);
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
            while(!user.setEmailAddress(email)) {
                email = ViewUtils.getStringOnSameLine("Enter Email: ");
            }

            //enter phone prompt
            String phone = ViewUtils.getStringOnSameLine("Enter Phone Number: ");
            while(!user.setPhoneNumber(phone)) {
                phone = ViewUtils.getStringOnSameLine("Enter Phone Number: ");
            }

            System.out.println("Registration Successful");

            Login.createNewUser(user);

            userCreated = true;
        }

        return userCreated;
    }

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

        if(Login.getUser().isAdmin()) {
            access = admin;
            userType = "admin";
        }
        else {
            access = nonAdmin;
        }

        do {
            selection = ViewUtils.displayMenu("\nWelcome " + Login.getName() + " (" + userType + ")",
                    access,
                    "Please select what you would like to do");

            switch (selection) {
                case 1:
                    //TODO change to user profile screen
                    updateUserMenu();
                    break;
                case 2:
                    //TODO change to digital scroll management screen
                    break;
                case 3:
                    //TODO change to scroll seeker screen
                    break;
                case 4:
                    existingUserMenu();
                    break;
                case 5:
                    //TODO change to user management screen
                    adminMenu();
                    break;

                default:
                    // Can't get here
            }
        } while (-1 == selection);
    }

    private static void updateUserMenu() throws IOException {
        int selection;

        do {
            selection = ViewUtils.displayMenu("\nMake Selection:",
                    new String[]{
                            "Update my username",
                            "Update my password",
                            "Update my phone number",
                            "Update my email address",
                            "Update my name",
                            "GO BACK",
                    },
                    "Please enter a selection");

            switch (selection) {
                case 1:
                    String username = ViewUtils.checkUsernameInput();
                    Login.getUser().updateUsernameToJSONFile(username);

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

                case 6:
                    initMenu();
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
                            "View all users",
                            "Add another user",
                            "Delete a user",
                            "View scroll statistics",
                            "GO BACK",
                    },
                    "Please enter a selection");

            switch (selection) {
                case 1:
                    viewUsers(1);

                    selection = -1;
                    break;
                case 2:
                    adminOrUser();
                    selection = -1;
                    break;
                case 3:
                    selection = -1;
                    break;

                case 4:
                    selection = -1;
                    break;

                case 5:
                    initMenu();
                    break;

                default:
                    // Can't get here
            }
        } while (-1 == selection);
    }
    private static void adminOrUser() throws IOException {
        int selection;

        do {
            selection = ViewUtils.displayMenu("\nMake Selection:",
                    new String[]{
                            "Create Regular User",
                            "Create Admin",
                    },
                    "Please enter a selection");

            switch (selection) {
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
}
