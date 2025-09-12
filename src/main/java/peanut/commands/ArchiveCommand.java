package peanut.commands;

import peanut.PeanutException;
import peanut.Storage;
import peanut.TaskList;
import peanut.Ui;

public class ArchiveCommand extends Command {
    private final Storage storage;

    public ArchiveCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean run(TaskList taskList, Ui ui) throws PeanutException {
        ui.showArchiveMessage();
        storage.archive(taskList);
        return false;
    }
}
