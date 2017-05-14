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
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @generated
 */
@XmlRootElement
public class ViviendaDetailDTO extends ViviendaDTO{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Lista de reservas dto
     */
    private List<ReservaDTO> reservas;
    
    /**
     * Lista de Habitaciones dto
     */
    private List<HabitacionDTO> habitaciones;
    
    /**
     * Retorna las habitaciones
     * @return habitaciones
     */
    public List<HabitacionDTO> getHabitaciones() {
        return habitaciones;
    }
    
    /**
     * Modifica las habiraciones
     * @param habitaciones nuevas
     */
    public void setHabitaciones(List<HabitacionDTO> habitaciones) {
        this.habitaciones = habitaciones;
    }
    
    /**
     * Retorna las reservas
     * @return reservas
     */
    public List<ReservaDTO> getReservas() {
        return reservas;
    }
    
    /**
     * Cambia las reservas
     * @param reservas nuevas
     */
    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
    
    /**
     * Constructor vivienda dto
     */
    public ViviendaDetailDTO(){
        super();
        ViviendaEntity entity = super.toEntity();
        if(entity.getHabitaciones()!=null){
            this.habitaciones=new ArrayList();
            for(HabitacionEntity habitacion:entity.getHabitaciones()){
                this.habitaciones.add(new HabitacionDTO(habitacion));
            }
        }
        
        if(entity.getReservas()!=null){
            this.reservas=new ArrayList();
            for(ReservaEntity reserva:entity.getReservas()){
                this.reservas.add(new ReservaDTO(reserva));
            }
        }
    }
    
    /**
     * Crea un objeto ViviendaDetailDTO a partir de un objeto ViviendaEntity incluyendo los atributos de ViviendaDTO.
     *
     * @param entity Entidad ViviendaEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ViviendaDetailDTO(ViviendaEntity entity) {
        super(entity);
        if(entity!=null){
            if(entity.getHabitaciones()!=null){
                this.habitaciones=new ArrayList();
                for(HabitacionEntity habitacion:entity.getHabitaciones()){
                    this.habitaciones.add(new HabitacionDTO(habitacion));
                }
            }
            
            if(entity.getReservas()!=null){
                this.reservas=new ArrayList();
                for(ReservaEntity reserva:entity.getReservas()){
                    this.reservas.add(new ReservaDTO(reserva));
                }
            }
        }
    }
    
    /**
     * Convierte un objeto ViviendaDetailDTO a ViviendaEntity incluyendo los atributos de ViviendaDTO.
     *
     * @return Nueva objeto ViviendaEntity.
     * @generated
     */
    @Override
    public ViviendaEntity toEntity() {
        ViviendaEntity entity = new ViviendaEntity();
        entity.setIdVivienda(this.getIdVivienda());
        entity.setCiudad(this.getCiudad());
        entity.setDescripcion(this.getDescripcion());
        entity.setDireccion(this.getDireccion());
        entity.setCapacidad(this.getCapacidad());
        
        entity.setValorDiario(this.getValorDiario());
        entity.setImagen(this.getImagen());
        entity.setHabitaciones(this.getHabitaciones());
        
        if(entity.getHabitaciones()!=null){
            this.habitaciones=new ArrayList();
            for(HabitacionEntity habitacion:entity.getHabitaciones()){
                this.habitaciones.add(new HabitacionDTO(habitacion));
            }
        }
        
        if(entity.getReservas()!=null){
            this.reservas=new ArrayList();
            for(ReservaEntity reserva:entity.getReservas()){
                this.reservas.add(new ReservaDTO(reserva));
            }
        }
        return entity;
    }
    
}
