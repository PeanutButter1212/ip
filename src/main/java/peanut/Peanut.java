package peanut;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Entry point of the Peanut application.
 */

public class Peanut {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs the Peanut application with the given file path.
     *
     * @param filePath path to the file used for saving and loading tasks
     */

    public Peanut(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Runs the application event loop until the user exits.
     */
    public void run() {
        ui.welcomeMessage();
        boolean exit = false;
        while (!exit) {
            String input = ui.readCommand();
            try {
                exit = parser.parse(input, tasks, ui, storage);
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
            boolean exit = parser.parse(input, tasks, ui, storage);
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

