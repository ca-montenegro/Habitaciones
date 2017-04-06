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
    private MultaDTO multa;
    private HabitacionDTO habitacion;
    private ViviendaDTO vivienda;
    
    
    public ReservaDTO(){
        super();
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

    public MultaDTO getMulta() {
        return multa;
    }

    public void setMulta(MultaDTO multa) {
        this.multa = multa;
    }

    public HabitacionDTO getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(HabitacionDTO habitacion) {
        this.habitacion = habitacion;
    }

    public ViviendaDTO getVivienda() {
        return vivienda;
    }

    public void setVivienda(ViviendaDTO vivienda) {
        this.vivienda = vivienda;
    }
    
    
}

