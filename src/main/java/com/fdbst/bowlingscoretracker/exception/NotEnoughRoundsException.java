package com.fdbst.bowlingscoretracker.exception;

public class NotEnoughRoundsException extends Exception {

    public NotEnoughRoundsException() {
        super("Not enough rounds on import file");
    }
}
