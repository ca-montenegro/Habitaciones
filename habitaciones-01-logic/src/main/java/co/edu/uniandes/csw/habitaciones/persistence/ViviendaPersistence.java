package co.edu.uniandes.csw.habitaciones.persistence;

import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.Query;


/**
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
