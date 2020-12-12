package com.company;
import java.io.IOException;

/*
    Author : Julien GODEST / Julie HUA
    File : com.company.Montre.java
    Date : 01/11/2020
    La classe Montre est une chaine de compteur spécifique (3 compteurs qui representent respectivement les heures, minutes et secondes)
*/

public class Montre extends ChaineCompteur {

    public Montre() {
        super(new int[]{0, 0, 0},new int[]{0, 0, 0}, new int[] {24, 60, 60}, new int[] {1, 1, 1});
    }

    // Incrémentation toute les secondes grâce à la classe mère
    public void tourner() throws IOException {
        System.out.println(super.afficher());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.incrementer();
    }

    public void raz(){
        super.raz();
    }

    // Reglage de la montre en démarrant à 0 et en incrémentant d'autant de secondes que nécéssaire.
    public void regler(int h, int m, int s) throws IOException {
        raz();
        for (int i = 0; i < h * 3600 + m * 60 + s; i++) {
            super.incrementer(); // Pour regler à 0h/0min/10s -> On incremente 10 fois | Pour regler à 1h/0min/0s -> On incremente 3600 fois (car 1h = 3600s)
        }
    }

    // Accesseurs
    public int getSecondes(){
        return super.getSecondes();
    }
    public int getMinutes(){
        return super.getMinutes();
    }
    public int getHeures(){
        return super.getHeures();
    }
}


