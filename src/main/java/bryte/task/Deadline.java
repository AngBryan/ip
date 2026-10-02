package bryte.task;

import java.time.LocalDateTime;

import bryte.exception.BryteException;
import bryte.util.DateTimeUtil;

/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected LocalDateTime dueDate;

    /**
     * Constructs a new {@code Deadline} task with the specified description and deadline.
     *
     * @param description The description or name of the task.
     * @param dueDateString The date/time the task needs to be done by.
     * @throws BryteException If the due date string is not in a valid format.
     */
    public Deadline(String description, String dueDateString) throws BryteException {
        super(description, false);
        this.dueDate = DateTimeUtil.parse(dueDateString);
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeUtil.formatForDisplay(dueDate) + ")";
    }

    /**
     * Returns the string representation of the Deadline task for file storage.
     *
     * @return The formatted string for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + DateTimeUtil.formatForStorage(dueDate);
    }
}
