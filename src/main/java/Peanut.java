import java.nio.file.Paths;
import java.util.Scanner;




public class Peanut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String filePath = "./data/peanut.txt";
        Storage storage = new Storage(Paths.get("data", "peanut.txt").toString());
        TaskList taskList = new TaskList(storage.load());




        System.out.println("Hello! I'm Peanut\n" +
                "What can I do for you?" );


        while (true) {
            String userInput = sc.nextLine();
            try {
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                } else if (userInput.equals("list")) {
                    taskList.list();

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

                } else if (userInput.startsWith("todo")) {
                    String[] parts = userInput.split("\\s+", 2);

                    if (parts.length < 2 || parts[1].isBlank()) {
                        throw new PeanutException("Please add description for todo!! (e.g todo read book)");
                    }

                    Task todoTask = new ToDo(parts[1]);
                    taskList.add(todoTask);
                    storage.save(taskList);

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
                    String deadline = sep[1];
                    Task deadlineTask = new Deadline(task, deadline);
                    taskList.add(deadlineTask);
                    storage.save(taskList);

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
                    String deadline = sep2[1];
                    String startDate = sep2[0];
                    Task eventTask = new Event(task, deadline, startDate);
                    taskList.add(eventTask);
                    storage.save(taskList);

                }
                else if (userInput.startsWith("delete")){
                    String[] parts = userInput.split("\\s+", 2);
                    if (parts.length < 2 || parts[1].isBlank() || Integer.parseInt(parts[1]) > taskList.size()) {
                        throw new PeanutException("Please enter a valid number!!");
                    }
                    taskList.delete(Integer.parseInt(parts[1])-1);
                    storage.save(taskList);
                } else {
                  throw new PeanutException("Sorry idk wat u saying bro");
                }
            }

         catch (PeanutException error) {
             System.out.println(error.getMessage());
        }



    }
        }


    }


