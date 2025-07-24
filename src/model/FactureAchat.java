/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class FactureAchat extends Facture {
    private Fournisseur fournisseur;

    public FactureAchat() {
             super();   

    }

    public FactureAchat(int numFact, Date date, Fournisseur fournisseur) {
        super(numFact, date,"Achat");

        this.fournisseur = fournisseur;
    }

   
    
    @Override
    public void saisirFacture() {
     System.out.println("Saisie des informations pour une Facture d'Achat...");

    }

    
    @Override
    public void afficherFacture() {
        System.out.println("Facture Vente - Client : " + fournisseur.getNomFour());
        fournisseur.afficherDetailFournisseur();
        super.afficherFacture();
    }
    
    
    
    

    
}
