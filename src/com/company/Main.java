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
    private JTextField TFHeureActuelle;
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

        //HeureActuelle.setBounds(100,200,500,400);
        LHeureActuelle.setFont(new Font("Calibri", Font.BOLD, 20));




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
            //            //reveil.addDeclenchable(radio);

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

            if(reveil.isActive) {
              LHeureActuelle.setText(montre.afficher());
              montre.tourner();

              /*if(test.getM()==1) {
                  System.out.println("ok"); 
              }*/

            System.out.println(radio.getM());

            if(radio.getM()==1){
                LRadio.setText("Activation de la radio : Station SkyRock 100.2 lancée");
                arrêterLaRadioCheckBox.setVisible(true);

            }

            if(cafetiere.getM()==1){
                LCafe.setText("Cafetière activé");



            }

                if(sonnerie.getM()==1){
                    LSonnerie.setText("ring ring ring");
                    arrêterLaSonnerieCheckBox.setVisible(true);
                }


                arrêterLaRadioCheckBox.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("julie");
                        LRadio.setText("radio arrete");
                        Fmessage_reveil.setText("");


                    }
                });
                arrêterLaSonnerieCheckBox.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("hua");
                        LSonnerie.setText("sonnerie arrete");
                        Fmessage_reveil.setText("");
                    }
                });


             /* if(addSonnerie) {


              }
              if(addCafetiere){

              }
              if(addSonnerie){

              }*/ // we don't cheat anymore LMAO
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