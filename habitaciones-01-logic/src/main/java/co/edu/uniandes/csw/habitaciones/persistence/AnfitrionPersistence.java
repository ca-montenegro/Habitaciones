/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jd.cardenas10
 */
@Stateless
public class AnfitrionPersistence {
    
    @PersistenceContext(unitName="habitacionesPU")
    protected EntityManager em;
    
    public AnfitrionEntity find(Long id)
    {
        return em.find(AnfitrionEntity.class, id);
    }
    
    public List<AnfitrionEntity> findAll()
    {
        Query q = em.createQuery("select u from AnfitrionEntity u");
        return q.getResultList();
    }

    public AnfitrionEntity create(AnfitrionEntity entity) {
        em.persist(entity);
        return entity;
    }
    
    public AnfitrionEntity update(AnfitrionEntity entity)
    {
        return em.merge(entity);
    }
    
    
    
}
