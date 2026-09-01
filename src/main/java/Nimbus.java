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
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + tasks[i]);
            }
        } else if (input.startsWith("mark ")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            tasks[index].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[index]);
        } else if (input.startsWith("unmark ")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            tasks[index].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[index]);
        } else if (input.startsWith("todo ")) {
            tasks[taskCount] = new Todo(input.substring(5));
            taskCount++;
            printAddedMessage(tasks[taskCount - 1], taskCount);
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ", 2);
            tasks[taskCount] = new Deadline(parts[0], parts[1]);
            taskCount++;
            printAddedMessage(tasks[taskCount - 1], taskCount);
        } else if (input.startsWith("event ")) {
            String[] fromSplit = input.substring(6).split(" /from ", 2);
            String[] toSplit = fromSplit[1].split(" /to ", 2);
            tasks[taskCount] = new Event(fromSplit[0], toSplit[0], toSplit[1]);
            taskCount++;
            printAddedMessage(tasks[taskCount - 1], taskCount);
        } else {
            System.out.println("Sorry, I don't recognise that command.");
        }
        return taskCount;
    }

    /**
     * Prints the standard "task added" confirmation message.
     *
     * @param task Task that was just added.
     * @param taskCount Total number of tasks currently stored.
     */
    private static void printAddedMessage(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}








