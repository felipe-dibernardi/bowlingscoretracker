package com.fdbst.bowlingscoretracker.service;

import com.fdbst.bowlingscoretracker.exception.*;
import com.fdbst.bowlingscoretracker.model.Round;
import com.fdbst.bowlingscoretracker.model.Player;
import com.fdbst.bowlingscoretracker.service.impl.BowlingScoreReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class BowlingScoreReaderTest {

    private ScoreReader scoreReader = new BowlingScoreReader();

    private static Player player1;

    private static Player player2;

    @BeforeAll
    public static void setup() {
        player1 = new Player("Jeff");
        player1.getRounds().add(new Round(10, 0));
        player1.getRounds().add(new Round(7, 3));
        player1.getRounds().add(new Round(9, 0));
        player1.getRounds().add(new Round(10, 0));
        player1.getRounds().add(new Round(0, 8));
        player1.getRounds().add(new Round(8, 2));
        player1.getRounds().add(new Round(0, 6));
        player1.getRounds().add(new Round(10, 0));
        player1.getRounds().add(new Round(10, 0));
        player1.getRounds().add(new Round(10, 8, 1));

        player2 = new Player("John");
        player2.getRounds().add(new Round(3, 7));
        player2.getRounds().add(new Round(6, 3));
        player2.getRounds().add(new Round(10, 0));
        player2.getRounds().add(new Round(8, 1));
        player2.getRounds().add(new Round(10, 0));
        player2.getRounds().add(new Round(10, 0));
        player2.getRounds().add(new Round(9, 0));
        player2.getRounds().add(new Round(7, 3));
        player2.getRounds().add(new Round(4, 4));
        player2.getRounds().add(new Round(10, 9,0));

    }

    @Test
    @DisplayName("Read a score with two players")
    public void readScoreTwoPlayers() throws FileNotFoundException, InvalidValueException, InvalidScoreException,
            NoPlayersException, TooManyRoundsException, NotEnoughRoundsException {
        List<Player> players = scoreReader.readScore("src/test/resources/twoPlayers.txt");

        assertThat(players).hasSize(2);
        assertThat(players.get(0).getRounds()).hasSize(10);
        assertThat(players.get(1).getRounds()).hasSize(10);
        assertPlayersPlay(players.get(0), player1);
        assertPlayersPlay(players.get(1), player2);
    }

    @Test
    @DisplayName("Read a score with one player")
    public void readScoreOnePlayer() throws FileNotFoundException, InvalidValueException, InvalidScoreException,
            NoPlayersException, TooManyRoundsException, NotEnoughRoundsException {
        List<Player> players = scoreReader.readScore("src/test/resources/onePlayer.txt");

        assertThat(players).hasSize(1);
        assertThat(players.get(0).getRounds()).hasSize(10);
        assertPlayersPlay(players.get(0), player1);
    }

    @Test
    @DisplayName("Catch invalid letter on import file")
    public void catchInvalidLetterOnImportFile() {
        assertThatExceptionOfType(InvalidValueException.class)
                .isThrownBy(() -> scoreReader.readScore("src/test/resources/onePlayerInvalidLetter.txt"))
                .withMessage("Invalid value: J. Not an Integer or F");
    }

    @Test
    @DisplayName("Catch invalid float on import file")
    public void catchInvalidFloatOnImportFile() {
        assertThatExceptionOfType(InvalidValueException.class)
                .isThrownBy(() -> scoreReader.readScore("src/test/resources/onePlayerInvalidFloat.txt"))
                .withMessage("Invalid value: 6.2. Not an Integer or F");
    }

    @Test
    @DisplayName("Catch invalid integer on import file")
    public void catchInvalidIntegerOnImportFile() {
        assertThatExceptionOfType(InvalidValueException.class)
                .isThrownBy(() -> scoreReader.readScore("src/test/resources/onePlayerInvalidInteger.txt"))
                .withMessage("Invalid value: 11. Not in the range 0-10");
    }

    @Test
    @DisplayName("Catch invalid negative number on import file")
    public void catchInvalidNegativeOnImportFile() {
        assertThatExceptionOfType(InvalidValueException.class)
                .isThrownBy(() -> scoreReader.readScore("src/test/resources/onePlayerInvalidNegative.txt"))
                .withMessage("Invalid value: -1. Not in the range 0-10");
    }

    @Test
    @DisplayName("Catch not enough rounds on import file")
    public void catchNotEnoughRoundsOnImportFile() {
        assertThatExceptionOfType(NotEnoughRoundsException.class)
                .isThrownBy(() -> scoreReader.readScore("src/test/resources/onePlayerNotEnoughRounds.txt"))
                .withMessage("Not enough rounds on import file");
    }

    @Test
    @DisplayName("Catch too many rounds on import file")
    public void catchTooManyRoundsOnImportFile() {
        assertThatExceptionOfType(TooManyRoundsException.class)
                .isThrownBy(() -> scoreReader.readScore("src/test/resources/onePlayerTooManyRounds.txt"))
                .withMessage("Too many rounds on import file");
    }

    @Test
    @DisplayName("Catch empty file")
    public void catchEmptyFile() {
        assertThatExceptionOfType(NoPlayersException.class)
                .isThrownBy(() -> scoreReader.readScore("src/test/resources/emptyFile.txt"))
                .withMessage("No Players found. Is the file empty?");
    }

    @Test
    @DisplayName("Catch invalid score on import file")
    public void catchInvalidScoreOnImportFile() {
        assertThatExceptionOfType(InvalidScoreException.class)
                .isThrownBy(() -> scoreReader.readScore("src/test/resources/onePlayerInvalidScore.txt"))
                .withMessage("Invalid sum of pins: 18");
    }

    private void assertPlayersPlay(Player actualPlayer, Player expectedPlayer) {
        for (int i = 0; i < actualPlayer.getRounds().size(); i++) {
            Round round = actualPlayer.getRounds().get(i);
            assertThat(round.getFirstTry()).isEqualTo(expectedPlayer.getRounds().get(i).getFirstTry());
            assertThat(round.getSecondTry()).isEqualTo(expectedPlayer.getRounds().get(i).getSecondTry());
            if (i == actualPlayer.getRounds().size() - 1) {
                assertThat(round.getThirdTry()).isEqualTo(expectedPlayer.getRounds().get(i).getThirdTry());
            }
        }
    }

}
