package com.company.cafetiere;

/*
    Author : Julien GODEST / Julie HUA
    File : CafetiereClass.java
    Date : 14/11/2020
    Implémentation des méthodes "declencher()" et "arreter()" de l'interface Cafetiere
*/

public class CafetiereClass implements Cafetiere { // RadioClass et RadioDeclenchable utilisent l'interface

    private int m; // utilisation d'une variable,  qui va nous servir pour savoir si la procédure Declencher a été appelé ou non

    public CafetiereClass(int m){
        this.m = m;
    }// CafetiereClass et CafetiereDeclenchable utilisent l'interface

    public void declencher() {
        this.m=1;
        System.out.println("Démarrage de la cafetière");
        // 6 : on est ici, on a le code concret. cafetiere activé !!!

    }

    public int getM() {
        System.out.print(m);
        return m;

    }

    public void arreter() {
        System.out.println("Votre café est prêt, attention c'est chaud !");
    }

}