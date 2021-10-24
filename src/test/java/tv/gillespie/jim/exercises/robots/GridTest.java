package tv.gillespie.jim.exercises.robots;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GridTest {
    @Test
    public void testGridXTooLarge() {
        // GIVEN A grid whose maximum X coordinate is too large
        // WHEN It is created
        // THEN Creation fails
        assertThrows(IllegalArgumentException.class, () -> {
            Grid grid = new Grid(String.format("%d %d", Grid.MAX_COORDINATE_VALUE + 1, 10));
        });
    }

    @Test
    public void testGridYTooLarge() {
        // GIVEN A grid whose maximum Y coordinate is too large
        // WHEN It is created
        // THEN Creation fails
        assertThrows(IllegalArgumentException.class, () -> {
            Grid grid = new Grid(String.format("%d %d", 10, Grid.MAX_COORDINATE_VALUE + 1));
        });
    }

    @Test
    public void testAddRobotOffGrid() {
        // GIVEN A grid
        Grid grid = new Grid("10 10");

        // WHEN I add a robot outside the grid
        // THEN It throws an exception
        assertThrows(IllegalArgumentException.class, () -> grid.addRobot("11 5 N", "F"));
    }

    @Test
    public void testMoveRobotSimple() {
        // GIVEN A grid
        Grid grid = new Grid("10 10");

        // WHEN I add and move a robot
        grid.addRobot("1 1 N", "F");

        // THEN It ends up where I expect
        assertEquals(grid.getRobotDetails().get(0), "1 2 N");
    }

    @Test
    public void testMoveRobotOffNorth() {
        // GIVEN A grid
        Grid grid = new Grid("10 10");

        // WHEN I add and move a robot
        grid.addRobot("5 5 N", "FFFFFF");

        // THEN It ends up where I expect
        assertEquals(grid.getRobotDetails().get(0), "5 10 N LOST");
    }

    @Test
    public void testMoveTwoRobotsOffNorth() {
        // GIVEN A grid
        Grid grid = new Grid("10 10");

        // WHEN I add and move two robots
        grid.addRobot("5 5 N", "FFFFFF");
        grid.addRobot("5 5 N", "FFFFFF");

        // THEN The second robot won't get lost
        assertEquals(grid.getRobotDetails().get(0), "5 10 N LOST");
        assertEquals(grid.getRobotDetails().get(1), "5 10 N");
    }

    @Test
    public void testInstructionsAfterSaveFollowed() {
        // GIVEN A grid
        Grid grid = new Grid("10 10");

        // WHEN I add and move two robots
        grid.addRobot("5 5 N", "FFFFFF");
        grid.addRobot("5 5 N", "FFFFFFRF");

        // THEN The second robot won't get lost, and will follow all valid instructions
        assertEquals(grid.getRobotDetails().get(0), "5 10 N LOST");
        assertEquals(grid.getRobotDetails().get(1), "6 10 E");
    }

    @Test
    public void testBadInstruction() {
        // GIVEN A grid
        Grid grid = new Grid("10 10");

        // WHEN I add a robot with a bad instruction
        // THEN An exception will be thrown
        assertThrows(IllegalArgumentException.class, () -> grid.addRobot("1 1 E", "FLA"));
    }

    @Test
    public void testBadLocation() {
        // GIVEN A grid
        Grid grid = new Grid("10 10");

        // WHEN I add a robot with a bad location
        // THEN An exception will be thrown
        assertThrows(IllegalArgumentException.class, () -> grid.addRobot("11 1 E", "FFR"));
    }

    @Test
    public void testSampleData() {
        // GIVEN A grid
        Grid grid = new Grid("5 3");

        // WHEN I add and move some robots
        grid.addRobot("1 1 E", "RFRFRFRF");
        grid.addRobot("3 2 N", "FRRFLLFFRRFLL");
        grid.addRobot("0 3 W", "LLFFFLFLFL");

        // THEN They will all end up where expected
        assertEquals(grid.getRobotDetails().get(0), "1 1 E");
        assertEquals(grid.getRobotDetails().get(1), "3 3 N LOST");
        assertEquals(grid.getRobotDetails().get(2), "2 3 S");
    }
}
