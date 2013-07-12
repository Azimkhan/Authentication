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
}
