package ee.ardel.service;

import ee.ardel.cron.HourlyTimerTask;
import ee.ardel.util.Utils;
import ee.ardel.model.Pet;
import ee.ardel.client.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by saarlane on 30/08/16.
 */
@Component
public class GameService {

    public static final ThreadPoolExecutor EXECUTOR = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    /**
     * In this perfect world, our hour is 7 secs long
     *  and there are 4 hours in a day and 20 days in a year
     */
    public static final int HOURS_IN_YEAR = 20;
    public static final int HOUR_LENGTH = 7 * 1000;
    public static final int HOURS_IN_DAY = 4;

    private LinkedList<String> messages;

    private Timer hourlyTimer;

    @Autowired
    private PetService petService;

    private Pet pet;

    public GameService withMessageBundle(LinkedList<String> messages) {
        this.messages = messages;
        return this;
    }

    public void start(final Pet pet) {
        System.out.println("Starting game with " + pet.getName());

        this.pet = pet;
        this.hourlyTimer = new Timer();

        hourlyTimer.schedule(new HourlyTimerTask(pet, messages), HOUR_LENGTH, HOUR_LENGTH);
    }

    public void processClientRequest(ClientRequest req) {
        String message = "";
        switch (req) {
            case POOP:
                message = petService.poop(pet); break;
            case FEED:
                message = petService.eat(pet); break;
            case SLEEP:
                message = petService.sleep(pet); break;
            case GO_TO_BAR:
                message = petService.drinkAlcohol(pet); break;
            case CHANGE_CLOTHES:
                message = petService.dress(pet); break;
            case GO_SHOPPING:
                message = petService.goShopping(pet); break;
            case BE_AUSTRALIAN:
                message = petService.beAustralian(pet); break;
            case WISH_HAPPY_BDAY:
                message = petService.wishHappyBday(pet); break;
            case HELP:
                Utils.printHelp(); break;
        }
        messages.add(message);
    }

    public boolean isAlive(Pet pet) {
        synchronized (pet.getState()) {
            return petService.isAlive(pet);
        }
    }

    public void stop() {
        hourlyTimer.cancel();
        EXECUTOR.shutdown();
    }
}
