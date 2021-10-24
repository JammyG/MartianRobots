package tv.gillespie.jim.exercises.robots;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

/**
 * The Grid is created with a fixed size. You can add robots and they will follow their instructions.
 */
public class Grid {
    public final static int MIN_COORDINATE_VALUE = 0;
    public final static int MAX_COORDINATE_VALUE = 50;

    private final int maxX;
    private final int maxY;
    private List<Robot> robots;
    private Set<Point> edgeOfTheWorld;  // This stores the "scent" of by robots which left the grid

    public Grid(String definition) {
        String[] coordinates = definition.trim().split(" +");
        if (coordinates.length != 2) {
            throw new IllegalArgumentException("The grid definition must contain only the coordinates of the top right corner, for example '10 10'");
        }

        this.maxX = Integer.parseInt(coordinates[0]);
        validateCoordinate(maxX, "Max X");
        this.maxY = Integer.parseInt(coordinates[1]);
        validateCoordinate(maxY, "Max Y");

        this.robots = new ArrayList<>();
        this.edgeOfTheWorld = new HashSet<>();
    }

    public static void validateCoordinate(int coordinate, String description) {
        if (coordinate < MIN_COORDINATE_VALUE) {
            throw new IllegalArgumentException(format("Coordinate '%s' %d is too small, must be at least %d", description, coordinate, MIN_COORDINATE_VALUE));
        }

        if (coordinate > MAX_COORDINATE_VALUE) {
            throw new IllegalArgumentException(format("Coordinate '%s' %d is too large, must be at most %d", description, coordinate, MAX_COORDINATE_VALUE));
        }
    }

    /**
     * Add a robot and run its instructions to see where it ends up
     *
     * @param start        The starting definition for the robot
     * @param instructions A set of instructions
     */
    public void addRobot(String start, String instructions) {
        // Create a new Robot and add it to the list
        Robot robot = new Robot(start.trim(), this);
        robots.add(robot);

        // Process the instruction string, after forcing to upper case and splitting into individual letters
        List<Instruction> instructionList = Stream.of(instructions.trim().toUpperCase().split(""))
                .map(Instruction::valueOf)
                .collect(Collectors.toList());

        for (Instruction instruction : instructionList) {
            if (!robot.processInstruction(instruction)) {
                // It fell off the edge, remember the location
                edgeOfTheWorld.add(robot.getPoint());
                // Don't bother with any further instructions
                break;
            }
        }
    }

    /**
     * @param point The point to validate
     * @return true if the point is on the grid, otherwise false
     */
    public boolean validatePoint(Point point) {
        return point.x() >= 0 && point.x() <= maxX && point.y() >= 0 && point.y() <= maxY;
    }

    /**
     * @param point Is this point where a previous robot has left the grid?
     * @return true if it is, otherwise false
     */
    public boolean isOnTheEdge(Point point) {
        return edgeOfTheWorld.contains(point);
    }

    /**
     * @return The current robot details, as a list of Strings
     */
    public List<String> getRobotDetails() {
        return robots.stream()
                .map(Robot::toString)
                .collect(Collectors.toList());
    }

    public String toString() {
        return String.format("Grid[maxX=%d, maxY=%d]", maxX, maxY);
    }
}
