/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Pabli
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
