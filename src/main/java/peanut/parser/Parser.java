package peanut.parser;

import peanut.ui.Ui;
import peanut.commands.ArchiveCommand;
import peanut.commands.ByeCommand;
import peanut.commands.Command;
import peanut.commands.DeadlineCommand;
import peanut.commands.DeleteCommand;
import peanut.commands.EventCommand;
import peanut.commands.FindCommand;
import peanut.commands.ListCommand;
import peanut.commands.MarkCommand;
import peanut.commands.TodoCommand;
import peanut.commands.UnmarkCommand;
import peanut.commands.WelcomeCommand;
import peanut.storage.Storage;
import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;

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
    public Command parse(String userInput, TaskList taskList, Ui ui, Storage storage) throws PeanutException {
        assert taskList != null: "TaskList must not be null";
        assert ui != null: "Ui must not be null";
        assert storage != null: "storage must not be null";
        assert userInput != null: "userInput must not be null";

        String[] parts = userInput.split(" ", 2);
        String command = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
        case "welcome": {
            return new WelcomeCommand();
        }

        case "bye": {
            return new ByeCommand();
        }

        case "list": {
            return new ListCommand();
        }

        case "mark": {
            return new MarkCommand(args);
        }

        case "unmark": {
           return new UnmarkCommand(args);
        }

        case "todo": {
            return new TodoCommand(args);
        }

        case "deadline": {
            return new DeadlineCommand(args);
        }

        case "event": {
            return new EventCommand(args);
        }

        case "delete": {
            return new DeleteCommand(args);
        }

        case "find": {
            return new FindCommand(args);
        }

        case "archive": {
            return new ArchiveCommand(storage);
        }
        default:
            throw new PeanutException("Sorry idk wat u saying bro");
        }
    }
}







