package Controlador;

import Modelo.Textos;
import Modelo.UsuarioDB;
import Vista.Configuracion;
import Vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Pablo Oraa Lopez
 */
public class ControladorConfiguracion extends Controlador
{
    /**
     * Referencia a la ventana de configuración de la aplicación.
     */
    private Configuracion ventana;
    
    /**
     * Constructor del Controlador donde se iniciliza la variable ventana y se asignan los Listener.
     * @param config 
     */
    public ControladorConfiguracion(Configuracion config)
    {
        super(config.getPadre());
        ventana = config;
        setCancelarListener();
        setConfiguracionApartados();
        setTemasListener();
        setFondo(ventana);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Configuración del JList">
    /**
     * Configura el menú izquierdo para las diferentes acciones didsponibles.
     */
    private void configurarJList()
    {
        JList opciones = ((JList)ventana.getPaneles().getLeftComponent());
        opciones.addListSelectionListener(new ListSelectionListener() 
        {
            @Override
            public void valueChanged(ListSelectionEvent lse)
            {
                if(lse.getValueIsAdjusting() && !opciones.isSelectionEmpty())
                {
                    switch((String)opciones.getSelectedValue())
                    {
                        case Textos.EXIT:
                            cerrarApp();
                            break;
                        case Textos.ABOUT:
                            acercaDe();
                            break;
                        case Textos.DELETEACOUNT:
                            if(ventana.getPadre().getUsuario() == null)
                                ventana.getPadre().getIniciarSesion().doClick();
                            else
                                borrarCuenta();
                            break;
                        case Textos.PERSONALIZATION:
                            personalizar();
                            break;
                    }
                    opciones.clearSelection();
                }
            }
        });
    }
    
    /**
     * Muestra el número de versión y los créditos de la versión de la aplicación.
     */
    public void acercaDe()
    {
        configurarJOptionPane("Versión " + Textos.VERSION + "\n" + Textos.CREDITS,JOptionPane.INFORMATION_MESSAGE,JOptionPane.DEFAULT_OPTION,Textos.ABOUT);
        getJd().setVisible(true);
    }
    
    /**
     * Añade las opciones de Tema Claro o Tema Oscuro en el apartado de Personalización en Configuración.
     */
    public void personalizar()
    {
        ventana.getPersonalizacion().setVisible(true);
        ventana.getTemas().add(ventana.getTemaClaro());
        ventana.getTemas().add(ventana.getTemaOscuro());
        if(VentanaPrincipal.getColorDefecto().equals(Textos.TEMACLARO))
            ventana.getTemaClaro().setSelected(true);
        else
            ventana.getTemaOscuro().setSelected(true);
    }
    
    /**
     * Borra la cuenta de un usuario previa confirmación.
     */
    public void borrarCuenta()
    {
        configurarJOptionPane("¿Estas seguro de que quieres borrar la cuenta?",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION,Textos.DELETEACOUNT);
        setFondo(getJp());
        getJd().setVisible(true);
        if(jp.getValue() != null &&(Integer)getJp().getValue() == JOptionPane.YES_OPTION)
        {
            new UsuarioDB().borrarCuenta(ventana.getPadre().getUsuario().getUsername());
            ventana.getPadre().setUsuario(null);
            sesionIniciada();
            configurarJOptionPane("Se ha borrado la cuenta correctamente",JOptionPane.INFORMATION_MESSAGE,JOptionPane.DEFAULT_OPTION, "Confirmacion");
            getJd().setVisible(true);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Listener de cierre de ventana">
    /**
     * Marca el funcionamiento de la aplicación cuando esta ventana de Configuración se cierre.
     */
    private void setCancelarListener()
    {
        ventana.addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosed(WindowEvent we)
            {
                ventana.getPadre().cerrarHijo(ventana);
            }
        });
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Colocar apartados de configuracion">
    /**
     * Añade el panel derecho del JList y lo marca como Single_Mode para que solo se pueda seleccionar uno a la vez.
     */
    private void setConfiguracionApartados()
    {
        ((JList)ventana.getPaneles().getLeftComponent()).setListData(Textos.CONFIGUTARIONSECTION);
        ((JList)ventana.getPaneles().getLeftComponent()).setBorder(null);
        ((JList)ventana.getPaneles().getLeftComponent()).setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        configurarJList();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Listener Temas">
    /**
     * Cambia los temas de la ventana cuando se selecciona esta función.
     */
    private void setTemasListener()
    {
        ventana.getTemaClaro().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                VentanaPrincipal.setColorDefecto(Textos.TEMACLARO);
                VentanaPrincipal.setColorTextoDefecto(Textos.TEMAOSCURO);
                VentanaPrincipal.setColorJTextFieldDefecto(Textos.TOTALWHITE);
                VentanaPrincipal.setColorJTextFieldTextoDefecto(Textos.TOTALBLACK);
                configurarFondo();
            }
        });
        
        ventana.getTemaOscuro().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                VentanaPrincipal.setColorDefecto(Textos.TEMAOSCURO);
                VentanaPrincipal.setColorTextoDefecto(Textos.TEMACLARO);
                VentanaPrincipal.setColorJTextFieldDefecto(Textos.TOTALBLACK);
                VentanaPrincipal.setColorJTextFieldTextoDefecto(Textos.TOTALWHITE);
                configurarFondo();
            }
        });
    }
    
    /**
     * Configura el fondo de las ventanas hijas respecto a la Ventana Principal.
     */
    public void configurarFondo()
    {
        setFondo(ventana.getPadre());
        setFondo(ventana);
        if(ventana.getPadre().getInicioSesion() != null)
            setFondo(ventana.getPadre().getInicioSesion());
        else if(ventana.getPadre().getRegistrar()!= null)
            setFondo(ventana.getPadre().getRegistrar());
        if(ventana.getPadre().getOferta() != null)
            setFondo(ventana.getPadre().getOferta());
        if(ventana.getPadre().getCurriculum() != null)
            setFondo(ventana.getPadre().getCurriculum());
    }
    //</editor-fold>
}
