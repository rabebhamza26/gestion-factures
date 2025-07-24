/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

    

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Login;

/**
 * LoginController manages login operations with the database.
 */
public class LoginController {

    public static String url = "jdbc:mysql://localhost:3306/gestionfactures";
    public static String login = "root";
    public static String pwd = "";

    // Helper method to get a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, pwd);
    }
 // Get all logins from the database
    public ArrayList<Login> getAll() {
        ArrayList<Login> logins = new ArrayList<>();
        String query = "SELECT * FROM login";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Login l = new Login(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
                logins.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Error fetching logins", ex);
        }
        return logins;
    }
     // Create a new login entry in the database
    public boolean create(Login obj) {
        String query = "INSERT INTO login (username, password) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, obj.getUsername());
            pst.setString(2, obj.getPassword());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Error creating login", ex);
        }
        return false;
    }
     // Validate login credentials
    public boolean findByLoginAndPassword(Login loginObj) {
        String query = "SELECT * FROM login WHERE username = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, loginObj.getUsername());
            pst.setString(2, loginObj.getPassword());

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next(); // Return true if at least one record matches
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Error validating login", ex);
        }
        return false;
    }
    
}
