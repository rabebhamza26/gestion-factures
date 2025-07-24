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
import java.sql.PreparedStatement;
import java.util.List;
import model.ArticleDetails;


/**
 *
 * @author HP
 */
public class FactureAchatController {
    public static String url = "jdbc:mysql://localhost:3306/gestionfactures";
    public static String login = "root";
    public static String pwd = "";
    
    public List<String> getFournisseurs() {
    List<String> fournisseurs = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(url, login, pwd);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT nom FROM fournisseur")) {
         
        while (rs.next()) {
            fournisseurs.add(rs.getString("nom"));
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de la récupération des fournisseurs : " + e.getMessage());
    }
    return fournisseurs; // Retourne la liste, même vide en cas d'erreur
}

    public List<String> getArticles() {
    List<String> articles = new ArrayList<>();
    String query = """
            SELECT a.numArt
            FROM article a
            INNER JOIN ligneFact lf ON a.id = lf.article_id
            """;

    try (Connection conn = DriverManager.getConnection(url, login, pwd);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            articles.add(rs.getString("numArt"));
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de la récupération des articles : " + e.getMessage());
    }
    return articles; // Retourner la liste même en cas d'erreur (vide par défaut)
}

   public void ajouterFacture(String numeroFacture, String fournisseurNom, String ligneFacture, String date) {
    String query = "INSERT INTO factureachat (numFacture, nom_fournisseur, Date, lignefacture) VALUES (?, ?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(url, login, pwd);
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setString(1, numeroFacture);
        pstmt.setString(2, fournisseurNom);
        pstmt.setString(3, date);
        pstmt.setString(4, ligneFacture);
        
        pstmt.executeUpdate();
        System.out.println("Facture ajoutée avec succès !");
    } catch (SQLException e) {
        System.err.println("Erreur lors de l'ajout de la facture : " + e.getMessage());
    }
}

    public List<Object[]> getFactures() {
    List<Object[]> factures = new ArrayList<>();
    String query = "SELECT Id, numFacture, nom_fournisseur, Date, lignefacture FROM factureachat";
    try (Connection conn = DriverManager.getConnection(url, login, pwd);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        
        while (rs.next()) {
            Object[] row = {
                    rs.getString("Id"),
                    rs.getString("numFacture"),
                    rs.getString("nom_fournisseur"),
                    rs.getDate("Date"),
                    rs.getString("lignefacture")
            };
            factures.add(row);
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de la récupération des factures : " + e.getMessage());
    }
    return factures; // Retourne la liste même en cas d'erreur (vide par défaut)
}

    
public ArticleDetails getDetailsArticle(String numArt) {
    String query = """
            SELECT a.libelle, lf.quantite, lf.prix
            FROM article a
            INNER JOIN ligneFacture lf ON a.id = lf.article_id
            WHERE a.numArt = ?
            """;

    try (Connection conn = DriverManager.getConnection(url, login, pwd);
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setString(1, numArt);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return new ArticleDetails(
                    rs.getString("libelle"),
                    rs.getInt("quantite"),
                    rs.getDouble("prix")
                );
            }
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de la récupération des détails de l'article : " + e.getMessage());
    }
    return null; // Retourne null si aucun détail n'est trouvé ou en cas d'erreur
}
    
}
