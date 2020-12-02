package com.fdbst.bowlingscoretracker.exception;

public class TooManyRoundsException extends Exception {

    public TooManyRoundsException() {
        super("Too many rounds on import file");
    }
}
