package kz.enu.epam.azimkhan.auth.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 */
public class PasswordDigest {
    public static String md5hash(String password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(password.getBytes("UTF-8"));
            BigInteger bigInteger = new BigInteger(1, bytes);
            String hash = bigInteger.toString(16);
            while(hash.length() < 32){
                hash = "0" + hash;
            }

            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
