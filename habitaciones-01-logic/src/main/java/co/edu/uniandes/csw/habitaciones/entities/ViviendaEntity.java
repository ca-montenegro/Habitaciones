package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author c.penaloza
 */
@Entity
public class ViviendaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVivienda;
    
    private String descripcion;
    
    private String ciudad;
    
    private String direccion;
    
    private Double valorDiario;
    
    private int capacidad;
    
    private String imagen;
    
    @OneToMany(mappedBy = "vivienda")
    private List<ReservaEntity> reservas;
    
    @OneToMany(mappedBy="idVivienda")
    private List<HabitacionEntity> habitaciones;
    
    
    public ViviendaEntity(){
        
    }

    public Long getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(Long idVivienda) {
        this.idVivienda = idVivienda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getValorDiario() {
        return valorDiario;
    }

    public void setValorDiario(Double valorDiario) {
        this.valorDiario = valorDiario;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List reservas) {
        this.reservas = reservas;
    }

    public List<HabitacionEntity> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List habitaciones) {
        this.habitaciones = habitaciones;
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