package todolist;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class InputReader {

    protected Scanner inputReader;

    public InputReader() {
        inputReader = new Scanner(System.in);
    }

    public InputReader(String filename) throws FileNotFoundException {
        try {
            File fileToReadFrom = new File("" + filename + "");
            inputReader = new Scanner(fileToReadFrom);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public String readString() {
        return inputReader.nextLine();
    }

    public int readInt() {
        return inputReader.nextInt();
    }

}
