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
import co.edu.uniandes.csw.habitaciones.dtos.ViviendaDTO;
import co.edu.uniandes.csw.habitaciones.dtos.ViviendaDetailDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.AnfitrionLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.HabitacionLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.ViviendaLogic;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
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
import java.util.ArrayList;
import java.util.Objects;
import javax.ws.rs.WebApplicationException;
import org.springframework.util.Assert;


@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ViviendaResource {
    
    
    private final ViviendaLogic viviendaLogic;
    private final HabitacionLogic habitacionLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    
    /**
     *
     * @param viviendaLogic
     */
    @Inject
    public ViviendaResource (ViviendaLogic viviendaLogic, HabitacionLogic habitacionLogic) {
        Assert.notNull(viviendaLogic, "Vivienda logic no puede ser nula!");
        this.viviendaLogic = viviendaLogic;
        
        Assert.notNull(habitacionLogic, "Habitacion logic no puede ser nula!");
        this.habitacionLogic = habitacionLogic;
    }
    
    /**
     * Convierte una lista de ViviendaEntity a una lista de ViviendaDetailDTO.
     *
     * @param entityList Lista de ViviendaEntity a convertir.
     * @return Lista de ViviendaDetailDTO convertida.
     * @generated
     */
    private List<ViviendaDTO> listEntity2DTO(List<ViviendaEntity> entityList){
        List<ViviendaDTO> list = new ArrayList<>();
        for (ViviendaEntity entity : entityList) {
            list.add(new ViviendaDTO(entity));
        }
        return list;
    }
    
    /**
     * Obtiene la lista de los registros de Vivienda
     * @return Colección de objetos de ViviendaDetailDTO
     * @generated
     */
    @GET
    @Path("/viviendas")
    public List<ViviendaDTO> getViviendas() {
        return listEntity2DTO(viviendaLogic.getViviendas());
    }
    
    /**
     * Obtiene los datos de una instancia de Vivienda a partir de su ID
     *
     * @param idA
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ViviendaDetailDTO con los datos del Vivienda consultado
     * @generated
     */
    @GET
    @Path("/viviendas/{id: \\d+}")
    public ViviendaDetailDTO getVivienda(@PathParam("id") Long id) throws BusinessLogicException {
        ViviendaEntity vivienda = viviendaLogic.getVivienda(id);
        if (vivienda==null){
            throw new WebApplicationException("La vivienda no existe", 404);
        }
        return new ViviendaDetailDTO(vivienda);
    }
    
    /**
     * Se encarga de crear un Vivienda en la base de datos
     * @param dto Objeto de ViviendaDetailDTO con los datos nuevos
     * @return Objeto de ViviendaDetailDTOcon los datos nuevos y su ID
     * @throws co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException
     * @generated
     */
    @POST
    @Path("/anfitriones/{idA:\\d+}/viviendas/")
    public ViviendaDetailDTO createVivienda(ViviendaDetailDTO dto) throws BusinessLogicException {
        return new ViviendaDetailDTO(viviendaLogic.createVivienda(dto.toEntity()));
    }
    
    
    /**
     * Elimina una instancia de Vivienda de la base de datos
     * @param id Identificador de la instancia a eliminar
     * @throws co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException
     * @generated
     */
    @DELETE
    @Path("/viviendas/{id: \\d+}")
    public void deleteVivienda(@PathParam("id") Long id) throws BusinessLogicException {
        ViviendaEntity vivienda = viviendaLogic.getVivienda(id);
        if (vivienda==null){
            throw new WebApplicationException("La vivienda no existe", 404);
        }
        viviendaLogic.deleteVivienda(id);
    }
    
    /**
     *Crea una nueva habitación en la vivienda del id dado por parámetro con los datos en el dto.
     * @param dto nueva habitación
     * @param idV id vivienda
     * @return objeto HabitacionDetailDTO de la nueva habitación.
     * @throws BusinessLogicException si no existe una vivienda con el id dado
     */
    @POST
    @Path("/viviendas/{idV:\\d+}/habitaciones/")
    public HabitacionDetailDTO createHabitacion(HabitacionDTO dto, @PathParam("idV") Long idV) throws BusinessLogicException {
        ViviendaEntity v = viviendaLogic.getVivienda(idV);
        List<HabitacionEntity> h = v.getHabitaciones();
        HabitacionEntity nueva = dto.toEntity();
        nueva.setVivienda(v);
        h.add(nueva);
        v.setHabitaciones(h);
        viviendaLogic.updateVivienda(v);
        return new HabitacionDetailDTO(habitacionLogic.createHabitacion(nueva));
    }
    
    /**
     *Retorna una habitacion buscada
     * @param idV id de la vivienda
     * @param id id de la habitacion buscada
     * @return HabitacionDetailDTO de la habitacion buscada
     * @throws BusinessLogicException
     */
    @GET
    @Path("/viviendas/{idV:\\d+}/habitaciones/{id}")
    public HabitacionDetailDTO getHabitacion(@PathParam("idV") Long idV, @PathParam("id") Long id) throws BusinessLogicException {
        ViviendaEntity v = viviendaLogic.getVivienda(idV);
        HabitacionEntity buscada = null;
        for (HabitacionEntity h: v.getHabitaciones())
        {
            if(Objects.equals(h.getId(), id))
            {
                buscada = h;
            }
        }
        if (buscada==null)
        {
            throw new WebApplicationException("La habitación no existe", 404);
        }
        return new HabitacionDetailDTO(buscada);
    }
    
    /**
     * Retorna todas las habitaciones de una vivienda
     * @param idV id de la vivienda
     * @return Lista de HabitacionDTO
     * @throws co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException
     */
    @GET
    @Path("viviendas/{idV:\\d+}/habitaciones/")
    public List<HabitacionDTO> getHabitaciones(@PathParam("idV") Long idV) throws BusinessLogicException {
        if (viviendaLogic.getVivienda(idV)==null){
            throw new WebApplicationException("La vivienda no existe", 404);
        }
        //return HabitacionResource.listEntity2DTO(habitacionLogic.getHabitaciones());
        return HabitacionResource.listEntity2DTO(habitacionLogic.getHabitacionesVivienda(idV));
    }
    
}
