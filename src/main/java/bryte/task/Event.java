package bryte.task;

/**
 * Represents a task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {

    protected String startTime;
    protected String endTime;

    /**
     * Constructs a new {@code Event} task with the specified description, start time, and end time.
     *
     * @param description The description or name of the task.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description, false);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}

