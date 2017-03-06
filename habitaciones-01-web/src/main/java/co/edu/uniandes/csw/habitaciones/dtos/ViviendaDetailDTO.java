package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @generated
 */
@XmlRootElement
public class ViviendaDetailDTO extends ViviendaDTO{

private List habitaciones;

    public List getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List habitaciones) {
        this.habitaciones = habitaciones;
    }

    /**
     * Crea un objeto ViviendaDetailDTO a partir de un objeto ViviendaEntity incluyendo los atributos de ViviendaDTO.
     *
     * @param entity Entidad ViviendaEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ViviendaDetailDTO(ViviendaEntity entity) {
        super(entity);
        this.habitaciones = entity.getHabitaciones();
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
        entity.setReservas(this.getReservas());
        entity.setValorDiario(this.getValorDiario());
        entity.setImagen(this.getImagen());
        entity.setHabitaciones(this.getHabitaciones());
    return entity;
    }

}
