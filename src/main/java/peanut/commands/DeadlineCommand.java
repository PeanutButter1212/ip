package peanut.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import peanut.tasks.Deadline;
import peanut.tasks.PeanutException;
import peanut.tasks.Task;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

/**
 * Represents a command to create and add a Deadline task.
 */
public class DeadlineCommand extends Command {
    private final String args;
    /**
     * Creates a new DeadlineCommand with the given arguments.
     *
     * @param args the arguments containing description and due date
     */
    public DeadlineCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the DeadlineCommand.
     *
     * @param taskList The current list of tasks.
     * @param ui The user interface for displaying messages.
     * @return The confirmation message
     * @throws PeanutException If the arguments are invalid or the deadline cannot be created.
     */
    @Override
    public String run(TaskList taskList, Ui ui) throws PeanutException {
        int sizeBefore = taskList.size();
        Task deadlineTask = createDeadlineTask(args);
        taskList.add(deadlineTask);
        assert taskList.size() == sizeBefore + 1 : "TaskList size should increase by 1";
        assert taskList.getTasks().get(taskList.size() - 1) == deadlineTask : "New task should added to bottom";
        return ui.addListMessage(deadlineTask, taskList.size());
    }

    /**
     * Parses the raw arguments and constructs a Deadline task.
     *
     * @param args The raw argument string
     * @return A new Deadline task constructed from the parsed values
     * @throws PeanutException If the description/date is missing or the date cannot be parsed
     */
    private Task createDeadlineTask(String args) throws PeanutException {
        if (args.isBlank()) {
            throw new PeanutException("The description/time of deadline cannot be empty!! "
                    + "(e.g deadline read book /by 2019-10-15)");
        }

        String[] descriptionBySplit = args.split("/by", 2);

        if (descriptionBySplit.length < 2 || descriptionBySplit[0].isBlank()
                || descriptionBySplit[1].isBlank()) {
            throw new PeanutException("The description/time of deadline cannot be empty!! "
                    + "(e.g deadline read book /by 2019-10-15)");
        }

        String description = descriptionBySplit[0].trim();
        String endDateText = descriptionBySplit[1].trim();

        try {
            LocalDate.parse(endDateText);
        } catch (DateTimeParseException e) {
            throw new PeanutException("Please enter dates in yyyy-MM-dd format (e.g. 2019-10-15)!!!");
        }

        return new Deadline(description, endDateText);

    }
}
