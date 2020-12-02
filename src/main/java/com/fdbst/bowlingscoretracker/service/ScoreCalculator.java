package com.fdbst.bowlingscoretracker.service;

import com.fdbst.bowlingscoretracker.model.Player;

public interface ScoreCalculator {

    /**
     * Calculates the score for a Player.
     * @param player Player who will have the score calculated.
     */
    void calculateScore(final Player player);

}
