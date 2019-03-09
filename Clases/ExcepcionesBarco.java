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
public class ExcepcionesBarco extends Exception
{
    /**
     * Crea una excepción con el mensaje null.
     */
    public ExcepcionesBarco()
    {
        super();
    }
    
    /**
     * Crea una excepcion con el mensaje pasado por parametro.
     * @param message Mensaje del usuario al que se puede acceder desde el metodo getMessage().
     */
    public ExcepcionesBarco(String message)
    {
        super(message);
    }
    
    /**
     * Crea una excepción con el mensaje null.
     * @param thr Indica la causa de la excepción. Si es null se considera que no hay causa
     */
    public ExcepcionesBarco(Throwable thr)
    {
        super(thr);
    }
    
    /**
     * Crea una excepcion con el mensaje pasado por parametro.
     * @param message Mensaje del usuario al que se puede acceder desde el metodo getMessage().
     * @param thr Indica la causa de la excepción. Si es null se considera que no hay causa
     */
    public ExcepcionesBarco(String message, Throwable thr)
    {
        super(message, thr);
    }
}
