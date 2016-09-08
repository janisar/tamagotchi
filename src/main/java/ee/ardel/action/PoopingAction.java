package ee.ardel.action;

import ee.ardel.model.Pet;
import ee.ardel.service.GameService;
import ee.ardel.util.Utils;

/**
 * Created by saarlane on 31/08/16.
 */
public class PoopingAction extends  Action {

    private static final int ACTION_LENGTH = GameService.HOUR_LENGTH / 2;

    private boolean plannedEvent;

    public PoopingAction(Pet pet, boolean plannedEvent) {
        super(pet);
        this.plannedEvent = plannedEvent;
    }

    public void run() {
        pet.setLocked(true);
        pet.setAction("pooping");
        pet.setPoop(Utils.safeSubstract(pet.getPoop(), 90));
        try {
            Thread.sleep(ACTION_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pet.setHunger(Utils.safeAdd(pet.getHunger(), 25));

        if (!plannedEvent) {
            pet.setMood(Utils.safeSubstract(pet.getMood(), 50));
        } else {
            pet.setMood(Utils.safeAdd(pet.getMood(), 25));
        }
        printActionResult("finished pooping");
        pet.setLocked(false);
    }
}
