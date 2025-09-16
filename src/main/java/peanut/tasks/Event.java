package peanut.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start date and an end date.
 * Provides methods to display the task and store it in file format.
 */
public class Event extends Task {
    protected LocalDate startDate;
    protected LocalDate deadline;

    /**
     * Creates a new Event task with description, start and end times.
     *
     * @param description The description of the Event task.
     * @param startDate The start time (yyyy-MM-dd)
     * @param deadline The end time (yyyy-MM-dd)
     * */
    public Event(String description, String startDate, String deadline) {
        super(description);
        this.startDate = LocalDate.parse(startDate);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[E] " + super.toString() + " (from: " + startDate.format(dateFormat)
                + " to: " + deadline.format(dateFormat) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + startDate + " | " + deadline;
    }

}
