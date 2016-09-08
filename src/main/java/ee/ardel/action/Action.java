package ee.ardel.action;

import ee.ardel.model.Pet;

/**
 * Created by saarlane on 31/08/16.
 */
public abstract class Action extends Thread {

    protected Pet pet;

    void printActionResult(String s) {
        System.out.println(pet.getName() + " " + s);
    }

    Action(Pet pet) {
        this.pet = pet;
    }
}
