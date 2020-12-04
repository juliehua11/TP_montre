package com.company;
import com.company.cafetiere.CafetiereDeclenchable;
import com.company.radio.RadioDeclenchable;
import com.company.sonnerie.SonnerieDeclenchable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;

/*
    Author : Julien GODEST / Julie HUA
    File : Reveil.java
    Date : 08/11/2020
    La classe Reveil hérite de la classe Montre et permets l'ajout d'objets Declenchable qui seront démarrés lorsque le reveil sonnera.
*/

public class Reveil extends Montre {

    Declenchable[] declenchable;
    int h;
    int m;
    int s;
    boolean isActive;
    Montre montre;

    public Reveil(int h, int m, int s, Montre montre){
        this.isActive = false;
        this.h = h;
        this.m = m;
        this.s = s;
        this.montre=montre;
    }

    public void addDeclenchable(Declenchable d){
        this.declenchable = new Declenchable[1];
        this.declenchable[0]= d;  // 2 on initialise le composant declenchable

    }

    public void addDeclenchable(Declenchable d, Declenchable n){
        this.declenchable = new Declenchable[2];
        this.declenchable[0]= d;
        this.declenchable[1]=n;
    }
    public void addDeclenchable(Declenchable d, Declenchable n, Declenchable m){
        this.declenchable = new Declenchable[3];
        this.declenchable[0]=d;
        this.declenchable[1]=n;
        this.declenchable[2]=m;
    }

    public void incrementer() throws IOException {
        this.montre.tourner();
        if (this.montre.getHeures() == this.h && this.montre.getMinutes() == this.m && this.montre.getSecondes() == this.s) {
            isActive = true;
            try {
                for (int i = 0; i < declenchable.length; i++) {
                    declenchable[i].declencher(); // 3  on déclenche declenchable si le reveil doit sonner
                }


            } catch (Exception e) {
               //  throw new IOException("Vous n'avez pas ajouté de déclenchable"); normalement l'exception est déjà géré
            }
        }
    }







}
