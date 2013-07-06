package jc;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 *
 * @author adityatj
 */
public class Builder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        String key = "";
        System.out.println("***Welcome to JCrypter v0.1***");
        BlowfishCrypter bfc = new BlowfishCrypter();
        MD5String md5str = new MD5String();
        CreateJar createJar = new CreateJar();
        key = md5str.getMD5String("simple encryption");
        String outputFile = "encrypted_file.enc";
        System.out.print("Enter the input filename: ");
        BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
        String inputFile = user.readLine();
        File outputJar = new File("enc.jar");
        String mainClass = "Stub";
        File[] filesList = new File[5];
        filesList[0] = new File("BlowfishCrypter.class");
	  filesList[1] = new File("Stub.class");
	  filesList[2] = new File("MD5String.class");
	  filesList[3] = new File("Base64.class");
	  filesList[4] = new File(outputFile);
        bfc.encryptFile(inputFile, outputFile, key);
        createJar.createJarFile(outputJar, mainClass, filesList);
        
    }

}
