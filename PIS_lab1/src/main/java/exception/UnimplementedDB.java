package exception;

public class UnimplementedDB extends Exception {
    public UnimplementedDB() {
        super("Oops, some of your DBs won't implemented");
    }

    public UnimplementedDB(String message) {
        super(message);
    }

    public UnimplementedDB(String message, Throwable cause) {
        super(message, cause);
    }
}
