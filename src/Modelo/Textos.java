package Modelo;

import java.awt.Color;

/**
 * Textos de interes para la aplicación
 * @author Pablo Oraa López
 */
public interface Textos
{
   /**
    * Numero que indica la versión de la aplicación
    */
   String VERSION = "0.9.8.5";
   /**
    * Creadores de la aplicación Portal de Empleo
    */
   String CREDITS = "Creado por Pablo Oraa Lopez y Enrique Dominguez Prieto";
   /**
    * Nombre con el que se identifica a una Persona
    */
   String USERTYPE = "Persona";
   /**
    * Nombre con el que se identifica a una Empresa
    */
   String ENTERPRISETYPE = "Empresa";
   /**
    * Lista de acciones disponibles para un usuario que no haya iniciado sesión
    */
   String[] ACCIONESEMPTY = {Textos.LOGIN, Textos.REGISTER , Textos.GETOFERTAS,Textos.CONFIGURATION,Textos.EXIT, ""};
   /**
    * Lista de acciones disponibles para un usuario persona.
    */
   String[] ACCIONESUSER = {Textos.PROFILE,Textos.SHOWCURRICULUM, Textos.ADDCURRICULUM, Textos.OFERTASINSCRITO, Textos.GETOFERTAS,Textos.CONFIGURATION,Textos.SESSIONCLOSE, ""};
   /**
    * Lista de acciones disponibles para una empresa
    */
   String[] ACCIONESENTERPRISE = {Textos.PROFILE, Textos.ADDOFERTA, Textos.GETOFERTAS,Textos.CONFIGURATION,Textos.SESSIONCLOSE, ""};
   /**
    * Opción Mi Perfil del hamburguer menu de la aplicación
    */
   String PROFILE = "Mi Perfil";
   /**
    * Opción Modificar datos personales del Hamburguer Menu de la aplicación
    */
   String MODIFYPROFILEUSER = "Modificar datos personales";
   /**
    * Opción Ver Curricumulum del Hamburguer Menu de la aplicación
    */
   String SHOWCURRICULUM = "Ver Curriculum";
   /**
    * Opción Añadir Curriculum del Hamburguer Menu de la aplicación
    */
   String ADDCURRICULUM = "Añadir Curriculum";
   /**
    * Opción Ofertas en las que se ha Inscrito del Hamburguer Menu de la Aplicación
    */
   String OFERTASINSCRITO = "Ofertas que estoy inscrito";
   /**
    * Opcion Modificar información adicional del Hamburguer Menu de la aplicación
    */
   String MODIFYPROFILEENTERPRISE = "Modificar información adicional";
   /**
    * Opción Asignar persona de Contacto del Hambuguer Menu de la Aplicación
    */
   String ASIGNCONTACTNAME = "Asignar persona de contacto";
   /**
    * Opción Añadir Oferta del Hamburguer Menu de la Aplicación
    */
   String ADDOFERTA = "Crear oferta";
   /**
    * Opción para ver todas las ofertas en general
    */
   String GETOFERTAS = "Pagina principal";
   /**
    * Opcion salir del Hamburguer Menu
    */
   String EXIT = "Salir";
   /**
    * Texto mostrado en la confirmación del cierre de la aplicación
    */
   String EXITCONFIRMATION = "¿Estás seguro que deseas salir de la aplicación?";
   /**
    * Tituto de la confirmación del cierre de la confirmación del cierre de la aplicación
    */
   String EXITCONFIRMATIONTITLE = "Cerrar Aplicación";
   /**
    * Opción Cerrar Sesión del Hamburguer Menu
    */
   String SESSIONCLOSE = "Cerrar Sesión"; 
   /**
    * Texto mostrado en la confirmación del cierre de sesión
    */
   String SESSIONCLOSECONFIRMATION = "¿Estás seguro que deseas cerrar sesión?";
   /**
    * Tituto de la confirmación del cierre de la confirmación del cierre de sesión
    */
   String SESSIONCLOSECONFIRMATIONTITLE = "Cerrar Sesión";
   /**
    * Opción Configuración del Hamburguer Menu
    */
   String CONFIGURATION = "Configuración"; 
   /**
    * Opción Iniciar Sesión del Hamburguer Menu
    */
   String LOGIN = "Iniciar Sesión";
    /**
    * Opción Registrarse del Hamburguer Menu
    */
   String REGISTER = "Registrarse";   
   /**
    * Opciones dipsonibles en la configuración
    */
   String[] CONFIGUTARIONSECTION ={Textos.PERSONALIZATION,Textos.ABOUT, Textos.DELETEACOUNT,Textos.EXIT, ""};
   /**
    * Opción Personalización de la Configuración de la Aplicación
    */
   String PERSONALIZATION = "Personalización";
   /**
    * Opción Acerca de de la Configuración de la Aplicación
    */
   String ABOUT = "Acerca de";
   /**
    * Opcion Cerrar Cuenta de la Configuración de la Aplicación
    */
   String DELETEACOUNT = "Borrar Cuenta";
   /**
    * Color 240,240,240 para el tema claro de la aplicacion
    */
   Color TEMACLARO = new Color(240, 240, 240);
   /**
    * Color DARK_GRAY para el tema oscuro de la aplicacion
    */
   Color TEMAOSCURO = Color.BLACK;
   /**
    * Color blanco de la barra de busqueda y texto
    */
   Color TOTALWHITE = Color.WHITE;
   /**
    * Color negro de la barra de busqueda y texto
    */
   Color TOTALBLACK = Color.DARK_GRAY;
   
   /**
    * ArrayList con las clases obviadas para el tema oscuro en negro
    */
   String[] CLASESEXCLUIDAS = {"javax.swing.JTextField","javax.swing.JPasswordField","javax.swing.JButton","javax.swing.JList","javax.swing.JTextArea","javax.swing.JSpinner"};
   
   /**
    * Texto que indica al usuario que vuelta a intentarlo en unos instantes por muchos errores seguidos
    */
   String RETRYLATER = "Demasiados intentos fallidos. Por favor, vuelva a intentarlo en unos instantes";
   /**
    * Texto que indica al usuario que elija la imagen .jpg que desee
    */
   String CHOOSEFILE = "Elige el archivo jpg que quieras de imagen de perfil";
   /**
    * Texto que indica que un usuario se ha registrado correctamente
    */
   String USERREGISTERED = "Se ha registrado correctamente.";
}
