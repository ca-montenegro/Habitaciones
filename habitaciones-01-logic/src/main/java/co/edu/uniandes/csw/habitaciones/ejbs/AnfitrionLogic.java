/* 
 * Copyright (C) 2017 c.penaloza.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
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
    
    static final long serialVersionUID = 1L;
    
    public AnfitrionLogic(){}
    
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
