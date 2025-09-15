package peanut.commands;

import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public String run(TaskList taskList, Ui ui) throws PeanutException {
        return ui.byeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}


