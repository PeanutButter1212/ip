package peanut.commands;

import peanut.tasks.PeanutException;
import peanut.tasks.Task;
import peanut.tasks.TaskList;
import peanut.tasks.ToDo;
import peanut.ui.Ui;

/** Represents a command to create and add a todo task. */
public class TodoCommand extends Command {
    private final String args;

    public TodoCommand(String args) {
        this.args = args;
    }

    @Override
    public String run(TaskList taskList, Ui ui) throws PeanutException {
        Task todoTask = createTodoTask(args);
        int sizeBefore = taskList.size();
        taskList.add(todoTask);
        assert taskList.size() == sizeBefore + 1 : "TaskList size should increase by 1";
        assert taskList.getTasks().get(taskList.size() - 1) == todoTask : "New task should added to bottom";
        return ui.addListMessage(todoTask, taskList.size());
    }

    private Task createTodoTask(String args) throws PeanutException {
        if (args.isBlank()) {
            throw new PeanutException("Please add description for todo!! (e.g todo read book)");
        }
        return new ToDo(args);
    }
}
