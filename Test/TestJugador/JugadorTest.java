package TestJugador;

import Clases.Barco;
import Clases.Jugador;
import Clases.Textos;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase TestJugador que representa todos los test realizados a la clase Barcos
 * @Version 1.0.1
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class JugadorTest 
{
    /**
     * Test of Disparar method, of class Jugador.
     * @throws java.lang.Exception
     */
    @Test
    public void testDisparar_Agua() throws Exception 
    {
        System.out.println("Disparar");
        Jugador j2 = new Jugador(Textos.NPC);
        int fila = 1;
        char columna = 'A';
        Jugador instance = new Jugador("Prueba");
        String expResult = Textos.FAIL;
        String result = instance.Disparar(j2, fila, columna);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of Disparar method, of class Jugador.
     * @throws java.lang.Exception
     */
    @Test
    public void testDisparar_Tocado() throws Exception 
    {
        System.out.println("Disparar");
        Jugador j2 = new Jugador(Textos.NPC);
        int fila = 1;
        char columna = 'A';
        Jugador instance = new Jugador("Prueba");
        String expResult = Textos.RIGHT;
        j2.insertarBarco(Textos.VERTICAL, fila, columna, new Barco(Textos.PORTAAVIONES, Textos.PORTAAVIONESFIGURE.length()));
        String result = instance.Disparar(j2, fila, columna);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of Disparar method, of class Jugador.
     * @throws java.lang.Exception
     */
    @Test
    public void testDisparar_Hundido() throws Exception 
    {
        System.out.println("Disparar");
        Jugador j2 = new Jugador(Textos.NPC);
        int fila = 1;
        char columna = 'A';
        Jugador instance = new Jugador("Prueba");
        String expResult = Textos.ENDOFSHIP;
        j2.insertarBarco(Textos.VERTICAL, fila, columna, new Barco(Textos.LANCHA, 1));
        String result = instance.Disparar(j2, fila, columna);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of Disparar method, of class Jugador.
     * @throws java.lang.Exception
     */
    @Test
    public void testDisparar_PlayerDead() throws Exception 
    {
        System.out.println("Disparar");
        Jugador j2 = new Jugador(Textos.NPC);
        int fila = 1;
        char columna = 'A';
        Jugador instance = new Jugador("Prueba");
        String expResult = Textos.PLAYERDEAD;
        for(int i = 0; i < j2.getListaBarcos().size()-1;i++) //Quitamos las vidas de Portaaviones, Buque y Submarino
        {
            for(int j = 0; j < j2.getListaBarcos().get(i).getLength();j++)
                j2.getListaBarcos().get(i).reducirVida();
        }
        j2.insertarBarco(Textos.VERTICAL, fila, columna, new Barco(Textos.LANCHA, 1)); 
        String result = instance.Disparar(j2, fila, columna); //Quitamos la vida de Lancha, vidas de j2 = 0
        assertEquals(expResult, result);
    }
}
