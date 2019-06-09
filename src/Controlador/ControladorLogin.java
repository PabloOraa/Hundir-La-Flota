package Controlador;

import Entidades.Persona;
import Entidades.Usuario;
import Modelo.UsuarioDB;
import Modelo.Error;
import Modelo.Textos;
import Vista.Loging;
import Vista.CrearUsuario;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * @author Pablo Oraa Lopez
 */
public class ControladorLogin extends Controlador
{
    /**
     * Referencia a la ventana de Inicio de sesión.
     */
    private Loging ventana;
    /**
     * Contador de 1 minuto por haber fallado tres veces seguidas.
     */
    private Timer t;
    /**
     * Referencia a la base de datos de Usuario donde se comprueba el inicio de sesión.
     */
    private UsuarioDB usDB;
    
    /**
     * Constructor de la clase Controlador Login que establece los Listener y referencias principales.
     * @param vtn Ventana de inciio de sesión sobre la que se aplican los Litener.
     */
    public ControladorLogin(Loging vtn)
    {
        super(vtn.getPadre());
        ventana = vtn;
        setIniciarListener();
        setCancelarListener();
        setCrearCuentaListener();
        setFondo(ventana);
        usDB = new UsuarioDB();
        asignarKeyEvent(ventana);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Listener Teclado">
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
                if(ke.getKeyCode() == KeyEvent.VK_ENTER)
                    ventana.getLogin().doClick();
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
    
    //<editor-fold defaultstate="collapsed" desc="Listener Iniciar">
    /**
     * Configura las comprobaciones y el inicio de sesión de un usuario cunado este ha introducido los valores.
     * <br/>
     * Comprueba que se hayan introducido ambos campos pedidos, sea nombre de usuario o correo y contraseña.
     * <br/>
     * En caso de que esto se haya introducido se crea un usuario con estos valores, llama a la Base de Datos de la aplicación y comprueba si existe un usuario con ese usuario/correo y esa contraseña.
     * <br/>
     * Si este es el caso, se cambia toda la aplicación para reflejar el cambio. Si no es así, se suma un fallo al contador.
     */
    private void setIniciarListener()
    {
        ventana.getLogin().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if(ventana.getUsername().getText().equals("") || String.valueOf(ventana.getPassword().getPassword()).equals(""))
                {
                    configurarJOptionPane("Hay que introducir usuario/correo y contraseña", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION, Error.FAIL);
                    getJd().setVisible(true);
                }
                else
                {
                    Usuario p = new Persona(ventana.getUsername().getText(), String.valueOf(ventana.getPassword().getPassword())); //El tipo de usuario creado aqui es igual, ya que solo vale para obtener el Hash de la contraseña
                    p = usDB.comprobarUsuario(p.getUsername(),p.getPassword());
                    if(p == null)
                    {
                        configurarJOptionPane(Error.NEXISTUSER + " o la contraseña es incorrecta", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION, Error.FAIL);
                        jd.setVisible(true);
                        ventana.setContadorFallos(ventana.getContadorFallos()+1);
                        desactivarBotonLogin();
                    }
                    else
                    {
                        ventana.getPadre().setUsuario(p);
                        sesionIniciada();
                        ventana.getPadre().getNombreUsuario().setPreferredSize(new Dimension(ventana.getPadre().getIniciarSesion().getWidth()+ventana.getPadre().getRegistrarse().getWidth()+18, ventana.getPadre().getNombreUsuario().getHeight()));
                        ventana.dispose();
                    }
                }
            }
        });
    }
    
    /**
     * Desactiva el botón de Iniciar si el usuario se ha equivocado tres veces.
     */
    private void desactivarBotonLogin()
    {
        if(ventana.getContadorFallos()%3 == 0)
        {
            ventana.getLogin().setEnabled(false);
            ventana.getLogin().setToolTipText(Textos.RETRYLATER);
            t = ventana.getTime();
            t = new Timer(1000*60, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    ventana.getLogin().setEnabled(true);
                    ventana.getLogin().setToolTipText("");
                    t.stop();
                } 
            });
            t.start();
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Listener Cancelar">
    /**
     * Configura el funcionamiento de la aplicación si se cierra esta ventana.
     */
    private void setCancelarListener()
    {
        ventana.getCancel().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                ventana.getPadre().cerrarHijo(ventana);
            }
        });
        
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
    
    //<editor-fold defaultstate="collapsed" desc="Listener Crear Usuario">
    /**
     * Abre una ventana de creación de usuario en caso de que no tenga todavía una cuenta de usuario.
     */
    private void setCrearCuentaListener()
    {
        ventana.getCrearCuenta().addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                CrearUsuario cu = ventana.getPadre().getRegistrar();
                if(cu == null)
                {
                    cu = new CrearUsuario(ventana.getPadre());
                    new ControladorCrearUsuario(cu);
                }
                cu.setVisible(true);
                ventana.getPadre().cerrarHijo(ventana);
            }
        });
    }
    //</editor-fold>
}
