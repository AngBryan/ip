package bryte.command;

import bryte.Storage;
import bryte.TaskList;
import bryte.Ui;
import bryte.exception.BryteException;
import bryte.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BryteException {
        if (index < 0 || index >= tasks.size()) {
            throw new BryteException("Invalid task number.");
        }

        Task removedTask = tasks.remove(index);
        storage.save(tasks.getTasks());
        ui.showDivider();
        ui.showMessage(" Noted. I've removed this task:");
        ui.showMessage("   " + removedTask.toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showDivider();
    }
}
