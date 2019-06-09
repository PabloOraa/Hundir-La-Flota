package Controlador;

import Entidades.Empresa;
import Entidades.Persona;
import Modelo.Textos;
import Modelo.Error;
import Entidades.Usuario;
import Modelo.UsuarioDB;
import Vista.Loging;
import Vista.CrearUsuario;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * @author pabli
 */
public class ControladorCrearUsuario extends Controlador
{
    /**
     * Referencia a la ventana de Crear Usuario.
     */
    private CrearUsuario ventana;
    /**
     * Lista de elementos a ocultar o mostrar dentro del formulario.
     */
    private ArrayList<Component> arr;
    /**
     * Referencia a la clase que accede a la base de datos.
     */
    private UsuarioDB usDB;
    
    /**
     * Contructor de la clase Crear Usuario que configura los Listener correspondientes e incializa las variables.
     * @param cu Ventana Crear Usuariocreada desde la vetnana principal.
     */
    public ControladorCrearUsuario(CrearUsuario cu)
    {
        super(cu.getPadre());
        ventana = cu;
        configurarListener();
        configuracionInicial();
        asignarKeyEvent(ventana);
        usDB = new UsuarioDB();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Listener Crear Usuario Teclado">
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
                    ventana.getCrearCuenta().doClick();
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
    
    //<editor-fold defaultstate="collapsed" desc="Configuración Inicial de la Ventana">
    /**
     * Configura los Listener correspondientes para el formulario de Crear Usuario.
     */
    private void configurarListener()
    {
        setCancelarListener();
        setListenerPassword();
        setImagenListener();
        setIniciarSesionListener();
        setCrearCuentaListener();
        setTiposListener();
    }
    
    /**
     * En función del tipo de usuario seleccionado cambia los campos a introducir por el usuario.
     */
    private void setTiposListener()
    {
        ventana.getTipoPersona().addItemListener(new ItemListener() 
        {
            @Override
            public void itemStateChanged(ItemEvent ie)
            {
                ventana.getJPanel1().setPreferredSize(new Dimension(610,840));
                if(ie.getStateChange() == ItemEvent.SELECTED)
                    mostrarCamposExtra(Textos.USERTYPE);
                else
                    mostrarCamposExtra(Textos.USERTYPE);
            }
        });
        
        ventana.getTipoEmpresa().addItemListener(new ItemListener() 
        {
            @Override
            public void itemStateChanged(ItemEvent ie)
            {
                ventana.getJPanel1().setPreferredSize(new Dimension(610,900));
                if(ie.getStateChange() == ItemEvent.SELECTED)
                    mostrarCamposExtra(Textos.ENTERPRISETYPE);
                else
                    mostrarCamposExtra(Textos.ENTERPRISETYPE);
            }
        });
    }
    
    /**
     * Selecciona los campos a mostrar u ocultar en función de si se trata de un usuario persona o Empresa.
     * @param type 
     */
    private void mostrarCamposExtra(String type)
    {
        arr.clear();
        llenarArray(type);
        
        for(Component cmp : arr)
            cmp.setVisible(!cmp.isVisible());
    }
    
    /**
     * Llena el array de los campos que corresponden a Persona o el que corresponde a Empresa.
     * @param tipo 
     */
    private void llenarArray(String tipo)
    {
        switch (tipo)
        {
            case Textos.USERTYPE:
                arr.add(ventana.getApellido1());
                arr.add(ventana.getApellido1E());
                arr.add(ventana.getApellido2());
                arr.add(ventana.getApellido2E());
                arr.add(ventana.getConducir());
                arr.add(ventana.getConducirE());
                arr.add(ventana.getDNI());
                arr.add(ventana.getDNIE());
                arr.add(ventana.getFechaNacimiento());
                arr.add(ventana.getFechaNacimientoE());
                arr.add(ventana.getNacionalidad());
                arr.add(ventana.getNacionalidadE());
                arr.add(ventana.getCrearCuenta());
                break;
            case Textos.ENTERPRISETYPE:
                arr.add(ventana.getCIF());
                arr.add(ventana.getCIFE());
                arr.add(ventana.getContacto());
                arr.add(ventana.getContactoE());
                arr.add(ventana.getInformacion());
                arr.add(ventana.getInformacionE());
                arr.add(ventana.getjScrollPane3());
                arr.add(ventana.getjScrollPane4());
                arr.add(ventana.getCrearCuenta());
                arr.add(ventana.getURL());
                arr.add(ventana.getURLE());
                break;
            default:
                llenarArray(Textos.USERTYPE);
                llenarArray(Textos.ENTERPRISETYPE);
                arr.remove(ventana.getCrearCuenta());
                break;
        }
    }
    
    /**
     * Configura el formulario para la primera vez que se abre el formulario.
     */
    private void configuracionInicial()
    {
        arr = new ArrayList<>();
        ventana.setLocationRelativeTo(ventana.getPadre());
        setFondo(ventana);
        ventana.getTipoUsuario().add(ventana.getTipoEmpresa());
        ventana.getTipoUsuario().add(ventana.getTipoPersona());
        ventana.getTipoUsuario().clearSelection();
        mostrarCamposExtra("");
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
    
    //<editor-fold defaultstate="collapsed" desc="Listener Contraseña">
    /**
     * COmprueba que las contraseñas se han escrito en ambos campos e igualdad de la contraseña.
     */
    private void setListenerPassword()
    {
        ventana.getPass().addKeyListener(new KeyAdapter()
        {
           @Override
           public void keyReleased(KeyEvent ke)
           {
               comprobarContra();
           }
        });
        
        ventana.getRepeatPass().addKeyListener(new KeyAdapter()
        {
           @Override
           public void keyReleased(KeyEvent ke)
           {
               comprobarContra();
           }
        });
    }
    
    /**
     * Comprueba que la contraseña y repetir la contraseña son iguales, con un mensaje verde o rojo en función de si coinciden o no.
     */
    private void comprobarContra()
    {
        String resultado = "";
        String contra =  String.valueOf(ventana.getPass().getPassword()).equals("") ? null: String.valueOf(ventana.getPass().getPassword());
        String contraRep = String.valueOf(ventana.getRepeatPass().getPassword()).equals("") ? null: String.valueOf(ventana.getRepeatPass().getPassword());
        if(contra != null && contraRep != null)
            if(contra.equals(contraRep))
                resultado = "<html><font color='Green'>Coinciden</font></html>";
            else
                resultado = "<html><font color='Red'>No coinciden</font></html>";
        else
            resultado = "";
        ventana.getCoinciden().setText(resultado);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Listener Imagen">
    /**
     * Listener cuando pulsamos al botón de seleccionar imagen del formulario.
     */
    private void setImagenListener()
    {
        ventana.getSeleccionarImagen().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                elegirArchivo();
            }
        });
    }
    
    /**
     * Crea el JFileChooser con el que se selecciona la imagen.
     */
    private void elegirArchivo()
    {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileNameExtensionFilter jpg = new FileNameExtensionFilter("Imagen jpg", "jpg");
        jfc.addChoosableFileFilter(jpg);
        jfc.setDialogTitle(Textos.CHOOSEFILE);
        int res = jfc.showOpenDialog(ventana);
        
        if(res == JFileChooser.APPROVE_OPTION)
            ventana.setDireccionImagenPerfil(jfc.getSelectedFile().getAbsolutePath());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Listener Inicio Sesion">
    /**
     * Abre la ventana de inicio de sesión en caso de que ya haya una cuenta de inicio de sesión.
     */
    private void setIniciarSesionListener()
    {
        ventana.getIniciarSesion().addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                Loging lg = ventana.getPadre().getInicioSesion();
                if(lg == null)
                {
                    lg = new Loging(ventana.getPadre());
                    new ControladorLogin(lg);
                }
                lg.setVisible(true);
                ventana.getPadre().cerrarHijo(ventana);
            }
        });
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Listener Crear Cuenta">
    /**
     * Listener cuando se presiona el botón de Crear Cuenta.
     */
    private void setCrearCuentaListener()
    {
        ventana.getCrearCuenta().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                crearNuevoUsuario();
            }
        });
    }
    
    /**
     * Obtiene las variables registradas por el usuario y comprueba si se trata de un usuario o de una empresa, con previa comprobación de si ha escrito el DNI o el CIF.
     */
    private void crearNuevoUsuario()
    {
        //<editor-fold defaultstate="collapsed" desc="Variables de usuario">
        String nombre = ventana.getNombre().getText().equals("") ? null: ventana.getNombre().getText();
        String usuario = ventana.getUser().getText().equals("") ? null: ventana.getUser().getText();
        String correo = ventana.getMail().getText().equals("") ? null: ventana.getMail().getText();
        String contra =  String.valueOf(ventana.getPass().getPassword()).equals("") ? null: String.valueOf(ventana.getPass().getPassword());
        String contraRep = String.valueOf(ventana.getRepeatPass().getPassword()).equals("") ? null: String.valueOf(ventana.getRepeatPass().getPassword());
        String tipo = "";
        Enumeration<AbstractButton> listaBotones = ventana.getTipoUsuario().getElements();
        while(listaBotones.hasMoreElements())
        {
            JRadioButton boton = (JRadioButton) listaBotones.nextElement();
            if(boton.isSelected())
                tipo = boton.getText();
        }  
        Icon imagen = ventana.getDireccionImagenPerfil().equals("") ? null: new ImageIcon(ventana.getDireccionImagenPerfil());
        String telefono = ventana.getTelefono().getText().equals("") ? null:ventana.getTelefono().getText();
        String ciudad = ventana.getCiudad().getText().equals("") ? null:ventana.getCiudad().getText();
        String direccion = ventana.getDireccion().getText().equals("") ? null:ventana.getDireccion().getText();
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Variables de persona">
        String DNI = ventana.getDNI().getText().equals("") ? null: ventana.getDNI().getText();
        String primerApellido = ventana.getApellido1().getText().equals("") ? null: ventana.getApellido1().getText();
        String segundoApellido = ventana.getApellido2().getText().equals("") ? null: ventana.getApellido2().getText();
        Date fecha;
        try
        {
            fecha =  ventana.getFechaNacimiento().getText().equals("") ? null: new SimpleDateFormat("dd/MM/yyyy").parse(ventana.getFechaNacimiento().getText());
        } catch (ParseException ex)
        {
            fecha = null;
        }
        String nacionalidad = ventana.getNacionalidad().getText().equals("") ? null: ventana.getNacionalidad().getText();
        boolean permisoConducir = ventana.getConducir().isSelected();
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Variables de empresa">
        Integer CIF = ventana.getCIF().getText().equals("") ? null: Integer.parseInt(ventana.getCIF().getText());
        String informacionEmpresa = ventana.getInformacion().getText().equals("") ? null: ventana.getInformacion().getText();
        String personaContacto = ventana.getContacto().getText().equals("") ? null: ventana.getContacto().getText();
        String url = ventana.getURL().getText().equals("") ? null: ventana.getURL().getText();
        //</editor-fold>
        
        if(comprobarUsuario(nombre,usuario,correo,contra,contraRep,tipo));
            if(tipo.equals(Textos.USERTYPE) && comprobarPersona(DNI))
                crearPersona(nombre,usuario,correo,contra,imagen,telefono,ciudad,direccion,DNI,primerApellido,segundoApellido,fecha,nacionalidad,permisoConducir);
            else if(tipo.equals(Textos.ENTERPRISETYPE) && comprobarEmpresa(CIF))
                crearEmpresa(nombre,usuario,correo,contra,imagen,telefono,ciudad,direccion,CIF,informacionEmpresa,personaContacto, url);
    }
    
    /**
     * Comprueba si un usuario ha introducido todos los datos requeridos para el registro de la aplicación.
     * @param nombre Nombre del usuario.
     * @param usuario Nombre de usuario de la persona o empresa.
     * @param correo Correo de la persona o empresa.
     * @param contra Contraseña introducida por el usuario.
     * @param contraRep Repetición de la contraseña introducida por el usuario.
     * @param tipo Tipo de usuario en función del JRadioButton marcado.
     * @return True si se ha comprobado correctamente o False si hay algún error.
     */
    private boolean comprobarUsuario(String nombre, String usuario, String correo, String contra, String contraRep, String tipo)
    {
        if(usuario == null || contra == null)
            configurarJOptionPane(Error.NOUSERPASS, JOptionPane.ERROR_MESSAGE,JOptionPane.DEFAULT_OPTION,Error.FAIL);
        else if(contraRep == null)
            configurarJOptionPane(Error.NOPASSREPEAT, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION,Error.FAIL);
        else if(!contra.equals(contraRep))
            configurarJOptionPane(Error.NOTSAMEPASS, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION,Error.FAIL);
        else if(tipo.isEmpty())
            configurarJOptionPane(Error.NOTYPESELECTED, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION,Error.FAIL);
        else if(nombre == null)
            configurarJOptionPane(Error.NONAME, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION,Error.FAIL);
        else if(correo == null)
            configurarJOptionPane(Error.NOMAIL, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION,Error.FAIL);
        else
            return true;
        jd.setVisible(true);
        return false;
    }
    
    /**
     * Comprueba si, siendo una persona, ha introducido el DNI.
     * @param DNI Numero de indentificación de una Persona.
     * @return True si lo ha introducido y False si no.
     */
    private boolean comprobarPersona(String DNI)
    {
        if(DNI == null)
            configurarJOptionPane(Error.NODNI, JOptionPane.ERROR_MESSAGE,JOptionPane.DEFAULT_OPTION,Error.FAIL);
       else
            return true;
        jd.setVisible(true);
        return false;
    }
    
    /**
     * Comprueba si, siendo una empresa, ha introducido el CIF.
     * @param CIF Número que indica el código de identificación fisca de una empresa.
     * @return True si ha sido introducio y False si no.
     */
    private boolean comprobarEmpresa(Integer CIF)
    {
        if(CIF == null)
            configurarJOptionPane(Error.NOCIF, JOptionPane.ERROR_MESSAGE,JOptionPane.DEFAULT_OPTION,Error.FAIL);
        else
            return true;
        jd.setVisible(true);
        return false;
    }
    
    /**
     * Crea una persona con los valores introducidos por el usuario, siendo null en caso de no haber introducido nada.
     * @param nombre Nombre del usuario a registrarse
     * @param usuario Nombre de usuario del usuario que quiere registrarse.
     * @param correo Correo del usuario a registrarse.
     * @param contra Contraseña introducida por el usuario que pasará a ser un campo HashCode
     * @param imagen Imagen de perfil del usuario, si ha seleccionado alguna.
     * @param telefono Número de teléfono del usuario registrado. 
     * @param ciudad Ciudad donde se encuentra el usuario.
     * @param direccion Dirección exacta donde vive la persona que inicia sesión.
     * @param DNI Numero de identificación del usuario en cuestión.
     * @param primerApellido Primer Apellido del usuario que se registra.
     * @param segundoApellido Segundo Apellido del usuario que se registra.
     * @param fecha Fecha de nacimiento del usuario.
     * @param nacionalidad Nacionalidad original de la persona.
     * @param permisoConducir Variable booleana que indica si tiene o no permiso de conducción.
     */
    private void crearPersona(String nombre, String usuario, String correo, String contra, Icon imagen, String telefono, String ciudad, String direccion, String DNI, String primerApellido, String segundoApellido, Date fecha, String nacionalidad, boolean permisoConducir)
    {
        Persona p = new Persona(usuario, contra, correo, nombre, (ImageIcon)imagen, DNI);
        p.setTelefono(telefono);
        p.setCiudad(ciudad);
        p.setDireccion(direccion);
        p.setPrimerApellido(primerApellido);
        p.setSegundoApellido(segundoApellido);
        p.setFecha_Nacimiento(fecha);
        p.setNacionalidad(nacionalidad);
        p.setPermisoConduccion(permisoConducir);
        insertarUsuario(p);
    }
    
    /**
     * Crea una persona con los valores introducidos por el usuario, siendo null en caso de no haber introducido nada.
     * @param nombre Nombre del usuario a registrarse
     * @param usuario Nombre de usuario del usuario que quiere registrarse.
     * @param correo Correo del usuario a registrarse.
     * @param contra Contraseña introducida por el usuario que pasará a ser un campo HashCode
     * @param imagen Imagen de perfil del usuario, si ha seleccionado alguna.
     * @param telefono Número de teléfono del usuario registrado. 
     * @param ciudad Ciudad donde se encuentra el usuario.
     * @param direccion Dirección exacta donde vive la persona que inicia sesión.
     * @param CIF Numero de identificación del usuario en cuestión.
     * @param informacionEmpresa Texto que es una pequeña descripción de la empresa.
     * @param personaContacto Nombre de la persona de contacto que servirá para hablar con la empresa.
     * @param url Dirección de la página web de la aplicación.
     */
    private void crearEmpresa(String nombre, String usuario, String correo, String contra, Icon imagen, String telefono, String ciudad, String direccion, Integer CIF, String informacionEmpresa, String personaContacto,String url)
    {
        Empresa p = new Empresa(usuario, contra, correo, nombre, (ImageIcon)imagen, CIF);
        p.setTelefono(telefono);
        p.setCiudad(ciudad);
        p.setDireccion(direccion);
        p.setInformacion_Adicional(informacionEmpresa);
        p.setPersonaContacto(personaContacto);
        p.setURL(url);
        insertarUsuario(p);
    }
    
    /**
     * Añade el usuario a la base de datos y, si no hay ningún problema cambia toda la aplicación para registrar el inicio de sesión.
     * @param user Usuario que se regitsra.
     */
    private void insertarUsuario(Usuario user)
    {
        if(!usDB.addUser(user))
            configurarJOptionPane(Error.USEREXISTS, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION,Error.FAIL);
        else
        {
            configurarJOptionPane(Textos.USERREGISTERED, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,"Confirmación");
            ventana.getPadre().setUsuario(user);
            sesionIniciada();
            ventana.getPadre().cerrarHijo(ventana);
            ventana.dispose();
        }
        jd.setVisible(true);
    }
    //</editor-fold>
}
