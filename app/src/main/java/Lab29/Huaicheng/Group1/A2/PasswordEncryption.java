package Lab29.Huaicheng.Group1.A2;

import java.security.*;
import java.util.HashMap;
import javax.crypto.*;

public class PasswordEncryption {

    private String alg = "AES";
    private Cipher cipher;
    private HashMap<String, Key> keyList;
    private HashMap<String, byte[]> passwordList;



    public PasswordEncryption(String alg) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.keyList = new HashMap<String, Key>();
        this.passwordList = new HashMap<String, byte[]>();
        this.cipher = Cipher.getInstance(alg);
    }

    public byte[] ReplaceMethod(String user, String unEncrypted) throws Exception {

        Key key = KeyGenerator.getInstance(alg).generateKey();

        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] iBytes = unEncrypted.getBytes();
        byte[] encryption = cipher.doFinal(iBytes);
        keyList.put(user,key);
        passwordList.put(user,encryption);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] unencryptedBytes = cipher.doFinal(encryption);

        return unencryptedBytes;
    }

}

