package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MultaEntity implements Serializable{
    
    @Id
    @OneToOne( mappedBy = "multa")
    private String codigoReserva;
    
     @Temporal(TemporalType.TIMESTAMP) 
    private Date fechaCancelacion;
    
    @Temporal(TemporalType.TIMESTAMP) 
    private Date fechaPago;

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }
   

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
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
        final MultaEntity other = (MultaEntity) obj;
        if (!Objects.equals(this.codigoReserva, other.codigoReserva)) {
            return false;
        }
        return true;
    }
}
