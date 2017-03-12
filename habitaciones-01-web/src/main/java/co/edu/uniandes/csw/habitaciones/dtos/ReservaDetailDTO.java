package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservaDetailDTO extends ReservaDTO implements Serializable{
    
    public ReservaDetailDTO() {
        super();
    }
    
    public ReservaDetailDTO(ReservaEntity entity){
        super(entity);
    }
    
    @Override
    public ReservaEntity toEntity(){
        ReservaEntity entity = super.toEntity();
      return entity;  
    }
}
    

