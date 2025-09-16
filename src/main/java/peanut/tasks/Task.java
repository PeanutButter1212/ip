package peanut.tasks;

/**
 * Represents a task that user inputs.
 * Provides methods to display, mark, and unmark tasks, get description and get status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Creates a Task with a description
     * The task is initially marked as not done.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns "X" or "" based on isDone
     *
     * @return a string representing status of isDone
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    /**
     * Sets isDone to true when mark task
     */
    public void mark() {
        this.isDone = true;
    }
    /**
     * Sets isDone to false when unmark task
     */
    public void unmark() {
        this.isDone = false;
    }
    /**
     * Returns format of the task to be displayed by bot
     *
     * @return String format to be displayed by bot
     */
    @Override
    public String toString() {

        return "[" + getStatusIcon() + "] " + description;
    }
    /**
     * Returns format of the task to be written into file
     *
     * @return String format to be written into file
     */
    public String toFileFormat() {
        return "Task | " + (isDone ? "1" : "0") + " | " + description;
    }
    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean getStatus() {
        return this.isDone;
    }

}

