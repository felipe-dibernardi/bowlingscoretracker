package com.fdbst.bowlingscoretracker;

import com.fdbst.bowlingscoretracker.exception.*;
import com.fdbst.bowlingscoretracker.service.ScoreCalculator;
import com.fdbst.bowlingscoretracker.service.ScorePrinter;
import com.fdbst.bowlingscoretracker.service.ScoreProcessor;
import com.fdbst.bowlingscoretracker.service.ScoreReader;
import com.fdbst.bowlingscoretracker.service.impl.BowlingScorePrinter;
import com.fdbst.bowlingscoretracker.service.impl.BowlingScoreProcessor;
import com.fdbst.bowlingscoretracker.service.impl.BowlingScoreReader;
import com.fdbst.bowlingscoretracker.service.impl.TraditionalBowlingScoreCalculator;

import java.io.FileNotFoundException;

public class Application {

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new NoFileException();
            } else if (args.length > 1) {
                throw new TooManyFilesException();
            }
            ScoreReader scoreReader = new BowlingScoreReader();
            ScoreCalculator scoreCalculator = new TraditionalBowlingScoreCalculator();
            ScorePrinter scorePrinter = new BowlingScorePrinter();
            ScoreProcessor scoreProcessor = new BowlingScoreProcessor(scoreReader, scoreCalculator, scorePrinter);

            System.out.println(scoreProcessor.processScore(args[0]));
        } catch (InvalidValueException | InvalidScoreException | InvalidThirdValueException
                | NoPlayersException | TooManyRoundsException | NotEnoughRoundsException
                | NoFileException | TooManyFilesException | FileNotFoundException e) {
            System.out.println("Error on executing application: " + e.getMessage());
        }
    }
}
