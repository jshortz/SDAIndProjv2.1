package todolist;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TaskListTest {

    @Test
    public void testIsListEmpty() {
        TaskList taskList = new TaskList();

        boolean isEmpty = taskList.isListEmpty();

        assertEquals(true, isEmpty);
    }

    @Test
    public void testGetTaskByTitle() {
        TaskList taskList = new TaskList();

        Task testTask = taskList.getTaskByTitle("Anything");

        assertEquals(null, testTask);
    }
}
