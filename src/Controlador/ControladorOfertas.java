package Controlador;

import Entidades.Oferta;
import Entidades.Empresa;
import Modelo.OfertasDB;
import Vista.Ofertas;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * @author Pablo Oraa Lopez
 */
public class ControladorOfertas extends Controlador
{
    /**
     * Referencia a la vetana Oferta que crea un formularo de la Creación de Ofertas.
     */
    private Ofertas ventana;
    /**
     * Referencia a la clase de conexión con la base de datos con las operaciones frecuentes sobre las Ofertas.
     */
    private OfertasDB ofDB;
    
    /**
     * Constructor del Controlador de la Creación de Ofertas.
     * @param vtn Ventana Crear Oferta sobre la que se aplica toda la configuración.
     */
    public ControladorOfertas(Ofertas vtn)
    {
        super(vtn.getPadre());
        ventana = vtn;
        ofDB = new OfertasDB();
        setFondo(ventana);
        ventana.setLocationRelativeTo(ventana.getPadre());
        asignarKeyEvent(ventana);
        setCancelarListener();
        listenerCrearOferta();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Listener Crear Curriculum Teclado">
    /**
     * Asigna los Listener de teclas para confirmar la creación de la cuenta en toda la ventana con la tecla Enter
     * @param cmp Componente a la que se le asigna el KeyListener
     */
    private void asignarKeyEvent(Component cmp)
    {
        cmp.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyReleased(KeyEvent ke)
            {
                if(!ke.getSource().getClass().getName().equals("javax.swing.JTextArea"))
                    if(ke.getKeyCode() == KeyEvent.VK_ENTER)
                        ventana.getCrearOferta().doClick();
            }
        });
        
        if(cmp instanceof Container)
        {
            Container ctn = (Container) cmp;
            Component[] componentes = ctn.getComponents();
            for (Component componente : componentes)
                asignarKeyEvent(componente);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Listener Cierre de la Ventana">
    /**
     * Configura el funcionamiento de la aplicación si se cierra esta ventana.
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
    
    //<editor-fold defaultstate="collapsed" desc="Creación de una Oferta">
    /**
     * Listener que marca la creación de una oferta cuando pulsa al botón de Enviar.
     */
    public void listenerCrearOferta()
    {
        ventana.getCrearOferta().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                crearOferta();
            }
        });
    }
    
    /**
     * Crea un curriculum en base a los datos enviados.
     */
    public void crearOferta()
    {
        Oferta cu = new Oferta(obtenerUltimaOferta(), ((Empresa)ventana.getPadre().getUsuario()).getCIF());
        cu.setPuesto(ventana.getPuesto().getText().equals("") ? null: ventana.getPuesto().getText());
        cu.setFecha_Creacion(new Date());
        cu.setDescripcion(ventana.getDescripcion().getText().equals("") ? null: ventana.getDescripcion().getText());
        int vacantes = Integer.parseInt(String.valueOf(ventana.getVacantes().getValue()));
        cu.setNumeroVacantes(vacantes);
        cu.setDiasVacaciones(ventana.getVacaciones().getText().equals("") ? 0: Integer.parseInt(ventana.getVacaciones().getText()));
        cu.setUbicacion(ventana.getUbicacion().getText().equals("") ? null: ventana.getUbicacion().getText());
        cu.setRequisitos(ventana.getRequisitos().getText().equals("") ? null: ventana.getRequisitos().getText());
        cu.setSalario(ventana.getSalario().getText().equals("") || Double.parseDouble(ventana.getSalario().getText()) < 0 ? 0: Double.parseDouble(ventana.getSalario().getText()));
        cu.setJornada(ventana.getJornada().getText().equals("") ? null: ventana.getJornada().getText());
        cu.setNumeroInscritos(0);
        
        insertarOferta(cu);
        
    }

    /**
     * Obtenemos el ID del último curriculum registrado dentro de la aplicación
     * @return Numero que indica el último ID registrado
     */
    private int obtenerUltimaOferta()
    {
        return ofDB.obtenerIdUltimo(ventana.getPadre().getUsuario());
    }

    /**
     * Inserta el curriculum en la base de datos.
     * @param cu Curriculum a Insertas.
     */
    private void insertarOferta(Oferta of)
    {
        ofDB.addOferta(ventana.getPadre().getUsuario(),of);
        configurarJOptionPane("Se ha insertado correctamente", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, "Confirmacion");
        getJd().setVisible(true);
        ventana.getPadre().cerrarHijo(ventana);
    }
    //</editor-fold>
}
