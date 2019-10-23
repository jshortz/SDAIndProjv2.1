/**
 * Class for reading input from the user (generally commands, generally multi-step interactions)
 * @author  Jessica Shortz
 * @version 2019.10.19
 */

package todolist;
import java.util.GregorianCalendar;

public class CommandReader {

    public CommandReader() {

    }

    /**
     * Gathers the title, project, description, and due date for a new task from the user
     * @return generic Task
     */
    public Task getTaskToAddFromUser() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Please enter a short title for your task.");
        String title = inputReadFromUser.readString().trim();
        System.out.println("Please enter a project to associate with the task.");
        String project = inputReadFromUser.readString().trim();
        System.out.println("Please enter a description of the task");
        String description = inputReadFromUser.readString().trim();
        GregorianCalendar date = getDateFromUser();
        System.out.println();
        return new Task(title, project, date, description);
    }

    /**
     * Creates a GregorianCalendar date object from user inputs
     * Includes checks against invalid inputs and prompts the user for correction
     * @return GregorianCalendar date object
     */
    public GregorianCalendar getDateFromUser() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Please enter the year the task is due as four digits between 2019 and 9999");
        int year = inputReadFromUser.readInt();
        inputReadFromUser.readString();
        System.out.println("Please enter the month the task is due as two digits between 1 and 12");
        int month = inputReadFromUser.readInt() - 1;
        inputReadFromUser.readString();
        System.out.println("Enter the day the task is due as two digits between 1 and 31");
        int day = inputReadFromUser.readInt();
        inputReadFromUser.readString();
        System.out.println("Please enter the hour the task is due as two digits between 0 and 23");
        int hour = inputReadFromUser.readInt();
        inputReadFromUser.readString();
        System.out.println("Please enter the minute the task is due as two digits between 0 and 59");
        int minute = inputReadFromUser.readInt();
        inputReadFromUser.readString();
        GregorianCalendar date = new GregorianCalendar(year, month, day, hour, minute);
        date.setLenient(false);
        boolean isValidDate = true;
        int maxDays = date.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        int minDays = date.getActualMinimum(GregorianCalendar.DAY_OF_MONTH);
        int maxMonth = 11;
        int minMonth = 0;
        int maxHour = 23;
        int minHour = 0;
        int maxMinutes = 59;
        int minMinutes = 0;
        int maxYear = 9999;
        if (year > maxYear) {
            System.out.println("Year exceeds maximum value.");
            isValidDate = false;
        }
        if ((month > maxMonth) || (month < minMonth)) {
            System.out.println("Invalid month.");
            isValidDate = false;
        }
        if ((day > maxDays) || (day < minDays)) {
            System.out.println("Invalid day.");
            isValidDate = false;
        }
        if ((hour > maxHour) || (hour < minHour)) {
            System.out.println("Invalid hour.");
            isValidDate = false;
        }
        if ((minute > maxMinutes) || (minute < minMinutes)) {
            System.out.println("Invalid minute.");
            isValidDate = false;
        }
        while (date.before(GregorianCalendar.getInstance()) || !isValidDate) {
            System.out.println("Please enter a valid future date (after " + GregorianCalendar.getInstance().getTime().toString() + " ):");
            getDateFromUser();
        }
        return date;
    }

    /**
     * Gets a title from the user for a task to edit
     * @return String title of task to be edited
     */
    public String getTaskToEditFromUser() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Please enter the title of the task you wish to edit:");
        return inputReadFromUser.readString().trim();
    }

    /**
     * Gets a title from the user for a task to remove
     * @return String title of task to be removed
     */
    public String getTaskToRemoveFromUser() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Please enter the title of the task you wish to remove:");
        return inputReadFromUser.readString().trim();
    }

    /**
     * Queries if user wants to edit the title of a Task
     * @return boolean true if title should be edited
     */
    public boolean editTitle() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Do you want to edit the title of the task? Enter Y or N.");
        String answer = inputReadFromUser.readString().toUpperCase().trim();
        if (answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Queries if user wants to edit the project of a Task
     * @return boolean true if project should be edited
     */
    public boolean editProject() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Do you want to edit the Project assigned to the task? Enter Y or N.");
        String answer = inputReadFromUser.readString().toUpperCase().trim();
        if (answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Queries if user wants to edit the description of a Task
     * @return boolean true if description should be edited
     */
    public boolean editDescription() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Do you want to edit the Description of the task? Enter Y or N.");
        String answer = inputReadFromUser.readString().toUpperCase().trim();
        if (answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Queries if user wants to edit the due date of a Task
     * @return boolean true if due date should be edited
     */
    public boolean editDate() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Do you want to edit the Due Date of the task? Enter Y or N.");
        String answer = inputReadFromUser.readString().toUpperCase().trim();
        if (answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Queries if user wants to mark a Task as complete
     * @return boolean true if Task should be marked as complete
     */
    public boolean markComplete() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Do you wish to mark the task as complete? Enter Y or N.\nIf Y, this will permanently move this Task to " +
                "an archived list and you will no longer be able to edit the Task.");
        String answer = inputReadFromUser.readString().toUpperCase().trim();
        if (answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Queries if user wants to sort list by title or by date
     * @return boolean true if sort is by title
     */
    public String getTypeOfSort() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Do you want to sort by TITLE, PROJECT, or DATE?");
        String userSortAnswer = inputReadFromUser.readString().toUpperCase().trim();
        if ((!userSortAnswer.equals("TITLE")) && (!userSortAnswer.equals("DATE")) && (!userSortAnswer.equals("PROJECT"))) {
            System.out.println("Please enter TITLE, PROJECT, or DATE.");
            userSortAnswer = inputReadFromUser.readString().toUpperCase().trim();
        }
        return userSortAnswer;
    }
}
