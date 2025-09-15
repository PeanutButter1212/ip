package peanut.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import peanut.tasks.Deadline;
import peanut.tasks.PeanutException;
import peanut.tasks.Task;
import peanut.tasks.TaskList;
import peanut.ui.Ui;


public class DeadlineCommand extends Command {
    private final String args;

    public DeadlineCommand(String args) {
        this.args = args;
    }

    @Override
    public String run(TaskList taskList, Ui ui) throws PeanutException {
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
        String description = descriptionBySplit[0];
        String endDateText = descriptionBySplit[1].trim();

        try {
            LocalDate.parse(endDateText);
        } catch (DateTimeParseException e) {
            throw new PeanutException("Please enter dates in yyyy-MM-dd format (e.g. 2019-10-15)!!!");
        }
        int sizeBefore = taskList.size();
        Task deadlineTask = new Deadline(description, endDateText);
        taskList.add(deadlineTask);
        assert taskList.size() == sizeBefore + 1 : "TaskList size should increase by 1";
        assert taskList.getTasks().get(taskList.size() - 1) == deadlineTask : "New task should added to bottom";
        return ui.addListMessage(deadlineTask, taskList.size());
    }
}
