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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] taskList = new Task[MAX_TASKS];
        int taskCount = 0;
        boolean isRunning = true;

        System.out.println(BANNER);
        System.out.println("Hello! I'm BRYTE.");
        System.out.println("What can I do for you?");

        while (isRunning) {
            String command = scanner.nextLine();
            String[] commandParts = command.split(" ");

            switch (commandParts[0].toLowerCase()) {
            case "list":
                System.out.println(DIVIDER);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i + ".[" + taskList[i].getStatusIcon() + "] "
                            + taskList[i].getName());
                }
                System.out.println(DIVIDER);
                break;

            case "mark":
                System.out.println(DIVIDER);
                setMark(true, taskList, commandParts);
                System.out.println(DIVIDER);
                break;

            case "unmark":
                System.out.println(DIVIDER);
                setMark(false, taskList, commandParts);
                System.out.println(DIVIDER);
                break;

            case "bye":
                isRunning = false;
                break;

            default:
                Task newTask = new Task(command, false);
                taskList[taskCount] = newTask;
                taskCount++;
                System.out.println(DIVIDER);
                System.out.println("Added: " + newTask.getName());
                System.out.println(DIVIDER);
                break;
            }
        }

        scanner.close();
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    /**
     * Handles the execution of mark and unmark commands on the task list.
     *
     * @param markStatus The status to set: {@code true} for marked as done, {@code false} for undone.
     * @param taskList The array containing the user's tasks.
     * @param commandParts The array of string tokens from the user input.
     */
    private static void setMark(boolean markStatus, Task[] taskList, String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println("Please specify a task number.");
            return;
        }

        try {
            int index = Integer.parseInt(commandParts[1]);

            if (index >= 0 && index < taskList.length && taskList[index] != null) {
                if (markStatus) {
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    System.out.println("OK, I've marked this task as not done yet:");
                }
                taskList[index].setDone(markStatus);
                System.out.println("  [" + taskList[index].getStatusIcon() + "] "
                        + taskList[index].getName());
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Task number must be an integer.");
        }
    }
}
