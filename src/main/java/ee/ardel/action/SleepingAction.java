package ee.ardel.action;

import ee.ardel.model.Pet;
import ee.ardel.service.GameService;
import ee.ardel.util.Utils;

/**
 * Created by saarlane on 31/08/16.
 */
public class SleepingAction extends Action {

    private static final int ACTION_LENGTH = GameService.HOUR_LENGTH * 3;

    public SleepingAction(Pet pet) {
        super(pet);
    }

    public void run() {
        pet.setLocked(true);
        pet.setAction("sleeping");
        pet.setSleepy(Utils.safeSubstract(pet.getSleepy(), 50));
        try {
            Thread.sleep(ACTION_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printActionResult("just woke up!");

        pet.setMood(Utils.safeAdd(pet.getMood(), 25));
        pet.setHunger(Utils.safeAdd(pet.getHunger(), 25));
        pet.setLocked(false);
    }
}
