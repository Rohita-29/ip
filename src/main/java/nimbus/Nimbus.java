package nimbus;

import java.util.Scanner;

/**
 * Represents a simple command-line task-tracking chatbot names Nimbus.
 */

public class Nimbus {
    /**
     * Runs Nimbus chatbot, reading commands from standard input until user types "bye".
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();

        TaskList tasks = new TaskList(Storage.loadTasks());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            handleCommand(input, tasks);
        }
        ui.showGoodbye();
        scanner.close();
    }


    /**
     * Interprets a single line of user input and carries out the matching command.
     *
     * @param input Line of input entered by the user.
     * @param tasks List of tasks stored so far.
     */
    private static void handleCommand(String input, TaskList tasks) {
        if (input.equals("list")) {
            printTaskList(tasks);
        } else if (input.startsWith("mark ")) {
            markTask(tasks, input.substring(5), true);
        } else if (input.startsWith("unmark ")) {
            markTask(tasks, input.substring(7), false);
        } else if (input.startsWith("delete ")) {
            deleteTask(tasks, input.substring(7));
        }else if (input.equals("todo") || input.startsWith("todo ")) {
            String description = input.length() > 4 ? input.substring(5).trim() : "";
            if (description.isEmpty()) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
            } else {
                addTask(tasks, new Todo(description));
            }
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ", 2);
            if (parts.length < 2) {
                System.out.println("OOPS!!! Please use the format: deadline <description> /by <date>");
            } else {
                addTask(tasks, new Deadline(parts[0], parts[1]));
            }
        } else if (input.startsWith("event ")) {
            String[] fromSplit = input.substring(6).split(" /from ", 2);
            if (fromSplit.length < 2) {
                System.out.println("OOPS!!! Please use the format: event <description> /from <start> /to <end>");
            } else {
                String[] toSplit = fromSplit[1].split(" /to ", 2);
                if (toSplit.length < 2) {
                    System.out.println("OOPS!!! Please use the format: event <description> /from <start> /to <end>");
                } else {
                    addTask(tasks, new Event(fromSplit[0], toSplit[0], toSplit[1]));
                }
            }
        } else {
            System.out.println("Sorry, I don't recognise that command.");
        }
    }

    /**
     * Prints every stored task, numbered from 1.
     *
     *  @param tasks List of tasks stored so far.
     */
    private static void printTaskList(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Marks or unmarks the task at the given 1-based index and prints a confirmation.
     *
     * @param tasks List of tasks stored so far.
     * @param indexText 1-based task number, as typed by the user.
     * @param isDone True to mark the task as done, false to mark it as not done.
     */
    private static void markTask(TaskList tasks, String indexText, boolean isDone) {
        int index;
        try {
            index = Integer.parseInt(indexText) - 1;
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please give me a valid task number.");
            return;
        }

        if (index < 0 || index >= tasks.size()) {
            System.out.println("OOPS!!! That task number doesn't exist.");
            return;
        }
        if (isDone) {
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            tasks.get(index).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + tasks.get(index));

        tasks.save();

    }

    /**
     * Stores a new task and prints the standard "task added" confirmation message.
     *
     * @param tasks List of tasks stored so far.
     * @param task Task to add.
     */
    private static void addTask(TaskList tasks, Task task) {
        tasks.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);

        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        tasks.save();
    }

    /**
     * Deletes the task at the given 1-based index and prints a confirmation.
     *
     * @param tasks List of tasks stored so far.
     * @param indexText 1-based task number, as typed by the user.
     */
    private static void deleteTask(TaskList tasks, String indexText) {
        int index;

        try {
            index = Integer.parseInt(indexText) - 1;
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please give me a valid task number.");
            return;
        }

        if (index < 0 || index >= tasks.size()) {
            System.out.println("OOPS!!! That task number doesn't exist.");
            return;
        }

        Task removedTask = tasks.remove(index);

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        tasks.save();
    }

}










