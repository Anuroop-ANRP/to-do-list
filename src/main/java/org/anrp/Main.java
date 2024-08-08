package org.anrp;

import org.anrp.Commands.Commands;
import org.anrp.service.ToDoService;

import java.util.Scanner;

public class Main {

    static {

        System.out.println("------------------------------------------------------------------------\n" +
                "Welcome to the TO DO List\n"+"---------------------------\n"+
                "Hi there, This is a Command Line Application. You can easily navigate through " +
                "the command line and add, fetch, and update your profile and TO DO List.\n\n" +
                "Please Follow the Below Instructions\n"+
                "---------------------------------------"
        );


    }

    public static void main(String[] args) {

        Commands commands = new Commands();
        ToDoService service =  new ToDoService();
        Scanner scanner = new Scanner(System.in);

        commands.intro();
        while (true) {

            String userPreference = scanner.nextLine();
            if (!service.takeAction(userPreference)) {
                commands.bye();
                break;
            }
        }

    }
}