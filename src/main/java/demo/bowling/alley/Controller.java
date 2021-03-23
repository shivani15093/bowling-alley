package demo.bowling.alley;

import demo.bowling.alley.entities.Frames;
import demo.bowling.alley.entities.Lane;
import demo.bowling.alley.entities.Player;
import demo.bowling.alley.entities.PlayerGroup;
import demo.bowling.alley.exceptions.LaneNotAvailableException;
import demo.bowling.alley.service.BowlingService;
import demo.bowling.alley.service.TicketCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class Controller {

    @Autowired
    BowlingService bowlingService;
    @Autowired
    TicketCounterService ticketCounterService;
    @PostMapping(value = "/startGame")
    public void startGame() throws LaneNotAvailableException, ExecutionException, InterruptedException {
        List<Player> players1 = new ArrayList<>();
        players1.add(Player.builder().name("a").score(Frames.builder().frames(new ArrayList<>()).build()).build());
        players1.add(Player.builder().name("b").score(Frames.builder().frames(new ArrayList<>()).build()).build());
        players1.add(Player.builder().name("c").score(Frames.builder().frames(new ArrayList<>()).build()).build());
        players1.add(Player.builder().name("d").score(Frames.builder().frames(new ArrayList<>()).build()).build());
        players1.add(Player.builder().name("e").score(Frames.builder().frames(new ArrayList<>()).build()).build());


        PlayerGroup playerGroup1 = PlayerGroup.builder()
                .groupName("Group_1")
                .members(players1).build();

        CompletableFuture<Lane> l1 = bowlingService.start(ticketCounterService.assignLane(playerGroup1));
        System.out.println("==================== FINAL SCORES LANE 1 " + "====================");

        for(Player p : l1.get().getPlayerGroup().getMembers()){
            System.out.println("Player Name : " + p.getName()
                    + "\nScore : " + p.getScore().getFrames());
        }

        List<Player> players2 = new ArrayList<>();
        players2.add(Player.builder().name("a2").score(Frames.builder().frames(new ArrayList<>()).build()).build());
        players2.add(Player.builder().name("b2").score(Frames.builder().frames(new ArrayList<>()).build()).build());
        players2.add(Player.builder().name("c2").score(Frames.builder().frames(new ArrayList<>()).build()).build());
        players2.add(Player.builder().name("d2").score(Frames.builder().frames(new ArrayList<>()).build()).build());
        players2.add(Player.builder().name("e2").score(Frames.builder().frames(new ArrayList<>()).build()).build());


        PlayerGroup playerGroup2 = PlayerGroup.builder()
                .groupName("Group_2")
                .members(players2).build();

        CompletableFuture<Lane> l2 = bowlingService.start(ticketCounterService.assignLane(playerGroup2));

        System.out.println("==================== FINAL SCORES LANE 2 " + "====================");
        for(Player p : l2.get().getPlayerGroup().getMembers()){
            System.out.println("Player Name : " + p.getName()
                    + "\nScore : " + p.getScore().getFrames());
        }


    }

}
