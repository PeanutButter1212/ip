package peanut.commands;

import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

public class ListCommand extends Command {
    @Override
    public boolean run(TaskList tasklist, Ui ui) throws PeanutException {
        ui.showListMessage(tasklist);
        return false;
    }
}
