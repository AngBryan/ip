/**
 * Represents a task in the Bryte task management application.
 * A task has a description and a status indicating whether it has been completed.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The initial completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description for the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone {@code true} to mark as done, {@code false} to mark as undone.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns an icon representing the completion status of the task.
     *
     * @return "X" if the task is completed, or a blank space " " if it is pending.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
