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

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
@XmlRootElement
public class ViviendaDTO  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Long idVivienda;
    private String descripcion;
    private String ciudad;
    private String direccion;
    private Double valorDiario;
    private int capacidad;
    private String imagen;
    private AnfitrionBasicDTO anfitrion;
    private int numeroHabitaciones;
    
    
    /**
     * Constructor por defecto
     * @generated
     */
    public ViviendaDTO(){
        
    }
    
    /**
     * Crea un objeto ViviendaDTO a partir de un objeto ViviendaEntity.
     *Constructor con entidad
     * @param entity Entidad ViviendaEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ViviendaDTO(ViviendaEntity entity){
        if (entity!=null){
            this.idVivienda=entity.getIdVivienda();
            this.anfitrion=new AnfitrionBasicDTO(entity.getAnfitrion());
            this.ciudad=entity.getCiudad();
            this.descripcion=entity.getDescripcion();
            this.direccion=entity.getDireccion();
            this.capacidad=entity.getCapacidad();
            this.valorDiario=entity.getValorDiario();
            this.imagen=entity.getImagen();
            this.numeroHabitaciones=entity.getHabitaciones().size();
        }
    }
    /**
     * Constructor necesario para poder evitar ciclos infinitos
     * al momento que ReservaDTO llama al constructor normal
     * @param entity entidad
     * @param condicion string para error
     */
    
    public ViviendaDTO(ViviendaEntity entity, String condicion)
    {
        if (entity!=null){
            this.idVivienda=entity.getIdVivienda();
            this.ciudad=entity.getCiudad();
            this.descripcion=entity.getDescripcion();
            this.direccion=entity.getDireccion();
            this.capacidad=entity.getCapacidad();
            this.valorDiario=entity.getValorDiario();
            this.imagen=entity.getImagen();
        }
    }
    
    /**
     * Convierte un objeto ViviendaDTO a ViviendaEntity.
     * Metodo to entuty
     * @return Nueva objeto ViviendaEntity.
     * @generated
     */
    public ViviendaEntity toEntity() {
        ViviendaEntity entity = new ViviendaEntity();
        entity.setIdVivienda(this.getIdVivienda());
        entity.setCiudad(this.getCiudad());
        entity.setDescripcion(this.getDescripcion());
        entity.setDireccion(this.getDireccion());
        entity.setCapacidad(this.getCapacidad());
        
        entity.setValorDiario(this.getValorDiario());
        entity.setImagen(this.getImagen());
        entity.setNumeroHabitaciones (this.getNumeroHabitaciones());
        return entity;
    }
    
    /**
     * Retorna el id
     * @return idvivienda
     */
    public Long getIdVivienda(){
        return idVivienda;
    }
    
    /**
     * Cambia el id
     * @param idVivienda nuevo id
     */
    public void setIdVivienda(Long idVivienda){
        this.idVivienda = idVivienda;
    }
    
    /**
     * Retorna la descripcion
     * @return descripcion
     */
    public String getDescripcion(){
        return descripcion;
    }
    
    /**
     * Cambia la descripcion
     * @param descripcion nueva
     */
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    /**
     * Retorna la ciudad
     * @return ciudad
     */
    public String getCiudad(){
        return ciudad;
    }
    
    /**
     * Cambia la ciudad
     * @param ciudad nueva
     */
    public void setCiudad(String ciudad){
        this.ciudad = ciudad;
    }
    
    /**
     * Retorna la direccion
     * @return direccion
     */
    public String getDireccion(){
        return direccion;
    }
    
    /**
     * Cambia la direccion
     * @param direccion nueva
     */
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    /**
     * Retorna el valor
     * @return valor
     */
    public Double getValorDiario(){
        return valorDiario;
    }
    
    /**
     * Cambia el valor
     * @param valorDiario nuevo
     */
    public void setValorDiario(Double valorDiario){
        this.valorDiario = valorDiario;
    }
    
    /**
     * Retorna la capacidad
     * @return capacidad
     */
    public int getCapacidad(){
        return capacidad;
    }
    
    /**
     * Cambia la capacidad
     * @param capacidad nueva
     */
    public void setCapacidad(int capacidad){
        this.capacidad = capacidad;
    }
    
    /**
     * Retorna la imagen
     * @return imagen
     */
    public String getImagen(){
        return imagen;
    }
    
    /**
     * Cambia la imagen
     * @param imagen nueva
     */
    public void setImagen(String imagen){
        this.imagen = imagen;
    }
    
    /**
     * Retorna el anfitrion
     * @return anfitrion
     */
    public AnfitrionBasicDTO getAnfitrion(){
        return anfitrion;
    }
    
    /**
     * Cambia el anfitrion
     * @param anfitrion nuevo
     */
    public void setAnfitrion(AnfitrionBasicDTO anfitrion){
        this.anfitrion = anfitrion;
    }
    
    public int getNumeroHabitaciones(){
        return numeroHabitaciones;
    }
    
    public void setNumeroHabitaciones(int numeroHabitaciones){
        this.numeroHabitaciones = numeroHabitaciones;
    }   
}
