package com.fdbst.bowlingscoretracker.service;

import com.fdbst.bowlingscoretracker.model.Player;
import com.fdbst.bowlingscoretracker.model.Round;
import com.fdbst.bowlingscoretracker.service.impl.BowlingScorePrinter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingScorePrinterTest {

    private ScorePrinter scorePrinter = new BowlingScorePrinter();

    private static Player player1;

    private static Player player2;

    private static String player1Scoreboard;

    private static String twoPlayerScoreboard;

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
        player1.getRounds().add(new Round(8, 1, 0));

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

        player1Scoreboard = "Frame\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\t\n" +
                "Jeff\n" +
                "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\t0\t6\t\tX\t\tX\t8\t1\t0\n" +
                "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0";

        twoPlayerScoreboard =  player1Scoreboard + "\n" +
                "John\n" +
                "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\n" +
                "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0";
    }

    @Test
    @DisplayName("Print player score")
    public void printPlayerScore() {
        List<Player> players = new ArrayList<>();
        players.add(player1);
        assertThat(scorePrinter.printScore(players)).isEqualTo(player1Scoreboard);
    }

    @Test
    @DisplayName("Print two players game score")
    public void printTwoPlayersGameScore() {
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        String scoreBoard = scorePrinter.printScore(players);
        assertThat(scoreBoard).isEqualTo(twoPlayerScoreboard);
    }


}
