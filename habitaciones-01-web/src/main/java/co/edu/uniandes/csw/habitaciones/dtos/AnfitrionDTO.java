/**
 *DTO Anfitrion
 * @author jd.cardenas10
 */

package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AnfitrionDTO extends UsuarioDTO{
    static final long serialVersionUID = 1L;
    
    private Double puntuacion;

    public AnfitrionDTO(){
        super();
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
        entity.setReservas(null);
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
