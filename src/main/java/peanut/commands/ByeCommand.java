package peanut.commands;

import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

/**
 * Represents a command to exit the Peanut application.
 * <p>
 * When executed, this command returns a farewell message and signals
 * the chatbot to terminate.
 */
public class ByeCommand extends Command {
    /**
     * Executes the ByeCommand.
     *
     * @param taskList The current list of tasks.
     * @param ui The user interface for displaying messages.
     * @return The farewell message to the user.
     * @throws PeanutException If error occurs during ByeCommand.
     */
    @Override
    public String run(TaskList taskList, Ui ui) throws PeanutException {
        return ui.byeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}


