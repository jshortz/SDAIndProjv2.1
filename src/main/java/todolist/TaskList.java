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
    protected ArrayList<Task> archivedTaskList;
    protected CommandReader commandReader;

    public TaskList() {
        taskList = new ArrayList<>();
        archivedTaskList = new ArrayList<>();
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
            if (inputReader.readString().toUpperCase().trim().equals("Y")) {
                editTask();
            }
            return;
        }
        InputReader inputReader = new InputReader();
        if (commandReader.editTitle()) {
            System.out.println("Please enter a new title:");
            Task tempTask = new Task(taskToEdit.title, taskToEdit.project, taskToEdit.date, taskToEdit.description);
            tempTask.title = inputReader.readString().trim();
            while (tempTask.isDuplicateTask(tempTask.title, tempTask.date, taskList) && !tempTask.title.toUpperCase().equals(taskToEdit.title.toUpperCase())) {
                System.out.println("Your title change has created a duplicate task. Please enter a new title.");
                tempTask.title = inputReader.readString().trim();
            }
            taskToEdit.title = tempTask.title;
            taskToEdit.id = "" + taskToEdit.title + taskToEdit.date.hashCode() + "";
        }
        if (commandReader.editProject()) {
            System.out.println("Please enter a new Project:");
            taskToEdit.project = inputReader.readString().trim();
        }
        if (commandReader.editDescription()) {
            System.out.println("Please enter a new Description:");
            taskToEdit.description = inputReader.readString().trim();
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
            archivedTaskList.add(taskToEdit);
            removeTask(taskToEdit);
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
        System.out.println("\nYour incomplete tasks are:");
        for (Task task : taskList) {
            System.out.println(task);
        }
        System.out.println("Completed tasks:");
        for (Task task : archivedTaskList) {
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
     * @param taskToRemove Task task that should be removed
     */
    public void removeTask(Task taskToRemove) {
        taskList.removeIf((task -> task == taskToRemove));
    }

    /**
     * Saves the active and archived lists to external files (taskList.tmp) (archiveTaskList.tmp)
     */
    public void saveAndQuit() throws IOException {
        FileOutputStream fos = new FileOutputStream("taskList.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(taskList);
        oos.close();

        FileOutputStream fosArchive = new FileOutputStream("archiveTaskList.tmp");
        ObjectOutputStream oosArchive = new ObjectOutputStream(fosArchive);
        oosArchive.writeObject(archivedTaskList);
        oosArchive.close();
    }

    /**
     * Sorts the to do list by date
     * If user opts to sort by title or project, does a second sort by title
     * If user opts to sort by project, does a third sort by project
     */
    public void sort() {
        taskList.sort(Comparator.comparing(Task::getDate));
        String typeOfSort = commandReader.getTypeOfSort().toUpperCase().trim();
        if (!typeOfSort.equals("DATE")) {
            taskList.sort(Comparator.comparing(Task::getTitle));
        }
        if (typeOfSort.equals("PROJECT")) {
            taskList.sort(Comparator.comparing(Task::getProject));
        }
    }

    /**
     * returns the size of the list of current (not complete) tasks
     * @return int size of taskList
     */
    public int getSizeOfCurrentList() {
        return taskList.size();
    }

    /**
     * returns the size of the list of archived (complete) tasks
     * @return int size of archivedTaskList
     */
    public int getSizeOfArchivedList() {
        return archivedTaskList.size();
    }

    /**
     * If taskList.tmp exists, sets the to do list equal to its contents
     * If archiveTaskList.tmp exists, sets the archived task list equal to it contents
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

        File archivedTaskListFile = new File("archiveTaskList.tmp");
        if (archivedTaskListFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream("archiveTaskList.tmp");
                ObjectInputStream ois = new ObjectInputStream(fis);
                archivedTaskList = (ArrayList<Task>) ois.readObject();
                ois.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException f) {
                System.out.println(f.getMessage());
            }
        }
    }
}


