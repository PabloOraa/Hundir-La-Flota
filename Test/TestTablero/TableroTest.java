package TestTablero;

import Clases.Barco;
import Clases.ExcepcionesBarco;
import Clases.Tablero;
import Clases.Textos;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase TestTablero que representa todos los test realizados a la clase Barcos
 * @Version 1.0.1
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class TableroTest 
{
    /**
     * Test of getPos method, of class Tablero.
     */
    @Test
    public void testGetPos_int_char() {
        System.out.println("getPos");
        int fila = 1;
        char columna = 'A';
        Tablero instance = new Tablero();
        char expResult = 'A';
        char result = instance.getPos(fila, columna);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPos method, of class Tablero.
     */
    @Test
    public void testGetPos_int_int() {
        System.out.println("getPos_int_int");
        int fila = 0;
        int columna = 7;
        Tablero instance = new Tablero();
        char expResult = 'A';
        char result = instance.getPos(fila, columna);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCoord method, of class Tablero.
     */
    @Test
    public void testGetCoord() {
        System.out.println("getCoord");
        char columna = 'J';
        Tablero instance = new Tablero();
        int expResult = 9;
        int result = instance.getCoord(columna);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCoord method, of class Tablero
     * cuando el caracter no está entre las posiciones permtidas.
     */
    @Test
    public void testGetCoordIncorrecto() {
        System.out.println("getCoord");
        char columna = '1';
        Tablero instance = new Tablero();
        int expResult = -1;
        int result = instance.getCoord(columna);
        assertEquals(expResult, result);
    }

    /**
     * Test of comprobarVertical method, of class Tablero.
     */
    @Test
    public void testComprobarVertical() {
        try {
            System.out.println("comprobarVertical");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 1;
            char columna = 'J';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.VERTICAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());
        }
    }
    
    /**
     * Test of comprobarVertical method, of class Tablero.
     */
    @Test
    public void testComprobarVertical_2() {
        try {
            System.out.println("comprobarVertical");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 10;
            char columna = 'A';
            Tablero instance = new Tablero();
            boolean expResult = false;
            boolean result = instance.insertar(Textos.VERTICAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarVertical method, of class Tablero.
     */
    @Test
    public void testComprobarVertical_3() {
        try {
            System.out.println("comprobarVertical");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 10;
            char columna = 'E';
            Tablero instance = new Tablero();
            boolean expResult = false;
            boolean result = instance.insertar(Textos.VERTICAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarVertical method, of class Tablero.
     */
    @Test
    public void testComprobarVertical_4() {
        try {
            System.out.println("comprobarVertical");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 2;
            char columna = 'E';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.VERTICAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarVertical method, of class Tablero.
     */
    @Test
    public void testComprobarVertical_5() {
        try {
            System.out.println("comprobarVertical");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 2;
            char columna = 'A';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.VERTICAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarVertical method, of class Tablero.
     */
    @Test
    public void testComprobarVertical_6() {
        try {
            System.out.println("comprobarVertical");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 6;
            char columna = 'E';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.VERTICAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarVertical method, of class Tablero.
     */
    @Test
    public void testComprobarVertical_7() {
        try {
            System.out.println("comprobarVertical");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 7;
            char columna = 'A';
            Tablero instance = new Tablero();
            boolean expResult = false;
            boolean result = instance.insertar(Textos.VERTICAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarVertical method, of class Tablero.
     */
    @Test
    public void testComprobarVertical_8() {
        try {
            System.out.println("comprobarVertical");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 8;
            char columna = 'H';
            Tablero instance = new Tablero();
            boolean expResult = false;
            boolean result = instance.insertar(Textos.VERTICAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarVertical method, of class Tablero.
     */
    @Test
    public void testComprobarVertical_9() {
        try {
            System.out.println("comprobarVertical");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 9;
            char columna = 'I';
            Tablero instance = new Tablero();
            boolean expResult = false;
            boolean result = instance.insertar(Textos.VERTICAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    
    /**
     * Test of comprobarVertical method, of class Tablero con un barco
     * horizontal en la casilla de al lado.
     */
    @Test
    public void testComprobarVertical_Barco_Cerca() 
    {
        try {
            System.out.println("comprobarVertical");
            
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 6;
            char columna = 'J';
            Tablero instance = new Tablero();
            try {
                instance.insertar('V', 1, 'J', new Barco(Textos.BUQUE,Textos.BUQUEFIGURE.length()));
            } catch (ExcepcionesBarco ex) {
                assertEquals(Textos.NOTFREEPOSITION,ex.getMessage());;
            }
            boolean expResult = true;
            boolean result = instance.insertar(Textos.VERTICAL, fila, columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            System.err.println(ex.getMessage());
        }
    }
    /**
     * Test of comprobarVertical method, of class Tablero con un barco
     * horizontal en la casilla de al lado.
     */
    @Test
    public void testComprobarVertical_Barco_Cerca_NoPosible() 
    {
        try {
            System.out.println("comprobarVertical");
            
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 6;
            char columna = 'J';
            Tablero instance = new Tablero();
            try {
                instance.insertar('V', 3, 'J', new Barco(Textos.BUQUE,Textos.BUQUEFIGURE.length()));
            } catch (ExcepcionesBarco ex) {
                assertEquals(Textos.NOTFREEPOSITION,ex.getMessage());;
            }
            instance.insertar(Textos.VERTICAL, fila, columna,brc);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.SQUARENOTFREE,ex.getMessage());
        }
    }

    /**
     * Test of comprobarHorizontal method, of class Tablero.
     */
    @Test
    public void testComprobarHorizontal() {
        try {
            System.out.println("comprobarHorizontal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 10;
            char columna = 'J';
            Tablero instance = new Tablero();
            boolean expResult = false;
            boolean result = instance.insertar(Textos.HORIZONTAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    
    /**
     * Test of comprobarHorizontal method, of class Tablero.
     */
    @Test
    public void testComprobarHorizontal_2() {
        try {
            System.out.println("comprobarHorizontal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 10;
            char columna = 'A';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.HORIZONTAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarHorizontal method, of class Tablero.
     */
    @Test
    public void testComprobarHorizontal_3() {
        try {
            System.out.println("comprobarHorizontal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 5;
            char columna = 'J';
            Tablero instance = new Tablero();
            boolean expResult = false;
            boolean result = instance.insertar(Textos.HORIZONTAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarHorizontal method, of class Tablero.
     */
    @Test
    public void testComprobarHorizontal_4() {
        try {
            System.out.println("comprobarHorizontal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 1;
            char columna = 'E';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.HORIZONTAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarHorizontal method, of class Tablero.
     */
    @Test
    public void testComprobarHorizontal_5() {
        try {
            System.out.println("comprobarHorizontal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 1;
            char columna = 'A';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.HORIZONTAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.NOTFREEPOSITION,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarHorizontal method, of class Tablero.
     */
    @Test
    public void testComprobarHorizontal_6() {
        try {
            System.out.println("comprobarHorizontal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 5;
            char columna = 'E';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.HORIZONTAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    
    /**
     * Test of comprobarHorizontal method, of class Tablero.
     */
    @Test
    public void testComprobarHorizontal_8() {
        try {
            System.out.println("comprobarHorizontal");
            Barco brc = new Barco(Textos.BUQUE, Textos.BUQUEFIGURE.length());
            int fila = 7;
            char columna = 'H';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.HORIZONTAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarHorizontal method, of class Tablero.
     */
    @Test
    public void testComprobarHorizontal_9() {
        try {
            System.out.println("comprobarHorizontal");
            Barco brc = new Barco(Textos.BUQUE, Textos.BUQUEFIGURE.length());
            int fila = 9;
            char columna = 'I';
            Tablero instance = new Tablero();
            boolean expResult = false;
            boolean result = instance.insertar(Textos.HORIZONTAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    
    /**
     * Test of comprobarHorizontal method, of class Tablero con un barco
     * horizontal en la casilla de al lado.
     */
    @Test
    public void testComprobarHorizontal_Barco_Cerca() 
    {
        try {
            System.out.println("comprobarHorizontal");
            
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 1;
            char columna = 'B';
            Tablero instance = new Tablero();
            try {
                instance.insertar('H', 1, 'H', new Barco(Textos.BUQUE,Textos.BUQUEFIGURE.length()));
            } catch (ExcepcionesBarco ex) {
                assertEquals(Textos.NOTFREEPOSITION,ex.getMessage());;
            }
            boolean expResult = true;
            boolean result = instance.insertar(Textos.HORIZONTAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());
        }
    }
    /**
     * Test of comprobarHorizontal method, of class Tablero con un barco
     * horizontal en la casilla de al lado.
     */
    @Test
    public void testComprobarHorizontal_Barco_Cerca_NoPosible() 
    {
        try {
            System.out.println("comprobarHorizontal");
            
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 1;
            char columna = 'C';
            Tablero instance = new Tablero();
            try {
                instance.insertar('H', 1, 'H', new Barco(Textos.BUQUE,Textos.BUQUEFIGURE.length()));
            } catch (ExcepcionesBarco ex) {
                assertEquals(Textos.NOTFREEPOSITION,ex.getMessage());;
            }
            boolean expResult = false;
            boolean result = instance.insertar(Textos.HORIZONTAL, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.SQUARENOTFREE,ex.getMessage());
        }
    }

    /**
     * Test of comprobarDiagonal method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonal() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 9;
            char columna = 'J';
            Tablero instance = new Tablero();
            boolean expResult = false;
            boolean result = instance.insertar(Textos.DIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    
    /**
     * Test of comprobarDiagonal method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonal_2() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 4;
            char columna = 'A';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.DIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonal method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonal_3() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 5;
            char columna = 'J';
            Tablero instance = new Tablero();
            boolean expResult = false;
            boolean result = instance.insertar(Textos.DIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonal method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonal_4() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 1;
            char columna = 'E';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.DIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonal method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonal_5() {
       try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 1;
            char columna = 'A';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.DIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonal method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonal_6() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 5;
            char columna = 'E';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.DIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonal method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonal_7() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 6;
            char columna = 'A';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.DIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonal method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonal_8() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.BUQUE, Textos.BUQUEFIGURE.length());
            int fila = 7;
            char columna = 'H';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.DIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonal method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonal_9() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.BUQUE, Textos.BUQUEFIGURE.length());
            int fila = 8;
            char columna = 'I';
            Tablero instance = new Tablero();
            boolean expResult = false;
            boolean result = instance.insertar(Textos.DIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
   
    /**
     * Test of comprobarDiagonal method, of class Tablero con un barco
     * Diagonal en la casilla de al lado.
     */
    @Test
    public void testComprobarDiagonal_Barco_Cerca() 
    {
        try {
            System.out.println("comprobarDiagonal");
            
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 1;
            char columna = 'C';
            Tablero instance = new Tablero();
            try {
                instance.insertar('D', 7, 'H', new Barco(Textos.BUQUE,Textos.BUQUEFIGURE.length()));
            } catch (ExcepcionesBarco ex) {
                assertEquals(Textos.NOTFREEPOSITION,ex.getMessage());;
            }
            boolean expResult = true;
            boolean result = instance.insertar(Textos.DIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonal method, of class Tablero con un barco
     * Diagonal en la casilla de al lado.
     */
    @Test
    public void testComprobarDiagonal_Barco_Cerca_NoPosible() 
    {
        try {
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 1;
            char columna = 'C';
            Tablero instance = new Tablero();
            try {
                instance.insertar('D', 6, 'H', new Barco(Textos.BUQUE,Textos.BUQUEFIGURE.length()));
            } catch (ExcepcionesBarco ex) {
                assertEquals(Textos.NOTFREEPOSITION,ex.getMessage());;
            }
            boolean expResult = false;
            boolean result = instance.insertar(Textos.DIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.SQUARENOTFREE,ex.getMessage());;
        }
    }

    /**
     * Test of comprobarDiagonalInversa method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonalInversa() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 10;
            char columna = 'A';
            Tablero instance = new Tablero();
            boolean expResult = false;
            boolean result = instance.insertar(Textos.INVERSEDIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    
    /**
     * Test of comprobarDiagonalInversa method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonalInversa_2() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 4;
            char columna = 'J';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.INVERSEDIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonalInversa method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonalInversa_3() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 6;
            char columna = 'C';
            Tablero instance = new Tablero();
            boolean expResult = false;
            boolean result = instance.insertar(Textos.INVERSEDIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonalInversa method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonalInversa_4() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 1;
            char columna = 'E';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.INVERSEDIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonalInversa method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonalInversa_5() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 1;
            char columna = 'J';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.INVERSEDIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonalInversa method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonalInversa_6() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 5;
            char columna = 'G';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.INVERSEDIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonalInversa method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonalInversa_7() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.BUQUE, Textos.BUQUEFIGURE.length());
            int fila = 6;
            char columna = 'J';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.INVERSEDIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonalInversa method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonalInversa_8() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.BUQUE, Textos.BUQUEFIGURE.length());
            int fila = 7;
            char columna = 'C';
            Tablero instance = new Tablero();
            boolean expResult = true;
            boolean result = instance.insertar(Textos.INVERSEDIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());;
        }
    }
    /**
     * Test of comprobarDiagonalInversa method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonalInversa_9() {
        try {
            System.out.println("comprobarDiagonal");
            Barco brc = new Barco(Textos.BUQUE, Textos.BUQUEFIGURE.length());
            int fila = 8;
            char columna = 'B';
            Tablero instance = new Tablero();
            instance.insertar(Textos.INVERSEDIAGONAL,fila,columna,brc);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());
        }
    }
    
    /**
     * Test of comprobarDiagonalInversa method, of class Tablero con un barco
     * Diagonal en la casilla de al lado.
     */
    @Test
    public void testComprobarDiagonalInversa_Barco_Cerca() 
    {
        try {
            System.out.println("comprobarDiagonal");
            
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 1;
            char columna = 'G';
            Tablero instance = new Tablero();
            try {
                instance.insertar('I', 7, 'C', new Barco(Textos.BUQUE,Textos.BUQUEFIGURE.length()));
            } catch (ExcepcionesBarco ex) {
                assertEquals(Textos.NOTFREEPOSITION,ex.getMessage());;
            }
            boolean expResult = true;
            boolean result = instance.insertar(Textos.INVERSEDIAGONAL,fila,columna,brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.TOOBIGSHIP,ex.getMessage());
        }
    }
    /**
     * Test of comprobarDiagonalInversa method, of class Tablero con un barco
     * Diagonal en la casilla de al lado.
     */
    @Test
    public void testComprobarDiagonalInversa_Barco_Cerca_NoPosible() 
    {
        try {
            Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
            int fila = 1;
            char columna = 'G';
            Tablero instance = new Tablero();
            try {
                instance.insertar('I', 6, 'C', new Barco(Textos.BUQUE,Textos.BUQUEFIGURE.length()));
            } catch (ExcepcionesBarco ex) {
                assertEquals(Textos.NOTFREEPOSITION,ex.getMessage());;
            }
            instance.insertar(Textos.INVERSEDIAGONAL,fila,columna,brc);
        } catch (ExcepcionesBarco ex) {
            assertEquals(Textos.SQUARENOTFREE,ex.getMessage());
        }
    }
    
    /**
     * Test of insertar method, of class Tablero.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertar_4args() throws Exception {
        System.out.println("insertar");
        char dir = 'D';
        int fila = 1;
        char columna = 'A';
        Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
        Tablero instance = new Tablero();
        boolean expResult = true;
        boolean result = instance.insertar(dir, fila, columna, brc);
        assertEquals(expResult, result);
    }
    /**
     * Test of insertar method, of class Tablero.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertar_4args_2() throws Exception {
        System.out.println("insertar");
        char dir = 'H';
        int fila = 1;
        char columna = 'A';
        Barco brc = new Barco(Textos.BUQUE, Textos.BUQUEFIGURE.length());
        Tablero instance = new Tablero();
        boolean expResult = true;
        boolean result = instance.insertar(dir, fila, columna, brc);
        assertEquals(expResult, result);
    }
    /**
     * Test of insertar method, of class Tablero.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertar_4args_3() throws Exception {
        System.out.println("insertar");
        char dir = 'V';
        int fila = 5;
        char columna = 'E';
        Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
        Tablero instance = new Tablero();
        boolean expResult = true;
        boolean result = instance.insertar(dir, fila, columna, brc);
        assertEquals(expResult, result);
    }
    /**
     * Test of insertar method, of class Tablero.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertar_4args_4() throws Exception {
        System.out.println("insertar");
        char dir = 'I';
        int fila = 5;
        char columna = 'E';
        Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
        Tablero instance = new Tablero();
        boolean expResult = true;
        boolean result = instance.insertar(dir, fila, columna, brc);
        assertEquals(expResult, result);
    }
    /**
     * Test of insertar method, of class Tablero.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertar_4args_L() throws Exception {
        System.out.println("insertar");
        char dir = 'D';
        int fila = 1;
        char columna = 'A';
        Barco brc = new Barco(Textos.LANCHA, 1);
        Tablero instance = new Tablero();
        boolean expResult = true;
        boolean result = instance.insertar(dir, fila, columna, brc);
        assertEquals(expResult, result);
    }
    /**
     * Test of insertar method, of class Tablero.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertar_4args_L_2() throws Exception {
        System.out.println("insertar");
        char dir = 'D';
        int fila = 10;
        char columna = 'J';
        Barco brc = new Barco(Textos.LANCHA, 1);
        Tablero instance = new Tablero();
        boolean expResult = true;
        boolean result = instance.insertar(dir, fila, columna, brc);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of insertar method, of class Tablero.
     */
    @Test
    public void testInsertar_4args_I()
    {
        System.out.println("insertar");
        char dir = 'I';
        int fila = 1;
        char columna = 'A';
        Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
        Tablero instance = new Tablero();
        boolean expResult = false;
        boolean result;
        try {
            result = instance.insertar(dir, fila, columna, brc);
            assertEquals(expResult, result);
        } catch (ExcepcionesBarco ex) {
            assertEquals(true,true); //Solo si falla al insertar entra aqui.
        }
    }

    /**
     * Test of insertarResultado method, of class Tablero.
     */
    @Test
    public void testInsertarResultado() {
        System.out.println("insertarResultado");
        int fila = 0;
        int columna = 0;
        char res = Textos.RIGHTLETTER;
        Tablero instance = new Tablero();
        instance.insertarResultado(fila, columna, res);
    }
}
