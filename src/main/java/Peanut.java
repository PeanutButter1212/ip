import java.util.Scanner;

public class Peanut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] userList = new String[100];
        int x = 0;
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
            if(userInput.equals("list")) {
                for (int i = 0 ; i < x  ; i ++) {
                    System.out.println( (i+1) + ": " + userList[i]);
                }
            }
            else {

                System.out.println("added: " + userInput);
                userList[x] = userInput;
                x++;
            }
        }


    }
}
