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
package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class ViviendaEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /**
     * Id autogenerado de la vivienda
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVivienda;
    
    /**
     * Descripcion de la vivienda
     */
    private String descripcion;
    
    /**
     * Ciudad de la vivienda
     */
    private String ciudad;
    
    /**
     * Direccion de la vivienda
     */
    private String direccion;
    
    
    /**
     * Valor diario de la vivienda
     */
    private Double valorDiario;
    
    /**
     * Capacidad de la vivienda
     */
    private int capacidad;
    
    /**
     * Imagen de la vivienda
     */
    private String imagen;
    
    /**
     * Numero de habitaciones de la vivienda
     */
    private int numeroHabitaciones;
    
    /**
     * Lista de reservas de la vivienda
     */
    @OneToMany(mappedBy = "vivienda")
    private List<ReservaEntity> reservas;
    
    /**
     * Lista de habitaciones de la vivienda
     */
    @OneToMany(mappedBy="vivienda")
    private List<HabitacionEntity> habitaciones;
    
    /**
     * Anfitrion de la vivienda
     */
    @ManyToOne
    private AnfitrionEntity anfitrion;
    
    public ViviendaEntity()
    {
        
    }
    
    /**
     * Retorna el id
     * @return idVivienda
     */
    public Long getIdVivienda(){
        return idVivienda;
    }
    
    /**
     * Cambia el id de la vivienda
     * @param idVivienda
     */
    public void setIdVivienda(Long idVivienda){
        this.idVivienda = idVivienda;
    }
    
    /**
     * Retorna la descripcion
     * @return descripcio
     */
    public String getDescripcion(){
        return descripcion;
    }
    
    /**
     * Define la descripcion
     * @param descripcion
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
     * Define la ciudad
     * @param ciudad
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
     * Define la direccion
     * @param direccion
     */
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    /**
     * Retorna el valor diario de la vivienda
     * @return valorDiario
     */
    public Double getValorDiario(){
        return valorDiario;
    }
    
    /**
     * Define el valor diario de la vivienda
     * @param valorDiario
     */
    public void setValorDiario(Double valorDiario){
        this.valorDiario = valorDiario;
    }
    
    /**
     * Retorna la capacidadde la vivienda
     * @return capacidad
     */
    public int getCapacidad(){
        return capacidad;
    }
    
    /**
     * Define la capacidad de la vivienda
     * @param capacidad
     */
    public void setCapacidad(int capacidad){
        this.capacidad = capacidad;
    }
    
    /**
     * Retorna la ruta de la imagen de la vivienda
     * @return imagen
     */
    public String getImagen(){
        return imagen;
    }
    
    /**
     * Define la rita de la imagen
     * @param imagen
     */
    public void setImagen(String imagen){
        this.imagen = imagen;
    }
    
    /**
     * Retorna la lista de reservas
     * @return copy
     */
    public List<ReservaEntity> getReservas(){
        List<ReservaEntity> copy = reservas;
        return copy;
    }
    
    /**
     * Cambia la lista de reservas
     * @param reservas
     */
    public void setReservas(List reservas){
        this.reservas = reservas;
    }
    
    /**
     * Retorna la lista de habitaciones
     * @return copy
     */
    public List<HabitacionEntity> getHabitaciones(){
        List<HabitacionEntity> copy = habitaciones;
        return copy;
    }
    
    /**
     * Cambia la lista de habitaciones
     * @param habitaciones
     */
    public void setHabitaciones(List habitaciones){
        this.habitaciones = habitaciones;
    }
    
    /**
     * Retorna el anfitrion
     * @return anfitrion
     */
    public AnfitrionEntity getAnfitrion(){
        return anfitrion;
    }
    
    public void setAnfitrion(AnfitrionEntity anfitrion){
        this.anfitrion = anfitrion;
    }
    
    public int getNumeroHabitaciones(){
        return numeroHabitaciones;
    }
    
    public void setNumeroHabitaciones(int numeroHabitaciones){
        this.numeroHabitaciones=numeroHabitaciones;
    }
    
    @Override
    public int hashCode() 
    {
        if (this.getIdVivienda()!= null)
        {
            return this.getIdVivienda().hashCode();
        }
        return super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final ViviendaEntity other = (ViviendaEntity) obj;
        if (!Objects.equals(this.idVivienda, other.idVivienda))
        {
            return false;
        }
        return true;
    }
}
