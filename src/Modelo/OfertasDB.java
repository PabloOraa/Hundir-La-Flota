package Modelo;

import Entidades.Curriculum;
import Entidades.Empresa;
import Entidades.Oferta;
import Entidades.Persona;
import Entidades.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * @author Pablo Oraa Lopez
 */
public class OfertasDB
{
    /**
     * Lista de Ofertas encontradas.
     */
    private ArrayList<Oferta> lista = new ArrayList<>();
    
    /**
     * Inserta la oferta en la base de datos.
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @param of Oferta a Insertar
     */
    public void addOferta(Usuario p, Oferta of)
    {
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL, p != null ? p.getUsuario():SQL.USERPRUEBA,p != null ? p.getPass():SQL.PASSPRUEBA).prepareStatement(SQL.INSERTAROFERTA))
        {
            st.setInt(1, of.getIdOferta());
            st.setInt(2, of.getCIF());
            st.setString(3, of.getPuesto());
            st.setString(4, of.getDescripcion());
            st.setInt(5, of.getNumeroVacantes());
            st.setString(6, of.getUbicacion());
            st.setDate(7, new Date(of.getFecha_Creacion().getTime()));
            st.setString(8, of.getRequisitos());
            st.setDouble(9, of.getSalario());
            st.setString(10, of.getJornada());
            st.setInt(11, of.getDiasVacaciones());
            st.setInt(12, of.getNumeroInscritos());
            st.executeUpdate();
        } catch (SQLException ex)
        {
            System.err.println(Error.CONNECTIONFAILED);
        }
    }
    
    /**
     * Obtiene las últimas ofertas que se encuentran en la aplicación.
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @return Lista de ofertas encontradas en la aplicación.
     */
    public ArrayList<Oferta> obtenerUltimasOfertas(Usuario p)
    {
        lista = buscarDatos(p);  
        reordenarDatos(lista);
        return lista;
    }
    
    public int obtenerIdUltimo(Usuario p)
    {
        int i = 0;
        lista = buscarDatos(p);
        for(Oferta of:lista)
            i++;
        i++;
        return i;
    }
    
    /**
     * Reordena las ofertas encontradas en la consulta para que cada vez de valores diferentes.
     * @param arr Lista de Ofertas a cambiar.
     */
    public void reordenarDatos(ArrayList<Oferta> arr)
    {
        Random r = new Random(); 
        int nuevaPos;
        for(int i = 0; i < arr.size(); i++)
        {
            nuevaPos = r.nextInt(arr.size());
            Oferta temp = arr.get(i);
            arr.set(i, arr.get(nuevaPos));
            arr.set(nuevaPos, temp);
        }
    }

    /**
     * Realiza la consulta seleccionada por el usuario que llame al metodo
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @return ArrayList con los resultados de la conexión a la base de datos
     */
    public ArrayList<Oferta> buscarDatos(Usuario p)
    {
        try (Connection con = DriverManager.getConnection(SQL.URL, p != null ? p.getUsuario():SQL.USERPRUEBA,p != null ? p.getPass():SQL.PASSPRUEBA);)
        {
            PreparedStatement st = con.prepareStatement(SQL.OBTENERTODASOFERTAS);
            ResultSet rs = st.executeQuery();
            ArrayList<Oferta> lista = new ArrayList();
            while(rs.next())
            {
                Oferta f = new Oferta(rs.getInt("ID_Oferta"), rs.getInt("CIF"));
                f.setDescripcion(rs.getString("Descripcion"));
                f.setPuesto(rs.getString("Puesto"));
                f.setNumeroVacantes(rs.getInt("Numero_Vacantes"));
                f.setDiasVacaciones(rs.getInt("Vacaciones"));
                f.setNumeroInscritos(rs.getInt("Numero_Inscritos"));
                f.setJornada(rs.getString("Jornada"));
                f.setSalario(rs.getDouble("Salario"));
                f.setRequisitos(rs.getString("Requisitos"));
                f.setFecha_Creacion(rs.getDate("Fecha_creacion"));
                f.setUbicacion(rs.getString("Ubicacion"));
                lista.add(f);
            }
            return lista;
        } catch (SQLException ex)
        {
            return null;
        }
    }
    
    /**
     * Obtiene las Ofertas de Empleo en función de la búsqueda indicada por el usuario.
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @param busqueda Parametro a buscar por el usuario.
     * @return Lista de ofertas de Empleo en función de la búsqueda del usuario.
     */
    public ArrayList<Oferta> obtenerDatosBusqueda(Usuario p,String busqueda)
    {
        lista = buscarDatosBusqueda(p,busqueda);  
        reordenarDatos(lista);
        return lista;
    }
    
    /**
     * Realiza la consulta seleccionada por el usuario que ha escrito algo en la busqueda
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @param textoBusqueda Texto a buscar entre las ofertas
     * @return ArrayList con los resultados de la conexión a la base de datos
     */
    public ArrayList<Oferta> buscarDatosBusqueda(Usuario p, String textoBusqueda)
    {
        try (Connection con = DriverManager.getConnection(SQL.URL, p != null ? p.getUsuario():SQL.USERPRUEBA,p != null ? p.getPass():SQL.PASSPRUEBA);)
        {
            PreparedStatement st = con.prepareStatement(SQL.OBTENEROFERTASFILTRO);
            st.setString(1, "%"+textoBusqueda+"%");
            st.setString(2, "%"+textoBusqueda+"%");
            ResultSet rs = st.executeQuery();
            ArrayList<Oferta> lista = new ArrayList();
            while(rs.next())
            {
                Oferta f = new Oferta(rs.getInt("ID_Oferta"), rs.getInt("CIF"));
                f.setDescripcion(rs.getString("Descripcion"));
                f.setPuesto(rs.getString("Puesto"));
                f.setNumeroVacantes(rs.getInt("Numero_Vacantes"));
                f.setDiasVacaciones(rs.getInt("Vacaciones"));
                f.setNumeroInscritos(rs.getInt("Numero_Inscritos"));
                f.setJornada(rs.getString("Jornada"));
                f.setSalario(rs.getDouble("Salario"));
                f.setRequisitos(rs.getString("Requisitos"));
                f.setFecha_Creacion(rs.getDate("Fecha_creacion"));
                f.setUbicacion(rs.getString("Ubicacion"));
                lista.add(f);
            }
            return lista;
        } catch (SQLException ex)
        {
            return null;
        }
    }
    
    /**
     * Obtiene el nombre de una empresa en función de la Oferta en la que nos encontremos
     * @param of Oferta de la que conocer el nombre
     * @return Nombre de la empresa.
     */
    public String getNombreEmpresa(Oferta of)
    {
        try (Connection con = DriverManager.getConnection(SQL.URL, SQL.USERPRUEBA,SQL.PASSPRUEBA);)
        {
            PreparedStatement st = con.prepareStatement(SQL.OBTENERNOMBREEMPRESA);
            st.setInt(1, of.getCIF());
            ResultSet rs = st.executeQuery();
            rs.next();
            return rs.getString("Nombre");
        } catch (SQLException ex)
        {
            return null;
        }
    }

    /**
     * Obtiene las diferentes ofertas en las que se ha registrado un usuario.
     * @param usuario Usuario sobre el que se buscan las ofertas inscrito.
     * @return Lista de ofertas en ArrayList.
     */
    public ArrayList<Oferta> obtenerOfertasUsuarioInscrito(Usuario usuario)
    {
        lista = buscarDatosUser(usuario);  
        reordenarDatos(lista);
        return lista;
    }

    /**
     * Obtiene las ofertas en las que se ha inscrito un usuario.
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @return ArrayList con los resultados de la conexión a la base de datos
     */
    private ArrayList<Oferta> buscarDatosUser(Usuario p)
    {
        try (Connection con = DriverManager.getConnection(SQL.URL, p != null ? p.getUsuario():SQL.USERPRUEBA,p != null ? p.getPass():SQL.PASSPRUEBA);)
        {
            PreparedStatement st = con.prepareStatement(SQL.OBTENERDATOSUSUARIO);
            st.setString(1, ((Persona)p).getDNI());
            ResultSet rs = st.executeQuery();
            ArrayList<Oferta> lista = new ArrayList();
            while(rs.next())
            {
                Oferta f = new Oferta(rs.getInt("ID_Oferta"), rs.getInt("CIF"));
                f.setDescripcion(rs.getString("Descripcion"));
                f.setPuesto(rs.getString("Puesto"));
                f.setNumeroVacantes(rs.getInt("Numero_Vacantes"));
                f.setDiasVacaciones(rs.getInt("Vacaciones"));
                f.setNumeroInscritos(rs.getInt("Numero_Inscritos"));
                f.setJornada(rs.getString("Jornada"));
                f.setSalario(rs.getDouble("Salario"));
                f.setRequisitos(rs.getString("Requisitos"));
                f.setFecha_Creacion(rs.getDate("Fecha_creacion"));
                f.setUbicacion(rs.getString("Ubicacion"));
                lista.add(f);
            }
            return lista;
        } catch (SQLException ex)
        {
            return null;
        }
    }
    
    /**
     * Obtiene los datos de una empresa a través de su nombre.
     * @param nombreEmpresa Nombre de la empresa.
     * @return Usuario empresa con todos los datos registrados para ese nombre.
     */
    public Empresa obtenerDatosEmpresa(String nombreEmpresa)
    {
        Empresa emp = null;
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL, SQL.USERPRUEBA, SQL.PASSPRUEBA).prepareStatement(SQL.OBTENERINFORMACIONUSUARIONAME);
                PreparedStatement stE = DriverManager.getConnection(SQL.URL, SQL.USERPRUEBA, SQL.PASSPRUEBA).prepareStatement(SQL.OBTENERINFORMACIONEMPRESAUSER))
        {
            st.setString(1, nombreEmpresa);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                stE.setString(1, rs.getString("Nombre_Usuario"));
                stE.setString(2, rs.getString("Nombre_Usuario"));
                ResultSet rsP = stE.executeQuery();
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
            }
            return emp;
        }catch(SQLException ex)
        {
            System.err.println(Error.CONNECTIONFAILED);
            return null;
        }
    }
    
    /**
     * Borra la oferta seleccionada por la empresa.
     * @param id_oferta ID de la oferta a borrar.
     */
    public void borrarOferta(int id_oferta)
    {
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL, SQL.USERPRUEBA, SQL.PASSPRUEBA).prepareStatement(SQL.DELETEOFERTA))
        {
            st.setInt(1, id_oferta);
            st.executeUpdate();
        }catch(SQLException ex)
        {
            System.err.println(Error.CONNECTIONFAILED);
        }
    }
    
    /**
     * Actualiza la Oferta que desee el Usuario.
     * @param p Usuario actual de la Aplicación que indica el usuario y contraseña de la Base de Datos.
     * @param cu Oferta con los datos actualizados para llevarlos a la base de datos
     */
    public void actualizarOferta(Usuario p, Oferta cu)
    {
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL, p != null ? p.getUsuario():SQL.USERPRUEBA,p != null ? p.getPass():SQL.PASSPRUEBA).prepareStatement(SQL.UPDATEOFERTA))
        {
            st.setString(1, cu.getPuesto());
            st.setString(2, cu.getDescripcion());
            st.setInt(3, cu.getNumeroVacantes());
            st.setString(4, cu.getUbicacion());
            st.setString(5, cu.getRequisitos());
            st.setDouble(6, cu.getSalario());
            st.setString(7, cu.getJornada());
            st.setInt(8, cu.getDiasVacaciones());
            st.setInt(9, cu.getIdOferta());
            st.executeUpdate();
        }catch(SQLException ex)
        {
            System.err.println(Error.CONNECTIONFAILED);
        }
    }
    
    /**
     * Inscribe a un usuario en la oferta en cuestión.
     * @param user Usuario que se inscribe en una oferta.
     * @param cu Curriculum asociado a la inscripción.
     * @param of Oferta en la que se inscribe.
     */
    public void addInscripcion(Usuario user, Curriculum cu, Oferta of)
    {
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL,SQL.USERPRUEBA,SQL.PASSPRUEBA).prepareStatement(SQL.INSERTINSCRIPCION))
        {
            st.setString(1, ((Persona)user).getDNI());
            st.setInt(2, of.getIdOferta());
            st.setString(3, String.valueOf(cu.getId_Curriculum()));
            st.setDate(4, new Date(new java.util.Date().getTime()));
            
            st.executeUpdate();
        }catch(SQLException ex)
        {
            System.err.println(Error.CONNECTIONFAILED);
        }
    }
    
    /**
     * Añade una persona al número de inscritos de una ofera.
     * @param cu Oferta con la que añadir un inscrito más.
     */
    public void addInscrito(Oferta cu)
    {
        try(PreparedStatement st = DriverManager.getConnection(SQL.URL,SQL.USERPRUEBA,SQL.PASSPRUEBA).prepareStatement(SQL.ADDINSCRITO))
        {
            st.setInt(1, cu.getIdOferta());
            st.executeUpdate();
        }catch(SQLException ex)
        {
            System.err.println(Error.CONNECTIONFAILED);
        }
    }
}
