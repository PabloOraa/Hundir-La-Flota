package Entidades;

import Modelo.OfertasDB;
import java.util.Date;

/**
 * @author Pablo Oraa Lopez
 */
public class Oferta
{
    private final int idOferta;
    private final int CIF;
    private String puesto;
    private String descripcion;
    private int numeroVacantes;
    private String ubicacion;
    private Date fecha_Creacion;
    private String requisitos;
    private double salario;
    private String jornada;
    private int diasVacaciones;
    private int numeroInscritos;
    
    public Oferta(int idOferta, int CIF)
    {
        this.idOferta = idOferta;
        this.CIF = CIF;
    }

    public int getIdOferta()
    {
        return idOferta;
    }
    
    public String getPuesto()
    {
        return puesto;
    }

    public void setPuesto(String puesto)
    {
        this.puesto = puesto;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getUbicacion()
    {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion)
    {
        this.ubicacion = ubicacion;
    }

    public Date getFecha_Creacion()
    {
        return fecha_Creacion;
    }

    public void setFecha_Creacion(Date fecha_Creacion)
    {
        this.fecha_Creacion = fecha_Creacion;
    }

    public String getRequisitos()
    {
        return requisitos;
    }

    public void setRequisitos(String requisitos)
    {
        this.requisitos = requisitos;
    }

    public double getSalario()
    {
        return salario;
    }

    public void setSalario(double salario)
    {
        this.salario = salario;
    }

    public String getJornada()
    {
        return jornada;
    }

    public void setJornada(String jornada)
    {
        this.jornada = jornada;
    }

    public int getDiasVacaciones()
    {
        return diasVacaciones;
    }

    public void setDiasVacaciones(int diasVacaciones)
    {
        this.diasVacaciones = diasVacaciones;
    }

    public int getNumeroInscritos()
    {
        return numeroInscritos;
    }

    public void setNumeroInscritos(int numeroInscritos)
    {
        this.numeroInscritos = numeroInscritos;
    }

    public int getNumeroVacantes()
    {
        return numeroVacantes;
    }

    public void setNumeroVacantes(int numeroVacantes)
    {
        this.numeroVacantes = numeroVacantes;
    }

    public int getCIF()
    {
        return CIF;
    }
    
    @Override
    public String toString()
    {
        return puesto + " - " + new OfertasDB().getNombreEmpresa(this) + "\n" + descripcion + "\n" + numeroVacantes
                + " vacantes - " + fecha_Creacion;
    }
}
