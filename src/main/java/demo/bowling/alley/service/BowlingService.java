package demo.bowling.alley.service;

import demo.bowling.alley.constants.AppConstants;
import demo.bowling.alley.entities.Frame;
import demo.bowling.alley.entities.FrameType;
import demo.bowling.alley.entities.Lane;
import demo.bowling.alley.entities.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BowlingService {

    @Autowired
    TicketCounterService ticketCounterService;

    @Async
    public CompletableFuture<Lane> start(Lane lane){
        Logger logger = LoggerFactory.getLogger(BowlingService.class);
        System.out.println("GAME BEGINS !!");
        logger.info("Method running for thread {}", Thread.currentThread().getName());
        int idx = 0;
        int frame = 0;
        List<Player> playerList = lane.getPlayerGroup().getMembers();
        do{
            for(Player p : playerList){
                //First ball
                int score1 = p.bowl(AppConstants.MAX_POINTS+1);
                Frame frame1 = Frame.builder().firstBallScore(score1).build();
                p.getScore().getFrames().add(frame1);
                p.getScore().getFrames().get(idx).setFirstBallScore(score1);
                if(p.getScore().getFrames().get(idx).isStrike()){
                    //Strike
                    p.getScore().getFrames().get(idx).setFrameType(FrameType.STRIKE);
                    p.getScore().getFrames().get(idx).setTotalScore(p.getScore().getFrames().get(idx).getFrameScore());
                }else {
                    //Second ball
                    int score2 = p.bowl(AppConstants.MAX_POINTS-score1+1);
                    frame1.setSecondBallScore(score2);
                    p.getScore().getFrames().set(idx, frame1);

                    if(p.getScore().getFrames().get(idx).isSpare()){
                        //Spare
                        p.getScore().getFrames().get(idx).setFrameType(FrameType.SPARE);
                        p.getScore().getFrames().get(idx).setTotalScore(p.getScore().getFrames().get(idx).getFrameScore());
                    }
                    else {
                        //Open
                        p.getScore().getFrames().get(idx).setFrameType(FrameType.OPEN);
                        p.getScore().getFrames().get(idx).setTotalScore(p.getScore().getFrames().get(idx).getFrameScore());
                    }
                }


                if(idx<3){
                    if(idx >= 1 && p.getScore().getFrames().get(idx-1).getFrameType().equals(FrameType.SPARE)){
                        int s = p.getScore().getFrames().get(idx-1).getFrameScore();
                        p.getScore().getFrames().get(idx-1).setTotalScore(s + p.getScore().getFrames().get(idx).getFirstBallScore());
                    }

                    if(idx >= 1 && p.getScore().getFrames().get(idx-1).getFrameType().equals(FrameType.STRIKE)
                            && !p.getScore().getFrames().get(idx).getFrameType().equals(FrameType.STRIKE)){

                        int s = p.getScore().getFrames().get(idx-1).getFrameScore();
                        p.getScore().getFrames().get(idx-1).setTotalScore(s
                                + p.getScore().getFrames().get(idx).getFirstBallScore()
                                + p.getScore().getFrames().get(idx).getSecondBallScore());
                    }

                    if(idx >= 2 && p.getScore().getFrames().get(idx-2).getFrameType().equals(FrameType.STRIKE)
                            && p.getScore().getFrames().get(idx-1).getFrameType().equals(FrameType.STRIKE)){

                        int s = p.getScore().getFrames().get(idx-2).getFrameScore();
                        p.getScore().getFrames().get(idx-2).setTotalScore(s
                                + p.getScore().getFrames().get(idx-1).getFirstBallScore()
                                + p.getScore().getFrames().get(idx).getFirstBallScore());

                    }

                }


                if(idx >= 3){
                    if(idx==4 && p.getScore().getFrames().get(idx-1).getFrameType().equals(FrameType.SPARE)){
                        int s = p.getScore().getFrames().get(idx).getFrameScore();
                        p.getScore().getFrames().get(idx-1).setTotalScore(s + p.getScore().getFrames().get(idx).getFrameScore());
                    }
                    if(idx==4 && p.getScore().getFrames().get(idx).getFrameType().equals(FrameType.SPARE)
                            && p.getScore().getFrames().get(idx-1).getFrameType().equals(FrameType.STRIKE)){
                        int s = p.getScore().getFrames().get(idx-1).getFrameScore();
                        p.getScore().getFrames().get(idx-1).setTotalScore(s + p.getScore().getFrames().get(idx).getFirstBallScore()
                                + p.getScore().getFrames().get(idx).getSecondBallScore());
                    }
                    if(idx==4 && p.getScore().getFrames().get(idx).getFrameType().equals(FrameType.SPARE)){
                        int s = p.getScore().getFrames().get(idx).getFrameScore();
                        p.getScore().getFrames().get(idx).setTotalScore(s + p.bowl(AppConstants.MAX_POINTS+1));
                    }
                    //Last and second last
                    if(idx==4 && p.getScore().getFrames().get(idx-1).getFrameType().equals(FrameType.STRIKE)
                            && p.getScore().getFrames().get(idx).getFrameType().equals(FrameType.STRIKE)){
                        int s1 = p.getScore().getFrames().get(idx-1).getFrameScore();
                        int bonus1 = p.bowl(AppConstants.MAX_POINTS+1);
                        int bonus2 = p.bowl(AppConstants.MAX_POINTS+1);
                        p.getScore().getFrames().get(idx-1).setTotalScore(s1 + p.getScore().getFrames().get(idx).getFirstBallScore() + bonus1);
                        int s2 = p.getScore().getFrames().get(idx).getFrameScore();
                        p.getScore().getFrames().get(idx).setTotalScore(s2 + bonus1 + bonus2);
                    }
                    if(idx==4 && p.getScore().getFrames().get(idx).getFrameType().equals(FrameType.STRIKE)
                            && !p.getScore().getFrames().get(idx-1).getFrameType().equals(FrameType.STRIKE)){
                        int s = p.getScore().getFrames().get(idx).getFrameScore();
                        p.getScore().getFrames().get(idx).setTotalScore(s + p.bowl(AppConstants.MAX_POINTS+1) + p.bowl(AppConstants.MAX_POINTS+1));
                    }

                }

            }

            System.out.println("==================== SCORES AFTER FRAME NUMBER " + frame + "====================");

            for(Player player : playerList){
                if(idx>=1){
                    System.out.println(" Frame Score : " + player.getScore().getFrames().subList(0, idx));
                }
                else{
                    System.out.println(" Frame Score : " + player.getScore().getFrames().get(0));
                }
            }
            idx++;
            frame++;
        }while(frame<5);
        // unassign lane
        ticketCounterService.unAssignLane(lane.getLaneNo());
        System.out.println("GAME ENDS !!");
        return CompletableFuture.completedFuture(lane);
    }
}
