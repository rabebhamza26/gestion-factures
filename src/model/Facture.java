/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;


/**
 *
 * @author HP
 */
public class Facture implements OperationFacture {
    private int numFact;
    private Date date;
    private String typeFacture;

    protected ArrayList<LigneFacture> ligneFact = new ArrayList<>();
    
        public Facture() {
    
    }

    public Facture(int numFact, Date date, String typeFacture) {
        this.numFact = numFact;
        this.date = date;
        this.typeFacture =typeFacture;
        this.ligneFact = new ArrayList<>();

    }
    
    public int getNumFact() {
        return numFact;
    }

    public void setNumFact(int numFact) {
        this.numFact = numFact;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String  getTypeFacture() {
        return typeFacture;
    }

    public void setTypeFacture(String typeFacture ) {
        this.typeFacture = typeFacture;
    }




    @Override
    public void saisirFacture() {

    }

    @Override
    public void afficherFacture() {
        System.out.println("Le numéro de la facture est : " + numFact);
    
    // Affichage de la date de la facture
    if (date != null) {
        date.afficherDate(); // Appel de la méthode AfficherDate() de la classe Date
    } else {
        System.out.println("Date non disponible.");
    }
    
    // Affichage des lignes de facture
    if (ligneFact.isEmpty()) {
        System.out.println("Aucune ligne de facture.");
    } else {
        
        for (int i = 0; i < ligneFact.size(); i++) {
            ligneFact.get(i).afficherLigneFacture(); // Appel de la méthode AfficherLigFacture() de chaque ligne
        }
    }
            System.out.println("Total: " + totalFacture());

            
    }

    @Override
    public double totalFacture() {
        double total = 0;
    for (int i = 0; i < ligneFact.size(); i++) {
        total += ligneFact.get(i).getPrix() * ligneFact.get(i).getQuantite();
    }
    
    return total;

        
    }
    
}
