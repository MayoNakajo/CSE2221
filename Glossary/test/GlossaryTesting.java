import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * JUnit test cases for Glossary.
 *
 */
public class GlossaryTesting {

    /**
     * Test for method generateElements, routine.
     */
    public void testGenerateElementsEmpty() {
        String str = "";
        Set<Character> charSet = new Set1L<>();
        Set<Character> charSetExpected = new Set1L<>();
        Glossary.generateElements(str, charSet);
        assertEquals(charSetExpected, charSet);
    }

    /**
     * Test for method generateElements, routine.
     */
    @Test
    public void testGenerateElementsABC123() {
        String str = "ABC123";
        Set<Character> charSet = new Set1L<>();
        Set<Character> charSetExpected = new Set1L<>();
        charSetExpected.add('A');
        charSetExpected.add('B');
        charSetExpected.add('C');
        charSetExpected.add('1');
        charSetExpected.add('2');
        charSetExpected.add('3');
        Glossary.generateElements(str, charSet);
        assertEquals(charSetExpected, charSet);
    }

    /**
     * Test for method generateElements, challenging.
     */
    @Test
    public void testGenerateElementsRepeats() {
        String str = "aabbcc112233";
        Set<Character> charSet = new Set1L<>();
        Set<Character> charSetExpected = new Set1L<>();
        charSetExpected.add('a');
        charSetExpected.add('b');
        charSetExpected.add('c');
        charSetExpected.add('1');
        charSetExpected.add('2');
        charSetExpected.add('3');
        Glossary.generateElements(str, charSet);
        assertEquals(charSetExpected, charSet);
    }

    /**
     * Test for method outputHeader, routine.
     */
    @Test
    public void testOutputHeader() {
        SimpleWriter out = new SimpleWriter1L("testOutputHeader.txt");
        Glossary.outputHeader(out);
    }

    /**
     * Test for method nextWordOrSeparator, boundary.
     */
    @Test
    public void testNextWordOrSeparatorEmpty() {
        String text = " ";
        int position = 0;
        Set<Character> separators = new Set1L<>();
        String textExpected = " ";

        text = Glossary.nextWordOrSeparator(text, position, separators);

        assertEquals(textExpected, text);
    }

    /**
     * Test for method nextWordOrSeparator, routine.
     */
    @Test
    public void testNextWordOrSeparatorHiBye() {
        String text = "Hi, bye";
        int position = 0;
        Set<Character> separators = new Set1L<>();
        String separatorStr = " \t, ";
        Glossary.generateElements(separatorStr, separators);
        String textExpected = "Hi";

        text = Glossary.nextWordOrSeparator(text, position, separators);

        assertEquals(textExpected, text);
    }

    /**
     * Test for method nextWordOrSeparator, routine.
     */
    @Test
    public void testNextWordOrSeparatorNewPosition() {
        String text = "hi, bye";
        final int position = 4;
        Set<Character> separators = new Set1L<>();
        String separatorStr = " \t, ";
        Glossary.generateElements(separatorStr, separators);
        String textExpected = "bye";

        text = Glossary.nextWordOrSeparator(text, position, separators);

        assertEquals(textExpected, text);
    }

    /**
     * Test for method nextWordOrSeparator, challenging.
     */
    @Test
    public void testNextWordOrSeparatorComma() {
        String text = ", hi";
        int position = 0;
        Set<Character> separators = new Set1L<>();
        String separatorStr = " \t, ";
        Glossary.generateElements(separatorStr, separators);
        String textExpected = ",";

        text = Glossary.nextWordOrSeparator(text, position, separators);

        assertEquals(textExpected, text);
    }

    /**
     * Test for method wordsAndDefinitions, boundary.
     */
    @Test
    public void testWordsAndDefinitionsEmpty() {
        SimpleWriter out = new SimpleWriter1L("testOutEmpty.txt");
        SimpleReader in = new SimpleReader1L("testEmpty.txt");

        Map<String, String> mapExpected = new Map1L<>();

        Map<String, String> map = Glossary.wordsAndDefinitions(out, in);

        assertEquals(mapExpected, map);
    }

    /**
     * Test for method alphabetical, boundary.
     */
    @Test
    public void testAlphabeticalEmpty() {
        Map<String, String> map = new Map1L<>();
        Queue<String> qExpected = new Queue1L<>();
        Queue<String> q = Glossary.alphabetical(map);

        assertEquals(qExpected, q);
    }

    /**
     * Test for method alphabetical, routine.
     */
    @Test
    public void testAlphabeticalABC() {
        Map<String, String> map = new Map1L<>();
        map.add("B", "b");
        map.add("A", "a");
        map.add("C", "c");
        Queue<String> qExpected = new Queue1L<>();
        qExpected.enqueue("A");
        qExpected.enqueue("B");
        qExpected.enqueue("C");
        Queue<String> q = Glossary.alphabetical(map);

        assertEquals(qExpected, q);
    }

    /**
     * Test for method alphabetical, challenging.
     */
    @Test
    public void testAlphabeticalRepeats() {
        Map<String, String> map = new Map1L<>();
        map.add("B", "b");
        map.add("b", "B");
        map.add("A", "a");
        map.add("a", "A");
        Queue<String> qExpected = new Queue1L<>();
        qExpected.enqueue("A");
        qExpected.enqueue("B");
        qExpected.enqueue("a");
        qExpected.enqueue("b");
        Queue<String> q = Glossary.alphabetical(map);

        assertEquals(qExpected, q);
    }

    /**
     * Test for method wordPage, boundary.
     */
    @Test
    public void testWordPageEmpty() {
        String term = "empty";
        Map<String, String> map = new Map1L<>();
        String folder = "data";
        Set<Character> separator = new Set1L<>();
        Glossary.wordPage(term, map, folder, separator);

    }

    /**
     * Test for method wordPage, routine.
     */
    @Test
    public void testWordPageHi() {
        String term = "hi";
        Map<String, String> map = new Map1L<>();
        String folder = "data";
        Set<Character> separator = new Set1L<>();
        map.add("hi", "bye");
        Glossary.wordPage(term, map, folder, separator);

    }

    /**
     * Test for method wordPage, challenging.
     */
    @Test
    public void testWordPageLink() {
        String term = "test";
        Map<String, String> map = new Map1L<>();
        String folder = "data";
        Set<Character> separator = new Set1L<>();
        map.add("test", "testing case link");
        map.add("link", "test case linking");
        Glossary.wordPage(term, map, folder, separator);
    }

    /**
     * Test for method wordPage, boundary.
     */
    @Test
    public void testMainPageEmpty() {
        Queue<String> q = new Queue1L<>();
        SimpleWriter out = new SimpleWriter1L("testMain.html");
        Map<String, String> map = new Map1L<>();
        Glossary.mainPage(q, out, map);
    }

    /**
     * Test for method wordPage, routine.
     */
    @Test
    public void testMainPageHiBye() {
        Queue<String> q = new Queue1L<>();
        q.enqueue("hi");
        q.enqueue("bye");
        SimpleWriter out = new SimpleWriter1L("testMainHiBye.html");
        Map<String, String> map = new Map1L<>();
        map.add("hi", "hello");
        map.add("bye", "goodbye");
        Glossary.mainPage(q, out, map);
    }
}
