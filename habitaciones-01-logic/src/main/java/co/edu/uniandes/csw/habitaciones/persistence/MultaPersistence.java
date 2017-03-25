package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.MultaEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MultaPersistence {
    
    @PersistenceContext(unitName = "habitacionesPU")
    protected EntityManager em;
    
    public MultaEntity find(String codigo){
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