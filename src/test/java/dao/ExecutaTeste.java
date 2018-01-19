/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author c007329
 */
public class ExecutaTeste {

    public static void main(String[] args) {
        /*TesteModelDao dao = new TesteModelDao();
        TesteModel teste = new TesteModel();
        teste.setId(1);
        teste.setNome("teste");
        dao.beginTransaction();
        dao.save(teste);
        dao.commitAndCloseTransaction();*/

        try {
            String text = "Hello World";
            String key = "Bar12345Bar12345"; // 128 bit key
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            System.err.println(new String(encrypted));
            // decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(encrypted));
            System.err.println(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
