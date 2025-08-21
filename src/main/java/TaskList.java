import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void mark(int index) {
        tasks.get(index).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index));
    }

    public void unmark(int index) {
        tasks.get(index).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index));
    }

}
