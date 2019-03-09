package Clases;

/**
 * Clase jugador que representa el tablero de un jugador
 * @Version 1.0
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class Tablero
{
    /**
     * Matriz tablero que guarda los datos del los barcos o de los tiros del
     * jugador
     */
    private char[][] tablero; 

    /**
     * Constructor de la clase tablero que crea e incializa el tablero a Agua,
     * 'A' considerándose que fueese vacio.
     */
    public Tablero()
    {
        tablero = new char[10][10];
        crearTablero();
    }
    
    /**
     * Rellenamos el tablero con la letra A indicando que aghí hay Agua, siendo 
     * un tablero vacio.
     */
    private void crearTablero()
    {
        for (char[] tablero1 : tablero)
            for (int j = 0; j < tablero1.length; j++)
                tablero1[j] = Textos.EMPTY;
    }
    
    /**
     * Imprime el tablero tal cual se encuente en ese momento
     */
    public void imprimirTablero()
    {
        System.out.println(Textos.LINE);
        for (char[] tablero1 : tablero)
            for (int i = 0; i < tablero1.length; i++)
                System.out.println(tablero1[i]);
    }
    
    /**
     * Obtiene el caracter que haya en la posición seleccionada por el usuario.
     * <br/><br/>
     * Metodo utilizado de cara al usuario de la clase en función de los datos 
     * introducidos por el jugador
     * @param fila numero de fila en la que se busca el valor
     * @param columna caracter de la matriz que marca la columna a buscar seleccionada por el usuario
     * @return char que puede ser 'A','P','B','S','L' en caso del tablero principal y 'X','A','O' en caso del tablero secundario
     */
    public char getPos(int fila, char columna)
    {
        return tablero[fila][getCoord(columna)];
    }
    
    /**
     * Obtiene el caracter que haya en la posición seleccionada por el usuario
     * @param fila numero de fila en la que se busca el valor
     * @param columna numero de la matriz quemarca la columna a buscar
     * @return char que puede ser 'A','P','B','S','L' en caso del tablero principal y 'X','A','O' en caso del tablero secundario
     */
    public char getPos(int fila, int columna)
    {
        return tablero[fila][columna];
    }
    
    /**
     * Obtiene la posición en la matriz entre 1 y 10 en función de la letra A-J 
     * que se pase por parámetro
     * @param columna Columna seleccionada por el usuario
     * @return int que representa la posición de la letra en la Matriz
     */
    public int getCoord(char columna)
    {
        switch(columna)
        {
            case 'A':
                return 1;
            case 'B':
                return 2;
            case 'C':
                return 3;
            case 'D':
                return 4;
            case 'E':
                return 5;
            case 'F':
                return 6;
            case 'G':
                return 7;
            case 'H':
                return 8;
            case 'I':
                return 9;
            default: /*Se considera el Default la letra J*/
                return 10;
        }
    }
    
    /**
     * Comprueba que el barco que se quiere colocar en esa posición es posible,
     * es decir, que encaja en tamaño en la posición horizontal elegida.
     * @param tamBarco tamaño del barco a introducir
     * @param fila fila en la que se quiere colocar la primera posición del barco
     * @return true si es posible y false si no lo es
     */
    public boolean comprobarHorizontal(int tamBarco, int fila)
    {
        return fila > 0 && fila+tamBarco < tablero.length-1;
    }
    
    /**
     * Comprueba que el barco que se quiere colocar en esa posición es posible,
     * es decir, que encaja en tamaño en la posición vertical elegida.
     * @param tamBarco tamaño del barco a introducir
     * @param columna columna en la que se desea introducir la posición del barco
     * @return true si es posible y false si no lo es
     */
    public boolean comprobarVertical(int tamBarco, char columna)
    {        
        return getCoord(columna) > 0 && getCoord(columna)+tamBarco < tablero.length;
    }
    
    /**
     * Comprueba que el barco que se quiere colocar en esa posición es posible,
     * es decir, que encaja en tamaño en la posición diagonal elegida.
     * @param tamBarco tamaño del barco a introducir
     * @param fila fila en la que se quiere colocar la primera posición del barco
     * @param columna columna en la que se desea introducir la primera posición del barco
     * @param carDir caracter que marca si es Derecha o Izquiera para indicar el sentido de la diagonal
     * @return true si es posible y false si no lo es
     */
    public boolean comprobarDiagonal(int tamBarco, int fila, char columna, char carDir)
    {
        if(getCoord(columna) > 0  && getCoord(columna)+tamBarco < tablero.length)
            if(carDir == Textos.DIAGONAL)
                return (fila > 0  && fila+tamBarco < tablero.length);
            else
                return (fila < tablero.length  && fila-tamBarco > 0);
        else
            return false;
    }
}
