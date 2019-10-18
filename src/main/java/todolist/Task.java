package todolist;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

    protected String title;
    protected String project;
    protected Date date;
    protected String description;
    protected String id;
    protected boolean complete;

    public Task(String title, String project, Date date, String description) {
        this.title = title;
        this.project = project;
        this.date = date;
        this.description = description;
        id = "" + title + date.getYear() + date.getMonth() + date.getDay() + date.getHours() + "";
        complete = false;
    }

    public String toString() {
        return "\nTitle: " + title + "\nProject: " + project + "\nDue Date: " + date + "\nDescription: " + description + "\nID: " + id + "\n";
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

}
