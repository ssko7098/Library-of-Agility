package Lab29.Huaicheng.Group1.A2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
        Assertions.assertTrue(Login.login("ssko7098", "1234"));
        Assertions.assertFalse(Login.getUser().isAdmin());
        Assertions.assertEquals("Sebastian Skontos", Login.getName());
    }

    @Test
    public void createNewUserTest() {
        String newUsername = "test";
        String newPassword = "1234";
        String newName = "test name";
        String newEmail = "test@email.com";
        String newPhone = "0412343891";

        try {
            Login.createNewUser(new User(newUsername, newPassword, newPhone, newEmail, newName));
            Assertions.assertFalse(Login.checkUsernameExists(newUsername));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
