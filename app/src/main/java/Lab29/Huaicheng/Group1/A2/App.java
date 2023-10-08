package Lab29.Huaicheng.Group1.A2;

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

    public static void main(String[] args) {
        existingUserMenu();
    }

    private static void existingUserMenu() {
        int selection = -1;
        do {
            selection = ViewUtils.displayMenu("Welcome to the Library of Agility!",
                    new String[]{
                            "Existing User",
                            "New User",
                    },
                    "Are you an existing user or a new user?");

            switch (selection) {
                case 1:
                    loginMenu();
                    break;
                case 2:
                    createUser();

                    //TODO change to new user registration screen
                    break;

                default:
                    // Can't get here
            }
        } while (-1 == selection);
    }

    private static void loginMenu() {
        boolean isUser = Login.askForLogin();

        while(!isUser) {
            isUser = Login.askForLogin();
        }

        initMenu();
    }

    private static void createUser() {
        boolean userCreated = NewUser.createUser();
        while (!userCreated) {
            userCreated = NewUser.createUser();
        }
        existingUserMenu();
    }

    private static void initMenu() {
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

    private static void updateUserMenu() {
        int selection;

        do {
            selection = ViewUtils.displayMenu("\nWelcome " + Login.getName() + " (" + userType + ")",
                    new String[]{
                            "Update my phone number",
                            "Update my email address",
                            "Update my name",
                            "Update my ID Key",
                            "Update my username",
                            "Update my password",
                    },
                    "Please enter a selection");

            switch (selection) {
                case 1:
//                    create();
                    selection = -1;
                    break;
                case 2:
//                    listVehicles();
                    selection = -1;
                    break;
                case 3:
                    return;
                default:
                    // Can't get here
            }
        } while (-1 == selection);
    }

    private static void adminMenu() {
        int selection;

        do {
            selection = ViewUtils.displayMenu("\nWelcome " + Login.getName() + " (" + userType + ")",
                    new String[]{
                            "View all users",
                            "Add another user",
                            "Delete a user",
                            "View scroll statistics",
                    },
                    "Please enter a selection");

            switch (selection) {
                case 1:
                    Admin.viewAllUsers();
                    selection = -1;
                    break;
                case 2:
//                    listVehicles();
                    selection = -1;
                    break;
                case 3:
                    return;
                default:
                    // Can't get here
            }
        } while (-1 == selection);
    }
}
