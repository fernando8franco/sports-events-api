package zyx.franco.sports_events_api.exceptions;

public class InvalidTeamSizeException extends RuntimeException {
    public InvalidTeamSizeException(String message) {
        super(message);
    }
}