package com.fdbst.bowlingscoretracker.exception;

public class TooManyFilesException extends Exception {

    public TooManyFilesException() {
        super("More than one file especified on execution");
    }

}
