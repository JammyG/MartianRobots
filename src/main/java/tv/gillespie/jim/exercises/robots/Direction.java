package tv.gillespie.jim.exercises.robots;

/**
 * Directions: North, East, South, West
 */
public enum Direction {
    N, E, S, W;

    public Direction turnLeft() {
        return switch (this) {
            case N -> W;
            case W -> S;
            case S -> E;
            case E -> N;
            default -> throw new IllegalArgumentException(String.format("Add a new branch for '%s'", this.name()));
        };
    }

    public Direction turnRight() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
            default -> throw new IllegalArgumentException(String.format("Add a new branch for '%s'", this.name()));
        };
    }
}
