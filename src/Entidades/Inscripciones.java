package Entidades;

import java.sql.Date;

/**
 * @author Pablo Oraa Lopez
 */
public class Inscripciones
{
    private String DNI;
    private int id_Oferta;
    private int id_Curriculum;
    private Date fecha;
    
    public Inscripciones(String DNI, int id_Oferta)
    {
        this.DNI = DNI;
        this.id_Oferta = id_Oferta;
    }

    public String getDNI()
    {
        return DNI;
    }

    public int getId_Oferta()
    {
        return id_Oferta;
    }

    public int getId_Curriculum()
    {
        return id_Curriculum;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setId_Curriculum(int id_Curriculum)
    {
        this.id_Curriculum = id_Curriculum;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }
}
