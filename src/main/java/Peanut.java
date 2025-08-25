import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;





public class Peanut {


    public static void main(String[] args) {
        Ui ui = new Ui();
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage(Paths.get("data", "peanut.txt").toString());
        TaskList taskList = new TaskList(storage.load());


        ui.welcomeMessage();




        while (true) {
            String userInput = ui.readCommand();
            try {
                if (userInput.equals("bye")) {
                    ui.byeMessage();
                    break;

                } else if (userInput.equals("list")) {
                    ui.showList(taskList);

                } else if (userInput.startsWith("mark")) {
                    String[] parts = userInput.split(" ", 2);

                    if (parts.length < 2 || parts[1].isBlank()) {
                        throw new PeanutException("Please provide a task number for mark!! (e.g mark 1)");
                    }

                    if (Integer.parseInt(parts[1]) > taskList.size()) {
                        throw new PeanutException("Please provide a valid task number!");
                    }

                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    taskList.mark(taskNumber);
                    storage.save(taskList);
                    ui.markList(taskList.getTasks().get(taskNumber));

                } else if (userInput.startsWith("unmark")) {
                    String[] parts = userInput.split(" ", 2);

                    if (parts.length < 2 || parts[1].isBlank()) {
                        throw new PeanutException("Please provide a task number for unmark!! (e.g unmark 1)");
                    }

                    if (Integer.parseInt(parts[1]) > taskList.size()) {
                        throw new PeanutException("Please provide a valid task number!");
                    }

                    int taskNumber = Integer.parseInt(parts[1]) - 1;
                    taskList.unmark(taskNumber);
                    storage.save(taskList);
                    ui.unmarkList(taskList.getTasks().get(taskNumber));

                } else if (userInput.startsWith("todo")) {
                    String[] parts = userInput.split("\\s+", 2);

                    if (parts.length < 2 || parts[1].isBlank()) {
                        throw new PeanutException("Please add description for todo!! (e.g todo read book)");
                    }

                    Task todoTask = new ToDo(parts[1]);
                    taskList.add(todoTask);
                    storage.save(taskList);
                    ui.addList(todoTask,taskList.size());

                } else if (userInput.startsWith("deadline")) {
                    String[] parts = userInput.split("\\s+", 2);

                    if (parts.length < 2 || parts[1].isBlank()) {
                        throw new PeanutException("The description/time of deadline cannot be empty!! (e.g deadline read book /by Friday)");
                    }

                    String[] sep = parts[1].split("/by", 2);

                    if (sep.length < 2 || sep[0].isBlank() || sep[1].isBlank()) {
                        throw new PeanutException("The description/time of deadline cannot be empty!! (e.g deadline read book /by Friday)");
                    }

                    String task = sep[0];
                    String deadlineStr = sep[1].trim();

                    try {
                        LocalDate deadline = LocalDate.parse(deadlineStr);
                        Task deadlineTask = new Deadline(task, deadlineStr);
                        taskList.add(deadlineTask);
                        storage.save(taskList);
                        ui.addList(deadlineTask,taskList.size());
                    } catch (DateTimeParseException e) {
                        throw new PeanutException("Please enter dates in yyyy-MM-dd format (e.g. 2019-10-15)!!!");
                    }

                } else if (userInput.startsWith("event")) {
                    String[] parts = userInput.split("\\s+", 2);

                    if (parts.length < 2 || parts[1].isBlank()) {
                        throw new PeanutException("The description/time of start and deadline " +
                                "cannot be empty!! (e.g event project meeting /from Mon 2pm /to 4pm)");
                    }

                    String[] sep = parts[1].split("/from", 2);

                    if (sep.length < 2) {
                        throw new PeanutException("The description/time of start and deadline " +
                                "cannot be empty!! (e.g event project meeting /from Mon 2pm /to 4pm)");
                    }

                    String[] sep2 = sep[1].split("/to", 2);

                    if (sep.length < 2 || sep2.length < 2
                            || sep[0].isBlank() || sep2[0].isBlank() || sep2[1].isBlank()) {
                        throw new PeanutException("The description/time of start and deadline " +
                                "cannot be empty!! (e.g event project meeting /from Mon 2pm /to 4pm)");
                    }
                    String task = sep[0];
                    String deadlineStr = sep2[1].trim();
                    String startDateStr = sep2[0].trim();

                    try {
                        LocalDate startDate = LocalDate.parse(startDateStr);
                        LocalDate deadline = LocalDate.parse(deadlineStr);

                        Task eventTask = new Event(task, deadlineStr, startDateStr);
                        taskList.add(eventTask);
                        storage.save(taskList);
                        ui.addList(eventTask,taskList.size());
                    } catch (DateTimeParseException e) {
                        throw new PeanutException("Please enter dates in yyyy-MM-dd format (e.g. 2019-10-15)!!!");
                    }

                }
                else if (userInput.startsWith("delete")){
                    String[] parts = userInput.split("\\s+", 2);
                    if (parts.length < 2 || parts[1].isBlank() || Integer.parseInt(parts[1]) > taskList.size()) {
                        throw new PeanutException("Please enter a valid number!!");
                    }
                    taskList.delete(Integer.parseInt(parts[1])-1);
                    storage.save(taskList);
                    ui.deleteList(taskList);
                } else {
                  throw new PeanutException("Sorry idk wat u saying bro");
                }
            }

         catch (PeanutException error) {
             ui.showError(error.getMessage());
        }



    }
        }


    }


