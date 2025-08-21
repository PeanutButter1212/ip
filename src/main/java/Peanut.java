import java.util.Scanner;



public class Peanut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        System.out.println("Hello! I'm Peanut\n" +
                "What can I do for you?" );

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;

            }
            else if(userInput.equals("list")) {
                taskList.list();
            }

            else if (userInput.startsWith("mark")) {
                String[] parts = userInput.split(" ", 2);
                int taskNumber = Integer.parseInt(parts[1]) -1;
                taskList.mark(taskNumber);


            }
            else if (userInput.startsWith("unmark")) {
                String[] parts = userInput.split(" ", 2);
                int taskNumber = Integer.parseInt(parts[1]) -1;
                taskList.unmark(taskNumber);
            }
            else if (userInput.startsWith("todo")) {
                String[] parts= userInput.split("\\s+", 2);
                Task todoTask = new ToDo(parts[1]);
                System.out.println("Got it. I've added this task:");
                taskList.add(todoTask);
            }

            else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.split("\\s+", 2);
                String[] sep = parts[1].split("/by", 2);
                String task = sep[0];
                String deadline = sep[1];
                Task deadlineTask = new Deadline(task, deadline );
                System.out.println("Got it. I've added this task:");
                taskList.add(deadlineTask);
            }

            else if (userInput.startsWith("event")) {
                String[] parts = userInput.split("\\s+", 2);
                String[] sep = parts[1].split("/from", 2);
                String[] sep2 = sep[1].split("/to",2);
                String task = sep[0];
                String deadline = sep2[1];
                String startDate = sep2[0];
                Task eventTask = new Event(task, deadline,startDate );
                System.out.println("Got it. I've added this task:");
                taskList.add(eventTask);

            }

            else {
                Task genericTask = new Task(userInput);
                System.out.println("added: " + userInput);
                taskList.add(genericTask);
            }
        }


    }
}

