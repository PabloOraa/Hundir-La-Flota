package Clases;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Clase Jugador que representa a cada uno de los miembros de la partida
 * @Version 2.0
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class Jugador implements Serializable
{
    /**
     * Nombre del jugador
     */
    private final String nickname;
    /**
     * Tablero donde almacenará sus barcos en la partida
     */
    private Tablero tableroBarcos;
    /**
     * Tablero donde almacena los resultados de sus disparos
     */
    private Tablero tableroResultados;
    /**
     * Lista de los barcos del usuario
     */
    private final ArrayList<Barco> listaBarcos;
    /**
     * Contador de los barcos introducidos
     */
    private int contador = 0;

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
        listaBarcos.add(new Barco(Textos.PORTAAVIONES,5));
        listaBarcos.add(new Barco(Textos.BUQUE,3));
        listaBarcos.add(new Barco(Textos.SUBMARINO,2));
        listaBarcos.add(new Barco(Textos.LANCHA,1));
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
    public Tablero getTableroResultados()
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
     * Suma un valor al contador de barcos introducidos
     */
    public void sumContador()
    {
        contador++;
    }
    
    /**
     * Obtiene el contador de barcos introducidos.
     * @return ArrayList con los barcos del usuario.
     */
    public int getContador()
    {
        return contador;
    }
    
    /**
     * Llama al método insertar con 4 parámetros del tableroBarcos.
     * @param dir dirección del barco.
     * @param fila Fila entre 1 y 10 en la que está la primera posición del barco.
     * @param columna Carcater entre A y J que indica la primera posición del barco.
     * @param brc Barco a introducir en las coordenadas indicadas.
     * @return True si se ha insertado y false si no.
     * @throws ExcepcionesBarco - Barco no entra o ya hay en esa posición.
     */
    public boolean insertarBarco(char dir, int fila, char columna, Barco brc) throws ExcepcionesBarco
    {
        return tableroBarcos.insertar(dir, fila, columna, brc);
    }
    
    /**
     * Inserta los barcos del jugador a partir de un fichero CSV
     * @param archivo Archivo CSV con los barcos a insertar.
     * @return ArrayList con dos valores, el primero un boolean con verdadero si lo ha hecho corretamente y falso si no.<br/>El segundo valor es un número con los barcos insertados.
     */
    public ArrayList insertarBarco(File archivo)
    {
        ArrayList resultados = new ArrayList();
        try {
            ArrayList arr = tableroBarcos.insertar(archivo);
            resultados.add(arr.get(0));
            resultados.add(arr.get(1));
        } catch (ExcepcionesBarco ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            resultados.add(false);
            resultados.add(0);
        } finally
        {
            return resultados;
        }
    }
    
    /**
     * Dispara al otro jugador en una posición determinada.
     * <br />
     * Una vez disparemos, el otro jugador apuntará en su tablero si es Agua o Tocado
     * con X u O, al igual que nosotros lo haremos en el secundario.
     * <br/>
     * Comprueba antes de nada si podemos disparar en esa posición
     * @param j2 Jugador al que dispara
     * @param fila Fila entre 1 y 10 en las que se encuentra el disparo
     * @param columna Columna ente A y J en la que se encuentra el disparo
     * @return Cadena de texto con el resultado: Agua, Tocado y Hundido
     * @throws Clases.ExcepcionesBarco
     */
    public String Disparar(Jugador j2, int fila, char columna) throws ExcepcionesBarco
    {
        String cadTexto;
        if(tableroResultados.getPos(fila-1, columna) == 'A') //No se ha disparado en ese punto
            cadTexto = j2.comprobarDisparo(fila-1,columna);
        else
            throw new ExcepcionesBarco(Textos.NOTFREEPOSITION);
        
        if(cadTexto.equals(Textos.FAIL))
            referenciaTablero(fila-1, tableroBarcos.getCoord(Character.toUpperCase(columna)), Textos.FAILLETTER);
        else
            referenciaTablero(fila-1, tableroBarcos.getCoord(Character.toUpperCase(columna)), Textos.RIGHTLETTER);
        return cadTexto;
    }
    
    /**
     * Comprueba si el disparo ha dado en un barco o en el agua.
     * @param fila Fila entre 0 y 9 en las que se encuentra el disparo
     * @param columna Columna ente A y J en la que se encuentra el disparo
     * @return Cadena de texto con el resultado: Agua, Tocado y Hundido. 
     * Si el usuario se ha quedado sin barcos devolverá Jugador derrotado
     * @throws Clases.ExcepcionesBarco
     */
    private String comprobarDisparo(int fila, char columna) throws ExcepcionesBarco
    {
        char res;
        String resultado;
        if(Textos.EMPTY != tableroBarcos.getPos(fila,tableroBarcos.getCoord(Character.toUpperCase(columna))))
        {
            res = Textos.RIGHTLETTER;
            resultado = Textos.RIGHT;
            char letraB = tableroBarcos.getPos(fila,tableroBarcos.getCoord(Character.toUpperCase(columna)));
            int posBarco = listaBarcos.indexOf(new Barco(Barco.obtenerNombre(String.valueOf(letraB))));
            Barco brc = listaBarcos.get(posBarco);
            if(comprobarBarco(brc))
            {
                resultado = Textos.ENDOFSHIP;
                if(!comprobarBarcos())
                    resultado = Textos.PLAYERDEAD;
            }
        }
        else
        {
                res = Textos.FAILLETTER;
                resultado = Textos.FAIL;
        }
        apuntar(fila, columna, res);
        return resultado;
    }
    
    /**
     * Comprueba que un barco no tenga vidas
     * @param brc Barco a comprobar
     * @return True si no tiene y false si tiene vidas
     */
    private boolean comprobarBarco(Barco brc)
    {
        brc.reducirVida();
        return brc.getVidas() == 0;
    }
    
    /**
     * Comprueba si el usuarios sigue pudiendo jugar o si le han eliminado
     * @return True si está vivo y false si no
     */
    private boolean comprobarBarcos()
    {
        boolean vivo = false;
        for (Barco listaBarco : listaBarcos)
        {
            if(listaBarco.getVidas() > 0)
                vivo = true;
        }
        
        return vivo;
    }
    
    /**
     * Guarda en tablero de Barcos el resultado del disparo que nos han realizado.
     * @param fila Numero entre 0 y 9 que representa la fila elegida por el usuario.
     * @param columna letra entre A y J que representa la columna elegida por el usuario.
     * @param res Resultado en X u O de lo que ha ocurrido.
     */
    private void apuntar(int fila, char columna, char res)
    {
        tableroBarcos.insertarResultado(fila, tableroBarcos.getCoord(Character.toUpperCase(columna)),res);
    }
    
    /**
     * Guarda en su segundo tablero el resultado del disparo.
     * @param fila Numero entre 0 y 9 que representa la fila elegida por el usuario.
     * @param columna Numero que representa la columna elegida por el usuario,
     * convertida desde la letra.
     * @param res Resultado en X u O de lo que ha ocurrido.
     */
    private void referenciaTablero(int fila, int columna, char res)
    {
        tableroResultados.insertarResultado(fila, columna, res);
    }
}
