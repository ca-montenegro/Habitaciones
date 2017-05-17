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
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.dtos.AnfitrionBasicDTO;
import co.edu.uniandes.csw.habitaciones.dtos.AnfitrionDTO;
import co.edu.uniandes.csw.habitaciones.dtos.AnfitrionDetailDTO;
import co.edu.uniandes.csw.habitaciones.dtos.ViviendaDTO;
import co.edu.uniandes.csw.habitaciones.dtos.ViviendaDetailDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.AnfitrionLogic;
import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jd.cardenas10
 */
@Path("/anfitriones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AnfitrionResource {
    
    public AnfitrionResource(){
    
    }
    
    @Inject
    private AnfitrionLogic anfitrionlogic;
    
    @POST
    public AnfitrionDTO createAnfitrion(AnfitrionDTO dto){
        AnfitrionEntity anfitrion=dto.toEntity();
        AnfitrionEntity stored=anfitrionlogic.createAnfitrion(anfitrion);
        return new AnfitrionDTO(stored);
    }
    
    @GET
    public List<AnfitrionDTO> getAnfitriones(){
        List<AnfitrionDTO> habitacionDTOs=new ArrayList();
        
        List<AnfitrionEntity> anfitriones=anfitrionlogic.getAnfitriones();
        for(AnfitrionEntity company: anfitriones){
            AnfitrionDTO dto=new AnfitrionDTO(company);
            habitacionDTOs.add(dto);
        }
        return habitacionDTOs;
    }
    
    public AnfitrionDTO getAnfitrion(Long id){
        AnfitrionDTO buscado=new AnfitrionDTO(anfitrionlogic.getAnfitrion(id));
  
        return buscado;
    }

    public AnfitrionBasicDTO getAnfitrionBasic(Long id){
        AnfitrionBasicDTO buscado=new AnfitrionBasicDTO(anfitrionlogic.getAnfitrion(id));
  
        return buscado;
    }

    @POST
    @Path("{id: \\d+}/viviendas")
    public ViviendaDTO agregarVivienda(@PathParam("id") Long id, ViviendaDTO dto) throws Exception{
        dto.setAnfitrion(getAnfitrionBasic(id));
        ViviendaEntity vivienda=dto.toEntity();
        ViviendaEntity stored=anfitrionlogic.createVivienda(id,vivienda);
        return new ViviendaDTO(stored);
    }
    
     /**
     * Actualiza la información de una instancia de Vivienda
     * @param idA
     * @param id Identificador de la instancia de Vivienda a modificar
     * @param dto Instancia de ViviendaDetailDTO con los nuevos datos
     * @return Instancia de ViviendaDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{idA}/viviendas/{id}")
    public ViviendaDetailDTO updateVivienda(@PathParam("idA") Long idA, @PathParam("id") Long id, ViviendaDetailDTO dto) {
        ViviendaEntity entity = dto.toEntity();
        entity.setIdVivienda(id);
        return new ViviendaDetailDTO(dto.toEntity());
    }
}
