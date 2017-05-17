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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class ReservaEntity implements Serializable{
    
    //Constructor vacio
    public ReservaEntity(){
        
    }
    
    private static final long serialVersionUID = 1L;
    
    //Codigo de la reserva
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PodamExclude
    private Long codigoReserva;
    
    //Fecha de inicio de la reserva
    @Temporal(TemporalType.TIMESTAMP) 
    private Date fechaInicio;
    
    //Fecha de fin de la reserva
    @Temporal(TemporalType.TIMESTAMP) 
    private Date fechaFin;
    
    //Costo de la reserva
    private double costo;
    
    //Estado de la reserva
    @PodamExclude
    private char estado;
    
    //Multa de la reserva
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @PodamExclude
    private MultaEntity multa;
    
    //Habitacion ede la reserva
    @ManyToOne(cascade = CascadeType.PERSIST)
    @PodamExclude
    private HabitacionEntity habitacion;
    
    //Vivienda de la reserva
    @ManyToOne
    @PodamExclude
    private ViviendaEntity vivienda;

    //Get de habtiacion
    public HabitacionEntity getHabitacion() {
        return habitacion;
    }

    //Definir la habitación
    public void setHabitacion(HabitacionEntity habitacion) {
        this.habitacion = habitacion;
    }

    //Get de la vivienda
    public ViviendaEntity getVivienda() {
        return vivienda;
    }
    
    //Definir la vivienda
    public void setVivienda(ViviendaEntity vivienda) {
        this.vivienda = vivienda;
    } 

    //Get de la reserva
    public Long getCodigoReserva() {
        return codigoReserva;
    }

    //Definir el codigo de la reserva
    public void setCodigoReserva(Long codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    //Get de la fecha de inicio
    public Date getFechaInicio() {
        return fechaInicio;
    }

    //Definir la fecha de inicio
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    //Get de la fecha de fin
    public Date getFechaFin() {
        return fechaFin;
    }

    //Definir la fecha de fin
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    //Get del costo de la reserva
    public double getCosto() {
        return costo;
    }

    //Definir el costo de la reserva
    public void setCosto(double costo) {
        this.costo = costo;
    }

    //Get del estado de la reserva
    public char getEstado() {
        return estado;
    }

    //Definir el estado de la reserva
    public void setEstado(char estado) {
        this.estado = estado;
    }
    
    //Get de la multa de la reserva
     public MultaEntity getMulta() {
        return multa;
    }

    //Definir la multa de la reserva
    public void setMulta(MultaEntity multa) {
        this.multa = multa;
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
        final ReservaEntity other = (ReservaEntity) obj;
        if (!Objects.equals(this.codigoReserva, other.codigoReserva)) {
            return false;
        }
        return true;
    }
}
