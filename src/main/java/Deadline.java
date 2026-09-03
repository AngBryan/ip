/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     *
     * @param description The description or name of the task.
     * @param by The date/time the task needs to be done by.
     */
    public Deadline(String description, String by) {
        super(description, false);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
