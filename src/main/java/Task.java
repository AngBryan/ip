/**
 * Represents a task in the Bryte task management application.
 * A task has a name and a status indicating whether it has been completed.
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructs a new {@code Task} with the specified name and completion status.
     *
     * @param name The description or name of the task.
     * @param isDone The initial completion status of the task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return The task name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the task.
     *
     * @param name The new name for the task.
     */
    public void setName(String name) {
        this.name = name;
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
        return "[" + getStatusIcon() + "] " + name;
    }
}
