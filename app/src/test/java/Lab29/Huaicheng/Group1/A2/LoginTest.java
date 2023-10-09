package Lab29.Huaicheng.Group1.A2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class LoginTest {

    @Test
    public void IncorrectLoginTest() {
        Assertions.assertFalse(Login.login("wrongUsername", "wrongPassword"));
        Assertions.assertFalse(Login.login("ssko7098", "wrongPassword"));
        Assertions.assertFalse(Login.login("wrongUsername", "1234"));
    }

    @Test
    public void AdminLoginTest() {
        String adminUsername = "admin";
        String adminPassword = "123";

        Assertions.assertTrue(Login.login(adminUsername, adminPassword));
        Assertions.assertTrue(Login.getUser().isAdmin());
    }

    @Test
    public void nonAdminLoginTest() {
        String nonAdminUsername = "ssko7098";
        String nonAdminPassword = "1234";

        Assertions.assertTrue(Login.login(nonAdminUsername, nonAdminPassword));
        Assertions.assertFalse(Login.getUser().isAdmin());
        Assertions.assertEquals("Sebastian Skontos", Login.getName());
    }

    @Test
    public void createNewUserTest() {
        String newUsername = "test";
        String newPassword = "1234";

        try {
            Login.createNewUser(newUsername, newPassword);
            Assertions.assertFalse(Login.checkUsernameExists(newUsername));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
