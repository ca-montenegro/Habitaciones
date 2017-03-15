/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

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
    
    //@GET
    //@Path("{id: \\d+}/viviendas")
    //public AnfitrionDetailDTO getViviendas(@PathParam("id") Long id){
    //    return new AnfitrionDetailDTO(anfitrionlogic.getAnfitrion(id));
    //}
    
    @PUT
    @Path("{id: \\d+}/viviendas")
    public ViviendaDTO agregarVivienda(@PathParam("id") Long id, ViviendaDTO dto) throws Exception{
        ViviendaEntity vivienda=dto.toEntity();
        ViviendaEntity stored=anfitrionlogic.createVivienda(id,vivienda);
        return new ViviendaDTO(stored);
    }
    
    
}
