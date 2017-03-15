package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservaDTO implements Serializable{
    
    private Long codigoReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private Double costo;
    private Character estado;
    
    
    public ReservaDTO(){
    }

    public ReservaDTO(ReservaEntity entity){
        if(entity!=null){
            this.codigoReserva =  entity.getCodigoReserva();
            this.costo = entity.getCosto();
            this.estado = entity.getEstado();
            this.fechaInicio = entity.getFechaInicio();
            this.fechaFin = entity.getFechaFin();
        }
    }
    
    public ReservaEntity toEntity() {
        ReservaEntity entity = new ReservaEntity();
        
        entity.setCodigoReserva(this.getCodigoReserva());
        entity.setCosto(this.getCosto());
        entity.setEstado(this.getEstado());
        entity.setFechaInicio(this.getFechaInicio());
        entity.setFechaFin(this.getFechaFin());
        
      return entity;  
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

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }
    
}

