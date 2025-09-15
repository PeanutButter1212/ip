package peanut.commands;

import peanut.storage.Storage;
import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

public class ArchiveCommand extends Command {
    private final Storage storage;

    public ArchiveCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String run(TaskList taskList, Ui ui) throws PeanutException {
        storage.archive(taskList);
        return ui.showArchiveMessage();
    }
}
