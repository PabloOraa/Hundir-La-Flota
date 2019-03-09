/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 * Clase jugador que representa a cada uno de los miembros de la partida
 * @Version 1.0
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class Jugador
{
    private final String nickname;
    private Tablero tableroBarcos;
    private Tablero tabletoResultados;
    private ArrayList<Barco> listaBarcos;

    public Jugador(String nickname)
    {
        this.nickname = nickname;
        tableroBarcos = new Tablero();
        tabletoResultados = new Tablero();
        listaBarcos = new ArrayList<>();
        crearListaBarcos();
    }
    
    private void crearListaBarcos()
    {
        listaBarcos.add(new Barco("portaaviones",5));
        listaBarcos.add(new Barco("buque",3));
        listaBarcos.add(new Barco("submarino",2));
        listaBarcos.add(new Barco("lancha",1));
    }
    
    public char Disparar(Jugador j2, int fila, char columna)
    {
        return j2.comprobarDisparo(fila,columna);
    }
    
    public char comprobarDisparo(int fila, int columna)
    {
        if(tableroBarcos[fila][columna])
    }
}
