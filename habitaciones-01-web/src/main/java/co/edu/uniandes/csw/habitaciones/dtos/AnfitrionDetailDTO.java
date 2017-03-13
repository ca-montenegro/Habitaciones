/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.dtos;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jd.cardenas10
 */
public class AnfitrionDetailDTO {
    /**
 *
 * @author jd.cardenas10
 */
@XmlRootElement
public class AnfitrionDTO extends UsuarioDTO{
    private Double puntuacion;
    
    private List<ViviendaDTO> viviendas;

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public List<ViviendaDTO> getViviendas() {
        return viviendas;
    }

    public void setViviendas(List<ViviendaDTO> viviendas) {
        this.viviendas = viviendas;
    }
}
}
