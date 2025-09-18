package peanut;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import peanut.commands.Command;
import peanut.parser.Parser;
import peanut.storage.Storage;
import peanut.tasks.PeanutException;
import peanut.tasks.TaskList;
import peanut.ui.Ui;

/**
 * Entry point of the Peanut application.
 */

public class Peanut {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;
    private boolean hasWelcomed;

    /**
     * Constructs the Peanut application with the given file path.
     *
     * @param filePath path to the file used for saving and loading tasks
     */

    public Peanut(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.taskList = new TaskList(storage.load());
        this.hasWelcomed = false;
    }

    /**
     * Runs the application event loop until the user exits.
     */
    public void run() {
        boolean shouldExit = false;
        System.out.println(ui.welcomeMessage());

        while (!shouldExit) {
            String input = ui.readCommand();
            try {
                Command command = parser.parse(input, taskList, ui, storage);
                String msg = command.run(taskList, ui);
                System.out.println(msg);
                storage.save(taskList);
                shouldExit = command.isExit();
            } catch (PeanutException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input the input that users provide to chatbot
     * @return The chatbot's reply including welcome message on the first call
     */
    public String getResponse(String input) {
        StringBuilder sb = new StringBuilder();

        //if first call and no user input then return welcome message
        if (!hasWelcomed) {
            hasWelcomed = true;

            if (input == null || input.isBlank()) {
                return ui.welcomeMessage();
            }

            sb.append(ui.welcomeMessage()).append("\n");
        }


        try {
            Command command = parser.parse(input, taskList, ui, storage);
            String msg = command.run(taskList, ui);
            storage.save(taskList);
            sb.append(msg);
            // Used the help of chatgpt to make sure users can see message before closing by using a delay
            if (command.isExit()) {
                PauseTransition delay = new PauseTransition(Duration.millis(400));
                delay.setOnFinished(e -> Platform.exit());
                delay.play();
            }

        } catch (PeanutException e) {
            sb.append(ui.showErrorMessage(e.getMessage()));
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        new Peanut("data/peanut.txt").run();


    }
}

