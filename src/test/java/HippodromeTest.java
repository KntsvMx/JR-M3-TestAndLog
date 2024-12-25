import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void shouldThrowIllegalArgumentExceptionWhenParameterIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void shouldThrowMessageOfExceptionWhenParameterIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenParameterIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void shouldThrowMessageOfExceptionWhenParameterIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void checkOfSimilarAmountAndSequencesOfHorsesAfterPassedInConstructor() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i+1, i+1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void shouldCallMoveMethodInAllHorses() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse1 : hippodrome.getHorses()) {
            Mockito.verify(horse1, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner() {
        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Horse1", 1, 1));
        horses.add(new Horse("Horse2", 2, 2));
        horses.add(new Horse("Horse3", 3, 3));

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(2), hippodrome.getWinner());
    }
}