/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;



import java.awt.*;
import javax.swing.*;

public class Splash extends JFrame {

    public Splash() {
       
        splash();
        setLocationRelativeTo(null);

    }

    private void splash() {
       // Titre
jLabel1 = new JLabel("Invoice Management System");
jLabel1.setFont(new Font("Century Gothic", Font.BOLD, 30));
jLabel1.setForeground(new Color(0, 0, 204));

// Panel principal
jPanel1 = new JPanel();
jPanel1.setBackground(new Color(255, 255, 255)); // White using RGB
jPanel1.setLayout(new GridBagLayout()); // Meilleure disposition

// Logo
jLabel2 = new JLabel();
jLabel2.setIcon(new ImageIcon(getClass().getResource("/images/logo.png")));
jLabel2.setHorizontalAlignment(SwingConstants.CENTER);

// Texte de progression
jLabel3 = new JLabel("Chargement...");
jLabel3.setFont(new Font("Times New Roman", Font.BOLD, 24));
jLabel3.setForeground(new Color(0, 0, 204));

// Barre de progression
jProgressBar1 = new JProgressBar();
jProgressBar1.setForeground(new Color(0, 0, 204)); // Couleur attrayante
jProgressBar1.setStringPainted(true); // Affiche la progression en pourcentage

// Ajouter les composants avec GridBagLayout
GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(10, 10, 10, 10); // Marges

// Position du titre (en haut de la page)
gbc.gridx = 0;
gbc.gridy = 0;
gbc.anchor = GridBagConstraints.NORTH; // Alignement en haut
jPanel1.add(jLabel1, gbc);

// Position du logo
gbc.gridy = 1;
gbc.anchor = GridBagConstraints.CENTER;
jPanel1.add(jLabel2, gbc);

// Position du texte de progression
gbc.gridy = 2;
jPanel1.add(jLabel3, gbc);

// Position de la barre de progression
gbc.gridy = 3;
gbc.fill = GridBagConstraints.HORIZONTAL;
jPanel1.add(jProgressBar1, gbc);

// Configuration du JFrame
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setTitle("Splash Screen");
setSize(600, 400);
setLocationRelativeTo(null); // Centrer sur l'écran
setUndecorated(true); // Retirer la barre de titre pour plus de style
add(jPanel1);

// Simuler le chargement
        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(50);
                    jProgressBar1.setValue(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dispose();
            openLogin(); // Ouvrir l'interface login
        }).start();
    }

    private void openLogin() {
        new login().setVisible(true);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            new Splash().setVisible(true);
        });
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jProgressBar1.setBackground(new java.awt.Color(0, 0, 204));
        jProgressBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 3, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText(" Invoice Management System");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(280, 280, 280)
                            .addComponent(jLabel3))))
                .addContainerGap(119, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 365, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}





   
