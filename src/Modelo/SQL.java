package Modelo;

/**
 * @author Pablo Oraa Lopez
 */
public interface SQL
{
    /**
     * Cadena que permite la conexión con la base de datos de Oracle
     * en el ordenador con IP 172.26.3.118.
     */
    //String URL = "jdbc:oracle:thin:@172.26.3.118:1521:orcl";
    String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    /**
     * Usuario de pruebas de la base de datos con acceso a todas las tablas 
     */
    //String USERPRUEBA = "ProyectoBD";
    String USERPRUEBA = "ProyectoDB";
    /**
     * Contraseña del usuario de pruebas de la base de datos con acceso a todas las tablas 
     */
    String PASSPRUEBA = "ProyectoBD";
    
    /*CONSULTAS A LA BASE DE DATOS*/
    
    /**
     * Consulta SQL para obtener todas las ofertas de la base de datos
     */
    String OBTENERTODASOFERTAS = "SELECT * FROM "+SQL.USERPRUEBA+".OFERTA";
    /**
     * Consulta SQL para obtener todos los Curriculum de la base de datos
     */
    String OBTENERTODOSCURRICULUM = "SELECT * FROM "+SQL.USERPRUEBA+".CURRICULUM";
    /**
     * Consulta SQL para obtener las ofertas en base a una busqueda por parte del usuario
     */
    String OBTENEROFERTASFILTRO = "SELECT * FROM "+SQL.USERPRUEBA+".OFERTA WHERE PUESTO LIKE ? OR DESCRIPCION LIKE ?";
    /**
     * Consulta SQL para obtener los Curriculum de un usuario
     */
    String OBTENERCURRICULUMPERSONA = "SELECT * FROM "+SQL.USERPRUEBA+".CURRICULUM WHERE DNI = ? ORDER BY FECHA_DE_ACTUALIZACION";
    /**
     * Consulta SQL para obtener las ofertas de una empresa
     */
    String OBTENEROFERTASEMPRESA = "SELECT * FROM "+SQL.USERPRUEBA+".OFERTA WHERE CIF = ?";
    /**
     * Consulta SQL para ver los datos de un usuario
     */
    String OBTENERINFORMACIONUSUARIO = "SELECT * FROM "+SQL.USERPRUEBA+".USUARIO WHERE NOMBRE_USUARIO = ?";
    /**
     * Consulta SQL para ver los datos de un usuario a partir de su nombre
     */
    String OBTENERINFORMACIONUSUARIONAME = "SELECT * FROM "+SQL.USERPRUEBA+".USUARIO WHERE NOMBRE = ?";
    /**
     * Consulta SQL para ver los datos de una persona
     */
    String OBTENERINFORMACIONPERSONA = "SELECT * FROM "+SQL.USERPRUEBA+".PERSONA WHERE NOMBRE_USUARIO = ?";
    /**
     * Consulta SQL para ver los datos de una empresa
     */
    String OBTENERINFORMACIONEMPRESA = "SELECT * FROM "+SQL.USERPRUEBA+".EMPRESA WHERE NOMBRE_USUARIO = ?";
    /**
     * Consulta SQL para comprobar los datos del Login
     */
    String COMPROBARLOGIN = "SELECT * FROM "+SQL.USERPRUEBA+".USUARIO WHERE (NOMBRE_USUARIO = ? OR CORREO_ELECTRONICO = ?) AND CONTRASENA = ?";
    /**
     * Consulta SQL para comprobar los datos del Login con el correo electrónico
     */
    String COMPROBARLOGINCORREO = "SELECT * FROM "+SQL.USERPRUEBA+".USUARIO WHERE CORREO_ELECTRONICO = ?";
    /**
     * Consulta SQL para comprobar los datos de un usuario persona
     */
    String OBTENERINFORMACIONPERSONAUSER = "SELECT * FROM "+SQL.USERPRUEBA+".USUARIO, "+SQL.USERPRUEBA+".PERSONA WHERE PERSONA.NOMBRE_USUARIO = USUARIO.NOMBRE_USUARIO AND (PERSONA.NOMBRE_USUARIO = ? OR CORREO_ELECTRONICO = ?)";
    /**
     * Consulta SQL para comprobar los datos de un usuario empresa
     */
    String OBTENERINFORMACIONEMPRESAUSER = "SELECT * FROM "+SQL.USERPRUEBA+".USUARIO, "+SQL.USERPRUEBA+".EMPRESA WHERE EMPRESA.NOMBRE_USUARIO = USUARIO.NOMBRE_USUARIO AND (EMPRESA.NOMBRE_USUARIO = ? OR CORREO_ELECTRONICO = ?)";
    /**
     * Consulta SQL para comprobar obtener el duseño de un curriculum.
     */
    String OBETENERCREADORCURRICULUM = "SELECT * FROM "+SQL.USERPRUEBA+".USUARIO,"+SQL.USERPRUEBA+".PERSONA WHERE USUARIO.NOMBRE_USUARIO = PERSONA.NOMBRE_USUARIO AND DNI = ?";
    /**
     * Consulta SQL para obtener los datos de un usuario
     */
    String OBTENERDATOSUSUARIO = "SELECT OFERTA.* FROM "+SQL.USERPRUEBA+".SE_INSCRIBEN, "+SQL.USERPRUEBA+".OFERTA WHERE OFERTA.ID_OFERTA = SE_INSCRIBEN.ID_OFERTA AND DNI = ?";
    /**
     * Consulta SQL para obtener el nombre de la empresa que ha creado una oferta
     */
    String OBTENERNOMBREEMPRESA = "SELECT Nombre FROM "+SQL.USERPRUEBA+".USUARIO,"+SQL.USERPRUEBA+".EMPRESA,"+SQL.USERPRUEBA+".OFERTA WHERE USUARIO.NOMBRE_USUARIO = EMPRESA.NOMBRE_USUARIO AND OFERTA.CIF = EMPRESA.CIF AND OFERTA.CIF = ?";
    
    /*INSERCION DE DATOS*/
    
    /**
     * Consulta SQL para añadir una Oferta
     */
    String INSERTAROFERTA = "INSERT INTO "+SQL.USERPRUEBA+".OFERTA VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    /**
     * Consulta SQL para añadir un Curriculum
     */
    String INSERTARCURRICULUM = "INSERT INTO "+SQL.USERPRUEBA+".CURRICULUM VALUES (?,?,?,?,?,?,?,?)";
    /**
     * Consulta SQL para añadir un usuario
     */
    String INSERTADDUSER = "INSERT INTO "+SQL.USERPRUEBA+".USUARIO VALUES (?,?,?,?,?,?,?,?)";
    /**
     * Consulta SQL para añadir una persona
     */
    String INSERTPERSON = "INSERT INTO "+SQL.USERPRUEBA+".PERSONA VALUES (?,?,?,?,?,?,?)";
    /**
     * Consulta SQL para añadir una empresa
     */
    String INSERTENTERPRISE = "INSERT INTO "+SQL.USERPRUEBA+".EMPRESA VALUES (?,?,?,?,?)";
    /**
     * Consulta SQL para añadir una inscripción a una oferta
     */
    String INSERTINSCRIPCION = "INSERT INTO "+SQL.USERPRUEBA+".SE_INSCRIBEN VALUES (?,?,?,?)";
    
    /*BORRADO DE DATOS*/
    
    /**
     * Consulta SQL para borrar una oferta
     */
    String DELETEOFERTA = "DELETE FROM "+SQL.USERPRUEBA+".OFERTA WHERE ID_OFERTA = ?";
    /**
     * Consulta SQL para borrar un usuario
     */
    String DELETEUSER = "DELETE FROM "+SQL.USERPRUEBA+".USUARIO WHERE NOMBRE_USUARIO = ?";
    /**
     * Consulta SQL para borrar una empresa
     */
    String DELETEENTERPRISE = "DELETE FROM "+SQL.USERPRUEBA+".EMPRESA WHERE CIF = ?";
    /**
     * Consulta SQL para borrar una persona
     */
    String DELETEPERSON = "DELETE FROM "+SQL.USERPRUEBA+".PERSONA WHERE DNI = ?";
    /**
     * Consulta SQL para borrar un Curriculum
     */
    String DELETECURRICULUM = "DELETE FROM "+SQL.USERPRUEBA+".CURRICULUM WHERE ID_CURRICULUM = ?";
    /**
     * Consulta SQL para borrar la inscripción a una oferta
     */
    String DELTERESEINSCRIBEN = "DELETE FROM "+SQL.USERPRUEBA+".SE_INSCRIBEN WHERE ID_OFERTA = ? AND DNI = ?";
    /**
     * Consulta SQL para borrar un Usuario
     */
    String DELETEACOUNT = "DELETE FROM "+SQL.USERPRUEBA+".USUARIO WHERE NOMBRE_USUARIO = ?";

    /*ACTUALIZACION DE DATOS*/
    
    /**
     * Consulta SQL para actualizar los datos de una oferta.
     */
    String UPDATEOFERTA = "UPDATE "+SQL.USERPRUEBA+".OFERTA SET PUESTO = ?, DESCRIPCION = ?, NUMERO_VACANTES = ?, UBICACION = ?, REQUISITOS = ?, SALARIO = ?, JORNADA = ?, VACACIONES = ? WHERE ID_OFERTA = ?";
    /**
     * Consulta SQL para actualizar los datos de un Curriculum.
     */
    String UPDATECURRICULUM = "UPDATE "+SQL.USERPRUEBA+".CURRICULUM SET FECHA_DE_ACTUALIZACION = ?, EXPERIENCIA_LABORAL = ?, FORMACION = ?, IDIOMAS = ?, GUSTOS_Y_AFICIONES = ?, OTROS_DATOS = ? WHERE ID_CURRICULUM = ?";
    /**
     * Consulta SQL para actualizar los datos de un Usuario.
     */
    String UPDATEUSUARIO = "UPDATE "+SQL.USERPRUEBA+".USUARIO SET NOMBRE = ?, DIRECCION = ?, CIUDAD = ?, TELEFONO = ?, CORREO_ELECTRONICO = ?, IMAGEN = ? WHERE NOMBRE_USUARIO = ?";
    /**
     * Consulta SQL para actualizar los datos de una Persona.
     */
    String UPDATEPERSONA = "UPDATE "+SQL.USERPRUEBA+".PERSONA PRIMERO_APELLIDO = ?, SEGUNDO_APELLIDO = ?, FECHA_NACIMIENTO = ?, NACIONALIDAD = ?, PERMISO_DE_CONDUCIR = ? WHERE NOMBRE_USUARIO = ?";
    /**
     * Consulta SQL para actualizar los datos de una Empresa.
     */
    String UPDATEEMPRESA = "UPDATE "+SQL.USERPRUEBA+".EMPRESA SET INFORMACION_ADICIONAL = ?, PERSONA_DE_CONTACTO = ?, URL = ?";
    /**
     * Consulta SQL para añadir un usuario al número de inscritos.
     */
    String ADDINSCRITO = "UPDATE "+SQL.USERPRUEBA+".OFERTA SET NUMERO_INSCRITOS = NUMERO_INSCRITOS + 1 WHERE ID_OFERTA = ?";
}
