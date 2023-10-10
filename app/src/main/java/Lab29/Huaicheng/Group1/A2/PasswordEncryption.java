package Lab29.Huaicheng.Group1.A2;

import java.security.*;
import java.util.HashMap;
import javax.crypto.*;

public class PasswordEncryption {

    private String alg = "AES";
    private Cipher cipher;
    private HashMap<String, Key> keyList;
    private HashMap<String, byte[]> passwordList;



    public PasswordEncryption() throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.keyList = new HashMap<String, Key>();
        this.passwordList = new HashMap<String, byte[]>();
        this.cipher = Cipher.getInstance(alg);
    }

    public byte[] ReplaceMethod(String user, String unEncrypted) throws Exception {

        Key key = KeyGenerator.getInstance(alg).generateKey();

        byte[] encryptedBytes = encrypt(user, unEncrypted, key);
        System.out.println("The Key is: " + key);
        System.out.println("Encrypted text " + encrypt(user, unEncrypted, key));
        System.out.println("Decrypted text " + decrypt(encryptedBytes, key));

        return encryptedBytes;
    }

    private byte[] encrypt(String user, String input, Key key) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] iBytes = input.getBytes();
        byte[] encryption = cipher.doFinal(iBytes);
        keyList.put(user,key);
        passwordList.put(user,encryption);
        return encryption;
    }
    public String decrypt(byte[] encrypted, Key key) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] unencryptedBytes = cipher.doFinal(encrypted);
        return new String(unencryptedBytes);
    }

    public String retrieveDecryption(String user){
        try {
            cipher.init(cipher.DECRYPT_MODE,keyList.get(user));
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        byte[] unecryptedBytes;
        try {
            unecryptedBytes = cipher.doFinal(passwordList.get(user));
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return new String(unecryptedBytes);
    }

}

