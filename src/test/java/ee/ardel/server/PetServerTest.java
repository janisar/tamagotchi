package ee.ardel.server;

import org.junit.Test;

/**
 * Created by saarlane on 1/09/16.
 */
public class PetServerTest {

    private PetServer petServer = new PetServerMock();

    @Test
    public void testStartingUp() {

        petServer.start();
    }

    private class PetServerMock extends PetServer {

    }
}
