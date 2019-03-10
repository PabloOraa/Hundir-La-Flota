package TestBarcos;

import org.junit.Test;
import static org.junit.Assert.*;
import Clases.Barco;
import Clases.Textos;

/**
 * Clase TestBarcos que representa todos los test realizados a la clase Barcos
 * @Version 1.0
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class BarcoTest
{
    @Test
    public void testCreatePortaavionesC()
    {
        Barco portaaviones = new Barco(Textos.PORTAAVIONES,5);
        
        String figura = portaaviones.getFigure();
        
        assertEquals(Textos.PORTAAVIONESFIGURE,figura);
    }
    
    @Test
    public void testCreatePortaavionesI()
    {
        Barco portaaviones = new Barco(Textos.PORTAAVIONES,5);
        
        String figura = portaaviones.getFigure();
        
        assertNotEquals("ppppp",figura);
    }
    
    @Test
    public void testCreateBuqueC()
    {
        Barco buque = new Barco(Textos.BUQUE,3);
        
        String figura = buque.getFigure();
        
        assertEquals(Textos.BUQUEFIGURE,figura);
    }
    
    @Test
    public void testCreateBuqueI()
    {
        Barco Buque = new Barco(Textos.BUQUE,5);
        
        String figura = Buque.getFigure();
        
        assertNotEquals("bbb",figura);
    }
    
    @Test
    public void testCreateSubmarinoC()
    {
        Barco submarino = new Barco(Textos.SUBMARINO,2);
        
        String figura = submarino.getFigure();
        
        assertEquals(Textos.SUBMARINOFIGURE,figura);
    }
    
    @Test
    public void testCreateSubmarinoI()
    {
        Barco submarino = new Barco(Textos.SUBMARINO,2);
        
        String figura = submarino.getFigure();
        
        assertNotEquals("ss",figura);
    }
    
    @Test
    public void testCreateLanchaC()
    {
        Barco lancha = new Barco(Textos.LANCHA,1);
        
        String figura = lancha.getFigure();
        
        assertEquals(Textos.LANCHAFIGURE,figura);
    }
    
    @Test
    public void testCreateLanchaI()
    {
        Barco lancha = new Barco(Textos.LANCHA,5);
        
        String figura = lancha.getFigure();
        
        assertNotEquals("l",figura);
    }
    
    @Test
    public void testObtenerNombrePortaavionesC()
    {
        String figura = Textos.PORTAAVIONESFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertEquals(Textos.PORTAAVIONES, nombre);
    }
    
    @Test
    public void testObtenerNombrePortaavionesI()
    {
        String figura = Textos.SUBMARINOFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertNotEquals(Textos.LANCHA, nombre);
    }
    
    @Test
    public void testObtenerNombreBuqueC()
    {
        String figura = Textos.BUQUEFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertEquals(Textos.BUQUE, nombre);
    }
    
    @Test
    public void testObtenerNombreBuqueI()
    {
        String figura = Textos.BUQUEFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertNotEquals(Textos.PORTAAVIONES, nombre);
    }
    
    @Test
    public void testObtenerNombreSubmarinoC()
    {
        String figura = Textos.SUBMARINOFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertEquals(Textos.SUBMARINO, nombre);
    }
    
    @Test
    public void testObtenerNombreSubmarinoI()
    {
        String figura = Textos.SUBMARINOFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertNotEquals(Textos.LANCHA, nombre);
    }
    
    @Test
    public void testObtenerNombreLanchaC()
    {
        String figura = Textos.LANCHAFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertEquals(Textos.LANCHA, nombre);
    }
    
    @Test
    public void testObtenerNombreLanchaI()
    {
        String figura = Textos.LANCHAFIGURE;

        String nombre = Barco.obtenerNombre(figura);
        
        assertNotEquals(Textos.PORTAAVIONES, nombre);
    }
}
