/**
 *DTO Anfitrion
 * @author jd.cardenas10
 */

package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AnfitrionBasicDTO extends AnfitrionDTO{
    private static final long serialVersionUID = 1L;

    public AnfitrionBasicDTO()
    {
        
    }
    
    public AnfitrionBasicDTO(AnfitrionEntity entity){
        this.setNumeroID(entity.getNumeroID());
        this.setTipoID(entity.getTipoID());
        this.setNombre(entity.getNombre());
        this.setCorreo(entity.getCorreo());
        this.setDireccion(entity.getDireccion());
        this.setTelefono(entity.getTelefono());
        this.setPuntuacion(entity.getPuntuacion());
    }
    
    @Override
    public AnfitrionEntity toEntity(){
        AnfitrionEntity entity=new AnfitrionEntity();
        entity.setNumeroID(this.getNumeroID());
        entity.setTipoID(entity.getTipoID());
        entity.setNombre(this.getNombre());
        entity.setCorreo(this.getCorreo());
        entity.setDireccion(this.getDireccion());
        entity.setTelefono(this.getTelefono());
        entity.setPuntuacion(this.getPuntuacion());
        return entity;
    }
    
}
