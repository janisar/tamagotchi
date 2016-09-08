package ee.ardel.action;

import ee.ardel.model.Pet;
import ee.ardel.util.Utils;

/**
 * Created by saarlane on 31/08/16.
 */
public class EatingJunkAction extends EatingAction {

    public EatingJunkAction(Pet pet) {
        super(pet);
    }

    @Override
    protected void updatePet() {
        pet.setHunger(Utils.safeSubstract(pet.getHunger(), 50));
        pet.setMood(Utils.safeAdd(pet.getMood(), 5));
        pet.setPoop(Utils.safeAdd(pet.getPoop(), 50));
    }
}
