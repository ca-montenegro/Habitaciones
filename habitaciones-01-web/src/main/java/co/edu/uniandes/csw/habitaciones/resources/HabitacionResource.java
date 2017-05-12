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

import co.edu.uniandes.csw.habitaciones.dtos.HabitacionDTO;
import co.edu.uniandes.csw.habitaciones.dtos.HabitacionDetailDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.HabitacionLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.ViviendaLogic;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *URI: habitaciones/
 * @author l.maya10
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HabitacionResource {
    
    public HabitacionResource(){}
    
    @Inject private HabitacionLogic habitacionLogic;
    @Inject private ViviendaLogic viviendaLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
 
     public static List<HabitacionDTO> listEntity2DTO(List<HabitacionEntity> entityList){
        List<HabitacionDTO> list = new ArrayList<>();
        for (HabitacionEntity entity : entityList) {
            list.add(new HabitacionDTO(entity));
        }
        return list;
    }
    
     @GET
     @Path("/habitaciones/")
        public List<HabitacionDTO> getHabitaciones() {
        return listEntity2DTO(habitacionLogic.getHabitaciones());
    }
        
        
    @GET
    @Path("/viviendas/{idV:\\d+}/habitaciones/{id: \\d+}")
    public HabitacionDetailDTO getHabitacion(@PathParam("id") Long id, @PathParam("idV") Long idV) throws BusinessLogicException {
        HabitacionDetailDTO buscada = null;
        ViviendaEntity vivienda = viviendaLogic.getVivienda(idV);
        List<HabitacionEntity> habitaciones = vivienda.getHabitaciones();
         for (HabitacionEntity h: habitaciones){
             if (h.getId()==idV){
                habitaciones.add(h);
                viviendaLogic.updateVivienda(vivienda);
                buscada = new HabitacionDetailDTO(h);
             }
         }
         if (buscada == null)
         {
             throw new BusinessLogicException("Error 404");
         }
        return buscada;
    }
    
    @DELETE
    @Path("/viviendas/{idV:\\d+}/habitaciones/{id}")
        public void deleteHabitacion (@PathParam("idV") Long idV, @PathParam("id") Long id) {
        habitacionLogic.deleteHabitacion(id);
    }
        
            /**
     *Actualiza la información de una habitación específica en una vivienda dada.
     * @param idV id vivienda
     * @param id id habitacion a modificar
     * @param dto nueva información de la habitación
     * @return HabitacionDetailDTO con la nueva información de la habitación
     * @throws co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException
     */
    @PUT
    @Path("/viviendas/{idV:\\d+}/habitaciones/{id}")
    public HabitacionDetailDTO updateHabitacion(@PathParam("idV") Long idV, @PathParam("id") Long id, HabitacionDetailDTO dto) throws BusinessLogicException {
        getHabitacion(idV, id);
        HabitacionEntity entity = dto.toEntity();
        entity.setId(id);
        return new HabitacionDetailDTO(entity);
    }
}
