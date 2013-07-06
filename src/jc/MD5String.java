package jc;

import java.security.MessageDigest;
import java.math.BigInteger;
/**
 *
 * @author adityatj
 */
public class MD5String {
    public String getMD5String(String key){

        try{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.reset();
        md5.update(key.getBytes());
        BigInteger bigInteger = new BigInteger(1,md5.digest());
        return String.format("%1$032X", bigInteger).substring(16, 32);
        }
        catch(Exception e){System.err.println(e.toString());}

        return null;
    }

}
