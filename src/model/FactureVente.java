/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class FactureVente extends Facture {
    private Client client;

    public FactureVente() {
             super();   

    }

    public FactureVente(int numFact, Date date, Client client) {
        super(numFact, date,"Vente");
        this.client = client;
    }

    
    @Override
    public void saisirFacture() {
        System.out.println("Saisie des informations pour une Facture de vente...");
    }

    
    @Override
    public void afficherFacture() {
        System.out.println("Facture Vente - Client : " + client.getNomClient());
        client.afficherDetailClient();
        super.afficherFacture();
    }

    
    
    
}
