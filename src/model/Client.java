/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Client {
    private int idClient;
    private String nomClient;
    private String adresseClient;
    private String contactClient;
    
   public Client() 
   {
 
   }

    public Client( int idClient, String nomClient, String adresseClient, String contactClient) 
    {   this.idClient = idClient ; 
        this.nomClient = nomClient;
        this.adresseClient = adresseClient;
        this.contactClient = contactClient;
    }
    public int getIdClient(){
     return idClient;
    }
    
    public int setIdClient(){
     return idClient;
    }
    
    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getContactClient() {
        return contactClient;
    }

    public void setContactClient(String contactClient) {
        this.contactClient = contactClient;
    }
    
    public void saisirDetailClient(String nom, String adresse, String contact) {
        this.nomClient = nom;
        this.adresseClient = adresse;
        this.contactClient = contact;
    }

    public void afficherDetailClient() {
        System.out.println("Nom: " + nomClient + ", Adresse: " + adresseClient + ", Contact: " + contactClient);
    }

    
   
}
