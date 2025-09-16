package peanut.commands;

import java.util.List;

import peanut.tasks.PeanutException;
import peanut.tasks.Task;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

/** Represents a command to find tasks by keyword. */
public class FindCommand extends Command {
    private final String args;

    public FindCommand(String args) {
        this.args = args;
    }

    @Override
    public String run(TaskList taskList, Ui ui) throws PeanutException {
        if (args.isBlank()) {
            throw new PeanutException("Please enter a valid word to find!! (e.g find book)");
        }
        List<Task> matched = taskList.match(args);
        return ui.showMatches(matched);
    }
}
