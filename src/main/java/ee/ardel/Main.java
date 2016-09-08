package ee.ardel;

import ee.ardel.client.PetClient;
import ee.ardel.server.PetServer;


public class Main {

    public static void main(String[] args) {

        /*
         * Let's do it plain old Java way -
         *     sockets and multithreading
         */
        new Thread(new PetServer()).start();

        new Thread(new PetClient()).start();
    }
}
