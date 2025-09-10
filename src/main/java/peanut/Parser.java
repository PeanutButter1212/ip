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
     * @param taskList TaskList that is loaded from previously saved file
     * @param ui UI to handle user instructions and display messages
     * @param storage Storage to handle loading and saving of files
     * @throws PeanutException If the input cannot be translated into a valid command
     */
    public boolean parse(String userInput, TaskList taskList, Ui ui, Storage storage) throws PeanutException {
        assert taskList != null: "TaskList must not be null";
        assert ui != null: "Ui must not be null";
        assert storage != null: "storage must not be null";
        assert userInput != null: "userInput must not be null";

        userInput = userInput.trim();

        if (userInput.equals("bye")) {
            ui.byeMessage();
            return true;
        }

        if (userInput.equals("list")) {
            ui.showListMessage(taskList);
            return false;
        }

        if (userInput.startsWith("mark")) {
            String[] parts = userInput.split(" ", 2);

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

        if (userInput.startsWith("unmark")) {
            String[] parts = userInput.split(" ", 2);

            if (parts.length < 2 || parts[1].isBlank()) {
                throw new PeanutException("Please provide a task number for unmark!! (e.g unmark 1)");
            }

            if (Integer.parseInt(parts[1]) > taskList.size()) {
                throw new PeanutException("Please provide a valid task number!");
            }

            int taskNumber = Integer.parseInt(parts[1]) - 1;
            int sizeBefore = taskList.size();
            taskList.unmark(taskNumber);
            storage.save(taskList);
            ui.unmarkListMessage(taskList.getTasks().get(taskNumber));
            assert taskList.size() == sizeBefore : "TaskList size should stay the same";
            assert taskList.getTasks().get(taskNumber).getStatus() : "Task must be unmarked";
            return false;

        }

        if (userInput.startsWith("todo")) {
            String[] parts = userInput.split("\\s+", 2);

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
        if (userInput.startsWith("deadline")) {
            String[] parts = userInput.split("\\s+", 2);

            if (parts.length < 2 || parts[1].isBlank()) {
                throw new PeanutException("The description/time of deadline cannot be empty!! "
                        + "(e.g deadline read book /by 2019-10-15)");
            }

            String[] sep = parts[1].split("/by", 2);

            if (sep.length < 2 || sep[0].isBlank() || sep[1].isBlank()) {
                throw new PeanutException("The description/time of deadline cannot be empty!! "
                        + "(e.g deadline read book /by 2019-10-15)");
            }

            String task = sep[0];
            String deadlineStr = sep[1].trim();

            try {
                LocalDate deadline = LocalDate.parse(deadlineStr);
            } catch (DateTimeParseException e) {
                throw new PeanutException("Please enter dates in yyyy-MM-dd format (e.g. 2019-10-15)!!!");
            }

            Task deadlineTask = new Deadline(task, deadlineStr);
            int sizeBefore = taskList.size();
            taskList.add(deadlineTask);
            storage.save(taskList);
            ui.addListMessage(deadlineTask, taskList.size());
            assert taskList.size() == sizeBefore + 1 : "TaskList size should increase by 1";
            assert taskList.getTasks().get(taskList.size() - 1) == deadlineTask : "New task should added to bottom";
            return false;
        }

        if (userInput.startsWith("event")) {
            String[] parts = userInput.split("\\s+", 2);

            if (parts.length < 2 || parts[1].isBlank()) {
                throw new PeanutException("The description/time of start and deadline "
                        + "cannot be empty!! (e.g event project meeting /from 2019-10-15 /to 2019-10-16)");
            }

            String[] descriptionAndTime = parts[1].split("/from", 2);

            if (descriptionAndTime.length < 2) {
                throw new PeanutException("The description/time of start and deadline "
                        + "cannot be empty!! (e.g event project meeting /from 2019-10-15 /to 2019-10-16)");
            }

            String[] startAndEnd = descriptionAndTime[1].split("/to", 2);

            if (descriptionAndTime.length < 2 || startAndEnd.length < 2
                    || descriptionAndTime[0].isBlank() || startAndEnd[0].isBlank() || startAndEnd[1].isBlank()) {
                throw new PeanutException("The description/time of start and deadline "
                        + "cannot be empty!! (e.g event project meeting /from 2019-10-15 /to 2019-10-16)");
            }
            String task = descriptionAndTime[0];
            String deadlineStr = startAndEnd[1].trim();
            String startDateStr = startAndEnd[0].trim();

            try {
                LocalDate startDate = LocalDate.parse(startDateStr);
                LocalDate deadline = LocalDate.parse(deadlineStr);

            } catch (DateTimeParseException e) {
                throw new PeanutException("Please enter dates in yyyy-MM-dd format (e.g. 2019-10-15)!!!");
            }

            Task eventTask = new Event(task, deadlineStr, startDateStr);
            int sizeBefore = taskList.size();
            taskList.add(eventTask);
            storage.save(taskList);
            ui.addListMessage(eventTask, taskList.size());
            assert taskList.size() == sizeBefore + 1 : "TaskList size should increase by 1";
            assert taskList.getTasks().get(taskList.size() - 1) == eventTask : "New task should added to bottom";

            return false;
        }

        if (userInput.startsWith("delete")) {
            String[] parts = userInput.split("\\s+", 2);
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

        if (userInput.startsWith("find")) {
            String[] parts = userInput.split("\\s+", 2);
            if (parts.length < 2 || parts[1].isBlank()) {
                throw new PeanutException("Please enter a valid word to find!! (e.g find book)");
            }
            List<Task> matched = taskList.match(parts[1]);
            ui.showMatches(matched);
            return false;
        }

        throw new PeanutException("Sorry idk wat u saying bro");

    }


}

