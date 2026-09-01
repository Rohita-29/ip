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
    private static final int MAX_TASKS=100;
    public static void main(String[] args) {
        printWelcome();

        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            taskCount = handleCommand(input, tasks, taskCount);
        }
        System.out.println("Bye! Hope to see you again!");
        scanner.close();
    }
    /**
     * Prints the banner and greeting shown when Nimbus starts.
     */
    private static void printWelcome() {
        String banner = " _   _ _           _               \n"
                + "| \\ | (_)_ __ ___ | |__  _   _ ___ \n"
                + "|  \\| | | '_ ` _ \\| '_ \\| | | / __|\n"
                + "| |\\  | | | | | | | |_) | |_| \\__ \\\n"
                + "|_| \\_|_|_| |_| |_|_.__/ \\__,_|___/\n";
        System.out.println(banner);
        System.out.println("Hello! I'm Nimbus.");
        System.out.println("What can I do for you?");
    }

    /**
     * Interprets a single line of user input and carries out the matching command.
     *
     * @param input Line of input entered by the user.
     * @param tasks Array of tasks stored so far.
     * @param taskCount Number of tasks currently stored.
     * @return Updated task count after handling the command.
     */
    private static int handleCommand(String input, Task[] tasks, int taskCount) {
        if (input.equals("list")) {
            printTaskList(tasks, taskCount);
        } else if (input.startsWith("mark ")) {
            markTask(tasks, input.substring(5), true);
        } else if (input.startsWith("unmark ")) {
            markTask(tasks, input.substring(7), false);
        } else if (input.startsWith("todo ")) {
            taskCount = addTask(tasks, taskCount, new Todo(input.substring(5)));
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ", 2);
            taskCount = addTask(tasks, taskCount, new Deadline(parts[0], parts[1]));
        } else if (input.startsWith("event ")) {
            String[] fromSplit = input.substring(6).split(" /from ", 2);
            String[] toSplit = fromSplit[1].split(" /to ", 2);
            taskCount = addTask(tasks, taskCount, new Event(fromSplit[0], toSplit[0], toSplit[1]));
        } else {
            System.out.println("Sorry, I don't recognise that command.");
        }
        return taskCount;
    }

    /**
     * Prints every stored task, numbered from 1.
     *
     * @param tasks Array of tasks stored so far.
     * @param taskCount Number of tasks currently stored.
     */
    private static void printTaskList(Task[] tasks, int taskCount) {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    /**
     * Marks or unmarks the task at the given 1-based index and prints a confirmation.
     *
     * @param tasks Array of tasks stored so far.
     * @param indexText 1-based task number, as typed by the user.
     * @param isDone True to mark the task as done, false to mark it as not done.
     */
    private static void markTask(Task[] tasks, String indexText, boolean isDone) {
        int index = Integer.parseInt(indexText) - 1;
        if (isDone) {
            tasks[index].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            tasks[index].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + tasks[index]);
    }

    /**
     * Stores a new task and prints the standard "task added" confirmation message.
     *
     * @param tasks Array of tasks stored so far.
     * @param taskCount Number of tasks currently stored.
     * @param task Task to add.
     * @return Updated task count.
     */
    private static int addTask(Task[] tasks, int taskCount, Task task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        return taskCount;
    }
}








