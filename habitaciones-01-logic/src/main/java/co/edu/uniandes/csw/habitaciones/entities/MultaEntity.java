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
    
    public MultaEntity(){
        
    }
    
    static final long serialVersionUID = 1L;
    
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
