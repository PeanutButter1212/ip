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

            else {
                System.out.println("added: " + userInput);
                tasks.add(new Task(userInput));
            }
        }


    }
}

