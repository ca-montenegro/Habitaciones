/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.resources;

import co.edu.uniandes.csw.habitaciones.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.habitaciones.dtos.UsuarioDTO;
import co.edu.uniandes.csw.habitaciones.dtos.ViviendaDetailDTO;
import co.edu.uniandes.csw.habitaciones.dtos.ReservaDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.ReservaLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.ViviendaLogic;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.UsuarioEntity;
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
 *
 * @author ca.montenegro
 */
@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    private UsuarioLogic usuarioLogic;
    @Inject private ReservaLogic reservaLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("limit")
    private Integer maxRecords;

    /**
     * Convierte una lista de UsuarioEntity a una lista de UsuarioDetailDTO.
     *
     * @param entityList Lista de UsuarioEntity a convertir.
     * @return Lista de UsuarioDetailDTO convertida.
     * @generated
     */
    private List<UsuarioDetailDTO> listEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene la lista de los registros de Usuarios
     *
     * @return Colección de objetos de UsuarioDetailDTO
     * @generated
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios() {

        return listEntity2DTO(usuarioLogic.getUsuarios());
    }

    /**
     * Obtiene los datos de una instancia de Usuario a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de UsuarioDetailDTO con los datos del Usuario
     * consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("id") Long id) {
        return new UsuarioDetailDTO(usuarioLogic.getUsuario(id));
    }

    /**
     * Obtiene los datos de una instancia de reserva de un usuario.
     *
     * @param id
     * @param idReserva
     * @return
     */
    @GET
    @Path("{id: \\d+}/reservas/{idReserva}")
    public ReservaDTO getUsuarioReservaID(@PathParam("id") Long id, @PathParam("idReserva") String idReserva) {
        for (ReservaEntity re : usuarioLogic.getUsuario(id).getReservas()) {
            if (re.getCodigoReserva().equals(idReserva)) {
                return new ReservaDTO(re);
            }

        }
        return null;
    }

    /**
     * Se encarga de crear un Usuario en la base de datos
     *
     * @param dto Objeto de UsuarioDetailDTO con los datos nuevos
     * @return Objeto de UsuarioDetailDTOcon los datos nuevos y su ID
     * @throws
     * co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException
     * @generated
     */
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO dto) throws BusinessLogicException {
        return new UsuarioDTO(usuarioLogic.createUsuario(dto.toEntity()));
    }

    @POST
    @Path("{id: \\d+}")
    public boolean createUsuarioReserva(@PathParam("id") Long id, ReservaDTO dto) throws BusinessLogicException {
        ReservaEntity reve = reservaLogic.createReserva(dto.toEntity());
        return usuarioLogic.getUsuario(id).agregarReserva(reve);
        //if(reservo) 
        //return getUsuarioReservaID(id, reve.getCodigoReserva());
        // return null;

    }

    /**
     * Actualiza la información de una instancia de Usuario
     *
     * @param id Identificador de la instancia de Usuario a modificar
     * @param dto Instancia UsuarioDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("id") Long id, UsuarioDTO dto) {
        UsuarioEntity entity = dto.toEntity();
        entity.setNumeroID(id);
        return new UsuarioDTO(usuarioLogic.updateUsuario(entity));
    }

    @PUT
    @Path("{id: \\d+}/reservas/{idReserva}")
    public void updateReserva(@PathParam("id") Long id, @PathParam("idReserva") String idReserva, ReservaDTO dto) {
        List<ReservaEntity> listaReservas = usuarioLogic.getUsuario(id).getReservas();

        for (ReservaEntity re : listaReservas) {
            int count = 0;

            if (re.getCodigoReserva().equals(idReserva)) {

                listaReservas.remove(count);
                re = reservaLogic.updateReserva(dto.toEntity());
                listaReservas.add(re);
                break;
            }
            count++;
        }
        

    }
}
