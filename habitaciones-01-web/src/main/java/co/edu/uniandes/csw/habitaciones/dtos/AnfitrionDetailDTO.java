package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jd.cardenas10
 */
@XmlRootElement
public class AnfitrionDetailDTO extends AnfitrionDTO{
    
    private List<ViviendaDetailDTO> viviendas;
    
    public AnfitrionDetailDTO(){
        super();
    }
    
    public AnfitrionDetailDTO(AnfitrionEntity entity){
        super(entity);
        if(entity!=null){  
        if(entity.getViviendas()!=null){
            this.viviendas=new ArrayList();
            for(ViviendaEntity vivienda:entity.getViviendas())
            {   
                this.viviendas.add(new ViviendaDetailDTO(vivienda));
            }
        }
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

    public List<ViviendaDetailDTO> getViviendas() {
        return viviendas;
    }

    public void setViviendas(List<ViviendaDetailDTO> viviendas) {
        this.viviendas = viviendas;
    }
}
