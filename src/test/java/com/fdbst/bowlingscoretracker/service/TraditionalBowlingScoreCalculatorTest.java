package com.fdbst.bowlingscoretracker.service;

import com.fdbst.bowlingscoretracker.model.Round;
import com.fdbst.bowlingscoretracker.model.Player;
import com.fdbst.bowlingscoretracker.service.impl.TraditionalBowlingScoreCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TraditionalBowlingScoreCalculatorTest {

    private ScoreCalculator scoreCalculator = new TraditionalBowlingScoreCalculator();

    private Player player;

    private Round firstRound;

    private Round secondRound;

    private Round thirdRound;

    private Round fourthRound;

    private Round fifthRound;

    private Round sixthRound;

    private Round seventhRound;

    private Round eighthRound;

    private Round ninthRound;

    private Round tenthRound;

    @BeforeEach
    public void setup() {
        player = new Player("John");
        firstRound = new Round(3,7);
        secondRound = new Round(6,3);
        thirdRound = new Round(10,0);
        fourthRound = new Round(8,1);
        fifthRound = new Round(10,0);
        sixthRound = new Round(10,0);
        seventhRound = new Round(9,-1);
        eighthRound = new Round(7,3);
        ninthRound = new Round(4,4);
        tenthRound = new Round(10,9,0);
        player.getRounds().add(firstRound);
        player.getRounds().add(secondRound);
        player.getRounds().add(thirdRound);
        player.getRounds().add(fourthRound);
        player.getRounds().add(fifthRound);
        player.getRounds().add(sixthRound);
        player.getRounds().add(seventhRound);
        player.getRounds().add(eighthRound);
        player.getRounds().add(ninthRound);
        player.getRounds().add(tenthRound);

    }

    @Test
    @DisplayName("Calculate successfully the player's final score to regular game")
    public void successfulTotalScoreCalculationRegularGame() {
        scoreCalculator.calculateScore(player);
        assertThat(player.getRounds().get(player.getRounds().size() - 1).getAccumulatedScore()).isEqualTo(151);
    }

    @Test
    @DisplayName("Calculate successfully the player's final score to perfect game")
    public void sucessfulTotalScoreCalculationPerfectGame() {
        setCornerCaseGame(10, 0);

        scoreCalculator.calculateScore(player);
        assertThat(player.getRounds().get(player.getRounds().size() - 1).getAccumulatedScore()).isEqualTo(300);
    }

    @Test
    @DisplayName("Calculate successfully the player's final score to zero game")
    public void sucessfulTotalScoreCalculationZeroGame() {
        setCornerCaseGame(0, 0);

        scoreCalculator.calculateScore(player);
        assertThat(player.getRounds().get(player.getRounds().size() - 1).getAccumulatedScore()).isEqualTo(0);
    }

    private void setCornerCaseGame(int firstTry, int secondTry) {
        firstRound.setFirstTry(firstTry);
        firstRound.setSecondTry(secondTry);
        secondRound.setFirstTry(firstTry);
        secondRound.setSecondTry(secondTry);
        thirdRound.setFirstTry(firstTry);
        thirdRound.setSecondTry(secondTry);
        fourthRound.setFirstTry(firstTry);
        fourthRound.setSecondTry(secondTry);
        fifthRound.setFirstTry(firstTry);
        fifthRound.setSecondTry(secondTry);
        sixthRound.setFirstTry(firstTry);
        sixthRound.setSecondTry(secondTry);
        seventhRound.setFirstTry(firstTry);
        seventhRound.setSecondTry(secondTry);
        eighthRound.setFirstTry(firstTry);
        eighthRound.setSecondTry(secondTry);
        ninthRound.setFirstTry(firstTry);
        ninthRound.setSecondTry(secondTry);
        tenthRound.setFirstTry(firstTry);
        tenthRound.setSecondTry(firstTry);
        tenthRound.setThirdTry(firstTry);
    }

}
