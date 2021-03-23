package demo.bowling.alley.entities;

import lombok.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Player {
    private String name;
    private Frames score;
    @Synchronized
    public int bowl(int n){
        Random rand = new Random();
        return rand.nextInt(n);
    }

}
