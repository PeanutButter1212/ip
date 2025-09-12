package peanut.commands;

import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

public class MarkCommand extends Command {
    private final String args;

    public MarkCommand(String args) {
        this.args = args;
    }

    @Override
    public boolean run(TaskList taskList, Ui ui) throws PeanutException {
        if (args.isBlank()) {
            throw new PeanutException("Please provide a task number for mark!! (e.g mark 1)");
        }

        if (Integer.parseInt(args) > taskList.size()) {
            throw new PeanutException("Please provide a valid task number!");
        }

        int taskNumber = Integer.parseInt(args) - 1;
        int sizeBefore = taskList.size();
        taskList.mark(taskNumber);
        ui.markListMessage(taskList.getTasks().get(taskNumber));
        assert taskList.size() == sizeBefore : "TaskList size should stay the same";
        assert taskList.getTasks().get(taskNumber).getStatus() : "Task must be marked done";
        return false;
    }

}
