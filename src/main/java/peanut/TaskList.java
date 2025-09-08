package peanut;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks users want.
 * Provides methods to add, delete, mark, and unmark tasks.
 */
public class TaskList {
    private List<Task> tasks;
    /**
     * Creates a TaskList with existing list of tasks
     *
     * @param tasks List of task previously saved
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }
    /**
     * Returns all tasks in TaskList.
     *
     * @return A list containing all the tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }
    /**
     * Mark task at the specified index.
     *
     * @param index Index of task that user wants to mark.
     */
    public void mark(int index) {
        tasks.get(index).mark();
    }
    /**
     * Unmark task at the specified index.
     *
     * @param index Index of task that user wants to unmark.
     */
    public void unmark(int index) {
        tasks.get(index).unmark();
    }
    /**
     * Returns number of tasks in the TaskList
     *
     * @return The number of tasks in the TaskList
     */
    public int size() {
        return tasks.size();
    }
    /**
     * Delete task at the specified index.
     *
     * @param index Index of task that user wants to delete.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Finds tasks in the list that contain the keyword.
     *
     * @param keyword The keyword user wants to search for.
     * @return A List with all matching tasks
     */
    public List<Task> match(String keyword) {
        List<Task> matches = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().contains(keyword)) {
                matches.add(t);
            }
        }
        return matches;
    }

}


