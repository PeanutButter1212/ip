package peanut.commands;

import peanut.PeanutException;
import peanut.TaskList;
import peanut.Ui;

public class ListCommand extends Command {
    @Override
    public boolean run(TaskList tasklist, Ui ui) throws PeanutException {
        ui.showListMessage(tasklist);
        return false;
    }
}
