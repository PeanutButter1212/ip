package peanut.commands;

import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

public class ListCommand extends Command {
    @Override
    public String run(TaskList tasklist, Ui ui) throws PeanutException {
        return ui.showListMessage(tasklist);
    }
}
