// TODO: eliminar los comentarios por defecto
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
// TODO: eliminar los import que no se usan
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
 * @author l.maya10
 */
@Path("/habitaciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HabitacionResource {
    
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
     @Path("/viviendas/{idV:\\d+}/habitaciones/")
        public List<HabitacionDTO> getHabitaciones(@PathParam("idV") Long idV) {
        return listEntity2DTO(habitacionLogic.getHabitaciones());
    }
    
    // TODO: generar una excepción / error 404 si no existe
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
    
    @DELETE
    @Path("/viviendas/{idV:\\d+}/habitaciones/{id}")
        public void deleteHabitacion (@PathParam("idV") Long idV, @PathParam("id") Long id) {
        habitacionLogic.deleteHabitacion(id);
    }
}
