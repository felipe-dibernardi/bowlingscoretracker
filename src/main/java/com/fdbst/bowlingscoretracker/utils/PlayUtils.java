package com.fdbst.bowlingscoretracker.utils;

public class PlayUtils {

    public static int normalizePlay(int play) {
        return play >= 0 ? play : 0;
    }

    public static String evaluatePlay(int play) {
        return play >= 0 ? String.valueOf(play) : "F";
    }

}
