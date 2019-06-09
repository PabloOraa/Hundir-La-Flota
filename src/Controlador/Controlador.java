package Controlador;

import Modelo.Textos;
import Vista.VentanaPrincipal;
import static Vista.VentanaPrincipal.getColorDefecto;
import static Vista.VentanaPrincipal.getColorTextoDefecto;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * @author Pablo Oraa Lopez
 */
public abstract class Controlador
{
    /**
     * Referencia a la Ventana principal de la aplicación
     */
    private VentanaPrincipal ventana;
    /**
     * JOptionPane que mostrará los mensajes de confirmación e información de la aplicación.
     */
    protected JOptionPane jp;
    /**
     * JDialog creado en función del JOptionPane.
     */
    protected JDialog jd;
    
    /**
     * Constructor de la clase Controlador que inicializa el JOptionPane y la Ventana Principal
     * @param vtn Ventana Principal de la aplicación.
     */
    public Controlador(VentanaPrincipal vtn)
    {
        ventana = vtn;
        jp = new JOptionPane();
    }

    /**
     * Obtiene el JOptionPane creado.
     * @return JOptionPane creado durante la inicialización del Controlador.
     */
    public JOptionPane getJp()
    {
        return jp;
    }

    /**
     * Obtiene el JDialog creado cuando se llame al método de configuración del JOptionPane.
     * @return JDialog creado al configurar un JOptionPane.
     */
    public JDialog getJd()
    {
        return jd;
    }
    
    /**
     * Método para mostrar y tratar el mensaje de confirmación de cerrar la aplicación.
     */
    public void cerrarApp()
    {
        configurarJOptionPane(Textos.EXITCONFIRMATION,JOptionPane.INFORMATION_MESSAGE,JOptionPane.YES_NO_OPTION,Textos.EXITCONFIRMATIONTITLE);
        jd.setVisible(true);
        if(jp.getValue() != null &&(Integer)jp.getValue() == JOptionPane.YES_OPTION)
            System.exit(0);
    }
    
    /**
     * Configura el JOptionPane en función del mensaje necesarion para mostrar y el tipo que sea.
     * @param mensaje Cadena de texto a mostrar en el JOptionPane
     * @param tipoMensaje Integer que indica el tipo de mensaje, es decir, ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE.
     * @param tipoOpciones Integer que indica las opciones del JOptionPane, es decir DEFAULT_OPTION, YES_NO_OPTION, YES_NO_CANCEL_OPTION
     * @param titulo Titulo que marca el mensaje de la aplicación.
     */
    public void configurarJOptionPane(String mensaje, int tipoMensaje, int tipoOpciones, String titulo)
    {
        jp.setMessage(mensaje);
        jp.setOptionType(tipoOpciones);
        jp.setMessageType(tipoMensaje);
        jd = jp.createDialog(ventana, titulo);
        setFondo(jp);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Tema claro y oscuro">
    /**
     * Cambia el fondo del componente pasado y sus Componentes en caso de ser un Container.<br/><br/>A mayores elige los iconos correctos para la visualización de la aplicación.
     * @param cmp Componente desde el que comenzar el cambio del color de la aplicación.
     */
    public void setFondo(Component cmp)
    {
        cambiarBackground(cmp);
        cambiarIconos();
    }
    
    /**
     * Cambia el color de fondo de un Componente y los que contiene ese si es un Container.
     * @param cmp Componente principal desde el que comenzar.
     */
    private void cambiarBackground(Component cmp)
    {
        if(!comprobarClaseExcluida(cmp))
        {
            cmp.setBackground(VentanaPrincipal.getColorDefecto());
            cmp.setForeground(VentanaPrincipal.getColorTextoDefecto());
        }
        else
        {
            cmp.setBackground(VentanaPrincipal.getColorJTextFieldDefecto());
            cmp.setForeground(VentanaPrincipal.getColorJTextFieldTextoDefecto());
        }
        
        if(cmp instanceof Container)
        {
            Container ctn = (Container) cmp;
            Component[] componentes = ctn.getComponents();
            for (Component componente : componentes)
                cambiarBackground(componente);
        }
    }
    
    /**
     * Comprueba si uno de los componentes que cambia el fondo debe tener una variable de Color u otra.
     * @param cmp Componente a comprobar.
     * @return True si es un componente con un color que destaque frente a lo general o False si debe tener el color general.
     */
    private boolean comprobarClaseExcluida(Component cmp)
    {
        for (String CLASESEXCLUIDAS : Textos.CLASESEXCLUIDAS)
            if (cmp.getClass().getName().equals(CLASESEXCLUIDAS))
                return true;
        return false;
    }
    
    /**
     * Cambia los iconos de la aplicación en función del tema claro o del tema oscuro.
     */
    private void cambiarIconos()
    {
        if(getColorDefecto() == Textos.TEMACLARO)
        {
            if(ventana.getMenuAbierto())
                ventana.getMenu().setIcon(new ImageIcon("Recursos/Imagenes/Tema Claro/Cerrar Hamburguer Menu.png"));
            else
                ventana.getMenu().setIcon(new ImageIcon("Recursos/Imagenes/Tema Claro/Hamburguer Menu.png"));
            ventana.getBusquedaI().setIcon(new ImageIcon("Recursos/Imagenes/Tema Claro/Lupa de Busqueda.png"));
        }
        else
        {
            if(ventana.getMenuAbierto())
                ventana.getMenu().setIcon(new ImageIcon("Recursos/Imagenes/Tema Oscuro/Cerrar Hamburguer Menu.png"));
            else
                ventana.getMenu().setIcon(new ImageIcon("Recursos/Imagenes/Tema Oscuro/Hamburguer Menu.png"));
            ventana.getBusquedaI().setIcon(new ImageIcon("Recursos/Imagenes/Tema Oscuro/Lupa de Busqueda.png"));
        }
        iconoUsuario();
    }
    
    /**
     * Cambia el icono del usuario en función de si es tema claro o tema oscuro.<br/>Además, si se trata de un inicio de sesión con imagen de perfil, se cambiará a esta.
     */
    private void iconoUsuario()
    {
        ventana.getUsuarioI().setBorder(null);
        if(getColorDefecto() == Textos.TEMACLARO)
        {
            ventana.getUsuarioI().setIcon(new ImageIcon("Recursos/Imagenes/Tema Claro/Perfil no iniciado.png"));
            ventana.getUsuarioI1().setIcon(new ImageIcon("Recursos/Imagenes/Tema Claro/Perfil no iniciado.png"));
        }
        else
        {
            ventana.getUsuarioI().setIcon(new ImageIcon("Recursos/Imagenes/Tema Oscuro/Perfil no iniciado.png"));
            ventana.getUsuarioI1().setIcon(new ImageIcon("Recursos/Imagenes/Tema Oscuro/Perfil no iniciado.png"));
        }
        
        if(ventana.getUsuario() != null && ventana.getUsuario().getImagenPerfil() != null)
        {
            ventana.getUsuarioI().setIcon(ventana.getUsuario().getImagenPerfil());
            ventana.getUsuarioI().setBorder(BorderFactory.createLineBorder(getColorTextoDefecto()));
            ventana.getUsuarioI1().setIcon(ventana.getUsuario().getImagenPerfil());
            ventana.getUsuarioI1().setBorder(BorderFactory.createLineBorder(getColorTextoDefecto()));
        }
    }
    //</editor-fold>
    
    /**
     * Cambia los elementos de la ventana principal cuando se haya iniciado sesión/cerrado sesión para visualizar botones, mensaje de bienvenida y el cambio de icono y mensajes del menú desplegable.
     */    
    public void sesionIniciada()
    {
        ventana.setBotonActivado(!ventana.isBotonActivado());
        ventana.getIniciarSesion().setVisible(ventana.isBotonActivado());
        ventana.getRegistrarse().setVisible(ventana.isBotonActivado());
        iconoUsuario();
        new ControladorVentanaPrincipal(ventana, ventana.getUsuario());
        ventana.setTextoActivado(!ventana.isTextoActivado());
        if(ventana.isTextoActivado())
            ventana.getNombreUsuario().setText("Bienvenido "+ ventana.getUsuario().getNombre());
        ventana.getNombreUsuario().setVisible(ventana.isTextoActivado());
    }
}
