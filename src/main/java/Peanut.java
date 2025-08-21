import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Peanut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/
        //System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Peanut\n" +
                "What can I do for you?" );

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;

            }
            else if(userInput.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0 ; i < tasks.size()  ; i ++) {
                    System.out.println((i+1) + ". " + tasks.get(i).toString());
                }
            }

            else if (userInput.startsWith("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                String[] parts = userInput.split(" ", 2);
                int taskNumber = Integer.parseInt(parts[1]) -1;
                tasks.get(taskNumber).mark();
                System.out.println(tasks.get(taskNumber).toString());


            }
            else if (userInput.startsWith("unmark")) {
                System.out.println( "OK, I've marked this task as not done yet:");
                String[] parts = userInput.split(" ", 2);
                int taskNumber = Integer.parseInt(parts[1]) -1;
                tasks.get(taskNumber).unmark();
                System.out.println(tasks.get(taskNumber).toString());


            }
            else if (userInput.startsWith("todo")) {
                System.out.println("Got it. I've added this task:");
                String[] parts= userInput.split("\\s+", 2);
                String task = parts[1];
                Task todoTask = new ToDo(task);
                System.out.println(todoTask);
                System.out.println("Now you have " + (tasks.size() + 1) + " tasks in the list");
                tasks.add(todoTask);
            }

            else if (userInput.startsWith("deadline")) {
                System.out.println("Got it. I've added this task:");
                String[] parts = userInput.split("\\s+", 2);
                String[] sep = parts[1].split("/by", 2);
                String task = sep[0];
                String deadline = sep[1];
                Task deadlineTask = new Deadline(task, deadline );
                System.out.println(deadlineTask);
                System.out.println("Now you have " + (tasks.size() + 1) + " tasks in the list");
                tasks.add(deadlineTask);
            }

            else if (userInput.startsWith("event")) {
                System.out.println("Got it. I've added this task:");
                String[] parts = userInput.split("\\s+", 2);
                String[] sep = parts[1].split("/from", 2);
                String[] sep2 = sep[1].split("/to",2);
                String task = sep[0];
                String deadline = sep2[1];
                String startDate = sep2[0];
                Task eventTask = new Event(task, deadline,startDate );
                System.out.println(eventTask);
                System.out.println("Now you have " + (tasks.size() + 1) + " tasks in the list");
                tasks.add(eventTask);
            }

            else {
                System.out.println("added: " + userInput);
                tasks.add(new Task(userInput));
            }
        }


    }
}

