package ee.ardel.server;

import ee.ardel.model.Pet;
import ee.ardel.client.ClientRequest;
import ee.ardel.service.GameService;
import ee.ardel.util.Utils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Created by saarlane on 30/08/16.
 */
public class PetServer implements Runnable {

    protected void start() {

        boolean run = true;
        LinkedList<String> messagesToSend = new LinkedList<String>();

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
            serverSocket = new ServerSocket(Utils.PORT);

            while (run) {
                clientSocket = serverSocket.accept();
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());

                System.out.println("New game started for id [" +
                        clientSocket.getPort() +
                        "]\nPlease choose a name for your pet: ");

                String name = (String) in.readObject();

                ApplicationContext ctx = new ClassPathXmlApplicationContext("pet-beans.xml");

                GameService gameService = ctx.getBean(GameService.class).withMessageBundle(messagesToSend);
                Pet myPet = new Pet(name);
                gameService.start(myPet);

                while (true) {
                    ClientRequest request = (ClientRequest) in.readObject();
                    if (request.equals(ClientRequest.QUIT)) {
                        gameService.stop();
                        run = false;
                        break;
                    } else if (request.equals(ClientRequest.STATE_CHECK)) {

                        if (!messagesToSend.isEmpty()) {

                            out.writeObject(messagesToSend.removeFirst());
                        } else {
                            out.writeObject("");
                        }
                    } else if (!gameService.isAlive(myPet)) {
                        out.writeObject("RIP");
                    } else {
                        gameService.processClientRequest(request);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(serverSocket);
            IOUtils.closeQuietly(clientSocket);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }


    }

    public void run() {
        start();
    }
}
