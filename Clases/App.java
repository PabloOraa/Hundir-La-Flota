/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pabli
 */
public class App
{
    private static Scanner sc = new Scanner(System.in);
    private Jugador j1;
    private Jugador j2;
    private String path;
    
    public App(Jugador j1)
    {
        this.j1 = j1;
        j2 = new Jugador(Textos.NPC);
        path = System.getProperty("user.home") + "/Desktop/"+j1.getNickname()+".save";
    }
    
    public static void main(String[] args)
    {
        try 
        {
            System.out.println(Textos.ASKNICKNAME);
            String nickname = sc.nextLine();
            Jugador jtemp = new Jugador(nickname);
            
            App aplicacion = new App(jtemp);
            
            
            if(aplicacion.buscarPartida())
            {
                aplicacion.cargarPartida();
                System.out.println("Cargado, imprimiendo");
                aplicacion.imprimirTableros(aplicacion.j1);
                aplicacion.imprimirTableros(aplicacion.j2);
            }
            else
            {   
                aplicacion.j2.getTableroBarcos().insertar(new File("src/Datos/posiciones.csv"));
                aplicacion.imprimirTableros(aplicacion.j1);
                aplicacion.imprimirTableros(aplicacion.j2);
                aplicacion.save(aplicacion.j1);
                aplicacion.save(aplicacion.j2);
            }
        } catch (ExcepcionesBarco ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void imprimirTableros(Jugador j1) 
    {
        j1.imprimirTableros();
    }

    public boolean save(Jugador j1)
    {
        try 
        {
            File archivoGuardado = new File(path);
            
            if(!archivoGuardado.exists())
                archivoGuardado.createNewFile();
                
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoGuardado,true));)
            {
                oos.writeObject(j1);
                return true;
            }
        } 
        catch (IOException ex) 
        {
            return false;
        }
    }
    
    public boolean buscarPartida()
    {
        File archivoGuardado = new File(path); 
        
        return archivoGuardado.exists();
    }
    
    private void cargarPartida()
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)));) 
        {

            this.j1 = (Jugador) ois.readObject();
            j2 = (Jugador) ois.readObject();
        } catch (EOFException ex) {
        } catch (IOException | ClassNotFoundException ex) {
        }
    }
}
