
/*                  
                                                                                              
     ▄████████    ▄████████          ███        ▄████████    ▄████████     ███        ▄████████    ▄████████ 
    ███    ███   ███    ███      ▀█████████▄   ███    ███   ███    ███ ▀█████████▄   ███    ███   ███    ███ 
    ███    █▀    ███    █▀          ▀███▀▀██   ███    █▀    ███    █▀     ▀███▀▀██   ███    █▀    ███    ███ 
    ███          ███                 ███   ▀  ▄███▄▄▄       ███            ███   ▀  ▄███▄▄▄      ▄███▄▄▄▄██▀ 
    ███        ▀███████████          ███     ▀▀███▀▀▀     ▀███████████     ███     ▀▀███▀▀▀     ▀▀███▀▀▀▀▀   
    ███    █▄           ███          ███       ███    █▄           ███     ███       ███    █▄  ▀███████████ 
    ███    ███    ▄█    ███          ███       ███    ███    ▄█    ███     ███       ███    ███   ███    ███ 
    ████████▀   ▄████████▀          ▄████▀     ██████████  ▄████████▀     ▄████▀     ██████████   ███    ███ 
                                                                                                  ███    ███ 
    Cheers,
    - Ryder Johnson
    - Justin Hwang
 */

import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import java.util.Comparator;
import java.util.Random;
import java.util.Arrays;
import java.io.PrintStream;
import org.junit.Assert;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.Assert.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
@RunWith(Enclosed.class)
public class JUnitTests {
	
	
	
	public static class KMPTests {
	
	    private CountComparator comparator;
	    @Before
	    public void setup() {
	        comparator = new CountComparator();
	    }
	
	    @Test
	    public void nullPattern() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.kmp(null, "Shayan", comparator));
	    }
	
	    @Test
	    public void emptyPattern() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.kmp("", "Shayan", comparator));
	    }
	
	    @Test
	    public void nullText() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.kmp("Mustangs", null, comparator));
	    }
	
	    @Test
	    public void nullComparator() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.kmp("Mustangs", "Homestead Mustangs", null));
	    }
	
	    @Test
	    public void visToolOne() {
	        String text = "BBBBBBBBBBBBBBBBBBBBA";
	        String pattern = "BBBBBA";
	
	        int[] table = new int[] {0, 1, 2, 3, 4, 0};
	
	        AssertUtils.assertArrayEquals(table, PatternMatching.buildFailureTable(pattern, comparator));
	        AssertUtils.checkComparisons(comparator, 9);
	        comparator.resetComparisonCount();
	
	        AssertUtils.assertArrayEquals(new Integer[] {15}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 45);
	    }
	
	    @Test
	    public void visToolTwo() {
	        String text = "ABABABABABABABABABABA";
	        String pattern = "ABABAB";
	
	        int[] table = new int[] {0, 0, 1, 2, 3, 4};
	
	        AssertUtils.assertArrayEquals(table, PatternMatching.buildFailureTable(pattern, comparator));
	        AssertUtils.checkComparisons(comparator, 5);
	        comparator.resetComparisonCount();
	
	        AssertUtils.assertArrayEquals(new Integer[] {0, 2, 4, 6, 8, 10, 12, 14}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 25);
	    }
	
	    @Test
	    public void visToolThree() {
	        String text = "Sphinxofblackquartz";
	        String pattern = "quartz";
	
	        int[] table = new int[] {0, 0, 0, 0, 0, 0};
	
	        AssertUtils.assertArrayEquals(table, PatternMatching.buildFailureTable(pattern, comparator));
	        AssertUtils.checkComparisons(comparator, 5);
	        comparator.resetComparisonCount();
	
	        AssertUtils.assertArrayEquals(new Integer[] {13}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 24);
	    }
	
	    @Test
	    public void visToolFour() {
	        String text = "GGACTGA";
	        String pattern = "ACT";
	
	        int[] table = new int[] {0, 0, 0};
	
	        AssertUtils.assertArrayEquals(table, PatternMatching.buildFailureTable(pattern, comparator));
	        AssertUtils.checkComparisons(comparator, 2);
	        comparator.resetComparisonCount();
	
	        AssertUtils.assertArrayEquals(new Integer[] {2}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 7);
	    }
	
	    @Test
	    public void visToolFive() {
	        String text = "Machine Learning";
	        String pattern = "in";
	
	        int[] table = new int[] {0, 0};
	
	        AssertUtils.assertArrayEquals(table, PatternMatching.buildFailureTable(pattern, comparator));
	        AssertUtils.checkComparisons(comparator, 1);
	        comparator.resetComparisonCount();
	
	        AssertUtils.assertArrayEquals(new Integer[] {4, 13}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 16);
	    }
	
	    @Test
	    public void visToolSix() {
	        String text = "AAAAABAAABA";
	        String pattern = "AAAA";
	
	        int[] table = new int[] {0, 1, 2, 3};
	
	        AssertUtils.assertArrayEquals(table, PatternMatching.buildFailureTable(pattern, comparator));
	        AssertUtils.checkComparisons(comparator, 3);
	        comparator.resetComparisonCount();
	
	        AssertUtils.assertArrayEquals(new Integer[] {0, 1}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 17);
	    }
	
	    @Test
	    public void visToolSeven() {
	        String text = "BBBBAABBBAB";
	        String pattern = "BAB";
	
	        int[] table = new int[] {0, 0, 1};
	
	        AssertUtils.assertArrayEquals(table, PatternMatching.buildFailureTable(pattern, comparator));
	        AssertUtils.checkComparisons(comparator, 2);
	        comparator.resetComparisonCount();
	
	        AssertUtils.assertArrayEquals(new Integer[] {8}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 19);
	    }
	
	    @Test
	    public void oneOccurrence() {
	        String text = "chrysanthemum butterfly flowers";
	        String pattern = "butt";
	
	        int[] table = new int[] {0, 0, 0, 0};
	
	        AssertUtils.assertArrayEquals(table, PatternMatching.buildFailureTable(pattern, comparator));
	        AssertUtils.checkComparisons(comparator, 3);
	        comparator.resetComparisonCount();
	
	        AssertUtils.assertArrayEquals(new Integer[] {14}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 31);
	    }
	
	    @Test
	    public void overlappingOccurrences() {
	        String text = "the coca cola coca cola company";
	        String pattern = "coca cola co";
	
	        int[] table = new int[] {0, 0, 1, 0, 0, 1, 2, 0, 0, 0, 1, 2};
	
	        AssertUtils.assertArrayEquals(table, PatternMatching.buildFailureTable(pattern, comparator));
	        AssertUtils.checkComparisons(comparator, 13);
	        comparator.resetComparisonCount();
	
	        AssertUtils.assertArrayEquals(new Integer[] {4, 14}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 39);
	    }
	
	    @Test
	    public void emptyText() {
	        String text = "";
	        String pattern = "eli";
	
	        // No exception should occur-- the code should just exit without running any comparisons or building
	        // the last occurrence table.
	        AssertUtils.assertArrayEquals(new Integer[] {}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 0);
	    }
	
	    @Test
	    public void shorterTextThanPattern() {
	        String text = "Burrito";
	        String pattern = "Taco Cat";
	
	        // No exception should occur-- the code should just exit without running any comparisons.
	        AssertUtils.assertArrayEquals(new Integer[] {}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 0);
	    }
	
	    @Test
	    public void patternAtVeryEndOfText() {
	        String text = "the cav goes moo";
	        String pattern = "moo";
	
	        int[] table = new int[] {0, 0, 0};
	
	        AssertUtils.assertArrayEquals(table, PatternMatching.buildFailureTable(pattern, comparator));
	        AssertUtils.checkComparisons(comparator, 2);
	        comparator.resetComparisonCount();
	
	        AssertUtils.assertArrayEquals(new Integer[] {13}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 18);
	    }
	
	    @Test
	    public void zeroOccurrences() {
	        String text = "According to all known laws of aviation there is no way a bee should be able to fly.";
	        String pattern = "wasp";
	
	        int[] table = new int[] {0, 0, 0, 0};
	
	        AssertUtils.assertArrayEquals(table, PatternMatching.buildFailureTable(pattern, comparator));
	        AssertUtils.checkComparisons(comparator, 3);
	        comparator.resetComparisonCount();
	
	        AssertUtils.assertArrayEquals(new Integer[] {}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 87);
	    }
	
	    @Test
	    public void onlyOneLetter() {
	        String text = "AAAAAAAAAAAAAAAAAAAA";
	        String pattern = "AAA";
	
	        int[] table = new int[] {0, 1, 2};
	        AssertUtils.assertArrayEquals(table, PatternMatching.buildFailureTable(pattern, comparator));
	        AssertUtils.checkComparisons(comparator, 2);
	        comparator.resetComparisonCount();
	
	        AssertUtils.assertArrayEquals(new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 22);
	
	
	    }
	
	}

	
	
	
	
	public static class GalilRuleTests {
	
	    private CountComparator comparator;
	
	    @Before
	    public void setup() {
	        comparator = new CountComparator();
	    }
	
	    @Test
	    public void nullPattern() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.boyerMooreGalilRule(null, "Shayan", comparator));
	    }
	
	    @Test
	    public void emptyPattern() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.boyerMooreGalilRule("", "Shayan", comparator));
	    }
	
	    @Test
	    public void nullText() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.boyerMooreGalilRule("Mustangs", null, comparator));
	    }
	
	    @Test
	    public void nullComparator() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.boyerMooreGalilRule("Mustangs", "Homestead Mustangs", null));
	    }
	
	    @Test
	    public void visToolOne() {
	        String text = "BBBBBBBBBBBBBBBBBBBBA";
	        String pattern = "BBBBBA";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('B', 4);
	        loc.put('A', 5);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[]{15}, PatternMatching.boyerMooreGalilRule(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 30);
	    }
	
	    @Test
	    public void visToolTwo() {
	        String text = "ABABABABABABABABABABA";
	        String pattern = "ABABAB";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('A', 4);
	        loc.put('B', 5);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[]{0, 2, 4, 6, 8, 10, 12, 14}, PatternMatching.boyerMooreGalilRule(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 25);
	    }
	
	    @Test
	    public void visToolThree() {
	        String text = "Sphinxofblackquartz";
	        String pattern = "quartz";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('q', 0);
	        loc.put('u', 1);
	        loc.put('a', 2);
	        loc.put('r', 3);
	        loc.put('t', 4);
	        loc.put('z', 5);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[]{13}, PatternMatching.boyerMooreGalilRule(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 14);
	    }
	
	    @Test
	    public void visToolFour() {
	        String text = "GGACTGA";
	        String pattern = "ACT";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('A', 0);
	        loc.put('C', 1);
	        loc.put('T', 2);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[]{2}, PatternMatching.boyerMooreGalilRule(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 6);
	    }
	
	    @Test
	    public void visToolFive() {
	        String text = "Machine Learning";
	        String pattern = "in";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('i', 0);
	        loc.put('n', 1);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[]{4, 13}, PatternMatching.boyerMooreGalilRule(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 11);
	    }
	
	    @Test
	    public void visToolSix() {
	        String text = "AAAAABAAABA";
	        String pattern = "AAAA";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('A', 3);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[]{0, 1}, PatternMatching.boyerMooreGalilRule(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 10);
	    }
	
	    @Test
	    public void visToolSeven() {
	        String text = "BBBBAABBBAB";
	        String pattern = "BAB";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('B', 2);
	        loc.put('A', 1);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[]{8}, PatternMatching.boyerMooreGalilRule(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 19);
	    }
	
	    @Test
	    public void oneOccurrence() {
	        String text = "chrysanthemum butterfly flowers";
	        String pattern = "butt";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('b', 0);
	        loc.put('u', 1);
	        loc.put('t', 3);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[]{14}, PatternMatching.boyerMooreGalilRule(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 15);
	    }
	
	    @Test
	    public void overlappingOccurrences() {
	        String text = "the coca cola coca cola company";
	        String pattern = "coca cola co";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('c', 10);
	        loc.put('o', 11);
	        loc.put('a', 8);
	        loc.put(' ', 9);
	        loc.put('l', 7);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[]{4, 14}, PatternMatching.boyerMooreGalilRule(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 36);
	    }
	
	    @Test
	    public void emptyText() {
	        String text = "";
	        String pattern = "eli";
	
	        // No exception should occur-- the code should just exit without running any comparisons or building
	        // the last occurrence table.
	        AssertUtils.assertArrayEquals(new Integer[]{}, PatternMatching.boyerMooreGalilRule(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 0);
	    }
	
	    @Test
	    public void shorterTextThanPattern() {
	        String text = "Burrito";
	        String pattern = "Taco Cat";
	
	        // No exception should occur-- the code should just exit without running any comparisons.
	        AssertUtils.assertArrayEquals(new Integer[]{}, PatternMatching.boyerMooreGalilRule(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 0);
	    }
	
	    @Test
	    public void patternAtVeryEndOfText() {
	        String text = "the cav goes moo";
	        String pattern = "moo";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('m', 0);
	        loc.put('o', 2);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[]{13}, PatternMatching.boyerMooreGalilRule(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 11);
	    }
	
	    @Test
	    public void onlyOneLetter() {
	        String text = "AAAAAAAAAAAAAAAAAAAA";
	        String pattern = "AAA";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('A', 2);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}, PatternMatching.boyerMooreGalilRule(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 22);
	    }
	    
	}
	
	
	public static class BoyerMooreTests {
	
	    private CountComparator comparator;
	    @Before
	    public void setup() {
	        comparator = new CountComparator();
	    }
	
	    @Test
	    public void nullPattern() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.boyerMoore(null, "Shayan", comparator));
	    }
	
	    @Test
	    public void emptyPattern() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.boyerMoore("", "Shayan", comparator));
	    }
	
	    @Test
	    public void nullText() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.boyerMoore("Mustangs", null, comparator));
	    }
	
	    @Test
	    public void nullComparator() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.boyerMoore("Mustangs", "Homestead Mustangs", null));
	    }
	
	    @Test
	    public void visToolOne() {
	        String text = "BBBBBBBBBBBBBBBBBBBBA";
	        String pattern = "BBBBBA";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('B', 4);
	        loc.put('A', 5);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[] {15}, PatternMatching.boyerMoore(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 21);
	    }
	
	    @Test
	    public void visToolTwo() {
	        String text = "ABABABABABABABABABABA";
	        String pattern = "ABABAB";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('A', 4);
	        loc.put('B', 5);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[] {0, 2, 4, 6, 8, 10, 12, 14}, PatternMatching.boyerMoore(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 56);
	    }
	
	    @Test
	    public void visToolThree() {
	        String text = "Sphinxofblackquartz";
	        String pattern = "quartz";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('q', 0);
	        loc.put('u', 1);
	        loc.put('a', 2);
	        loc.put('r', 3);
	        loc.put('t', 4);
	        loc.put('z', 5);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[] {13}, PatternMatching.boyerMoore(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 9);
	    }
	
	    @Test
	    public void visToolFour() {
	        String text = "GGACTGA";
	        String pattern = "ACT";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('A', 0);
	        loc.put('C', 1);
	        loc.put('T', 2);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[] {2}, PatternMatching.boyerMoore(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 5);
	    }
	
	    @Test
	    public void visToolFive() {
	        String text = "Machine Learning";
	        String pattern = "in";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('i', 0);
	        loc.put('n', 1);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[] {4, 13}, PatternMatching.boyerMoore(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 13);
	    }
	
	    @Test
	    public void visToolSix() {
	        String text = "AAAAABAAABA";
	        String pattern = "AAAA";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('A', 3);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[] {0, 1}, PatternMatching.boyerMoore(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 10);
	    }
	
	    @Test
	    public void visToolSeven() {
	        String text = "BBBBAABBBAB";
	        String pattern = "BAB";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('B', 2);
	        loc.put('A', 1);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[] {8}, PatternMatching.boyerMoore(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 17);
	    }
	
	    @Test
	    public void oneOccurrence() {
	        String text = "chrysanthemum butterfly flowers";
	        String pattern = "butt";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('b', 0);
	        loc.put('u', 1);
	        loc.put('t', 3);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[] {14}, PatternMatching.boyerMoore(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 13);
	    }
	
	    @Test
	    public void overlappingOccurrences() {
	        String text = "the coca cola coca cola company";
	        String pattern = "coca cola co";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('c', 10);
	        loc.put('o', 11);
	        loc.put('a', 8);
	        loc.put(' ', 9);
	        loc.put('l', 7);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[] {4, 14}, PatternMatching.boyerMoore(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 34);
	    }
	
	    @Test
	    public void emptyText() {
	        String text = "";
	        String pattern = "eli";
	
	        // No exception should occur-- the code should just exit without running any comparisons or building
	        // the last occurrence table.
	        AssertUtils.assertArrayEquals(new Integer[] {}, PatternMatching.boyerMoore(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 0);
	    }
	
	    @Test
	    public void shorterTextThanPattern() {
	        String text = "Burrito";
	        String pattern = "Taco Cat";
	
	        // No exception should occur-- the code should just exit without running any comparisons.
	        AssertUtils.assertArrayEquals(new Integer[] {}, PatternMatching.boyerMoore(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 0);
	    }
	
	    @Test
	    public void patternAtVeryEndOfText() {
	        String text = "the cav goes moo";
	        String pattern = "moo";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('m', 0);
	        loc.put('o', 2);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[] {13}, PatternMatching.boyerMoore(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 9);
	    }
	
	    @Test
	    public void onlyOneLetter() {
	        String text = "AAAAAAAAAAAAAAAAAAAA";
	        String pattern = "AAA";
	
	        HashMap<Character, Integer> loc = new HashMap<>();
	        loc.put('A', 2);
	
	        assertEquals(loc.entrySet(), PatternMatching.buildLastTable(pattern).entrySet());
	        AssertUtils.assertArrayEquals(new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}, PatternMatching.kmp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 22);
	    }
	}

	
	
	public static class RabinKarpTests {
	
	    private CountComparator comparator;
	    @Before
	    public void setup() {
	        comparator = new CountComparator();
	    }
	
	    @Test
	    public void nullPattern() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.rabinKarp(null, "Shayan", comparator));
	    }
	
	    @Test
	    public void emptyPattern() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.rabinKarp("", "Shayan", comparator));
	    }
	
	    @Test
	    public void nullText() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.rabinKarp("Mustangs", null, comparator));
	    }
	
	    @Test
	    public void nullComparator() {
	        ExceptionUtils.assertExceptionIsCorrect(IllegalArgumentException.class, () ->
	                PatternMatching.rabinKarp("Mustangs", "Homestead Mustangs", null));
	    }
	
	    @Test
	    public void visToolOne() {
	        String text = "BBBBBBBBBBBBBBBBBBBBA";
	        String pattern = "BBBBBA";
	
	        AssertUtils.assertArrayEquals(new Integer[] {15}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 6);
	    }
	
	    @Test
	    public void visToolTwo() {
	        String text = "ABABABABABABABABABABA";
	        String pattern = "ABABAB";
	
	        AssertUtils.assertArrayEquals(new Integer[] {0, 2, 4, 6, 8, 10, 12, 14}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 48);
	    }
	
	    @Test
	    public void visToolThree() {
	        String text = "Sphinxofblackquartz";
	        String pattern = "quartz";
	
	        AssertUtils.assertArrayEquals(new Integer[] {13}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 6);
	    }
	
	    @Test
	    public void visToolFour() {
	        String text = "GGACTGA";
	        String pattern = "ACT";
	
	        AssertUtils.assertArrayEquals(new Integer[] {2}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 3);
	    }
	
	    @Test
	    public void visToolFive() {
	        String text = "Machine Learning";
	        String pattern = "in";
	
	        AssertUtils.assertArrayEquals(new Integer[] {4, 13}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 4);
	    }
	
	    @Test
	    public void visToolSix() {
	        String text = "AAAAABAAABA";
	        String pattern = "AAAA";
	
	        AssertUtils.assertArrayEquals(new Integer[] {0, 1}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 8);
	    }
	
	    @Test
	    public void visToolSeven() {
	        String text = "BBBBAABBBAB";
	        String pattern = "BAB";
	
	        AssertUtils.assertArrayEquals(new Integer[] {8}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 3);
	    }
	
	    @Test
	    public void oneOccurrence() {
	        String text = "chrysanthemum butterfly flowers";
	        String pattern = "butt";
	
	        AssertUtils.assertArrayEquals(new Integer[] {14}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 4);
	    }
	
	    @Test
	    public void overlappingOccurrences() {
	        String text = "the coca cola coca cola company";
	        String pattern = "coca cola co";
	
	        AssertUtils.assertArrayEquals(new Integer[] {4, 14}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 24);
	    }
	
	    @Test
	    public void emptyText() {
	        String text = "";
	        String pattern = "eli";
	
	        AssertUtils.assertArrayEquals(new Integer[] {}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 0);
	    }
	
	    @Test
	    public void shorterTextThanPattern() {
	        String text = "Burrito";
	        String pattern = "Taco Cat";
	
	        AssertUtils.assertArrayEquals(new Integer[] {}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 0);
	    }
	
	    @Test
	    public void patternAtVeryEndOfText() {
	        String text = "the cav goes moo";
	        String pattern = "moo";
	
	        AssertUtils.assertArrayEquals(new Integer[] {13}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 3);
	    }
	
	    @Test
	    public void onlyOneLetter() {
	        String text = "AAAAAAAAAAAAAAAAAAAA";
	        String pattern = "AAA";
	
	        AssertUtils.assertArrayEquals(new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 54);
	    }
	
	    @Test
	    public void zeroOccurrences() {
	        String text = "According to all known laws of aviation there is no way a bee should be able to fly.";
	        String pattern = "wasp";
	
	        AssertUtils.assertArrayEquals(new Integer[] {}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 0);
	    }
	
	    @Test
	    public void matchingHashButDifferentText() {
	        // The pattern we're looking for has the values "5, 113^1, 113^2", in that order.
	        // The hash of this pattern will be 5 * 113^2 + 113^1 * 113^1 + 113^2 * 113^0 = 7 * 113^2
	        // In hexademical, the original pattern's values are 0001, 0071, 31E1.
	
	        // To force a matching pattern, we will include substrings where the values will sum up to 3 * 113^2.
	        // We can accomplish this by using the character with value 0 (null character). The following substrings
	        // will match the has, but will not equal the original text.
	        // - "7, 0, 0" will have the hash 7 * 113^2.     Converted to HEX, this looks like 0007, 0000, 0000
	        // - "3, 226, 0" will have the hash 7 * 113^2.   Converted to HEX, this looks like 0003, 00E2, 0000
	        // - "6, 0, 113^2" will have the hash 7 * 113^2. Converted to HEX, this looks like 0006, 0000, 31E1
	
	
	
	        //                            [  MATCHING HASH HERE  ]            [  MATCHING HASH HERE  ]      [  MATCHING HASH HERE  ]              [ REAL PATTERN HERE ]
	        //                                       V                                    V                             V                                   V
	        String text = "\u0670\u0254\u2367\u0007\u0000\u0000\u1678\u0840\u5419\u0003\u00E2\u0000\u9125\u1868\u0006\u0000\u31E1\u5940\u1323\u1351\u0001\u0071\u31E1\u4414\u0604";
	        String pattern = "\u0001\u0071\u31E1";
	
	        // String text = "ٰɔ⍧\u0007\u0000\u0000ᙸࡀ吙\u0003â\u0000鄥ᡨ\u0006\u0000㇡奀ጣፑ\u0001q㇡䐔\u0604";
	        // String pattern = "\u0001q㇡";
	
	        AssertUtils.assertArrayEquals(new Integer[] {20}, PatternMatching.rabinKarp(pattern, text, comparator).toArray());
	        AssertUtils.checkComparisons(comparator, 3);
	    }
	
	}















}



class CountComparator extends CharacterComparator {

    private int comparisonCount;
    private ArrayList<String> log;

    public CountComparator() {
        comparisonCount = 0;
        log = new ArrayList<>();
    }

    @Override
    public int compare(Character a, Character b) {

        if (a == null || b == null) {
            throw new IllegalArgumentException(
                    String.format("Neither a nor b may be null! Received a = %s, b = %s", a, b));
        }

        ++comparisonCount;
        log.add(String.format("%3d: Compared %s to %s", comparisonCount, a, b));

        return a - b;

    }

    /**
     * Resets the number of comparisons back to 0.
     */
    public void resetComparisonCount() {
        comparisonCount = 0;
        log = new ArrayList<>();
    }

    /**
     * Retrieves the number of comparisons that occurred
     * @return
     */
    public int getComparisonCount() {
        return comparisonCount;
    }

    /**
     * Returns the entire comparison log of this CountComparator as one long String.
     * @return The log, with each comparison on its own line.
     */
    public String getLog() {
        StringBuilder output = new StringBuilder("Here is the log of all comparisons performed:");
        for (String comparison : log) {
            output.append("\n");
            output.append(comparison);
        }
        return output.toString();
    }

}

/**
 * Wrapper class for Integers that contains extra metadata for testing purposes.
 */
class IntegerWrapper implements Comparable<IntegerWrapper> {

    /**
     * Integer data for this IntegerWrapper to store.
     */
    private int data;

    /**
     * Extra data (typically a single char) to test for sorting algorithm stability
     */
    private String metadata;

    /**
     * Most specific constructor.
     * @param data Data to be stored in this IntegerWrapper.
     * @param metadata Metadata to be stored in this IntegerWrapper.
     */
    public IntegerWrapper(int data, String metadata) {
        this.data = data;
        this.metadata = metadata == null ? "" : metadata;
    }

    /**
     * Non-metadata constructor.
     * @param data Data to be stored in this IntegerWrapper.
     */
    public IntegerWrapper(int data) {
        this(data, "");
    }

    /**
     * Default constructor. Defaults data to 0 and metadata to null.
     */
    public IntegerWrapper() {
        this(0, "");
    }

    /**
     * Retrieves the value from the data field.
     * @return The value in the data field.
     */
    public int getData() {
        return data;
    }

    /**
     * Retrieves the value from the metadata field.
     * @return The value in the metadata field.
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * Checks if this IntegerWrapper's data AND metadata are equal.
     * @param other The other Object to compare with.
     * @return True if both data and metadata are equal, false otherwise.
     */
    public boolean equals(Object other) {

        if (other == null || !other.getClass().equals(this.getClass())) {
            return false;
        }

        IntegerWrapper o = (IntegerWrapper) other;

        return this.data == o.data && this.metadata.equals(o.metadata);

    }

    /**
     * Returns a String containing the integer data stored in this node, as well
     * as the metadata string directly concatenated to the end of it.
     * @return
     */
    public String toString() {
        return String.format("%d%s", this.data, this.metadata);
    }

    public static IntegerWrapper[] makeArray(int... args) {
        IntegerWrapper[] arr = new IntegerWrapper[args.length];
        for (int i = 0; i < args.length; ++i) {
            arr[i] = new IntegerWrapper(args[i]);
        }
        return arr;
    }

    public int compareTo(IntegerWrapper other) {
        return this.data - other.data;
    }

}


class FakeRandom extends Random {

    private static final int[] PSEUDORANDOM_ORDER = new int[] {6, 7, 0, 2, 5, 4, 3, 1, 9, 8};

    private int i = 0;

    @Override
    public int nextInt(int range) {

        do {
            i = (i + 1) % PSEUDORANDOM_ORDER.length;
        } while (PSEUDORANDOM_ORDER[i] >= range);

        return PSEUDORANDOM_ORDER[i];
    }

}

interface Drawable {
    String draw();
}



class ArrayDrawingBuilder {
    private static final int ARROW_SIZE = 1;

    private static final int LABEL_WHITESPACE = 10;

    private final Object[] objects;

    private String finalOutput = "";

    private String label;

    private int[] highlightedIndices;

    private int padding;

    public ArrayDrawingBuilder(Object[] objects, int padding) {
        this.objects = Arrays.stream(objects).map(o -> o == null ? "null" : o).toArray();
        this.padding = padding;
    }

    public ArrayDrawingBuilder withArrayVisualization() {
        finalOutput += getArrayString(objects);
        return this;
    }

    public ArrayDrawingBuilder withPointerAt(int index, String message) {
        // Replace with linkedlist system in next iteration (this was purely for sake of time)
        int pointerIndex = index >= objects.length ? finalOutput.split("\n")[1].length() + 5 : getMiddleIndex(index) + (label != null ? 10 : 0);

        for (int i = 0; i < ARROW_SIZE + 1; i++) {
            String symbol = i == 0 ? "^" : "|";

            finalOutput += " ".repeat(pointerIndex);
            finalOutput += symbol;

            if (i == ARROW_SIZE) {
                finalOutput += " " + message;

            }

            finalOutput += "\n";

        }


        return this;
    }

    public ArrayDrawingBuilder withHighlightedIndices(int... indices) {
        this.highlightedIndices = indices;

        return this;
    }

    public ArrayDrawingBuilder withLabel(String label) {
        this.label = label;

        String[] lines = finalOutput.split("\n");;
        int lineCount = lines.length;

        int insertIndex = 2;

        StringBuilder newOutput = new StringBuilder();

        String space = " ".repeat(LABEL_WHITESPACE);

        for (int i = 0; i < lineCount; i++) {
            if (i == insertIndex) {
                newOutput.append(label).append(" ".repeat(LABEL_WHITESPACE - label.length()));
            } else {
                newOutput.append(space);
            }

            newOutput.append(lines[i]).append("\n");
        }

        finalOutput = newOutput.toString();

        return this;
    }

    public String build() {
        return finalOutput.stripTrailing();
    }

    private int getMiddleIndex(int elementIndex) {
        return elementIndex * padding + (padding / 2);
    }

    public String getArrayString(Object[] arr) {
        StringBuilder start = new StringBuilder("\n");

        int topLength = arr.length;

        for (Object o : arr) {
            topLength += padding;
        }

        topLength -= 1;

//        start.append(createLine(topLength));


        StringBuilder middle = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            middle.append("|");

            boolean highlighted = false;

            if (highlightedIndices != null) {
                for (int highlightedIndex: highlightedIndices) {
                    if (i == highlightedIndex) {
                        highlighted = true;
                        break;
                    }
                }
            }

            int addedPadding = (padding - arr[i].toString().length()) / 2;

            middle.append(" ".repeat(addedPadding));


            if (highlighted) {
                middle.append(ColorUtils.formatColorString(AsciiColorCode.RED_BACKGROUND, AsciiColorCode.BLACK_FOREGROUND, arr[i].toString()));
            } else {
                middle.append(arr[i].toString());
            }

            middle.append(" ".repeat(addedPadding));

        }

        middle.append("|");

        start.append(createLine(getRawStringLength(middle)));
        start.append(middle).append("\n");
        start.append(createLine(getRawStringLength(middle)));

        if (highlightedIndices != null) {

            if (highlightedIndices.length != 0) {
                // start backwards to avoid duplicate
                for (int index = highlightedIndices.length - 1; index >= 0; index--) {
                    if (highlightedIndices[index] == objects.length) {
                        start.append(ColorUtils.formatColorString(AsciiColorCode.RED_BACKGROUND, AsciiColorCode.BLACK_FOREGROUND, " MISSING "));
                        break;
                    }
                }
            }
        }

        start.append("\n");

//        start.append(createLine(topLength));


        return start.toString();
    }

    private static String createLine(int topLength) {

        return "+" + "-".repeat(Math.max(0, topLength - 2)) +
                "+" +
                "\n";
    }

    private static int getRawStringLength(StringBuilder str) {
        return str.toString().replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -/]*[@-~]", "").length();
    }

    public static void printInitialArray(Object[] arr) {
        System.out.println("Initial (unsorted) array: " + Arrays.toString(arr));
    }

    public static void printFinalArray(Object[] arr) {
//        System.out.println("Final (sorted) array: " + Arrays.toString(arr));
    }
}

class ArrayElement  implements Drawable {
    private Object element;

    public ArrayElement(Object element) {
        this.element = element;
    }

    @Override
    public String draw() {



        return null;
    }
}


class TreeDrawingTool {
    
    private static char[][] canvas;
    private static int[][] coords;

    /**
     * Generates tree, ignoring the leading null
     */
    public static String[] generateTree(String[] args, boolean ignoreLeadingItem) {
        if (!ignoreLeadingItem)
            return generateTree(args);

        String[] withoutLeadingNull = new String[args.length];
        for (int i = 0; i < args.length - 1; ++i) {
            withoutLeadingNull[i] = args[i + 1];
        }
        return generateTree(withoutLeadingNull);
    }

    /**
     * Each arg should be length 3
     */
    public static String[] generateTree(String[] args) {

        if (args.length == 0) {
            return new String[] {
                    "(No tree drawn, since there were no items in the tree!"
            };
        }

        int lastNonNullIndex = args.length - 1;
        while (args[lastNonNullIndex] == null && lastNonNullIndex > 1) {
            --lastNonNullIndex;
        }

        String[] noTrailingNulls = new String[lastNonNullIndex + 1];
        for (int i = 0; i < noTrailingNulls.length; ++i) {
            noTrailingNulls[i] = args[i];
        }

        args = noTrailingNulls;


        // Format the args
        for (int i = 0; i < args.length; i++) {
            if (!stringIsValid(args[i])) {
                args[i] = "no";
            } else if (args[i].length() > 3) {
                args[i] = args[i].substring(0, 3);
            } else if (args[i].length() == 0) {
                args[i] = "---";
            } else if (args[i].length() == 1) {
                args[i] = args[i];
            } else if (args[i].length() == 2) {
                args[i] = args[i] + ' ';
            }
        }

        // calculate size of canvas
        int height = (int) (Math.log(args.length) / Math.log(2)) + 1; // number of rows of data
        int canvasHeight = (height * 1) + (height) * 3; // Each box is 3 tall. Each gap between rows is 3 tall.
        int canvasWidth = (1 << (height-1)) * 8 - 1;

        //initialize canvas
        canvas = new char[canvasHeight][];
        for (int r = 0; r < canvasHeight; ++r) {
            char[] row = new char[canvasWidth];
            for (int c = 0; c < canvasWidth; ++c) {
                row[c] = ' ';
            }
            canvas[r] = row;
        }

        // Initialize list of coords
        coords = new int[args.length][];

        // Draws all items in canvas
        int currentRow = 1;
        for (int i = 0; i < args.length; i++) {
            if (((i+1) & i) == 0 && i > 0) { //checks if number is a power of 2
                currentRow += 4;
            }
            int itemsOnThisRow = 1 << ((currentRow - 1) / 4);
            int widthPerItem = (canvasWidth / itemsOnThisRow) + 1;
            int col = (int) (((i + 1 - itemsOnThisRow) + 0.5) * widthPerItem) - 1;
//            System.out.println("Drawing item " + args[i] + " at " + currentRow + ", " + col);
//            System.out.printf("There are %d items on this row. widthPerItem is %d\n", itemsOnThisRow, widthPerItem);
            drawItemAt(currentRow, col, args[i]);
            coords[i] = new int[] {currentRow, col, stringIsValid(args[i]) ? 1 : 0};
        }

        // Draws all arrows
        for (int i = 0; i <= args.length/2 - 1; ++i) {
            int childOneIndex = (i << 1) + 1;
            int childTwoIndex = (i + 1) << 1;

            if (childOneIndex < coords.length)
                drawArrow(coords[i], coords[childOneIndex]);
            if (childTwoIndex < coords.length)
                drawArrow(coords[i], coords[childTwoIndex]);
        }

        // Returns the canvas
        return getCanvas();
    }

    private static void drawArrow(int[] start, int[] end) {
        if (start[2] != 0 && end[2] != 0) { // If it starts at invalid location, don't draw arrow
            setCharAt(start[0] + 1, start[1] + (start[1] > end[1] ? -1 : 1), start[1] > end[1] ? '/' : '\\');
            setCharAt(end[0] - 1, end[1] + (start[1] > end[1] ? 1 : -1), start[1] > end[1] ? '/' : '\\');

            if (Math.abs(start[1] - end[1]) == 4) {
                setCharAt(start[0] + 2, Math.min(start[1], end[1]) + 2, start[1] < end[1] ? '\\' : '/');
            }
            for (int c = Math.min(start[1], end[1]) + 3; c < Math.max(end[1], start[1]) - 2; ++c) {
                setCharAt(start[0] + 2, c, '-');
            }
        }
    }
    
    private static String[] getCanvas() {
        String[] rowStrings = new String[canvas.length];

        int skipColumns = 0;

        outerLoop:
        while(skipColumns < canvas[0].length) {
            for (int r = 0; r < canvas.length; ++r) {
                if (canvas[r][skipColumns] != ' ') {
                    break outerLoop;
                }
            }
            ++skipColumns;
        }

        for (int r = 0; r < rowStrings.length; ++r) {
            StringBuilder row = new StringBuilder();
            for (int c = skipColumns; c < canvas[r].length; ++c) {
                row.append(canvas[r][c]);
            }

            rowStrings[r] = row.toString();
        }

        return rowStrings;
    }

    public static void printCanvas(String[] rows) {
        for (String row : rows) {
            System.out.println(row);
        }
    }
    

    /**
     * Row and col denote the CENTER of each box.
     * Item must be exactly 3 characters wide!
     */
    private static void drawItemAt(int row, int col, String item) {

        if (!stringIsValid(item)) return;
        
//        drawLine(row - 1, col - 1, row - 1, col + 1);
//        drawLine(row - 1, col - 1, row + 1, col - 1);
//        drawLine(row + 1, col - 1, row + 1, col + 1);
//        drawLine(row - 1, col + 1, row + 1, col + 1);
        for (int i = 0; i < item.length(); i++) {
            setCharAt(row, col + i, item.charAt(i));
        }
    }

    /**
     * Draws a line at the given two points.
     */
    private static void drawLine(int r1, int c1, int r2, int c2) {
        if (r1 == r2) {
            for (int c = Math.min(c1, c2); c < Math.max(c1, c2); ++c) {
                setCharAt(r1, c, '-');
            }
        } else {
            for (int r = Math.min(r1, r2); r < Math.max(r1, r2); ++r) {
                setCharAt(r, c1, '|');
            }
        }
        setCharAt(r1, c1, '+');
        setCharAt(r2, c2, '+');
    }
    

    /**
     * Sets the canvas's char at that location to be the given char. If
     * the given coordinates are invalid, then this method does nothing.
     */
    private static void setCharAt(int row, int col, char character) {
        if (row >= 0 && row < canvas.length && col >= 0 && col < canvas[0].length) {
            canvas[row][col] = character;
        }
    }

    private static boolean stringIsValid(String str) {
        return !(str == null || str.equalsIgnoreCase("no") || str.equalsIgnoreCase("null"));
    }
    
}

final class TestConfiguration {

    /**
     * Set this boolean to TRUE if you want the TREE visualizations.
     * Set this to FALSE if you want the ARRAY visualizations.
     */
    public static final boolean USE_TREE_DRAWINGS = true;

    /**
     * Set this to the desired timeout (in milliseconds).
     */
    public static final int TIMEOUT = 10000;

    /**
     * Toggles whether or not to show the full comparison log (for easier debugging).
     *
     * --------------------------------------------------
     *
     *                !!!  WARNING !!!
     *       The print statements will significantly
     *        slow your program. If you enable this
     *       setting, we recommend only running the
     *           specific tests that you failed!
     *
     * --------------------------------------------------
     *
     */
    public static final boolean SHOW_COMPARISON_LOG = false;

}

class ColorUtils {
    /**
     * Formats a string to have an ASCII background in terminal.
     *
     * @param background The ASCII representation of the background color, pulled
     *                   from AsciiColorCode
     * @param s          The string to color
     * @return The colored string
     */
    public static String formatBackgroundColorString(String background, String s) {
        return background + s + AsciiColorCode.RESET_COLOR;
    }

    /**
     * Formats a string to have an ASCII foreground (text color) in terminal.
     *
     * @param foreground The ASCII representation of the foreground color, pulled
     *                   from AsciiColorCode
     * @param s          The string to color
     * @return The colored string
     */
    public static String formatForegroundColorString(String foreground, String s) {
        return foreground + s + AsciiColorCode.RESET_COLOR;

    }

    /**
     * Formats a string to have both an ASCII foreground and background in terminal
     *
     * @param background The ASCII representation of the background color, pulled
     *                   from AsciiColorCode
     * @param foreground The ASCII representation of the foreground color, pulled
     *                   from AsciiColorCode
     * @param s          The string to color
     * @return The colored string
     */
    public static String formatColorString(String background, String foreground, String s) {
        return foreground + background + s.replace("\n", AsciiColorCode.RESET_COLOR + "\n" + foreground + background) + AsciiColorCode.RESET_COLOR;
    }
}
final class AsciiColorCode {

    public static final String RESET_COLOR = "\033[0m";

    public static final String BLACK_FOREGROUND = "\033[30m";
    public static final String BLACK_BACKGROUND = "\033[40m";

    public static final String RED_FOREGROUND = "\033[31m";
    public static final String RED_BACKGROUND = "\033[41m";

    public static final String GREEN_FOREGROUND = "\033[32m";
    public static final String GREEN_BACKGROUND = "\033[42m";

    public static final String YELLOW_FOREGROUND = "\033[33m";
    public static final String YELLOW_BACKGROUND = "\033[43m";

    public static final String BLUE_FOREGROUND = "\033[34m";
    public static final String BLUE_BACKGROUND = "\033[44m";

    public static final String MAGENTA_FOREGROUND = "\033[35m";
    public static final String MAGENTA_BACKGROUND = "\033[45m";

    public static final String CYAN_FOREGROUND = "\033[36m";
    public static final String CYAN_BACKGROUND = "\033[46m";

    public static final String WHITE_FOREGROUND = "\033[37m";
    public static final String WHITE_BACKGROUND = "\033[47m";

    public static final String BRIGHT_BLACK_FOREGROUND = "\033[90m";
    public static final String BRIGHT_BLACK_BACKGROUND = "\033[100m";

    public static final String BRIGHT_RED_FOREGROUND = "\033[91m";
    public static final String BRIGHT_RED_BACKGROUND = "\033[101m";

    public static final String BRIGHT_GREEN_FOREGROUND = "\033[92m";
    public static final String BRIGHT_GREEN_BACKGROUND = "\033[102m";

    public static final String BRIGHT_YELLOW_FOREGROUND = "\033[93m";
    public static final String BRIGHT_YELLOW_BACKGROUND = "\033[103m";

    public static final String BRIGHT_BLUE_FOREGROUND = "\033[94m";
    public static final String BRIGHT_BLUE_BACKGROUND = "\033[104m";

    public static final String BRIGHT_MAGENTA_FOREGROUND = "\033[95m";
    public static final String BRIGHT_MAGENTA_BACKGROUND = "\033[105m";

    public static final String BRIGHT_CYAN_FOREGROUND = "\033[96m";
    public static final String BRIGHT_CYAN_BACKGROUND = "\033[106m";

    public static final String BRIGHT_WHITE_FOREGROUND = "\033[97m";
    public static final String BRIGHT_WHITE_BACKGROUND = "\033[107m";
}

/**
 * A utility for redirecting the standard output for use in test cases.
 * <p>
 * Primary, this is being used to ensure "nothing should be printed" when the code is run.
 * The IORedirector will not on its own fail the tests, but is used before and after
 * to record all messages sent.
 */
class IORedirector {
    private static IORedirector instance;

    private PrintStream originalStream;
    private PrintStream redirectedStream;

    private String log = "";

    /**
     * Private singleton construction method, initially grabbing the standard output for later use.
     */
    private IORedirector() {
        this.originalStream = System.out;
    }

    /**
     * Begin redirecting the standard output into the custom PrintStream, used before a test is run.
     */
    public void beginRedirecting() {
        log = "";

        if (redirectedStream == null) {
            redirectedStream = getRedirectorStream();
        }

        System.setOut(redirectedStream);
    }

    /**
     * Standard Singleton-pattern instance system to avoid IORedirectors fighting over the same stream.
     * @return The IORedirector instance
     */
    public static IORedirector getInstance() {
        if (instance == null) {
            instance = new IORedirector();
        }

        return instance;
    }

    /**
     * All messages received from the standard output system are directed to this message via
     * the redirector stream. This is responsible for attaching those messages to the log.
     * @param s The input to handle
     */
    private void handleMessage(String s) {
        log += s.replaceAll("\r", "");
    }

    public boolean hasReceivedMessage() {
        return !log.isEmpty();
    }

    private PrintStream getRedirectorStream() {
        return new PrintStream(System.out, true) {
            @Override
            public void print(String s) {
                IORedirector.getInstance().handleMessage(s);
            }

            @Override
            public PrintStream printf(String message, Object... args) {
                IORedirector.getInstance().handleMessage(String.format(message, args));
                return this;
            }

            @Override
            public void println(String s) {
                IORedirector.getInstance().handleMessage(s + "\n");
            }
        };
    }
}




/**
 * A set of utilities to remove redundant assertion boilerplate.
 */
class AssertUtils {

    public static void assertValidException(Exception e) {
        assertTrue(ExceptionUtils.isDescriptiveException(e));
    }

    public static void assertArrayEquals(int[] expected, int[] actual) {
        Integer[] e = new Integer[expected.length];
        Integer[] a = new Integer[actual.length];

        for (int i = 0; i < e.length; ++i) {
            e[i] = expected[i];
        }

        for (int i = 0; i < a.length; ++i) {
            a[i] = actual[i];
        }

        assertArrayEquals(e, a);
    }

    public static void assertArrayEquals(Object[] expected, Object[] actual) {
        try {
            Assert.assertArrayEquals(expected, actual);
        } catch (AssertionError error) {
            try {
                int max = Math.round((float) (Arrays.stream(expected).map(Object::toString).mapToInt(String::length).max().orElse(5) + 2) / 2) * 2;

                String expectedString = new ArrayDrawingBuilder(expected, max)
                        .withArrayVisualization()
                        .withLabel("Expected")
                        .build();

                ArrayDrawingBuilder actualBuilder = new ArrayDrawingBuilder(actual, max);


                String errorMessage = error.getMessage();

                Matcher matcher = Pattern.compile("\\[([0-9]+)\\]").matcher(errorMessage);

                if (matcher.find()) {
                    int index = Integer.parseInt(matcher.group(1));

                    actualBuilder = actualBuilder.withHighlightedIndices(index)
                            .withArrayVisualization()
                            .withPointerAt(index, "Element differed")
                            .withLabel("Actual");


                } else {
                    actualBuilder = actualBuilder.withArrayVisualization().withLabel("Actual");
                }

                String actualString = actualBuilder.build();

                String message = expectedString + actualString + "\n" + error.getMessage();

                fail(message);
            } catch (Exception e) {
                // Catch issues like the inputted array being null when it shouldn't be,
                // defaulting it to the normal JUnit exception message
                fail(error.getMessage());
            }
        }
    }

    public static void checkComparisons(CountComparator comparator, int comparisons) {
        if (comparator.getComparisonCount() < comparisons) {
            System.out.println(ColorUtils.formatColorString(AsciiColorCode.BRIGHT_BLUE_BACKGROUND,
                    AsciiColorCode.BRIGHT_WHITE_FOREGROUND, "Well done! Your code ran in " + comparator.getComparisonCount()
                            + " comparisons, while our reference code ran in " + comparisons + ".\n"
                            + "If many sorts are consistently more efficient than ours, please reply to our\n" +
                            "post on Piazza so we can improve the test cases!"));
        } else if (comparator.getComparisonCount() == comparisons) {
            //System.out.println("Efficiency passed. Expected " + comparisons + " comparisons, and found " + comparator.getComparisonCount() + " comparisons.");
        } else {
            if (!TestConfiguration.SHOW_COMPARISON_LOG) {
                System.out.println(ColorUtils.formatColorString(AsciiColorCode.BRIGHT_RED_BACKGROUND,
                        AsciiColorCode.BRIGHT_WHITE_FOREGROUND, "Your code ran " + comparator.getComparisonCount() + " comparisons when it should" +
                                " have only run " + comparisons + ".\nTo see the full comparison log, set" +
                                " TestConfiguration.SHOW_COMPARISON_LOG to true."));
            } else {
                System.out.println(ColorUtils.formatColorString(AsciiColorCode.BRIGHT_RED_BACKGROUND,
                        AsciiColorCode.BRIGHT_WHITE_FOREGROUND, "Your code ran " + comparator.getComparisonCount() + " comparisons when it should" +
                                " have only run " + comparisons + ". To hide the full comparison log, set" +
                                " TestConfiguration.SHOW_COMPARISON_LOG to false."));

                System.out.println(comparator.getLog());
            }
            fail();
        }
    }

    public static void checkTotalComparisons(long expected, long actual) {
        System.out.println("\n------- BRUTE FORCE EFFICIENCY REPORT --------");
        System.out.printf("Total comparisons: %,d\n", actual);
        System.out.printf("Expected comparisons: %,d\n", expected);
        System.out.println("----------------------------------------------\n");

        if (expected != actual) {
            fail("Failed efficiency check! Expected " + expected + " but found " + actual + "comparisons!");
        }
    }

    public static <T> void assertListsEqual(List<T> expected, List<T> actual) {
        StringBuilder failMessage = new StringBuilder("Failed! ");

        if (expected.size() != actual.size()) {
            failMessage.append("Expected length " + expected.size() + " but received length " + actual.size() + ".");
        }

        for (int i = 0; i < expected.size(); i++) {
            if (expected.get(i) != actual.get(i)) {
                failMessage.append(String.format("\nLists differed at index %d. Expected %s, received %s.", i, expected.get(i).toString(), actual.get(i).toString()));
            }
        }
        if (!failMessage.toString().equals("Failed! ")) {
            fail(failMessage.toString());
        }
    }

    /**
     * Helper method to turn any type array into an array of Strings by
     * calling the toString() method of each item in the array.
     * @param input The array (of any reference type) to convert to a String array.
     * @return The same items in the given array, in the same order, but as their String representations.
     * @param <T> The type of the array being passed in.
     */
    public static <T> String[] convertToStringArray (T[] input) {
        String[] arr = new String[input.length];
        for (int i = 0 ; i < input.length; ++i) {
            arr[i] = input[i] == null ? null : input[i].toString();
        }
        return arr;
    }
}


/**
 * A set of utilities for handling test cases regarding exceptions.
 */
class ExceptionUtils {

    /**
     * Asserts a snippet of code throws the required exception, checking if it's descriptive along the way.
     * @param expected The expected exception to throw
     * @param codeThatMayThrowException A runnable containing the code intended to throw an exception
     * @param requiredSubstring A required substring for the message to be considered descriptive.
     *                          If this is null, then no substring is required.
     */
    public static void assertExceptionIsCorrect(Class<? extends Exception> expected, Runnable codeThatMayThrowException, String requiredSubstring) {
        try {
            codeThatMayThrowException.run();
            // If this line runs, then your code was supposed to throw an Exception but did not.
            fail("Encountered a test that did not throw the expected expected of " + expected.getName());

        } catch (Exception e) {
            assertSame("checking if the thrown exception is what we expected", expected, e.getClass()); // If this line fails, the code threw the wrong exception (or threw no exception at all)
            assertTrue(e.getClass().getName() + " exception thrown needs to be descriptive " + (requiredSubstring != null ? "\" and must contain the message \"" + requiredSubstring : "\""), isDescriptiveException(e, requiredSubstring)); // If this line fails, then the message was insufficiently descriptive
        }
    }

    public static void assertExceptionIsCorrect(Class<? extends Exception> expected, Runnable codeThatMayThrowException) {
        assertExceptionIsCorrect(expected, codeThatMayThrowException, null);
    }

    /**
     * Determines whether a message is considered "descriptive" by the rubric.
     * This isn't a perfect check, but it at the very least ensures all exceptions
     * contain some sort of error message.
     *
     * @param e                 The exception to check
     * @param requiredSubstring The substring that most be contained in the exception message.
     *                          If this is null, then no substring is required.
     * @return true if the exception is considered descriptive
     */
    public static boolean isDescriptiveException(Exception e, String requiredSubstring) {
        if (requiredSubstring == null) {
            return !(e.getMessage() == null || e.getMessage().isBlank());
        } else {
            return e.getMessage().contains(requiredSubstring);
        }
    }

    /**
     * Convenience feature for isDescriptiveException(Exception e, String requiredSubstring).
     * @param e Exception to check.
     * @return Same as isDescriptiveException(Exception e, String requiredSubstring).
     */
    public static boolean isDescriptiveException(Exception e) {
        return isDescriptiveException(e, null);
    }
}


class CompressionUtils {

    public static String serializeCharList(Collection<?> charList) {
        int currentN = 0;

        StringBuilder builder = new StringBuilder();

        for (Object c : charList) {
            if (c == null) {
                currentN++;
            } else {
                if (currentN != 0) {
                    builder.append(currentN);
                    currentN = 0;
                }

                builder.append(c);
            }
        }

        if (currentN != 0) {
            builder.append(currentN);
        }

        return builder.toString();
    }

    /**
     * Turns a generic array into a Collection of Character that the method
     * serializeCharList(Collection) can accept.
     * @param charArray The array to turn into a Character array.
     * @return The serialized list.
     */
    public static String serializeCharList(Object[] charArray) {
        ArrayList<Character> collection = new ArrayList<>();
        for (Object item : charArray) {
            collection.add(item == null ? null : item.toString().charAt(0));
        }
        return serializeCharList(collection);
    }

    public static String decompressString(String str) throws IOException {
        byte[] byteArr = Base64.getDecoder().decode(str);

        GZIPInputStream bais = new GZIPInputStream(new ByteArrayInputStream(byteArr));

        BufferedReader br = new BufferedReader(new InputStreamReader(bais, StandardCharsets.UTF_8));

        String line;

        StringBuilder output = new StringBuilder();

        while ((line = br.readLine()) != null) {
            output.append(line).append("\n");
        }

        return output.toString();
    }


    public static ArrayList<Character> compressedStringToCharList(String str) {
        StringBuilder numBuilder = new StringBuilder();

        ArrayList<Character> charList = new ArrayList<>();

        for (char c: str.toCharArray()) {
            if (Character.isLetter(c)) {
                if (numBuilder.length() > 0) {
                    int nullElements = Integer.parseInt(numBuilder.toString());

                    for (int i = 0; i < nullElements; i++) {
                        charList.add(null);
                    }

                    numBuilder.setLength(0);
                }

                charList.add(c);
            } else if (Character.isDigit(c)) {
                numBuilder.append(c);
            }
        }

        if (numBuilder.length() != 0) {
            int nullElements = Integer.parseInt(numBuilder.toString());

            for (int j = 0; j < nullElements; j++) {
                charList.add(null);
            }
        }


        return charList;
    }
}