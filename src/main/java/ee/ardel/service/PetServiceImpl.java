package ee.ardel.service;

import ee.ardel.action.*;
import ee.ardel.model.Pet;
import ee.ardel.model.PetAgeState;
import ee.ardel.util.MessageConstants;
import ee.ardel.util.Utils;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import static ee.ardel.service.GameService.EXECUTOR;

/**
 * Created by saarlane on 30/08/16.
 */
@Service
public class PetServiceImpl implements PetService {

    public String poop(Pet pet) {
        if (!pet.isLocked()) {
            EXECUTOR.execute(getPoopingAction(pet, true));
            return "I'm going to poop now..";
        }
        return getCurrentActionMessage(pet);
    }

    public String sleep(Pet pet) {
        if (!pet.isLocked()) {
            EXECUTOR.execute(new SleepingAction(pet));
            return "I'm going to sleep now..";
        }
        return getCurrentActionMessage(pet);
    }

    public String eat(Pet pet) {
        if (!pet.isLocked()) {
            EXECUTOR.execute(new EatingAction(pet));
            return "Thanks mate! I'm going to eat now";
        }
        return getCurrentActionMessage(pet);
    }

    public String drinkAlcohol(Pet pet) {
        if (!pet.isLocked()) {
            EXECUTOR.execute(new BoozeAction(pet));
            return "Cheers mate! I'm going to drink now";
        }
        return getCurrentActionMessage(pet);
    }

    public String dress(Pet pet) {
        if (!pet.isLocked()) {
            EXECUTOR.execute(new DressingAction(pet));
            return "Changing my clothes now!";
        }
        return getCurrentActionMessage(pet);
    }

    public String goShopping(Pet pet) {
        if (!pet.isLocked()) {
            EXECUTOR.execute(new ShoppingAction(pet));
            return "Cheers, I'm off to shopping now!";
        }
        return getCurrentActionMessage(pet);
    }

    public String beAustralian(Pet pet) {
        pet.setMood(Utils.safeAdd(pet.getMood(), 5));
        return generateRandomMessage(pet.getName(), MessageConstants.AUSTRALIAN_EXPRESSIONS);
    }

    public String wishHappyBday(Pet pet) {
        if (!pet.isLocked()) {
            if (pet.isBirthday()) {
                pet.setMood(Utils.safeAdd(pet.getMood(), 25));
                return "Aww, thank you! <3";
            } else {
                pet.setMood(Utils.safeSubstract(pet.getMood(), 25));
                return "Umm.. did you forget when is my birthday?";
            }
        }
        return getCurrentActionMessage(pet);
    }

    public String mourn(Pet pet) {
        EXECUTOR.execute(new MourningAction(pet));
        return "RIP";
    }

    public boolean isAlive(Pet pet) {
        return !pet.getState().equals(PetAgeState.DEAD);
    }

    public String celebrateBirthday(Pet pet) {
        pet.increaseAge();
        pet.setState(PetAgeState.fromInt(pet.getAge()));

        return "ok";
    }

    public void increaseHunger(Pet pet) {
        pet.setHunger(Utils.safeAdd(pet.getHunger(), 4));
    }

    public void increaseTiredness(Pet pet) {
        pet.setSleepy(Utils.safeAdd(pet.getSleepy(), 4));
    }

    public void increasePoop(Pet pet) {
        pet.setPoop(Utils.safeAdd(pet.getPoop(), 10));
    }

    public void updateMood(Pet pet) {
        pet.setMood(Utils.safeSubstract(pet.getMood(), 4));
    }

    public List<String> healthCheck(Pet pet) {
        List<String> result = new LinkedList<String>();

        if (pet.getHunger() > 75) {
            result.add(generateRandomMessage(pet.getName(), MessageConstants.HUNGER_MESSAGES));

            pet.setHealth(Utils.safeSubstract(pet.getHealth(), 2));
        }
        if (pet.getMood() < 50) {
            result.add(generateRandomMessage(pet.getName(), MessageConstants.MOOD_MESSAGES));
            pet.setHealth(Utils.safeSubstract(pet.getHealth(), 2));
        }
        if (pet.getAge() > Pet.EXPECTED_LIFE) {
            pet.setHealth(Utils.safeSubstract(pet.getHealth(), (10 * pet.getAge())));
        }

        if (pet.getHealth() == 0 && pet.getMood() == 0 && pet.getHunger() == 100) {
            pet.setState(PetAgeState.DEAD);
        }

        return result;
    }

    public List<String> actionCheck(Pet pet) {
        List<String> result = new LinkedList<String>();

        if (pet.getSleepy() >= 100) {
            EXECUTOR.execute(getSleepingAction(pet));
            result.add("Oh snap, " + pet.getName() + " fell asleep now, and is really mad at you, because it doesn't like to fall asleep alone!");
        }
        if (pet.getPoop() >= 100) {
            EXECUTOR.execute(getPoopingAction(pet, false));
            result.add("Oh snap, " + pet.getName() + " just pooped into its pants, and is really really moody now.");
        }
        return result;
    }

    PoopingAction getPoopingAction(Pet pet, boolean plannedEvent) {
        return new PoopingAction(pet, plannedEvent);
    }

    SleepingAction getSleepingAction(Pet pet) {
        return new SleepingAction(pet);
    }

    private String generateRandomMessage(String name, String[] array) {
        return name + ": " + array[new Random().nextInt(array.length)];
    }

    private String getCurrentActionMessage(Pet pet) {
        return "Ssshh! " + pet.getName() + " is " + pet.getAction() + " at the moment!";
    }
}
