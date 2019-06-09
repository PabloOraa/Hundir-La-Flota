package Controlador;

import Entidades.Curriculum;
import Entidades.Persona;
import Modelo.CurriculumDB;
import Vista.CrearCurriculum;
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
public class ControladorCurriculum extends Controlador
{
    /**
     * Referencia a la ventana de Crreación de curriculum.
     */
    private CrearCurriculum ventana;
    /**
     * Referencia a las funciones de unión con la base de datos en relación a los Curriculum.
     */
    private CurriculumDB cuDB;
    
    /**
     * Constructor del controlador de la creación de curriculum.
     * @param vtn Ventana Crear Curriculum sobre la que se asignan los Listener y funciones a realizar.
     */
    public ControladorCurriculum(CrearCurriculum vtn)
    {
        super(vtn.getPadre());
        ventana = vtn;
        setFondo(ventana);
        ventana.setLocationRelativeTo(ventana.getPadre());
        asignarKeyEvent(ventana);
        setCancelarListener();
        cuDB = new CurriculumDB();
        listenerCrearCurriculum();
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
                    ventana.getEnviarCurriculum().doClick();
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
    
    //<editor-fold defaultstate="collapsed" desc="Creación de un Curriculum">
    /**
     * Listener que marca la creación de un curriculum cuando pulsa al botón de Enviar.
     */
    public void listenerCrearCurriculum()
    {
        ventana.getEnviarCurriculum().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                crearCurriculum();
            }
        });
    }
    
    /**
     * Crea un curriculum en base a los datos enviados.
     */
    public void crearCurriculum()
    {
        Curriculum cu = new Curriculum(obtenerUltimoCurriculum(), ((Persona)ventana.getPadre().getUsuario()).getDNI());
        cu.setExperienciaLaboral(ventana.getExperiencia().getText().equals("") ? null: ventana.getExperiencia().getText());
        cu.setFechaActualizacion(new Date());
        cu.setFormacion(ventana.getFormacion().getText().equals("") ? null: ventana.getFormacion().getText());
        cu.setGustosyAficiones(ventana.getGustos().getText().equals("") ? null: ventana.getGustos().getText());
        cu.setIdiomas(ventana.getIdiomas().getText().equals("") ? null: ventana.getIdiomas().getText());
        cu.setOtrosDatos(ventana.getOtros().getText().equals("") ? null: ventana.getOtros().getText());
        
        insertarCurriculum(cu);
    }

    /**
     * Obtenemos el ID del último curriculum registrado dentro de la aplicación
     * @return Numero que indica el último ID registrado
     */
    private int obtenerUltimoCurriculum()
    {
        return cuDB.obtenerIdUltimo(ventana.getPadre().getUsuario());
    }

    /**
     * Inserta el curriculum en la base de datos.
     * @param cu Curriculum a Insertas.
     */
    private void insertarCurriculum(Curriculum cu)
    {
        cuDB.addCurriculum(ventana.getPadre().getUsuario(),cu);
        configurarJOptionPane("Se ha insertado correctamente", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, "Confirmacion");
        getJd().setVisible(true);
        ventana.getPadre().cerrarHijo(ventana);
    }
    //</editor-fold>
}
