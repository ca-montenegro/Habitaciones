package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @generated
 */
@XmlRootElement
public class ViviendaDetailDTO extends ViviendaDTO{
    
    static final long serialVersionUID = 1L;
    
    private List<ReservaDTO> reservas;
    private List<HabitacionDTO> habitaciones;
    
    public List<HabitacionDTO> getHabitaciones() {
        return habitaciones;
    }
    
    public void setHabitaciones(List<HabitacionDTO> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
    
    
    
    public ViviendaDetailDTO(){
        super();
        ViviendaEntity entity = super.toEntity();
        if(entity.getHabitaciones()!=null){
            this.habitaciones=new ArrayList();
            for(HabitacionEntity habitacion:entity.getHabitaciones())
            {
                this.habitaciones.add(new HabitacionDTO(habitacion));
            }
        }
        
        if(entity.getReservas()!=null){
            this.reservas=new ArrayList();
            for(ReservaEntity reserva:entity.getReservas())
            {
                this.reservas.add(new ReservaDTO(reserva));
            }
        }
    }
    
    /**
     * Crea un objeto ViviendaDetailDTO a partir de un objeto ViviendaEntity incluyendo los atributos de ViviendaDTO.
     *
     * @param entity Entidad ViviendaEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ViviendaDetailDTO(ViviendaEntity entity) {
        super(entity);
        if(entity!=null){
            if(entity.getHabitaciones()!=null){
                this.habitaciones=new ArrayList();
                for(HabitacionEntity vivienda:entity.getHabitaciones())
                {
                    this.habitaciones.add(new HabitacionDTO(vivienda));
                }
            }
        }
    }
    
    /**
     * Convierte un objeto ViviendaDetailDTO a ViviendaEntity incluyendo los atributos de ViviendaDTO.
     *
     * @return Nueva objeto ViviendaEntity.
     * @generated
     */
    @Override
    public ViviendaEntity toEntity() {
        ViviendaEntity entity = new ViviendaEntity();
        entity.setIdVivienda(this.getIdVivienda());
        entity.setCiudad(this.getCiudad());
        entity.setDescripcion(this.getDescripcion());
        entity.setDireccion(this.getDireccion());
        entity.setCapacidad(this.getCapacidad());
        
        entity.setValorDiario(this.getValorDiario());
        entity.setImagen(this.getImagen());
        entity.setHabitaciones(this.getHabitaciones());
        
        if(entity.getHabitaciones()!=null){
            this.habitaciones=new ArrayList();
            for(HabitacionEntity habitacion:entity.getHabitaciones())
            {
                this.habitaciones.add(new HabitacionDTO(habitacion));
            }
        }
        
        if(entity.getReservas()!=null){
            this.reservas=new ArrayList();
            for(ReservaEntity reserva:entity.getReservas())
            {
                this.reservas.add(new ReservaDTO(reserva));
            }
        }
        return entity;
    }
    
}
