/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ca.montenegro
 */
@XmlRootElement
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable {
    private List<ReservaDTO> reservas;
    
    public UsuarioDetailDTO(){
        super();
    }
    
    public UsuarioDetailDTO(UsuarioEntity entity)
    {
        super(entity);
        if(entity!=null){ 
        if(entity.getReservas()!=null){
            this.reservas=new ArrayList();
            for(ReservaEntity reserva:entity.getReservas())
            {   
                this.reservas.add(new ReservaDTO(reserva));
            }
        }
        }
    }

    
    public UsuarioEntity toEntity()
    {
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
            for(ReservaDTO reserva:this.getReservas())
            {
                entities0.add(reserva.toEntity());
            }
        }
        return entity;
    }
    
    //@Override
    public List<ReservaDTO> getReservas()
    {
        return reservas;
    }
    
    //@Override
    public void setReservas(List reservas)
    {
        this.reservas = reservas;
    }
}
