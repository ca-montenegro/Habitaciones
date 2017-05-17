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
package co.edu.uniandes.csw.habitaciones.ejbs;


import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.habitaciones.persistence.ViviendaPersistence;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.util.Assert;

/**
 * Clase de logica del recurso vivienda
 * @author c.penaloza
 */
@Stateless
public class ViviendaLogic{
    /**
     * Constructor por defecto
     * Inicializa la persistencia
     */
    public ViviendaLogic(){
        persistence = new ViviendaPersistence();
    }
    
    /**
     * Serializable
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Persistencia
     */
    private final ViviendaPersistence persistence;
    
    /**
     * Constructor
     * Inyecta la persistencia
     * @param persistence persistencia
     */
    @Inject
    public ViviendaLogic (ViviendaPersistence persistence){
        Assert.notNull(persistence, "My persistence must be not null");
        this.persistence = persistence;
    }
    
    /**
     * Obtiene la lista de los registros de Vivienda.
     * @return Colección de objetos de ViviendaEntity.
     */
    public List<ViviendaEntity> getViviendas(){
        return persistence.findAll();
    }
    
    /**
     * Obtiene los datos de una instancia de Vivienda a partir de su ID.
     * Revisa que la vivienda no sea nula
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ViviendaEntity con los datos 
     * del Vivienda consultado.
     * @throws co.edu.uniandes.csw.habitaciones.exceptions.
     * BusinessLogicException si no existe una vivienda con el id dado
     */
    public ViviendaEntity getVivienda(Long id) throws BusinessLogicException{
        ViviendaEntity vivienda = persistence.find(id);
        if (vivienda==null){
            throw new BusinessLogicException("La vivienda con el id dado no existe.");
        }
        return vivienda;
    }
    
    /**
     * Se encarga de crear un Vivienda en la base de datos.
     * Realiza verificaciones necesarias
     * @param entity Objeto de ViviendaEntity con los datos nuevos
     * @return Objeto de ViviendaEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.habitaciones.exceptions.
     * BusinessLogicException
     * @generated
     */
    
    public ViviendaEntity createVivienda(ViviendaEntity entity) throws BusinessLogicException{
        if (entity.getDireccion().isEmpty()||entity.getCiudad().isEmpty()){
            throw new BusinessLogicException("Debe incluir la dirección completa.");}
        if (entity.getValorDiario()<0.0){
            throw new BusinessLogicException("El precio no puede ser negativo.");}
        if (entity.getCapacidad()<0){
            throw new BusinessLogicException("La vivienda debe tener capacidad para por lo menos una persona.");}
        persistence.create(entity);
        return entity;
    }
    
    /**
     * Actualiza la información de una instancia de Vivienda.
     * @param entity Instancia de ViviendaEntity con los nuevos datos.
     * @return Instancia de ViviendaEntity con los datos actualizados.
     * @throws co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException
     */
    public ViviendaEntity updateVivienda(ViviendaEntity entity) throws BusinessLogicException{
        if (entity.getDireccion().isEmpty()||entity.getCiudad().isEmpty()){
            throw new BusinessLogicException("Debe incluir la dirección completa.");}
        if (entity.getValorDiario()<0.0){
            throw new BusinessLogicException("El precio no puede ser negativo.");}
        if (entity.getCapacidad()<0){
            throw new BusinessLogicException("La capacidad de la vivienda no puede ser negativa.");}
        return persistence.update(entity);
    }
    
    /**
     * Elimina una instancia de Vivienda de la base de datos.
     * @param id Identificador de la instancia a eliminar.
     * @throws co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException
     */
    public void deleteVivienda(Long id) throws BusinessLogicException{
        ViviendaEntity vivienda = getVivienda(id);
        Date d = new Date();
        for (ReservaEntity r: vivienda.getReservas()){
            if (r.getFechaInicio().after(d)){
                throw new BusinessLogicException("No se puede borrar una vivienda con reservas futuras.");
            }
        }
        for (HabitacionEntity h: vivienda.getHabitaciones()){
            for (ReservaEntity re: h.getReservas()){
                if (re.getFechaInicio().after(d)){
                    throw new BusinessLogicException
                ("No se puede borrar una vivienda con"
                        + " habitaciones que tienen reservas futuras.");
                }
            }
        }
        persistence.delete(id);
    }
}
