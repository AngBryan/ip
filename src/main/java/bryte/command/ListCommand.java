package bryte.command;

import bryte.Storage;
import bryte.TaskList;
import bryte.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showDivider();
        ui.showMessage(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage(" " + (i + 1) + "." + tasks.get(i).toString());
        }
        ui.showDivider();
    }
}
