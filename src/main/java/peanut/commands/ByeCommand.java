package peanut.commands;

import peanut.PeanutException;
import peanut.TaskList;
import peanut.Ui;

public class ByeCommand extends Command {
    @Override
    public boolean run(TaskList taskList, Ui ui) throws PeanutException {
        ui.byeMessage();
        return false;
    }
}


