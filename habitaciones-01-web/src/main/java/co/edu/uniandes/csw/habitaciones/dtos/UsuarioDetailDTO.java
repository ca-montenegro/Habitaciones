/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ca.montenegro
 */
@XmlRootElement
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable {
    private List<ReservaEntity> reservas;
    
    @Override
    public List getReservas()
    {
        return reservas;
    }
    
    @Override
    public void setReservas(List reservas)
    {
        this.reservas = reservas;
    }
    
    public UsuarioDetailDTO(UsuarioEntity entity)
    {
        super(entity);
        this.reservas = entity.getReservas();
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
        entity.setReservas(this.getReservas());
        return entity;
    }
}
