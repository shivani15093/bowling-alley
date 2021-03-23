package demo.bowling.alley.exceptions;

public class LaneNotAvailableException extends  Exception {
    public LaneNotAvailableException(String message) {
        super(message);
    }
}
