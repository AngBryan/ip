package bryte.command;

import bryte.Storage;
import bryte.TaskList;
import bryte.Ui;
import bryte.exception.BryteException;
import bryte.task.Task;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand.
     *
     * @param task The task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BryteException {
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showDivider();
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + task.toString());
        ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
        ui.showDivider();
    }
}
