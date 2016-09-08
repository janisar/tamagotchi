package ee.ardel.action;

import ee.ardel.model.Pet;
import ee.ardel.service.GameService;
import ee.ardel.util.Utils;

/**
 * Created by saarlane on 31/08/16.
 */
public class EatingAction extends Action {

    private static final int ACTION_LENGTH = GameService.HOUR_LENGTH / 2;

    public EatingAction(Pet pet) {
        super(pet);
    }

    public void run() {
        pet.setLocked(true);
        pet.setAction("eating");

        try {
            Thread.sleep(ACTION_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updatePet();
        printActionResult("just finished eating");
        pet.setLocked(false);
    }

    protected void updatePet() {
        pet.setHunger(Utils.safeSubstract(pet.getHunger(), 90));
        pet.setMood(Utils.safeAdd(pet.getMood(), 25));
        pet.setPoop(Utils.safeAdd(pet.getPoop(), 25));
    }
}
