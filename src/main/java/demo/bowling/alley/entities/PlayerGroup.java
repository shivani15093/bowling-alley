package demo.bowling.alley.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PlayerGroup {
    private String groupName;
    private List<Player> members;
}
