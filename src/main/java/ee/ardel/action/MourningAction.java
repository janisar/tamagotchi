package ee.ardel.action;

import ee.ardel.model.Pet;
import ee.ardel.service.GameService;

/**
 * Created by saarlane on 31/08/16.
 */
public class MourningAction extends Action {

    private static final int ACTION_LENGTH = GameService.HOUR_LENGTH;

    public MourningAction(Pet pet) {
        super(pet);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(ACTION_LENGTH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Was pleasure to be your loyal partner, " + pet.getName());
    }
}
