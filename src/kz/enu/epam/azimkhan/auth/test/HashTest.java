package kz.enu.epam.azimkhan.auth.test;

import kz.enu.epam.azimkhan.auth.util.PasswordDigest;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class HashTest {

    @Test
    public void md5test(){
        final String hash = PasswordDigest.md5hash("user");

        Assert.assertNotNull(hash);
        Assert.assertEquals(hash, "ee11cbb19052e40b07aac0ca060c23ee");
    }

    @Test
    public void md5test2(){
        final String hash = PasswordDigest.md5hash("jack");

        Assert.assertNotNull(hash);
        Assert.assertEquals(hash, "4ff9fc6e4e5d5f590c4f2134a8cc96d1");
    }
}
