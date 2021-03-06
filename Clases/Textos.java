package Clases;

/**
 * Textos de la aplicación
 * @Version 2.0
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public interface Textos 
{
    /**
     * Constante que marca el nombre del juego y de la aplicación.
     */
    String NAMETEXT = "Hundir la flota";
    /**
     * Mensaje de bienvenida de la aplicación.
     */
    String WELCOMETEXT = "Bienvenido a " + Textos.NAMETEXT;
    /**
     * Texto cuando falla el disparo.
     */
    String FAIL = "Agua";
    /**
     * Texto cuando acierta el disparo.
     */
    String RIGHT = "Tocado";
   /**
    * Texto cuando se acierta y disparo y la vida del barco llega a 0.
    */
    String ENDOFSHIP = "Hundido";
    /**
     * Texto que representa el Portaaviones, el barco más grande de todos.
     */
    String PORTAAVIONES = "Portaaviones";
    /**
     * Cadena que representa la figura del portaaviones.
     */
    String PORTAAVIONESFIGURE = "PPPPP";
    /**
     * Texto que representa el Submarino.
     */
    String SUBMARINO = "Submarino";
    /**
     * Cadena que representa la figura del Submarino.
     */
    String SUBMARINOFIGURE = "SS";
    /**
     * Texto que representa el Buque.
     */
    String BUQUE = "Buque";
    /**
     * Cadena que representa la figura del Buque.
     */
    String BUQUEFIGURE = "BBB";
    /**
     * Texto que representa la Lancha.
     */
    String LANCHA = "Lancha";
    /**
     * Cadena que representa la figura de la Lancha.
     */
    char LANCHAFIGURE = 'L';
    /**
     * Caracter A que marca que no hay nada en esa casilla.
     */
    char EMPTY = 'A';
    /**
     * Linea entre las casillas de los tableros de cada jugador.
     */
    String LINE = "------------------------------";
    /**
     * Caracter que hace de barra vertical para simular el tablero.
     */
    char VERTICALBAR = '|';
    /**
     * Caracter X utilizado en la matriz secundaria para marcar que ha fallado.
     */
    char FAILLETTER = 'X';
    /**
     * Caracter O utilizado en la matriz secundaria para marcar que ha acertado.
     */
    char RIGHTLETTER = 'O';
    /**
     * Caracter D que indica  que el barco situado en diagonal va hacia la derecha.
     */
    char DIAGONAL = 'D';
    /**
     * Caracter I que indica  que el barco situado en diagonal va hacia la izquierda.
     */
    char INVERSEDIAGONAL = 'I';
    /**
     * Caracter H que indica que el barco está situado en horizontal.
     */
    char HORIZONTAL = 'H';
    /**
     * Caracter V que indica que el barco está situado en horizontal.
     */
    char VERTICAL = 'V';
    /**
     * Primera Columna (A) de la tabla
     */
    char COLUMN1 = 'A';
    /**
     * Primera Columna (B) de la tabla
     */
    char COLUMN2 = 'B';
    /**
     * Primera Columna (C) de la tabla
     */
    char COLUMN3 = 'C';
    /**
     * Primera Columna (D) de la tabla
     */
    char COLUMN4 = 'D';
    /**
     * Primera Columna (E) de la tabla
     */
    char COLUMN5 = 'E';
    /**
     * Primera Columna (F) de la tabla
     */
    char COLUMN6 = 'F';
    /**
     * Primera Columna (G) de la tabla
     */
    char COLUMN7 = 'G';
    /**
     * Primera Columna (H) de la tabla
     */
    char COLUMN8 = 'H';
    /**
     * Primera Columna (I) de la tabla
     */
    char COLUMN9 = 'I';
    /**
     * Primera Columna (J) de la tabla
     */
    char COLUMN10 = 'J';
    /**
     * Texto que indica que no se ha encontrado el archivo.
     */
    String FILENOTFOUND = "Archivo no encontrado";
    /**
     * Texto que indica que hay un error al leer el archivo.
     */
    String READERROR = "Error al leer el archivo";
    /**
     * Texto que indica que el barco es demasiado grande para la posición elegida.
     */
    String TOOBIGSHIP = "El barco es demasiado grande para la posición elegida.";
    /**
     * Texto que indica que el barco no se puede introducir porque algna de las
     * casillas está ocupada.
     */
    String SQUARENOTFREE = "Casillas ocupadas o con sus extremos con un barco"
            + " en la misma dirección.";
    /**
     * No se puede disparar en la posición seleccionada.
     */
    String NOTFREEPOSITION = "Ya se ha disparado en esa posición";
    /**
     * Estado Vivo del Barco.
     */
    String ALIVE = "Vivo";
    /**
     * Estado Hundido del Barco.
     */
    String DEAD = "Hundido";
    /**
     * El jugador ha muerto.
     */
    String PLAYERDEAD = "Jugador derrotado";
    /**
     * String que pide el nombre al jugador.
     */
    String ASKNICKNAME = "Indica el nombre de usuario";
    /**
     * Cadena con el nombre de la máquina.
     */
    String NPC = "Npc";
    /**
     * Cadena con el menu de la aplicacion.
     */
    String MENU = "Elige la opcion que desees"
            + "\n1) Disparar"
            + "\n2) Guardar"
            + "\n3) Guardar y salir"
            + "\n4) Salir";
    /**
     * Color azul.
     */
    String BLUE = "\033[34m";
    /**
     * Color verde.
     */
    String GREEN = "\033[32m";
    /**
     * Color rojo.
     */
    String RED = "\033[31m";
    /**
     * Color negro.
     */
    String BLACK = "\033[30m";
    /**
     * Pide la fila al usuario.
     */
    String ASKROW = "Introduce la fila entre 1 y 10";
    /**
     * Pide la columna al usuario.
     */
    String ASKCOLUMN = "Introduce la columna entre A y J";
    /**
     * Pide la dirección en la que se introduce el barco.
     */
    String ASKDIR = "Introduce la dirección en la que se introduce el barco.\n"
            + "Nota: D para Diagonal (\\), I para Diagonal Inversa(/), H para Horizontal y V para vertical\n\tNOTA: Ten en cuenta que se insertan de arriba a abajo y de izquierda a derecha.";
    /**
     * El usuario ha introducido mal la fila o la columna.
     */
    String NOTVALIDFIELDS = "La fila o la columna no son validas.";
    /**
     * El usuario ha introducido mal la direccion a insertar el barco.
     */
    String NOTVALIDDIR= "La direccion de insercion indicada no es valida.";
    /**
     * El usuario ha introducido mal el nombre de usuario.
     */
    String NOTVALIDNAME = "El nombre introducido tiene carácteres no validos.";
    /**
     * Cadena de texto para indicar que se ha encontrado una partida guardada.
     */
    String SAVEFILEFOUND = "Se ha encontrado una partida guardada ";
    /**
     * Cadena para pregutnar si se quiere cargar la partida.
     */
    String ASKLOAD = "\nDeseas cargarla?";
    /**
     * Respuesta afirmativa para cargar la partida del usuario.
     */
    String AFFIRMATIVE = "SI";
    /**
     * Formato de la fecha al preguntar al usuario si quiere cargarla.
     */
    String FORMAT = "'el' dd/MM/yyyy 'a las' kk:mm:ss";
    /**
     * Cadena de texto que indica que el usuario puede disparar
     */
    String SHOOT = "Disparar";
    /**
     * Cadena de texto que indica que el usuario inserta un barco
     */
    String ADDSHIP = "Insertar barco";
}