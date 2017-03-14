/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.persistence.AnfitrionPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jd.cardenas10
 */
public class AnfitrionLogic {
    
    @Inject 
    private AnfitrionPersistence persistence;
    
    public List<AnfitrionEntity> getAnfitriones()
    {
        return persistence.findAll();
    }

    public AnfitrionEntity createAnfitrion(AnfitrionEntity entity) {
        return persistence.create(entity);
    }
    
    
}
