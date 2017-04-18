
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.HabitacionPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author l.maya10
 * @generated
 */
@Stateless
public class HabitacionLogic {
    @Inject private HabitacionPersistence persistence;
    
    public List<HabitacionEntity> getHabitaciones()
    {
        return persistence.findAll();
    }
    
    public List<HabitacionEntity> getHabitacionesVivienda(Long idVivienda)
    {
        return persistence.findAllVivienda(idVivienda);
    }
    
    public HabitacionEntity getHabitacion(Long id)
    {
        return persistence.find(id);
    }
    
    public HabitacionEntity createHabitacion(HabitacionEntity entity) throws BusinessLogicException
    {
        if(entity.getArea() <= 0.0)
            throw new BusinessLogicException ("El area debe ser mayor a 0");
        if(entity.getCapacidad() <= 0)
            throw new BusinessLogicException("La capacidad debe ser mayor a 0");
        if(entity.getValorDiario() <= 0)
            throw new BusinessLogicException("El precio debe ser mayor a cero");
        if(entity.getVivienda() == null)
            throw new BusinessLogicException("La habitacion debe pertenecer a una vivienda");
        persistence.create(entity);
        return entity;
    }
    
    public HabitacionEntity updateHabitacion(HabitacionEntity entity) {
        return persistence.update(entity);
    }
    
    public void deleteHabitacion(Long id) {
        persistence.delete(id);
    }
}
