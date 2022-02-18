package com.statistics.exceptions;

/**
 * @author Serhii_Movenko
 */
public class StatisticsFileNotFoundException extends Exception {

    public StatisticsFileNotFoundException(String message) {
        super(message);
    }

    public StatisticsFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
