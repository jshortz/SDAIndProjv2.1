package todolist;

import java.io.*;
import java.util.ArrayList;

public class ExecutableShell {

    protected static ArrayList<String> commands;
    protected TaskList toDoList;
    protected static ExecutableShell shell = new ExecutableShell();

    public ExecutableShell() {
        commands = new ArrayList<>();
        commands.add("HELP");
        commands.add("VIEW LIST");
        commands.add("ADD TASK");
        commands.add("EDIT TASK");
        commands.add("REMOVE TASK");
        commands.add("SORT LIST");
        commands.add("SAVE AND QUIT");
        toDoList = new TaskList();
    }

    public static void displayWelcome() {
        System.out.println("Welcome to the Task Manager. You may type HELP to view the user manual.");
    }

    public void displayCommands() {
        System.out.println(commands.toString());
    }

    public void displayHelpManual() throws IOException {
        InputStream userManual = new BufferedInputStream(new FileInputStream("UserManual.md"));
        byte[] buffer = new byte[8192];

        try {
            for (int i = 0; (i = userManual.read(buffer)) != -1;) {
                System.out.write(buffer, 0, i);
            }
        } finally {
            userManual.close();
        }
    }

    public static String getCommand() {
        InputReader userInputReader = new InputReader();
        String command = userInputReader.readString();
        return command;
    }

    public boolean isValidCommand(String command) {
        return commands.contains(command);
    }

    public void processCommand(String command) throws IOException {
        if (isValidCommand(command)) {
            switch (command) {
                case "HELP" :
                    displayHelpManual();
                    break;
                case "VIEW LIST" :
                    toDoList.displayList();
                    break;
                case "ADD TASK" :
                    toDoList.addTask();
                    break;
                case "EDIT TASK" :
                    if (toDoList.isListEmpty()) {
                        System.out.println("Your list doesn't contain any tasks to edit");
                    } else {
                        toDoList.editTask();
                    }
                    break;
                case "REMOVE TASK" :
                    toDoList.removeTask();
                    break;
                case "SORT LIST" :
                    toDoList.sort();
                case "SAVE AND QUIT" :
                    toDoList.saveAndQuit();
                    break;
            }
        } else {
            System.out.println("That is not a valid command");
        }
    }

    public void queryUserForCommand() throws IOException {
        System.out.println("Please enter any of the following commands:");
        System.out.println("(Note that any command other than 'SAVE AND QUIT' will lead to a new prompt for a command)");
        shell.displayCommands();
        String command = getCommand();
        if (!command.equals("SAVE AND QUIT")) {
            shell.processCommand(command);
            queryUserForCommand();
        } else {
            System.out.println("Thank you for using Task Manager. The next time you start, your list will be loaded automatically.");
        }
    }

    public void loadList() {
        toDoList.loadList();
    }

    public static void main(String[] args) throws IOException {
        shell.loadList();
        displayWelcome();
        shell.queryUserForCommand();
    }
}

