package Lab29.Huaicheng.Group1.A2;

import org.junit.jupiter.api.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUser() {
        user = new User(
                "ssko7098",
                "1234",
                "0401033233",
                "sebastian.skontos@gmail.com",
                "Sebastian Skontos"
                );
    }

    @AfterEach
    public void resetUser() {
        user.setUsername("ssko7098");
        user.setPassword("1234");
        user.setPhoneNumber("0401033233");
        user.setEmailAddress("sebastian.skontos@gmail.com");
        user.setFullName("Sebastian Skontos");
    }

    @Test
    public void changeUsernameCorrect() {
        // change username to one that doesn't already exist
        user.setUsername("doesn't exist");
        Assertions.assertEquals("doesn't exist", user.getUsername());
    }

    @Test
    public void changeUsernameWrong() {
        // change username to one that already exists --> this shouldn't be allowed
        user.setUsername("admin");
        Assertions.assertEquals("ssko7098", user.getUsername());
    }

    @Test
    public void changePassword() {
        user.setPassword("new password");
        Assertions.assertEquals("new password", user.getPassword());
    }

    @Test
    public void changePhoneNumber() {
        String number = "0401033233";
        user.setPhoneNumber(number);
        Assertions.assertEquals(number, user.getPhoneNumber());
    }

    @Test
    public void changePhoneNumberWrong() {
        String number = "0401033230942";
        user.setPhoneNumber(number);
        Assertions.assertEquals("0401033233", user.getPhoneNumber());
    }

    @Test
    public void changeEmailAddress() {
        user.setEmailAddress("john.smith@gmail.com");
        Assertions.assertEquals("john.smith@gmail.com", user.getEmailAddress());

        user.setEmailAddress("thisShouldntWork");
        Assertions.assertEquals("john.smith@gmail.com", user.getEmailAddress());
    }

    @Test
    public void changeName() {
        user.setFullName("John Smith");
        Assertions.assertEquals("John Smith", user.getFullName());
    }

}
