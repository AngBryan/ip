import java.util.Scanner;

public class Bryte {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Task[] taskList = new Task[100];
        String banner = "██████╗ ██████╗ ██╗   ██╗████████╗███████╗\n"
              + "██╔══██╗██╔══██╗╚██╗ ██╔╝╚══██╔══╝██╔════╝\n"
              + "██████╔╝██████╔╝ ╚████╔╝    ██║   █████╗  \n"
              + "██╔══██╗██╔══██╗  ╚██╔╝     ██║   ██╔══╝  \n"
              + "██████╔╝██║  ██║   ██║      ██║   ███████╗\n"
              + "╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝\n";
        int listCounter = 0;
        boolean running = true;

        System.out.println(banner);
        System.out.println("Hello! I'm BRYTE.");
        System.out.println("What can I do for you?");

        while (running){
            String command = input.nextLine();
            String[] commandParts = command.split(" ");
            switch(commandParts[0].toLowerCase()){
                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listCounter; i++){
                        System.out.println(i + ".["+ taskList[i].getStatusIcon() + "] " + taskList[i].getName());
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case "mark":
                    System.out.println("____________________________________________________________");
                    setMark(true, taskList, commandParts);
                    System.out.println("____________________________________________________________");
                    break;

                case "unmark":
                    System.out.println("____________________________________________________________");
                    setMark(false, taskList, commandParts);
                    System.out.println("____________________________________________________________");
                    break;

                case "bye":
                    running = false;
                    break;
                    
                default:
                    Task newTask = new Task(command, false);
                    taskList[listCounter] = newTask;
                    listCounter++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Added: " + newTask.getName());
                    System.out.println("____________________________________________________________");

            }

        }
        input.close();
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    //Handles the mark and unmark command 
    private static void setMark(boolean markStatus, Task[] taskList, String[] commandParts) {
        if (commandParts.length < 2) {
            System.out.println("Please specify a task number.");
            return;
        }
        //use of AI for more robust error catching
        try {
            int index = Integer.parseInt(commandParts[1]);

            if (index >= 0 && index < taskList.length && taskList[index] != null) {
                if (markStatus == true){
                    System.out.println("Nice! I've marked this task as done:");
                }
                else{
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
