package peanut;

/**
 * Represents a task that user inputs
 * Provides methods to display, mark, and unmark tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Creates a Task with a description
     *
     * @param description type of task with relevant times
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
     * Sets isdone to true when mark task
     */
    public void mark() {this.isDone = true; }
    /**
     * Sets isdone to false when unmark task
     */
    public void unmark() {this.isDone = false ; }
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
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

}

