package exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Hmmm, user can't be found!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
