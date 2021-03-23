package demo.bowling.alley;

import demo.bowling.alley.entities.Lane;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class SingletonClass {
    private static SingletonClass singleInstance;
    Map<Integer, Lane> laneMap;

    private SingletonClass(Map<Integer, Lane> laneMap) {
        this.laneMap = laneMap;
    }

    synchronized public static SingletonClass getInstance(){
        // To ensure only one instance is created
        if (singleInstance == null)
        {
            Map<Integer, Lane> hm = new HashMap<>();
            hm.put(1, null);
            hm.put(2, null);
            hm.put(3, null);
            hm.put(4, null);
            hm.put(5, null);
            singleInstance = new SingletonClass(hm);
        }
        return singleInstance;
    }


}
