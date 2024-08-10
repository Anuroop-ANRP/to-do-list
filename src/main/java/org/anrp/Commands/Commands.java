package org.anrp.Commands;

import org.anrp.Options.Options;

public class Commands {

    public void intro() {

        System.out.println("------------------------------------------------------------------------\n" +
                "Welcome to the TO DO List\n"+"---------------------------\n"+
                "Hi there, This is a Command Line Application. You can easily navigate through " +
                "the command line and add, fetch, and update your profile and TO DO List.\n\n" +
                "Please Follow the Below Instructions\n"+
                "---------------------------------------"
        );

        System.out.println("Hi, Please Type 'get'  or 1 View yout To Do List.\n" +
                "Type 'add' or 2 to add a new task.\n" +
                "Type 'delete' or 3 to add a new task.\n" +
                "Type 'update' or 4 to add a new task.\n" +
                "Type 'quit' or press 0 to Quit the Application.");
    }

    public void bye() {

        System.out.println("Thank you...");
    }
}
