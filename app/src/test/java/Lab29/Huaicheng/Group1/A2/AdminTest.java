package Lab29.Huaicheng.Group1.A2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminTest {

    @Test
    public void checkDeleteTest() {
        Assertions.assertFalse(Admin.checkDelete("admin"));
        Assertions.assertTrue(Admin.checkDelete("seb"));
    }

}
