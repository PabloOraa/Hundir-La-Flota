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
    private Tablero tableroResultados;
    private ArrayList<Barco> listaBarcos;

    /**
     * Constructor de la clase jugador que cea los tableros, Barcos y nombre del
     * jugador.
     * @param nickname Nombre del jugador
     */
    public Jugador(String nickname)
    {
        this.nickname = nickname;
        tableroBarcos = new Tablero();
        tableroResultados = new Tablero();
        listaBarcos = new ArrayList<>();
        crearListaBarcos();
    }
    
    /**
     * Crea los barcos que tiene el usuario.
     */
    private void crearListaBarcos()
    {
        listaBarcos.add(new Barco("portaaviones",5));
        listaBarcos.add(new Barco("buque",3));
        listaBarcos.add(new Barco("submarino",2));
        listaBarcos.add(new Barco("lancha",1));
    }

    /**
     * Obtiene el nombre de usuario que usa el jugador en la partida.
     * @return Nombre del usuario
     */
    public String getNickname()
    {
        return nickname;
    }

    /**
     * Obtiene el tablero con los barcos que usa el jugador en la partida.
     * @return Tablero con los barcos del usuario.
     */
    public Tablero getTableroBarcos()
    {
        return tableroBarcos;
    }

    /**
     * Obtiene el tablero con los resultado de los disparos que usa el jugador 
     * en la partida.
     * @return Tablero con los disparos del usuario.
     */
    public Tablero getTabletoResultados()
    {
        return tableroResultados;
    }

    /**
     * Obtiene la lista de barcos del usuario.
     * @return ArrayList con los barcos del usuario.
     */
    public ArrayList<Barco> getListaBarcos()
    {
        return listaBarcos;
    }
    
    /**
     * Dispara al otro jugador en una posición determinada.
     * @param j2 Jugador al que dispara
     * @param fila Fila entre 1 y 10 en las que se encuentra el disparo
     * @param columna Columna ente A y J en la que se encuentra el disparo
     * @return Cadena de texto con el resultado: Agua, Tocado y Hundido
     * @throws Clases.ExcepcionesBarco
     */
    public String Disparar(Jugador j2, int fila, char columna) throws ExcepcionesBarco
    {
        return j2.comprobarDisparo(fila-1,columna);
    }
    
    /**
     * Comprueba si el disparo ha dado en un barco o en el agua.
     * @param fila Fila entre 0 y 9 en las que se encuentra el disparo
     * @param columna Columna ente A y J en la que se encuentra el disparo
     * @return Cadena de texto con el resultado: Agua, Tocado y Hundido
     * @throws Clases.ExcepcionesBarco
     */
    private String comprobarDisparo(int fila, char columna) throws ExcepcionesBarco
    {
        char res;
        String resultado;
        if(tableroResultados.getPos(fila, columna) == 'A') //No se ha disparado en ese punto
        {
            if(Textos.EMPTY != tableroBarcos.getPos(fila,tableroBarcos.getCoord(Character.toUpperCase(columna))))
            {
                res = Textos.RIGHTLETTER;
                resultado = Textos.RIGHT;
            }
            else
            {
                res = Textos.FAILLETTER;
                resultado = Textos.FAIL;
            }
            referenciaTablero(fila, tableroBarcos.getCoord(Character.toUpperCase(columna)), res);
            return resultado;
        }
        else
            throw new ExcepcionesBarco(Textos.NOTFREEPOSITION);
    }

    /**
     * Guarda en su segundo tablero el resultado del disparo.
     * @param fila Numero que representa la fila elegida por el usuario.
     * @param columna Numero que representa la columna elegida por el usuario,
     * convertida desde la letra.
     * @param res Resultado en X u O de lo que ha ocurrido.
     */
    private void referenciaTablero(int fila, int columna, char res)
    {
        tableroResultados.insertarResultado(fila, columna, res);
    }
    
    
}
