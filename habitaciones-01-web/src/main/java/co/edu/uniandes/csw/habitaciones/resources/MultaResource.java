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

import co.edu.uniandes.csw.habitaciones.dtos.MultaDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.MultaLogic;
import co.edu.uniandes.csw.habitaciones.entities.MultaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/multas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MultaResource {
    
    public MultaResource(){}
    
    @Inject private MultaLogic multaLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    private List<MultaDTO> listEntity2DTO (List<MultaEntity> entityList){
        List<MultaDTO> list= new ArrayList<>();
        for (MultaEntity entity : entityList){
            list.add(new MultaDTO(entity));
        }    
        return list;
    }
 
    @GET
    @Path("usuario{idU:\\d+}/multas")
    public MultaDTO getMulta( @PathParam("idU") Long idU) throws BusinessLogicException{
        MultaEntity multa = multaLogic.getMulta(idU);
        if( multa == null) throw new BusinessLogicException("Error 404");
        return new MultaDTO(multa);
    }
    
    @PUT
    @Path("usuario/{idU:\\d+}/multas")
    public MultaDTO updateMulta(@PathParam("idU") Long idU, MultaDTO dto)
    {
        MultaEntity entity = dto.toEntity();
        entity.setCodigoMulta(idU);
        return new MultaDTO(entity);
    }
    
    @POST
    @Path("usuario/{idU:\\d+}/multas")
    public MultaDTO createMulta(MultaDTO dto, @PathParam("idU") Long idU) throws BusinessLogicException{
        return new MultaDTO(multaLogic.createMulta(dto.toEntity()));
    }
}
