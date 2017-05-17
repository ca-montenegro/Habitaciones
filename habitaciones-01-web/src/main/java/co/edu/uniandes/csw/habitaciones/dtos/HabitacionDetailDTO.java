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
public class HabitacionDetailDTO extends HabitacionDTO {
    
    private static final long serialVersionUID = 1L;
    
    private List<ReservaDTO> reservas;
    
    public HabitacionDetailDTO(){      
        super();
    }
    
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

    public List<ReservaDTO> getReservas() {
        List<ReservaDTO> resp = new ArrayList<>();
        resp.addAll(this.reservas);
        return resp;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        List<ReservaDTO> resp = new ArrayList<>();
        resp.addAll(reservas);
        this.reservas = resp;
    }    
}
