package bryte;

import java.util.ArrayList;
import java.util.Scanner;

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
    private static final String BANNER = "██████╗ ██████╗ ██╗   ██╗████████╗███████╗\n"
            + "██╔══██╗██╔══██╗╚██╗ ██╔╝╚══██╔══╝██╔════╝\n"
            + "██████╔╝██████╔╝ ╚████╔╝    ██║   █████╗  \n"
            + "██╔══██╗██╔══██╗  ╚██╔╝     ██║   ██╔══╝  \n"
            + "██████╔╝██║  ██║   ██║      ██║   ███████╗\n"
            + "╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝\n";
    private static final String DIVIDER = "____________________________________________________________";
    private static ArrayList<Task> taskList = new ArrayList<>();

    private static final String DEADLINE_DELIMITER = " /by ";
    private static final String EVENT_START_DELIMITER = " /from ";
    private static final String EVENT_END_DELIMITER = " /to ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println(BANNER);
        System.out.println("Hello! I'm BRYTE.");
        System.out.println("What can I do for you?");

        while (isRunning) {
            String command = scanner.nextLine().trim();
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
                    System.out.println(DIVIDER);
                    setMark(true, commandParts);
                    System.out.println(DIVIDER);
                    break;

                case "unmark":
                    System.out.println(DIVIDER);
                    setMark(false, commandParts);
                    System.out.println(DIVIDER);
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

                case "bye":
                    isRunning = false;
                    break;

                default:
                    handleDefaultCommand(command);
                    break;
                }
            } catch (BryteException e) {
                System.out.println(DIVIDER);
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println(DIVIDER);
            }
        }

        scanner.close();
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    private static void handleListCommand() {
        System.out.println(DIVIDER);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + "." + taskList.get(i).toString());
        }
        System.out.println(DIVIDER);
    }

    private static void handleAddTodoCommand(String[] commandParts) throws BryteException {
        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new BryteException("The description of a todo cannot be empty.");
        }
        Todo newTodo = new Todo(commandParts[1].trim());
        addTask(newTodo);
    }

    private static void handleAddDeadlineCommand(String[] commandParts) throws BryteException {
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

    private static void handleAddEventCommand(String[] commandParts) throws BryteException {
        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new BryteException("The description of an event cannot be empty.");
        }
        String[] eventParts = commandParts[1].split(EVENT_START_DELIMITER);
        if (eventParts.length < 2) {
            throw new BryteException("Please use " + EVENT_START_DELIMITER.trim() + " and " + EVENT_END_DELIMITER.trim() + " to specify the event duration.");
        }
        String[] timeParts = eventParts[1].split(EVENT_END_DELIMITER);
        if (timeParts.length < 2) {
            throw new BryteException("Please use " + EVENT_END_DELIMITER.trim() + " to specify the end time of the event.");
        }
        Event newEvent = new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        addTask(newEvent);
    }

    private static void handleDefaultCommand(String command) throws BryteException {
        throw new BryteException("I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Handles the execution of mark and unmark commands on the task list.
     *
     * @param markStatus The status to set: {@code true} for marked as done, {@code false} for undone.
     * @param commandParts The array of string tokens from the user input.
     */
    private static void setMark(boolean markStatus, String[] commandParts) throws BryteException {
        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new BryteException("Please specify a task number.");
        }

        try {
            int index = Integer.parseInt(commandParts[1].trim()) - 1;

            if (index < 0 || index >= taskList.size()) {
                throw new BryteException("Invalid task number.");
            }

            if (markStatus) {
                System.out.println(" Nice! I've marked this task as done:");
            } else {
                System.out.println(" OK, I've marked this task as not done yet:");
            }
            taskList.get(index).setDone(markStatus);
            System.out.println("   " + taskList.get(index).toString());
        } catch (NumberFormatException e) {
            throw new BryteException("Task number must be an integer.");
        }
    }

    /**
     * Adds a task to the task list and prints the confirmation message.
     *
     * @param task The task to be added.
     */
    private static void addTask(Task task) {
        taskList.add(task);
        System.out.println(DIVIDER);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }
}
