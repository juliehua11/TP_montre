package com.company.radio;
import com.company.Declenchable;

import java.io.IOException;

/*
    Author : Julien GODEST / Julie HUA
    File : RadioDeclenchable.java
    Date : 14/11/2020
    Ce fichier hérite de Declenchable et implémente Radio. Les méthodes "declencher" et "arreter" appellent celles de la classe Radio
*/

public class RadioDeclenchable  extends Declenchable implements Radio { // RadioClass et RadioDeclenchable utilisent l'interface

   RadioClass maRadio;
    public RadioDeclenchable(){
        this.maRadio = new RadioClass();
    }

    @Override
    public void declencher() {
        maRadio.declencher();
    }// 5 : on est ici, il va utiliser le declencher de RadioClass


    @Override
    public void arreter() {
        maRadio.arreter();
    }


}
