package com.company;
import java.io.IOException;


/*
    Author : Julien GODEST / Julie HUA
    File : ChaineCompteur.java
    Date : 01/11/2020
    La classe ChaineCompteur créé un tableau de compteurs et l'incrémente grâce à la fonction de la classe Compteur.
*/

public class ChaineCompteur {

    private Compteur[] tabCompteur;

    // Constructeur qui créé un tableau de compteurs
    public ChaineCompteur(int[] valeur, int[] borneInf, int[] borneSup, int[] pas) {
        this.tabCompteur = new Compteur[valeur.length]; // Initialisation du tableau
        for(int i = 0; i < valeur.length; i++){
            this.tabCompteur[i] = new Compteur(valeur[i],borneInf[i],borneSup[i], pas[i]); // Déclaration du tableaus
        }
    }

    // Remise à la valeur minimale de tout les compteurs du tableau
    public void raz(){
       for(Compteur c : tabCompteur){
           c.raz();
       }
    }

    // Incrementation du tableau de compteurs

    public void incrementer() throws IOException {
        boolean termine = false;
        for (int i = tabCompteur.length - 1; i >= 0 && termine == false; i--) {  // On incremente le premier compteur, tant qu'il n'est pas terminé, on considère les autres comme terminé et on ne les incrémente pas
            termine = !tabCompteur[i].incrementer(); // Si return true, alors on incremente le compteur suivant. Si return false, on met terminé a true les autres.
        }
    }


    public String afficher() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tabCompteur.length; i++) {
            s.append(tabCompteur[i].getValeur());
            if (i < tabCompteur.length - 1) { // Condition pour ne pas mettre les ":" après le dernier compteur
                s.append(':');
            }
        }
        return s.toString();
    }

    // Accesseurs
    public int getHeures() {
        return this.tabCompteur[0].getValeur();
    }

    public int getMinutes() {
        return this.tabCompteur[1].getValeur();
    }

    public int getSecondes() {
        return this.tabCompteur[2].getValeur();
    }
}
