/**
 * Represents a task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs a new {@code Event} task with the specified description, start time, and end time.
     *
     * @param description The description or name of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description, false);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

