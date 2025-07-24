/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;


import model.Client;
import com.mysql.jdbc.PreparedStatement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ClientController implements IDAO<Client> {
     public static String url = "jdbc:mysql://localhost:3306/gestionfactures";
    public static String login = "root";
    public static String pwd = "";

    @Override
    public ArrayList<Client> getAll() {
    ArrayList<Client> clients = new ArrayList<>();
    try {
        // Étape 1 : Charger le pilote JDBC
        Class.forName("com.mysql.jdbc.Driver");
        
        // Étape 2 : Établir la connexion
        Connection conn = DriverManager.getConnection(url, login, pwd);
        
        // Étape 3 : Préparer la requête SQL
        String req = "SELECT * FROM client";
        Statement st = conn.createStatement();
        
        // Étape 4 : Exécuter la requête et traiter les résultats
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Client f = new Client(
                rs.getInt("idClient"),        
                rs.getString("nomClient"),    
                rs.getString("adresseClient"),
                rs.getString("contactClient")
            );
            clients.add(f);
        }
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return clients; 
}
    

    @Override
     public boolean create(Client obj) {
    try {
        // Step1: Chargement du pilote
        Class.forName("com.mysql.jdbc.Driver");
        
        // Step2: Établissement de la connexion
        Connection conn = DriverManager.getConnection(url, login, pwd);
        
        // Step3: Préparation de la requête
        Statement st = conn.createStatement();
        
        // Step4: Exécution de la requête d'insertion
        String req = "INSERT INTO client (nomClient, adresseClient, contactClient) VALUES ('"
                + obj.getNomClient() + "', '" + obj.getAdresseClient() + "', '" + obj.getContactClient() + "')";
        
        System.out.println(req); // Affiche la requête SQL pour le débogage
        st.executeUpdate(req);
        
        return true; // Insertion réussie
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false; // Échec de l'insertion
   }

    

    @Override
    public boolean update(Client o, int id) {
          try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, login, pwd);

        String req = "UPDATE client SET nomClient = ?, adresseClient = ?, contactClient = ? WHERE idClient = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(req);
        ps.setString(1, o.getNomClient());
        ps.setString(2, o.getAdresseClient());
        ps.setString(3, o.getContactClient());
        ps.setInt(4, id);

        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;

    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}

    


    @Override
    public boolean delete(int id) {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, login, pwd);

        String req = "DELETE FROM client WHERE idClient = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(req);
        ps.setInt(1, id);

        int rowsDeleted = ps.executeUpdate();
        return rowsDeleted > 0;

    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}

    

    
   public Client getClientById(int id) {
    try {
        // Étape 1 : Charger le pilote JDBC
        Class.forName("com.mysql.jdbc.Driver");
        
        // Étape 2 : Établir la connexion
        Connection conn = DriverManager.getConnection(url, login, pwd);
        
        // Étape 3 : Préparer la requête SQL avec un PreparedStatement
        String req = "SELECT * FROM client WHERE idClient = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(req);
        ps.setInt(1, id); 
        
        // Étape 4 : Exécuter la requête et traiter les résultats
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Client(
                rs.getInt("idClient"),
                rs.getString("nomClient"),
                rs.getString("adresseClient"),
                rs.getString("contactClient")
            );
        }
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null; 
}

    
    
}
