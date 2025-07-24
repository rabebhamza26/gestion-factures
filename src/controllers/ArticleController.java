/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mysql.jdbc.PreparedStatement;
import model.Article;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ArticleController implements IDAO<Article> {
     public static String url = "jdbc:mysql://localhost:3306/gestionfactures";
    public static String login = "root";
    public static String pwd = "";

     @Override
    public ArrayList<Article> getAll() {
        try {
            // Step 1: Chargement du pilote
            Class.forName("com.mysql.jdbc.Driver");
            // Step 2: Etablissement de la connection
            Connection conn = DriverManager.getConnection(url, login, pwd);
            // Step 3: Préparation
            Statement st = conn.createStatement();

            // Step 4: Exécution de la requête de sélection
            String req = "SELECT * FROM article";
            ResultSet rs = st.executeQuery(req);
            ArrayList<Article> articles = new ArrayList<Article>();

            while (rs.next()) {
                Article article = new Article(
                        rs.getInt("id"), 
                        rs.getInt("numArt"),// numArt
                        rs.getString("libelle")
                        
                );
                articles.add(article);
            }

            return articles;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

     @Override
    public boolean create(Article obj) {
      
 
    try {
        // Vérification des valeurs avant l'insertion
        System.out.println("Ajout de l'article : " + obj.getNumArt() + ", " + obj.getLibelle());
        
        // Etablissement de la connexion à la base de données
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, login, pwd);
        Statement st = conn.createStatement();

        // Requête d'insertion de l'article
        String req = "INSERT INTO article (numArt, libelle) VALUES (" + obj.getNumArt() + ", '" + obj.getLibelle() + "')";
         System.out.println(req);
            st.executeUpdate(req);

            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;


    }

     @Override
  
    public boolean update(Article o, int id) {
    try {
            Class.forName("com.mysql.jdbc.Driver");
            // Step2: etablissement de la connection
            Connection conn = DriverManager.getConnection(url, login, pwd);
            // Step3: preparation
            Statement st = conn.createStatement();

            // Step4.4:  execution de la requete de selection
            String req = "UPDATE article SET numArt = '" + o.getNumArt() + "', libelle = '" + o.getLibelle() + "' WHERE id =" + id;
            System.out.println(req);
            st.executeUpdate(req);

            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;   
    }


     @Override
    public boolean delete(int articleId) {
    try {
        // Create database connection
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, login, pwd);
        
        // Create the SQL query
        String req = "DELETE FROM article WHERE id = ?";
        
        // Prepare and execute the statement
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(req);
        ps.setInt(1, articleId);
        int rowsDeleted = ps.executeUpdate();
        
        // Return true if a row was deleted
        return rowsDeleted > 0;
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}

    
    
    public Article findByNumArt(int numArt) {
    try {
        // Charger le driver JDBC
        Class.forName("com.mysql.jdbc.Driver");

        // Se connecter à la base de données
        Connection conn = DriverManager.getConnection(url, login, pwd);

        // Créer la requête pour trouver un article par son numéro
        String req = "SELECT * FROM article WHERE numArt = ?";
        PreparedStatement pst = (PreparedStatement) conn.prepareStatement(req);
        pst.setInt(1, numArt);

        // Exécuter la requête
        ResultSet rs = pst.executeQuery();
        
        // Si un article est trouvé, le retourner
        if (rs.next()) {
            Article article = new Article();
            article.setNumArt(rs.getInt("numArt"));
            article.setLibelle(rs.getString("libelle"));
            return article;
        }
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null; // Retourner null si l'article n'est pas trouvé
}

 public Article findById(int id) {

        try {
            // Step1: Chargement du pilote
            Class.forName("com.mysql.jdbc.Driver");
            // Step2: etablissement de la connection
            Connection conn = DriverManager.getConnection(url, login, pwd);
            // Step3: preparation
            Statement st = conn.createStatement();

            // Step4.4:  execution de la requete de selection
            String req = "SELECT * FROM article WHERE id = " + id;
            ResultSet rs = st.executeQuery(req);
            Article u = null;
            while (rs.next()) {
                 u = new Article(
                        rs.getInt("id"),
                        rs.getInt("numArt"),
                        rs.getString("libelle"));
                
                return u ;
            }

            
            return u;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
