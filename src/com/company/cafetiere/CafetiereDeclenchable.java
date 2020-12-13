package com.company.cafetiere;
import com.company.Declenchable;

/*
    Author : Julien GODEST / Julie HUA
    File : CafetiereDeclenchable.java
    Date : 14/11/2020
    Ce fichier hérite de Declenchable et implémente Cafetiere. Les méthodes "declencher" et "arreter" appellent celles de la classe Cafetiere
*/

public class CafetiereDeclenchable  extends Declenchable implements Cafetiere { // CafetiereClass et CafetiereDeclenchable utilisent l'interface

    CafetiereClass monCafe;
    public CafetiereDeclenchable(){
        this.monCafe = new CafetiereClass(0);
    }

    @Override
    public void declencher() {
        monCafe.declencher();
        arreter(); // Une fois le café coulé, on appelle la méthode arreter() pour afficher le message de fin
    }// 5 : on est ici, il va utiliser le declencher de CafetiereClass

    @Override
    public void arreter() {
        monCafe.arreter();
    }

    @Override
    public int  getIfDeclencher() {
          return  monCafe.getIfDeclencher();
    }
}
