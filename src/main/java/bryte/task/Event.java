package bryte.task;

import java.time.LocalDateTime;

import bryte.exception.BryteException;
import bryte.util.DateTimeUtil;

/**
 * Represents a task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Constructs a new {@code Event} task with the specified description, start time, and end time.
     *
     * @param description The description or name of the task.
     * @param startTimeString The start time of the event.
     * @param endTimeString The end time of the event.
     * @throws BryteException If the start or end time strings are not in a valid format.
     */
    public Event(String description, String startTimeString, String endTimeString) throws BryteException {
        super(description, false);
        this.startTime = DateTimeUtil.parse(startTimeString);
        this.endTime = DateTimeUtil.parse(endTimeString);
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeUtil.formatForDisplay(startTime)
                + " to: " + DateTimeUtil.formatForDisplay(endTime) + ")";
    }

    /**
     * Returns the string representation of the Event task for file storage.
     *
     * @return The formatted string for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + DateTimeUtil.formatForStorage(startTime)
                + " | " + DateTimeUtil.formatForStorage(endTime);
    }
}
