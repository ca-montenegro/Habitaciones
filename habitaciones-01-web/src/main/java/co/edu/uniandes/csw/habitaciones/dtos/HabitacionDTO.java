
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author l.maya10
 */
@XmlRootElement
public class HabitacionDTO implements Serializable {
    private Long id;
    private Integer capacidad;
    private Double area;
    private Double valorDiario;
    private String descripcion;
    private String imagen;
    
    public HabitacionDTO()
    {
        
    }
    
    public HabitacionDTO(HabitacionEntity entity)
    {
        if(entity!=null)
        {
            this.id = entity.getId();
            this.area = entity.getArea();
            this.capacidad = entity.getCapacidad();
            this.descripcion = entity.getDescripcion();
            this.imagen = entity.getImagen();
            this.valorDiario = entity.getValorDiario();
        }
    }
    
    public HabitacionEntity toEntity()
    {
        HabitacionEntity entity = new HabitacionEntity();
        entity.setId(this.getId());
        entity.setArea(this.getArea());
        entity.setCapacidad(this.getCapacidad());
        entity.setDescripcion(this.getDescripcion());
        entity.setValorDiario(this.getValorDiario());
        entity.setImagen(this.getImagen());
        return entity;
    }

    public Long getId() {
        return id;
    }
    
    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getValorDiario() {
        return valorDiario;
    }

    public void setValorDiario(Double valorDiario) {
        this.valorDiario = valorDiario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
