package Modelo;

import Entidades.Empresa;
import Entidades.Persona;
import Entidades.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;

/**
 * @author Pablo Oraa Lopez
 */
public class UsuarioDB
{  
    /**
     * Comprueba que se haya introducido correctamente el usuario/correo y la contraseña de una persona.
     * @param usuario Nombre de usuario/Correo de una persona.
     * @param pass Contraseña introducida por el usuario.
     * @return Usuario encontrado por la aplicación, en caso de ser así.
     */
    public Usuario comprobarUsuario(String usuario, String pass)
    {
        Usuario user = null;
        Persona per = null;
        Empresa emp = null;
        boolean persona = false;
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL, SQL.USERPRUEBA, SQL.PASSPRUEBA).prepareStatement(SQL.COMPROBARLOGIN );
                PreparedStatement sc = DriverManager.getConnection(SQL.URL, SQL.USERPRUEBA, SQL.PASSPRUEBA).prepareStatement(SQL.COMPROBARLOGINCORREO);
                PreparedStatement stU = DriverManager.getConnection(SQL.URL, SQL.USERPRUEBA, SQL.PASSPRUEBA).prepareStatement(SQL.OBTENERINFORMACIONPERSONA);
                PreparedStatement stE = DriverManager.getConnection(SQL.URL, SQL.USERPRUEBA, SQL.PASSPRUEBA).prepareStatement(SQL.OBTENERINFORMACIONEMPRESA))
        {
            st.setString(1, usuario);
            st.setString(2, usuario);
            sc.setString(1, usuario);
            ResultSet rsC = sc.executeQuery();
            while(rsC.next())
            {
                String passC = rsC.getString("Contrasena");
                pass = passC;
            }
            
            st.setString(3, pass);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                //Existe un usuario con esa combinación de Usuario y contraseña
                stU.setString(1, rs.getString("Nombre_Usuario"));
                stE.setString(1, rs.getString("Nombre_Usuario"));
                ResultSet rsP = stU.executeQuery();
                while(rsP.next())
                {
                    //Existe Persona
                    persona = true;
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
                if(!persona)
                {
                    rsP = stE.executeQuery();
                    while(rsP.next())
                    {
                        //Existe Empresa
                        emp = new Empresa(rs.getString("Nombre_Usuario"), rs.getString("Contrasena"), rs.getString("Correo_Electronico"), rs.getString("Nombre"), rs.getString("Imagen") == null ? null :new ImageIcon(rs.getString("Imagen")), rsP.getInt("CIF"));
                        emp.setPassword(rs.getString("Contrasena"));
                        emp.setCiudad(rs.getString("Ciudad"));
                        emp.setDireccion(rs.getString("Direccion"));
                        emp.setTelefono(rs.getString("Telefono"));
                        emp.setInformacion_Adicional(rsP.getString("Informacion_Adicional"));
                        emp.setPersonaContacto(rsP.getString("Persona_De_Contacto"));
                        emp.setURL(rsP.getString("URL"));
                    }
                    user = emp;
                }
                if(user == null)
                    user = per;
                return user;
            }
            return null;
        }catch(SQLException ex)
        {
            System.err.println(Error.CONNECTIONFAILED);
            return null;
        }
    }
    
    /**
     * Añade un usuario a la base de datos.
     * @param p Usuario introducido en el formulario de registro.
     * @return True si lo ha introducido correctamente y False si no.
     */
    public boolean addUser(Usuario p)
    {
        try(Connection con = DriverManager.getConnection(SQL.URL, SQL.USERPRUEBA, SQL.PASSPRUEBA);
                PreparedStatement st = con.prepareStatement(SQL.INSERTADDUSER);
                PreparedStatement stmU = con.prepareStatement(SQL.INSERTPERSON);
                PreparedStatement stmE = con.prepareStatement(SQL.INSERTENTERPRISE))
                
        {
            st.setString(1, p.getUsername());
            st.setString(2, p.getNombre());
            st.setString(3, p.getPassword());
            st.setString(4, p.getDireccion());
            st.setString(5, p.getCiudad());
            st.setString(6, p.getTelefono());
            st.setString(7, p.getCorreoElectronico());
            st.setString(8, p.getImagenPerfilDir());
            if(p instanceof Persona)
            {
                 
                Persona per = (Persona)p;
                Date fecha;
                if(per.getFecha_Nacimiento() != null)
                    fecha = new Date(per.getFecha_Nacimiento().getTime());
                else
                    fecha = null;
                stmU.setString(1, per.getDNI());
                stmU.setString(2, per.getUsername());
                stmU.setString(3, per.getPrimerApellido());
                stmU.setString(4, per.getSegundoApellido());
                stmU.setDate(5,fecha);
                stmU.setString(6, per.getNacionalidad());
                stmU.setInt(7, per.isPermisoConduccion());
                st.executeUpdate();
                stmU.executeUpdate();
            }
            else
            {
                Empresa emp = (Empresa)p;
                stmE.setInt(1, emp.getCIF());
                stmE.setString(2, emp.getUsername());
                stmE.setString(3, emp.getInformacion_Adicional());
                stmE.setString(4, emp.getPersonaContacto());
                stmE.setString(5, emp.getURL());
                st.executeUpdate();
                stmE.executeUpdate();
            }  
            
            return true;
        } catch (SQLException ex)
        {
            System.err.println(Error.CONNECTIONFAILED);
            return false;
        }
    }
    
    /**
     * Borra la cuenta de un usuario que lo haya solicitado.
     * @param nombre Nombre de Usuariodel usuario que haya solicitado el borrado de sesión.
     */
    public void borrarCuenta(String nombre)
    {
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL, SQL.USERPRUEBA, SQL.PASSPRUEBA).prepareStatement(SQL.DELETEACOUNT))
        {
            st.setString(1, nombre);
            st.executeUpdate();
            st.execute("COMMIT");
        }catch(SQLException ex)
        {
            System.err.println(Error.CONNECTIONFAILED);
        }
    }
}
