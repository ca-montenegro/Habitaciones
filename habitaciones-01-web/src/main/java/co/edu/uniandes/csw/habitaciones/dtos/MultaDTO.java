package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.MultaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MultaDTO implements Serializable {
    
    static final long serialVersionUID = 1L;
    
    private Long codigoMulta;
    
    private ReservaDTO reserva;
    
    private Date fechaCancelacion;
    
    private Date fechaPago;
    
    public MultaDTO() {
        
    }
    
    
    public MultaDTO(MultaEntity entity) { 
        if(entity != null){
            this.codigoMulta = entity.getCodigoMulta();
            this.fechaCancelacion = entity.getFechaCancelacion();
            this.fechaPago = entity.getFechaPago();
        }
    }
    
    
    public MultaEntity toEntity() {
        MultaEntity entity = new MultaEntity();
        
        entity.setCodigoMulta(this.codigoMulta);
        entity.setFechaCancelacion(this.fechaCancelacion);
        entity.setFechaPago(this.fechaPago);
        
        return entity;
    }

    public Long getCodigoMulta() {
        return codigoMulta;
    }

    public void setCodigoMulta(Long codigoMulta) {
        this.codigoMulta = codigoMulta;
    }

    public ReservaDTO getReserva() {
        return reserva;
    }

    public void setReserva(ReservaDTO reserva) {
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
            
}
