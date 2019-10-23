package todolist;
import org.junit.Test;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;

public class InputReaderTest {

    @Test
    public void testInputString() throws FileNotFoundException {
        InputReader inputReader = new InputReader("UserManual.md");
        String test1 = inputReader.readString();
        String test1Expected = "### This is a guide on how to use Task Manager, a basic application for managing a to-do list.";

        assertEquals(test1Expected, test1);
    }
}
