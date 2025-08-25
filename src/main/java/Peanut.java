import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;





public class Peanut {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Peanut(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.tasks = new TaskList(storage.load());
        }


    public void run() {
        ui.welcomeMessage();
        boolean exit = false;
        while(!exit) {
            String input = ui.readCommand();
            try {
                exit = parser.parse(input, tasks, ui, storage);
            } catch (PeanutException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Peanut("data/peanut.txt").run();
    }
}

