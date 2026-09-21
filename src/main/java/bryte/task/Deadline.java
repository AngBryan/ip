package bryte.task;

/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected String dueDate;

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     *
     * @param description The description or name of the task.
     * @param dueDate The date/time the task needs to be done by.
     */
    public Deadline(String description, String dueDate) {
        super(description, false);
        this.dueDate = dueDate;
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }

    /**
     * Returns the string representation of the Deadline task for file storage.
     *
     * @return The formatted string for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + dueDate;
    }
}
