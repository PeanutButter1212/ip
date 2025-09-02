package peanut;


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

    public static void main(String[] args) {
        new Peanut("data/peanut.txt").run();
    }
}

