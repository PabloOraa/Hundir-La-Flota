package Clases;

import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Clase Tablero que representa el tablero de un jugador
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
        {
            for (int i = 0; i < tablero1.length; i++)
                if(tablero1[i]=='A'){
                    String color = Textos.BLUE+tablero1[i];
                    System.out.print(Textos.VERTICALBAR + "" + color + "" + Textos.VERTICALBAR);
                }else{
                    System.out.print(Textos.VERTICALBAR + "" + tablero1[i] + "" + Textos.VERTICALBAR);
                }
                
            System.out.println("\n"+Textos.LINE);
        }
    }
    
    /**
     * Obtiene el caracter que haya en la posición seleccionada por el usuario.
     * <br/><br/>
     * Metodo utilizado de cara al usuario de la clase en función de los datos 
     * introducidos por el jugador
     * @param fila numero de fila entre 1 y 10 en la que se busca el valor
     * @param columna caracter de la matriz que marca la columna a buscar seleccionada por el usuario
     * @return char que puede ser 'A','P','B','S','L' en caso del tablero principal y 'X','A','O' en caso del tablero secundario
     */
    public char getPos(int fila, char columna)
    {
        return tablero[fila-1][getCoord(Character.toUpperCase(columna))];
    }
    
    /**
     * Obtiene el caracter que haya en la posición seleccionada por el usuario
     * @param fila numero de fila entre 1 y 10 en la que se busca el valor
     * @param columna numero de la matriz quemarca la columna a buscar
     * @return char que puede ser 'A','P','B','S','L' en caso del tablero principal y 'X','A','O' en caso del tablero secundario
     */
    public char getPos(int fila, int columna)
    {
        return tablero[fila-1][columna];
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
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            default: /*Se considera el Default la letra J*/
                return 9;
        }
    }
    
    /**
     * Comprueba que el barco que se quiere colocar en esa posición es posible,
     * es decir, que encaja en tamaño en la posición horizontal elegida.
     * @param tamBarco tamaño del barco a introducir
     * @param fila fila en la que se quiere colocar la primera posición del barco
     * @param columna columna en la que se desea introducir la posición del barco
     * @return true si es posible y false si no lo es
     */
    public boolean comprobarVertical(int tamBarco, int fila, char columna)
    {
        if(!(fila >= 0 && fila+tamBarco < tablero.length))
            return false;
        
        for (int i = fila-1, j = getCoord(Character.toUpperCase(columna)), k = 0; k < tamBarco+2; i++, k++)
            if(i > 0 && i < 10)
                if(tablero[i][j] != 'A')
                    return false;
        
        return true;
    }
    
    /**
     * Comprueba que el barco que se quiere colocar en esa posición es posible,
     * es decir, que encaja en tamaño en la posición vertical elegida.
     * @param tamBarco tamaño del barco a introducir
     * @param fila fila en la que se quiere colocar la primera posición del barco
     * @param columna columna en la que se desea introducir la posición del barco
     * @return true si es posible y false si no lo es
     */
    public boolean comprobarHorizontal(int tamBarco, int fila, char columna)
    {       
        if(!(getCoord(Character.toUpperCase(columna)) > 0 && getCoord(Character.toUpperCase(columna))+tamBarco < tablero.length))
            return false;
        
        for (int i = fila, j = getCoord(Character.toUpperCase(columna))-1, k = 0; k < tamBarco+2; j++, k++)
            if(j > 0 && j < 10)
                if(tablero[i][j] != 'A')
                    return false;
        
        return true;
    }
    
    /**
     * Comprueba que el barco que se quiere colocar en esa posición es posible,
     * es decir, que encaja en tamaño en la posición diagonal elegida y no hay
     * ningún valor en la misma casilla o contigua.
     * @param tamBarco tamaño del barco a introducir
     * @param fila fila en la que se quiere colocar la primera posición del barco
     * @param columna columna en la que se desea introducir la primera posición del barco
     * @param carDir caracter que marca si es Derecha o Izquiera para indicar el sentido de la diagonal
     * @return true si es posible y false si no lo es
     */
    public boolean comprobarDiagonal(int tamBarco, int fila, char columna, char carDir)
    {
        if(getCoord(Character.toUpperCase(columna)) > 0  && getCoord(Character.toUpperCase(columna))+tamBarco < tablero.length)
            if(carDir == Textos.DIAGONAL)
                if(!(fila >= 0  && fila+tamBarco < tablero.length))
                    return false;
            else
                if(!(fila < tablero.length  && fila-tamBarco >= 0))
                    return false;
        else
            return false;
        
        if(carDir == Textos.DIAGONAL)
        {    
            for (int i = fila-1, j = getCoord(Character.toUpperCase(columna))-1, k = 0; k < tamBarco+2; i++,j++, k++)
                if((i > 0 && i < 10) && (j > 0 && j < 10))
                    if(tablero[i][j] != 'A')
                        return false;
        }
        else
            for (int i = fila-1, j = getCoord(Character.toUpperCase(columna))-1, k = 0; k < tamBarco+2; i++,j++, k++)
                if((i > 0 && i < 10) && (j > 0 && j < 10))
                    if(tablero[i][j] != 'A')
                        return false;            
        return true;
    }
    
    /**
     * Inserta en el tablero en función de un archivo csv que se pase con la estructura
     * Direccion;Columna;Fila;Barco gracias a la libreria javacsv
     * @param archivo Archivo csv que incluye las coordenadas de cada uno de los barcos de un jugador.
     * @throws Clases.ExcepcionesBarco con el mensaje Archivo no encontrado en caso de que ocurra.
     */
    public void insertar(File archivo) throws ExcepcionesBarco
    {
        try
        {
            CsvReader csvr = new CsvReader(archivo.getAbsolutePath(), ';');
        
            csvr.readHeaders();
            
            while(csvr.readRecord())
            {
                char dir = csvr.get(0).charAt(0);
                int fila = Integer.valueOf(csvr.get(2));
                char columna = csvr.get(1).charAt(0);
                Barco brc = new Barco(Barco.obtenerNombre(csvr.get(3)), csvr.get(3).length());
            
                insertar(dir,fila,columna,brc);
            }
        } catch (FileNotFoundException ex )
        {
            System.err.println(Textos.FILENOTFOUND);
        } catch (IOException ex)
        {
            System.err.println(Textos.READERROR);
        }
    }

    /**
     * Inserta el barco en fila y columna indicada por el usuario para un barco 
     * en concreto en la dirección en cuestión
     * @param dir Char que indica la dirección que quiere la dirección en la que se introduce el barco
     * @param fila Numero que indica la fila de la primera posición del barco
     * @param columna Caracter que indica la primera posició del barco
     * @param brc Barco a introducir por el usuario
     * @throws ExcepcionesBarco 
     */
    public void insertar(char dir, int fila, char columna, Barco brc) throws ExcepcionesBarco
    {
        fila = fila-1; //Ajusta la fila elegida a la matriz
        if(Character.toUpperCase(dir) == Textos.HORIZONTAL)
            if(comprobarHorizontal(brc.getFigure().length(), fila, columna))
                insertarHorizontal(fila, Character.toUpperCase(columna), brc);
            else
                throw new ExcepcionesBarco(Textos.TOOBIGSHIP);
        else if(Character.toUpperCase(dir) == Textos.VERTICAL)
            if(comprobarVertical(brc.getFigure().length(), fila, columna))
                insertarVertical(fila,Character.toUpperCase(columna),brc);
            else
                throw new ExcepcionesBarco(Textos.TOOBIGSHIP);
        else 
            if(comprobarDiagonal(brc.getFigure().length(), fila, columna, Character.toUpperCase(dir)))
                if(Character.toUpperCase(dir) == Textos.DIAGONAL)
                    insertarDiagonal(fila,Character.toUpperCase(columna),brc);
                else
                    insertarDiagonalInversa(fila,Character.toUpperCase(columna),brc);
            else
                throw new ExcepcionesBarco(Textos.TOOBIGSHIP);
    }

    /**
     * Inserta el barco en la matriz con dirección horizontal
     * @param fila fila donde se inserta el barco
     * @param columna columna donde se inserta el barco
     * @param brc Barco a insertar
     */
    private void insertarHorizontal(int fila, char columna, Barco brc)
    {
        for (int i = getCoord(columna), j = 0; j < brc.getFigure().length(); i++,j++)
            tablero[fila][i] = brc.getFigure().charAt(0);
    }

    /**
     * Inserta el barco en la matriz con dirección vertical
     * @param fila fila donde se inserta el barco
     * @param columna columna donde se inserta el barco
     * @param brc Barco a insertar
     */
    private void insertarVertical(int fila, char columna, Barco brc)
    {
        for (int i = fila, j = 0; j < brc.getFigure().length(); i++,j++)
            tablero[i][getCoord(columna)] = brc.getFigure().charAt(0);
    }

    /**
     * Inserta el barco en la matriz con dirección diagonal hacia la derecha
     * @param fila fila donde se inserta el barco
     * @param columna columna donde se inserta el barco
     * @param brc Barco a insertar
     */
    private void insertarDiagonal(int fila, char columna, Barco brc)
    {
        for (int i = fila, j = getCoord(columna), k = 0; k < brc.getFigure().length(); i++,j++, k++)
            tablero[i][j] = brc.getFigure().charAt(0);
    }

    /**
     * Inserta el barco en la matriz con dirección diagonal inversa (izquierda)
     * @param fila fila donde se inserta el barco
     * @param columna columna donde se inserta el barco
     * @param brc Barco a insertar
     */
    private void insertarDiagonalInversa(int fila, char columna, Barco brc)
    {
        for (int i = fila, j = getCoord(columna), k = 0; k < brc.getFigure().length(); i--,j--, k++)
            tablero[i][j] = brc.getFigure().charAt(0);
    }
    
    /**
     * Guarda en el tablero el resultado del disparo en la posición indicada con
     * X en caso de haber fallado y O en caso de acertar.
     * @param fila Fila a introducir el resultado.
     * @param columna Columna a introducir el resultado.
     * @param res X u O en caso del restulado del disparo.
     */
    public void insertarResultado(int fila, int columna, char res)
    {
        tablero[fila][columna] = res;
    }
}
