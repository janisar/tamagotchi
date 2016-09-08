package ee.ardel.model;

/**
 * Created by saarlane on 30/08/16.
 */
public enum PetAgeState {

    BABY(0, 1),
    CHILD(1, 3),
    ADULT(3, 10),
    ELDERY(10, Pet.EXPECTED_LIFE),
    DEAD(Pet.EXPECTED_LIFE, 100);

    private int minAge;
    private int maxAge;

    PetAgeState(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public static PetAgeState fromInt(int i) {
        for (PetAgeState state : PetAgeState.values()) {
            if ((state.minAge <= i)  && (i < state.maxAge)) {
                return state;
            }
        }
        return null;
    }
}
