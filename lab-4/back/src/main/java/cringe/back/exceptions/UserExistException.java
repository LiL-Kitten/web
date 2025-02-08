package cringe.back.exceptions;

public class UserExistException extends Exception {
    public UserExistException(String message) {
        super(message);
    }
}
