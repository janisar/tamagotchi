package ee.ardel.action;

import ee.ardel.model.Pet;
import ee.ardel.service.GameService;
import ee.ardel.util.Utils;

/**
 * Created by saarlane on 31/08/16.
 */
public class DressingAction extends Action {

    private static final int ACTION_LENGTH = GameService.HOUR_LENGTH / 2;

    public DressingAction(Pet pet) {
        super(pet);
    }

    @Override
    public void run() {
        pet.setLocked(true);
        pet.setAction("dressing");
        try {
            Thread.sleep(ACTION_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printActionResult("just finished dressing");

        pet.setMood(Utils.safeAdd(pet.getMood(), 10));
        pet.setLocked(false);
    }
}
