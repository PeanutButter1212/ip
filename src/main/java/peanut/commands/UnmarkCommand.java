package peanut.commands;

import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

/** Represents a command to unmark a task. */
public class UnmarkCommand extends Command {
    private final String args;

    public UnmarkCommand(String args) {
        this.args = args;
    }


    @Override
    public String run(TaskList taskList, Ui ui) throws PeanutException {
        if (args.isBlank()) {
            throw new PeanutException("Please provide a task number for unmark! (e.g. unmark 1)");
        }

        int index;

        try {
            index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new PeanutException("Please enter a valid number (e.g. unmark 2)!");
        }

        if (index <= 0 || index > taskList.size()) {
            throw new PeanutException("Please provide a valid task number!");
        }

        int unmarkTaskNumber = index - 1;

        if (!taskList.getTasks().get(unmarkTaskNumber).getStatus()) {
            throw new PeanutException("Task is already unmarked!");
        }

        int sizeBefore = taskList.size();
        taskList.unmark(unmarkTaskNumber);
        assert taskList.size() == sizeBefore : "TaskList size should stay the same";
        assert !taskList.getTasks().get(unmarkTaskNumber).getStatus() : "Task must be unmarked";
        return ui.unmarkListMessage(taskList.getTasks().get(unmarkTaskNumber));
    }
}
