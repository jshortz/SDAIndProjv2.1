/**
 * Class for building a Task object
 * Each Task represents one entry on the user's to-do list
 * Tasks may have the same title or same due date, but cannot have an identical due date and identical title
 * @author  Jessica Shortz
 * @version 2019.10.19
 */

package todolist;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

// Serializable used to output stream to file and read back again
// serialVersionUID used to maintain file sync between versions of the compiled code
public class Task implements Serializable {

    protected String title;
    protected String project;
    protected GregorianCalendar date;
    protected String description;
    protected String id;
    protected boolean complete;
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a Task
     * Title, project, date, and description are based on user input
     * ID is generated from a combination of title and the date's hashCode
     * field complete is set to false by default
     * @param title project date description
     * @return Task
     */
    public Task(String title, String project, GregorianCalendar date, String description) {
        this.title = title;
        this.project = project;
        this.date = date;
        this.description = description;
        id = "" + title + date.hashCode() + "";
        complete = false;
    }

    /**
     * Overwrites the parent toString method
     * @return String with Task information
     */
    public String toString() {
        String alwaysPrint = "\nTitle: " + title + "\nProject: " + project + "\nDue Date: " + date.getTime().toString() + "\nDescription: " + description + "\nID: " + id;
        if (complete == false) {
            return alwaysPrint + "\nComplete: No\n";
        } else {
            return alwaysPrint + "\nComplete: Yes\n";
        }
    }

    /**
     * Returns the title of a Task
     * @return String title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the project of a Task
     * @return String project
     */
    public String getProject() {
        return project;
    }

    /**
     * Returns the date of a Task
     * @return GregorianCalendar date of a Task
     */
    public GregorianCalendar getDate() {
        return date;
    }

    /**
     * Checks whether a task is a duplicate of another task in the list
     * @return boolean true if task is a duplicate
     * @param titleToCheck the title of the task that is being checked for duplication
     * @param dateToCheck the date of the task that is being checked for duplication
     * @param taskList the list that contains the potential duplicates
     */
    public boolean isDuplicateTask(String titleToCheck, GregorianCalendar dateToCheck, ArrayList<Task> taskList) {
        ArrayList<Task> potentialDuplicates = new ArrayList<>();
        titleToCheck = titleToCheck.toUpperCase().trim();
        for (Task task : taskList) {
            if (task.title.toUpperCase().equals(titleToCheck)) {
                potentialDuplicates.add(task);
            }
        }

        if (potentialDuplicates.size() == 0) {
            return false;
        } else {
            int duplicateCounter = 0;
            for (Task task : potentialDuplicates) {
                if (task.date.equals(dateToCheck)) {
                    duplicateCounter++;
                }
            }
            if (duplicateCounter == 0) {
                return false;
            } else {
                return true;
            }
        }
    }
}