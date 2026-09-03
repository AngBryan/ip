import java.util.Scanner;

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
    private static final int MAX_TASKS = 100;
    private static Task[] taskList = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println(BANNER);
        System.out.println("Hello! I'm BRYTE.");
        System.out.println("What can I do for you?");

        while (isRunning) {
            String command = scanner.nextLine();
            String[] commandParts = command.split(" ", 2);
            String keyword = commandParts[0].toLowerCase();

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
        }

        scanner.close();
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    private static void handleListCommand() {
        System.out.println(DIVIDER);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + taskList[i].toString());
        }
        System.out.println(DIVIDER);
    }

    private static void handleAddTodoCommand(String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println(DIVIDER);
            System.out.println(" Please provide a description for the todo.");
            System.out.println(DIVIDER);
            return;
        }
        Todo newTodo = new Todo(commandParts[1]);
        addTask(newTodo);
    }

    private static void handleAddDeadlineCommand(String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println(DIVIDER);
            System.out.println(" Please provide a description and deadline.");
            System.out.println(DIVIDER);
            return;
        }
        String[] deadlineParts = commandParts[1].split(" /by ");
        if (deadlineParts.length < 2) {
            System.out.println(DIVIDER);
            System.out.println(" Please use /by to specify the deadline.");
            System.out.println(DIVIDER);
            return;
        }
        Deadline newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
        addTask(newDeadline);
    }

    private static void handleAddEventCommand(String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println(DIVIDER);
            System.out.println(" Please provide a description and duration for the event.");
            System.out.println(DIVIDER);
            return;
        }
        String[] eventParts = commandParts[1].split(" /from ");
        if (eventParts.length < 2) {
            System.out.println(DIVIDER);
            System.out.println(" Please use /from and /to to specify the event duration.");
            System.out.println(DIVIDER);
            return;
        }
        String[] timeParts = eventParts[1].split(" /to ");
        if (timeParts.length < 2) {
            System.out.println(DIVIDER);
            System.out.println(" Please use /to to specify the end time of the event.");
            System.out.println(DIVIDER);
            return;
        }
        Event newEvent = new Event(eventParts[0], timeParts[0], timeParts[1]);
        addTask(newEvent);
    }

    private static void handleDefaultCommand(String command) {
        // Keep creating generic Tasks for backward compatibility or if no keyword matches
        Task newTask = new Task(command, false);
        taskList[taskCount] = newTask;
        taskCount++;
        System.out.println(DIVIDER);
        System.out.println(" Added: " + newTask.getName());
        System.out.println(DIVIDER);
    }

    /**
     * Handles the execution of mark and unmark commands on the task list.
     *
     * @param markStatus The status to set: {@code true} for marked as done, {@code false} for undone.
     * @param commandParts The array of string tokens from the user input.
     */
    private static void setMark(boolean markStatus, String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println(" Please specify a task number.");
            return;
        }

        try {
            int index = Integer.parseInt(commandParts[1]) - 1;

            if (index >= 0 && index < taskCount && taskList[index] != null) {
                if (markStatus) {
                    System.out.println(" Nice! I've marked this task as done:");
                } else {
                    System.out.println(" OK, I've marked this task as not done yet:");
                }
                taskList[index].setDone(markStatus);
                System.out.println("   " + taskList[index].toString());
            } else {
                System.out.println(" Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println(" Task number must be an integer.");
        }
    }

    /**
     * Adds a task to the task list and prints the confirmation message.
     *
     * @param task The task to be added.
     */
    private static void addTask(Task task) {
        if (taskCount >= MAX_TASKS) {
            System.out.println(DIVIDER);
            System.out.println(" Task list is full. Cannot add more tasks.");
            System.out.println(DIVIDER);
            return;
        }
        taskList[taskCount] = task;
        taskCount++;
        System.out.println(DIVIDER);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }
}
