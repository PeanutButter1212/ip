package peanut;

import java.util.List;
import java.util.Scanner;


/**
 * The Ui class handles all interactions with the user.
 * It is responsible for displaying messages
 */

public class Ui {
    private Scanner sc;
    /**
     * Creates a new Ui object and initialises scanner to read user input.
     */
    public Ui() {

        sc = new Scanner(System.in);
    }
    /**
     * Prints welcome message when bot starts up.
     */
    public void welcomeMessage() {

        System.out.println("Hello! :D I'm Peanut.\nWhat can I do for you?");
    }
    /**
     * Prints goodbye message when user exits chatbot.
     */
    public void byeMessage() {

        System.out.println("Bye. Hope to see you again soon!");
    }
    /**
     * Reads commands entered by user.
     *
     * @return Command line entered by user.
     */
    public String readCommand() {

        return sc.nextLine();
    }
    /**
     * Shows message confirming task has been added to list.
     *
     * @param task The task user adds to list.
     * @param size Total number of tasks in the list.
     *
     */
    public void addListMessage(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    /**
     * Shows entire TaskList in order.
     *
     * @param tasks The current TaskList.
     */
    public void showListMessage(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTasks().get(i));
        }
    }
    /**
     * Shows message confirming task has been marked.
     *
     * @param task Task that user wants to be marked.
     */
    public void markListMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");

        System.out.println(task);
    }
    /**
     * Shows message confirming task has been unmarked.
     *
     * @param task Task that user wants to be unmarked
     */
    public void unmarkListMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }
    /**
     * Shows message confirming task has been deleted.
     *
     * @param taskList TaskList containing task that user wants to delete.
     */
    public void deleteListMessage(TaskList taskList, int taskNumber) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.getTasks().get(taskNumber));
        System.out.println("Now you have " + (taskList.size() - 1) + " tasks in the list.");
    }
    /**
     * Shows relevant error message
     */
    public void showErrorMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays list of tasks that matches keyword
     *
     * @param matches List of tasks that matches users input
     */
    public void showMatches(List<Task> matches) {
        if (matches.isEmpty()) {
            System.out.println("no results found :(");
        } else {
            System.out.println("Here are the matching tasks:");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println((i + 1) + ". " + matches.get(i));
            }
        }
    }



}
