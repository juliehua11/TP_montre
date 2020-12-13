package com.company;

import com.company.cafetiere.CafetiereDeclenchable;
import com.company.radio.RadioDeclenchable;
import com.company.sonnerie.SonnerieDeclenchable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDateTime;

/*
    Author : Julien GODEST / Julie HUA
    File : Main.java
    Date : 18/11/2020
    La classe Main définit les élements de l'interface graphique et lance le script.
*/

public class Main extends JFrame {

    private JPanel rootPanel;
    private JButton lancerLeRéveilButton;
    private JComboBox CBHeure;
    private JComboBox CBMinute;
    private JComboBox CBSeconde;
    private JCheckBox CheckBoxSonnerie;
    private JCheckBox CheckBoxRadio;
    private JCheckBox CheckBoxCafe;
    private JLabel LHeureActuelle;
    private JLabel Fmessage_reveil;
    private JLabel LRadio;
    private JLabel LSonnerie;
    private JLabel LCafe;
    private JCheckBox arrêterLaRadioCheckBox;
    private JCheckBox arrêterLaSonnerieCheckBox;
    private static boolean pasDeBoutonAppuyé = true;
    static int hReveil = 0;
    static int mReveil = 0;
    static int sReveil = 0;
    static Boolean addSonnerie = false;
    static Boolean addRadio = false;
    static Boolean addCafetiere = false;
    static Boolean composant_click =false;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
    }

    public Main() throws IOException {

        // Choix de la police et de la taille du texte
        LHeureActuelle.setFont(new Font("Calibri", Font.BOLD, 20));

        // Création de la montre
        Montre montre = new Montre();
        // Réglage à au temps actuelle
        montre.regler(LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(), LocalDateTime.now().getSecond());
        add(rootPanel);
        setTitle("Mon reveil");
        setSize(1000, 1000);

        lancerLeRéveilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrêterLaRadioCheckBox.setVisible(false);
                arrêterLaSonnerieCheckBox.setVisible(false);
                Fmessage_reveil.setText("");
                LCafe.setText("");
                LRadio.setText("");
                LSonnerie.setText("");

                hReveil = new Integer(CBHeure.getSelectedItem().toString()); // récupération de la valeur de l'heure du réveil
                mReveil = new Integer(CBMinute.getSelectedItem().toString());
                sReveil = new Integer(CBSeconde.getSelectedItem().toString());
                addSonnerie = CheckBoxSonnerie.isSelected(); // récupération si checkbox true or false
                addRadio = CheckBoxRadio.isSelected();
                addCafetiere = CheckBoxCafe.isSelected();
                pasDeBoutonAppuyé = false;

                // Message d'erreur si aucun déclenchable est selectionné
                if(addSonnerie==false && addRadio==false && addCafetiere==false)  {
                    Fmessage_reveil.setText("Vous devez sélectionner au moins un paramètre pour activé le réveil ");
                }
                else {
                    composant_click = true;
                    System.out.println("Reveil paramétré");
                    Fmessage_reveil.setText("Réveil activé avec succès");
                }
            }
        });
        arrêterLaRadioCheckBox.setVisible(false);
        arrêterLaSonnerieCheckBox.setVisible(false);
        this.setVisible(true);

        // Tant que le reveil n'est pas lancé, on incremente le temps
        while (pasDeBoutonAppuyé || composant_click==false) {

            // si le bouton n'est pas appuyé
            LHeureActuelle.setText(montre.afficher());
            montre.tourner();
        }

        // Reveil lancé
        while (!pasDeBoutonAppuyé && composant_click==true) {
            Reveil reveil = new Reveil(hReveil, mReveil, sReveil, montre);

            // Création des trois objets déclenchables
            //Sonnerie
            SonnerieDeclenchable sonnerie = new SonnerieDeclenchable();

            // Radio
            RadioDeclenchable radio = new RadioDeclenchable();

            // cafetière
            CafetiereDeclenchable cafetiere = new CafetiereDeclenchable();

            // Selon les declenchables cochés, on les ajoutent au reveil
            if (addSonnerie && !addCafetiere && !addRadio) {
                reveil.addDeclenchable(sonnerie);
            }
            if (addSonnerie && addRadio && !addCafetiere) {
                reveil.addDeclenchable(sonnerie, radio);
            }
            if (addSonnerie && !addRadio && addCafetiere) {
                reveil.addDeclenchable(sonnerie, cafetiere);
            }
            if (!addSonnerie && addRadio && !addCafetiere) {
                reveil.addDeclenchable(radio);
            }
            if (!addSonnerie && !addRadio && addCafetiere) {
                reveil.addDeclenchable(cafetiere);
            }
            if (!addSonnerie && addRadio && addCafetiere) {
                reveil.addDeclenchable(radio, cafetiere);
            }
            if (addSonnerie && addRadio && addCafetiere) {
                reveil.addDeclenchable(sonnerie, radio, cafetiere);
            }

            // Tant que le reveil n'est pas arrivé au temps voulu, on l'incremente
            while (!reveil.isActive) {
                LHeureActuelle.setText(montre.afficher());
                reveil.incrementer();
            }

            // Reveil arrivé au temps ordoné par l'utilisateur
            if(reveil.isActive) {
              LHeureActuelle.setText(montre.afficher());
              montre.tourner();

              // Affichage des messages des declencheurs sur l'interface visuelle
                if(radio.getIfDeclencher()==1){
                    LRadio.setText("Activation de la radio : Station SkyRock 100.2 lancée");
                    arrêterLaRadioCheckBox.setVisible(true);
                }

                if(cafetiere.getIfDeclencher()==1){
                    LCafe.setText("Cafetière activé");
                }

                if(sonnerie.getIfDeclencher()==1){
                    LSonnerie.setText("ring ring ring");
                    arrêterLaSonnerieCheckBox.setVisible(true);
                }

                // Deux boutons pour arreter la radio et la sonnerie
                arrêterLaRadioCheckBox.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        LRadio.setText("radio arrete");
                        Fmessage_reveil.setText("");

                    }
                });
                arrêterLaSonnerieCheckBox.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        LSonnerie.setText("sonnerie arrete");
                        Fmessage_reveil.setText("");
                    }
                });
            }
        }
    }
}