/**
 * TaskList creates a container for the Tasks in the to do list
 * TaskList also facilitates accessors and mutators of the list itself
 * @author  Jessica Shortz
 * @version 2019.10.19
 */

package todolist;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskList {

    protected ArrayList<Task> taskList;
    protected CommandReader commandReader;

    public TaskList() {
        taskList = new ArrayList<>();
        commandReader = new CommandReader();
    }

    /**
     * Alters each field of a particular Task if user chooses to edit that field
     */
    public void editTask() {
        Task taskToEdit = getTaskByTitle(commandReader.getTaskToEditFromUser());
        if (taskToEdit == null) {
            System.out.println("Your task list is empty or the title you supplied does not exist. Do you wish to enter a new title? Y or N?");
            InputReader inputReader = new InputReader();
            if (inputReader.readString().toUpperCase().equals("Y")) {
                editTask();
            }
            return;
        }
        InputReader inputReader = new InputReader();
        if (commandReader.editTitle()) {
            System.out.println("Please enter a new title:");
            Task tempTask = new Task(taskToEdit.title, taskToEdit.project, taskToEdit.date, taskToEdit.description);
            tempTask.title = inputReader.readString();
            while (tempTask.isDuplicateTask(tempTask.title, tempTask.date, taskList) && !tempTask.title.toUpperCase().equals(taskToEdit.title.toUpperCase())) {
                System.out.println("Your title change has created a duplicate task. Please enter a new title.");
                tempTask.title = inputReader.readString();
            }
            taskToEdit.title = tempTask.title;
            taskToEdit.id = "" + taskToEdit.title + taskToEdit.date.hashCode() + "";
        }
        if (commandReader.editProject()) {
            System.out.println("Please enter a new Project:");
            taskToEdit.project = inputReader.readString();
        }
        if (commandReader.editDescription()) {
            System.out.println("Please enter a new Description:");
            taskToEdit.description = inputReader.readString();
        }
        if (commandReader.editDate()) {
            Task tempTask = new Task(taskToEdit.title, taskToEdit.project, taskToEdit.date, taskToEdit.description);
            tempTask.date = commandReader.getDateFromUser();
            while (tempTask.isDuplicateTask(tempTask.title, tempTask.date, taskList)) {
                System.out.println("Your date change has created a duplicate task. Please enter a new date.");
                tempTask.date = commandReader.getDateFromUser();
            }
            taskToEdit.date = tempTask.date;
            taskToEdit.id = "" + taskToEdit.title + taskToEdit.date.hashCode() + "";
        }
        if (commandReader.markComplete()) {
            taskToEdit.complete = true;
        }
    }

    /**
     * Adds a generic Task to the list
     */
    public void addTask() {
        Task taskToAdd = commandReader.getTaskToAddFromUser();
        while (taskToAdd.isDuplicateTask(taskToAdd.title, taskToAdd.date, taskList)) {
            System.out.println("The task you are trying to add is a duplicate of another task. Please try again.");
            taskToAdd = commandReader.getTaskToAddFromUser();
        }
        taskList.add(taskToAdd);
    }

    /**
     * Displays the to-do list
     */
    public void displayList() {
        for (Task task : taskList) {
            System.out.println(task);
        }
    }

    /**
     * Checks whether the to do list is empty
     * @return boolean true if list is empty
     */
    public boolean isListEmpty() {
        if (taskList.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Accesses a Task in the list by title
     * Also checks whether the title is shared by another task and distinguishes between them
     * @return Task as determined by user inputs
     * @param titleToGet the title of the Task that the user wishes to access
     */
    public Task getTaskByTitle(String titleToGet) {

        ArrayList<Task> tasksByTitle = new ArrayList<>();
        for (Task task : taskList) {
            if (task.title.equals(titleToGet)) {
                tasksByTitle.add(task);
            }
        }

        if (tasksByTitle.size() == 1) {
            return tasksByTitle.get(0);
        } else if (tasksByTitle.size() > 1){
            System.out.println("Your To Do List: ");
            System.out.println(tasksByTitle);
            System.out.println("More than one task has that title. Please specify which you want by ID (enter exactly).");
            InputReader inputReader = new InputReader();
            String idToGet = inputReader.readString();
            Task taskToReturn = null;
            for (Task task : tasksByTitle) {
                if (task.id.equals(idToGet)) {
                    taskToReturn = task;
                    System.out.println(taskToReturn);
                }
            }
            if (taskToReturn != null) {
                return taskToReturn;
            } else {
                System.out.println("That task ID doesn't exist. Please enter a new command.");
                return null;
            }
        } else {
            System.out.println("No task has that title. Please enter a new command.");
            return null;
        }
    }

    /**
     * Removes the selected Task from the list
     */
    public void removeTask() {
        Task taskToRemove = getTaskByTitle(commandReader.getTaskToRemoveFromUser());
        taskList.removeIf((task -> task == taskToRemove));
    }

    /**
     * Saves the list to an external file (taskList.tmp)
     */
    public void saveAndQuit() throws IOException {
        FileOutputStream fos = new FileOutputStream("taskList.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(taskList);
        oos.close();
    }

    /**
     * Sorts the to do list by date
     * If user opts to sort by title, does a second sort by title
     */
    public void sort() {
        taskList.sort(Comparator.comparing(Task::getDate));
        if (commandReader.getIfTitleSort()) {
            taskList.sort(Comparator.comparing(Task::getTitle));
        }
    }

    /**
     * If taskList.tmp exists, sets the to do list equal to its contents
     */
    public void loadList() {
        File taskListFile = new File("taskList.tmp");
        if (taskListFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream("taskList.tmp");
                ObjectInputStream ois = new ObjectInputStream(fis);
                taskList = (ArrayList<Task>) ois.readObject();
                ois.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException f) {
                System.out.println(f.getMessage());
            }
        }
    }
}


