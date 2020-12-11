package com.company;
import java.io.IOException;

/*
    Author : Julien GODEST / Julie HUA
    File : Declenchable.java
    Date : 07/11/2020
    Ce fichier est une classe abstraite qui déclare les méthodes "declencher" et "arreter"
*/


public abstract class Declenchable {

    public abstract void declencher() throws IOException; // 4 : on appelle ce declencher -> il va voir sonnerie declenchable

    public abstract void arreter();


    public abstract int getM();
}
