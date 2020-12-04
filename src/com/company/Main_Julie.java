package com.company;

import com.company.cafetiere.CafetiereDeclenchable;
import com.company.radio.RadioDeclenchable;
import com.company.sonnerie.SonnerieDeclenchable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;


public class Main_Julie extends JFrame {

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
    private JTextField TFHeureActuelle;
    private static boolean pasDeBoutonAppuyé = true;
    static int hReveil = 0;
    static int mReveil = 0;
    static int sReveil = 0;
    static Boolean addSonnerie = false;
    static Boolean addRadio = false;
    static Boolean addCafetiere = false;
    static Boolean composant_click =false;



    public Main_Julie() throws IOException {

            /*final boolean[] pasDeBoutonAppuyé = {true};
            final int[] hReveil = {0};
            final int[] mReveil = {0};
            final int[] sReveil = {0};
            final boolean[] addSonnerie = {false};
            final boolean[] addRadio = {false};
            final boolean[] addCafetiere = {false};*/

        Montre montre = new Montre();
        montre.regler(LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(), LocalDateTime.now().getSecond());
        add(rootPanel);
        setTitle("MY TITLE");
        setSize(600, 600);

        lancerLeRéveilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                hReveil = new Integer(CBHeure.getSelectedItem().toString()); // récupération de la valeur de l'heure du réveil
                mReveil = new Integer(CBMinute.getSelectedItem().toString());
                sReveil = new Integer(CBSeconde.getSelectedItem().toString());
                addSonnerie = CheckBoxSonnerie.isSelected(); // récupération si checkbox true or false
                addRadio = CheckBoxRadio.isSelected();
                addCafetiere = CheckBoxCafe.isSelected();
                pasDeBoutonAppuyé = false;
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

        this.setVisible(true);

        while (pasDeBoutonAppuyé || composant_click==false) {

            // si le bouton n'est pas appuyé
            LHeureActuelle.setText(montre.afficher());
            montre.tourner();
        }

        while (!pasDeBoutonAppuyé && composant_click==true) {
            Reveil reveil = new Reveil(hReveil, mReveil, sReveil, montre);

            //Sonnerie
            SonnerieDeclenchable sonnerie = new SonnerieDeclenchable();
            //reveil.addDeclenchable(sonnerie); //  1 on ajoute un déclenchable sonnerie qu'on utilisera, attention on ne peut instancier une classe abstraite


            // Radio
            RadioDeclenchable radio = new RadioDeclenchable();
            //reveil.addDeclenchable(radio);

            // cafetière
            CafetiereDeclenchable cafetiere = new CafetiereDeclenchable();
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
            while (!reveil.isActive) {
                LHeureActuelle.setText(montre.afficher());
                reveil.incrementer();
            }


            while(reveil.isActive) {
              LHeureActuelle.setText(montre.afficher());
              montre.tourner();


                    if (addSonnerie && !addCafetiere && !addRadio) {



                    }
                    if (addSonnerie && addRadio && !addCafetiere) {


                    }
                    if (addSonnerie && !addRadio && addCafetiere) {

                    }
                    if (!addSonnerie && addRadio && !addCafetiere) {
                        Fmessage_reveil.setText("la radio est allumé");

                        // si que radio

                    }
                    if (!addSonnerie && !addRadio && addCafetiere) {

                    }
                    if (!addSonnerie && addRadio && addCafetiere) {
                        reveil.addDeclenchable(radio, cafetiere);
                    }

                    if (addSonnerie && addRadio && addCafetiere) {

                    }
                }






        }


    }




           /*for(int i =0; i<2000000; i++) {
                //TFHeureActuelle = new JTextField();
               // TFHeureActuelle.setText(Integer.toString(j));
               //TAHeureActuelle.append(montre.afficher());
               LHeureActuelle.setText(montre.afficher());
                montre.tourner();
            }*/




}