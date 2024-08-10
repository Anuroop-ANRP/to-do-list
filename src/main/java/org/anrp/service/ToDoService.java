package org.anrp.service;

import org.anrp.Options.Options;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class ToDoService {

    public boolean takeAction(String userPreference) {

        Options op = null;

        if (Objects.equals(Options.QUIT.name(),userPreference) || (userPreference.matches("-?[0-9]+") && Integer.parseInt(userPreference) == 9)) {
            return false;
        }

        try {

            if (Integer.parseInt(userPreference) > Options.values().length - 1) {
                return getCorrectInput();
            }
            else {
                op = Options.values()[Integer.parseInt(userPreference)];
            }
        } catch (NumberFormatException ignored) {

            Optional<Options> userCmnd = Arrays.stream(Options.values()).filter(x -> x.name().equals(userPreference)).findAny();
            if (userCmnd.isPresent()) {
                op = userCmnd.get();
            }
            else {
                return getCorrectInput();
            }
        }
        return callOperations(op);
    }

    boolean callOperations (Options op) {

        switch (op) {

            case ADD    -> addTask();
            case GET    -> getTasks();
            case UPDATE -> updateTask();
            case DELETE -> deleteTasks();
            default -> getCorrectInput();
        }

        return true;

    }

    private void addTask() {
        System.out.println("Added a Task");
    }

    private void getTasks() {
        System.out.println("Showing all the Tasks");
    }

    private void updateTask() {
        System.out.println("Updated The Task");
    }

    private void deleteTasks() {
        System.out.println("Deleted The Task");
    }


    boolean getCorrectInput() {

        System.out.println("Enter a valid Option: ");
        return takeAction(new Scanner(System.in).nextLine().toUpperCase());
    }


}
