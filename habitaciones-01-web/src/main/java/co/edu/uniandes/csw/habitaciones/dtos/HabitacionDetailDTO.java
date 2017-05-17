/* 
 * Copyright (C) 2017 c.penaloza.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
/**
 * Clase Habitacion Detail Dto
 */
public class HabitacionDetailDTO extends HabitacionDTO {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Lista de reservas de la habitacion
     */
    private List<ReservaDTO> reservas;
    
    /**
     * Constructor de la clase
     */
    public HabitacionDetailDTO(){      
        super();
    }
    /**
     * Constructor de la clase con parametros de entrada
     * @param entity Entidad que se quiere volver DTO
     */
    public HabitacionDetailDTO(HabitacionEntity entity){
        super(entity);
        if(entity!=null){
            if(entity.getReservas()!=null){
                this.reservas=new ArrayList();
                for(ReservaEntity reserva:entity.getReservas()){
                    this.reservas.add(new ReservaDTO(reserva));
                }
            }
        }
    }
    
    /**
     * Metodo que convierte un DTO en un entity
     * @return 
     */
    @Override
    public HabitacionEntity toEntity(){
        HabitacionEntity entity = new HabitacionEntity();
        entity.setId(this.getId());
        entity.setArea(this.getArea());
        entity.setCapacidad(this.getCapacidad());
        entity.setDescripcion(this.getDescripcion());
        entity.setValorDiario(this.getValorDiario());
        entity.setImagen(this.getImagen());
        if(this.getReservas()!=null){
            entity.setReservas(new ArrayList());
            List<ReservaEntity> entities0=entity.getReservas();
            for(ReservaDTO reserva:this.getReservas()){
                entities0.add(reserva.toEntity());
            }
        }
        return entity;
    }

    /**
     * Metodo que retorna las reservas de la habitacion
     * @return Reservas de la habitacion
     */
    public List<ReservaDTO> getReservas() {
        List<ReservaDTO> resp = new ArrayList<>();
        resp.addAll(this.reservas);
        return resp;
    }

    /**
     * Metodo que asigna las reservas de la hablitacion
     * @param reservas Reservas que se le asignaran a la habitacion
     */
    public void setReservas(List<ReservaDTO> reservas) {
        List<ReservaDTO> resp = new ArrayList<>();
        resp.addAll(reservas);
        this.reservas = resp;
    }    
}
