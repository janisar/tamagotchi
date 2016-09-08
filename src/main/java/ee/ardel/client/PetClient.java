package ee.ardel.client;

import ee.ardel.cron.MessageTimerTask;
import ee.ardel.util.Utils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;

/**
 * Created by saarlane on 30/08/16.
 */
public class PetClient implements Runnable {

    private Timer messageTimer;

    private void listenForMessages(final ObjectInputStream inputStream,
                                   final ObjectOutputStream outputStream) throws IOException {

        messageTimer = new Timer();
        messageTimer.schedule(new MessageTimerTask(outputStream, inputStream), 0, 500);
    }

    public void run() {
        Utils.printHelp();

        Socket client = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        Scanner scanner = null;
        try {
            client = new Socket(InetAddress.getLocalHost(), Utils.PORT);
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
            scanner = new Scanner(System.in);

            // Wait for pet name..
            out.writeObject(scanner.next().trim());
            listenForMessages(in, out);

            while (true) {
                try {
                    if (scanner.hasNext()) {
                        ClientRequest request = ClientRequest.fromString(scanner.next().trim());
                        out.writeObject(request);

                        if (request.equals(ClientRequest.QUIT)) {
                            break;
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Command not allowed");
                    Utils.printHelp();
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            messageTimer.cancel();
            IOUtils.closeQuietly(client);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(scanner);
        }
    }
}
