package tv.gillespie.jim.exercises.robots;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static tv.gillespie.jim.exercises.robots.Direction.*;

class DirectionTest {

    @Test
    public void testLeft() {
        // GIVEN A valid Direction
        // WHEN Asked to turn left
        // THEN Check the new direction
        assertEquals(W, N.turnLeft());
        assertEquals(N, E.turnLeft());
        assertEquals(E, S.turnLeft());
        assertEquals(S, W.turnLeft());
    }

    @Test
    public void testRight() {
        // GIVEN A valid Direction
        // WHEN Asked to turn left
        // THEN Check the new direction
        assertEquals(E, N.turnRight());
        assertEquals(S, E.turnRight());
        assertEquals(W, S.turnRight());
        assertEquals(N, W.turnRight());
    }
}
