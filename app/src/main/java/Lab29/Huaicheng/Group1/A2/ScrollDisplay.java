package Lab29.Huaicheng.Group1.A2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScrollDisplay {

    private File scroll;

    public static void readScroll(String scrollName) {
        try {
            File myObj = new File("app/src/main/resources/" + scrollName + ".txt");
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

}
