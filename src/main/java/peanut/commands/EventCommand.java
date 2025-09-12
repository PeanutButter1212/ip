package peanut.commands;

import peanut.Event;
import peanut.PeanutException;
import peanut.Task;
import peanut.TaskList;
import peanut.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private final String args;

    public EventCommand(String args) {
        this.args = args;
    }

    @Override
    public boolean run(TaskList taskList, Ui ui) throws PeanutException {
        if (args.isBlank()) {
            throw new PeanutException("The description/time of start and deadline "
                    + "cannot be empty!! (e.g event project meeting /from 2019-10-15 /to 2019-10-16)");
        }

        String[] descriptionFromSplit = args.split("/from", 2);

        if (descriptionFromSplit.length < 2) {
            throw new PeanutException("The description/time of start and deadline "
                    + "cannot be empty!! (e.g event project meeting /from 2019-10-15 /to 2019-10-16)");
        }

        String[] fromToSplit = descriptionFromSplit[1].split("/to", 2);

        if (descriptionFromSplit.length < 2 || fromToSplit.length < 2
                || descriptionFromSplit[0].isBlank() || fromToSplit[0].isBlank() || fromToSplit[1].isBlank()) {
            throw new PeanutException("The description/time of start and deadline "
                    + "cannot be empty!! (e.g event project meeting /from 2019-10-15 /to 2019-10-16)");
        }

        String description = descriptionFromSplit[0];
        String endDateText = fromToSplit[1].trim();
        String startDateText = fromToSplit[0].trim();

        try {
            LocalDate startDate = LocalDate.parse(startDateText);
            LocalDate deadline = LocalDate.parse(endDateText);
        } catch (DateTimeParseException e) {
            throw new PeanutException("Please enter dates in yyyy-MM-dd format (e.g. 2019-10-15)!!!");
        }
        int sizeBefore = taskList.size();
        Task eventTask = new Event(description, endDateText, startDateText);
        taskList.add(eventTask);
        ui.addListMessage(eventTask, taskList.size());
        assert taskList.size() == sizeBefore + 1 : "TaskList size should increase by 1";
        assert taskList.getTasks().get(taskList.size() - 1) == eventTask : "New task should added to bottom";
        return false;
    }
}
