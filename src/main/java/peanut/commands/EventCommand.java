package peanut.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import peanut.tasks.Event;
import peanut.tasks.PeanutException;
import peanut.tasks.Task;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

/**
 * Represents a command to create and add a Event task.
 */
public class EventCommand extends Command {
    private final String args;
    /**
     * Creates a new EventCommand with the given arguments.
     *
     * @param args the arguments containing description, start and due date.
     */
    public EventCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the EventCommand.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface for displaying messages
     * @return The confirmation message
     * @throws PeanutException if the arguments are empty, malformed, or the dates are invalid
     */
    @Override
    public String run(TaskList taskList, Ui ui) throws PeanutException {
        Task eventTask = createEventTask(args);
        int sizeBefore = taskList.size();
        taskList.add(eventTask);
        assert taskList.size() == sizeBefore + 1 : "TaskList size should increase by 1";
        assert taskList.getTasks().get(taskList.size() - 1) == eventTask : "New task should added to bottom";
        return ui.addListMessage(eventTask, taskList.size());
    }

    /**
     * Parses the raw arguments and constructs an Event task.
     *
     * @param args The raw argument string.
     * @return A new Event task constructed from the parsed values.
     * @throws PeanutException if any part (description/start/end) is missing or a date cannot be parsed
     */

    private Task createEventTask(String args) throws PeanutException {
        if (args.isBlank()) {
            throw new PeanutException("The description/time of start and deadline "
                    + "cannot be empty!! (e.g event project meeting /from 2019-10-15 /to 2019-10-16)");
        }

        String[] descriptionFromSplit = args.split("/from", 2);

        if (descriptionFromSplit.length < 2) {
            throw new PeanutException("Please specify a start date using /from!");
        }

        String[] fromToSplit = descriptionFromSplit[1].split("/to", 2);

        if (fromToSplit.length < 2
                || descriptionFromSplit[0].isBlank() || fromToSplit[0].isBlank() || fromToSplit[1].isBlank()) {
            throw new PeanutException("The description/time of start and deadline "
                    + "cannot be empty!! (e.g event project meeting /from 2019-10-15 /to 2019-10-16)");
        }

        String description = descriptionFromSplit[0].trim();
        String endDateText = fromToSplit[1].trim();
        String startDateText = fromToSplit[0].trim();

        try {
            LocalDate.parse(startDateText);
            LocalDate.parse(endDateText);
        } catch (DateTimeParseException e) {
            throw new PeanutException("Please enter dates in yyyy-MM-dd format (e.g. 2019-10-15)!!!");
        }

        return new Event(description, startDateText, endDateText);

    }
}
