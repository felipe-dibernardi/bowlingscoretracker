package com.fdbst.bowlingscoretracker.exception;

public class NoPlayersException extends Exception {

    public NoPlayersException() {
        super("No Players found. Is the file empty?");
    }
}
