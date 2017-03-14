/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jd.cardenas10
 */
@XmlRootElement
public class AnfitrionDTO extends UsuarioDTO{
    private Double puntuacion;

    public AnfitrionDTO(){
        
    }
    
    public AnfitrionDTO(AnfitrionEntity entity){
        super(entity);
        if(entity!=null){
        this.puntuacion=entity.getPuntuacion();
        }
    }
    
    @Override
    public AnfitrionEntity toEntity(){
        AnfitrionEntity entity=new AnfitrionEntity();
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
        entity.setPuntuacion(this.getPuntuacion());
        return entity;
    }
    
    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    
}
