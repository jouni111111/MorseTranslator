package fi.jkk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// An interface that returns morsecode from a language
public interface MorseBaseInterface {

    public static char[] charactersInLanguage = {
            //Define the characters that are the basis for Morse code translation in this language
    };

    public static String[] morseCodesInLanguage = {
            //Define the Morsecodes that are the equivalent for the characters in this language
    };

    public static HashMap createLanguageHashMap(char[] languageCharacterSet, String[] morseCodeSet) {
        HashMap<Character, String> morseCodesInThisLanguage = new HashMap<Character, String>();
        return morseCodesInThisLanguage;
    }
    //Creates the hashmap connecting the characters in a language to equivalent morse codes

    public static String translateCharacterToMorse(char characterToTranslate) {
        String morseCharacter = "";
        return morseCharacter;
    }
    //Returns a String of morse code from a character.

    public static String translateWordToMorse(String word) {
        StringBuilder translatedMorseCode = new StringBuilder();
        return translatedMorseCode.toString();
    }
    //Returns String of morse code from a string word.

    public static char translateMorseCodeToCharacter(String morseCode) {
        char translatedCharacter = ' ';
        return translatedCharacter;
    }
    //Returns a character for equivalent morse code string.

    public static String translateMorseToWord(String morseCode) {
        String[] morseCodeToArray = translateMorseCodeToArray(morseCode);
        String morseToWord = translateMorseWordToLanguageWord(morseCodeToArray);
        return morseToWord;
    }
    // Returns a translated string from morse code string.

    public static String[] translateMorseCodeToArray(String morseCode) {
        String[] morseLetters = morseCode.split(" ");
        return morseLetters;
    }
    // Returns an array of morse code from morse code String

    public static String translateMorseWordToLanguageWord(String[] morseArray) {
        String translatedWord = "";
        return translatedWord;
    }
    // Returns a string word from a morse decoded string array

    public static List<String> sliceTextToWordArray (String text) {
        List<String> listOfWords = new ArrayList<String>();
        return listOfWords;
    }
    // Returns a List of the words e.g. that have been read from the file.

    public static String readFromFile(String filename) {
        String result = null;
        return result;
    }
    // Returns a string from the file with "filename" string

    public static void writeToFile(String fileName, String textTobeWritten) {
    }
    //Writes to a file with the "Filename" the String passed in the argument "textTobeWritten"
    //The morse coded words should be added to a file with ":" added between the translated words


}
