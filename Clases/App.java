/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import java.io.IOException;
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
    public static void main(String[] args)
    {
        try {
            System.out.println(Textos.ASKNICKNAME);
            String nickname = sc.nextLine();
            Jugador j1 = new Jugador(nickname);
            Jugador npc = new Jugador(Textos.NPC);
            
            npc.getTableroBarcos().insertar(new File("src/Datos/posiciones.csv"));
            imprimirTableros(j1);
            save(j1);
        } catch (ExcepcionesBarco ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void imprimirTableros(Jugador j1) 
    {
        j1.imprimirTableros();
    }

    public static boolean save(Jugador j1)
    {
            String path = System.getProperty("user.home");
            File archivoGuardado = new File(path + "/Desktop/"+j1.getNickname()+".save");
    }
}
