package ee.ardel.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saarlane on 30/08/16.
 */
public class Pet {

    public static final int EXPECTED_LIFE = 2;

    private String name;
    private int age = 0;

    private int hunger = 0;
    private int mood = 100;
    private int sleepy = 0;
    private int health = 100;
    private int poop = 0;
    private PetAgeState state;
    private boolean birthday;

    private boolean petLock = false;
    private String action;

    private Pet(String name, PetAgeState state) {
        this.state= state;
        this.name = name;
    }

    public Pet(String name) {
        this(name, PetAgeState.BABY);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getSleepy() {
        return sleepy;
    }

    public void setSleepy(int sleepy) {
        this.sleepy = sleepy;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public PetAgeState getState() {
        return state;
    }

    public void setState(PetAgeState state) {
        this.state = state;
    }

    public void increaseAge() {
        this.age++;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getPoop() {
        return poop;
    }

    public void setPoop(int poop) {
        this.poop = poop;
    }

    public boolean isBirthday() {
        return birthday;
    }

    public void setBirthday(boolean birthday) {
        this.birthday = birthday;
    }

    public boolean isLocked() {
        synchronized (this) {
            return petLock;
        }
    }

    public void setLocked(boolean locked) {
        synchronized (this) {
            this.petLock = locked;
        }
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hunger=" + hunger + " (0-100)" +
                ", mood=" + mood + " (0-100)" +
                ", sleepy=" + sleepy + " (0-100)" +
                ", health=" + health + " (0-100)" +
                ", poop=" + poop + " (0-100)" +
                ", state=" + state.name() +
                '}';
    }
}
