package com.fdbst.bowlingscoretracker;

import com.fdbst.bowlingscoretracker.exception.NoFileException;
import com.fdbst.bowlingscoretracker.exception.TooManyFilesException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws NoFileException, TooManyFilesException, FileNotFoundException {
        if (args.length < 1) {
            throw new NoFileException();
        } else if (args.length > 1) {
            throw new TooManyFilesException();
        }
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);

    }
}
