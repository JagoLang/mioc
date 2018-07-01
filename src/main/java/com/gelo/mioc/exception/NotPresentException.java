package com.gelo.mioc.exception;

/**
 * The type Not present exception.
 */
public class NotPresentException extends RuntimeException {
    /**
     * Instantiates a new Not present exception.
     */
    public NotPresentException() {
    }

    /**
     * Instantiates a new Not present exception.
     *
     * @param message the message
     */
    public NotPresentException(String message) {
        super(message);
    }
}
