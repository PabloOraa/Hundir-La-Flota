package Modelo;

/**
 * Interfaz con los mensajes de error de la aplicación
 * @author Pablo Oraa Lopez
 */
public interface Error
{
    /**
     * Mensaje de Error con el nombre "Error" ccuando es general y no específico.
     */
    String FAIL = "Error";
    
    /**
     * Mensaje de error "No existe el usuario" cuando el Login tiene algún problema para encontrar el usuario.
     */
    String NEXISTUSER = "No existe el usuario";
    
    /**
     * Mensaje de error "Usuario o contraseña incorrectos" cuando se equivoca al introducir un dato.
     */
    String USERPASSFAIL = "Usuario o contraseña incorrectos";
    /**
     * Mensaje de Error "Hay que introducir el usuario y la contraseña" cuando no ha introducido el usuario o la contraseña
     */
    String NOUSERPASS = "Hay que introducir el usuario y la contraseña";
    /**
     * Mensaje de Error "Hay que repetir la contraseña" cuando no ha repetido la contraseña
     */
    String NOPASSREPEAT = "Hay que repetir la contraseña";
    /**
     * Mensaje de Error "Las contraseñas no coinciden" cuando las dos contraseñas introducidas no son iguales
     */
    String NOTSAMEPASS = "Las contraseñas no coinciden";
    /**
     * Mensaje de Error "Hay que seleccionar un tipo de usuarios" cuando no se ha seleccionado el tipo del usuario
     */
    String NOTYPESELECTED = "Hay que seleccionar un tipo de usuarios";
    /**
     * Mensaje de Error "Ese correo electrónico está siendo usado actualmente" cuando el correo electrónico indicado ya existe
     */
    String MAILEXISTS = "Ese correo electrónico está siendo usado actualmente";
    /**
     * Mensaje de Error "Ese usuario ya existe" cuando el usuario indicado ya existe
     */
    String USEREXISTS = "Ese usuario ya existe";
    /**
     * Mensaje de Error "Hay que introducir un nombre" cuando el usuario no haya introducido el nombre
     */
    String NONAME = "Hay que introducir un nombre";
    /**
     * Mensaje de Error "Hay que introducir un correo" cuando el usuario no haya introducido el correo
     */
    String NOMAIL = "Hay que introducir un correo";
    /**
     * Mensaje de Error "Hay que introducir un DNI" cuando la persona no haya introducido uno y sea tipo Persona
     */
    String NODNI = "Hay que introducir un DNI";
    /**
     * Mensaje de Error "Hay que introducir un CIF" cuando la empresa no haya introducido uno y sea tipo Empresa
     */
    String NOCIF = "Hay que introducir un CIF";
    /**
     * Mensaje de error en la conexión de la base de datos
     */
    String CONNECTIONFAILED = "Error al conectarse a la base de datos. Intentelo de nuevo en unos instantes.";
    /**
     * Mensaje que indica que no se han obtenido resultados
     */
    String NODATAFOUND = "No se han encontrado resultados.";
}
