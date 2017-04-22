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
