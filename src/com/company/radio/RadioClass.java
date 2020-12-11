package com.company.radio;

/*
    Author : Julien GODEST / Julie HUA
    File : RadioClass.java
    Date : 14/11/2020
    Implémentation des méthodes "declencher()" et "arreter()" de l'interface Radio
*/

public class RadioClass implements Radio {
    private int m; // RadioClass et RadioDeclenchable utilisent l'interface

    public RadioClass(int m){
       this.m = m;
    }

    public void  declencher() {
        this.m = 1;
        System.out.println("Activation de la radio : Station SkyRock 100.2 lancée");

       // 6 : on est ici, on a le code concret. Radio activé !!!
    }

    public int getM() {



        return m;
        //return m;

    }

    public void arreter() {
        System.out.println("Désactivation de la radio");
    }

}