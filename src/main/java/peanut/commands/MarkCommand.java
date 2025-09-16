package peanut.commands;

import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

/** Represents a command to mark a task as done. */
public class MarkCommand extends Command {
    private final String args;

    public MarkCommand(String args) {
        this.args = args;
    }

    @Override
    public String run(TaskList taskList, Ui ui) throws PeanutException {
        if (args.isBlank()) {
            throw new PeanutException("Please provide a task number to mark! (e.g. mark 1)");
        }

        int index;

        try {
            index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new PeanutException("Please enter a valid number (e.g. mark 2)!");
        }

        if (index <= 0 || index > taskList.size()) {
            throw new PeanutException("Please provide a valid task number within the list's range!");
        }

        int taskNumber = index - 1;
        int sizeBefore = taskList.size();

        taskList.mark(taskNumber);

        assert taskList.size() == sizeBefore : "TaskList size should stay the same after marking";
        assert taskList.getTasks().get(taskNumber).getStatus() : "Task must be marked as done";

        return ui.markListMessage(taskList.getTasks().get(taskNumber));
    }

}
