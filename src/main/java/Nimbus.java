import java.util.Scanner;

public class Nimbus {
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

        String[] tasks = new String[100];
        boolean[] isDone = new boolean[100];
        int taskCount =0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    String icon = isDone[i] ? "X" : " ";
                    System.out.println((i + 1) + ".[" + icon + "]" + tasks[i]);
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                isDone[index] = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [X] " + tasks[index]);
            } else if(input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                isDone[index] = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [ ] " + tasks[index]);
            }else{
                    tasks[taskCount]=input;
                    taskCount++;
                    System.out.println("added: "+ input);
                }
            }
        System.out.println("Bye! Hope to see you again!");
        scanner.close();
    }
}
