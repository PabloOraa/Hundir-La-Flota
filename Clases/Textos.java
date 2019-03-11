package Clases;

/**
 * Textos de la aplicación
 * @Version 1.0
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public interface Textos 
{
    /**
     * Constante que marca el nombre del juego y de la aplicación
     */
    String NAMETEXT = "Hundir la flota";
    /**
     * Mensaje de bienvenida de la aplicación
     */
    String WELCOMETEXT = "Bienvenido a " + Textos.NAMETEXT;
    /**
     * Texto cuando falla el disparo
     */
    String FAIL = "Agua";
    /**
     * Texto cuando acierta el disparo
     */
    String RIGHT = "Tocado";
   /**
    * Texto cuando se acierta y disparo y la vida del barco llega a 0
    */
    String ENDOFSHIP = "Hundido";
    /**
     * Texto que representa el Portaaviones, el barco más grande de todos
     */
    String PORTAAVIONES = "Portaaviones";
    /**
     * Cadena que representa la figura del portaaviones
     */
    String PORTAAVIONESFIGURE = "PPPPP";
    /**
     * Texto que representa el Submarino
     */
    String SUBMARINO = "Submarino";
    /**
     * Cadena que representa la figura del Submarino
     */
    String SUBMARINOFIGURE = "SS";
    /**
     * Texto que representa el Buque
     */
    String BUQUE = "Buque";
    /**
     * Cadena que representa la figura del Buque
     */
    String BUQUEFIGURE = "BBB";
    /**
     * Texto que representa la Lancha
     */
    String LANCHA = "Lancha";
    /**
     * Cadena que representa la figura de la Lancha
     */
    String LANCHAFIGURE = "L";
    
    String ROW = "Elige la fila";
    /**
     * Caracter A que marca que no hay nada en esa casilla
     */
    char EMPTY = 'A';
    /**
     * Linea entre las casillas de los tableros de cada jugador
     */
    String LINE = "------------------------------";
    /**
     * Caracter que hace de barra vertical para simular el tablero
     */
    char VERTICALBAR = '|';
    /**
     * Caracter X utilizado en la matriz secundaria para marcar que ha fallado
     */
    char FAILLETTER = 'X';
    /**
     * Caracter O utilizado en la matriz secundaria para marcar que ha acertado
     */
    char RIGHTLETTER = 'O';
    /**
     * Caracter D que indica  que el barco situado en diagonal va hacia la derecha
     */
    char DIAGONAL = 'D';
    /**
     * Caracter I que indica  que el barco situado en diagonal va hacia la izquierda
     */
    char INVERSEDIAGONAL = 'I';
    /**
     * Caracter H que indica que el barco está situado en horizontal
     */
    char HORIZONTAL = 'H';
    /**
     * Caracter V que indica que el barco está situado en horizontal
     */
    char VERTICAL = 'V';
    /**
     * Texto que indica que no se ha encontrado el archivo
     */
    String FILENOTFOUND = "Archivo no encontrado";
    /**
     * Texto que indica que hay un error al leer el archivo
     */
    String READERROR = "Error al leer el archivo";
    /**
     * Texto que indica que el barco es demasiado grande para la posición elegida.
     */
    String TOOBIGSHIP = "El barco no se puede situar en la posición indicada";
    /**
     * 
     */
    String NOTFREEPOSITION = "Ya se ha disparado en esa posición";
}