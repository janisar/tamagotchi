package ee.ardel.service;

import ee.ardel.action.PoopingAction;
import ee.ardel.action.SleepingAction;
import ee.ardel.model.Pet;
import ee.ardel.model.PetAgeState;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

/**
 * Created by saarlane on 1/09/16.
 */
public class PetServiceImplTest {

    @Mock
    private SleepingAction sleepingAction;

    @Mock
    private PoopingAction poopingAction;

    private PetServiceMock petService = new PetServiceMock();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void petShouldDieIfNoMoodAndHealthAndHungry() {
        Pet pet = new Pet("Test");
        pet.setHealth(0);
        pet.setMood(0);
        pet.setHunger(100);

        petService.healthCheck(pet);

        Assert.assertEquals(pet.getState(), PetAgeState.DEAD);
    }

    @Test
    public void shouldFallAsleepIfSleepy() {
        Pet pet = new Pet("Test");
        pet.setSleepy(100);

        petService.actionCheck(pet);

        verify(sleepingAction, times(1)).run();
    }

    @Test
    public void shouldPoopIfNecessary() {
        Pet pet = new Pet("Test");
        pet.setPoop(100);

        petService.actionCheck(pet);

        verify(poopingAction, times(1)).run();
    }

    @Test
    public void shouldCallPoopAndSleepBothIfNecessary() {
        Pet pet = new Pet("Test");
        pet.setPoop(100);
        pet.setSleepy(100);

        petService.actionCheck(pet);

        verify(poopingAction, times(1)).run();
        verify(sleepingAction, times(1)).run();
    }

    private class PetServiceMock extends PetServiceImpl {

        @Override
        SleepingAction getSleepingAction(Pet pet) {
            return sleepingAction;
        }

        @Override
        PoopingAction getPoopingAction(Pet pet, boolean plannedEvent) {
            return poopingAction;
        }
    }
}
