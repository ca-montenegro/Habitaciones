package co.edu.uniandes.csw.habitaciones.entities;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author ca.montenegro
 */
@Entity
@DiscriminatorValue("c")
public class AnfitrionEntity extends UsuarioEntity{
    
    
    static final long serialVersionUID = 1L;
    
    private Double puntuacion;
    
    @OneToMany(mappedBy = "anfitrion")
    private List<ViviendaEntity> viviendas;
    
    public AnfitrionEntity()
    {
        super();
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public List<ViviendaEntity> getViviendas() {
        return viviendas;
    }

    public void setViviendas(List<ViviendaEntity> viviendas) {
        this.viviendas = viviendas;
    }
    
    
    
}
