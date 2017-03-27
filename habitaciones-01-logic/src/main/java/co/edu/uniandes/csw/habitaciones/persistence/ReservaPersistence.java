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
    
    
   // public ReservaEntity findByCodigoReserva(Date fechaInicio, Date fechaFin, Parameter<Date> param) {
     //  
       // TypedQuery<ReservaEntity> q
      //          = em.createQuery("select u from ReservaEntity u where u.carnet = :carnet", ReservaEntity.class);
      //  q = q.setParameter(param, fechaInicio, TemporalType.TIMESTAMP);
      // List<ReservaEntity> sameCarnet = q.getResultList();
       // if (sameCarnet.isEmpty() ) {
       //     return null; 
        //} else {
         //   return sameCarnet.get(0);
        //}
    //}
    
    // aIni < bFin && aFin > bIni
}
