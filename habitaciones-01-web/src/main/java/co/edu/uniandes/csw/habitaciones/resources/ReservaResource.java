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


import co.edu.uniandes.csw.habitaciones.dtos.ReservaDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.ReservaLogic;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
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

@Path("/reservas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaResource {
    
    public ReservaResource(){}
    
    @Inject private ReservaLogic reservaLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    private List<ReservaDTO> listEntity2DTO (List<ReservaEntity> entityList){
        List<ReservaDTO> list= new ArrayList<>();
        for (ReservaEntity entity : entityList){
            list.add(new ReservaDTO(entity));
        }    
        return list;
    }
    
    @GET
    public List<ReservaDTO> getReservas(){
        return listEntity2DTO(reservaLogic.getReservas());
    }
    
    @GET
    @Path("{id: \\d+}")
    public ReservaDTO getReserva (@PathParam("id") Long id){
        ReservaEntity reserva = reservaLogic.getReserva(id);
        return new ReservaDTO(reserva);
    }
    
    @POST
    @Path("{id: \\d+}")
    public ReservaDTO createReserva(ReservaDTO dto)throws BusinessLogicException{
        return new ReservaDTO(reservaLogic.createReserva(dto.toEntity()));
    } 
            
    @PUT
    @Path("{id: \\d+}")
    public ReservaDTO updateReserva(@PathParam("id") Long id, ReservaDTO dto){
        ReservaEntity entity = dto.toEntity();
        entity.setCodigoReserva(id);
        return new ReservaDTO(reservaLogic.updateReserva(entity));
    }
}
