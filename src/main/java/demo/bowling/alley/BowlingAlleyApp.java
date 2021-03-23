package demo.bowling.alley;

import demo.bowling.alley.exceptions.LaneNotAvailableException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class BowlingAlleyApp {

    public static void main( String[] args ) throws LaneNotAvailableException
    {
        SpringApplication.run(BowlingAlleyApp.class, args);

    }
}
