package demo.bowling.alley.service;

import demo.bowling.alley.entities.Lane;
import demo.bowling.alley.entities.PlayerGroup;
import demo.bowling.alley.exceptions.LaneNotAvailableException;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

@Service
public class TicketCounterService {
    private boolean isLaneAvailable(){
        System.out.println("Thread Count : " + Thread.activeCount());
        //return false if max thread pool size reached else return false
        if(Thread.activeCount()==10){
            return false;
        }else {
            return true;
        }

    }
    // Make it thread safe : Synchronous call to assign lane
    @Synchronized
    public Lane assignLane(PlayerGroup group) throws LaneNotAvailableException {
        if(!isLaneAvailable()){
            throw new LaneNotAvailableException("Sorry all lanes are busy !!");
        }
        return Lane.builder().playerGroup(group).build();
    }
}
