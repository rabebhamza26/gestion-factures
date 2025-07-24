/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.List;
import model.ArticleDetails;




/**
 *
 * @author HP
 */
public class FactureVenteController {
    public static String url = "jdbc:mysql://localhost:3306/gestionfactures";
    public static String login = "root";
    public static String pwd = "";
    
    
     // Récupérer la liste des noms des clients depuis la table client.
    public ArrayList<String> getClients() {
    ArrayList<String> clients = new ArrayList<>();
    try {
        // Charger le pilote JDBC
        Class.forName("com.mysql.jdbc.Driver");
        
        // Établir la connexion à la base de données
        Connection conn = DriverManager.getConnection(url, login, pwd);
        
        // Préparer et exécuter la requête
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT nomClient FROM client");
        
        // Parcourir les résultats et ajouter les noms à la liste
        while (rs.next()) {
            clients.add(rs.getString("nomClient"));
        }
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(FactureVenteController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return clients; // Retourner la liste des noms de clients
}
    
     //Récupérer les numéros d'article liés à la table ligneFacture
    public ArrayList<String> getArticles() {
    ArrayList<String> articles = new ArrayList<>();
    String query = """
            SELECT a.numArt
            FROM article a
            INNER JOIN ligneFact lf ON a.id = lf.article_id
            """;
    try {
        // Charger explicitement le pilote JDBC
        Class.forName("com.mysql.jdbc.Driver");
        
        // Établir la connexion
        try (Connection conn = DriverManager.getConnection(url, login, pwd);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            // Ajouter les numéros d'article à la liste
            while (rs.next()) {
                articles.add(rs.getString("numArt"));
            }
        }
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(FactureVenteController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return articles; // Retourner la liste des articles
}
    
    //Ajouter une nouvelle facture dans la table facturevente
    public boolean ajouterFacture(String numeroFacture, String clientNom, String ligneFacture, String date) {
    String query = "INSERT INTO facturevente (numFact, nomClient, Date, lignefact) VALUES (?, ?, ?, ?)";
    try {
        // Charger le pilote JDBC
        Class.forName("com.mysql.jdbc.Driver");

        // Connexion à la base de données
        try (Connection conn = DriverManager.getConnection(url, login, pwd);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            // Configuration des paramètres
            pstmt.setString(1, numeroFacture);
            pstmt.setString(2, clientNom);
            pstmt.setString(3, date);
            pstmt.setString(4, ligneFacture);
            
            // Exécution de la requête
            int rowsInserted = pstmt.executeUpdate();

            // Vérifier si l'insertion a réussi
            if (rowsInserted > 0) {
                System.out.println("Facture ajoutée avec succès !");
                return true;
            }
        }
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(FactureVenteController.class.getName()).log(Level.SEVERE, "Pilote JDBC non trouvé", ex);
    } catch (SQLException ex) {
        Logger.getLogger(FactureVenteController.class.getName()).log(Level.SEVERE, "Erreur SQL", ex);
    }
    return false; // Retourner false en cas d'échec
}

   
    //Récupérer les factures sous forme de liste de listes d'objets.
    public List<List<Object>> getFactures() {
    List<List<Object>> factures = new ArrayList<>();
    String query = "SELECT id, numFact, nom_client, Date, lignefacture FROM facturevente";
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
        conn = DriverManager.getConnection(url, login, pwd);
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
        
        while (rs.next()) {
            List<Object> row = new ArrayList<>();
            row.add(rs.getInt("Id"));
            row.add(rs.getString("numFacture"));
            row.add(rs.getString("nomClient"));
            row.add(rs.getDate("Date"));
            row.add(rs.getString("lignefact"));
            factures.add(row);
        }
    } catch (SQLException e) {
        // Affiche une erreur dans la console
        System.err.println("Erreur lors de la récupération des factures : " + e.getMessage());
    } finally {
        // Fermeture des ressources pour éviter les fuites
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            System.err.println("Erreur lors de la fermeture des ressources : " + ex.getMessage());
        }
    }
    return factures;
}

   
   //Récupérer les détails d'un article (libellé, quantité, prix).   
  public ArticleDetails getDetailsArticle(String numArt) {
    String query = """
            SELECT a.libelle, lf.quantite, lf.prix
            FROM article a
            INNER JOIN ligneFact lf ON a.id = lf.article_id
            WHERE a.numArt = ?
            """;

    ArticleDetails details = null;

    try (Connection conn = DriverManager.getConnection(url, login, pwd);
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, numArt);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                // Créer un objet ArticleDetails si des données sont trouvées
                details = new ArticleDetails(
                        rs.getString("libelle"),
                        rs.getInt("quantite"),
                        rs.getDouble("prix")
                );
            }
        }
    } catch (SQLException e) {
        // Gestion de l'exception avec un message d'erreur
        System.err.println("Erreur lors de la récupération des détails de l'article : " + e.getMessage());
    }

    return details; // Retourne les détails ou null si aucun résultat trouvé
}

}