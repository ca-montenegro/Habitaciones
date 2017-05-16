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
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class HabitacionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Integer capacidad;
    private Double area;
    private Double valorDiario;
    private String descripcion;
    private String imagen;
    
    public HabitacionDTO()
    {
        
    }
    
    public HabitacionDTO(HabitacionEntity entity)
    {
        if(entity!=null)
        {
            this.id = entity.getId();
            this.area = entity.getArea();
            this.capacidad = entity.getCapacidad();
            this.descripcion = entity.getDescripcion();
            this.imagen = entity.getImagen();
            this.valorDiario = entity.getValorDiario();
        }
    }
    
    public HabitacionEntity toEntity()
    {
        HabitacionEntity entity = new HabitacionEntity();
        entity.setId(this.getId());
        entity.setArea(this.getArea());
        entity.setCapacidad(this.getCapacidad());
        entity.setDescripcion(this.getDescripcion());
        entity.setValorDiario(this.getValorDiario());
        entity.setImagen(this.getImagen());
        return entity;
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
}
