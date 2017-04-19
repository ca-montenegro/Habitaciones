package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.persistence.ReservaPersistence;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ReservaLogic {
    
    static final long serialVersionUID = 1L;
    
    @Inject 
    private ReservaPersistence persistence;
   
    public List<ReservaEntity> getReservas() {
        return persistence.findAll();
    }
    
    public ReservaEntity getReserva(Long codigo){
        return persistence.find(codigo);
    }
    
    public ReservaEntity createReserva(ReservaEntity entity) throws BusinessLogicException {
        if(entity.getFechaFin().after(entity.getFechaInicio()))
            throw new BusinessLogicException("La fecha final de la reserva no puede ser antes de la fecha de inicio de la reserva.");
        if(entity.getCosto()< 0.0)
             throw new BusinessLogicException("El costo no puede ser negativo.");
        if(entity.getEstado() == 'C')
             throw new BusinessLogicException("No se puede crear una reserva cancelada.");
        persistence.create(entity);
        return entity;
    }
    
    public ReservaEntity updateReserva(ReservaEntity entity)
    {
        return persistence.update(entity);
    }
}
