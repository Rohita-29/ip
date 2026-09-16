package nimbus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static final String FILE_PATH = "./data/nimbus.txt";

    public static void saveTasks(Task[] tasks, int taskCount) {
        try {
            File file = new File(FILE_PATH);
            File parent = file.getParentFile();

            if (parent != null) {
                parent.mkdirs();
            }

            FileWriter writer = new FileWriter(file);

            for (int i = 0; i < taskCount; i++) {
                Task task = tasks[i];

                if (task instanceof Todo) {
                    writer.write("T | " + (task.isDone ? "1" : "0")
                            + " | " + task.description);

                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    writer.write("D | " + (task.isDone ? "1" : "0")
                            + " | " + task.description
                            + " | " + deadline.by);

                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    writer.write("E | " + (task.isDone ? "1" : "0")
                            + " | " + task.description
                            + " | " + event.from
                            + " | " + event.to);
                }

                writer.write(System.lineSeparator());
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("OOPS!!! I couldn't save your tasks.");
        }

    }
    public static int loadTasks(Task[] tasks) {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return 0;
        }

        int taskCount = 0;

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");

                if (parts[0].equals("T")) {
                    Todo todo = new Todo(parts[2]);
                    if (parts[1].equals("1")) {
                        todo.markAsDone();
                    }
                    tasks[taskCount] = todo;
                    taskCount++;

                } else if (parts[0].equals("D")) {
                    Deadline deadline = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        deadline.markAsDone();
                    }
                    tasks[taskCount] = deadline;
                    taskCount++;

                } else if (parts[0].equals("E")) {
                    Event event = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) {
                        event.markAsDone();
                    }
                    tasks[taskCount] = event;
                    taskCount++;
                }
            }

            scanner.close();

        } catch (IOException e) {
            System.out.println("OOPS!!! I couldn't load your tasks.");
        }

        return taskCount;
    }
}