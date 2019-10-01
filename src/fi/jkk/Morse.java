package fi.jkk;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Morse implements MorseBaseInterface {
    public static char[] charactersInLanguage =
            {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                    'i', 'j', 'k', 'l', 'm', 'n', 'o',
                    'p', 'q', 'r', 's', 't', 'u', 'v',
                    'w', 'x', 'y', 'z', '.', ',', '?', '/', '@',
                    '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'}; //41

    public static String[] morseCodesInLanguage = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
            "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--",
            "--..",
            ".-.-.-", "--..--", "..--..", "-..-.", ".--.-.",
            ".----", "..---", "...--", "....-", ".....", "-....", "--...",
            "---..", "----.", "-----"}; //41

    public static HashMap createLanguageHashMap(char[] languageCharacterSet, String[] morseCodeSet) {
        HashMap<Character, String> morseCodesInThisLanguage = new HashMap<Character, String>();
        try {
            //Tähän try catch, jos mäpit eivät vastaa toisiaan, tai tulee null pointer tms.
            for (int i = 0; i < charactersInLanguage.length; i++) {
                morseCodesInThisLanguage.put(languageCharacterSet[i], morseCodeSet[i]);
            }
        } catch (ArrayIndexOutOfBoundsException aioe) {
            aioe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        return morseCodesInThisLanguage;
    }

    public static String translateCharacterToMorse(char characterToTranslate) {
        HashMap<Character, String> morseMap;
        morseMap = createLanguageHashMap(charactersInLanguage, morseCodesInLanguage);
        String morseCharacter = "";
        try {
            if (morseMap.get(characterToTranslate) == null) {
                throw new IllegalStateException("Other method need to be called first, hashmap can't be null.");
            } else {
                //Tarvitaanko tsekkausta, että kyseessä kirjain (tolowercase)
                morseCharacter = morseMap.get(Character.toLowerCase(characterToTranslate));
            }
        } catch (ArrayIndexOutOfBoundsException aioe) {
            aioe.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            System.out.println("The map does not contain this character!");
        } catch (IndexOutOfBoundsException ibe) {
            ibe.printStackTrace();
        }
        return morseCharacter;
    }

    public static String translateWordToMorse(String word) {
        StringBuilder translatedMorseCode = new StringBuilder();
        char[] charactersToTranslate = word.toCharArray();
        try {
            for (int i = 0; i < charactersToTranslate.length; i++) {
                translatedMorseCode.append(translateCharacterToMorse(charactersToTranslate[i]));
                if (i < (charactersToTranslate.length - 1)) {
                    translatedMorseCode.append(" ");
                }
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } catch (IndexOutOfBoundsException ibe) {
            ibe.printStackTrace();
        }
        return translatedMorseCode.toString();
    }

    public static char translateMorseCodeToCharacter(String morseCode) {
        HashMap<Character, String> morseMap;
        morseMap = createLanguageHashMap(charactersInLanguage, morseCodesInLanguage);
        if (morseMap == null)
            throw new IllegalStateException("Other method need to be called first, hashmap can't be null.");
        char translatedCharacter = ' ';
        translatedCharacter = getKey(morseMap, morseCode);
        return translatedCharacter;
    }

    public static String translateMorseToWord(String morseCode) {
        String[] morseCodeToArray = translateMorseCodeToArray(morseCode);
        String morseToWord = translateMorseWordToLanguageWord(morseCodeToArray);
        return morseToWord;
    }

    public static String[] translateMorseCodeToArray(String morseCode) {
        HashMap<Character, String> morseMap;
        morseMap = createLanguageHashMap(charactersInLanguage, morseCodesInLanguage);
        String[] morseLetters = morseCode.split(" ");
        return morseLetters;
    }

    public static String translateMorseWordToLanguageWord(String[] morseArray) {
        int numberOfLetters = morseArray.length;
        char translatedLetter;
        String translatedWord = "";
        try {
            for (int i = 0; i < numberOfLetters; i++) {
                translatedLetter = translateMorseCodeToCharacter(morseArray[i]);
                translatedWord += translatedLetter;
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        return translatedWord;
    }


    public static <Key, Value> Key getKey(Map<Key, Value> map, Value value) {
        try {
            for (Map.Entry<Key, Value> entry : map.entrySet()) {
                if (value.equals(entry.getValue())) {
                    return entry.getKey();
                }
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        return null;
    }

    public static int numberOfSpaces(String morseCode) {
        int countOfSpaces = 0;
        try {
            for (char c : morseCode.toCharArray()) {
                if (c == ' ') {
                    countOfSpaces++;
                }
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        return countOfSpaces;
    }

    public static ArrayList<String> sliceTextToWordArrayRemovingFullstops(String text) {
        text = text.replaceAll("[.]", "");
        String sliced[] = text.split(":");
        ArrayList<String> listOfWords = new ArrayList<String>(Arrays.asList(sliced));
        return listOfWords;
    }

    //The morse coded words are added to a file with ":" added between the translated words
    public static String wordArrayToMorseString(ArrayList<String> wordsList) {
        String stringInMorse = "";
        try {
            for (int i = 0; i < wordsList.size(); i++) {
                stringInMorse += translateWordToMorse(wordsList.get(i));
                if (i < (wordsList.size() - 1)) {
                    stringInMorse += ":";
                }
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        return stringInMorse;
    }


    public static String readFromFile(String filename) {
        String result = null;
        try (FileReader fr = new FileReader(filename);
             BufferedReader in = new BufferedReader(fr)) {
            StringBuilder inputString = new StringBuilder();
            String rowThatIsRead;
            while ((rowThatIsRead = in.readLine()) != null) {
                inputString.append(rowThatIsRead);
            }
            result = inputString.toString();
        } catch (FileNotFoundException ex) {
            System.out.println("Error: File not found");
        } catch (IOException ex) {
            System.out.println("Error: Some other error while reading the file");
        }
        return result;
    }

    public static void writeToFile(String fileName, String textTobeWritten)
            throws IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileName));
            bw.append(textTobeWritten);
            bw.close();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ioe) {
                    System.out.println("Here this is left unnoticed, caught earlier");
                }
            }
        }
    }


}
