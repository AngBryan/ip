import java.util.Scanner;

public class Bryte {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String banner = "██████╗ ██████╗ ██╗   ██╗████████╗███████╗\n"
              + "██╔══██╗██╔══██╗╚██╗ ██╔╝╚══██╔══╝██╔════╝\n"
              + "██████╔╝██████╔╝ ╚████╔╝    ██║   █████╗  \n"
              + "██╔══██╗██╔══██╗  ╚██╔╝     ██║   ██╔══╝  \n"
              + "██████╔╝██║  ██║   ██║      ██║   ███████╗\n"
              + "╚═════╝ ╚═╝  ╚═╝   ╚═╝      ╚═╝   ╚══════╝\n";
        System.out.println(banner);
        System.out.println("Hello! I'm BRYTE.");
        System.out.println("What can I do for you?");
        while (true){
            String command = input.nextLine();
            if (command.equalsIgnoreCase("exit")){
                break;
            }

            System.out.println("____________________________________________________________");
            System.out.println(command);
            System.out.println("____________________________________________________________");

        }
        input.close();
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
