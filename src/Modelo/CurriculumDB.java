package Modelo;

import Entidades.Curriculum;
import Entidades.Persona;
import Entidades.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * @author Pablo Oraa López
 */
public class CurriculumDB
{
    /**
     * ArrayList para guarlas la lista de Curriculum de un usuario.
     */
    private ArrayList<Curriculum> lista = new ArrayList<>();
    
    /**
     * Inserta el curriculum en la base de datos.
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @param cu Curriculum a Insertar
     */
    public void addCurriculum(Usuario p, Curriculum cu)
    {
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL, p != null ? p.getUsuario():SQL.USERPRUEBA,p != null ? p.getPass():SQL.PASSPRUEBA).prepareStatement(SQL.INSERTARCURRICULUM))
        {
            st.setString(1, String.valueOf(cu.getId_Curriculum()));
            st.setString(2, cu.getDNI());
            st.setDate(3, new Date(cu.getFechaActualizacion().getTime()));
            st.setString(4, cu.getExperienciaLaboral());
            st.setString(5, cu.getFormacion());
            st.setString(6, cu.getIdiomas());
            st.setString(7, cu.getGustosyAficiones());
            st.setString(8, cu.getOtrosDatos());
            st.executeQuery();
        } catch (SQLException ex)
        {
            System.err.println(Error.CONNECTIONFAILED);
        }
    }
    
    /**
     * Obtiene la lista de los Curriculum de un usuario a partir de un DNI
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @param DNI Numero de indentificación del Usuario del que se quieren buscar los Curriculum.
     * @return ArrayList con los diferentes Curriculum guardados por un usuario.
     */
    public ArrayList<Curriculum> obtenerCurriculum(Usuario p, String DNI)
    {
        return buscarCurriculum(p, DNI); 
    }
    
    /**
     * Obtenemos el último ID de los Curriculum guardados en la base de datos.
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @return Integer con el ID a ocupar en la inserción.
     */
    public int obtenerIdUltimo(Usuario p)
    {
        int i = 0;
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL, p != null ? p.getUsuario():SQL.USERPRUEBA,p != null ? p.getPass():SQL.PASSPRUEBA).prepareStatement(SQL.OBTENERTODOSCURRICULUM))
        {
            ResultSet rs = st.executeQuery();
            while(rs.next())
                i++;
            i++;
            return i;
        } catch (SQLException ex)
        {
            System.err.println(Error.CONNECTIONFAILED);
            return i;
        }
    }
    
    /**
     * Obtiene la lista de los Curriculum de un usuario a partir de un DNI
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @param DNI Numero de indentificación del Usuario del que se quieren buscar los Curriculum.
     * @return ArrayList con los diferentes Curriculum guardados por un usuario.
     */
    private ArrayList<Curriculum> buscarCurriculum(Usuario p,String DNI)
    {
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL, p != null ? p.getUsuario():SQL.USERPRUEBA,p != null ? p.getPass():SQL.PASSPRUEBA).prepareStatement(SQL.OBTENERCURRICULUMPERSONA))
        {
            st.setString(1, DNI);
            ResultSet rs = st.executeQuery();
            Curriculum e;
            ArrayList<Curriculum> list = new ArrayList<>();
            while(rs.next())
            {
                e = new Curriculum(rs.getInt("ID_Curriculum"), DNI);
                e.setFechaActualizacion(rs.getDate("Fecha_de_Actualizacion"));
                e.setFormacion(rs.getString("Formacion"));
                e.setIdiomas(rs.getString("Idiomas"));
                e.setOtrosDatos(rs.getString("Otros_Datos"));
                e.setExperienciaLaboral(rs.getString("Experiencia_Laboral"));
                e.setGustosyAficiones(rs.getString("Gustos_Y_AFICIONES"));
                list.add(e);
            }
            return list;
        } catch (SQLException ex)
        {
            System.err.println(Error.FAIL);
            return null;
        }
    }
    
    /**
     * Obtiene un usuario a partir del DNI 
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @param DNI Numero de indentificación del Usuario a bsucar.
     * @return Usuario obtenido de la base de datos.
     */
    public Usuario getDueñoCurriculum(Usuario p,String DNI)
    {
        Persona per = null;
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL, p != null ? p.getUsuario():SQL.USERPRUEBA,p != null ? p.getPass():SQL.PASSPRUEBA).prepareStatement(SQL.OBETENERCREADORCURRICULUM))
        {
            st.setString(1, DNI);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                //Existe Persona
                per = new Persona(rs.getString("Nombre_Usuario"),rs.getString("Contrasena"),rs.getString("Correo_Electronico"),rs.getString("Nombre"),rs.getString("Imagen") == null ? null :new ImageIcon(rs.getString("Imagen")), rs.getString("DNI"));
                per.setPassword(rs.getString("Contrasena"));
                per.setCiudad(rs.getString("Ciudad"));
                per.setDireccion(rs.getString("Direccion"));
                per.setTelefono(rs.getString("Telefono"));
                per.setPrimerApellido(rs.getString("Primer_Apellido"));
                per.setSegundoApellido(rs.getString("Segundo_Apellido"));
                per.setFecha_Nacimiento(rs.getDate("Fecha_Nacimiento"));
                per.setNacionalidad(rs.getString("Nacionalidad"));
                int conducir = rs.getInt("Permiso_De_Conducir");
                per.setPermisoConduccion(conducir==1);
            }
            return per;
        }catch (SQLException ex)
        {
            return null;
        }
    }
    
    /**
     * Obtiene un Usuario con todos los datos de una Persona a partir del nombre que tiene ese usuario.
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @param nombre Nombre sobre el que se quieren obtener los datos.
     * @return Usuario obtenido de la base de datos.
     */
    public Usuario getDueñoCurriculumName(Usuario p, String nombre)
    {
        Persona per = null;
        try(Connection con = DriverManager.getConnection(SQL.URL, p != null ? p.getUsuario():SQL.USERPRUEBA,p != null ? p.getPass():SQL.PASSPRUEBA);
                PreparedStatement st = con.prepareStatement(SQL.OBTENERINFORMACIONUSUARIONAME);
                PreparedStatement stU = con.prepareStatement(SQL.OBTENERINFORMACIONPERSONAUSER))
        {
            st.setString(1, nombre);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                stU.setString(1, rs.getString("Nombre_Usuario"));
                stU.setString(2, rs.getString("Nombre_Usuario"));
                ResultSet rsP = stU.executeQuery();
                while(rsP.next())
                {
                    //Existe Persona
                    per = new Persona(rs.getString("Nombre_Usuario"),rs.getString("Contrasena"),rs.getString("Correo_Electronico"),rs.getString("Nombre"),rs.getString("Imagen") == null ? null :new ImageIcon(rs.getString("Imagen")), rsP.getString("DNI"));
                    per.setPassword(rs.getString("Contrasena"));
                    per.setCiudad(rs.getString("Ciudad"));
                    per.setDireccion(rs.getString("Direccion"));
                    per.setTelefono(rs.getString("Telefono"));
                    per.setPrimerApellido(rsP.getString("Primer_Apellido"));
                    per.setSegundoApellido(rsP.getString("Segundo_Apellido"));
                    per.setFecha_Nacimiento(rsP.getDate("Fecha_Nacimiento"));
                    per.setNacionalidad(rsP.getString("Nacionalidad"));
                    int conducir = rsP.getInt("Permiso_De_Conducir");
                    per.setPermisoConduccion(conducir==1);
                }
            }
            return per;
        }
         catch (SQLException ex)
        {
            return null;
        }
    }
    
    /**
     * Borra el curriculum seleccionado por el usuario.
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @param id_curriculum ID del ciurriculum a borrar.
     */
    public void borrarCurriculum(Usuario p, int id_curriculum)
    {
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL, p != null ? p.getUsuario():SQL.USERPRUEBA,p != null ? p.getPass():SQL.PASSPRUEBA).prepareStatement(SQL.DELETECURRICULUM))
        {
            st.setInt(1, id_curriculum);
            st.executeUpdate();
        }catch(SQLException ex)
        {
            System.err.println(Error.CONNECTIONFAILED);
        }
    }
    
    /**
     * Actualiza el Curriculum que desee el Usuario.
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @param cu Curriculum con los datos actualizados para llevarlos a la base de datos
     */
    public void actualizarCurriculum(Usuario p, Curriculum cu)
    {
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL, p != null ? p.getUsuario():SQL.USERPRUEBA,p != null ? p.getPass():SQL.PASSPRUEBA).prepareStatement(SQL.UPDATECURRICULUM))
        {
            st.setDate(1, new Date(cu.getFechaActualizacion().getTime()));
            st.setString(2, cu.getExperienciaLaboral());
            st.setString(3, cu.getFormacion());
            st.setString(4, cu.getIdiomas());
            st.setString(5, cu.getGustosyAficiones());
            st.setString(6, cu.getOtrosDatos());
            st.setInt(7, cu.getId_Curriculum());
            st.executeUpdate();
        }catch(SQLException ex)
        {
            System.err.println(Error.CONNECTIONFAILED);
        }
    }
}
