package com.ACSI.Authentification.authModel;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class AuthModel {
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement=null;
    String username="root";
    String password="123";
    String url="jdbc:mysql://localhost:3306/acsi";
    public String getPassword(String mail)
    {
        try {
            //1. Registering the Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2. create a Connection
            connection = DriverManager.getConnection(url, username, password);

            // 3. Create a statement
            statement = connection.createStatement();

            // 4. Execute a query
            String sql = "SELECT pswrd FROM auth where mail='"+mail+"'";
            resultSet = statement.executeQuery(sql);
            if (!resultSet.next())
                return "";
            return resultSet.getString("pswrd");
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null) {connection.close(); System.out.println("close");}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
    public boolean verify(String mail)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            // 3. Create a statement
            statement = connection.createStatement();

            // 4. Execute a query
            String sql = "SELECT mail FROM auth where mail='"+mail+"'";
            resultSet = statement.executeQuery(sql);
            if(!resultSet.next())
                return true;
            else return false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null) {connection.close(); System.out.println("close");}
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean insert(String mail,String name ,String passwrd){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

            // 3. Create a statement
            statement = connection.createStatement();

            // 4. Execute a query
            String sql = "INSERT INTO auth (mail, name, pswrd) VALUES ('"+mail+"','"+name+"','"+passwrd+"')";
            int rowsAffected = statement.executeUpdate(sql);
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}