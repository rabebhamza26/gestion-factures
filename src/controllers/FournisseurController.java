/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Fournisseur;

/**
 *
 * @author HP
 */
public class FournisseurController implements IDAO<Fournisseur> {
    public static String url = "jdbc:mysql://localhost:3306/gestionfactures";
    public static String login = "root";
    public static String pwd = "";


    @Override
    public ArrayList<Fournisseur> getAll() {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, login, pwd);
            Statement st = conn.createStatement();

            String req = "SELECT * FROM fournisseur";
            ResultSet rs = st.executeQuery(req);
            ArrayList<Fournisseur> fournisseurs = new ArrayList<>();

            while (rs.next()) {
                 Fournisseur f = new Fournisseur(
                        rs.getInt("idFournisseur"),
                        rs.getString("nomFournisseur"),
                        rs.getString("adresseFournisseur"),
                        rs.getString("contactFournisseur"));
                fournisseurs.add(f);
            }
                 return fournisseurs;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    @Override
    public boolean create(Fournisseur obj) {
                try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, login, pwd);
            Statement st = conn.createStatement();

            String req = "INSERT INTO fournisseur (nomFournisseur, adresseFournisseur, contactFournisseur) VALUES ('"
                    + obj.getNomFour() + "', '" + obj.getAdresseFour() + "', '" + obj.getContactFour() + "')";

            System.out.println(req); // Affiche la requête SQL pour le débogage
            st.executeUpdate(req);

            return true; // Insertion réussie
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // Échec de l'insertion
    }

    @Override
    public boolean update(Fournisseur o, int id) {
                        
         try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, login, pwd);

        String req = "UPDATE fournisseur SET nomFournisseur = ?, adresseFournisseur = ?, contactFournisseur = ? WHERE idFournisseur = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(req);
        ps.setString(1, o.getNomFour());
        ps.setString(2, o.getAdresseFour());
        ps.setString(3, o.getContactFour());
        ps.setInt(4, id);

        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;

    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(FournisseurController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;    

    }

    @Override
    public boolean delete(int id) {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, login, pwd);

        String req = "DELETE FROM fournisseur WHERE idFournisseur = ?";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(req);
        ps.setInt(1, id);

        int rowsDeleted = ps.executeUpdate();
        return rowsDeleted > 0;

    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(FournisseurController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;

    }
    
    public Fournisseur getFournisseurById(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, login, pwd);
            Statement st = conn.createStatement();

            String req = "SELECT * FROM fournisseur WHERE idFournisseur = " + id;
            ResultSet rs = st.executeQuery(req);

            if (rs.next()) {
                return new Fournisseur(
                        rs.getInt("idFournisseur"),
                        rs.getString("nomFournisseur"),
                        rs.getString("adresseFournisseur"),
                        rs.getString("contactFournisseur")
                );
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null; // Si aucun fournisseur n'est trouvé
    }
    
}
