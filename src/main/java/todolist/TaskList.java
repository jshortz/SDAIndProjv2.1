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

    public void editTask() {
        Task taskToEdit = getTaskByTitle(commandReader.getTaskToEditFromUser());
        InputReader inputReader = new InputReader();
        if (commandReader.editTitle()) {
            System.out.println("Please enter a new title:");
            taskToEdit.title = inputReader.readString();
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
            taskToEdit.date = commandReader.getDateFromUser();
        }
        if (commandReader.markComplete()) {
            taskToEdit.complete = true;
        }
    }

    public void addTask() {
        Task taskToAdd = commandReader.getTaskToAddFromUser();
        taskList.add(taskToAdd);
    }

    public void displayList() {
        for (Task task : taskList) {
            System.out.println(task);
        }
    }

    public boolean isListEmpty() {
        if (taskList.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

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

    public void removeTask() {
        Task taskToRemove = getTaskByTitle(commandReader.getTaskToRemoveFromUser());
        taskList.removeIf((task -> task == taskToRemove));
    }

    public void saveAndQuit() throws IOException {
        FileOutputStream fos = new FileOutputStream("taskList.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(taskList);
        oos.close();
    }

    public void sort() {
        taskList.sort(Comparator.comparing(Task::getDate));
        if (commandReader.getIfTitleSort()) {
            taskList.sort(Comparator.comparing(Task::getTitle));
        }
    }

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


