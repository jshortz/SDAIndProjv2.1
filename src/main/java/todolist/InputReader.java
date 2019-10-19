/**
 * Class that creates a Scanner object with defined methods for reading the next String or next Int
 * Not necessary, but makes working with the Scanner more easily understood syntactically
 * Alternate constructor takes a filename as a parameter and reads from the file rather than System.in
 * (Currently not used in program, may be deprecated)
 * @author  Jessica Shortz
 * @version 2019.10.19
 */

package todolist;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class InputReader {

    protected Scanner inputReader;

    // Constructor for user input
    public InputReader() {
        inputReader = new Scanner(System.in);
    }

    // Constructor for reading from a file
    public InputReader(String filename) throws FileNotFoundException {
        try {
            File fileToReadFrom = new File("" + filename + "");
            inputReader = new Scanner(fileToReadFrom);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Uses a Scanner to read Strings
     * @return next String that is input or read from a file
     */
    public String readString() {
        return inputReader.nextLine();
    }

    /**
     * Uses a Scanner to read ints
     * @return next int that is input or read from a file
     */
    public int readInt() {
        return inputReader.nextInt();
    }

}
