package demo.bowling.alley.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Lane {
    private int laneNo;
    private PlayerGroup playerGroup;
    private static int MAX_PLAYERS_IN_A_LANE = 5;
}
