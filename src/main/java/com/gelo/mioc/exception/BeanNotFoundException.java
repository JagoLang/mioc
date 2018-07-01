package com.gelo.mioc.exception;

/**
 * The type Bean not found exception.
 */
public class BeanNotFoundException extends RuntimeException{
    /**
     * Instantiates a new Bean not found exception.
     */
    public BeanNotFoundException() {
    }

    /**
     * Instantiates a new Bean not found exception.
     *
     * @param message the message
     */
    public BeanNotFoundException(String message) {
        super(message);
    }
}
