package ee.ardel.action;

import static ee.ardel.service.GameService.EXECUTOR;

import ee.ardel.model.Pet;
import ee.ardel.model.ShoppingResult;
import ee.ardel.service.GameService;
import ee.ardel.util.Utils;

import java.util.Random;

/**
 * Created by saarlane on 31/08/16.
 */
public class ShoppingAction extends Action {


    private static final int ACTION_LENGTH = GameService.HOUR_LENGTH;

    public ShoppingAction(Pet pet) {
        super(pet);
    }

    @Override
    public void run() {
        pet.setLocked(true);
        pet.setAction("shopping");
        try {
            Thread.sleep(ACTION_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printActionResult("just finished shopping");
        processShoppingResult();
        pet.setLocked(false);
    }

    private void processShoppingResult() {
        pet.setMood(Utils.safeAdd(pet.getMood(), 50));
        pet.setSleepy(Utils.safeAdd(pet.getSleepy(), 50));

        ShoppingResult[] possibleShoppingResults = ShoppingResult.values();

        ShoppingResult result = possibleShoppingResults[new Random().nextInt(possibleShoppingResults.length)];

        System.out.println(pet.getName() + ": Wohoo, I just bought " + result.name().toLowerCase());

        String message = pet.getName() + ": ";

        switch (result) {
            case BANANAS:
                EXECUTOR.execute(new EatingAction(pet));
                message += "These bananas are awesome!";
                break;
            case JUNK_FOOD:
                EXECUTOR.execute(new EatingJunkAction(pet));
                message += "God bless McDonalds!";
                break;
            case CLOTHES:
                message += "I look so fabulous now!";
                EXECUTOR.execute(new DressingAction(pet));
                break;
            case LIQUEUR:
                message += "That wasn't the best purchase :(";
                EXECUTOR.execute(new BoozeAction(pet));
                break;
            case NONE:
                message += "That's sad!!";
                pet.setMood(Utils.safeSubstract(pet.getMood(), 70));
                break;
        }
        System.out.println(message);
    }
}
