/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *URI: habitaciones/
 * @author l.maya10 c.penaloza
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HabitacionResource {
    
    @Inject private HabitacionLogic habitacionLogic;
    @Inject private ViviendaLogic viviendaLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
 
     private List<HabitacionDetailDTO> listEntity2DTO(List<HabitacionEntity> entityList){
        List<HabitacionDetailDTO> list = new ArrayList<>();
        for (HabitacionEntity entity : entityList) {
            list.add(new HabitacionDetailDTO(entity));
        }
        return list;
    }
    
     @GET
     @Path("/viviendas/{idV:\\d+}/habitaciones/")
        public List<HabitacionDetailDTO> getHabitaciones(@PathParam("idV") Long idV) {
        return listEntity2DTO(habitacionLogic.getHabitaciones());
    }
    
    @GET
    @Path("/viviendas/{idV:\\d+}/habitaciones/{id: \\d+}")
    public HabitacionDetailDTO getHabitacion(@PathParam("id") Long id, @PathParam("idV") Long idV) {
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
        return buscada;
    }
    
     @POST
     @Path("/viviendas/{idV:\\d+}/habitaciones/")
    public HabitacionDetailDTO createHabitacion(HabitacionDetailDTO dto, @PathParam("idV") Long idV) throws BusinessLogicException {
        ViviendaEntity v = viviendaLogic.getVivienda(idV);
         List<HabitacionEntity> h = v.getHabitaciones();
         h.add(dto.toEntity());
         viviendaLogic.updateVivienda(v);
        return new HabitacionDetailDTO(habitacionLogic.createHabitacion(dto.toEntity()));
    }
    
    @PUT
    @Path("{id: \\d+}")
    public HabitacionDetailDTO updateHabitacion(@PathParam("id") Long id, HabitacionDetailDTO dto) {
       HabitacionEntity entity = dto.toEntity();
        entity.setId(id);
        return new HabitacionDetailDTO(habitacionLogic.updateHabitacion(entity));
    }
    
    @DELETE
    @Path("{id: \\d+}")
        public void deleteHabitacion (@PathParam("id") Long id) {
        habitacionLogic.deleteEmployee(id);
    }
}
