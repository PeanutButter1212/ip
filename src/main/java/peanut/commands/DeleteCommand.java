package peanut.commands;

import peanut.tasks.PeanutException;
import peanut.tasks.Task;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

/**
 * Represents a command to delete task from the list.
 */
public class DeleteCommand extends Command {
    private final String args;

    /**
     * Creates a new DeadlineCommand with the given arguments.
     *
     * @param args the arguments containing task number.
     */
    public DeleteCommand(String args) {
        this.args = args;
    }
    /**
     * Executes the DeleteCommand.
     *
     * @param taskList The current list of tasks.
     * @param ui The user interface for displaying messages.
     * @return The confirmation message after deletion
     * @throws PeanutException if the arguments are invalid.
     */
    @Override
    public String run(TaskList taskList, Ui ui) throws PeanutException {
        if (args.isBlank()) {
            throw new PeanutException("Please enter a task number!");
        }

        int index;
        try {
            index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new PeanutException("Please enter a valid number (e.g delete 2)!");
        }
        int sizeBefore = taskList.size();
        Task removed = taskList.getTasks().get(index - 1);
        taskList.delete(index - 1);
        assert taskList.size() == sizeBefore - 1 : "Delete must reduce size by 1";
        return ui.deleteListMessage(removed, Integer.parseInt(args) - 1);

    }
}
