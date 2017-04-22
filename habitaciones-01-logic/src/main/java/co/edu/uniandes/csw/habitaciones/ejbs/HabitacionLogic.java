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

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.HabitacionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author l.maya10
 * @generated
 */
@Stateless
public class HabitacionLogic {

    public HabitacionLogic() {
    }
    
    
    
    private static final long serialVersionUID = 1L;
    
    @Inject private HabitacionPersistence persistence;
    
    public List<HabitacionEntity> getHabitaciones()
    {
        return persistence.findAll();
    }
    
    public List<HabitacionEntity> getHabitacionesVivienda(Long idVivienda)
    {
        return persistence.findAllVivienda(idVivienda);
    }
    
    public HabitacionEntity getHabitacion(Long id)
    {
        return persistence.find(id);
    }
    
    public HabitacionEntity createHabitacion(HabitacionEntity entity) throws BusinessLogicException
    {
        if(entity.getArea() <= 0.0)
            throw new BusinessLogicException ("El area debe ser mayor a 0");
        if(entity.getCapacidad() <= 0)
            throw new BusinessLogicException("La capacidad debe ser mayor a 0");
        if(entity.getValorDiario() <= 0)
            throw new BusinessLogicException("El precio debe ser mayor a cero");
        if(entity.getVivienda() == null)
            throw new BusinessLogicException("La habitacion debe pertenecer a una vivienda");
        persistence.create(entity);
        return entity;
    }
    
    public HabitacionEntity updateHabitacion(HabitacionEntity entity) {
        return persistence.update(entity);
    }
    
    public void deleteHabitacion(Long id) {
        persistence.delete(id);
    }
}
