/**
 * Represents a task that starts and ends at specific dates/times.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates an event task with the given description, start, and end date/time.
     *
     * @param description Description of the task.
     * @param from Date/time the event starts.
     * @param to Date/time the event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns "E", the type icon for an event task.
     */
    @Override
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Returns a string representation of this event, including its start and end date/time.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}