package peanut.commands;

import peanut.PeanutException;
import peanut.TaskList;
import peanut.Ui;

public class UnmarkCommand extends Command{
    private final String args;

    public UnmarkCommand(String args) {
        this.args = args;
    }


    @Override
    public boolean run(TaskList taskList, Ui ui) throws PeanutException {
        if (args.isBlank()) {
            throw new PeanutException("Please provide a task number for unmark!! (e.g unmark 1)");
        }

        if (Integer.parseInt(args) > taskList.size()) {
            throw new PeanutException("Please provide a valid task number!");
        }


        int unmarkTaskNumber = Integer.parseInt(args) - 1;

        if (taskList.getTasks().get(unmarkTaskNumber).getStatus() == false) {
            throw new PeanutException("Task already unmarked!!");
        }
        int sizeBefore = taskList.size();
        taskList.unmark(unmarkTaskNumber);
        ui.unmarkListMessage(taskList.getTasks().get(unmarkTaskNumber));
        assert taskList.size() == sizeBefore : "TaskList size should stay the same";
        assert !taskList.getTasks().get(unmarkTaskNumber).getStatus() : "Task must be unmarked";
        return false;
    }
}
