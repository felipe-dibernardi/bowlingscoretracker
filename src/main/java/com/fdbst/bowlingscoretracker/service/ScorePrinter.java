package com.fdbst.bowlingscoretracker.service;

import com.fdbst.bowlingscoretracker.model.Player;

import java.util.List;

public interface ScorePrinter {

    String printScore(List<Player> players);

}
