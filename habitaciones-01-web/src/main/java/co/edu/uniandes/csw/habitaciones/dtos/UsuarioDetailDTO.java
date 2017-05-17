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
/**
 * Clase usuarioDetailDTO
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *Clase
 * UsuarioDetailDTO
 * Super: UsuarioDTO
 * @author ca.montenegro
 */
@XmlRootElement
public class UsuarioDetailDTO extends UsuarioDTO {
    
    /**
     * serialVersionUID = 1L
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Lista 
     * de reservas
     */
    private List<ReservaDTO> reservas;
    
    /**
     * Constructor por defecto
     */
    public UsuarioDetailDTO(){
        super();
    }
    
    /**
     * Constructor que crea un usuarioEntity
     * @param entity 
     */
    public UsuarioDetailDTO(UsuarioEntity entity){
        super(entity);
        if(entity!=null){ 
        if(entity.getReservas()!=null){
            this.reservas=new ArrayList();
            for(ReservaEntity reserva:entity.getReservas()){   
                this.reservas.add(new ReservaDTO(reserva));
            }
        }
        }
    }

    /**
     * Metodo convierte 
     * a entity
     * @return 
     */
    @Override
    public UsuarioEntity toEntity(){
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNumeroID(this.getNumeroID());
        entity.setTipoID(this.getTipoID());
        entity.setNombre(this.getNombre());
        entity.setUsuario(this.getUsuario());
        entity.setContrasenha(this.getContrasenha());
        entity.setCorreo(this.getCorreo());
        entity.setDireccion(this.getDireccion());
        entity.setTelefono(this.getTelefono());
        entity.setNumeroTarjeta(this.getNumeroTarjeta());
        
        if(this.getReservas()!=null){         
            entity.setReservas(new ArrayList());
            List<ReservaEntity> entities0=entity.getReservas();
            for(ReservaDTO reserva:this.getReservas()){
            
                entities0.add(reserva.toEntity());
            }
        }
        return entity;
    }
    
    /**
     * Getter 
     * reservas
     * @return 
     */
    //@Override
    public List<ReservaDTO> getReservas(){
    
        List<ReservaDTO> retu = reservas;
        return retu;
    }
    
    /**
     * Setter
     * reservas
     * @param reservas Lista de reservas
     */
    //@Override
    public void setReservas(List reservas){
    
        List<ReservaDTO> retu = reservas;
        this.reservas = retu;
    }
}
