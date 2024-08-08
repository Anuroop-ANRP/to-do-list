package org.anrp.service;

import org.anrp.Options.Options;

import java.util.Objects;

public class ToDoService {
    public boolean takeAction(String userPreference) {

        if (Objects.equals(userPreference, Options.QUIT.name()) || Integer.parseInt(userPreference) == Options.QUIT.ordinal())
            return false;
        return true;
    }
}
