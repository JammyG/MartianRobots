package tv.gillespie.jim.exercises.robots;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static tv.gillespie.jim.exercises.robots.Direction.*;
import static tv.gillespie.jim.exercises.robots.Instruction.*;

class RobotTest {
    @Test
    public void testMoveNorth() {
        // GIVEN A robot which is facing north
        Grid grid = new Grid("10 20");
        Robot robot = new Robot("1 1 N", grid);

        // WHEN It moves forward
        robot.processInstruction(F);

        // THEN It ends up where expected
        assertEquals(1, robot.getPoint().x());
        assertEquals(2, robot.getPoint().y());
        assertEquals(N, robot.getDirection());
    }

    @Test
    public void testMoveEast() {
        // GIVEN A robot which is facing east
        Grid grid = new Grid("10 20");
        Robot robot = new Robot("1 1 E", grid);

        // WHEN It moves forward
        robot.processInstruction(F);

        // THEN It ends up where expected
        assertEquals(2, robot.getPoint().x());
        assertEquals(1, robot.getPoint().y());
        assertEquals(E, robot.getDirection());
    }

    @Test
    public void testMoveSouth() {
        // GIVEN A robot which is facing south
        Grid grid = new Grid("10 20");
        Robot robot = new Robot("1 1 S", grid);

        // WHEN It moves forward
        robot.processInstruction(F);

        // THEN It ends up where expected
        assertEquals(1, robot.getPoint().x());
        assertEquals(0, robot.getPoint().y());
        assertEquals(S, robot.getDirection());
    }

    @Test
    public void testMoveWest() {
        // GIVEN A robot which is facing west
        Grid grid = new Grid("10 20");
        Robot robot = new Robot("1 1 W", grid);

        // WHEN It moves forward
        robot.processInstruction(F);

        // THEN It ends up where expected
        assertEquals(0, robot.getPoint().x());
        assertEquals(1, robot.getPoint().y());
        assertEquals(W, robot.getDirection());
    }

    @Test
    public void testMoveOffNorth() {
        // GIVEN A robot which is facing north and is on the north edge of the grid
        Grid grid = new Grid("10 20");
        Robot robot = new Robot("1 20 N", grid);

        // WHEN It moves forward
        robot.processInstruction(F);

        // THEN It ends up where expected
        Point location = robot.getPoint();
        assertEquals(1, location.x());
        assertEquals(20, location.y());
        assertTrue(robot.isLost());
    }

    @Test
    public void testMoveOffEast() {
        // GIVEN A robot which is facing east and is on the east edge of the grid
        Grid grid = new Grid("10 20");
        Robot robot = new Robot("10 1 E", grid);

        // WHEN It moves forward
        robot.processInstruction(F);

        // THEN It ends up where expected
        Point location = robot.getPoint();
        assertEquals(10, location.x());
        assertEquals(1, location.y());
        assertTrue(robot.isLost());
    }

    @Test
    public void testMoveOffSouth() {
        // GIVEN A robot which is facing south and is on the south edge of the grid
        Grid grid = new Grid("10 20");
        Robot robot = new Robot("1 0 S", grid);

        // WHEN It moves forward
        robot.processInstruction(F);

        // THEN It ends up where expected
        Point location = robot.getPoint();
        assertEquals(1, location.x());
        assertEquals(0, location.y());
        assertTrue(robot.isLost());
    }

    @Test
    public void testMoveOffWest() {
        // GIVEN A robot which is facing west and is on the west edge of the grid
        Grid grid = new Grid("10 20");
        Robot robot = new Robot("0 1 W", grid);

        // WHEN It moves forward
        robot.processInstruction(F);

        // THEN It ends up where expected
        Point location = robot.getPoint();
        assertEquals(0, location.x());
        assertEquals(1, location.y());
        assertTrue(robot.isLost());
    }

    @Test
    public void testTurnLeftNorthWest() {
        // GIVEN A robot which is facing north
        Grid grid = new Grid("10 20");
        Robot robot = new Robot("1 1 N", grid);

        // WHEN It turns left
        robot.processInstruction(L);

        // THEN It ends up where expected
        Point location = robot.getPoint();
        assertEquals(1, location.x());
        assertEquals(1, location.y());
        assertEquals(W, robot.getDirection());
    }

    @Test
    public void testTurnRightEastSouth() {
        // GIVEN A robot which is facing East
        Grid grid = new Grid("10 20");
        Robot robot = new Robot("1 1 E", grid);

        // WHEN It turns right
        robot.processInstruction(R);

        // THEN It ends up where expected
        Point location = robot.getPoint();
        assertEquals(1, location.x());
        assertEquals(1, location.y());
        assertEquals(S, robot.getDirection());
    }
}
