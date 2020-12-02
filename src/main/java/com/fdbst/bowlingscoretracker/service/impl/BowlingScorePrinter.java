package com.fdbst.bowlingscoretracker.service.impl;

import com.fdbst.bowlingscoretracker.model.Player;
import com.fdbst.bowlingscoretracker.model.Round;
import com.fdbst.bowlingscoretracker.service.ScorePrinter;
import com.fdbst.bowlingscoretracker.utils.PlayUtils;

import java.util.List;

public class BowlingScorePrinter implements ScorePrinter {

    @Override
    public String printScore(List<Player> players) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t\t\n");
        players.forEach(player -> {
            stringBuilder.append(player.getName()).append("\n");
            stringBuilder.append("Pinfalls").append("\t");
            player.getRounds().subList(0, player.getRounds().size() - 1)
                    .forEach(round -> createRegularPlayerRound(stringBuilder, round));
            createFinalPlayerRound(stringBuilder, player.getRounds().get(player.getRounds().size() - 1));
            stringBuilder.append("Score").append("\t\t");
            player.getRounds().subList(0, player.getRounds().size() - 1)
                    .forEach(round -> stringBuilder.append(round.getAccumulatedScore()).append("\t\t"));
            stringBuilder.append(player.getRounds().get(player.getRounds().size() - 1).getAccumulatedScore());
            stringBuilder.append("\n");
        });
        stringBuilder.setLength(stringBuilder.length() - 1);
        return stringBuilder.toString().trim();
    }

    private void createRegularPlayerRound(StringBuilder stringBuilder, Round round) {
        int normalizedFirstTry = PlayUtils.normalizePlay(round.getFirstTry());
        int normalizedSecondTry = PlayUtils.normalizePlay(round.getSecondTry());
        if (normalizedFirstTry == 10) {
            stringBuilder.append("\t").append("X").append("\t");
        } else if (normalizedFirstTry + normalizedSecondTry == 10) {
            stringBuilder.append(PlayUtils.evaluatePlay(round.getFirstTry())).append("\t")
                    .append("/").append("\t");
        } else {
            stringBuilder.append(PlayUtils.evaluatePlay(round.getFirstTry())).append("\t")
                    .append(PlayUtils.evaluatePlay(round.getSecondTry())).append("\t");
        }
    }

    private void createFinalPlayerRound(StringBuilder stringBuilder, Round round) {
        int normalizedFirstTry = PlayUtils.normalizePlay(round.getFirstTry());
        int normalizedSecondTry = PlayUtils.normalizePlay(round.getSecondTry());
        int normalizedThirdTry = PlayUtils.normalizePlay(round.getThirdTry());
        if (normalizedFirstTry != 10 && (normalizedFirstTry + normalizedSecondTry) == 10) {
            stringBuilder.append(PlayUtils.evaluatePlay(round.getFirstTry())).append("\t")
                    .append("/").append("\t")
                    .append(round.getThirdTry() == 10 ? "X" : PlayUtils.evaluatePlay(round.getThirdTry()));
        } else if (normalizedSecondTry != 10 && (normalizedSecondTry + normalizedThirdTry) == 10) {
            stringBuilder.append(normalizedFirstTry == 10 ? "X" : PlayUtils.evaluatePlay(round.getFirstTry())).append("\t")
                    .append(PlayUtils.evaluatePlay(round.getSecondTry())).append("\t")
                    .append("/");
        } else {
            stringBuilder.append(round.getFirstTry() == 10 ? "X" : PlayUtils.evaluatePlay(round.getFirstTry())).append("\t")
                    .append(round.getSecondTry() == 10 ? "X" : PlayUtils.evaluatePlay(round.getSecondTry())).append("\t")
                    .append(round.getThirdTry() == 10 ? "X" : PlayUtils.evaluatePlay(round.getThirdTry()));
        }
        stringBuilder.append("\n");
    }
}
