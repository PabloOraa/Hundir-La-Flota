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
import java.util.Random;
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
    
    /**
     * Main de la aplicación que crea los jugadores, busca partidas guardadas,
     * inserta los barcos en j2 (NPC) y lleva al menú de selección de opción.
     * @param args 
     */
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
                System.out.println(Textos.ASKLOAD);
                String respuesta = sc.nextLine(); 
                if(respuesta.toUpperCase().equals(Textos.AFFIRMATIVE))
                {        
                    aplicacion.cargarPartida();
                    System.out.println("Cargando");
                }
                else
                {   
                    aplicacion.insertarBarcos(aplicacion.j1);
                    aplicacion.j2.getTableroBarcos().insertar(new File("src/Datos/posiciones.csv"));
                }
            }
            else
            {   
                aplicacion.insertarBarcos(aplicacion.j1);
                aplicacion.j2.getTableroBarcos().insertar(new File("src/Datos/posiciones.csv"));
            }
            
            aplicacion.jugar();
        } catch (ExcepcionesBarco ex) {
            System.err.println(ex.getMessage());
        }

    }

    /**
     * Muestra las opciones disponibles al usuario disponibles en el juego y es
     * el método principal para jugar.
     */
    public void jugar()
    {
        Jugador turno = j1;
        Jugador anterior = j2;
        boolean salir = false;
        String resultado = "";
        
        while(!salir)
        {    
            boolean cambiarJugador = false;
            if(turno.equals(j1))
            {    
                System.out.println(Textos.MENU);
                int opcion = Integer.parseInt(sc.nextLine());
            
                switch(opcion)
                {
                    case 1:
                        System.out.println("Jugador " + turno.getNickname());
                        imprimirTableros(turno);
                        System.out.println("\n\n\nJugador " + anterior.getNickname());
                        imprimirTableros(anterior);
                        try
                        {
                            resultado = disparar(turno, anterior);
                            System.out.println(resultado);
                            if(resultado.equals(Textos.PLAYERDEAD))
                                salir = true;
                            else if(!resultado.equals(""))
                                cambiarJugador = true;
                        }catch (ExcepcionesBarco ex)
                        {
                            System.err.println(ex.getMessage());
                        }
                        break;
                    case 2:
                        save();
                        break;
                    case 3:
                        save();
                    case 4:
                        salir = true;
                        break;
                }
            }
            else
            {
                try
                {
                    resultado = disparar(turno, anterior);
                    System.out.println(resultado);
                    if(resultado.equals(Textos.PLAYERDEAD))
                        salir = true;
                    else if(!resultado.equals(""))
                        cambiarJugador = true;
                    System.out.println("Jugador " + turno.getNickname());
                    imprimirTableros(turno);
                    System.out.println("\n\n\nJugador " + anterior.getNickname());
                    imprimirTableros(anterior);
                } catch (ExcepcionesBarco ex)
                {
                    System.err.println(ex.getMessage());
                }
            }
            
            if(turno.getNickname().equals(j1.getNickname()) && cambiarJugador)
            {
                turno = j2;
                anterior = j1;
            }
            else if(salir)
            {
                if(resultado.equals(Textos.PLAYERDEAD))
                    System.out.println("El ganador es: " + turno.getNickname());
            }
            else
            {
                turno = j1;
                anterior = j2;
            }
        }
    }
    
    /**
     * Imprime los dos tableros del Jugador.
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

    /**
     * Metodo llamado cuando el jugador quiere disparar. Si es el jugador, se le
     * pide una fila y una columna que se comprueban como validos.
     * Si es el PC, se genera aleatoriamente entre los valores permitidos.
     * @param turno Jugador con el turno actual
     * @param anterior Otro jugador de la partida
     * @return Devuelve el restulado del disparo y en blanco si los valores son 
     * incorrectos.
     * @throws ExcepcionesBarco
     */
    private String disparar(Jugador turno, Jugador anterior) throws ExcepcionesBarco
    {
        int fila;
        char columna;
        if(turno.equals(j1))
        {    
            System.out.println(Textos.ASKROW);
            fila = Integer.parseInt(sc.nextLine());
            System.out.println(Textos.ASKCOLUMN);
            columna = sc.nextLine().charAt(0);
        }
        else 
        {
            fila = generarFila();
            columna = generarColumna();
        }
        
        if(comprobarFila(fila) && comprobarColumna(columna))
            return turno.Disparar(anterior, fila, columna);
        else
            System.out.println(Textos.NOTVALIDFIELDS);
        
        return "";
    }
    
    /**
     * Comprueba que la fila introducida esté entre 1 y 10.
     * @param fila Fila introducida por el usuario.
     * @return True si es valido y False si no.
     */
    private boolean comprobarFila(int fila)
    {
        return fila < 11 && fila > 0;
    }
    
    /**
     * Comprueba que la columna introducida esté entre A y J.
     * @param columna Columna introducida por el usuario.
     * @return True si es valido y False si no.
     */
    private boolean comprobarColumna(char columna)
    {
        return j1.getTableroBarcos().getCoord(columna) < 10 
                && j1.getTableroBarcos().getCoord(columna) >-1;
    }

    /**
     * Genera un numero entre 1 y 10 para representar la fila a disparar.
     * @return Número generado entre 1 y 10.
     */
    private int generarFila()
    {
        Random r = new Random();
        return 1+(r.nextInt(10));
    }

    /**
     * Genera un caracter entre A y J para representar la columna a disparar.
     * @return Caracter generado entre A y J.
     */
    private char generarColumna()
    {
        Random r = new Random();
        return obtenerCaracter(1+(r.nextInt(10)));
    }

    /**
     * A partir del número generado para la columna, se obtiene el caracter 
     * asociado.
     * @param i Numero de columna entre 1 y 10.
     * @return Caracter entre A y J asociado al número de columna.
     */
    private char obtenerCaracter(int i)
    {
        switch(i)
        {
            case 1:
                return 'A';
            case 2:
                return 'B';
            case 3:
                return 'C';
            case 4:
                return 'D';
            case 5:
                return 'E';
            case 6:
                return 'F';
            case 7:
                return 'G';
            case 8:
                return 'H';
            case 9:
                return 'I';
            default: /*Se considera el Default la letra J*/
                return 'J';
        }
    }

    /**
     * Inserta los barcos en la posición que indique el jugador.
     * <br/>
     * Empieza con el portaaviones, buque, submarino y acaba con la lancha.
     * @param j1 Jugador en el que se van a introducir los barcos según indique 
     * el usuario
     */
    private void insertarBarcos(Jugador j1)
    {
        try
        {
            j1.getTableroBarcos().insertar(new File("src/Datos/posiciones.csv"));
        } catch (ExcepcionesBarco ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}