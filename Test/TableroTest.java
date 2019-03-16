/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase TestBarcos que representa todos los test realizados a la clase Barcos
 * @Version 1.0
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class TableroTest 
{
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

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
     * Test of comprobarVertical method, of class Tablero.
     */
    @Test
    public void testComprobarVertical() {
        System.out.println("comprobarVertical");
        Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
        int fila = 10;
        char columna = 'J';
        Tablero instance = new Tablero();
        boolean expResult = false;
        boolean result = instance.comprobarVertical(brc, fila, columna);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of comprobarVertical method, of class Tablero con un barco
     * horizontal en la casilla de al lado.
     */
    @Test
    public void testComprobarVertical_Barco_Cerca() 
    {
        System.out.println("comprobarVertical");
        
        Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
        int fila = 5;
        char columna = 'J';
        Tablero instance = new Tablero();
        try {
            instance.insertar('V', 1, 'J', new Barco(Textos.BUQUE,Textos.BUQUEFIGURE.length()));
        } catch (ExcepcionesBarco ex) {
            System.err.println(ex.getMessage());
        }
        boolean expResult = true;
        boolean result = instance.comprobarVertical(brc, fila, columna);
        assertEquals(expResult, result);
    }
    /**
     * Test of comprobarVertical method, of class Tablero con un barco
     * horizontal en la casilla de al lado.
     */
    @Test
    public void testComprobarVertical_Barco_Cerca_NoPosible() 
    {
        Barco brc = new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length());
        int fila = 5;
        char columna = 'J';
        Tablero instance = new Tablero();
        try {
            instance.insertar('V', 3, 'J', new Barco(Textos.BUQUE,Textos.BUQUEFIGURE.length()));
        } catch (ExcepcionesBarco ex) {
            System.err.println(ex.getMessage());
        }
        boolean expResult = false;
        boolean result = instance.comprobarVertical(brc, fila, columna);
        assertEquals(expResult, result);
    }

    /**
     * Test of comprobarHorizontal method, of class Tablero.
     */
    @Test
    public void testComprobarHorizontal() {
        System.out.println("comprobarHorizontal");
        Barco brc = null;
        int fila = 0;
        char columna = ' ';
        Tablero instance = new Tablero();
        boolean expResult = false;
        boolean result = instance.comprobarHorizontal(brc, fila, columna);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of comprobarDiagonal method, of class Tablero.
     */
    @Test
    public void testComprobarDiagonal() {
        System.out.println("comprobarDiagonal");
        Barco brc = null;
        int fila = 0;
        char columna = ' ';
        char carDir = ' ';
        Tablero instance = new Tablero();
        boolean expResult = false;
        boolean result = instance.comprobarDiagonal(brc, fila, columna, carDir);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertar method, of class Tablero.
     */
    @Test
    public void testInsertar_File() throws Exception {
        System.out.println("insertar");
        File archivo = null;
        Tablero instance = new Tablero();
        instance.insertar(archivo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertar method, of class Tablero.
     */
    @Test
    public void testInsertar_4args() throws Exception {
        System.out.println("insertar");
        char dir = ' ';
        int fila = 0;
        char columna = ' ';
        Barco brc = null;
        Tablero instance = new Tablero();
        boolean expResult = false;
        boolean result = instance.insertar(dir, fila, columna, brc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertarResultado method, of class Tablero.
     */
    @Test
    public void testInsertarResultado() {
        System.out.println("insertarResultado");
        int fila = 0;
        int columna = 0;
        char res = ' ';
        Tablero instance = new Tablero();
        instance.insertarResultado(fila, columna, res);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
