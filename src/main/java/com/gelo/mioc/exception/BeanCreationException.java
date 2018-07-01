package com.gelo.mioc.exception;

/**
 * The type Bean creation exception.
 */
public class BeanCreationException extends RuntimeException {
    /**
     * Instantiates a new Bean creation exception.
     */
    public BeanCreationException() {
    }

    /**
     * Instantiates a new Bean creation exception.
     *
     * @param message the message
     */
    public BeanCreationException(String message) {
        super(message);
    }
}
