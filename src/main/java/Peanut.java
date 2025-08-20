import java.util.Scanner;

public class Peanut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
            else {
                System.out.println(userInput);
            }
        }


    }
}
