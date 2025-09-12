package peanut.commands;

import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

public class WelcomeCommand extends Command {
    @Override
    public boolean run(TaskList taskList, Ui ui) throws PeanutException {
        ui.welcomeMessage();
        return false;
    }
}
