package peanut.ui;

import java.util.List;
import java.util.Scanner;

import peanut.tasks.Task;
import peanut.tasks.TaskList;



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
     * @return  welcome message when bot starts up.
     */
    public String welcomeMessage() {

        return "Hello! :D I'm Peanut.\nWhat can I do for you?";
    }
    /**
     * Prints goodbye message when user exits chatbot.
     */
    public String byeMessage() {

        return "Bye. Hope to see you again soon!";
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
    public String addListMessage(Task task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(task).append("\n");
        sb.append("Now you have ").append(size).append(size == 1 ? " task" : " tasks").append(" in the list.");
        return sb.toString();
    }
    /**
     * Shows entire TaskList in order.
     *
     * @param tasks The current TaskList.
     */
    public String showListMessage(TaskList tasks) {
        if (tasks.size() == 0) {
            return "Your list is empty.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.getTasks().get(i)).append("\n");
        }
        return sb.toString().trim();
    }
    /**
     * Shows message confirming task has been marked.
     *
     * @param task Task that user wants to be marked.
     */
    public String markListMessage(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(task);
        return sb.toString();
    }
    /**
     * Shows message confirming task has been unmarked.
     *
     * @param task Task that user wants to be unmarked
     */
    public String unmarkListMessage(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("OK, I've marked this task as not done yet:\n");
        sb.append(task);
        return sb.toString();
    }
    /**
     * Shows message confirming task has been deleted.
     *
     * @param removedTask Task that user wants to delete.
     * @param remainingCount Number of tasks left after deletion
     */
    public String deleteListMessage(Task removedTask, int remainingCount) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(removedTask).append("\n");
        sb.append("Now you have ")
                .append(remainingCount)
                .append(remainingCount == 1 ? " task" : " tasks")
                .append(" in the list.");
        return sb.toString();
    }
    /**
     * Shows relevant error message
     */
    public String showErrorMessage(String msg) {
        return msg;
    }

    /**
     * Displays list of tasks that matches keyword
     *
     * @param matches List of tasks that matches users input
     */
    public String showMatches(List<Task> matches) {
        if (matches == null || matches.isEmpty()) {
            return "No results found :(";
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks:\n");
        for (int i = 0; i < matches.size(); i++) {
            sb.append(i + 1).append(". ").append(matches.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    public String showArchiveMessage() {
        return "TaskList successfully archived, TaskList has been cleared!!";
    }



}
