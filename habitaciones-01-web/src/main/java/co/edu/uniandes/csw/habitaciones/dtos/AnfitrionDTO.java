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

    public AnfitrionDTO(AnfitrionEntity entity){
        super(entity);
        if(entity!=null){
        this.puntuacion=entity.getPuntuacion();
        }
    }
    
    public AnfitrionEntity toEntity(){
        AnfitrionEntity entity=new AnfitrionEntity();
        //
        return entity;
    }
    
    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    
}
