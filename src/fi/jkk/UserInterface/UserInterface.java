package fi.jkk.UserInterface;

import fi.jkk.Morse;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// The logic of the text user interface need a bit more polishing.

public class UserInterface {

    public static void startTheUserInterface(String[] args) {
        try {
           uuserInterface(args);
        } catch (ArrayIndexOutOfBoundsException aioe) {
            System.out.println("To translate morse you need to enter a valid command: ");
            System.out.println("e.g. morse em abc.txt cde.txt");
        }
    }

    public static void uuserInterface (String[] args) {
        helpText();
        boolean continueUserInterface = true;
        while (continueUserInterface) {
            if (args.length == 0) {
                Scanner input = new Scanner(System.in);
                String inputString = input.nextLine();
                String separator = " ";
                args = inputString.split(separator);
            } if (args.length > 0) {
                String commandName = args[1];
                String inputFileName = args[2];
                String outputFileName = args[3];
                if (commandName.equals("me")) {
                    morseToEnglish(inputFileName, outputFileName);
                    continueUserInterface = false;
                }
                if (commandName.equals("em")) {
                    englishToMorse(inputFileName, outputFileName);
                    continueUserInterface = false;
                }
            }
        }
    }

    private static void morseToEnglish(String inputFileName, String outputFileName) {
        String stringReadFromFile = Morse.readFromFile(inputFileName);
        String sliced[] = stringReadFromFile.split(":");
        ArrayList<String> listOfMorseWords = new ArrayList<String>(Arrays.asList(sliced));
        String temporaryMorseWord = "";
        for (int i = 0; i < listOfMorseWords.size(); i++) {
            String morseString = listOfMorseWords.get(i);
            temporaryMorseWord += Morse.translateMorseToWord(morseString);
            if (i < listOfMorseWords.size() - 1) {
                temporaryMorseWord += ":";
            }
        }
        try {
            Morse.writeToFile(outputFileName, temporaryMorseWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void englishToMorse(String inputFileName, String outputFileName) {
        String stringReadFromFile = Morse.readFromFile(inputFileName);
        ArrayList<String> listOfWords = Morse.sliceTextToWordArrayRemovingFullstops(stringReadFromFile);
        String morseString = Morse.wordArrayToMorseString(listOfWords);
        try {
            Morse.writeToFile(outputFileName, morseString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void helpText() {
        System.out.println("***************************************************");
        System.out.println("* This program will translate Morse code!         *");
        System.out.println("* Morse to english (me) or english to morse (em)  *");
        System.out.println("* followed by a space and the filename            *");
        System.out.println("* to read from or write to.                       *");
        System.out.println("* For example to translate english to morse       *");
        System.out.println("* from a file abc.txt to a file cda.txt           *");
        System.out.println("* you should write to command line:               *");
        System.out.println("* morse em abc.txt cde.txt                        *");
        System.out.println("***************************************************");
        System.out.println();
        System.out.println("Please enter your command:");

    }
}
