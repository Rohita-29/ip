/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a deadline task with the given description and due date/time.
     *
     * @param description Description of the task.
     * @param by Date/time the task is due by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns "D", the type icon for a deadline task.
     */
    @Override
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Returns a string representation of this deadline, including its due date/time.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}