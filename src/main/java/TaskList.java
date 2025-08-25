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

}
