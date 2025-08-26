package peanut;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void mark(int index) {
        tasks.get(index).mark();
    }

    public void unmark(int index) {
        tasks.get(index).unmark();
    }

    public int size() {
        return tasks.size();
    }

    public void delete(int taskNumber) {
        tasks.remove(taskNumber);
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
