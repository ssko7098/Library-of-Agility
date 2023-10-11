package Lab29.Huaicheng.Group1.A2;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EncryptorTest {
    @Test
    public void testEncryptStringWithValidPassword() {
        String password = "HelloWorld123";
        String encryptedPassword = Encryptor.encryptString(password);

        String expectedEncryptedPassword = "2ef7bde608ce5404e97d5f042f95f89f1c232871";
        assertEquals(expectedEncryptedPassword, encryptedPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEncryptStringWithInvalidAlgorithm() {
        String password = "HelloWorld123";
        Encryptor.encryptString(password);
    }

    @Test(expected = NullPointerException.class)
    public void testEncryptStringWithNullPassword() {
        Encryptor.encryptString(null);
    }

    @Test(expected = NullPointerException.class)
    public void testEncryptStringWithNullAlgorithm() {
        String password = "HelloWorld123";
        Encryptor.encryptString(password);
    }
}


