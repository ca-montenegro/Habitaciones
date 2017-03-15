/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jd.cardenas10
 */
@XmlRootElement
public class AnfitrionDetailDTO extends UsuarioDetailDTO{

    private Double puntuacion;
    
    private List<ViviendaDetailDTO> viviendas;
    
    public AnfitrionDetailDTO(){
        
    }
    
    public AnfitrionDetailDTO(AnfitrionEntity entity){
        super(entity);
        if(entity!=null){
        this.puntuacion=entity.getPuntuacion();    
        if(entity.getViviendas()!=null){
            this.viviendas=new ArrayList();
            for(ViviendaEntity vivienda:entity.getViviendas())
            {   
                this.viviendas.add(new ViviendaDetailDTO(vivienda));
            }
        }
        }
    }
    
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
        
        if(this.getReservas()!=null){
            entity.setReservas(new ArrayList());
            List<ReservaEntity> entities0=entity.getReservas();
            for(ReservaDTO reserva:this.getReservas())
            {
                entities0.add(reserva.toEntity());
            }
        }
        entity.setPuntuacion(this.getPuntuacion());
        
        if(this.getViviendas()!=null){    
            entity.setViviendas(new ArrayList());
            List<ViviendaEntity> entities=entity.getViviendas();
            for(ViviendaDetailDTO vivienda:this.getViviendas())
            {
                entities.add(vivienda.toEntity());
            }
        }
        return entity;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public List<ViviendaDetailDTO> getViviendas() {
        return viviendas;
    }

    public void setViviendas(List<ViviendaDetailDTO> viviendas) {
        this.viviendas = viviendas;
    }
}
