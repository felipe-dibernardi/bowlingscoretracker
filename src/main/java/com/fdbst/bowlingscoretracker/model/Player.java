package com.fdbst.bowlingscoretracker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

    private String name;

    private List<Round> rounds;

    public Player(String name) {
        this.name = name;
        this.rounds = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(rounds, player.rounds);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, rounds);
    }
}
