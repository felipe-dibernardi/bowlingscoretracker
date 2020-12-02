package com.fdbst.bowlingscoretracker.service;

import com.fdbst.bowlingscoretracker.exception.*;
import com.fdbst.bowlingscoretracker.model.Player;

import java.io.FileNotFoundException;
import java.util.List;

public interface ScoreReader {

    List<Player> readScore(String filePath) throws FileNotFoundException, InvalidValueException, InvalidScoreException,
            NoPlayersException, TooManyRoundsException, NotEnoughRoundsException;

}
