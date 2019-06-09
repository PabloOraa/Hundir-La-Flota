package Entidades;

import java.util.Date;

/**
 * @author Pablo Oraa Lopez
 */
public class Curriculum
{
    /**
     * ID que identifica al curriculum.
     */
    private final int id_Curriculum;
    /**
     * Identificación del usuario dueño del curriculum.
     */
    private final String DNI;
    /**
     * Fecha de la actualización del curriculum de un usuario.
     */
    private Date fechaActualizacion;
    /**
     * Texto que indica la experiencia que tiene un usuario en el momento de la creación/editado del curriculum.
     */
    private String ExperienciaLaboral;
    /**
     * Formación que tiene un usuario en el momento de creación del Curriculum.
     */
    private String Formacion;
    /**
     * Idiomas que conoce el usuario dueño del curriculum.
     */
    private String Idiomas;
    /**
     * Gustos y aficiones del usuario.
     */
    private String GustosyAficiones;
    /**
     * Otros datos de interes que quiera introducir el usuario en la creación del curriculum.
     */
    private String otrosDatos;
    
    /**
     * Constructor del Curriculum.
     * @param id_Curriculum Numero de identificación del curriculum.
     * @param DNI Cadena de 9 caracteres de identificación de una persona dueña del curriculum.
     */
    public Curriculum(int id_Curriculum,String DNI)
    {
        this.id_Curriculum = id_Curriculum;
        this.DNI = DNI;
    }

    /**
     * Obtiene la fecha de actualización del curriculum del usuario.
     * @return Objeto Date que devuelve la fecha de la actualización del curriculum.
     */
    public Date getFechaActualizacion()
    {
        return fechaActualizacion;
    }

    /**
     * Indica la fecha de actualización del curriculum para actualizarla.
     * @param fechaActualizacion Objeto Date que registra la actualización del curriculum.
     */
    public void setFechaActualizacion(Date fechaActualizacion)
    {
        this.fechaActualizacion = fechaActualizacion;
    }

    /**
     * Obtiene la experiencia laboral de una persona según lo registrado en el curriculum.
     * @return cadena de texto que marca la experiencia laboral.
     */
    public String getExperienciaLaboral()
    {
        return ExperienciaLaboral;
    }

    /**
     * Cambia la experiencia laboral de una persona por la indicada en la edición del curriculum.
     * @param ExperienciaLaboral Texto indicado por el usuario.
     */
    public void setExperienciaLaboral(String ExperienciaLaboral)
    {
        this.ExperienciaLaboral = ExperienciaLaboral;
    }

    /**
     * Obtiene la formación de un usuario en función de lo indicado en el curriculum.
     * @return Texto guardado en el curriculum.
     */
    public String getFormacion()
    {
        return Formacion;
    }

    /**
     * Cambia la formación de una persona por la indicada en la edición del curriculum.
     * @param Formacion Texto indicado por el usuario.
     */
    public void setFormacion(String Formacion)
    {
        this.Formacion = Formacion;
    }

    /**
     * Obtiene los idiomas conocidos por un usuario en función de lo indicado en el curriculum.
     * @return Idiomas guardados en el curriculum.
     */
    public String getIdiomas()
    {
        return Idiomas;
    }

    /**
     * Actualiza los idiomas conocidos por una persona por la indicada en la edición del curriculum.
     * @param Idiomas Texto indicado por el usuario.
     */
    public void setIdiomas(String Idiomas)
    {
        this.Idiomas = Idiomas;
    }

    /**
     * Obtiene los Gustos y Aficiones por un usuario en función de lo indicado en el curriculum.
     * @return Idiomas guardados en el curriculum.
     */
    public String getGustosyAficiones()
    {
        return GustosyAficiones;
    }

    /**
     * Actualiza los Gustos y Aficiones de un persona por la indicada en la edición del curriculum.
     * @param GustosyAficiones Texto indicado por el usuario.
     */
    public void setGustosyAficiones(String GustosyAficiones)
    {
        this.GustosyAficiones = GustosyAficiones;
    }

    /**
     * Obtiene los Datos de interes por un usuario en función de lo indicado en el curriculum.
     * @return Idiomas guardados en el curriculum.
     */
    public String getOtrosDatos()
    {
        return otrosDatos;
    }

    /**
     * Actualiza los datos de interes de una persona por la indicada en la edición del curriculum.
     * @param otrosDatos Texto indicado por el usuario.
     */
    public void setOtrosDatos(String otrosDatos)
    {
        this.otrosDatos = otrosDatos;
    }

    /**
     * Obtiene el ID del curriculum guardado.
     * @return Número de identificción del Curriculum.
     */
    public int getId_Curriculum()
    {
        return id_Curriculum;
    }

    /**
     * Obtiene el documento de identidad de un usuario.
     * @return Cadena de 9 caracteres para conocer el DNI del usuario.
     */
    public String getDNI()
    {
        return DNI;
    }
    
    /**
     * ToString del Curriculum para la visualización de algunos elementos que permitan al usuario identificar el curriculum cuando busque todos los que tienen guardados.
     * @return Texto generado a mostrar.
     */
    @Override
    public String toString()
    {
        return fechaActualizacion + ":\n" + Formacion + "\n" + ExperienciaLaboral;
    }
}
