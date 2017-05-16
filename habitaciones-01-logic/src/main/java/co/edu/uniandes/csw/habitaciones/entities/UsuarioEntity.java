/* 
 * Copyright (C) 2017 c.penaloza.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package co.edu.uniandes.csw.habitaciones.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

/**
 *
 * @author ca.montenegro
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "DTYPE")
public class UsuarioEntity implements Serializable {

    /**
     * Constructor por defecto
     */
    public UsuarioEntity() {

    }

    private static final long serialVersionUID = 1L;

    /**
     * Atributo id
     */
    @Id
    private Long numeroID;

    /**
     * Atributo tipo id
     */
    private String tipoID;
    /**
     * Atriburo nombre
     */
    private String nombre;
    /**
     * Atributo del usuario
     */
    private String usuario;
    /**
     * Atributo de la contraseña del usuario
     */
    private String contrasenha;
    /**
     * Atributo del correo del usuario
     */
    private String correo;
    /**
     * Atributo de la direccion del usuario
     */
    private String direccion;

    /**
     * Telefono del usuario
     */
    private Long telefono;

    /**
     * Numero de la tarjeta del usuario
     */
    private Long numeroTarjeta;

    /**
     * String que representa la ruta de la imagen
     */
    private String image;

    /**
     * Lista de reservas de un usuario
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<ReservaEntity> reservas;

    /**
     * Getter ID
     *
     * @return Long id
     */
    public Long getNumeroID() {
        return numeroID;
    }

    /**
     * Setter ID
     *
     * @param numeroID Long id
     */
    public void setNumeroID(Long numeroID) {
        this.numeroID = numeroID;
    }

    /**
     * Getter TIPO id
     *
     * @return String tipo ID
     */
    public String getTipoID() {
        return tipoID;
    }

    /**
     * Setter tipo ID
     *
     * @param tipoID
     */
    public void setTipoID(String tipoID) {
        this.tipoID = tipoID;
    }

    /**
     * Getter nombre
     *
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter nombre
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter String usuario
     *
     * @return String usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Setter usuario
     *
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Getter contraseña
     *
     * @return String contraseña
     */
    public String getContrasenha() {
        return contrasenha;
    }

    /**
     * Setter contraseña
     *
     * @param contrasenha
     */
    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    /**
     * Getter correo
     *
     * @return String correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Setter correo
     *
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Getter direccion
     *
     * @return String direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Setter direccion
     *
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Getter telefono
     *
     * @return Long telefono
     */
    public Long getTelefono() {
        return telefono;
    }

    /**
     * Setter telefeno
     *
     * @param telefono
     */
    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    /**
     * Getter numero de tarjeta
     *
     * @return Long nunero tarjeta
     */
    public Long getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * Setter numero de tarjeta
     *
     * @param numeroTarjeta
     */
    public void setNumeroTarjeta(Long numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    /**
     * Getter imagen
     *
     * @return String ruta imagen
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter imagen
     *
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        if (this.getNumeroID() != null) {
            return this.getNumeroID().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Boolean retu = false;
        if (this == obj) {
            retu = false;
        }
        if (obj == null) {
            retu = false;
        }
        if (getClass() != obj.getClass()) {
            retu = false;
        }
        final UsuarioEntity other = (UsuarioEntity) obj;
        if (!Objects.equals(this.numeroID, other.numeroID)) {
            retu = false;
        } else {
            retu = true;
        }

        return retu;
    }

}
