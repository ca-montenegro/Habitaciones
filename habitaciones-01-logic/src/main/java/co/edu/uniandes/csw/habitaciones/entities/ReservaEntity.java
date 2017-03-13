package co.edu.uniandes.csw.habitaciones.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ReservaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String codigoReserva;
    
    @Temporal(TemporalType.TIMESTAMP) 
    private Date fechaInicio;
    
    @Temporal(TemporalType.TIMESTAMP) 
    private Date fechaFin;
    
    private double costo;
    
    private char estado;

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
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
