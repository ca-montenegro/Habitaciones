package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.AnfitrionPersistence;
import co.edu.uniandes.csw.habitaciones.persistence.ViviendaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.cardenas10
 */
@Stateless
public class AnfitrionLogic {
    
    @Inject 
    private AnfitrionPersistence persistence;
    @Inject
    private ViviendaPersistence persistenceVivienda;
    
    public List<AnfitrionEntity> getAnfitriones()
    {
        return persistence.findAll();
    }

    public AnfitrionEntity createAnfitrion(AnfitrionEntity entity) {
        return persistence.create(entity);
    }

    public AnfitrionEntity getAnfitrion(Long id) {
       return persistence.find(id);
    }

    public ViviendaEntity createVivienda(long id,ViviendaEntity vivienda) throws Exception{
       ViviendaEntity entity= persistenceVivienda.create(vivienda);
       AnfitrionEntity anfitrion=persistence.find(id);
       if(anfitrion==null){throw new BusinessLogicException("El Anfitrion no se encontro");}
       List<ViviendaEntity> viviendas=anfitrion.getViviendas();
       if(viviendas==null){
           viviendas=new ArrayList();
           viviendas.add(entity);
       }
       else{
           viviendas.add(entity);
       }
       persistence.update(anfitrion);
       return entity;
    }
    
    
}
