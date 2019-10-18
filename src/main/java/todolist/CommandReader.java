package todolist;

import java.util.Date;

public class CommandReader {

    public CommandReader() {

    }

    public Task getTaskToAddFromUser() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Please enter a short title for your task.");
        String title = inputReadFromUser.readString();
        System.out.println("Please enter a project to associate with the task.");
        String project = inputReadFromUser.readString();
        System.out.println("Please enter a description of the task");
        String description = inputReadFromUser.readString();
        Date date = getDateFromUser();
        System.out.println();
        return new Task(title, project, date, description);
    }

    public Date getDateFromUser() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Please enter the year the task is due as four digits between 2019 and 9999");
        int year = inputReadFromUser.readInt() - 1900;
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
        return new Date(year, month, day, hour, minute);
    }



    public String getTaskToEditFromUser() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Please enter the title of the task you wish to edit:");
        return inputReadFromUser.readString();
    }

    public String getTaskToRemoveFromUser() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Please enter the title of the task you wish to remove:");
        return inputReadFromUser.readString();
    }

    public boolean editTitle() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Do you want to edit the title of the task? Enter Y or N.");
        String answer = inputReadFromUser.readString();
        if (answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean editProject() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Do you want to edit the Project assigned to the task? Enter Y or N.");
        String answer = inputReadFromUser.readString();
        if (answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean editDescription() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Do you want to edit the Description of the task? Enter Y or N.");
        String answer = inputReadFromUser.readString();
        if (answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean editDate() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Do you want to edit the Due Date of the task? Enter Y or N.");
        String answer = inputReadFromUser.readString();
        if (answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean markComplete() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Do you wish to mark the task as complete?");
        String answer = inputReadFromUser.readString();
        if (answer.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getIfTitleSort() {
        InputReader inputReadFromUser = new InputReader();
        System.out.println("Do you want to sort by TITLE or DATE?");
        String userSortAnswer = inputReadFromUser.readString();
        if ((!userSortAnswer.equals("TITLE")) && (!userSortAnswer.equals("DATE"))) {
            System.out.println("Please enter TITLE or DATE to sort by.");
            userSortAnswer = inputReadFromUser.readString();
        }
        if (userSortAnswer.equals("TITLE")) {
            return true;
        } else {
            return false;
        }
    }
}
