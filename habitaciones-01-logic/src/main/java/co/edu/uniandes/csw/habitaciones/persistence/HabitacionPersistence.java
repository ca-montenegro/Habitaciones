/* 
 * Copyright (C) 2017
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

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author l.maya10
 * @generated
 */
@Stateless
public class HabitacionPersistence {
    
    @PersistenceContext(unitName="habitacionesPU")
     protected EntityManager em;
    
    public HabitacionEntity find(Long id){
        return em.find(HabitacionEntity.class, id);
    }
    
    public HabitacionEntity findV(Long id, Long idV){
        Query q = em.createQuery("SELECT u FROM HABITACIONENTITY u WHERE u.ID="+id+"AND u.IDVIVIENDA=:" + idV);
        return (HabitacionEntity) q.getResultList().get(0);
    }
    
    public List<HabitacionEntity> findAll() {
       
        Query q = em.createQuery("select u from HabitacionEntity u");
        return q.getResultList();
    }
    
    public List<HabitacionEntity> findAllVivienda(Long idVivienda) {
       
        Query q = em.createQuery("SELECT u FROM HABITACIONENTITY u WHERE u.IDVIVIENDA=: " + idVivienda);
        return q.getResultList();
    }
    
    public HabitacionEntity create(HabitacionEntity entity) {
        em.persist(entity);
        return entity;
    }
    
    public HabitacionEntity update(HabitacionEntity entity) {
       
        return em.merge(entity);
    }
    
    public void delete(Long id) {
        
       HabitacionEntity entity = em.find(HabitacionEntity.class, id);
        em.remove(entity);
    }
}
