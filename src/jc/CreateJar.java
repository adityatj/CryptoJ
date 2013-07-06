package jc;

import java.util.jar.JarOutputStream;
import java.util.jar.JarEntry;
import java.util.jar.Manifest;

import java.io.File;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
/**
 *
 * @author adityatj
 */
public class CreateJar {
    public void createJarFile(File outputJar , String mainClass , File[] filesList){

        try{
            byte[] buffer = new byte[32768];
            StringBuffer strbuff = new StringBuffer();
	    strbuff.append("Manifest-Version: 1.0\n");
	    strbuff.append("Main-Class: " + mainClass + "\n");
            InputStream is = new ByteArrayInputStream(strbuff.toString().getBytes("UTF-8"));
            Manifest mf = new Manifest(is);
            FileOutputStream fos = new FileOutputStream(outputJar);
            JarOutputStream jos = new JarOutputStream(fos , new Manifest(mf));
            for(int i = 0; i < filesList.length; i++){
                System.out.println("Adding: " + filesList[i].getName());
                JarEntry je = new JarEntry(filesList[i].getName());
                jos.putNextEntry(je);
                FileInputStream in = new FileInputStream(filesList[i]);
                while (true) {
                int nRead = in.read(buffer, 0, buffer.length);
                if (nRead <= 0)
                break;
                jos.write(buffer, 0, nRead);
                }
                in.close();
                //fis.close();
                //jos.write(data);
            }
            //
            jos.close();fos.close();
            System.out.println("Jar file built successfully...");
        }
        catch(Exception e){System.err.println(e.toString());}
    }

}
