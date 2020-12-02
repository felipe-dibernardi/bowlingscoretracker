package com.fdbst.bowlingscoretracker.model;

import java.util.Objects;

public class Round {

    private int firstTry;

    private int secondTry;

    private int thirdTry;

    private int accumulatedScore;

    public Round(int firstTry, int secondTry) {
        this.firstTry = firstTry;
        this.secondTry = secondTry;
    }

    public Round(int firstTry, int secondTry, int thirdTry) {
        this.firstTry = firstTry;
        this.secondTry = secondTry;
        this.thirdTry = thirdTry;
    }

    public Round(Round source) {
        this.firstTry = source.firstTry;
        this.secondTry = source.secondTry;
        this.thirdTry = source.thirdTry;
        this.accumulatedScore = source.accumulatedScore;
    }

    public boolean isStrike() {
        return firstTry == 10;
    }

    public boolean isSpare() {
        return firstTry != 10 && (firstTry + secondTry) == 10;
    }

    public int getFirstTry() {
        return firstTry;
    }

    public void setFirstTry(int firstTry) {
        this.firstTry = firstTry;
    }

    public int getSecondTry() {
        return secondTry;
    }

    public void setSecondTry(int secondTry) {
        this.secondTry = secondTry;
    }

    public int getThirdTry() {
        return thirdTry;
    }

    public void setThirdTry(int thirdTry) {
        this.thirdTry = thirdTry;
    }

    public int getAccumulatedScore() {
        return accumulatedScore;
    }

    public void setAccumulatedScore(int accumulatedScore) {
        this.accumulatedScore = accumulatedScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Round)) return false;
        Round round = (Round) o;
        return firstTry == round.firstTry &&
                secondTry == round.secondTry &&
                thirdTry == round.thirdTry &&
                accumulatedScore == round.accumulatedScore;
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstTry, secondTry, thirdTry, accumulatedScore);
    }
}
