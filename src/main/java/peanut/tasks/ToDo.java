package peanut.tasks;

/**
 * Represents a to-do task without any specific date attached.
 * Provides methods to display the task and store it in file format.
 */
public class ToDo extends Task {
    /**
     * Creates a new ToDo task with description.
     *
     * @param description the description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }


}
