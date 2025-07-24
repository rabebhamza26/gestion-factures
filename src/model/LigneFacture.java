/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class LigneFacture {
    private int id;
     private Article article;
    private double prix;
    private int quantite;

    public LigneFacture() {
    
    }

    public LigneFacture(int id , Article article, double prix, int quantite) {
        this.id=id;
        this.article = article;
        this.prix = prix;
        this.quantite = quantite;
    }
    
     public void setId(int id) {
        this.id =  id;
    }
      public int getId() {
        return id;
    }



    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }


    public void saisirLigneFacture(Article article, double prix, int quantite) {
        this.article = article;
        this.prix = prix;
        this.quantite = quantite;
    }

    public void afficherLigneFacture() {
        article.afficherArticle();
        System.out.println("Prix: " + prix + ", Quantité: " + quantite);
    }

    

    
}
