package peanut;

import peanut.commands.Command;
import peanut.commands.WelcomeCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Entry point of the Peanut application.
 */

public class Peanut {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
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
    }

    /**
     * Runs the welcome command to show welcome message
     */
    private void ensureWelcomed() throws PeanutException {
        if (!hasWelcomed) {
            ui.welcomeMessage();
            hasWelcomed = true;
        }
    }

    /**
     * Runs the application event loop until the user exits.
     */
    public void run() {
        try {
            ensureWelcomed();
        } catch (PeanutException e) {
            ui.showErrorMessage(e.getMessage());
        }
        boolean exit = false;
        while (!exit) {
            String input = ui.readCommand();
            try {
                Command command = parser.parse(input, taskList, ui, storage);
                exit = command.run(taskList,ui);
                storage.save(taskList);
            } catch (PeanutException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input the input that users provide to chatbot
     */
    public String getResponse(String input) {
        // Since we use printout for statements we need capture them and turn to string
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream capture = new PrintStream(buf);
        System.setOut(capture);

        String reply;
        try {
            Command command = parser.parse(input, taskList, ui, storage);
            boolean exit = command.run(taskList,ui);
            storage.save(taskList);

            reply = buf.toString().trim();
        } catch (PeanutException e) {
            reply = e.getMessage();
        } finally {
            System.out.flush();
            System.setOut(originalOut);
        }

        return reply;
    }


    public static void main(String[] args) {
        new Peanut("data/peanut.txt").run();


    }
}

