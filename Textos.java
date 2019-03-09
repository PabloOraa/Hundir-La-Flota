package Clases;

/**
 * Yextos de la aplicación
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
    String ENDOFSHIP = "HUNDIDO";
    String ROW = "Elige la fila";
    /**
     * Caracters que marca que no hay nada en esa casilla
     */
    char EMPTY = 'A';
    /**
     * Linea entre las casillas de los tableros de cada jugador
     */
    String LINE = "-----------------------------";
    /**
     * Caracter utilizado en la matriz secundaria para marcar que ha fallado
     */
    char FAILLETTER = 'X';
    /**
     * Caracter utilizado en la matriz secundaria para marcar que ha acertado
     */
    char RIGHTLETTER = 'O';
    /**
     * Caracter que indica  que el barco situado en diagonal va hacia la derecha
     */
    char DIAGONAL = 'D';
    /**
     * Caracter que indica  que el barco situado en diagonal va hacia la izquierda
     */
    char INVERSEDIAGONAL = 'I';
}
