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
package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.MultaEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MultaPersistence {
    
    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;
    
    public MultaEntity find(Long codigo){
        return em.find(MultaEntity.class, codigo); 
    }
               
    public MultaEntity create(MultaEntity entity) {
        em.persist(entity);
        return entity;
    }
    
    public MultaEntity update(MultaEntity entity)
    {
        return em.merge(entity);
    }
}