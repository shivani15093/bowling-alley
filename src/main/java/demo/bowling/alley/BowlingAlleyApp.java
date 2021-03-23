package demo.bowling.alley;

import demo.bowling.alley.entities.Frames;
import demo.bowling.alley.entities.Player;
import demo.bowling.alley.entities.PlayerGroup;
import demo.bowling.alley.exceptions.LaneNotAvailableException;
import demo.bowling.alley.service.BowlingService;
import demo.bowling.alley.service.TicketCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
@SpringBootApplication
public class BowlingAlleyApp {
//    @Autowired
//    static Controller c;

    public static void main( String[] args ) throws LaneNotAvailableException
    {
        SpringApplication.run(BowlingAlleyApp.class, args);
//        c.startGame();


    }
}
