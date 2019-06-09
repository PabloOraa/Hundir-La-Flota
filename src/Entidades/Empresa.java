package Entidades;

import javax.swing.ImageIcon;

/**
 * @author Pablo Oraa Lopez
 */
public class Empresa extends Usuario
{
    private final int CIF;
    private String informacion_Adicional;
    private String personaContacto;
    private String URL;
    private final String usuario = "Empresa";
    private final String pass = "EMPRESA";
    
    public Empresa(String user, String pass, int CIF)
    {
        super(user, pass);
        this.CIF = CIF;
    }
    
    public Empresa(String user, String pass, String correo, String name, int CIF)
    {
        super(user, pass, correo, name);
        this.CIF = CIF;
    }
    
    public Empresa(String user, String pass, String correo, String name, ImageIcon imagen, int CIF)
    {
        super(user, pass, correo, name, imagen);
        this.CIF = CIF;
    }

    public int getCIF()
    {
        return CIF;
    }

    public String getInformacion_Adicional()
    {
        return informacion_Adicional;
    }

    public void setInformacion_Adicional(String informacion_Adicional)
    {
        this.informacion_Adicional = informacion_Adicional;
    }

    public String getPersonaContacto()
    {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto)
    {
        this.personaContacto = personaContacto;
    }

    public String getURL()
    {
        return URL;
    }

    public void setURL(String URL)
    {
        this.URL = URL;
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
