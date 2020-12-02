package com.fdbst.bowlingscoretracker.service.impl;

import com.fdbst.bowlingscoretracker.model.Round;
import com.fdbst.bowlingscoretracker.model.Player;
import com.fdbst.bowlingscoretracker.service.ScoreCalculator;

public class TraditionalBowlingScoreCalculator implements ScoreCalculator {

    @Override
    public void calculateScore(Player player) {
        for (int i = 0; i < player.getRounds().size(); i++) {
            Round round = player.getRounds().get(i);
            if (i != player.getRounds().size() - 1
                    || (i == player.getRounds().size() && !round.isStrike() && !!round.isSpare())) {
                round.setAccumulatedScore(round.getFirstTry() + round.getSecondTry());
            } else {
                round.setAccumulatedScore(round.getFirstTry() + round.getSecondTry() + round.getThirdTry());
            }

            if (i != 0) {
                Round previousRound = player.getRounds().get(i - 1);
                if (previousRound.isSpare()) {
                    previousRound.setAccumulatedScore(previousRound.getAccumulatedScore() + round.getFirstTry());
                } else if (previousRound.isStrike()) {
                    if (i != 1) {
                        Round secondToLastRound = player.getRounds().get(i - 2);
                        if (secondToLastRound.isStrike()) {
                            secondToLastRound.setAccumulatedScore(secondToLastRound.getAccumulatedScore()
                                    + round.getFirstTry());
                            previousRound.setAccumulatedScore(previousRound.getAccumulatedScore()
                                    + round.getFirstTry());
                        }
                    }
                    previousRound.setAccumulatedScore(previousRound.getAccumulatedScore()
                            + round.getFirstTry() + round.getSecondTry());
                }
                round.setAccumulatedScore(round.getAccumulatedScore() + previousRound.getAccumulatedScore());
            }
        }
    }
}
