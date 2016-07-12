package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DbOperations {
public static ObservableList<Task> retrieveResultsFromTask(){
    ObservableList<Task> items = FXCollections.observableArrayList();
    Connection conn = null;
    try {
        conn = DbDriver.connect();
        Statement stmt;
        stmt = conn.createStatement();
        ResultSet rs= stmt.executeQuery("select * from Task");

        while(rs.next()){
            //System.out.println(rs.getString("name");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date aDate = rs.getDate("assigned_date");
            Date cDate = rs.getDate("completed_date");

            String aStr=sdf.format(aDate);
            String cStr=sdf.format(cDate);
            items.add(new Task(rs.getInt("id"), rs.getString("name"), LocalDate.parse(aStr, DateTimeFormatter.ISO_DATE),LocalDate.parse(cStr, DateTimeFormatter.ISO_DATE), rs.getDouble("hours_worked"), rs.getInt("FK_employee_id")));
        }
    } catch (SQLException e) {
        System.out.println("sql exceptions");
    }

    finally{
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("connection is not closed properly");
        }
    }

    return items;
}

    public static ObservableList<Employee> retrieveResultsFromEmployee(){
        ObservableList<Employee> items = FXCollections.observableArrayList();
        Connection conn = null;
        try {
            conn = DbDriver.connect();
            Statement stmt;
            stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery("select * from Employee");

            while(rs.next()){
                //System.out.println(rs.getString("name"));
                items.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getInt("FK_manager_id")));
            }
        } catch (SQLException e) {
            System.out.println("sql exceptions");
        }

        finally{
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("connection is not closed properly");
            }
        }

        return items;
    }



    public static void insertARowIntoTasks(int taskId, String taskName, Date taskAssigned, int empId)
    {
        Connection conn = null;
        try {
            conn = DbDriver.connect();
            Statement stmt;
            stmt = conn.createStatement();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sql="insert into Task values("+taskId+",'"+taskName+"','"+sdf.format(taskAssigned)+"','"+sdf.format(taskAssigned)+"',0.0,"+empId+")";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("insertion is not performed properly into Tasks table\n there could be a foreign key constraint");
            e.printStackTrace();
        }
        finally{
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("connection is not closed properly");
            }
        }
    }

    public static void insertARowIntoEmployee(int empId, String empName, int empMgrId)
    {
        Connection conn = null;
        try {
            conn = DbDriver.connect();
            Statement stmt;
            stmt = conn.createStatement();

            String sql="insert into Employee values("+empId+",'"+empName+"',"+empMgrId+")";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("insertion is not performed properly into Employee table\n there could be a foreign key constraint");
            e.printStackTrace();
        }
        finally{
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("connection is not closed properly");
            }
        }
    }

    public static void deleteARowFromEmployee(int empId)
    {
        Connection conn = null;
        try {
            conn = DbDriver.connect();
            Statement stmt;
            stmt = conn.createStatement();

            String sql="delete from Employee where id="+empId;
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("record not deleted operation from Employee table");
            e.printStackTrace();
        }
        finally{
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("connection is not closed properly");
            }
        }
    }
}
