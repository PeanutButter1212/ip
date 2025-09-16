package peanut.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date.
 * Provides methods to display the task and store it in file format.
 */

public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Creates a new Deadline task with the given description and end time.
     *
     * @param description the description of the Deadline task.
     * @param deadline The end time (yyyy-MM-dd).
     */

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);

    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D] " + super.toString() + " (by: " + deadline.format(dateFormat) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline;
    }



}
