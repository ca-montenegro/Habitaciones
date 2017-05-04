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

import co.edu.uniandes.csw.habitaciones.entities.MultaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.MultaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.util.Assert;


@Stateless
public class MultaLogic {
    
    public MultaLogic(){
        
    }
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    public MultaLogic (MultaPersistence persistence)
    {
        Assert.notNull(persistence, "My persistence must be not null");
        this.persistence = persistence;
    }
    private MultaPersistence persistence;
    
    public MultaEntity getMulta(Long codigo){
        return persistence.find(codigo);
    }
            
    public MultaEntity createMulta(MultaEntity entity) throws BusinessLogicException{
        if( persistence.find(entity.getReserva().getCodigoReserva()) == null)
        {
            throw new BusinessLogicException("Ya existe una multa asociada a esa reserva.");
        }
        
        persistence.create(entity);
        return entity;
    }
    
    public MultaEntity updateMulta(MultaEntity entity){
        return persistence.update(entity);
    }
}
