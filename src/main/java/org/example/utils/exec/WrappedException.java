package org.example.utils.exec;

public class WrappedException extends RuntimeException {

    public WrappedException(String message) {
        super(message);
        this.fillInStackTrace();
    }

    public WrappedException(Throwable throwable) {
        this(throwable.getMessage(), throwable);
    }

    public WrappedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
