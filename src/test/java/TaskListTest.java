import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import peanut.tasks.Task;
import peanut.tasks.TaskList;
import peanut.tasks.ToDo;



public class TaskListTest {

    @Test
    public void delete_success() {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.add(new ToDo("eat lunch"));
        tasks.add(new ToDo("eat dinner"));

        Task removedTask = tasks.getTasks().get(1);
        tasks.delete(1);

        assertEquals(tasks.size(), 1);
        assertFalse(tasks.getTasks().contains(removedTask));
    }

    @Test
    public void add_success() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Task addedTask = new ToDo("eat lunch");
        tasks.add(addedTask);

        assertEquals(tasks.size(), 1);
        assertTrue(tasks.getTasks().contains(addedTask));
    }
}
