package Clases;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * @Version 1.2
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class App
{
    /**
     * Clase Scanner para la entrada por teclado del usuario.
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Jugador controlado por el usuario del programa.
     */
    private Jugador j1;
    /**
     * Jugador controlado por la máquina.
     */
    private Jugador j2;
    /**
     * Ruta a guardar el archivo del jugador si desea guardar.
     */
    private final String path;
    
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
        System.out.println(Textos.WELCOMETEXT);
        System.out.println(Textos.ASKNICKNAME);
        String nickname = sc.nextLine();
        Jugador jtemp = new Jugador(nickname);
            
        App aplicacion = new App(jtemp);
            
            
        if(aplicacion.buscarPartida())
        {
            SimpleDateFormat sdf = new SimpleDateFormat(Textos.FORMAT);
            System.out.println(Textos.SAVEFILEFOUND 
                    + sdf.format(new Date(new File(aplicacion.path).lastModified())) + Textos.ASKLOAD);
            String respuesta = sc.nextLine(); 
            if(respuesta.toUpperCase().equals(Textos.AFFIRMATIVE))
            {        
                aplicacion.cargarPartida();
                System.out.println("Cargando");
            }
            else
            {   
                aplicacion.borrarPartida();
                aplicacion.insertarBarcos(aplicacion.j1);
                aplicacion.insertarBarcos(aplicacion.j2, "src/Datos/posiciones.csv");
                aplicacion.j2.getTableroBarcos().imprimirTablero();
            }
        }
        else
        {   
            //aplicacion.insertarBarcos(aplicacion.j1);
            aplicacion.insertarBarcos(aplicacion.j2, "src/Datos/posiciones.csv");
            aplicacion.j2.getTableroBarcos().imprimirTablero();
        }       
        aplicacion.jugar();
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
            System.out.println("Turno de " + turno.getNickname());
            if(turno.equals(j1))
            {    
                System.out.println(Textos.MENU);
                int opcion = Integer.parseInt(sc.nextLine());
            
                switch(opcion)
                {
                    case 1:
                        System.out.println("Jugador " + turno.getNickname());
                        imprimirTableros(turno);
                        System.out.println("\n\nJugador " + anterior.getNickname());
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
                    System.out.println("\n\nJugador " + anterior.getNickname());
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
                {   
                    System.out.println("El ganador es: " + turno.getNickname());
                    borrarPartida();
                }
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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)));)
        {
            oos.writeObject(j1);
            oos.writeObject(j2);
            return true;
        }
        catch (IOException ex) 
        {
            System.err.println(ex.getMessage());
            return false;
        }
    }
    
    /**
     * Busca si existe una partida guardada para el nickname indicado.
     * @return True si existe y False si no.
     */
    public boolean buscarPartida()
    {
        return new File(path).exists();
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
            System.err.println(ex.getMessage());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Borra la partida guardada al acabar la partida (si existe) o si no se 
     * quiere cargar
     */
    private void borrarPartida() 
    {
        File arch = new File(path);
        if(arch.exists())
            arch.delete();
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
            fila = pedirFila();
            columna = pedirColumna();
        }
        else 
        {
            fila = generarFila();
            columna = generarColumna();
        }
        
        if(comprobarFila(fila) && comprobarColumna(columna))
            return turno.Disparar(anterior, fila, columna);
        else
            System.err.println(Textos.NOTVALIDFIELDS);
        
        return "";
    }
    
    /**
     * Pide una fila al usuario que introducirá por teclado
     * @return Número introducido por teclado.
     */
    private int pedirFila()
    {
        System.out.println(Textos.ASKROW);
        return Integer.parseInt(sc.nextLine());
    }
    
    /**
     * Pide la columna al usuario para que la introduzca por teclado.
     * @return Caracter que indica la columna.
     */
    private char pedirColumna()
    {
        System.out.println(Textos.ASKCOLUMN);
        return sc.nextLine().charAt(0);
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
        return j1.getTableroBarcos().getCoord(Character.toUpperCase(columna)) < 10 
                && j1.getTableroBarcos().getCoord(Character.toUpperCase(columna)) >-1;
    }

    /**
     * Genera un numero entre 1 y 10 para representar la fila a disparar.
     * @return Número generado entre 1 y 10.
     */
    private int generarFila()
    {
        return 1+(new Random().nextInt(10));
    }

    /**
     * Genera un caracter entre A y J para representar la columna a disparar.
     * @return Caracter generado entre A y J.
     */
    private char generarColumna()
    {
        return obtenerCaracter(1+(new Random().nextInt(10)));
    }

    /**
     * A partir del número generado para la columna para el NPC, se obtiene el 
     * caracter asociado.
     * @param i Numero de columna entre 1 y 10.
     * @return Caracter entre A y J asociado al número de columna.
     */
    private char obtenerCaracter(int i)
    {
        switch(i)
        {
            case 1:
                return Textos.COLUMN1;
            case 2:
                return Textos.COLUMN2;
            case 3:
                return Textos.COLUMN3;
            case 4:
                return Textos.COLUMN4;
            case 5:
                return Textos.COLUMN5;
            case 6:
                return Textos.COLUMN6;
            case 7:
                return Textos.COLUMN7;
            case 8:
                return Textos.COLUMN8;
            case 9:
                return Textos.COLUMN9;
            default: /*Se considera el Default la letra J*/
                return Textos.COLUMN10;
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
        System.out.println("Insertando barcos del jugador " + j1.getNickname());
        char dir, columna;
        int fila;
        for(int i = 0; i < j1.getListaBarcos().size();)
        {
            try
            {
                System.out.println("Se va a introducir el: " + j1.getListaBarcos().get(i).getName());
                System.out.println(Textos.ASKDIR);
                dir = sc.nextLine().charAt(0);
                fila = pedirFila();
                columna = pedirColumna();
                
                if(comprobarFila(fila) && comprobarColumna(columna))
                {
                    if(j1.insertarBarco(dir, fila, columna, j1.getListaBarcos().get(i)))
                    {
                        j1.getTableroBarcos().imprimirTablero();
                        i++;
                    }
                }
                else
                    System.err.println(Textos.NOTVALIDFIELDS); 
            } catch (ExcepcionesBarco | NumberFormatException ex)
            {
                System.err.println(ex.getMessage());
            }
        }
    }
    
    /**
     * Inserta los barcos en la posición que indique el jugador mediante un fichero
     * CSV que el jugador indicará la ruta.
     * @param j1 Jugador en el que se van a introducir los barcos según indique 
     * el usuario
     * @param path Ruta en la que se encuentra el archivo CSV con los barcos a 
     * insertar.
     */
    private boolean insertarBarcos(Jugador j1, String path)
    {
        System.out.println("Insertando barcos del jugador " + j1.getNickname());
        return j1.insertarBarco(new File(path));
    }
}