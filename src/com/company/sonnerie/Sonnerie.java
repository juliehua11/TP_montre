package com.company.sonnerie;
import java.io.IOException;

/*
    Author : Julien GODEST / Julie HUA
    File : Sonnerie.java
    Date : 15/11/2020
    Ce fichier est l'interface sonnerie contenant les déclarations des méthodes "declencher()" et "arreter()"
*/

public interface Sonnerie {

    void declencher() throws IOException;

    void arreter();


}
