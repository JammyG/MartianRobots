package tv.gillespie.jim.exercises.robots;

import static tv.gillespie.jim.exercises.robots.Grid.validateCoordinate;

/**
 * A robot is defined by its location on the grid and the direction in which it is facing
 */
public class Robot {
    private Point point;
    private Direction direction;
    private Grid grid;
    private boolean lost = false;

    public Robot(String start, Grid grid) {
        String[] coordinates = start.split(" +");
        if (coordinates.length != 3) {
            throw new IllegalArgumentException("The start position must consist of two coordinates and a direction");
        }

        // Extract and validate the X and Y coordinates
        int startX = Integer.parseInt(coordinates[0]);
        validateCoordinate(startX, "Robot start X");
        int startY = Integer.parseInt(coordinates[1]);
        validateCoordinate(startY, "Robot start Y");
        this.point = new Point(startX, startY);

        if (!grid.validatePoint(point)) {
            throw new IllegalArgumentException(String.format("%s is not valid on %s", point, grid));
        }

        // Extract and validate the direction
        this.direction = Direction.valueOf(coordinates[2].toUpperCase());

        this.grid = grid;
    }

    /**
     * Process one instruction character
     *
     * @param instruction A singe instruction
     * @return true if the robot is still on the grid after processing the instruction, otherwise false
     */
    public boolean processInstruction(Instruction instruction) {
        return switch (instruction) {
            case L -> {
                direction = direction.turnLeft();
                yield true;
            }
            case R -> {
                direction = direction.turnRight();
                yield true;
            }
            case F -> {
                int newX = point.x();
                int newY = point.y();
                switch (direction) {
                    case N -> newY = point.y() + 1;
                    case E -> newX = point.x() + 1;
                    case S -> newY = point.y() - 1;
                    case W -> newX = point.x() - 1;
                }
                Point newPoint = new Point(newX, newY);

                if (grid.validatePoint(newPoint)) {
                    // If the new point is valid, move to it
                    point = newPoint;
                    yield true;
                } else if (grid.isOnTheEdge(point)) {
                    // If a previous robot has marked this as being on the edge of the grid, ignore the instruction
                    yield true;
                } else {
                    // Don't move, but mark yourself as lost
                    lost = true;
                    yield false;
                }
            }
        };
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isLost() {
        return lost;
    }

    /**
     * @return The current location, direction and whether the robot is lost
     */
    public String toString() {
        return String.format("%d %d %s%s", point.x(), point.y(), direction.name(), (lost ? " LOST" : ""));
    }
}
