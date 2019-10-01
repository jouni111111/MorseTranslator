package Tests;

import fi.jkk.Morse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

// These tests cover the basic functions of the morse translator.
// The tests do not include file write and file open tests.
// Tests could be next written for testing the hashmaps.
public class MorseTranslateTest {

    @Test
    public void testThatClassReturnsOneMorseCharacter() {
        char text = 'a';
        assertEquals(Morse.translateCharacterToMorse(text), ".-");
    }

    @Test
    public void testClassReturnsWordInMorse() {
        String word = "car";
        assertEquals(Morse.translateWordToMorse(word), "-.-. .- .-.");
    }

    @Test
    public void testClassReturnsMorseCodeToCharacter() {
        String morseCode = "--...";
        assertEquals(Morse.translateMorseCodeToCharacter(morseCode), '7');
    }

    @Test
    public void testClassReturnsMorsecodeToWord() {
        String[] morseCode = {"-.-.", ".-"};
        assertEquals(Morse.translateMorseWordToLanguageWord(morseCode), "ca");
    }

    @Test
    public void testThatClassReturnsNormalWordFromMorseCode() {
        String text = "-.-. .- .-.";
        assertEquals(Morse.translateMorseToWord(text), "car");
    }

    @Test
    public void testThatClassReturnsMorseStringFromListOfWords() {
        ArrayList<String> wordList = new ArrayList<String>();
        wordList.add("car");
        wordList.add("par");
        assertEquals(Morse.wordArrayToMorseString(wordList),"-.-. .- .-."+ ":" + ".--. .- .-.");
    }
}

