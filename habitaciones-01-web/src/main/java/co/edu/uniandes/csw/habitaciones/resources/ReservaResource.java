/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;


import co.edu.uniandes.csw.habitaciones.dtos.ReservaDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.ReservaLogic;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/reservas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservaResource {
    
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
}
