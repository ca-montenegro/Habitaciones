package co.edu.uniandes.csw.habitaciones.dtos;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
@XmlRootElement
public class ViviendaDTO  implements Serializable{
    
    static final long serialVersionUID = 1L;
    
    private Long idVivienda;
    private String descripcion;
    private String ciudad;
    private String direccion;
    private Double valorDiario;
    private int capacidad;
    private String imagen;
    private AnfitrionBasicDTO anfitrion;
    
    
    /**
     * @generated
     */
    public ViviendaDTO() {
        
    }
    
    /**
     * Crea un objeto ViviendaDTO a partir de un objeto ViviendaEntity.
     *
     * @param entity Entidad ViviendaEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public ViviendaDTO(ViviendaEntity entity) {
        if (entity!=null){
            this.idVivienda=entity.getIdVivienda();
            this.anfitrion=new AnfitrionBasicDTO(entity.getAnfitrion());
            this.ciudad=entity.getCiudad();
            this.descripcion=entity.getDescripcion();
            this.direccion=entity.getDireccion();
            this.capacidad=entity.getCapacidad();
            this.valorDiario=entity.getValorDiario();
            this.imagen=entity.getImagen();
        }
    }
    /**
     * Constructor necesario para poder evitar ciclos infinitos al momento que ReservaDTO llama al constructor normal
     * @param entity
     * @param condicion
     */
    
    public ViviendaDTO(ViviendaEntity entity, String condicion)
    {
        if (entity!=null){
            this.idVivienda=entity.getIdVivienda();
            this.ciudad=entity.getCiudad();
            this.descripcion=entity.getDescripcion();
            this.direccion=entity.getDireccion();
            this.capacidad=entity.getCapacidad();
            this.valorDiario=entity.getValorDiario();
            this.imagen=entity.getImagen();
        }
    }
    
    /**
     * Convierte un objeto ViviendaDTO a ViviendaEntity.
     *
     * @return Nueva objeto ViviendaEntity.
     * @generated
     */
    public ViviendaEntity toEntity() {
        ViviendaEntity entity = new ViviendaEntity();
        entity.setIdVivienda(this.getIdVivienda());
        entity.setCiudad(this.getCiudad());
        entity.setDescripcion(this.getDescripcion());
        entity.setDireccion(this.getDireccion());
        entity.setCapacidad(this.getCapacidad());
        entity.setReservas(new ArrayList());
        
        AnfitrionEntity anfitrion = new AnfitrionEntity();
        anfitrion.setCorreo(this.getAnfitrion().getCorreo());
        anfitrion.setNombre(this.getAnfitrion().getNombre());
        anfitrion.setDireccion(this.getAnfitrion().getDireccion());
        anfitrion.setPuntuacion(this.getAnfitrion().getPuntuacion());
        anfitrion.setTelefono(this.getAnfitrion().getTelefono());
        
        entity.setAnfitrion(anfitrion);
        entity.setValorDiario(this.getValorDiario());
        entity.setImagen(this.getImagen());
        return entity;
    }
    
    public Long getIdVivienda() {
        return idVivienda;
    }
    
    public void setIdVivienda(Long idVivienda) {
        this.idVivienda = idVivienda;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getCiudad() {
        return ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public Double getValorDiario() {
        return valorDiario;
    }
    
    public void setValorDiario(Double valorDiario) {
        this.valorDiario = valorDiario;
    }
    
    public int getCapacidad() {
        return capacidad;
    }
    
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    public String getImagen() {
        return imagen;
    }
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public AnfitrionBasicDTO getAnfitrion() {
        return anfitrion;
    }

    public void setAnfitrion(AnfitrionBasicDTO anfitrion) {
        this.anfitrion = anfitrion;
    }
    
    
}
