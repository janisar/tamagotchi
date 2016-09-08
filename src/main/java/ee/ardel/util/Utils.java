package ee.ardel.util;

import ee.ardel.client.ClientRequest;

/**
 * Created by saarlane on 30/08/16.
 */
public class Utils {

    public static final int PORT = 10011;

    public static void printHelp() {
        System.out.println("");
        System.out.println("----  Hy, I am tam -----");
        System.out.println("I can get angry and misbehave sometimes\n" +
                "But I am your new best friend.");
        System.out.println("");
        System.out.println("Here are controls to play with your virtual pet: ");
        for (ClientRequest request : ClientRequest.values()) {
            System.out.println("\t" + request.getValue() + " - " + request.name().toLowerCase());
        }
        System.out.println("");
    }

    public static int safeAdd(int num1, int num2) {
        if (num1 + num2 > 100) {
            return 100;
        } else
            return num1 + num2;
    }

    public static int safeSubstract(int num1, int num2) {
        if (num1 - num2 < 0) {
            return 0;
        } else
            return num1 - num2;
    }
}
