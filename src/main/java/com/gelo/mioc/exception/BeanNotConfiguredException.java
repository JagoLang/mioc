package com.gelo.mioc.exception;

/**
 * The type Bean not configured exception.
 */
public class BeanNotConfiguredException extends RuntimeException {
    /**
     * Instantiates a new Bean not configured exception.
     */
    public BeanNotConfiguredException() {
    }

    /**
     * Instantiates a new Bean not configured exception.
     *
     * @param message the message
     */
    public BeanNotConfiguredException(String message) {
        super(message);
    }
}
