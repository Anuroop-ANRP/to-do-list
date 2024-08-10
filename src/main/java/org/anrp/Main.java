package org.anrp;

import org.anrp.Commands.Commands;
import org.anrp.service.ToDoService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Commands commands = new Commands();
        ToDoService service =  new ToDoService();
        Scanner scanner = new Scanner(System.in);

        commands.intro();
        while (true) {

            String userPreference = scanner.nextLine().toUpperCase();
            if (!service.takeAction(userPreference)) {
                commands.bye();
                break;
            }
        }

    }
}