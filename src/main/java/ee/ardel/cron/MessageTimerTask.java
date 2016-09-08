package ee.ardel.cron;

import ee.ardel.client.ClientRequest;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TimerTask;

/**
 * Created by saarlane on 31/08/16.
 */
public class MessageTimerTask extends TimerTask {

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public MessageTimerTask(ObjectOutputStream out, ObjectInputStream in) {
        this.out = out;
        this.in = in;
    }

    public void run() {
        try {
            out.writeObject(ClientRequest.STATE_CHECK);
            String message = (String) in.readObject();
            if (StringUtils.equals("RIP", message)) {
                System.out.println("Press 'q' to exit game.");
                this.cancel();
            } else if (StringUtils.isNotBlank(message)) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
