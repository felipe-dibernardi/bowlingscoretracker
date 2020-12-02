package com.fdbst.bowlingscoretracker.exception;

public class NoFileException extends Exception {

    public NoFileException() {
        super("No file especified on execution.");
    }
}
