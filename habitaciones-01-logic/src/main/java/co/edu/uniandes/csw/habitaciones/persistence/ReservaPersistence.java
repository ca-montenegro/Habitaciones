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

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ReservaPersistence {
    
    @PersistenceContext(unitName="habitacionesPU")
    protected EntityManager em;
    
    public ReservaEntity find(Long codigo){
        return em.find(ReservaEntity.class, codigo);
    }
    
    public List<ReservaEntity> findAll() {
        Query q = em.createQuery("select r from ReservaEntity r");
        return q.getResultList();
    }
    
    public ReservaEntity create(ReservaEntity entity){
        em.persist(entity);
        return entity;
    }
    
    public ReservaEntity update(ReservaEntity entity){
        return em.merge(entity);
    }
}
