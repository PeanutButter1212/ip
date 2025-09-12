package peanut.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate startDate;
    protected LocalDate deadline;

    public Event(String description, String deadline, String startDate) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
        this.startDate = LocalDate.parse(startDate);
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
