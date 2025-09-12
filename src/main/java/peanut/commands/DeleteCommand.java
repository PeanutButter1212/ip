package peanut.commands;

import peanut.PeanutException;
import peanut.TaskList;
import peanut.Ui;

public class DeleteCommand extends Command {
    private final String args;

    public DeleteCommand(String args) {
        this.args = args;
    }

    @Override
    public boolean run(TaskList taskList, Ui ui) throws PeanutException {
        if (args.isBlank() || Integer.parseInt(args) > taskList.size()) {
            throw new PeanutException("Please enter a valid number!!");
        }
        int sizeBefore = taskList.size();
        ui.deleteListMessage(taskList,Integer.parseInt(args) - 1);
        taskList.delete(Integer.parseInt(args) - 1);
        assert taskList.size() == sizeBefore - 1 : "Delete must reduce size by 1";
        return false;
    }
}
