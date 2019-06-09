package Entidades;

import java.util.Objects;
import javax.swing.ImageIcon;

/**
 * @author Pablo Oraa Lopez
 */
public abstract class Usuario 
{
    private String nombre;
    private String username;
    private String password;
    private String correoElectronico;
    private String Direccion;
    private String telefono;
    private String Ciudad;
    private ImageIcon imagenPerfil;

    public Usuario(String user, String pass)
    {
        username = user;
        password = pass;
        password = String.valueOf(hashCode());
    }
    
    public Usuario(String user, String pass, String correo, String name)
    {
        username = user;
        password = pass;
        password = String.valueOf(hashCode());
        nombre = name;
        correoElectronico = correo;
    }
    
    public Usuario(String user, String pass, String correo, String name, ImageIcon imagen)
    {
        username = user;
        password = pass;
        password = String.valueOf(hashCode());
        nombre = name;
        correoElectronico = correo;
        imagenPerfil = imagen;
    }
    
    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCorreoElectronico()
    {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico)
    {
        this.correoElectronico = correoElectronico;
    }

    public ImageIcon getImagenPerfil()
    {
        return imagenPerfil;
    }
    
    public String getImagenPerfilDir()
    {
        if(imagenPerfil != null)
            return imagenPerfil.toString();
        else
            return null;
    }

    public void setImagenPerfil(ImageIcon imagenPerfil)
    {
        this.imagenPerfil = imagenPerfil;
    }

    public String getDireccion()
    {
        return Direccion;
    }

    public void setDireccion(String Direccion)
    {
        this.Direccion = Direccion;
    }

    public String getCiudad()
    {
        return Ciudad;
    }

    public void setCiudad(String Ciudad)
    {
        this.Ciudad = Ciudad;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.username);
        hash = 89 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass() && comprobarHijos(obj))
        {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.username, other.username))
        {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }
    
    public boolean comprobarHijos(Object obj)
    {
        if(obj instanceof Persona)
            return true;
        else 
            return obj instanceof Empresa;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }
    
    public abstract String getUsuario();

    public abstract String getPass();
}
