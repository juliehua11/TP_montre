package com.company;
import com.company.cafetiere.CafetiereDeclenchable;
import com.company.radio.RadioDeclenchable;
import com.company.sonnerie.SonnerieDeclenchable;
import javax.swing.*;
import java.awt.*;

/*
    Author : Julien GODEST / Julie HUA
    File : Main.java
    Date : 01/11/2020
    La classe Main initialise les objets et démarre le programme.
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main extends JFrame{

    private static boolean pasDeBoutonAppuyé = true;
    static int hReveil = 0;
    static int mReveil = 0;
    static int sReveil = 0;
    static Boolean addSonnerie = false;
    static Boolean addRadio = false;
    static Boolean addCafetiere = false;

    public static void main(String[] args) throws IOException {

        /*
        ChaineCompteur chaine = new ChaineCompteur(new int[]{0, 0, 0},new int[]{0, 0, 0}, new int[] {24, 60, 60}, new int[] {1, 1, 1});
        chaine.afficher();
        chaine.incrementer();
        */

        /*
        Montre montre = new com.company.Montre();
        montre.regler(1,20,10);
        while(true) {
            montre.tourner();
        }
        */
        Montre montre=new Montre();
        montre.regler( LocalDateTime.now().getHour(),LocalDateTime.now().getMinute(),LocalDateTime.now().getSecond());

        JFrame frame = new JFrame("Reveil"); // création d'une fenêtre
        frame.setSize(600, 600);
        frame.setLocation(0, 0);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setLayout(null); // fin paramètre fenêtre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // croix ferme fenêtre
        JLabel Heure = new JLabel(); // création de l'objet heure
        Heure.setBounds(200, 0 ,300 , 200); // placement de l'objet
        Heure.setFont(new Font("sansserif", Font.PLAIN, 60));
        frame.add(Heure); // ajout de l'objet dans la fenêtre

        Object[] listeH= new Object[]{0, 1, 2, 3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};  // création des selects pour heure
        JComboBox h = new JComboBox(listeH);
        h.setBounds(150,200,50,50);
        frame.add(h); // ajout des heures  dans la fenêtre

        Object[] listeM= new Object[]{0, 1, 2, 3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59};
        JComboBox m = new JComboBox(listeM);
        m.setBounds(250,200,50,50);
        frame.add(m); // ajout des min

        Object[] listeS= new Object[]{0, 1, 2, 3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59};
        JComboBox s = new JComboBox(listeS);
        s.setBounds(350,200,50,50);
        frame.add(s);

        // création des checkbox
        JCheckBox sonnerieButton = new JCheckBox("Sonnerie");
        sonnerieButton.setSelected(true);
        sonnerieButton.setBounds(150,300,150,50);
        sonnerieButton.setFont(new Font("sansserif", Font.PLAIN, 20));
        frame.add(sonnerieButton);

        JCheckBox radioButton = new JCheckBox("Radio");
        radioButton.setSelected(true);
        radioButton.setBounds(150,350,150,50);
        radioButton.setFont(new Font("sansserif", Font.PLAIN, 20));
        frame.add(radioButton);

        JCheckBox cafeButton = new JCheckBox("Café");
        cafeButton.setSelected(true);
        cafeButton.setBounds(150,400,150,50);
        cafeButton.setFont(new Font("sansserif", Font.PLAIN, 20));
        frame.add(cafeButton);

        JButton BTN1 = new JButton("Lancer le reveil");
        BTN1.setBounds(200, 460, 180, 80);
        BTN1.addActionListener(new ActionListener() { // click sur le bouton déclenche l'évènement
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Reveil paramétré");
                hReveil = new Integer(h.getSelectedItem().toString()); // récupération de la valeur de l'heure du réveil
                mReveil = new Integer(m.getSelectedItem().toString());
                sReveil = new Integer(s.getSelectedItem().toString());
                addSonnerie = sonnerieButton.isSelected(); // récupération si checkbox true or false
                addRadio= radioButton.isSelected();
                addCafetiere= cafeButton.isSelected();
                pasDeBoutonAppuyé = false;
            }
        });

        frame.add(BTN1);

        frame.setVisible(true); // affichage de la fenêtre

        while(pasDeBoutonAppuyé){ // si le bouton n'est pas appuyé
            Heure.setText(montre.afficher());
            montre.tourner();
        }
        while(!pasDeBoutonAppuyé){ // si bouton appuyé = réveil lancé
            Reveil reveil = new Reveil(hReveil, mReveil, sReveil, montre);

            //Sonnerie
                SonnerieDeclenchable sonnerie = new SonnerieDeclenchable();
                //reveil.addDeclenchable(sonnerie); //  1 on ajoute un déclenchable sonnerie qu'on utilisera, attention on ne peut instancier une classe abstraite


            // Radio
                RadioDeclenchable radio = new RadioDeclenchable();
                //reveil.addDeclenchable(radio);

            // cafetière
                CafetiereDeclenchable cafetiere = new CafetiereDeclenchable();

            if(addSonnerie && !addCafetiere && !addRadio){
                reveil.addDeclenchable(sonnerie);
            }
            if(addSonnerie && addRadio && !addCafetiere) {
                reveil.addDeclenchable(sonnerie, radio);
            }
            if(addSonnerie && !addRadio && addCafetiere) {
                reveil.addDeclenchable(sonnerie, cafetiere);
            }
            if(!addSonnerie && addRadio && !addCafetiere) {
                reveil.addDeclenchable(radio);
            }
            if(!addSonnerie && !addRadio && addCafetiere) {
                reveil.addDeclenchable(cafetiere);
            }
            if(!addSonnerie && addRadio && addCafetiere) {
                reveil.addDeclenchable(radio, cafetiere);
            }

            if(addSonnerie && addRadio && addCafetiere) {
                reveil.addDeclenchable(sonnerie, radio, cafetiere);
            }
            while(!reveil.isActive){
                Heure.setText(montre.afficher());
                reveil.incrementer();
            }
            Heure.setText("DRING DRING DRING");
        }
    }
}


/*

 Reveil reveil = new Reveil(1,1,6,montre);

        //Sonnerie
        SonnerieDeclenchable sonnerie = new SonnerieDeclenchable();
        //reveil.addDeclenchable(sonnerie); //  1 on ajoute un déclenchable sonnerie qu'on utilisera, attention on ne peut instancier une classe abstraite


        // Radio
        RadioDeclenchable radio = new RadioDeclenchable();
        //reveil.addDeclenchable(radio);


        // cafetière
        CafetiereDeclenchable cafetiere = new CafetiereDeclenchable();
        reveil.addDeclenchable(sonnerie);


package com.company;

import javax.swing.*;
import java.awt.event.*;

public class test extends JFrame {

    public test() {
        super("Reveil");

        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        };

        addWindowListener(l);
        setSize(1000,500);
        setVisible(true);
    }

    public static void main(String [] args){
        JFrame frame = new test();
    }
}
 */