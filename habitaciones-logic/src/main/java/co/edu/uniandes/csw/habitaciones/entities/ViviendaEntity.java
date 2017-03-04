package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    
    private List reservas;
    
    private List habitaciones;
    
    
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

    public List getReservas() {
        return reservas;
    }

    public void setReservas(List reservas) {
        this.reservas = reservas;
    }

    public List getHabitaciones() {
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
}