import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

public class MainTest {

    @Test
    public void testExemplo1() throws Exception {
        String input = "1\n" +
                "3\n" +
                "Square\n" +
                "2 2 0 0 1\n" +
                "1 1 1 3 3 3 3 1\n" +
                "2 1 0 0 0\n" +
                "Rect\n" +
                "5 5 0 0 1\n" +
                "4 3 4 7 6 7 6 3\n" +
                "0 0 0 0 0\n" +
                "Circle\n" +
                "10 4 0 0 1\n" +
                "10 4 1\n" +
                "-4 0 0 0 0\n";

        String expectedOutput = "Square Rect\n" +
                "Rect Square Circle\n" +
                "Circle Rect\n";

        runMainAndAssert(input, expectedOutput);
    }

    @Test
    public void testExemplo2() throws Exception {
        String input = "3\n" +
                "3\n" +
                "SmallC\n" +
                "0 4 0 0 1\n" +
                "0 4 1\n" +
                "0 -1 1 0 0\n" +
                "BigC\n" +
                "0 0 0 0 1\n" +
                "0 0 2\n" +
                "0 1 0 0 0\n" +
                "Tri\n" +
                "5.5 2 0 0 1\n" +
                "6 1 5 2 5 3\n" +
                "-2 0 0 0 0\n";

        String expectedOutput = "BigC Tri\n" +
                "Tri BigC\n";

        runMainAndAssert(input, expectedOutput);
    }

    @Test
    public void testExemplo3() throws Exception {
        String input = "10\n" +
                "2\n" +
                "bullet\n" +
                "1.5 2.5 4 0 1\n" +
                "1.5 2.5 1\n" +
                "2 0 0 0 0\n" +
                "target\n" +
                "8.5 2.5 4 0 1\n" +
                "8 1 8 4 9 4 9 1\n" +
                "1 0 0 0 0\n";

        String expectedOutput = "";

        runMainAndAssert(input, expectedOutput);
    }

    @Test
    public void testExemplo4() throws Exception {
        String input = "3\n" +
                "2\n" +
                "Grower\n" +
                "2.5 7.5 2 0 1\n" +
                "2 7 2 8 3 8 3 7\n" +
                "2 0 0 0 1\n" +
                "Rotor\n" +
                "4.5 10.5 2 0 1\n" +
                "4 8 4 13 5 13 5 8\n" +
                "0 0 0 -45 0\n";

        String expectedOutput = "Grower Rotor\n" +
                "Rotor Grower\n";

        runMainAndAssert(input, expectedOutput);
    }

    private void runMainAndAssert(String input, String expectedOutput) throws Exception {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            Main.main(new String[0]);

            String actual = outContent.toString().replaceAll("\\r\\n", "\n");
            assertEquals(expectedOutput, actual);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
