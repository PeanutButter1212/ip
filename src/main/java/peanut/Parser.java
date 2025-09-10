package peanut;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The Parser class interprets user input.
 * It is responsible for parsing command strings by users into instructions
 * for the chatbot to execute.
 */

public class Parser {
    /**
     * Parses users input and determines which commands to execute
     *
     * @param userInput Command that user enters.
     * @param taskList  TaskList that is loaded from previously saved file
     * @param ui        UI to handle user instructions and display messages
     * @param storage   Storage to handle loading and saving of files
     * @throws PeanutException If the input cannot be translated into a valid command
     */
    public boolean parse(String userInput, TaskList taskList, Ui ui, Storage storage) throws PeanutException {
        assert taskList != null: "TaskList must not be null";
        assert ui != null: "Ui must not be null";
        assert storage != null: "storage must not be null";
        assert userInput != null: "userInput must not be null";

        userInput = userInput.trim();
        String[] parts = userInput.split("\\s+", 2);
        String command = parts[0];


        switch (command) {
        case "bye": {
            ui.byeMessage();
            return true;
        }

        case "list": {
            ui.showListMessage(taskList);
            return false;
        }

        case "mark": {
            if (parts.length < 2 || parts[1].isBlank()) {
                throw new PeanutException("Please provide a task number for mark!! (e.g mark 1)");
            }

            if (Integer.parseInt(parts[1]) > taskList.size()) {
                throw new PeanutException("Please provide a valid task number!");
            }

            int taskNumber = Integer.parseInt(parts[1]) - 1;
            int sizeBefore = taskList.size();
            taskList.mark(taskNumber);
            storage.save(taskList);
            ui.markListMessage(taskList.getTasks().get(taskNumber));
            assert taskList.size() == sizeBefore : "TaskList size should stay the same";
            assert taskList.getTasks().get(taskNumber).getStatus() : "Task must be marked done";
            return false;
        }

        case "unmark": {
            if (parts.length < 2 || parts[1].isBlank()) {
                throw new PeanutException("Please provide a task number for unmark!! (e.g unmark 1)");
            }

            if (Integer.parseInt(parts[1]) > taskList.size()) {
                throw new PeanutException("Please provide a valid task number!");
            }


            int unmarkTaskNumber = Integer.parseInt(parts[1]) - 1;

            if (taskList.getTasks().get(unmarkTaskNumber).getStatus() == false) {
                throw new PeanutException("Task already unmarked!!");
            }
            int sizeBefore = taskList.size();
            taskList.unmark(unmarkTaskNumber);
            storage.save(taskList);
            ui.unmarkListMessage(taskList.getTasks().get(unmarkTaskNumber));
            assert taskList.size() == sizeBefore : "TaskList size should stay the same";
            assert taskList.getTasks().get(unmarkTaskNumber).getStatus() : "Task must be unmarked";
            return false;
        }

        case "todo": {
            if (parts.length < 2 || parts[1].isBlank()) {
                throw new PeanutException("Please add description for todo!! (e.g todo read book)");
            }

            Task todoTask = new ToDo(parts[1]);
            int sizeBefore = taskList.size();
            taskList.add(todoTask);
            storage.save(taskList);
            ui.addListMessage(todoTask, taskList.size());
            assert taskList.size() == sizeBefore + 1 : "TaskList size should increase by 1";
            assert taskList.getTasks().get(taskList.size() - 1) == todoTask : "New task should added to bottom";
            return false;
        }

        case "deadline": {
            if (parts.length < 2 || parts[1].isBlank()) {
                throw new PeanutException("The description/time of deadline cannot be empty!! "
                        + "(e.g deadline read book /by 2019-10-15)");
            }

            String[] descriptionBySplit = parts[1].split("/by", 2);

            if (descriptionBySplit.length < 2 || descriptionBySplit[0].isBlank() ||
                    descriptionBySplit[1].isBlank()) {
                throw new PeanutException("The description/time of deadline cannot be empty!! "
                        + "(e.g deadline read book /by 2019-10-15)");
            }
            String description = descriptionBySplit[0];
            String endDateText = descriptionBySplit[1].trim();

            try {
                LocalDate.parse(endDateText);
            } catch (DateTimeParseException e) {
                throw new PeanutException("Please enter dates in yyyy-MM-dd format (e.g. 2019-10-15)!!!");
            }
            int sizeBefore = taskList.size();
            Task deadlineTask = new Deadline(description, endDateText);
            taskList.add(deadlineTask);
            storage.save(taskList);
            ui.addListMessage(deadlineTask, taskList.size());
            assert taskList.size() == sizeBefore + 1 : "TaskList size should increase by 1";
            assert taskList.getTasks().get(taskList.size() - 1) == deadlineTask : "New task should added to bottom";
            return false;
        }


        case "event": {
            if (parts.length < 2 || parts[1].isBlank()) {
                throw new PeanutException("The description/time of start and deadline "
                        + "cannot be empty!! (e.g event project meeting /from 2019-10-15 /to 2019-10-16)");
            }

            String[] descriptionFromSplit = parts[1].split("/from", 2);

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
            storage.save(taskList);
            ui.addListMessage(eventTask, taskList.size());
            assert taskList.size() == sizeBefore + 1 : "TaskList size should increase by 1";
            assert taskList.getTasks().get(taskList.size() - 1) == eventTask : "New task should added to bottom";
            return false;
        }


        case "delete": {
            if (parts.length < 2 || parts[1].isBlank() || Integer.parseInt(parts[1]) > taskList.size()) {
                throw new PeanutException("Please enter a valid number!!");
            }
            int sizeBefore = taskList.size();
            ui.deleteListMessage(taskList,Integer.parseInt(parts[1]) - 1);
            taskList.delete(Integer.parseInt(parts[1]) - 1);
            storage.save(taskList);
            assert taskList.size() == sizeBefore - 1 : "Delete must reduce size by 1";
            return false;
        }

        case "find": {
            if (parts.length < 2 || parts[1].isBlank()) {
                throw new PeanutException("Please enter a valid word to find!! (e.g find book)");
            }
            List<Task> matched = taskList.match(parts[1]);
            ui.showMatches(matched);
            return false;
        }

        default:
            throw new PeanutException("Sorry idk wat u saying bro");
        }
    }
}







