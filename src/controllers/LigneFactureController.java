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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Article;
import model.LigneFacture;

/**
 *
 * @author HP
 */
public class LigneFactureController  {
     public static String url = "jdbc:mysql://localhost:3306/gestionfactures";
    public static String login = "root";
    public static String pwd = "";

    
    public ArrayList<LigneFacture> getAll() {
    ArrayList<LigneFacture> lignes = new ArrayList<>();
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, login, pwd);
        Statement st = conn.createStatement();

        // Requête avec JOIN pour récupérer les informations des articles et le numéro d'article
        String req = "SELECT lf.*, a.numArt, a.libelle FROM lignefacture lf " +
                     "JOIN article a ON lf.article_id = a.id";
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            // Création de l'objet Article avec numArt et libelle
            Article article = new Article(
                 rs.getInt("id"),      
                rs.getInt("numArt"),  // Récupérer le numéro de l'article
                rs.getString("libelle") // Récupérer le libellé de l'article
            );

            // Création de l'objet LigneFacture
            LigneFacture ligne = new LigneFacture(
                rs.getInt("id"),
                article,
                rs.getDouble("prix"),
                rs.getInt("quantite")
            );
            lignes.add(ligne);
        }
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(LigneFactureController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return lignes;
}



   

public boolean create(LigneFacture ligne) {
   
 
    // Vérification si l'article est nul
    if (ligne.getArticle() == null) {
        JOptionPane.showMessageDialog(null, "L'article n'est pas spécifié !", "Erreur", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    

    try {
        // Connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/gestionfactures";
        String login = "root";
        String pwd = "";
        Connection conn = DriverManager.getConnection(url, login, pwd);

        // Récupérer l'ID de l'article à partir du numArt
        int articleId = getArticleId(ligne.getArticle().getLibelle(), conn);

        if (articleId == -1) {
            JOptionPane.showMessageDialog(null, "Article non trouvé dans la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Requête d'insertion
        String req = "INSERT INTO lignefacture (article_id, quantite, prix) VALUES (?, ?, ?)";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(req);

        // Remplir la requête avec les valeurs
        pstmt.setInt(1, articleId);  // ID de l'article récupéré
        pstmt.setInt(2, ligne.getQuantite());  // Quantité
        pstmt.setFloat(3, (float) ligne.getPrix());  // Prix

        // Exécution de la requête
        int rowsInserted = pstmt.executeUpdate();

        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(null, "Ligne de facture ajoutée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion dans la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        pstmt.close();
        conn.close();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erreur de connexion ou de requête : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    return false;  // Retourne false en cas d'échec
 // Si l'insertion échoue
}

private int getArticleId(String numArticle, Connection conn) {
    // Recherche de l'ID de l'article par son numArt
    String query = "SELECT id FROM article WHERE numArt = ?";
    try (PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query)) {
        stmt.setString(1, numArticle);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erreur lors de la récupération de l'ID de l'article : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    return -1;  // Retourne -1 si l'article n'existe pas
}


    // Mettre à jour une ligne de facture
    public boolean update(int id, LigneFacture ligne) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, login, pwd);

            String req = "UPDATE lignefacture SET article_id = ?, prix = ?, quantite = ? WHERE id = ?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(req);

            pst.setInt(1, ligne.getArticle().getNumArt());
            pst.setDouble(2, ligne.getPrix());
            pst.setInt(3, ligne.getQuantite());
            pst.setInt(4, id);

            pst.executeUpdate();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LigneFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Supprimer une ligne de facture par ID
    public boolean delete(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, login, pwd);

            String req = "DELETE FROM lignefacture WHERE id = ?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(req);
            pst.setInt(1, id);

            pst.executeUpdate();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LigneFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // Trouver une ligne de facture par son ID
    public LigneFacture findById(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, login, pwd);

            String req = "SELECT * FROM lignefacture WHERE id = ?";
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(req);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                LigneFacture ligne = new LigneFacture(
                        rs.getInt("qte"),
                        new Article(
                                rs.getInt("id"),      
                                rs.getInt("numArt"), 
                                rs.getString("libelle")),// Création de l'article
                        rs.getDouble("prix"),
                        rs.getInt("quantite")
                );
                return ligne;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LigneFactureController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
}
