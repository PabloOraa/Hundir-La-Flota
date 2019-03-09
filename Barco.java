package Clases;

/**
 * Clase Barcos que representa los barcos usados por los jugadores
 * @Version 1.0
 * @author Enrique Dominguez, David Mateos, Pablo Oraa
 */
public class Barco
{
    private final String name;
    private final int length;
    private final String figure;

    public Barco(String name, int length)
    {
        this.name = name;
        this.length = length;
        figure = createFigure(name, length);
    }

    private String createFigure(String name, int length)
    {
        String finalFigure = "";
        for (int i = 0; i < length; i++)
        {
            finalFigure += Character.toUpperCase(name.charAt(0));
        }
        return finalFigure;
    }

    public String getName()
    {
        return name;
    }

    public int getLength()
    {
        return length;
    }

    public String getFigure()
    {
        return figure;
    }
    
    
}
