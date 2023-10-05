package Lab29.Huaicheng.Group1.A2;

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
        prompt = prompt + "(y/n) ";
        Boolean response;
        do {
            String str = getString(prompt);
            if ("y".equals(str.toLowerCase())) {
                return true;
            }

            if ("n".equals((str.toLowerCase()))) {
                return false;
            }

            System.out.println("Invalid input - must be y or n");

        } while (true);
    }
}
