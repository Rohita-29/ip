package nimbus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Saves and loads Nimbus tasks using a local text file.
 */

public class Storage {

    private static final String FILE_PATH = "./data/nimbus.txt";

    /**
     * Saves all supplied tasks, replacing the existing data file.
     * Creates the parent directory if it does not exist.
     *
     * @param tasks Tasks to save.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            File parent = file.getParentFile();

            if (parent != null) {
                parent.mkdirs();
            }

            FileWriter writer = new FileWriter(file);

            for (Task task : tasks) {
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

    /**
     * Loads tasks and their completion status from the data file.
     * Returns an empty list if the file does not exist.
     *
     * @return Tasks loaded from the data file.
     */

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks;
        }

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
                    tasks.add(todo);

                } else if (parts[0].equals("D")) {
                    Deadline deadline = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);

                } else if (parts[0].equals("E")) {
                    Event event = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                }
            }

            scanner.close();

        } catch (IOException e) {
            System.out.println("OOPS!!! I couldn't load your tasks.");
        }

        return tasks;
    }
}