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
    private final Scanner sc;
    /**
     * Creates a new Ui object and initialises scanner to read user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }
    /**
     * Returns the greeting shown when the app starts.
     *
     * @return The welcome message
     */
    public String welcomeMessage() {

        return "Hello! :D I'm Peanut.\nWhat can I do for you?";
    }
    /**
     * Returns the goodbye shown when the user exits the app.
     *
     * @return The goodbye message
     */
    public String byeMessage() {

        return "Bye. Hope to see you again soon!";
    }
    /**
     * Reads a single line of input
     *
     * @return The raw line entered by the user
     * */
    public String readCommand() {
        return sc.nextLine();
    }
    /**
     * Builds a message confirming that a task has been added.
     *
     * @param task Task that was added
     * @param size Total number of tasks after adding the task
     * @return A confirmation message
     */
    public String addListMessage(Task task, int size) {
        StringBuilder message = new StringBuilder();
        message.append("Got it. I've added this task:\n");
        message.append(task).append("\n");
        message.append("Now you have ").append(size).append(size == 1 ? " task" : " tasks").append(" in the list.");
        return message.toString();
    }
    /**
     * Builds a message listing all tasks in order.
     *
     * @param tasks Current TaskList
     * @return A formatted list of tasks, or a notice if the list is empty
     */
    public String showListMessage(TaskList tasks) {
        if (tasks.size() == 0) {
            return "Your list is empty.";
        }
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            message.append(i + 1).append(". ").append(tasks.getTasks().get(i)).append("\n");
        }
        return message.toString().trim();
    }
    /**
     * Builds a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked done
     * @return A confirmation message
     */
    public String markListMessage(Task task) {
        StringBuilder message = new StringBuilder();
        message.append("Nice! I've marked this task as done:\n");
        message.append(task);
        return message.toString();
    }
    /**
     * Builds a message confirming that a task has been unmarked
     *
     * @param task The task that was unmarked
     * @return A confirmation message
     */
    public String unmarkListMessage(Task task) {
        StringBuilder message = new StringBuilder();
        message.append("OK, I've marked this task as not done yet:\n");
        message.append(task);
        return message.toString();
    }
    /**
     * Builds a message confirming that a task has been deleted.
     *
     * @param removedTask Task that was removed
     * @param remainingCount Number of tasks remaining after deletion
     * @return A formatted confirmation message
     */
    public String deleteListMessage(Task removedTask, int remainingCount) {
        StringBuilder message = new StringBuilder();
        message.append("Noted. I've removed this task:\n");
        message.append(removedTask).append("\n");
        message.append("Now you have ")
                .append(remainingCount)
                .append(remainingCount == 1 ? " task" : " tasks")
                .append(" in the list.");
        return message.toString();
    }
    /**
     * Returns a formatted error message to be rendered by the caller.
     *
     * @param msg Error text
     * @return Error text
     */
    public String showErrorMessage(String msg) {
        return msg;
    }

    /**
     * Builds a message listing tasks that match a search query.
     *
     * @param matches Tasks that matched the query
     * @return A formatted list of matches, or a notice if no matches were found
     */
    public String showMatches(List<Task> matches) {
        if (matches == null || matches.isEmpty()) {
            return "No results found :(";
        }
        StringBuilder message = new StringBuilder("Here are the matching tasks:\n");
        for (int i = 0; i < matches.size(); i++) {
            message.append(i + 1).append(". ").append(matches.get(i)).append("\n");
        }
        return message.toString().trim();
    }

    /**
     * Builds a message confirming that the task list has been archived and cleared.
     *
     * @return Archive confirmation message
     */
    public String showArchiveMessage() {
        return "TaskList successfully archived, TaskList has been cleared!!";
    }



}
