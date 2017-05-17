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
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import co.edu.uniandes.csw.habitaciones.persistence.ReservaPersistence;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.util.Assert;

@Stateless
public class ReservaLogic {
    
    public ReservaLogic(){
        
    }
    
    private static final long serialVersionUID = 1L;
    
    @Inject 
    public ReservaLogic (ReservaPersistence persistence)
    {
        Assert.notNull(persistence, "My persistence must be not null");
        this.persistence = persistence;
    }
    private ReservaPersistence persistence;
   
    public List<ReservaEntity> getReservas() {
        return persistence.findAll();
    }
    
    public ReservaEntity getReserva(Long codigo){
        return persistence.find(codigo);
    }
    
    public ReservaEntity createReserva(ReservaEntity entity) throws BusinessLogicException {
        if(entity.getFechaFin().before(entity.getFechaInicio()))
        {
            throw new BusinessLogicException("La fecha final de la reserva no puede ser antes de la fecha de inicio de la reserva.");
        }
        if(entity.getEstado() == 'C')
        {
             throw new BusinessLogicException("No se puede crear una reserva cancelada.");
        }
        Long dias = (entity.getFechaFin().getTime() - entity.getFechaInicio().getTime()/(1000 * 60 * 60 * 24));  

        entity.setCosto(entity.getVivienda().getValorDiario() * dias); 
        
        if(entity.getCosto()< 0.0)
        {
             throw new BusinessLogicException("El costo no puede ser negativo.");
        }
        
        persistence.create(entity);
        return entity;
    }
    
    public ReservaEntity updateReserva(ReservaEntity entity)
    {
        return persistence.update(entity);
    }
}
