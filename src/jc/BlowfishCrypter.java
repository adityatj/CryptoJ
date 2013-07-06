package jc;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;

/**
 *
 * @author adityatj
 */
public class BlowfishCrypter {
    public void encryptFile(String inputFileName , String outputFileName , String key){
        //Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        try{
        
        File inputFile = new File(inputFileName);
        File outputFile = new File(outputFileName);
        Base64 base64 = new Base64();
        FileInputStream fis = new FileInputStream(inputFile);
        byte[] data = new byte[(int)inputFile.length()];
        fis.read(data);
        fis.close();
        //KeyGenerator kg = KeyGenerator.getInstance("Blowfish");
        //kg.init(128);
        //Key secretKey = kg.generateKey();
        SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, skey);
        byte[] cipherText = cipher.doFinal(data);
        byte[] base64Encode = base64.encodeToByte(cipherText, true);
        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(base64Encode);
        fos.close();
        }
        catch(Exception e){System.err.println(e.toString());}
    }

    public void decryptFile(String inputFileName , String outputFileName , String key){

        try{
        File inputFile = new File(inputFileName);
        File outputFile = new File(outputFileName);
        Base64 base64 = new Base64();
        FileInputStream fis = new FileInputStream(inputFile);
        byte[] data = new byte[(int)inputFile.length()];
        fis.read(data);
        fis.close();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),"Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] base64Decode = base64.decode(data);
        byte[] decryptBytes = cipher.doFinal(base64Decode);
        FileOutputStream fos = new FileOutputStream(outputFile);
        fos.write(decryptBytes);
        fos.close();
        }
        catch(Exception e){System.err.println(e.toString());}
    }

}
