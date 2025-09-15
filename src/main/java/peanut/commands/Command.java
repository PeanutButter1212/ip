package peanut.commands;

import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

/**
 * Abstract class representing a user command in the Peanut chatbot.
 * Each command defines its own behavior when executed.
 */

public abstract class Command {
    /**
     * Executes the command using the provided task list and UI.
     *
     * @param taskList The TaskList containing the user's tasks.
     * @param ui The Ui instance for displaying messages to the user.
     * @return True if the command was executed successfully else false.
     * @throws PeanutException If an error occurs during command execution.
     */
    public abstract String run(TaskList taskList, Ui ui) throws PeanutException;

    public boolean isExit() {
        return false;
    }
}
