package demo.bowling.alley.entities;

import demo.bowling.alley.constants.AppConstants;
import lombok.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Frame {
    private int firstBallScore;
    private int secondBallScore;
    private int totalScore;
    private FrameType frameType;

    @Synchronized
    public int getFrameScore(){
        return this.firstBallScore + this.secondBallScore;
    }
    @Synchronized
    public boolean isStrike(){
        return this.firstBallScore == AppConstants.MAX_POINTS;
    }
    @Synchronized
    public boolean isSpare(){
        return this.getFrameScore() == AppConstants.MAX_POINTS;
    }
    @Synchronized
    public boolean isOpenFrame(){
        return this.getFrameScore() < AppConstants.MAX_POINTS;
    }
}
