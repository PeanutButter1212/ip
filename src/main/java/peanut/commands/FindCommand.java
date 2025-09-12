package peanut.commands;

import peanut.PeanutException;
import peanut.Task;
import peanut.TaskList;
import peanut.Ui;

import java.util.List;

public class FindCommand extends Command {
    private final String args;

    public FindCommand(String args) {
        this.args = args;
    }

    @Override
    public boolean run(TaskList taskList, Ui ui) throws PeanutException {
        if (args.isBlank()) {
            throw new PeanutException("Please enter a valid word to find!! (e.g find book)");
        }
        List<Task> matched = taskList.match(args);
        ui.showMatches(matched);
        return false;
    }
}
