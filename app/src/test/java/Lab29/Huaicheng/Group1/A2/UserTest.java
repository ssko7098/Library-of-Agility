package Lab29.Huaicheng.Group1.A2;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class UserTest {

    private User user;

    @Before
    public void setUser() {
        user = new User(
                "ssko7098",
                "1234",
                "0401033232",
                "ssko7098@uni.sydney.edu.au",
                "Sebastian Skontos"
                );
    }


    @Test
    public void changeUsername() {
        // change username to one that doesn't already exist
        Assertions.assertTrue(user.setUsername("doesn't exist"));
        Assertions.assertEquals("doesn't exist", user.getUsername());

        // change username to one that already exists --> this shouldn't be allowed
        Assertions.assertFalse(user.setUsername("admin"));
        Assertions.assertEquals("ssko7098", user.getUsername());
    }

    @Test
    public void changePassword() {
        user.setPassword("new password");
        Assertions.assertEquals("new password", user.getPassword());
    }

    @Test
    public void changePhoneNumber() {
        Long number = 0401033233L;
        user.setPhoneNumber(number);
        Assertions.assertEquals(number, user.getPhoneNumber());
    }

    @Test
    public void changeEmailAddress() {
        user.setEmailAddress("john.smith@gmail.com");
        Assertions.assertEquals("john.smith@gmail.com", user.getEmailAddress());
    }

    @Test
    public void changeName() {
        user.setFullName("John Smith");
        Assertions.assertEquals("John Smith", user.getFullName());
    }

}
