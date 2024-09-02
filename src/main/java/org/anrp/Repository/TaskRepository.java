package org.anrp.Repository;

import org.anrp.Model.TaskModel;
import org.anrp.service.ToDoService;

import java.sql.*;
import java.util.List;

public class TaskRepository {

    private final static String url = "jdbc:postgresql://localhost:5432/db";
    private final static String user = "postgres";
    private final static String password = "******";
    private static Connection con = null;

    private final static String insertSQL = "INSERT INTO to_do_db (task_added_date,task_date,task_updated_date,task_title,task_description) VALUES (?,?,?,?,?)";
    private final static String selectAllSQL = "SELECT * FROM to_do_db";
    private final static String deleteSQL = "DELETE FROM to_do_db WHERE task_number = (?)";
    private final static String updateSQL = "UPDATE to_do_db SET task_title = (?), task_description = (?), task_date = (?), task_updated_date = (?) WHERE task_number = (?)";



    static void dbConnection () {

        try {

            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Something went wrong" + e);
        }
    }

    static void dbConnectionClose () {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Something went Wrong " + e);;
        }
    }

    public static int add(TaskModel task) {

        dbConnection();

        try {

            PreparedStatement statement = con.prepareStatement(insertSQL);
            statement.setDate(1, new Date(task.getTaskAddedDate().getTime()));
            statement.setDate(2, new Date(task.getTaskDate().getTime()));
            statement.setDate(3, new Date(task.getTaskUpdatedDate().getTime()));
            statement.setString(4,task.getTaskTitle());
            statement.setString(6,task.getTaskDescription());
            return statement.executeUpdate();

        }
        catch (Exception exception) {

            System.out.println("Something Went Wrong While inserting to DB"+ exception);
            return 0;
        }

        finally {
            dbConnectionClose();
        }


    }

    public static List<TaskModel> getAll() {

        List<TaskModel> taskList  =  null;
        ToDoService service = new ToDoService();

        dbConnection();

        try {

            PreparedStatement statement = con.prepareStatement(selectAllSQL);
            ResultSet taskResults = statement.executeQuery();
            taskList = service.getTaskList(taskResults);
            return taskList;
        }

        catch (Exception exception) {

            System.out.println("Something Went Wrong While Getting data from  DB" + exception);
            return taskList;
        }

        finally {
            dbConnectionClose();
        }

    }

    public static int delete(int taskNo) {

        dbConnection();
        int updatedCount = 0;
        try {

            PreparedStatement statement = con.prepareStatement(deleteSQL);
            statement.setInt(1,taskNo);
            updatedCount = statement.executeUpdate();
        }
        catch (Exception e){

            System.out.println("Error while connecting to DB...");
        }
        finally {
            dbConnectionClose();
        }

        return updatedCount;
    }

    public static int update(int taskNo, TaskModel updatedTask) {

        dbConnection();
        int updatedCount = 0;

        try {

            PreparedStatement statement = con.prepareStatement(updateSQL);
            statement.setString(1,updatedTask.getTaskTitle());
            statement.setInt(5,taskNo);
            statement.setString(2, updatedTask.getTaskDescription());
            statement.setDate(3, new Date(updatedTask.getTaskDate().getTime()));
            statement.setDate(4, new Date(updatedTask.getTaskUpdatedDate().getTime()));
            updatedCount = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something Went Error..." + e);
        } finally {
            dbConnectionClose();
        }

        return updatedCount;
    }
}
