package org.anrp.service;

import org.anrp.Model.TaskModel;
import org.anrp.Options.Options;
import org.anrp.Repository.TaskRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

        TaskModel task = getTaskDetailsFromUser();
        Date currentTime = new Date();
        task.setTaskAddedDate(currentTime);
        task.setTaskUpdatedDate(currentTime);
        int count = 0;

        if (task.getTaskDate() != null && !task.getTaskTitle().trim().isEmpty())
            count = TaskRepository.add(task);

        System.out.println(count > 0 ? "Task Details Added Successfully" : "Failed to add the task");
    }

    private TaskModel getTaskDetailsFromUser() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the task Title : ");
        String taskTitle = scanner.nextLine();
        System.out.print(("Enter the task : "));
        String taskDescription = scanner.nextLine();
        Date taskDate = null;
        try {
            System.out.print("Enter the Task Date (mm/dd/yyyy) : ");
            String date = scanner.nextLine();

            taskDate = new Date(date);
        }
        catch (Exception e) {

            System.out.println("You Entered the Wrong Date format... Please try again...");
            return new TaskModel();
        }

        return new TaskModel(taskDescription,taskTitle,taskDate);


    }

    private void getTasks() {

        List<TaskModel> taskList = TaskRepository.getAll();

        taskList.forEach(x-> System.out.println(x.toString()));
    }

    private void updateTask() {

        System.out.print("Enter the task number, which need to be updated : ");
        try {
            int taskNo = new Scanner(System.in).nextInt();
            System.out.print("Enter the Task Title that need to be updated : ");
            String taskTitle =  new Scanner(System.in).nextLine();
            System.out.print("Enter the Task Date that need to be updated : ");
            Date taskDate = new Date(new Scanner(System.in).nextLine());
            System.out.print("Enter the Task Description that need to be updated : ");
            String taskDescription =  new Scanner(System.in).nextLine();

            TaskModel updatedTask = new TaskModel(taskDescription,taskTitle,taskDate);
            updatedTask.setTaskUpdatedDate(new Date());

            System.out.println(TaskRepository.update(taskNo,updatedTask) > 0 ? "Task Details updated Successfully" : "Failed to update the task, please check the entered task number");

        }
        catch (NumberFormatException e) {

            System.out.println("Cancelling the operation as you enter a non digit value...");
        }
        catch (Exception e) {
            System.out.println("Wrong Input " + e);
        }
    }

    private void deleteTasks() {

        System.out.print("Enter the task number, which need to be deleted: ");
        try {
            int taskNo = new Scanner(System.in).nextInt();
            System.out.println(TaskRepository.delete(taskNo) > 0 ?
                    "Task Deleted Successfully, Task Number : " + taskNo :
                    "Something Went wrong while deleting the Task, Please check the task number");
        }
        catch (Exception e) {
            System.out.println("Cancelling the operation as you enter a non digit value...");
        }
    }


    boolean getCorrectInput() {

        System.out.println("Enter a valid Option: ");
        return takeAction(new Scanner(System.in).nextLine().toUpperCase());
    }


    public List<TaskModel> getTaskList(ResultSet taskResults) throws SQLException {

        List<TaskModel> returnList = new ArrayList<>();
        while (taskResults.next()) {


            String taskTitle = taskResults.getString("task_title");
            String taskDescription = taskResults.getString("task_description");
            Date taskDate = taskResults.getDate("task_date");
            int taskNo = taskResults.getInt("task_number");
            Date taskAddedDate = taskResults.getDate("task_added_date");
            Date taskUpdatedDate = taskResults.getDate("task_updated_date");
            TaskModel taskModel = new TaskModel(taskNo,taskAddedDate,taskDate,taskUpdatedDate,taskTitle,taskDescription);
            returnList.add(taskModel);
        }

        return returnList;
    }
}
