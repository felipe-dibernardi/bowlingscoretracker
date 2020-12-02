package com.fdbst.bowlingscoretracker.service.impl;

import com.fdbst.bowlingscoretracker.exception.*;
import com.fdbst.bowlingscoretracker.model.Round;
import com.fdbst.bowlingscoretracker.model.Player;
import com.fdbst.bowlingscoretracker.service.ScoreReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BowlingScoreReader implements ScoreReader {

    private static final int NUMBER_OF_ROUNDS = 10;

    private static final String FAULT_STRING = "F";

    @Override
    public List<Player> readScore(String filePath) throws FileNotFoundException, InvalidValueException, InvalidScoreException,
            NoPlayersException, TooManyRoundsException, NotEnoughRoundsException {
        File file = new File(filePath);
        List<Player> players = new ArrayList<>();
        List<String> playersFinished = new ArrayList<>();

        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\\n");
        int turnCounter = 0;
        Player player = null;
        int firstTry = 0;
        String currentName = "";

        while (scanner.hasNext()) {
            String input = scanner.next();
            String[] inputSplit = input.split("\\s+");
            String name = inputSplit[0];
            String attempt = inputSplit[1];
            validateScoreValue(attempt);
            if (!currentName.equals("") && currentName.equals(name) && turnCounter == 1) {
                if (player.getRounds().size() < NUMBER_OF_ROUNDS) {
                    if (!attempt.equals("F")) {
                        player.getRounds().add(new Round(firstTry, Integer.parseInt(attempt)));
                    } else {
                        player.getRounds().add(new Round(firstTry, 0));
                    }
                    if (player.getRounds().size() < NUMBER_OF_ROUNDS) {
                        int secondTry = Integer.parseInt(attempt);
                        if ((firstTry + secondTry) > NUMBER_OF_ROUNDS) {
                            throw new InvalidScoreException(firstTry + secondTry);
                        }
                        turnCounter = 0;
                    }
                } else {
                    if (playersFinished.contains(name)) {
                        throw new TooManyRoundsException();
                    }
                    if (!attempt.equals(FAULT_STRING)) {
                        player.getRounds().get(NUMBER_OF_ROUNDS - 1).setThirdTry(Integer.parseInt(attempt));
                    } else {
                        player.getRounds().get(NUMBER_OF_ROUNDS - 1).setThirdTry(0);
                    }
                    playersFinished.add(name);
                    turnCounter = 0;
                }
            } else {
                player = players.stream().filter(p -> p.getName().equals(name)).findAny().orElse(null);
                currentName = name;
                if (player == null) {
                    player = new Player(name);
                    players.add(player);
                }
                if (!attempt.equals(FAULT_STRING)) {
                    firstTry = Integer.parseInt(attempt);
                } else {
                    firstTry = 0;
                }
                if (firstTry == 10 && player.getRounds().size() < (NUMBER_OF_ROUNDS - 1)) {
                    player.getRounds().add(new Round(firstTry, 0));
                } else {
                    turnCounter++;
                }

            }

        }

        validatePlayers(players);
        return players;
    }

    private void validateScoreValue(String value) throws InvalidValueException {
        if (!value.equals(FAULT_STRING)) {
            try {
                int number = Integer.parseInt(value);
                if (number < 0 || number > 10) {
                    throw new InvalidValueException("Invalid value: " + value + ". Not in the range 0-10");
                }
            } catch (NumberFormatException e) {
                throw new InvalidValueException("Invalid value: " + value + ". Not an Integer or F");
            }
        }
    }

    private void validatePlayers(List<Player> players)
            throws NoPlayersException, TooManyRoundsException, NotEnoughRoundsException {
        if (players.size() == 0) {
            throw new NoPlayersException();
        }
        for (Player player : players) {
            if (player.getRounds().size() < NUMBER_OF_ROUNDS) {
                throw new NotEnoughRoundsException();
            }
        }
    }
}
