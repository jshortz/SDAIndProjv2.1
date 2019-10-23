package todolist;
import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TaskTest {

    @Test
    public void testToString() {
        GregorianCalendar dateForTest = new GregorianCalendar(2020, 10, 10, 10, 10);
        Task newTask = new Task("Do Dishes", "Household", dateForTest, "Do the dishes");
        String expectedToString = "\nTitle: Do Dishes\nProject: Household\nDue Date: " + dateForTest.getTime().toString() + "\nDescription: Do the dishes" +
                "\nID: " + newTask.id + "\nComplete: No\n";

        assertEquals(expectedToString, newTask.toString());
    }

    @Test
    public void testGetTitle() {
        GregorianCalendar dateForTest = new GregorianCalendar(2020, 10, 10, 10, 10);
        Task newTask = new Task("Do Dishes", "Household", dateForTest, "Do the dishes");
        String expectedTitle = "Do Dishes";
        String badTitle = "Don't Do The Dishes";

        assertEquals(expectedTitle, newTask.getTitle());
        assertFalse(badTitle.equals(newTask.getTitle()));
    }

    @Test
    public void testGetDate() {
        GregorianCalendar dateForTest = new GregorianCalendar(2020, 10, 10, 10, 10);
        Task newTask = new Task("Do Dishes", "Household", dateForTest, "Do the dishes");

        GregorianCalendar badDate = new GregorianCalendar(2010, 10, 10, 10, 10);

        assertEquals(dateForTest, newTask.getDate());
        assertFalse(badDate.equals(newTask.getDate()));
    }

    @Test
    public void testIsDuplicateTask() {
        GregorianCalendar dateForTest = new GregorianCalendar(2020, 10, 10, 10, 10);
        GregorianCalendar badDate = new GregorianCalendar(2010, 10, 10, 10, 10);
        Task newTask = new Task("Do Dishes", "Household", dateForTest, "Do the dishes");
        Task secondTask = new Task("Do Laundry", "Household", dateForTest, "Do the laundry");
        ArrayList<Task> emptyTaskList = new ArrayList<>();
        ArrayList<Task> twoTaskList = new ArrayList<>();
        twoTaskList.add(newTask);
        twoTaskList.add(secondTask);

        Task confirmDupe = new Task("Do Dishes", "Household", dateForTest, "Do the dishes");
        Task confirmDupeLowerCase = new Task("do dishes", "household", dateForTest, "do the dishes");
        Task confirmDupeAnyProject = new Task("Do Dishes", "anything", dateForTest, "Do the dishes");
        Task confirmDupeBadTitle = new Task("Walk Dog", "anything", dateForTest, "Walk the dog");
        Task confirmDupeBadDate = new Task("Do Dishes", "household", badDate, "Do the dishes");
        boolean emptyList = confirmDupe.isDuplicateTask(confirmDupe.title, confirmDupe.date, emptyTaskList);
        boolean twoList = confirmDupe.isDuplicateTask(confirmDupe.title, confirmDupe.date, twoTaskList);
        boolean twoListLC = confirmDupeLowerCase.isDuplicateTask(confirmDupeLowerCase.title, confirmDupeLowerCase.date, twoTaskList);
        boolean twoListProject = confirmDupeAnyProject.isDuplicateTask(confirmDupeAnyProject.title, confirmDupeAnyProject.date, twoTaskList);
        boolean twoListBadTitle = confirmDupeBadTitle.isDuplicateTask(confirmDupeBadTitle.title, confirmDupeBadTitle.date, twoTaskList);
        boolean twoListBadDate = confirmDupeBadDate.isDuplicateTask(confirmDupeBadDate.title, confirmDupeBadDate.date, twoTaskList);

        assertEquals(false, emptyList);
        assertEquals(true, twoList);
        assertEquals(true, twoListLC);
        assertEquals(true, twoListProject);
        assertEquals(false, twoListBadTitle);
        assertEquals(false, twoListBadDate);
    }

}
