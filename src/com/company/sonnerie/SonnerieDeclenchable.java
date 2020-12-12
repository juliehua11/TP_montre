package com.company.sonnerie;
import com.company.Declenchable;
import java.io.IOException;

/*
    Author : Julien GODEST / Julie HUA
    File : SonnerieDeclenchable.java
    Date : 15/11/2020
    Ce fichier hérite de Declenchable et implémente Sonnerie. Les méthodes "declencher" et "arreter" appellent celles de la classe Sonnerie.
*/

public class SonnerieDeclenchable  extends Declenchable implements Sonnerie { // SonnerieClass et SonnerieDeclenchable utilisent l'interface

    SonnerieClass maSonnerie;
    public SonnerieDeclenchable(){

        this.maSonnerie = new SonnerieClass(0);
    }

    @Override
    public void declencher() throws IOException {
        maSonnerie.declencher();
        arreter();
    } // 5 : on est ici, il va utiliser le declencher de SonnerieClass

    @Override
    public void arreter() {
        maSonnerie.arreter();
    }

    public int getM() {
        return maSonnerie.getM();
    }
}
