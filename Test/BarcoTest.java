/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestBarcos;

import org.junit.Test;
import static org.junit.Assert.*;
import Clases.Barco;

/**
 *
 * @author Pabli
 */
public class BarcoTest
{
    @Test
    public void testCreatePortaavionesC()
    {
        Barco portaaviones = new Barco("portaaviones",5);
        
        String figura = portaaviones.getFigure();
        
        assertEquals("PPPPP",figura);
    }
    
    @Test
    public void testCreatePortaavionesI()
    {
        Barco portaaviones = new Barco("portaaviones",5);
        
        String figura = portaaviones.getFigure();
        
        assertNotEquals("ppppp",figura);
    }
    
    @Test
    public void testCreateBuqueC()
    {
        Barco buque = new Barco("buque",3);
        
        String figura = buque.getFigure();
        
        assertEquals("BBB",figura);
    }
    
    @Test
    public void testCreateBuqueI()
    {
        Barco Buque = new Barco("Buque",5);
        
        String figura = Buque.getFigure();
        
        assertNotEquals("bbb",figura);
    }
    
    @Test
    public void testCreateSubmarinoC()
    {
        Barco submarino = new Barco("submarino",2);
        
        String figura = submarino.getFigure();
        
        assertEquals("SS",figura);
    }
    
    @Test
    public void testCreateSubmarinoI()
    {
        Barco submarino = new Barco("submarino",2);
        
        String figura = submarino.getFigure();
        
        assertNotEquals("ss",figura);
    }
    
    @Test
    public void testCreateLanchaC()
    {
        Barco lancha = new Barco("lancha",1);
        
        String figura = lancha.getFigure();
        
        assertEquals("L",figura);
    }
    
    @Test
    public void testCreateLanchaI()
    {
        Barco lancha = new Barco("lancha",5);
        
        String figura = lancha.getFigure();
        
        assertNotEquals("l",figura);
    }
}
