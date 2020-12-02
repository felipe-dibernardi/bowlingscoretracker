package com.fdbst.bowlingscoretracker.service;

import com.fdbst.bowlingscoretracker.exception.*;
import com.fdbst.bowlingscoretracker.service.impl.BowlingScorePrinter;
import com.fdbst.bowlingscoretracker.service.impl.BowlingScoreProcessor;
import com.fdbst.bowlingscoretracker.service.impl.BowlingScoreReader;
import com.fdbst.bowlingscoretracker.service.impl.TraditionalBowlingScoreCalculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingScoreProcessorIT {

    private ScoreReader scoreReader = new BowlingScoreReader();

    private ScoreCalculator scoreCalculator = new TraditionalBowlingScoreCalculator();

    private ScorePrinter scorePrinter = new BowlingScorePrinter();

    private ScoreProcessor scoreProcessor = new BowlingScoreProcessor(scoreReader, scoreCalculator, scorePrinter);

    private static String twoPlayerRegularGameScoreboard;

    private static String onePlayerRegularScoreboard;

    private static String perfectGameScoreboard;

    private static String zeroGameScoreboard;

    private static String allFaultsGameScoreboard;

    @BeforeAll
    public static void setup() {
        twoPlayerRegularGameScoreboard = "Frame\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\t\n" +
                "Jeff\n" +
                "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\n" +
                "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n" +
                "John\n" +
                "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\n" +
                "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151";

        onePlayerRegularScoreboard = "Frame\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\t\n" +
                "Jeff\n" +
                "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\n" +
                "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167";

        perfectGameScoreboard = "Frame\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\t\n" +
                "Jeff\n" +
                "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX\n" +
                "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300";

        zeroGameScoreboard = "Frame\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\t\n" +
                "Jeff\n" +
                "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n" +
                "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0";

        allFaultsGameScoreboard = "Frame\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\t\n" +
                "Jeff\n" +
                "Pinfalls\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\tF\t0\n" +
                "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0";
    }

    @Test
    @DisplayName("Process regular two player game scores")
    public void regularTwoPlayerGameScores() throws FileNotFoundException, InvalidValueException, InvalidScoreException,
            InvalidThirdValueException, NoPlayersException, TooManyRoundsException, NotEnoughRoundsException {
        String gameScore = scoreProcessor.processScore("src/test/resources/twoPlayers.txt");
        assertThat(gameScore).isEqualTo(twoPlayerRegularGameScoreboard);
    }

    @Test
    @DisplayName("Process regular one player game score")
    public void regularOnePlayerGameScore() throws FileNotFoundException, InvalidValueException, InvalidScoreException,
            InvalidThirdValueException, NoPlayersException, TooManyRoundsException, NotEnoughRoundsException {
        String gameScore = scoreProcessor.processScore("src/test/resources/onePlayer.txt");
        assertThat(gameScore).isEqualTo(onePlayerRegularScoreboard);
    }

    @Test
    @DisplayName("Process a perfect game score")
    public void perfectGameScore() throws FileNotFoundException, InvalidValueException, InvalidScoreException,
            InvalidThirdValueException, NoPlayersException, TooManyRoundsException, NotEnoughRoundsException {
        String gameScore = scoreProcessor.processScore("src/test/resources/perfectGame.txt");
        assertThat(gameScore).isEqualTo(perfectGameScoreboard);
    }

    @Test
    @DisplayName("Process a zero game score")
    public void zeroGameScore() throws FileNotFoundException, InvalidValueException, InvalidScoreException,
            InvalidThirdValueException, NoPlayersException, TooManyRoundsException, NotEnoughRoundsException {
        String gameScore = scoreProcessor.processScore("src/test/resources/zeroGame.txt");
        assertThat(gameScore).isEqualTo(zeroGameScoreboard);
    }

    @Test
    @DisplayName("Process a all faults game score")
    public void allMissesGameScore() throws FileNotFoundException, InvalidValueException, InvalidScoreException,
            InvalidThirdValueException, NoPlayersException, TooManyRoundsException, NotEnoughRoundsException {
        String gameScore = scoreProcessor.processScore("src/test/resources/allFaultsGame.txt");
        assertThat(gameScore).isEqualTo(allFaultsGameScoreboard);
    }

}
