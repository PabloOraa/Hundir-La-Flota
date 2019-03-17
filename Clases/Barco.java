package Clases;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase Barcos que representa los barcos usados por los jugadores
 * @Version 1.0.1
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class Barco implements Serializable
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
     * Cadena de texto que indica el estado del Barco, Vivo o Muerto
     */
    private String state;
    /**
     * Contador de vidas del Barco en función de la longitud
     */
    private int vidas;

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
        state = Textos.ALIVE;
        vidas = length;
    }
    
    /**
     * Constructor del barco utilizado para obtener el barco del usuario en base
     * al barco golpeado. 
     * @param name Nombre del barco
     */
    public Barco(String name)
    {
        this.name = name;
        length = 0;
        figure = "";
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
     * Obtiene el estado actual del barco
     * @return Cadena con el estado actual del barco
     */
    public String getState()
    {
        return state;
    }

    /**
     * Obtiene las vidas del barco
     * @return numero de vidas actuales barco
     */
    public int getVidas()
    {
        return vidas;
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
            case 'L':
            case 'l':
                return "Lancha";
            default:
                return "";
        }
    }
    
    /**
     * Reduce la vida del barco en 1 cuando le golpean con un disparo.
     */
    public void reducirVida()
    {
        vidas--;
        
        if(vidas == 0)
           barcoHundido();
    }
    
    /**
     * Cambia el estado Vivo por defecto del barco a Hundido, para indicar que se
     * ha quedado sin vidas.
     */
    private void barcoHundido()
    {
        state = Textos.DEAD;
    }

    /**
     * Comprueba que dos barcos sean iguales en función del nombre del barco
     * @param obj Objeto pasado por parametro
     * @return True si es el mismo y False si no
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Barco other = (Barco) obj;
        return Objects.equals(this.name.toUpperCase(), other.name.toUpperCase());
    }
}
