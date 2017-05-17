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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamDoubleValue;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;

/**
 *
 * @author l.maya10
 */
@Entity
public class HabitacionEntity implements Serializable {
    
    public HabitacionEntity(){
        
    }
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @PodamExclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @PodamIntValue(minValue = 0)
    private Integer capacidad;
    
    @PodamIntValue(minValue = 0)
    private Double area;
    
    @PodamDoubleValue(minValue = 0)
    private Double valorDiario;
    
    private String descripcion;
    private String imagen;
    
    @ManyToOne 
    @PodamExclude
    private ViviendaEntity vivienda;
    @OneToMany(mappedBy="habitacion", cascade = CascadeType.PERSIST)
    @PodamExclude
    private List<ReservaEntity> reservas;

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List reservas) {
        this.reservas = reservas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getValorDiario() {
        return valorDiario;
    }

    public void setValorDiario(Double valorDiario) {
        this.valorDiario = valorDiario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public ViviendaEntity getVivienda()
    {
        return vivienda;
    }
    
    public void setVivienda(ViviendaEntity idVivienda)
    {
        this.vivienda = idVivienda;
    }
}
