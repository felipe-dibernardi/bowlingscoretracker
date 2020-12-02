package com.fdbst.bowlingscoretracker.exception;

public class InvalidThirdValueException extends Exception {

    public InvalidThirdValueException() {
        super("First and second try sums < 10. Cannot have a third try");
    }
}
