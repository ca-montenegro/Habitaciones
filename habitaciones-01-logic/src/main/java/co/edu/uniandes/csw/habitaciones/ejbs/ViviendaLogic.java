package co.edu.uniandes.csw.habitaciones.ejbs;


import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.ViviendaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 * @author c.penaloza
 * @generated
 */
@Stateless
public class ViviendaLogic {

    @Inject private ViviendaPersistence persistence;

    /**
     * Obtiene la lista de los registros de Vivienda.
     *
     * @return Colección de objetos de ViviendaEntity.
     * 
     */
   
    public List<ViviendaEntity> getViviendas() {
        return persistence.findAll();
    }


    /**
     * Obtiene los datos de una instancia de Vivienda a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ViviendaEntity con los datos del Vivienda consultado.
     *
     */
    public ViviendaEntity getVivienda(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Vivienda en la base de datos.
     *
     * @param entity Objeto de ViviendaEntity con los datos nuevos
     * @return Objeto de ViviendaEntity con los datos nuevos y su ID.
     * @generated
     */
    
    public ViviendaEntity createVivienda(ViviendaEntity entity) throws BusinessLogicException {
        if (entity.getDireccion().isEmpty()||entity.getCiudad().isEmpty())
            throw new BusinessLogicException("Debe completar los datos");
        if (entity.getValorDiario()<0.0)
            throw new BusinessLogicException("El precio no puede ser negativo.");
        if (entity.getHabitaciones().isEmpty())
            throw new BusinessLogicException("La vivienda debe tener por lo menos una habitación.");
        if (entity.getCapacidad()<=0)
            throw new BusinessLogicException("La vivienda debe tener capacidad para por lo menos una persona.");
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Vivienda.
     *
     * @param entity Instancia de ViviendaEntity con los nuevos datos.
     * @return Instancia de ViviendaEntity con los datos actualizados.
     * 
     */
    // TODO: revisar las validaciones de negocio
    public ViviendaEntity updateVivienda(ViviendaEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Vivienda de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
    // TODO: revisar las validaciones de negocio
    public void deleteVivienda(Long id) {
        persistence.delete(id);
    }
  
}
