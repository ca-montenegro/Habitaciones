/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ca.montenegro
 */
@Entity
public class UsuarioEntity {
    
    @Id
    private Long numeroID;
    
    private String tipoID;
    
    private String nombre;
    
    private String usuario;
    
    private String contrasenha;
    
    private String correo;
    
    private String direccion;
    
    private Long telefono;
    
    private Long numeroTarjeta;
    
    private List<ReservaEntity> reservas;
    
    public UsuarioEntity()
    {
        
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

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }
    
    
    
    @Override
    public int hashCode() {
        if (this.getNumeroID()!= null) {
            return this.getNumeroID().hashCode();
        }
        return super.hashCode();
    }
    
    
}
