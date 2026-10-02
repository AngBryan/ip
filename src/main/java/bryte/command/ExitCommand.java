package bryte.command;

import bryte.Storage;
import bryte.TaskList;
import bryte.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // No execution needed for exit
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
