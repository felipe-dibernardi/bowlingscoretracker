package com.fdbst.bowlingscoretracker.service;

import com.fdbst.bowlingscoretracker.exception.*;

import java.io.FileNotFoundException;

public interface ScoreProcessor {

    String processScore(String filePath) throws FileNotFoundException, InvalidValueException, InvalidScoreException,
            InvalidThirdValueException, NoPlayersException, TooManyRoundsException, NotEnoughRoundsException;

}
