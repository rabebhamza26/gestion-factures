/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Article {
     private int id;
     private   int numArt;
    private String libelle;

    public Article()
    {
    }

    public Article(int id, int numArt, String libelle)
    {       this.id =id;
           this.numArt = numArt ;//NumArt  généré automatiquement
         this.libelle = libelle; 
    }

    public Article(int articleId, String newNumArt, String newLibelle) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getNumArt() {
        return numArt;
    }

    public void setNumArt(int numArt) {
        this.numArt = numArt;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
        
    

    public void saisirArticle(int num, String libelle) {
        Scanner scanner = new Scanner(System.in); //l'utilisateur saisir le libelle en clavier
        System.out.print("Entrez le libellé de l'article : ");
        this.libelle = scanner.nextLine();
        this.numArt = numArt; 
        
    }

    public void afficherArticle() {
        System.out.println("Numéro: " + numArt + ", Libellé: " + libelle );
    }

    public void setNumArt(String numArticle) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
   

    
    
    
}
