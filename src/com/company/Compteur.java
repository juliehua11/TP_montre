package com.company;

/*
    Author : Julien GODEST / Julie HUA
    File : Compteur.java
    Date : 01/11/2020
    La classe Compteur est utilisé par la classe chaîne de compteurs pour incrementer la val.
*/

public class Compteur {

    private int val;
    private int borneInf;
    private int borneSup;
    private int pas;

    public Compteur(int val, int borneInf, int borneSup, int pas){
        this.val = val;
        this.borneInf = borneInf;
        this.borneSup = borneSup;
        this.pas = pas;
    }

    // Remise à la valeur minimale
    public void raz(){
        this.val = this.borneInf;
    }

    // Incrementation de la valeur par le pas, retourne 0 si la borne sup non dépassée, 1 sinon.
    public boolean incrementer(){
        this.val += pas;
        if (val >= borneSup) {
            val = borneInf;
            return true;
        }
        return false;
    }

    // Acceseurs
    public int getValeur() {
        return val;
    }
    public int getbInf() {
        return borneInf;
    }
    public int getbSup() {
        return borneSup;
    }
    public int getPas() {
        return pas;
    }
    // Fin Accesseurs
}
