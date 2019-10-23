/**
 * ExecutableShell drives the flow of the Task Manager application
 * @author  Jessica Shortz
 * @version 2019.10.19
 */

package todolist;
import java.io.*;
import java.util.ArrayList;

public class ExecutableShell {

    // commands holds the commands that are available to the user
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

    /**
     * Displays a welcome message to the user
     */
    public void displayWelcome() {
        System.out.println("Welcome to the Task Manager.");
        System.out.println("You have " + toDoList.getSizeOfCurrentList() + " active tasks and " +
                toDoList.getSizeOfArchivedList() + "complete tasks.");
        System.out.println("You may type HELP to view the user manual.");
    }

    /**
     * Displays the available commands
     */
    public void displayCommands() {
        System.out.println(commands.toString());
    }

    /**
     * Displays the user manual in the console output
     */
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

    /**
     * Gets a command from the user
     * @return String with command choice
     */
    public static String getCommand() {
        InputReader userInputReader = new InputReader();
        String command = userInputReader.readString().trim();
        return command;
    }

    /**
     * Checks the list of commands against the user input to confirm that it is a valid command
     * @return boolean true if command is valid
     */
    public boolean isValidCommand(String command) {
        command = command.toUpperCase();
        return commands.contains(command);
    }

    /**
     * Takes the user command choice and performs relevant actions for command
     * @param command of user choice
     */
    public void processCommand(String command) throws IOException {
        command = command.toUpperCase().trim();
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
                    CommandReader commandReader = new CommandReader();
                    toDoList.removeTask(toDoList.getTaskByTitle(commandReader.getTaskToRemoveFromUser()));
                    break;
                case "SORT LIST" :
                    toDoList.sort();
                    break;
                // SAVE AND QUIT processed in queryUserForCommand()
            }
        } else {
            System.out.println("That is not a valid command");
        }
    }

    /**
     * Queries the user for a command to carry out
     */
    public void queryUserForCommand() throws IOException {
        System.out.println("Please enter any of the following commands:");
        System.out.println("(Note that any command other than 'SAVE AND QUIT' will lead to a new prompt for a command)");
        shell.displayCommands();
        String command = getCommand().toUpperCase().trim();
        if (!command.equals("SAVE AND QUIT")) {
            shell.processCommand(command);
            queryUserForCommand();
        } else {
            System.out.println("Thank you for using Task Manager. " +
                    "\nThe next time you start, your list (current and complete tasks) will be loaded automatically.");
            toDoList.saveAndQuit();
        }
    }

    /**
     * Loads the to do list that is saved in the taskList.tmp file
     */
    public void loadList() {
        toDoList.loadList();
    }

    /**
     * Main method
     */
    public static void main(String[] args) throws IOException {
        shell.loadList();
        shell.displayWelcome();
        shell.queryUserForCommand();
    }
}

