package com.fdbst.bowlingscoretracker.exception;

public class InvalidScoreException extends Exception {
    public InvalidScoreException(Integer value) {
        super("Invalid sum of pins: " + value);
    }
}
