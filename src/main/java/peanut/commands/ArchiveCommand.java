package peanut.commands;

import peanut.storage.Storage;
import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

/**
 * Represents a command to archive the current task list.
 * <p>
 * Archiving saves all existing tasks to an archive file
 * and resets the active task list to start fresh.
 */

public class ArchiveCommand extends Command {
    private final Storage storage;

    /**
     * Creates a new ArchiveCommand with the given storage handler.
     *
     * @param storage The storage object responsible for archiving tasks.
     */
    public ArchiveCommand(Storage storage) {
        this.storage = storage;
    }
    /**
     * Executes the ArchiveCommand.
     * <p>
     * Archives all tasks in the task list and returns a
     * confirmation message from the UI.
     *
     * @param taskList The list of tasks to archive.
     * @param ui The user interface for displaying messages.
     * @return The confirmation message after archiving tasks.
     * @throws PeanutException If an error occurs during archiving.
     */
    @Override
    public String run(TaskList taskList, Ui ui) throws PeanutException {
        storage.archive(taskList);
        return ui.showArchiveMessage();
    }
}
