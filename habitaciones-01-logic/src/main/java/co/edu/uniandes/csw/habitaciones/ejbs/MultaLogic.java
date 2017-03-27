package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.MultaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.MultaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MultaLogic {
    
    @Inject
    private MultaPersistence persistence;
    
    public MultaEntity getMulta(Long codigo){
        return persistence.find(codigo);
    }
            
    public MultaEntity createMulta(MultaEntity entity) throws BusinessLogicException{
        if( persistence.find(entity.getReserva().getCodigoReserva()) == null)
        {
            throw new BusinessLogicException("Ya existe una multa asociada a esa reserva.");
        }
        
        persistence.create(entity);
        return entity;
    }
    
    public MultaEntity updateMulta(MultaEntity entity){
        return persistence.update(entity);
    }
}
