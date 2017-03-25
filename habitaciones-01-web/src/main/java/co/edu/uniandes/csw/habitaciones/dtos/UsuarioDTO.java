package co.edu.uniandes.csw.habitaciones.dtos;

//import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.entities.UsuarioEntity;
import java.io.Serializable;
//import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ca.montenegro
 */

@XmlRootElement
public class UsuarioDTO implements Serializable {
    
     private Long numeroID;
    
    private String tipoID;
    
    private String nombre;
    
    private String usuario;
    
    private String contrasenha;
    
    private String correo;
    
    private String direccion;
    
    private Long telefono;
    
    private Long numeroTarjeta;
    
    //private List<ReservaEntity> reservas;
    
    public UsuarioDTO()
    {
        
    }
    
    public UsuarioDTO(UsuarioEntity entity)
    {
        if(entity!=null)
        {
            this.numeroID = entity.getNumeroID();
            this.tipoID=entity.getTipoID();
            this.nombre = entity.getNombre();
            this.usuario = entity.getUsuario();
            this.contrasenha = entity.getContrasenha();
            this.correo = entity.getCorreo();
            this.direccion = entity.getDireccion();
            this.telefono = entity.getTelefono();
            this.numeroTarjeta = entity.getNumeroTarjeta();
            //this.reservas = entity.getReservas();
        }    
    }
    
     public UsuarioEntity toEntity() {
        UsuarioEntity entity = new UsuarioEntity();
        
        entity.setNumeroID(this.numeroID);
        entity.setTipoID(this.getTipoID());
        entity.setNombre(this.nombre);
        entity.setUsuario(this.usuario);
        entity.setContrasenha(this.contrasenha);
        entity.setCorreo(this.correo);
        entity.setDireccion(this.direccion);
        entity.setTelefono(this.telefono);
        entity.setNumeroTarjeta(this.numeroTarjeta);
        //entity.setReservas(this.reservas);
        
      return entity;  
    }

    public Long getNumeroID() {
        return numeroID;
    }

    public void setNumeroID(Long numeroID) {
        this.numeroID = numeroID;
    }

    public String getTipoID() {
        return tipoID;
    }

    public void setTipoID(String tipoID) {
        this.tipoID = tipoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    //public List<ReservaEntity> getReservas() {
    //    return reservas;
    //}

    //public void setReservas(List<ReservaEntity> reservas) {
      //  this.reservas = reservas;
    //}
    
    
}
