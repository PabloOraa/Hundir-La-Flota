package Clases;

import java.util.ArrayList;

/**
 * Clase Jugador que representa a cada uno de los miembros de la partida
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

    public String getNickname()
    {
        return nickname;
    }

    public Tablero getTableroBarcos()
    {
        return tableroBarcos;
    }

    public Tablero getTabletoResultados()
    {
        return tabletoResultados;
    }

    public ArrayList<Barco> getListaBarcos()
    {
        return listaBarcos;
    }
    
    public String Disparar(Jugador j2, int fila, char columna)
    {
        return j2.comprobarDisparo(fila,columna);
    }
    
    public String comprobarDisparo(int fila, char columna)
    {
        char res;
        String resultado;
        if(Textos.EMPTY != tableroBarcos.getPos(fila,columna))
        {
            res = Textos.RIGHTLETTER;
            resultado = Textos.RIGHT;
        }
        else
        {
            res = Textos.FAILLETTER;
            resultado = Textos.FAIL;
        }
        referenciaTablero(res);
        return resultado;
    }

    private void referenciaTablero(char res)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
