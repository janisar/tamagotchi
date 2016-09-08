package ee.ardel.action;

import ee.ardel.model.Pet;
import ee.ardel.service.GameService;
import ee.ardel.util.Utils;

/**
 * Created by saarlane on 31/08/16.
 */
public class BoozeAction extends Action {

    private static final int ACTION_LENGTH = GameService.HOUR_LENGTH;

    public BoozeAction(Pet pet) {
        super(pet);
    }

    @Override
    public void run() {
        pet.setLocked(true);
        pet.setAction("drinking");
        try {
            Thread.sleep(ACTION_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printActionResult("just finished drinking");

        pet.setSleepy(Utils.safeAdd(pet.getSleepy(), 50));
        pet.setMood(Utils.safeAdd(pet.getMood(), 25));
        pet.setHealth(Utils.safeSubstract(pet.getHealth(), 10));

        pet.setLocked(false);
    }
}
