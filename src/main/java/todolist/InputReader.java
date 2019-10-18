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

    public void close() {
        inputReader.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputReader reader = new InputReader();
        System.out.println("Enter a String");
        String testString = reader.readString();
        System.out.println("Enter an int");
        int testInt = reader.readInt();
        System.out.println(testString);
        System.out.println(testInt);
        InputReader fileReader = new InputReader("testFile.txt");
        String testString2 = fileReader.readString();
        String testString3 = fileReader.readString();
        System.out.println(testString2 + testString3);
        int testInt2 = fileReader.readInt();
        int testInt3 = fileReader.readInt();
        int testInt4 = fileReader.readInt();
        System.out.println("" + testInt2 + testInt3 + testInt4);

    }
}
