import java.util.Scanner;

public class Bryte {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] list = new String[100];
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
            switch(command.toLowerCase()){
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < listCounter; i++){
                        System.out.println(i + ". " + list[i]);
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    list[listCounter] = command;
                    listCounter++;
                    System.out.println("____________________________________________________________");
                    System.out.println("Added: " + command);
                    System.out.println("____________________________________________________________");

            }

        }
        input.close();
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
