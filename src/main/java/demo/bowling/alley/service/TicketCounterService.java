package demo.bowling.alley.service;

import demo.bowling.alley.SingletonClass;
import demo.bowling.alley.entities.Lane;
import demo.bowling.alley.entities.PlayerGroup;
import demo.bowling.alley.exceptions.LaneNotAvailableException;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TicketCounterService {
    private int isLaneAvailable(){
//        System.out.println("Thread Count : " + Thread.activeCount());
//        //return false if max thread pool size reached else return false
//        if(Thread.activeCount()==10){
//            return false;
//        }else {
//            return true;
//        }
        int key = -1;
        for(Map.Entry<Integer, Lane> entry : SingletonClass.getInstance().getLaneMap().entrySet()){
            if(entry.getValue()==null){
                key = entry.getKey();
                break;
            }
        }
        return key;
    }
    // Make it thread safe : Synchronous call to assign lane
    @Synchronized
    public Lane assignLane(PlayerGroup group) throws LaneNotAvailableException {
        int key = isLaneAvailable();
        if(key==-1){
            throw new LaneNotAvailableException("Sorry all lanes are busy !!");
        }
        Lane l = Lane.builder().laneNo(key).playerGroup(group).build();
        SingletonClass.getInstance().getLaneMap().put(key, l);
        return l;
    }

    public void unAssignLane(int key)  {
        SingletonClass.getInstance().getLaneMap().put(key, null);
    }
}
