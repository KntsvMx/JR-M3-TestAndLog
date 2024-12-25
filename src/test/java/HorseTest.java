import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class HorseTest {
    //TODO: refactor tests and change "new Horse" like a global variable for all tests with different parameters for exceptions might be use @BeforeEach or @BeforeAll

    @Test
    void shouldThrowIllegalArgumentExceptionWhenFirstParameterIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    void shouldThrowMessageOfExceptionWhenFirstParameterIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null,1) );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void shouldThrowIllegalArgumentExceptionWhenFirstParameterIsBlankOrSpaceSymbols(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void shouldThrowMessageOfExceptionWhenFirstParameterIsBlandOrSpaceSymbols(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenSecondParameterIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1));
    }

    @Test
    void shouldThrowMessageOfExceptionWhenSecondParameterIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentWhenThirdParameterIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, -1));
    }

    @Test
    void shouldThrowMessageOfExceptionWhenThirdParameterIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, -1));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    void getName() {
        Horse horse = new Horse("name", 1);
        assertEquals("name", horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("name", 1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    void shouldReturnNumberOfThirdParameterAsDistance() {
        Horse construcorWith3Parameters = new Horse("name", 1, 1);
        assertEquals(1, construcorWith3Parameters.getDistance());
    }

    @Test
    void shouldReturnZeroAsDistance() {
        Horse construcorWith2Parameters = new Horse("name", 1);
        assertEquals(0, construcorWith2Parameters.getDistance());
    }

    @Test
    void move_shouldCallMethodAndVerifyByUsingMockito() {
        try(MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 1, 2);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }



    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.8, 15, 153})
    void move_shouldReturnNumberByCalculatingAndUsingMockedMethod(double fakeParameter) {
        double min = 0.2;
        double max = 0.9;
        double speed = 2.5;
        double distance = 250;
        Horse horse = new Horse("name", speed, distance);

        try(MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(fakeParameter);
            horse.move();
            assertEquals(distance + speed * fakeParameter, horse.getDistance());
        }
    }
}