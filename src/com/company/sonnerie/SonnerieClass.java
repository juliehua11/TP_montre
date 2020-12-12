package com.company.sonnerie;
import java.io.IOException;

/*
    Author : Julien GODEST / Julie HUA
    File : SonnerieClass.java
    Date : 15/11/2020
    Implémentation des méthodes "declencher()" et "arreter()" de l'interface Sonnerie
*/


public class SonnerieClass implements Sonnerie {

    private int m ;

    public SonnerieClass(int m) {
        this.m = m;
    }

    // SonnerieClass et SonnerieDeclenchable utilisent l'interface

    public void declencher() throws IOException {
        this.m = 1;
        System.out.println("Ring ring ring..."); // 6 : on est ici, on a le code concret. Sonnerie activé !!!
        /*System.out.println("Si voulez arreter la sonnerie, tapez y + entrer");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        while(!s.equals("y")) { // Tant que l'utilisateur n'éteints pas le reveil, il continue de sonner
            System.out.println("Ring Ring Ring");
            s = br.readLine();
        }*/
    }

    public void arreter() {
        System.out.println("Sonnerie arretée.");
    }

    public int getM() {
        return m;
    }
}
