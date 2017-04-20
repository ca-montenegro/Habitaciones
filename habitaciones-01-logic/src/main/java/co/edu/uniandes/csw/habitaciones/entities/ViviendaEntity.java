 /**
  * Entidad vivienda
  * @author c.penaloza
  */

package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class ViviendaEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Id autogenerado de la vivienda
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVivienda;
    
    /**
     * Descripcion de la vivienda
     */
    private String descripcion;
    
    /**
     * Ciudad de la vivienda
     */
    private String ciudad;
    
    /**
     * Direccion de la vivienda
     */
    private String direccion;
    
    
    /**
     * Valor diario de la vivienda
     */
    private Double valorDiario;
    
    /**
     * Capacidad de la vivienda
     */
    private int capacidad;
    
    /**
     * Imagen de la vivienda
     */
    private String imagen;
    
    /**
     * Lista de reservas de la vivienda
     */
    @OneToMany(mappedBy = "vivienda")
    private List<ReservaEntity> reservas;
    
    /**
     * Lista de habitaciones de la vivienda
     */
    @OneToMany(mappedBy="vivienda")
    private List<HabitacionEntity> habitaciones;
    
    /**
     * Anfitrion de la vivienda
     */
    @ManyToOne
    private AnfitrionEntity anfitrion;
    
    public ViviendaEntity(){
        
    }
    
    /**
     * Retorna el id
     * @return idVivienda
     */
    public Long getIdVivienda()
    {
        return idVivienda;
    }
    
    /**
     * Cambia el id de la vivienda
     * @param idVivienda
     */
    public void setIdVivienda(Long idVivienda)
    {
        this.idVivienda = idVivienda;
    }
    
    /**
     * Retorna la descripcion
     * @return descripcio
     */
    public String getDescripcion()
    {
        return descripcion;
    }
    
    /**
     * Define la descripcion
     * @param descripcion
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
    
    /**
     * Retorna la ciudad
     * @return ciudad
     */
    public String getCiudad()
    {
        return ciudad;
    }
    
    /**
     * Define la ciudad
     * @param ciudad
     */
    public void setCiudad(String ciudad)
    {
        this.ciudad = ciudad;
    }
    
    /**
     * Retorna la direccion
     * @return direccion
     */
    public String getDireccion()
    {
        return direccion;
    }
    
    /**
     * Define la direccion
     * @param direccion
     */
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }
    
    public Double getValorDiario()
    {
        return valorDiario;
    }
    
    public void setValorDiario(Double valorDiario)
    {
        this.valorDiario = valorDiario;
    }
    
    public int getCapacidad()
    {
        return capacidad;
    }
    
    public void setCapacidad(int capacidad)
    {
        this.capacidad = capacidad;
    }
    
    public String getImagen()
    {
        return imagen;
    }
    
    public void setImagen(String imagen)
    {
        this.imagen = imagen;
    }
    
    public List<ReservaEntity> getReservas()
    {
        return reservas;
    }
    
    public void setReservas(List reservas)
    {
        this.reservas = reservas;
    }
    
    public List<HabitacionEntity> getHabitaciones()
    {
        return habitaciones;
    }
    
    public void setHabitaciones(List habitaciones)
    {
        this.habitaciones = habitaciones;
    }
    
    public AnfitrionEntity getAnfitrion()
    {
        return anfitrion;
    }
    
    public void setAnfitrion(AnfitrionEntity anfitrion)
    {
        this.anfitrion = anfitrion;
    }
    
    
    
    @Override
    public int hashCode() {
        if (this.getIdVivienda()!= null) {
            return this.getIdVivienda().hashCode();
        }
        return super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ViviendaEntity other = (ViviendaEntity) obj;
        if (!Objects.equals(this.idVivienda, other.idVivienda)) {
            return false;
        }
        return true;
    }
}
