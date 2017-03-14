/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author l.maya10
 */
@XmlRootElement
public class HabitacionDetailDTO extends HabitacionDTO {
    
    private List reservas;
    private Long idHabitacion;
    private Long idVivienda;

    public long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public List getReservas() {
        return reservas;
    }

    public void setReservas(List reservas) {
        this.reservas = reservas;
    }

    public long getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(long idVivienda) {
        this.idVivienda = idVivienda;
    }
    
    
    
    public HabitacionDetailDTO()
    {      
        super();
    }
    
    public HabitacionDetailDTO(HabitacionEntity entity)
    {      
        super(entity);
        this.reservas = entity.getReservas();
        this.idVivienda = entity.getIdVivienda();
    }
    
    @Override
    public HabitacionEntity toEntity()
    {
        HabitacionEntity entity = super.toEntity();
        entity.setIdVivienda(this.getIdVivienda());
        entity.setReservas(this.getReservas());
        return entity;
    }
}
