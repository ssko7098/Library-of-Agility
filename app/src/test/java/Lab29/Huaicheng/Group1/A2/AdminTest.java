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

        Login.createNewUser(user1);
        Admin.updatePassword(user1.getUsername(),"1234");

        String newPass = null;
        JSONParser parser = new JSONParser();
        JSONArray users;

        try {
            users = (JSONArray) parser.parse(new FileReader("users.json"));
            for (int i = 0; i < users.size(); i++) {
                JSONObject user = (JSONObject) users.get(i);
                if (user1.getUsername().equals(user.get("username").toString())) {
                    newPass = user.get("password").toString();
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        if(newPass != null) {
            Assertions.assertNotEquals(Encryptor.encryptString(oldPass),newPass);
        }

        Admin.deleteUser(user1.getUsername());

    }



}
