/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Fournisseur {
    private int idFour;
    private String nomFour;
    private String adresseFour;
    private String contactFour;

    public Fournisseur() {}

    public Fournisseur(int idFour ,String nomFour, String adresseFour, String contactFour) {
        this.idFour = idFour; 
        this.nomFour = nomFour;
        this.adresseFour = adresseFour;
        this.contactFour = contactFour;
    }
    
     public int getIdFour(){
     return idFour;
    }
    
    public int setIdFour(){
     return idFour;
    }
    
     // Getter pour nomFour
    public String getNomFour() {
        return nomFour;
    }

    // Setter pour nomFour
    public void setNomFour(String nomFour) {
        this.nomFour = nomFour;
    }

    // Getter pour adresseFour
    public String getAdresseFour() {
        return adresseFour;
    }

    // Setter pour adresseFour
    public void setAdresseFour(String adresseFour) {
        this.adresseFour = adresseFour;
    }

    // Getter pour contactFour
    public String getContactFour() {
        return contactFour;
    }

    // Setter pour contactFour
    public void setContactFour(String contactFour) {
        this.contactFour = contactFour;
    }
    
    

    public void saisirDetailFournisseur(String nom, String adresse, String contact) {
        this.nomFour = nom;
        this.adresseFour = adresse;
        this.contactFour = contact;
    }

    public void afficherDetailFournisseur() {
        System.out.println("Nom: " + nomFour + ", Adresse: " + adresseFour + ", Contact: " + contactFour);
    }

    
    
    
}
