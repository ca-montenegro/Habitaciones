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
    @Path("usuario/{idU:\\d+}/multas")
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
