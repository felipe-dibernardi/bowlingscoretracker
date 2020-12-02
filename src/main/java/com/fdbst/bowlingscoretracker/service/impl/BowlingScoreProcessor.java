package com.fdbst.bowlingscoretracker.service.impl;

import com.fdbst.bowlingscoretracker.exception.*;
import com.fdbst.bowlingscoretracker.model.Player;
import com.fdbst.bowlingscoretracker.service.ScoreCalculator;
import com.fdbst.bowlingscoretracker.service.ScorePrinter;
import com.fdbst.bowlingscoretracker.service.ScoreProcessor;
import com.fdbst.bowlingscoretracker.service.ScoreReader;

import java.io.FileNotFoundException;
import java.util.List;

public class BowlingScoreProcessor implements ScoreProcessor {

    private ScoreReader scoreReader;

    private ScoreCalculator scoreCalculator;

    private ScorePrinter scorePrinter;

    public BowlingScoreProcessor(ScoreReader scoreReader, ScoreCalculator scoreCalculator, ScorePrinter scorePrinter) {
        this.scoreReader = scoreReader;
        this.scoreCalculator = scoreCalculator;
        this.scorePrinter = scorePrinter;
    }

    @Override
    public String processScore(String filePath) throws FileNotFoundException, InvalidValueException, InvalidScoreException,
            InvalidThirdValueException, NoPlayersException, TooManyRoundsException, NotEnoughRoundsException{
        List<Player> players = scoreReader.readScore(filePath);
        players.forEach(player -> scoreCalculator.calculateScore(player));
        return scorePrinter.printScore(players);
    }
}
