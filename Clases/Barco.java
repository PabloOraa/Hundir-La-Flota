package Clases;

/**
 * Clase Barcos que representa los barcos usados por los jugadores
 * @Version 1.0
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class Barco
{
    /**
     * Cadena de texto que indica el nombre del barco
     */
    private final String name;
    /**
     * Número que indica la longitud del barco
     */
    private final int length;
    /**
     * Cadena de texto que indica la figura del barco
     */
    private final String figure;

    /**
     * Constructor del barco que crea automáticamente la figura del barco que se 
     * usará en el tablero a partir del nombre y la longitud. 
     * @param name Nombre del barco
     * @param length Longitud del barco
     */
    public Barco(String name, int length)
    {
        this.name = name;
        this.length = length;
        figure = createFigure(name, length);
    }

    /**
     * Crea la figura que tendrá el barco en el tablero
     * @param name Nombre del barco
     * @param length Longitud del barco
     * @return Cadena que indica la figura del texto
     */
    private String createFigure(String name, int length)
    {
        String finalFigure = "";
        for (int i = 0; i < length; i++)
        {
            finalFigure += Character.toUpperCase(name.charAt(0));
        }
        return finalFigure;
    }

    /**
     * Obtiene el nombre del barco
     * @return Cadena de texto con el nombre del barco
     */
    public String getName()
    {
        return name;
    }

    /**
     * Obtiene la longitud del barco
     * @return Número con la longitud del barco
     */
    public int getLength()
    {
        return length;
    }

    /**
     * Obtiene la figura del barco
     * @return String con la figura del barco
     */
    public String getFigure()
    {
        return figure;
    }
    
    /**
     * Obtienes el nombre del barco a partir de las letras de la figura
     * @param figure Nombre de la figura que tiene el barco
     * @return String con la cadena de texto indicando el nombre del barco
     */
    public static String obtenerNombre(String figure)
    {
        switch(Character.toUpperCase(figure.charAt(0)))
        {
            case 'P':
            case 'p':
                return "Portaaviones";
            case 'B':
            case 'b':
                return "Buque";
            case 'S':
            case 's':
                return "Submarino";
            default: //Se interpreta como Lancha al no haber otra opción disponible
                return "Lancha";
        }
    }
}
