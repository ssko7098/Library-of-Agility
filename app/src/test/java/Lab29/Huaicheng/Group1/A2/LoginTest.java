package Lab29.Huaicheng.Group1.A2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoginTest {

    @Test
    public void loginTest() {
        String adminUsername = "admin";
        String adminPassword = "123";

        Assertions.assertTrue(Login.login(adminUsername, adminPassword));
        Assertions.assertTrue(Login.getUser().isAdmin());

        String nonAdminUsername = "ssko7098";
        String nonAdminPassword = "1234";

        Assertions.assertTrue(Login.login(nonAdminUsername, nonAdminPassword));
        Assertions.assertFalse(Login.getUser().isAdmin());
        Assertions.assertEquals("Sebastian Skontos", Login.getName());

        String wrongUsername = "doesntExist";
        String wrongPassword = "doesntExist";

        Assertions.assertFalse(Login.login(wrongUsername, wrongPassword));

        String rightUsername = "ssko7098";
        Assertions.assertFalse(Login.login(rightUsername, wrongPassword));
    }

}
