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
        int taskCount =0;

        Scanner scanner = new Scanner(System.in);
        while (true){
            String input= scanner.nextLine();
            if(input.equals("bye")){
                break;
            }else if (input.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
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
