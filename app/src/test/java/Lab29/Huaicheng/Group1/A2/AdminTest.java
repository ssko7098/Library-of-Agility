package Lab29.Huaicheng.Group1.A2;

import Lab29.Huaicheng.Group1.A2.Admin;
import Lab29.Huaicheng.Group1.A2.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

public class AdminTest {

    @Test
    public void checkDeleteTest() {
        Assertions.assertFalse(Admin.checkDelete("admin"));
        Assertions.assertTrue(Admin.checkDelete("seb"));
    }

    @Test
    public void changePasswordTest() throws IOException {

        //Sucessfully creating new User to test
        String oldPass = "123";
        User user1 = new User("userTest");
        user1.setPassword(oldPass);
        user1.setPhoneNumber("0413857586");
        user1.setEmailAddress("ethan@gmail.com");
        user1.setFullName("Ethan Acevksi");

    }



}
