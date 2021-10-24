package tv.gillespie.jim.exercises.robots;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class reads configuration from a resource file, creates a grid and adds robots to the grid.
 */
public class MartianRobots {
    public static void main(String[] args) throws URISyntaxException, IOException {
        // ASSUMPTION: The instruction file is in the resource folder and is called config.txt
        Path path = Paths.get(MartianRobots.class.getClassLoader().getResource("config.txt").toURI());

        // Extract all non-empty lines from the file
        Stream<String> lines = Files.lines(path);
        List<String> config = lines.filter(l -> !l.isBlank()).collect(Collectors.toList());
        lines.close();

        // There should be an odd number of lines
        if (config.size() % 2 == 0) {
            throw new IllegalArgumentException(String.format("There should be an odd number of non-blank lines in the config, but there are %d", config.size()));
        }

        // The first line holds the grid definition
        Grid grid = new Grid(config.get(0));

        // The rest of the lines are in pairs
        for (int i = 1; i < config.size(); i += 2) {
            String definition = config.get(i);
            String instructions = config.get(i + 1);
            grid.addRobot(definition, instructions);
        }

        // Print out the details of all the robots
        grid.getRobotDetails().forEach(System.out::println);
    }
}
