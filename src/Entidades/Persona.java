package Entidades;

import java.util.Date;
import javax.swing.ImageIcon;

/**
 * @author Pablo Oraa Lopez
 */
public class Persona extends Usuario
{
    private final String DNI;
    private String primerApellido;
    private String segundoApellido;
    private Date fecha_Nacimiento;
    private String nacionalidad;
    private boolean permisoConduccion;
    private final String usuario = "Persona";
    private final String pass = "PERSONA";
    
    
    public Persona(String user, String pass)
    {
        super(user, pass);
        DNI = "";
    }
    
    public Persona(String user, String pass, String correo, String name, String DNI)
    {
        super(user, pass, correo, name);
        this.DNI = DNI;
    }
    
    public Persona(String user, String pass, String correo, String name, ImageIcon imagen,  String DNI)
    {
        super(user, pass, correo, name, imagen);
        this.DNI = DNI;
    }

    public String getDNI()
    {
        return DNI;
    }

    public String getPrimerApellido()
    {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido)
    {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido()
    {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido)
    {
        this.segundoApellido = segundoApellido;
    }

    public Date getFecha_Nacimiento()
    {
        return fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(Date fecha_Nacimiento)
    {
        this.fecha_Nacimiento = fecha_Nacimiento;
    }

    public String getNacionalidad()
    {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad)
    {
        this.nacionalidad = nacionalidad;
    }

    public int isPermisoConduccion()
    {
        return permisoConduccion ? 1 : 0;
    }

    public void setPermisoConduccion(boolean permisoConduccion)
    {
        this.permisoConduccion = permisoConduccion;
    }

    @Override
    public String getUsuario()
    {
        return usuario;
    }

    @Override
    public String getPass()
    {
        return pass;
    }
}
