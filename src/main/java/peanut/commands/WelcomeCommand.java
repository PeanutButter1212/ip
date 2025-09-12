package peanut.commands;

import peanut.Peanut;
import peanut.PeanutException;
import peanut.TaskList;
import peanut.Ui;

public class WelcomeCommand extends Command {
    @Override
    public boolean run(TaskList taskList, Ui ui) throws PeanutException {
        ui.welcomeMessage();
        return false;
    }
}
