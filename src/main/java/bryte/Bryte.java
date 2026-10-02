package bryte;

import java.util.ArrayList;

import bryte.exception.BryteException;
import bryte.task.Deadline;
import bryte.task.Event;
import bryte.task.Task;
import bryte.task.Todo;

/**
 * Main entry point for the Bryte task management application.
 * Handles user interaction, task list management, and command parsing.
 */
public class Bryte {
    private static final String DATA_DIR = "data";
    private static final String FILE_NAME = "bryte.txt";
    private static final String DEADLINE_DELIMITER = " /by ";
    private static final String EVENT_START_DELIMITER = " /from ";
    private static final String EVENT_END_DELIMITER = " /to ";

    private ArrayList<Task> taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a new Bryte instance.
     *
     * @param dirPath The directory path for data storage.
     * @param filePath The file name for data storage.
     */
    public Bryte(String dirPath, String filePath) {
        ui = new Ui();
        storage = new Storage(dirPath, filePath);
        try {
            taskList = storage.load();
        } catch (BryteException e) {
            ui.showError(e.getMessage());
            taskList = new ArrayList<>();
        }
    }

    /**
     * Main method to start the Bryte application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Bryte(DATA_DIR, FILE_NAME).run();
    }

    /**
     * Runs the main loop of the Bryte application.
     */
    public void run() {
        boolean isRunning = true;

        ui.showWelcome();

        while (isRunning) {
            String command = ui.readCommand();
            if (command.isEmpty()) {
                continue;
            }
            String[] commandParts = command.split(" ", 2);
            String keyword = commandParts[0].toLowerCase();

            try {
                switch (keyword) {
                    case "list":
                        handleListCommand();
                        break;

                    case "mark":
                        ui.showDivider();
                        setMark(true, commandParts);
                        ui.showDivider();
                        break;

                    case "unmark":
                        ui.showDivider();
                        setMark(false, commandParts);
                        ui.showDivider();
                        break;

                    case "todo":
                        handleAddTodoCommand(commandParts);
                        break;

                    case "deadline":
                        handleAddDeadlineCommand(commandParts);
                        break;

                    case "event":
                        handleAddEventCommand(commandParts);
                        break;

                    case "delete":
                        handleDeleteCommand(commandParts);
                        break;

                    case "bye":
                        isRunning = false;
                        break;

                    default:
                        handleDefaultCommand(command);
                        break;
                }
            } catch (BryteException e) {
                ui.showDivider();
                ui.showError(e.getMessage());
                ui.showDivider();
            }
        }

        ui.showDivider();
        ui.showGoodbye();
        ui.showDivider();
    }

    /**
     * Handles the execution of the list command.
     * Prints all the tasks currently in the task list.
     */
    private void handleListCommand() {
        ui.showDivider();
        ui.showMessage(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            ui.showMessage(" " + (i + 1) + "." + taskList.get(i).toString());
        }
        ui.showDivider();
    }

    /**
     * Handles the execution of the todo command.
     * Creates a new Todo task and adds it to the list.
     *
     * @param commandParts The array of string tokens from the user input.
     * @throws BryteException If the description of the todo is empty.
     */
    private void handleAddTodoCommand(String[] commandParts) throws BryteException {
        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new BryteException("The description of a todo cannot be empty.");
        }
        Todo newTodo = new Todo(commandParts[1].trim());
        addTask(newTodo);
    }

    /**
     * Handles the execution of the deadline command.
     * Creates a new Deadline task and adds it to the list.
     *
     * @param commandParts The array of string tokens from the user input.
     * @throws BryteException If the description or deadline time is missing or incorrectly formatted.
     */
    private void handleAddDeadlineCommand(String[] commandParts) throws BryteException {
        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new BryteException("The description of a deadline cannot be empty.");
        }
        String[] deadlineParts = commandParts[1].split(DEADLINE_DELIMITER);
        if (deadlineParts.length < 2) {
            throw new BryteException("Please use " + DEADLINE_DELIMITER.trim() + " to specify the deadline.");
        }
        Deadline newDeadline = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
        addTask(newDeadline);
    }

    /**
     * Handles the execution of the event command.
     * Creates a new Event task and adds it to the list.
     *
     * @param commandParts The array of string tokens from the user input.
     * @throws BryteException If the description, start time, or end time is missing or incorrectly formatted.
     */
    private void handleAddEventCommand(String[] commandParts) throws BryteException {
        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new BryteException("The description of an event cannot be empty.");
        }
        String[] eventParts = commandParts[1].split(EVENT_START_DELIMITER);
        if (eventParts.length < 2) {
            throw new BryteException("Please use " + EVENT_START_DELIMITER.trim()
                    + " and " + EVENT_END_DELIMITER.trim() + " to specify the event duration.");
        }
        String[] timeParts = eventParts[1].split(EVENT_END_DELIMITER);
        if (timeParts.length < 2) {
            throw new BryteException("Please use " + EVENT_END_DELIMITER.trim()
                    + " to specify the end time of the event.");
        }
        Event newEvent = new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        addTask(newEvent);
    }

    /**
     * Handles unknown commands.
     * Throws a BryteException indicating the command is not understood.
     *
     * @param command The full user input string.
     * @throws BryteException Always thrown for default commands.
     */
    private void handleDefaultCommand(String command) throws BryteException {
        throw new BryteException("I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Handles the deletion of a task from the list.
     *
     * @param commandParts The array of string tokens from the user input.
     * @throws BryteException If the task number is invalid or missing.
     */
    private void handleDeleteCommand(String[] commandParts) throws BryteException {
        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new BryteException("Please specify a task number to delete.");
        }

        try {
            int index = Integer.parseInt(commandParts[1].trim()) - 1;

            if (index < 0 || index >= taskList.size()) {
                throw new BryteException("Invalid task number.");
            }

            Task removedTask = taskList.remove(index);
            try {
                storage.save(taskList);
            } catch (BryteException e) {
                ui.showError(e.getMessage());
            }
            ui.showDivider();
            ui.showMessage(" Noted. I've removed this task:");
            ui.showMessage("   " + removedTask.toString());
            ui.showMessage(" Now you have " + taskList.size() + " tasks in the list.");
            ui.showDivider();
        } catch (NumberFormatException e) {
            throw new BryteException("Task number must be an integer.");
        }
    }

    /**
     * Handles the execution of mark and unmark commands on the task list.
     *
     * @param markStatus The status to set: {@code true} for marked as done, {@code false} for undone.
     * @param commandParts The array of string tokens from the user input.
     */
    private void setMark(boolean markStatus, String[] commandParts) throws BryteException {
        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new BryteException("Please specify a task number.");
        }

        try {
            int index = Integer.parseInt(commandParts[1].trim()) - 1;

            if (index < 0 || index >= taskList.size()) {
                throw new BryteException("Invalid task number.");
            }

            if (markStatus) {
                ui.showMessage(" Nice! I've marked this task as done:");
            } else {
                ui.showMessage(" OK, I've marked this task as not done yet:");
            }
            taskList.get(index).setDone(markStatus);
            try {
                storage.save(taskList);
            } catch (BryteException e) {
                ui.showError(e.getMessage());
            }
            ui.showMessage("   " + taskList.get(index).toString());
        } catch (NumberFormatException e) {
            throw new BryteException("Task number must be an integer.");
        }
    }

    /**
     * Adds a task to the task list and prints the confirmation message.
     *
     * @param task The task to be added.
     */
    private void addTask(Task task) {
        taskList.add(task);
        try {
            storage.save(taskList);
        } catch (BryteException e) {
            ui.showError(e.getMessage());
        }
        ui.showDivider();
        ui.showMessage(" Got it. I've added this task:");
        ui.showMessage("   " + task.toString());
        ui.showMessage(" Now you have " + taskList.size() + " tasks in the list.");
        ui.showDivider();
    }
}
