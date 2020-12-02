package com.fdbst.bowlingscoretracker.service.impl;

import com.fdbst.bowlingscoretracker.model.Player;
import com.fdbst.bowlingscoretracker.model.Round;
import com.fdbst.bowlingscoretracker.service.ScoreCalculator;
import com.fdbst.bowlingscoretracker.utils.PlayUtils;

import java.util.ArrayList;
import java.util.List;

public class TraditionalBowlingScoreCalculator implements ScoreCalculator {

    @Override
    public void calculateScore(Player player) {
        List<Round> normalizedRounds = normalizeRounds(player);
        for (int i = 0; i < normalizedRounds.size(); i++) {
            Round normalizedRound = normalizedRounds.get(i);
            Round actualRound = player.getRounds().get(i);
            if (i != normalizedRounds.size() - 1
                    || (i == normalizedRounds.size() && !normalizedRound.isStrike() && !normalizedRound.isSpare())) {
                actualRound.setAccumulatedScore(normalizedRound.getFirstTry() + normalizedRound.getSecondTry());
            } else {
                actualRound.setAccumulatedScore(normalizedRound.getFirstTry() + normalizedRound.getSecondTry()
                        + normalizedRound.getThirdTry());
            }

            if (i != 0) {
                Round previousRound = player.getRounds().get(i - 1);
                if (previousRound.isSpare()) {
                    previousRound.setAccumulatedScore(previousRound.getAccumulatedScore()
                            + normalizedRound.getFirstTry());
                } else if (previousRound.isStrike()) {
                    if (i != 1) {
                        Round secondToLastRound = player.getRounds().get(i -2);
                        if (secondToLastRound.isStrike()) {
                            secondToLastRound.setAccumulatedScore(secondToLastRound.getAccumulatedScore()
                                    + normalizedRound.getFirstTry());
                            previousRound.setAccumulatedScore(previousRound.getAccumulatedScore()
                                    + normalizedRound.getFirstTry());
                        }
                    }
                    previousRound.setAccumulatedScore(previousRound.getAccumulatedScore()
                            + normalizedRound.getFirstTry() + normalizedRound.getSecondTry());
                }
                actualRound.setAccumulatedScore(actualRound.getAccumulatedScore() + previousRound.getAccumulatedScore());
            }
        }
    }

    private List<Round> normalizeRounds(Player player) {
        List<Round> normalizedRounds = new ArrayList<>();
        player.getRounds().forEach(round -> normalizedRounds.add(new Round(round)));
        normalizedRounds.forEach(round -> {
            round.setFirstTry(PlayUtils.normalizePlay(round.getFirstTry()));
            round.setSecondTry(PlayUtils.normalizePlay(round.getSecondTry()));
            round.setThirdTry(PlayUtils.normalizePlay(round.getThirdTry()));
        });
        return normalizedRounds;
    }
}
