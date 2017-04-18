
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
    
    public HabitacionEntity find(Long id)
    {
        return em.find(HabitacionEntity.class, id);
    }
    
    public List<HabitacionEntity> findAll() {
       
        Query q = em.createQuery("select u from HabitacionEntity u");
        return q.getResultList();
    }
    
    public List<HabitacionEntity> findAllVivienda(Long idVivienda) {
       
        Query q = em.createQuery("select u from HabitacionEntity u where vivienda_idVivienda=" + idVivienda);
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
