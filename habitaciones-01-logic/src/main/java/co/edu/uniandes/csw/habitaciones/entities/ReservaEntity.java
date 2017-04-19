package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ReservaEntity implements Serializable{
    
    static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoReserva;
    
    @Temporal(TemporalType.TIMESTAMP) 
    private Date fechaInicio;
    
    @Temporal(TemporalType.TIMESTAMP) 
    private Date fechaFin;
    
    private double costo;
    
    private char estado;
    
    @OneToOne (fetch = FetchType.LAZY)
    private MultaEntity multa;
    
    @ManyToOne//(fetch = FetchType.LAZY)
    private HabitacionEntity habitacion;
    
    @ManyToOne//(fetch = FetchType.LAZY)
    private ViviendaEntity vivienda;

    public HabitacionEntity getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(HabitacionEntity habitacion) {
        this.habitacion = habitacion;
    }

    public ViviendaEntity getVivienda() {
        return vivienda;
    }

    public void setVivienda(ViviendaEntity vivienda) {
        this.vivienda = vivienda;
    } 

    public Long getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(Long codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
    
     public MultaEntity getMulta() {
        return multa;
    }

    public void setMulta(MultaEntity multa) {
        this.multa = multa;
    }
    
    @Override
    public int hashCode() {
        if (this.getCodigoReserva()!= null) {
            return this.getCodigoReserva().hashCode();
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
        final ReservaEntity other = (ReservaEntity) obj;
        if (!Objects.equals(this.codigoReserva, other.codigoReserva)) {
            return false;
        }
        return true;
    }
}
