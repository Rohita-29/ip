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
        String banner = " _   _ _           _               \n"
                + "| \\ | (_)_ __ ___ | |__  _   _ ___ \n"
                + "|  \\| | | '_ ` _ \\| '_ \\| | | / __|\n"
                + "| |\\  | | | | | | | |_) | |_| \\__ \\\n"
                + "|_| \\_|_|_| |_| |_|_.__/ \\__,_|___/\n";
        System.out.println(banner);
        String name = "Nimbus";
        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");

        Task[] tasks = new Task[100];
        int taskCount = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
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
                String description = input.substring(5);
                tasks[taskCount] = new Todo(description);
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
        }
        System.out.println("Bye! Hope to see you again!");
        scanner.close();
    }

    /**
     * Prints the standard "task added" confirmation message.
     *
     * @param task      Task that was just added.
     * @param taskCount Total number of tasks currently stored.
     */
    private static void printAddedMessage(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}




