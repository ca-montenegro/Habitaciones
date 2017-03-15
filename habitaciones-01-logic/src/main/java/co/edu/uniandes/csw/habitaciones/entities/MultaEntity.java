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
    private Long codigoMulta;
    
    @OneToOne(mappedBy = "multa")
    private ReservaEntity reserva;
    
     @Temporal(TemporalType.TIMESTAMP) 
    private Date fechaCancelacion;
    
    @Temporal(TemporalType.TIMESTAMP) 
    private Date fechaPago;

    public ReservaEntity getReserva() {
        return reserva;
    }

    public void setReserva(ReservaEntity reserva) {
        this.reserva = reserva;
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

    public Long getCodigoMulta() {
        return codigoMulta;
    }

    public void setCodigoMulta(Long codigoMulta) {
        this.codigoMulta = codigoMulta;
    }
    
    @Override
            public int hashCode() {
        if (this.getReserva()!= null) {
            return this.getReserva().hashCode();
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
        if (!Objects.equals(this.reserva, other.reserva)) {
            return false;
        }
        return true;
    }
}
