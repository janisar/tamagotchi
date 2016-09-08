package ee.ardel.service;

import ee.ardel.model.Pet;

import java.util.List;

/**
 * Created by saarlane on 30/08/16.
 */
public interface PetService {

    String poop(Pet pet);

    String sleep(Pet pet);

    String eat(Pet pet);

    String drinkAlcohol(Pet pet);

    String dress(Pet pet);

    String goShopping(Pet pet);

    String beAustralian(Pet pet);

    String wishHappyBday(Pet pet);

    String mourn(Pet pet);

    boolean isAlive(Pet pet);

    String celebrateBirthday(Pet pet);

    void increaseHunger(Pet pet);

    void increaseTiredness(Pet pet);

    void increasePoop(Pet pet);

    void updateMood(Pet pet);

    List<String> healthCheck(Pet pet);

    List<String> actionCheck(Pet pet);
}
