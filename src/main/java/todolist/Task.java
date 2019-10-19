package todolist;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Task implements Serializable {

    protected String title;
    protected String project;
    protected GregorianCalendar date;
    protected String description;
    protected String id;
    protected boolean complete;
    private static final long serialVersionUID = 1L;

    public Task(String title, String project, GregorianCalendar date, String description) {
        this.title = title;
        this.project = project;
        this.date = date;
        this.description = description;
        id = "" + title + date.hashCode() + "";
        complete = false;
    }

    public String toString() {
        String alwaysPrint = "\nTitle: " + title + "\nProject: " + project + "\nDue Date: " + date.getTime().toString() + "\nDescription: " + description + "\nID: " + id + "\n";
        if (complete == false) {
            return alwaysPrint + "\nComplete: No";
        } else {
            return alwaysPrint + "\nComplete: Yes";
        }
    }

    public String getTitle() {
        return title;
    }

    public GregorianCalendar getDate() {
        return date;
    }

}
