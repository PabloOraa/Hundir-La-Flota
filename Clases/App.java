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

/**
 * @Version 1.0
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class App
{
    private static Scanner sc = new Scanner(System.in);
    private Jugador j1;
    private Jugador j2;
    private String path;
    
    /**
     * Constructor de la clase App que crea un jugador controlado por la maquina
     * y la ruta a usar por el usuario en caso de querer guardar.
     * @param j1 Jugador que usará el usuario.
     */
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
                aplicacion.j1.Disparar(aplicacion.j2, 3, 'A');
                aplicacion.j1.Disparar(aplicacion.j2, 10, 'I');
                aplicacion.imprimirTableros(aplicacion.j1);
                aplicacion.imprimirTableros(aplicacion.j2);
                aplicacion.save();
            }
        } catch (ExcepcionesBarco ex) {
            System.err.println(ex.getMessage());
        }

    }

    /**
     * Imprime los dos tableros el Jugador.
     * @param j1 Jugador a imprimir.
     */
    public void imprimirTableros(Jugador j1) 
    {
        j1.imprimirTableros();
    }

    /**
     * Guarda el estado de los jugadores para recuperarlo en el futuro.
     * @return True si se ha guardado y false si no.
     */
    public boolean save()
    {
        try 
        {
            File archivoGuardado = new File(path);
            
            if(!archivoGuardado.exists())
                archivoGuardado.createNewFile();
                
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoGuardado));)
            {
                oos.writeObject(j1);
                oos.writeObject(j2);
                return true;
            }
        } 
        catch (IOException ex) 
        {
            return false;
        }
    }
    
    /**
     * Busca si existe una partida guardada para el nickname indicado.
     * @return True si existe y False si no.
     */
    public boolean buscarPartida()
    {
        File archivoGuardado = new File(path); 
        
        return archivoGuardado.exists();
    }
    
    /**
     * Carga la partida que ha encontrado el programa para el nickname indicado.
     */
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