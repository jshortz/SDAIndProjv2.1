package todolist;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExecutableShellTest {

    @Test
    public void testIsValidCommand() {
        ExecutableShell executableShell = new ExecutableShell();

        String command = "HELP";
        String notCommand = "CAKE";
        String commandLC = "help";

        boolean isCommand = executableShell.isValidCommand(command);
        boolean isNotCommand = executableShell.isValidCommand(notCommand);
        boolean isCommandLC = executableShell.isValidCommand(commandLC);

        assertEquals(true, isCommand);
        assertEquals(false, isNotCommand);
        assertEquals(true, isCommandLC);
    }

}
