package Lab29.Huaicheng.Group1.A2;

import java.util.Scanner;

public class NewUser {
    public static boolean createUser() {
        Scanner scanner = new Scanner(System.in);
        boolean userCreated = false;

        System.out.println("\nUser Registration");
        System.out.print("Enter username: ");
        String username = null;
        if (scanner.hasNextLine()) {
            username = scanner.nextLine();
        }

        System.out.print("Enter password: ");
        String password = null;
        if (scanner.hasNextLine()) {
            password = scanner.nextLine();
        }

        if (username != null && password != null && !Login.login(username, password)) {
            System.out.println("Registration successful!");
            userCreated = true;
        } // else if(username == null || password == null){
//            System.out.println("Username or Password must not be left blank!");}
        else{
            System.out.println("This User Already Exists!");
        }
        return userCreated;
    }
}
