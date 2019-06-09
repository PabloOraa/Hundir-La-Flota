package Controlador;

import Entidades.Curriculum;
import Entidades.Empresa;
import Entidades.Oferta;
import Entidades.Persona;
import Entidades.Usuario;
import Modelo.CurriculumDB;
import Modelo.OfertasDB;
import Modelo.Textos;
import Modelo.Error;
import Vista.Configuracion;
import Vista.Loging;
import Vista.VentanaPrincipal;
import static Vista.VentanaPrincipal.getColorDefecto;
import Vista.CrearUsuario;
import Vista.CrearCurriculum;
import Vista.Ofertas;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Pablo Oraa Lopez
 */
public class ControladorVentanaPrincipal extends Controlador
{
    /**
     * Referencia a la Ventana Principal de la aplicación.
     */
    private VentanaPrincipal ventana;
    /**
     * Lista de JTextArea que muestra los datos de Ofertas o Curriculum en la aplicacion.
     */
    private ArrayList<JTextArea> resultados = new ArrayList<>();
    /**
     * Conexión con las funciones sobre las Ofertas de la Base de Datos de la aplicación.
     */
    private OfertasDB ofDB = new OfertasDB();
    /**
     * Conexión con las funciones sobre las Curriculum de la Base de Datos de la aplicación.
     */
    private CurriculumDB cuDB = new CurriculumDB();
    /**
     * Texto antiguo de la formación.
     */
    private String viejoTextoFormacion;
    /**
     * Texto antiguo de la experiencia laboral.
     */
    private String viejoTextoExperiencia;
    /**
     * Texto antiguo de los idiomas.
     */
    private String viejoTextoIdiomas;
    /**
     * Texto antiguo del puesto de la oferta.
     */
    private String viejoTextoPuesto;
    /**
     * Numero de curriculum de un usuario.
     */
    private int numeroCurriculum;
    
    /**
     * Constructor que cambia las opciones del menú hamburguesa/Menú desplegable de la ventana Principal una vez que ha iniciado sesión.
     * @param vp Ventana Principal de la aplicación.
     * @param user Usuario que ha inciiado sesión.
     */
    public ControladorVentanaPrincipal(VentanaPrincipal vp, Usuario user)
    {
        super(vp);
        ventana = vp;
        vp.setUsuario(user);
        configurarLista();
    }
    
    /**
     * Constructor del controlador de la Ventana Principal.
     * @param vistaVP Ventana Principal sobre la que asignar los Listener y valores de configuración en cuestión.
     */
    public ControladorVentanaPrincipal(VentanaPrincipal vistaVP)
    {
        super(vistaVP);
        ventana = vistaVP;
        configurarListener();
        configurarElementosInterfaz();
        obtenerOfertas();
        ocultarVentanas();
        mostrarCamposExtra("");
        ocultarArray();
        ventana.getOfertasScroll().setVisible(true);
        ventana.getPanelInvisible().setVisible(true);
    }
    
    /**
     * Oculta los paneles del panel principal para poder cambiar entre las diferentes opciones disponibles.
     */
    private void ocultarVentanas()
    {
        ventana.getOfertasScroll().setVisible(false);
        ventana.getPanelCurriculum().setVisible(false);
        ventana.getPanelOferta().setVisible(false);
        ventana.getPanelUsuario().setVisible(false);
        ventana.getPanelInvisible().setVisible(false);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Configuracion inicial">
    private void configurarListener()
    {
        setListenerHamburguerMenu();
        setVentanaReescalada();
        configurarLista();
        setListenerBusqueda();
        setListenerIcono();
        setListenerSesion();
        listenerEditar();
        listenerBotonOferta();
        listenerBotonCurriculum();
    }
    
    private void configurarElementosInterfaz()
    {
        ventana.setLocationRelativeTo(null);
        ventana.getHamburguerMenu().setVisible(false);
        ventana.getNombreUsuario().setVisible(false);
        ((FlowLayout)ventana.getBarraSuperior().getLayout()).setAlignment(FlowLayout.LEADING);
        reescalar();
        setColoresVentanas();
    }
    
    public void setColoresVentanas()
    {
        VentanaPrincipal.setColorDefecto(Textos.TEMACLARO);
        VentanaPrincipal.setColorTextoDefecto(Textos.TEMAOSCURO);
        VentanaPrincipal.setColorJTextFieldDefecto(Textos.TOTALWHITE);
        VentanaPrincipal.setColorJTextFieldTextoDefecto(Textos.TOTALBLACK);
        setIconos();
        setFondo(ventana);
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Ofertas">  
    /**
     * Obtiene todas las ofertas disponibles y las muestra en la pantalla.
     */
    public void obtenerOfertas()
    {
        ventana.setMostrarCurriculum(false);
        ventana.getResultados().clear();
        ventana.getResultados().addAll(ofDB.obtenerUltimasOfertas((ventana.getUsuario() == null ? null: ventana.getUsuario())));
        ocultarVentanas();
        ventana.getPanelInvisible().removeAll();
        resultados.clear();
        llenarArray(ventana.getResultados());
        setFondo(ventana.getPanelInvisible());
        ventana.getPanelInvisible().setVisible(true);
        ventana.getOfertasScroll().setVisible(true);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Listener">
    /**
     * Configura los Listener de los botones disponibles dentro de la aplicación respecto a las Ofertas.
     */
    private void listenerBotonOferta()
    {
        ventana.getEditarOferta().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if(ventana.getEditarOferta().getText().equals("Editar"))
                {
                    activarCampos(ventana.getPanelOferta());
                    ventana.getAceptarCambiosOferta().setVisible(true);
                    ventana.getNombreEmpresa().setEditable(false);
                    ventana.getCreacion().setEditable(false);
                    ventana.getInscritos().setEditable(false);
                    ventana.getBorrarOferta().setVisible(true);
                }
                else
                {
                    inscribirse();
                }
            }
        });
        
        ventana.getUrl().addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                if(me.getClickCount() == 2)
                    try 
                    {
                        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE) && !ventana.getUrl().getText().equals("")) 
                        {
                            String url = ventana.getUrl().getText();
                            if(url.startsWith("http://www.") || url.startsWith("https://www."))
                                Desktop.getDesktop().browse(new URI(url));
                            else
                                Desktop.getDesktop().browse(new URI("http://www."+url));
                        }
                    } catch (URISyntaxException | IOException ex) 
                    {
                        
                    }
            }
        });
        
        ventana.getNombreEmpresa().addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                if(me.getClickCount() == 2)
                {
                    ocultarVentanas();
                    mostrarCamposExtra(Textos.ENTERPRISETYPE);
                    desactivarCampos(ventana.getPanelUsuario());
                    llenarDatosUsuario(ofDB.obtenerDatosEmpresa(ventana.getNombreEmpresa().getText()));
                    ventana.getAceptarCambiosUsuario().setVisible(false);
                    ventana.getEditarUsuario().setVisible(false);
                    ventana.getPanelUsuario().setVisible(true);
                }
            }
        });
        
        ventana.getBorrarOferta().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                configurarJOptionPane("¿Seguro que deseas borrar la Oferta?",JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,"Borrar Oferta");
                jd.setVisible(true);
                if(jp.getValue() != null && (Integer)jp.getValue() == JOptionPane.YES_OPTION)
                {
                    Oferta of = obtenerOferta();
                    ofDB.borrarOferta(((Oferta)of).getIdOferta());
                    ocultarVentanas();
                    obtenerOfertas();
                    reescalar();
                    ventana.getPanelInvisible().setVisible(true);
                    ventana.getOfertasScroll().setVisible(true);
                }
            }
        });
        
        ventana.getAceptarCambiosOferta().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                Oferta ofer = obtenerOferta();
                ofer.setPuesto(ventana.getPuesto().getText());
                ofer.setDescripcion(ventana.getDescripcion().getText());
                ofer.setUbicacion(ventana.getUbicacion().getText());
                ofer.setRequisitos(ventana.getRequisitos().getText());
                ofer.setSalario(Double.parseDouble(ventana.getSalario().getText()));
                ofer.setJornada(ventana.getJornada().getText());
                ofer.setNumeroVacantes(Integer.parseInt(ventana.getVacantes().getText()));
                ofer.setDiasVacaciones(Integer.parseInt(ventana.getVacaciones().getText()));
                ofDB.actualizarOferta(ventana.getUsuario(),ofer);
                ocultarVentanas();
                obtenerOfertas();
                reescalar();
                ventana.getPanelInvisible().setVisible(true);
                ventana.getOfertasScroll().setVisible(true);
            }
        });
    }
    
    /**
     * Configura el Listener para obtener a partir del JTextArea los datos de una oferta o de un curriculum.
     */
    private void configurarSeleccionListener()
    {
        for(JTextArea ta: resultados)
            ta.addMouseListener(new MouseAdapter() 
            {
                @Override
                public void mouseClicked(MouseEvent me)
                {
                    for(Object ob : ventana.getResultados())
                        if(ob.toString().equals(ta.getText()))
                            if(!ventana.getMostrarCurriculum())
                                obtenerDatosOferta(((Oferta)ob));
                            else
                                obtenerDatosCurriculum(((Curriculum)ob));
                }
            });
    }
    //</editor-fold>
    
    /**
     * Obtiene una oferta seleccionada por el usuario.
     * @return Objeto Oferta con todos los datos.
     */
    private Oferta obtenerOferta()
    {
        Empresa emp = ofDB.obtenerDatosEmpresa(ventana.getNombreEmpresa().getText());
        for(Object of : ventana.getResultados())
            if(((Oferta)of).getCIF() == emp.getCIF() 
                    && ((Oferta)of).getPuesto().equals(viejoTextoPuesto) 
                    && new SimpleDateFormat("dd/MM/yyyy").format(((Oferta)of).getFecha_Creacion()).equals(ventana.getCreacion().getText()))
                return (Oferta)of;
        return null;
    }
    
    /**
     * Inscribe al usuario en la oferta que se encuentra actualmente.
     */
    private void inscribirse()
    {
        ArrayList<Curriculum> arrc = cuDB.obtenerCurriculum(ventana.getUsuario(), ((Persona)ventana.getUsuario()).getDNI());
        numeroCurriculum = arrc.size();
        if(numeroCurriculum > 0)
        {
            Oferta of = obtenerOferta();
            int i = 1;
            Curriculum curr = null;
            for(Curriculum cu : arrc)
            {
                if(i == numeroCurriculum)
                    curr= cu;
                i++;
            }
            ofDB.addInscripcion(ventana.getUsuario(), curr, of);
            Oferta ofer = (Oferta)of;
            ofDB.addInscrito(ofer);
            configurarJOptionPane("Se ha inscrito correctamente en la oferta", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, "Confirmación");
            getJd().setVisible(true);
        }
        else
        {
            configurarJOptionPane("Hay que tener al menos un curriculum para poder apuntarse", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION, Error.FAIL);
            getJd().setVisible(true);
        }
    }

    /**
     * Obtiene los datos de una Oferta seleccionada por el usuario
     * @param of Oferta a mostrar.
     */
    private void obtenerDatosOferta(Oferta of)
    {
        desactivarCampos(ventana.getPanelOferta());
        ventana.getCreacion().setEditable(false);
        ventana.getEditarOferta().setVisible(false);
        ventana.getBorrarOferta().setVisible(false);
        ventana.getAceptarCambiosOferta().setVisible(false);
        ocultarVentanas();
        if(ventana.getUsuario() != null)
            if(ventana.getUsuario() instanceof Persona)
            {
                ventana.getEditarOferta().setVisible(true);
                ventana.getEditarOferta().setText("Inscribirse");
            }
            else if(ventana.getUsuario() instanceof Empresa && ((Empresa)ventana.getUsuario()).getCIF() == of.getCIF())
            {
                ventana.getEditarOferta().setVisible(true);
                ventana.getEditarOferta().setText("Editar");
                ventana.getBorrarOferta().setVisible(true);
            }
        //<editor-fold defaultstate="collapsed" desc="Introducir los datos en la ventana">
        ventana.getPuesto().setText(of.getPuesto());
        ventana.getDescripcion().setText(of.getDescripcion());
        ventana.getUbicacion().setText(of.getUbicacion());
        ventana.getRequisitos().setText(of.getRequisitos());
        ventana.getSalario().setText(String.valueOf(of.getSalario()));
        ventana.getNombreEmpresa().setText(ofDB.getNombreEmpresa(of));
        ventana.getJornada().setText(of.getJornada());
        ventana.getVacantes().setText(String.valueOf(of.getNumeroVacantes()));
        ventana.getInscritos().setText(String.valueOf(of.getNumeroInscritos()));
        ventana.getVacaciones().setText(String.valueOf(of.getDiasVacaciones()));
        ventana.getCreacion().setText(new SimpleDateFormat("dd/MM/yyyy").format(of.getFecha_Creacion()));
        //</editor-fold>
        viejoTextoPuesto = ventana.getPuesto().getText();
        setFondo(ventana.getPanelOferta());
        ventana.getPanelOferta().setVisible(true);
    }
    
    /**
     * Llena el array de Ofertas/Curriculum con los datos solicitados, ya sea Oferta o Curriculum.
     * @param arr Array con los datos a mostrar.
     */
    private void llenarArray(ArrayList<Object> arr)
    {
        int i = 0;
        reescalar();
        int altura;
        if(arr.size() > 0)
            altura = ventana.getPanelInvisible().getHeight()/arr.size();
        else
            altura = ventana.getPanelInvisible().getHeight();
        ventana.getPanelInvisible().setPreferredSize(new Dimension(940, 520+(altura*(arr.size()-8))));
        for(Object opcion : arr)
        {
            resultados.add((new JTextArea(opcion.toString())));
            resultados.get(i).setEditable(false);
            resultados.get(i).setSize(ventana.getVentanaPrincipal().getWidth(), 50);
            reescalar();
            if(i > 0 && arr.size() < 10)
                resultados.get(i).setLocation(resultados.get(i-1).getX(), resultados.get(i-1).getY() + altura + 5);
            else if(i > 0 && arr.size() >= 10)
                resultados.get(i).setLocation(resultados.get(i-1).getX(), resultados.get(i-1).getY()+55);
            else
                resultados.get(i).setLocation(0,0);
            ventana.getPanelInvisible().add(resultados.get(i));
            i++;
        }
        configurarSeleccionListener();
    }
    
    /**
     * Obtiene las ofertas en las que está inscrito un usuario.
     */
    public void obtenerOfertasInscrito()
    {
        ventana.setMostrarCurriculum(false);
        ventana.getResultados().clear();
        ventana.getResultados().addAll(ofDB.obtenerOfertasUsuarioInscrito(ventana.getUsuario()));
        ocultarVentanas();
        ventana.getPanelInvisible().removeAll();
        resultados.clear();
        llenarArray(ventana.getResultados());
        setFondo(ventana.getPanelInvisible());
        ventana.getPanelInvisible().setVisible(true);
        ventana.getOfertasScroll().setVisible(true);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Curriculum">
    /**
     * Abre el formualrio de creación de un curriculum.
     */
    public void addCurriculum()
    {
        CrearCurriculum curr;
        if(ventana.getCurriculum()== null)
        {
            curr = new CrearCurriculum(ventana);
            ventana.setCurriculum(curr);
            new ControladorCurriculum(ventana.getCurriculum());
        }
        ventana.getCurriculum().setVisible(true);
    }
    
    /**
     * Obtiene la lista de Curriculums de un usuario y los muestra en el Panel Invisible.
     */
    public void verListaCurriculum()
    {
        ventana.setMostrarCurriculum(true);
        ventana.getResultados().clear();
        ventana.getResultados().addAll(cuDB.obtenerCurriculum(ventana.getUsuario(),((Persona)ventana.getUsuario()).getDNI()));
        ocultarVentanas();
        ventana.getPanelInvisible().removeAll();
        resultados.clear();
        llenarArray(ventana.getResultados());
        setFondo(ventana.getPanelInvisible());
        reescalar();
        ventana.getPanelInvisible().setVisible(true);
        ventana.getOfertasScroll().setVisible(true);
    }
    
    /**
     * Obtiene los datos del curriculum que haya pulsado el usuario.
     * @param cu Curriculum del que mostrar los datos.
     */
    private void obtenerDatosCurriculum(Curriculum cu)
    {
        desactivarCampos(ventana.getPanelCurriculum());
        Persona p = (Persona)cuDB.getDueñoCurriculum(ventana.getUsuario(),cu.getDNI());
        ocultarVentanas();
        ventana.getAceptarCambiosCurriculum().setVisible(false);
        ventana.getBorrarCurriculum().setVisible(false);
        //<editor-fold defaultstate="collapsed" desc="Llenar Curriculum">
            ventana.getNombreCurriculum().setText(p.getNombre());
            ventana.getApellido1Curriculum().setText(p.getPrimerApellido());
            ventana.getApellido2Curriculum().setText(p.getSegundoApellido());
            ventana.getCorreo().setText(p.getCorreoElectronico());
            ventana.getActualizacion().setText(new SimpleDateFormat("dd/MM/yyyy").format(cu.getFechaActualizacion()));
            ventana.getFormacion().setText(cu.getFormacion());
            ventana.getExperiencia().setText(cu.getExperienciaLaboral());
            ventana.getIdiomas().setText(cu.getIdiomas());
            ventana.getAficiones().setText(cu.getGustosyAficiones());
            ventana.getOtros().setText(cu.getOtrosDatos());
        //</editor-fold>
        ventana.getPanelCurriculum().setVisible(true);
    }
    
    /**
     * Desactiva los campos de JTextField y JTextArea de visualización de perfil/curriculum/oferta.
     * @param cmp Componente del que desactivar, o no, los campos.
     */
    private void desactivarCampos(Component cmp)
    {
        if(cmp instanceof JTextField)
            ((JTextField)cmp).setEditable(false);
        else if(cmp instanceof JTextArea)
            ((JTextArea)cmp).setEditable(false);
        
        if(cmp instanceof Container)
            for(Component cm : ((Container)cmp).getComponents())
                desactivarCampos(cm);    
    }
    
    /**
     * Activa los campos desactivas en caso de que se pulse el botón Editar.
     * @param cmp Componente del que activar, o no, los campos.
     */
    private void activarCampos(Component cmp)
    {
        if(cmp instanceof JTextField)
            ((JTextField)cmp).setEditable(true);
        else if(cmp instanceof JTextArea)
            ((JTextArea)cmp).setEditable(true);
        
        if(cmp instanceof Container)
            for(Component cm : ((Container)cmp).getComponents())
                activarCampos(cm);    
    }
    
    /**
     * Listener para el funcionamiento del botón Editar, Aceptar, borrar y acceder a los datos de un usuario.
     */
    private void listenerBotonCurriculum()
    {
        ventana.getEditar().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                viejoTextoFormacion = ventana.getFormacion().getText();
                viejoTextoExperiencia = ventana.getExperiencia().getText();
                viejoTextoIdiomas = ventana.getIdiomas().getText();
                activarCampos(ventana.getPanelCurriculum());
                ventana.getAceptarCambiosCurriculum().setVisible(true);
                ventana.getActualizacion().setEditable(false);
                ventana.getNombreCurriculum().setEditable(false);
                ventana.getApellido1Curriculum().setEditable(false);
                ventana.getApellido2Curriculum().setEditable(false);
                ventana.getCorreo().setEditable(false);
            }
        });
        
        ventana.getNombreCurriculum().addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                if(me.getClickCount() == 2)
                {
                    ocultarVentanas();
                    mostrarCamposExtra(Textos.USERTYPE);
                    desactivarCampos(ventana.getPanelUsuario());
                    llenarDatosUsuario(cuDB.getDueñoCurriculumName(ventana.getUsuario(),ventana.getNombreCurriculum().getText()));
                    ventana.getAceptarCambiosUsuario().setVisible(false);
                    ventana.getEditarUsuario().setVisible(false);
                    ventana.getPanelUsuario().setVisible(true);
                }
            }
        });
        
        ventana.getBorrarCurriculum().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                configurarJOptionPane("¿Seguro que deseas borrar el Curriculum?",JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,"Borrar Oferta");
                jd.setVisible(true);
                if(jp.getValue() != null && (Integer)jp.getValue() == JOptionPane.YES_OPTION)
                {
                    Persona p = (Persona)cuDB.getDueñoCurriculumName(ventana.getUsuario(), ventana.getNombreCurriculum().getText());
                    for(Curriculum of : cuDB.obtenerCurriculum(p, ((Persona)(ventana.getUsuario())).getDNI()))
                    {    
                        if(of.getDNI().equals(p.getDNI())
                                && ((of.getFormacion() == null && ventana.getFormacion().getText().equals(""))||(ventana.getFormacion().getText().equals(of.getFormacion())))
                                && ((of.getExperienciaLaboral()== null && ventana.getFormacion().getText().equals(""))||(ventana.getExperiencia().getText().equals(of.getExperienciaLaboral())))
                                && ((of.getIdiomas()== null && ventana.getIdiomas().getText().equals(""))||(ventana.getIdiomas().getText().equals(of.getIdiomas()))))
                                cuDB.borrarCurriculum(ventana.getUsuario(),of.getId_Curriculum());
                        ocultarVentanas();
                        obtenerOfertas();
                        reescalar();
                        ventana.getPanelInvisible().setVisible(true);
                        ventana.getOfertasScroll().setVisible(true);
                    }
                }
            }
        });
        
        ventana.getAceptarCambiosCurriculum().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                Persona p = (Persona)cuDB.getDueñoCurriculumName(ventana.getUsuario(), ventana.getNombreCurriculum().getText());
                for(Curriculum of : cuDB.obtenerCurriculum(p, ((Persona)(ventana.getUsuario())).getDNI()))
                {    
                    if(of.getDNI().equals(p.getDNI())
                            && ((of.getFormacion() == null && viejoTextoFormacion.equals(""))||(viejoTextoFormacion.equals(of.getFormacion())))
                            && ((of.getExperienciaLaboral()== null && viejoTextoExperiencia.equals(""))||(viejoTextoExperiencia.equals(of.getExperienciaLaboral())))
                            && ((of.getIdiomas()== null && viejoTextoIdiomas.equals(""))||(viejoTextoIdiomas.equals(of.getIdiomas()))))
                    {
                        of.setExperienciaLaboral(ventana.getExperiencia().getText());
                        of.setFechaActualizacion(new Date()); //Actualiza la fecha de actualización al día de hoy
                        of.setFormacion(ventana.getFormacion().getText());
                        of.setGustosyAficiones(ventana.getAficiones().getText());
                        of.setIdiomas(ventana.getIdiomas().getText());
                        of.setOtrosDatos(ventana.getOtros().getText());
                        
                        cuDB.actualizarCurriculum(ventana.getUsuario(),of);
                    }
                }
                ocultarVentanas();
                obtenerOfertas();
                reescalar();
                ventana.getPanelInvisible().setVisible(true);
                ventana.getOfertasScroll().setVisible(true);
            }
        });
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Listener del JList">
    private void configurarLista()
    {
        cambiarOpcionesHamburguerMenu();
        ventana.getOpciones().addListSelectionListener(new ListSelectionListener() 
        {
            @Override
            public void valueChanged(ListSelectionEvent lse)
            {
                if(lse.getValueIsAdjusting() && !ventana.getOpciones().isSelectionEmpty())
                {
                    switch((String)ventana.getOpciones().getSelectedValue())
                    {
                        case Textos.EXIT:
                            cerrarApp();
                            break;
                        case Textos.REGISTER:
                            registrarse();
                            break;
                        case Textos.CONFIGURATION:
                            abrirConfiguracion();
                            break;
                        case Textos.SESSIONCLOSE:
                            cerrarSesion();
                            break;
                        case Textos.LOGIN:
                            iniciarSesion();
                            break;
                        case Textos.ADDCURRICULUM:
                            addCurriculum();
                            break;
                        case Textos.ADDOFERTA:
                            addOferta();
                            break;
                        case Textos.GETOFERTAS:
                            obtenerOfertas();
                            break;
                        case Textos.PROFILE:
                            verPerfil();
                            break;
                        case Textos.SHOWCURRICULUM:
                            verListaCurriculum();
                            break;
                        case Textos.OFERTASINSCRITO:
                            obtenerOfertasInscrito();
                            break;
                    }
                    ventana.getOpciones().clearSelection();
                }
            }
        });
    }
    
    public void cerrarSesion()
    {
        configurarJOptionPane(Textos.SESSIONCLOSECONFIRMATION,JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,Textos.SESSIONCLOSECONFIRMATIONTITLE);
        jd.setVisible(true);
        if(jp.getValue() != null && (Integer)jp.getValue() == JOptionPane.YES_OPTION)
        {
            ventana.setUsuario(null);
            sesionIniciada();
            ocultarVentanas();
            reescalar();
            ventana.getPanelInvisible().setVisible(true);
            ventana.getOfertasScroll().setVisible(true);
        }
    }
    
    public void abrirConfiguracion()
    {
        Configuracion config;
        if(ventana.getConfiguracion() == null)
        {
            config = new Configuracion(ventana);
            ventana.setConfiguracion(config);
            new ControladorConfiguracion(ventana.getConfiguracion());
        }
        ventana.getConfiguracion().setVisible(true);
    }
    
    public void cambiarOpcionesHamburguerMenu()
    {
        ventana.setMostrarCurriculum(false);
        String[] lista;
        if(ventana.getUsuario() == null)
            lista = Textos.ACCIONESEMPTY;
        else if(ventana.getUsuario() instanceof Persona)
                lista = Textos.ACCIONESUSER;
            else
                lista = Textos.ACCIONESENTERPRISE;
        ventana.getOpciones().setListData(lista);
    }
    
    public void addOferta()
    {
        Ofertas ofer;
        if(ventana.getOferta()== null)
        {
            ofer = new Ofertas(ventana);
            ventana.setOferta(ofer);
            new ControladorOfertas(ventana.getOferta());
        }
        ventana.getOferta().setVisible(true);
    }
    
    public void verPerfil()
    {
        ventana.setMostrarCurriculum(false);
        ocultarVentanas();
        mostrarCamposExtra(ventana.getUsuario() instanceof Persona ? Textos.USERTYPE:Textos.ENTERPRISETYPE);
        desactivarCampos(ventana.getPanelUsuario());
        llenarDatosUsuario();
        ventana.getAceptarCambiosUsuario().setVisible(false);
        ventana.getPanelUsuario().setVisible(true);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Listener icono usuario">
    /**
     * Listener cuando el usuario pulsa el icono de usuario y abre la ventana de Inciio de Sesión o muestra los datos del usuario.
     */
    private void setListenerIcono()
    {
        ventana.getUsuarioI().addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                if(ventana.getUsuario() == null)
                    ventana.getIniciarSesion().doClick();
                else
                    verPerfil();
            }
        });
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Colocar iconos de los Label">
    /**
     * Coloca los iconos iniciales de la aplicación cuando esta se abre.
     */
    private void setIconos()
    {
        ventana.getMenu().setIcon(new ImageIcon("Recursos/Imagenes/Tema Claro/Hamburguer Menu.png"));
        ventana.getBusquedaI().setIcon(new ImageIcon("Recursos/Imagenes/Tema Claro/Lupa de Busqueda.png"));
        ventana.getUsuarioI().setIcon(new ImageIcon("Recursos/Imagenes/Tema Claro/Perfil no iniciado.png"));
        ventana.getUsuarioI1().setIcon(new ImageIcon("Recursos/Imagenes/Tema Claro/Perfil no iniciado.png"));
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Listener del Hamburguer Menu">
    /**
     * Listener que cambia el icono cuando se activa o desactiva el menú desplegable.
     */
    private void setListenerHamburguerMenu()
    {
        ventana.getMenu().addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                if(ventana.getMenuAbierto())
                {
                    verMenu();
                    if(getColorDefecto() == Textos.TEMACLARO)
                        ventana.getMenu().setIcon(new ImageIcon("Recursos/Imagenes/Tema Claro/Hamburguer Menu.png"));
                    else
                        ventana.getMenu().setIcon(new ImageIcon("Recursos/Imagenes/Tema Oscuro/Hamburguer Menu.png"));
                }
                else
                {
                    verMenu();
                    if(getColorDefecto() == Textos.TEMACLARO)
                        ventana.getMenu().setIcon(new ImageIcon("Recursos/Imagenes/Tema Claro/Cerrar Hamburguer Menu.png"));
                    else
                        ventana.getMenu().setIcon(new ImageIcon("Recursos/Imagenes/Tema Oscuro/Cerrar Hamburguer Menu.png"));
                } 
                ventana.setMenuAbierto(!ventana.getMenuAbierto());
                reescalar();
            }
        });
    }
    
    /**
     * Activa o desactiva el menú desplegable lateral y adapta los elementos en base a lo que ocupa el menú.
     */
    private void verMenu()
    {
        int altura = ventana.getHeight() - (ventana.getBarraSuperior().getHeight() + 50);
        ventana.getHamburguerMenu().setVisible(!ventana.getMenuAbierto());
        ventana.getOpciones().setSize(new Dimension(ventana.getOpciones().getWidth(), altura));
        ventana.getHamburguerMenu().setSize(new Dimension(ventana.getHamburguerMenu().getWidth(), ventana.getHamburguerMenu().getHeight()));
        ventana.getHamburguerMenu().setLocation(0,0);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Listener del reescalado de la ventana">
    /**
     * Listener cuando se reescala la ventana.
     * <br/>
     * De momento no se utiliza al impedir el reescalado de la aplicación.
     */
    private void setVentanaReescalada()
    {
        ventana.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent evt)
            {
                reescalar();
            }
        });
    }
    
    /**
     * Reescala los elementos de la interfaz para que se adapten a toda la pantalla ocupada.
     */
    public void reescalar()
    {
        int altura = ventana.getHeight() - (ventana.getBarraSuperior().getHeight() + 50);
        ventana.getOpciones().setSize(ventana.getOpciones().getWidth(), altura);
        ventana.getHamburguerMenu().setSize(ventana.getOpciones().getWidth(), altura);
        if(ventana.getMenuAbierto())
            ventana.getVentanaPrincipal().setSize(ventana.getWidth()-ventana.getOpciones().getWidth(),altura);
        else
            ventana.getVentanaPrincipal().setSize(ventana.getWidth(),altura);   
        ventana.getOfertasScroll().setSize(ventana.getVentanaPrincipal().getWidth()-6,altura);
        ventana.getOfertas().setSize(ventana.getOfertasScroll().getWidth(),altura);
        if(resultados.size() > 1 && resultados.size() < 10)
            for(JTextArea ja : resultados)
                ja.setSize(ventana.getVentanaPrincipal().getWidth(), ventana.getVentanaPrincipal().getHeight()/resultados.size());
        else if(resultados.size() == 1)
            resultados.get(0).setSize(ventana.getVentanaPrincipal().getWidth(), ventana.getVentanaPrincipal().getHeight());
        else if (resultados.size() >= 10)
            for(JTextArea ja : resultados)
                ja.setSize(ventana.getVentanaPrincipal().getWidth(), 50);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Listener de Sesion y Registro">
    /**
     * Listener de los botones de inicio de sesión y registrarse.
     */
    private void setListenerSesion()
    {
        ventana.getRegistrarse().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                registrarse();
            }
        });
        
        ventana.getIniciarSesion().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                iniciarSesion();
            }
        });
    }
    
    /**
     * Abre la ventana de Inicio de sesión.
     */
    public void iniciarSesion()
    {
        if(ventana.getRegistrar() == null)
        {    
            if(ventana.getInicioSesion() == null)
            {
                ventana.setInicioSesion(new Loging(ventana));
                new ControladorLogin(ventana.getInicioSesion());
            }
            ventana.getInicioSesion().setVisible(true);
        }
    }
    
    /**
     * Abre la ventana de Registro.
     */
    public void registrarse()
    {
        if(ventana.getInicioSesion() == null)
        {  
            if(ventana.getRegistrar() == null)
            {
                ventana.setRegistrar(new CrearUsuario(ventana));
                new ControladorCrearUsuario(ventana.getRegistrar());
            }
            ventana.getRegistrar().setVisible(true);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Listener de Barra de Busqueda">
    /**
     * COnfigura el Listener para que busque una vez se pulsa Enter o se hace click en la lupa.
     */
    private void setListenerBusqueda()
    {
        ventana.getBusquedaI().addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                buscar();
            }
        });
        
        ventana.getBusqueda().addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent ke)
            {
                if(ke.getKeyCode() == KeyEvent.VK_ENTER)
                    buscar();
            }
        });
    }
    
    /**
     * Busca en la base de datos las ofertas en relación a la busqueda indicada por el usuario.
     */
    public void buscar()
    {
        ventana.getResultados().clear();
        ventana.getResultados().addAll(ofDB.obtenerDatosBusqueda(ventana.getUsuario() == null ? null: ventana.getUsuario(),ventana.getBusqueda().getText()));
        ventana.getPanelInvisible().setVisible(false);
        ventana.getPanelInvisible().removeAll();
        resultados.clear();
        llenarArray(ventana.getResultados());
        setFondo(ventana.getPanelInvisible());
        reescalar();
        ventana.getPanelInvisible().setVisible(true);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Cambiar Panel Usuario">
    /**
     * Acciones disponibles cuando se quieren editar los datos del usuario.
     */
    public void listenerEditar()
    {
        ventana.getEditarUsuario().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                activarCampos(ventana.getPanelUsuario());
                ventana.getAceptarCambiosUsuario().setVisible(true);
            }
        });
    }
    
    /**
     * Muestra los campos del visualizado del perfil en función del tipo de usuario que sea.
     * @param type Tipo de usuario que se va a visualizar.
     */
    public void mostrarCamposExtra(String type)
    {
        if(ventana.getArr().size() > 0)
            ocultarArray();
        ventana.getArr().clear();
        llenarArray(type);
        
        for(Component cmp : ventana.getArr())
            cmp.setVisible(true);
    }
    
    /**
     * Hace que todo el array de datos visible en la ventana de visualizado del perfil pase a estar oculto.
     */
    private void ocultarArray()
    {
        for(Component cmp : ventana.getArr())
            cmp.setVisible(false);
    }
    
    /**
     * Selección de los campos que se deben o no mostrar en la vista del perfil.
     * @param tipo Tipo de usuario del que mostrar datos.
     */
    private void llenarArray(String tipo)
    {
        switch (tipo)
        {
            case Textos.USERTYPE:
                ventana.getArr().add(ventana.getApellido3());
                ventana.getArr().add(ventana.getApellido1E1());
                ventana.getArr().add(ventana.getApellido4());
                ventana.getArr().add(ventana.getApellido2E1());
                ventana.getArr().add(ventana.getNacimiento());
                ventana.getArr().add(ventana.getNacimientoE());
                ventana.getArr().add(ventana.getNacionalidad());
                ventana.getArr().add(ventana.getNacionalidadE());
                ventana.getArr().add(ventana.getConducir());
                break;
            case Textos.ENTERPRISETYPE:
                ventana.getArr().add(ventana.getContacto());
                ventana.getArr().add(ventana.getContactoE());
                ventana.getArr().add(ventana.getInformacionE());
                ventana.getArr().add(ventana.getInformacion());
                ventana.getArr().add(ventana.getUrl());
                ventana.getArr().add(ventana.getUrlE());
                ventana.getArr().add(ventana.getjScrollPane10());
                ventana.getArr().add(ventana.getjScrollPane11());
                break;
            default:
                llenarArray(Textos.ENTERPRISETYPE);
                llenarArray(Textos.USERTYPE);
                break;
        }
    }
    
    /**
     * Llenado de los datos del perfil del usuario que interesa ver.
     */
    private void llenarDatosUsuario()
    {
        Usuario u = ventana.getUsuario();
        ventana.getUsuarioI1().setIcon(u.getImagenPerfilDir() == null ? ventana.getUsuarioI().getIcon():new ImageIcon(u.getImagenPerfilDir()));
        ventana.getNombreUser().setText(u.getNombre());
        ventana.getDireccion().setText(u.getDireccion());
        ventana.getTelefono().setText(u.getTelefono());
        ventana.getCorreo1().setText(u.getCorreoElectronico());
        ventana.getCiudad().setText(u.getCiudad());
        if(u instanceof Persona)
        {
            Persona p = (Persona)u;
            ventana.getApellido3().setText(p.getPrimerApellido());
            ventana.getApellido4().setText(p.getSegundoApellido());
            ventana.getNacimiento().setText(p.getFecha_Nacimiento() == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(p.getFecha_Nacimiento()));
            ventana.getNacionalidad().setText(p.getNacionalidad());
            ventana.getConducir().setSelected(p.isPermisoConduccion() == 1);
        }
        else
        {
            Empresa emp = (Empresa)u;
            ventana.getInformacion().setText(emp.getInformacion_Adicional());
            ventana.getContacto().setText(emp.getPersonaContacto());
            ventana.getUrl().setText(emp.getURL());
        }
    }
    
    /**
     * Muestra los datos de un usuario que no sea el que ha iniciado sesión.
     * @param user Usuario del que mostrar datos.
     */
    private void llenarDatosUsuario(Usuario user)
    {
        Usuario viejo = ventana.getUsuario();
        ventana.setUsuario(user);
        llenarDatosUsuario();
        ventana.setUsuario(viejo);
    }
    //</editor-fold>
}
