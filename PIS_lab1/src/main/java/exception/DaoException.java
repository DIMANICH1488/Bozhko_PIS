package exception;

public class DaoException extends Exception {
    public DaoException(){
        super("There are exceptions with DAOs");
    }

    public DaoException(String message){
        super(message);
    }

    public DaoException(String message, Throwable cause){
        super(message,cause);
    }
}
