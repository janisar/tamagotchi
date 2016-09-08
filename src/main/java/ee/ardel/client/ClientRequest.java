package ee.ardel.client;

import ee.ardel.util.Utils;

/**
 * Created by saarlane on 30/08/16.
 */
public enum ClientRequest {
    FEED("1"),
    SLEEP("2"),
    POOP("3"),
    GO_SHOPPING("4"),
    GO_TO_BAR("5"),
    CHANGE_CLOTHES("6"),
    BE_AUSTRALIAN("7"),
    WISH_HAPPY_BDAY("8"),

    HELP("h"),
    INFO("i"),
    QUIT("q"),

    STATE_CHECK("s");

    private String value;

    public String getValue() {
        return this.value;
    }

    ClientRequest(String value) {
        this.value = value;
    }

    public static ClientRequest fromString(String request) {
        if (request != null) {
            for (ClientRequest b : ClientRequest.values()) {
                if (request.equalsIgnoreCase(b.value)) {
                    return b;
                }
            }
        }
        throw new IllegalArgumentException("No command with text " + request + " found");
    }
}
