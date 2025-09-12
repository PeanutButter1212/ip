package peanut.commands;

import peanut.PeanutException;
import peanut.Task;
import peanut.TaskList;
import peanut.ToDo;
import peanut.Ui;

public class TodoCommand extends Command{
    private final String args;

    public TodoCommand(String args) {
        this.args = args;
    }

    @Override
    public boolean run(TaskList taskList, Ui ui) throws PeanutException {
        if (args.isBlank()) {
            throw new PeanutException("Please add description for todo!! (e.g todo read book)");
        }

        Task todoTask = new ToDo(args);
        int sizeBefore = taskList.size();
        taskList.add(todoTask);
        ui.addListMessage(todoTask, taskList.size());
        assert taskList.size() == sizeBefore + 1 : "TaskList size should increase by 1";
        assert taskList.getTasks().get(taskList.size() - 1) == todoTask : "New task should added to bottom";
        return false;
    }
}
