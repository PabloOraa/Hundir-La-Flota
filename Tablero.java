package Clases;

import com.csvreader.CsvReader;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 * Clase Tablero que representa el tablero de un jugador
 * @Version 1.4
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class Tablero implements Serializable
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
     * Obtiene el caracter que haya en la posición seleccionada por el usuario.
     * <br/><br/>
     * Metodo utilizado de cara al usuario de la clase en función de los datos 
     * introducidos por el jugador
     * @param fila numero de fila entre 0 y 9 en la que se busca el valor
     * @param columna caracter de la matriz que marca la columna a buscar seleccionada por el usuario
     * @return char que puede ser 'A','P','B','S','L' en caso del tablero principal y 'X','A','O' en caso del tablero secundario
     */
    public char getPos(int fila, char columna)
    {
        return tablero[fila][getCoord(Character.toUpperCase(columna))];
    }
    
    /**
     * Obtiene el caracter que haya en la posición seleccionada por el usuario
     * @param fila numero de fila entre 0 y 9 en la que se busca el valor
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
            case Textos.COLUMN1:
                return 0;
            case Textos.COLUMN2:
                return 1;
            case Textos.COLUMN3:
                return 2;
            case Textos.COLUMN4:
                return 3;
            case Textos.COLUMN5:
                return 4;
            case Textos.COLUMN6:
                return 5;
            case Textos.COLUMN7:
                return 6;
            case Textos.COLUMN8:
                return 7;
            case Textos.COLUMN9:
                return 8;
            case Textos.COLUMN10:
                return 9;
            default:
                return -1;
        }
    }
    
    /**
     * Comprueba que el barco que se quiere colocar en esa posición es posible,
     * es decir, que encaja en tamaño en la posición horizontal elegida.
     * @param brc Barco a introducir
     * @param fila fila entre 0 y 9 en la que se quiere colocar la primera posición del barco
     * @param columna columna en la que se desea introducir la posición del barco
     * @return true si es posible y false si no lo es
     */
    private boolean comprobarVertical(Barco brc, int fila, char columna)
    {
        int tamBarco = brc.getLength();
        char Brc = ' ';
        
        for (int i = fila-2, j = getCoord(Character.toUpperCase(columna)), k = 0; k < tamBarco+4; i++, k++)
            if(i >= 0 && i < 10)
                if((i == fila-2 || i == fila + tamBarco))
                {    
                    if(tablero[i][j] != Textos.EMPTY)
                        Brc = tablero[i][j];
                }
                else if((i == fila -1 || i == fila + tamBarco+1))
                {
                    if((Brc != ' ' && tablero[i][j] == Brc) || tablero[i][j] == Textos.LANCHAFIGURE)
                        return false;
                }
                else 
                    if(tablero[i][j] != Textos.EMPTY)
                        return false;
        
        return true;
    }
    
    /**
     * Comprueba que el barco que se quiere colocar en esa posición es posible,
     * es decir, que encaja en tamaño en la posición vertical elegida.
     * @param brc Barco a introducir
     * @param fila fila entre 0 y 9 en la que se quiere colocar la primera posición del barco
     * @param columna columna en la que se desea introducir la posición del barco
     * @return true si es posible y false si no lo es
     */
    private boolean comprobarHorizontal(Barco brc, int fila, char columna)
    {      
        int tamBarco = brc.getLength();
        char Brc = ' ';

        for (int i = fila, j = getCoord(Character.toUpperCase(columna))-2, k = 0; k < tamBarco+4; j++, k++)
            if(j >= 0 && j < 10)
                if(j == getCoord(Character.toUpperCase(columna)) -2 || j == getCoord(Character.toUpperCase(columna)) + tamBarco)
                {    
                    if(tablero[i][j] != Textos.EMPTY)
                        Brc = tablero[i][j];
                }
                else if(j == getCoord(Character.toUpperCase(columna)) -1 || j == getCoord(Character.toUpperCase(columna))+tamBarco+1)
                {
                    if((Brc != ' ' && tablero[i][j] == Brc) || tablero[i][j] == Textos.LANCHAFIGURE)
                        return false;
                }
                else 
                    if(tablero[i][j] != Textos.EMPTY)
                        return false;
        
        return true;
    }
    
    /**
     * Comprueba que el barco que se quiere colocar en esa posición es posible,
     * es decir, que encaja en tamaño en la posición diagonal elegida y no hay
     * ningún valor en la misma casilla o contigua.
     * @param brc Barco a introducir
     * @param fila fila entre 0 y 9 en la que se quiere colocar la primera posición del barco
     * @param columna columna en la que se desea introducir la primera posición del barco
     * @param carDir caracter que marca si es Derecha o Izquiera para indicar el sentido de la diagonal
     * @return true si es posible y false si no lo es
     */
    private boolean comprobarDiagonal(Barco brc, int fila, char columna, char carDir)
    {
        int tamBarco = brc.getLength();
        char Brc = ' ';
        
        if(carDir == Textos.DIAGONAL)
        {    
            for (int i = fila-2, j = getCoord(Character.toUpperCase(columna))-2, k = 0; k < tamBarco+4; i++,j++, k++)
                if((i >= 0 && i < 10) && (j >= 0 && j < 10))
                    if(i == fila -2 || i == fila+tamBarco)
                    {    
                        if(tablero[i][j] != Textos.EMPTY)
                            Brc = tablero[i][j];
                    }
                    else if(i == fila -1 || i == fila+tamBarco+1)
                    {
                        if((Brc != ' ' && tablero[i][j] == Brc) || tablero[i][j] == Textos.LANCHAFIGURE)
                            return false;
                    }
                    else 
                        if(tablero[i][j] != Textos.EMPTY)
                            return false;
        }
        else
            for (int i = fila-2, j = getCoord(Character.toUpperCase(columna))+2, k = 0; k < tamBarco+4; i++,j--, k++)
                if((i > 0 && i < 10) && (j > 0 && j < 10))
                    if((i == fila -2 || i == fila + tamBarco))
                    {    
                        if(tablero[i][j] != Textos.EMPTY)
                            Brc = tablero[i][j];
                    }
                    else if(i == fila - 1 || i == fila + tamBarco + 1)
                    {
                        if((Brc != ' ' && tablero[i][j] == Brc) || tablero[i][j] == Textos.LANCHAFIGURE)
                            return false;
                    }
                    else 
                        if(tablero[i][j] != Textos.EMPTY)
                            return false;           
        return true;
    }
    
    /**
     * Comprueba que la lancha que se quiere colocar en esa posición es posible,
     * es decir, que encaja en tamaño en la posición elegida y no hay ningún valor
     * en la misma casilla o contigua.
     * @param fila fila entre 0 y 9 en la que se quiere colocar la primera posición del barco
     * @param columna columna en la que se desea introducir la primera posición del barco
     * @return true si es posible y false si no lo es
     */
    private boolean comprobarLancha(Barco brc, int fila, char columna) {
        if(!comprobarVertical(brc, fila, columna))
            return false;
        if(!comprobarHorizontal(brc,fila,columna))
            return false;
        if(!comprobarDiagonal(brc,fila,columna,Textos.DIAGONAL))
            return false;
        return comprobarDiagonal(brc,fila,columna,Textos.INVERSEDIAGONAL);
    }
    
    /**
     * Comprueba que se pueda introducir el barco y que no se salga del tablero
     * cuando se hace en diagonal o en diagonal inversa.
     * @param brc Barco a introducir.
     * @param fila Fila entre 0 y 9 que marca el inicio del barco.
     * @param columna Columna entre A y J que marca el inicio del barco.
     * @param carDir Dirección Diagonal (D) y Diagonal Inversa (I).
     * @return True si es posible y false si no.
     */
    private boolean comprobarDiagonalTamanio(Barco brc, int fila, char columna, char carDir)
    {
        int tamBarco = brc.getLength();
        if(fila >= 0  && fila+tamBarco-1 < tablero.length)
            if(carDir == Textos.DIAGONAL)
            {
                if(!(getCoord(Character.toUpperCase(columna)) >= 0  && getCoord(Character.toUpperCase(columna))+tamBarco-1 < tablero.length))
                    return false;
            }
            else
            {
                if(!(getCoord(Character.toUpperCase(columna)) < tablero.length  && getCoord(Character.toUpperCase(columna))-tamBarco+1 >= 0))
                    return false;
            }
        else
            return false;  
        
        return true;
    }
    
    /**
     * Comprueba que se pueda introducir el barco y que no se salga del tablero
     * cuando se hace en vertical.
     * @param brc Barco a introducir.
     * @param fila Fila entre 0 y 9 que marca el inicio del barco.
     * @param columna Columna entre A y J que marca el inicio del barco.
     * @return True si es posible y false si no.
     */
    private boolean comprobarVerticalTamanio(Barco brc, int fila)
    {
        int tamBarco = brc.getLength();
        //Se le resta 1 al tamaño del barco porque la casilla inicial cuenta
        return (fila >= 0 && fila+tamBarco-1 < tablero.length);//fila + tambBarco-1 porque se cuenta la fila pasada.
    }
    
    /**
     * Comprueba que se pueda introducir el barco y que no se salga del tablero
     * cuando se hace en horizontal.
     * @param brc Barco a introducir.
     * @param fila Fila entre 0 y 9 que marca el inicio del barco.
     * @param columna Columna entre A y J que marca el inicio del barco.
     * @return True si es posible y false si no.
     */
    private boolean comprobarHorizontalTamanio(Barco brc, char columna)
    {
        int tamBarco = brc.getLength();
        //Se le resta 1 al tamaño del barco porque la casilla inicial cuenta
        return getCoord(Character.toUpperCase(columna)) >= 0 && getCoord(Character.toUpperCase(columna))+tamBarco-1 < tablero.length;
    }
    
    /**
     * Inserta en el tablero en función de un archivo csv que se pase con la estructura
     * Direccion;Columna;Fila;Barco gracias a la libreria javacsv
     * @param archivo Archivo csv que incluye las coordenadas de cada uno de los barcos de un jugador.
     * @return True si se ha insertado correctamente y false si no.
     * @throws Clases.ExcepcionesBarco con el mensaje Archivo no encontrado en caso de que ocurra.
     */
    public boolean insertar(File archivo) throws ExcepcionesBarco
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
            return true;
        } catch (FileNotFoundException ex )
        {
            System.err.println(Textos.FILENOTFOUND);
        } catch (IOException ex)
        {
            System.err.println(Textos.READERROR);
        }
        return false;
    }

    /**
     * Inserta el barco en fila y columna indicada por el usuario para un barco 
     * en concreto en la dirección en cuestión
     * @param dir Char que indica la dirección que quiere la dirección en la que se introduce el barco
     * @param fila Numero entre 1 y 10 que indica la fila de la primera posición del barco
     * @param columna Caracter que indica la primera posició del barco
     * @param brc Barco a introducir por el usuario
     * @return True si se ha insertado
     * @throws ExcepcionesBarco 
     */
    public boolean insertar(char dir, int fila, char columna, Barco brc) throws ExcepcionesBarco
    {
        fila = fila-1; //Ajusta la fila elegida a la matriz
        if(brc.getName().toUpperCase().equals(Textos.LANCHA.toUpperCase()))
            if(comprobarHorizontalTamanio(brc,columna) && comprobarVerticalTamanio(brc,fila) 
                    && comprobarDiagonalTamanio(brc,fila,columna, Textos.DIAGONAL) 
                    && comprobarDiagonalTamanio(brc,fila,columna,Textos.INVERSEDIAGONAL))
                if(comprobarLancha(brc,fila,columna))
                {
                    insertarHorizontal(fila, Character.toUpperCase(columna), brc);
                    return true;
                }
                else
                    throw new ExcepcionesBarco(Textos.SQUARENOTFREE);
            else
                throw new ExcepcionesBarco(Textos.TOOBIGSHIP);
        else
        {
            if(Character.toUpperCase(dir) == Textos.HORIZONTAL)
                if(comprobarHorizontalTamanio(brc,columna))
                    if(comprobarHorizontal(brc, fila, columna))
                    {
                        insertarHorizontal(fila, Character.toUpperCase(columna), brc);
                        return true;
                    }
                    else
                        throw new ExcepcionesBarco(Textos.SQUARENOTFREE);
                else
                    throw new ExcepcionesBarco(Textos.TOOBIGSHIP);
            else if(Character.toUpperCase(dir) == Textos.VERTICAL)
                if(comprobarVerticalTamanio(brc,fila))
                    if(comprobarVertical(brc, fila, columna))
                    {
                        insertarVertical(fila,Character.toUpperCase(columna),brc);
                        return true;
                    }
                    else
                        throw new ExcepcionesBarco(Textos.SQUARENOTFREE);
                else
                    throw new ExcepcionesBarco(Textos.TOOBIGSHIP);
            else    
                if(comprobarDiagonalTamanio(brc,fila,columna, Character.toUpperCase(dir)))
                    if(comprobarDiagonal(brc, fila, columna, Character.toUpperCase(dir)))
                        if(Character.toUpperCase(dir) == Textos.DIAGONAL)
                        {    
                            insertarDiagonal(fila,Character.toUpperCase(columna),brc);
                            return true;
                        }
                        else
                        {
                            insertarDiagonalInversa(fila,Character.toUpperCase(columna),brc);
                            return true;
                        }
                    else
                        throw new ExcepcionesBarco(Textos.SQUARENOTFREE);    
                else
                    throw new ExcepcionesBarco(Textos.TOOBIGSHIP);
        }
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
        for (int i = fila, j = getCoord(columna), k = 0; k < brc.getFigure().length(); i++,j--, k++)
            tablero[i][j] = brc.getFigure().charAt(0);
    }
    
    /**
     * Guarda en el tablero el resultado del disparo en la posición indicada con
     * X en caso de haber fallado y O en caso de acertar.
     * @param fila Fila entre 0 y 9 a introducir el resultado.
     * @param columna Columna a introducir el resultado.
     * @param res X u O en caso del restulado del disparo.
     */
    public void insertarResultado(int fila, int columna, char res)
    {
        tablero[fila][columna] = res;
    }

    /**
     * Imprime el tablero tal cual se encuente en ese momento
     * @param jtp JTextPane que incluirá el tablero
     * @return Cadena con el tablero
     */
    public JTextPane imprimirTableroInterfaz(JTextPane jtp)
    {
        jtp.setText("");
        for (char[] tablero1 : tablero)
        {
            appendToPane(jtp,"  ", Color.BLACK);
            for (int i = 0; i < tablero1.length; i++)
                switch (tablero1[i])
                {
                    case Textos.EMPTY:
                        appendToPane(jtp,String.valueOf(Textos.VERTICALBAR), Color.BLACK);
                        appendToPane(jtp,String.valueOf(tablero1[i]), Color.BLUE);
                        appendToPane(jtp,String.valueOf(Textos.VERTICALBAR), Color.BLACK);
                        break;
                    case Textos.FAILLETTER:
                        appendToPane(jtp,String.valueOf(Textos.VERTICALBAR), Color.BLACK);
                        appendToPane(jtp,String.valueOf(tablero1[i]), Color.RED);
                        appendToPane(jtp,String.valueOf(Textos.VERTICALBAR), Color.BLACK);
                        break;
                    case Textos.RIGHTLETTER:
                        appendToPane(jtp,String.valueOf(Textos.VERTICALBAR), Color.BLACK);
                        appendToPane(jtp,String.valueOf(tablero1[i]), Color.GREEN);
                        appendToPane(jtp,String.valueOf(Textos.VERTICALBAR), Color.BLACK);
                        break;
                    default:
                        appendToPane(jtp,String.valueOf(Textos.VERTICALBAR), Color.BLACK);
                        appendToPane(jtp,String.valueOf(tablero1[i]), Color.BLACK);
                        appendToPane(jtp,String.valueOf(Textos.VERTICALBAR), Color.BLACK);
                        break;
                }
            appendToPane(jtp,"\n\n", Color.BLACK);
        }
        return jtp;
    }
    
    /**
     * Método para introducir en el JTextPane un texto o caracter en un color determinado.
     * Realiza un append al rext que ya hubiese en el JTextPane.
     * @param tp JTextPane donde se introduce el text
     * @param msg Cadena a introducir en el JTextPane
     * @param c Color en el que se introduce el texto
     */
    private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }
}
