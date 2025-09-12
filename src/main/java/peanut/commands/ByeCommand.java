package peanut.commands;

import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public boolean run(TaskList taskList, Ui ui) throws PeanutException {
        ui.byeMessage();
        return false;
    }
}


