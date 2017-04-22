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

import co.edu.uniandes.csw.habitaciones.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.habitaciones.dtos.UsuarioDTO;
import co.edu.uniandes.csw.habitaciones.dtos.ReservaDTO;
import co.edu.uniandes.csw.habitaciones.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.habitaciones.ejbs.ReservaLogic;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.UsuarioEntity;
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

/**
 *
 * @author ca.montenegro
 */
@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    
    public UsuarioResource(){}

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
    private List<UsuarioDTO> listEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDTO(entity));
        }
        return list;
    }
    
    private List<ReservaDTO> listEntity2DTOReserva(List<ReservaEntity> entityList) {
        List<ReservaDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDTO(entity));
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
    public List<UsuarioDTO> getUsuarios() {

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
    public UsuarioDetailDTO getUsuario(@PathParam("id") Long id) throws BusinessLogicException {
        return new UsuarioDetailDTO(usuarioLogic.getUsuario(id));
    }
    
    @GET
    @Path("{id:\\d+}/adminLogin}")
    public UsuarioDetailDTO getUsuarioAdmin(@PathParam("id") Long id) throws BusinessLogicException {
        return new UsuarioDetailDTO(usuarioLogic.getUsuario(id));
    }
    
    /**
     *Retorna una lista de reservas para un usuario con ID
     * @param id id del usuario
     * @return List<ReservaDTO> lista de reservasDTO
     * @throws BusinessLogicException
     */
    @GET
    @Path("{id:\\d+}/reservas")
    public List<ReservaDTO> getReservas(@PathParam("id") Long id) throws BusinessLogicException
    {
        return listEntity2DTOReserva(usuarioLogic.getUsuario(id).getReservas());
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
    public ReservaDTO getUsuarioReservaID(@PathParam("id") Long id, @PathParam("idReserva") String idReserva) throws BusinessLogicException {
        for (ReservaEntity re : usuarioLogic.getUsuario(id).getReservas()) {
            if (re.getCodigoReserva()==Long.parseLong(idReserva)) {
                return new ReservaDTO(re);
            }

        }
        return null;
    }

    /**
     * Se encarga de crear un Usuario en la base de datos
     *
     * @param dto Objeto de UsuarioDTO con los datos nuevos
     * @return Objeto de UsuarioDTO con los datos nuevos y su ID
     * @throws
     * co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException
     * 
     */
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO dto) throws BusinessLogicException {
        return new UsuarioDTO(usuarioLogic.createUsuario(dto.toEntity()));
    }

    /**
     * Crea una nueva reserva para un usuario
     * @param id id del usuario a agregar reserva
     * @param dto Objeto de ReservaDTO con los datos nuevos
     * @return true si se creo y agrego la reserva de usuario. False de los contrarío
     * @throws co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException
     */
    @POST
    @Path("{id: \\d+}/reserva")
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
     * @return UsuarioDTO actualizado
     * @throws co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("id") Long id, UsuarioDTO dto) throws BusinessLogicException {
        UsuarioEntity entity = dto.toEntity();
        entity.setNumeroID(id);
        return new UsuarioDTO(usuarioLogic.updateUsuario(entity));
    }

    /**
     * Metodo que actualiza una reserva de un usuario.
     * @param id id del usuario 
     * @param idReserva id de la reserva a actualizar
     * @param dto
     * @return 
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id: \\d+}/reservas/{idReserva}")
    public ReservaDTO updateReserva(@PathParam("id") Long id, @PathParam("idReserva") String idReserva, ReservaDTO dto) throws BusinessLogicException {
        List<ReservaEntity> listaReservas = usuarioLogic.getUsuario(id).getReservas();
        ReservaEntity reservaUpdate = null;
        
        for (ReservaEntity re : listaReservas) {
            int count = 0;

            if (re.getCodigoReserva()==Long.parseLong(idReserva)) {

                listaReservas.remove(count);
                reservaUpdate = reservaLogic.updateReserva(dto.toEntity());
                listaReservas.add(re);
                break;
            }
            count++;
        }
        if(reservaUpdate==null)
        {throw new BusinessLogicException("No existe la reserva con el id: "+idReserva+" para el usuario con id: " + id);}
        return new ReservaDTO(reservaUpdate);

    }
    
    
}
