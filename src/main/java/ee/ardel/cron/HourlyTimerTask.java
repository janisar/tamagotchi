package ee.ardel.cron;

import ee.ardel.model.Pet;
import ee.ardel.model.PetAgeState;
import ee.ardel.service.GameService;
import ee.ardel.service.PetService;
import ee.ardel.service.PetServiceImpl;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.TimerTask;

/**
 * Created by saarlane on 31/08/16.
 */
@Component
public class HourlyTimerTask extends TimerTask {

    private PetService petService = new PetServiceImpl();

    private Pet pet;
    private LinkedList<String> messages;

    private static int HOUR_COUNTER = 0;

    public HourlyTimerTask(Pet pet, LinkedList<String> messages) {
        this.pet = pet;
        this.messages = messages;
    }

    public void run() {
        HOUR_COUNTER++;

        petService.increaseHunger(pet);
        petService.increaseTiredness(pet);
        petService.increasePoop(pet);
        petService.updateMood(pet);

        if (HOUR_COUNTER % GameService.HOURS_IN_DAY == 0) {
            messages.add("INFO: Current pet - " + pet.getName() + ":\n\t" + pet.toString());
            if (pet.isBirthday()) {
                petService.celebrateBirthday(pet);
                pet.setBirthday(false);
            }
        }

        if (HOUR_COUNTER % GameService.HOURS_IN_YEAR == 0) {

            messages.add("Don't forget, its " + pet.getName() + " birthday today!");
            pet.setBirthday(true);
        }

        messages.addAll(petService.actionCheck(pet));
        messages.addAll(petService.healthCheck(pet));

        if (pet.getState().equals(PetAgeState.DEAD)) {
            messages.add("Your pet has passed away, lets morn a minute now..");
            messages.add(petService.mourn(pet));
            this.cancel();
        }
    }
}
