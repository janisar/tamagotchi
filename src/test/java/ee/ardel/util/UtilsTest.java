package ee.ardel.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by saarlane on 1/09/16.
 */

public class UtilsTest {

    @Test
    public void testAdd() {
        int add1 = Utils.safeAdd(90, 100);
        int add2 = Utils.safeAdd(20, 20);
        int add3 = Utils.safeAdd(0, 0);

        Assert.assertEquals(add3, 0);
        Assert.assertEquals(add2, 40);
        Assert.assertEquals(add1, 100);
    }

    @Test
    public void testSubstraction() {
        int sub1 = Utils.safeSubstract(100, 20);
        int sub2 = Utils.safeSubstract(20, 50);

        Assert.assertEquals(sub2, 0);
        Assert.assertEquals(sub1, 80);
    }
}
