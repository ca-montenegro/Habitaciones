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

import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Query;


/**
 * @author c.penaloza
 * @generated
 */
@Stateless
public class ViviendaPersistence  {

    @PersistenceContext(unitName="habitacionesPU")
    protected EntityManager em;

    public ViviendaEntity find(Long id) {
      
        return em.find(ViviendaEntity.class, id);
    }

    public List<ViviendaEntity> findAll() {
       
        Query q = em.createQuery("select v from ViviendaEntity v");
        return q.getResultList();
    }

    public ViviendaEntity create(ViviendaEntity entity) {
      
        em.persist(entity);
        
        return entity;
    }

    public ViviendaEntity update(ViviendaEntity entity) {
       
        return em.merge(entity);
    }

    public void delete(Long id) {
        
        ViviendaEntity entity = em.find(ViviendaEntity.class, id);
        em.remove(entity);
    }

}