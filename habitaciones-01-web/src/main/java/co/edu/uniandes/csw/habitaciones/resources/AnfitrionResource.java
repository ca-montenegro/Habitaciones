/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.dtos.AnfitrionDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.AnfitrionLogic;
import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    
    
    
}
