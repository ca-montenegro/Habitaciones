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
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.UsuarioEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.springframework.util.Assert;

/**
 *
 * @author ca.montenegro
 */
@Stateless
public class UsuarioLogic {
    
   
    
    private static final long serialVersionUID = 1L;
    
    
    
    private final UsuarioPersistence persistence;
    
    public UsuarioLogic(){
        persistence = new UsuarioPersistence();
    }
        
    @Inject
    public UsuarioLogic (UsuarioPersistence persistence)
    {
        Assert.notNull(persistence, "My persistence must be not null");
        this.persistence = persistence;
    }
    

    
    
    public List<UsuarioEntity> getUsuarios()
    {
        return persistence.findAll();
    }
    
    public UsuarioEntity getUsuario(Long id) throws BusinessLogicException
    {
        UsuarioEntity user =  persistence.find(id);
        if(user==null){
            throw new BusinessLogicException("El usuario no existe");
        }
        return user;
    }
    
    public UsuarioEntity createUsuario(UsuarioEntity entity) throws BusinessLogicException
    {
        List<UsuarioEntity> list = getUsuarios();
        for(UsuarioEntity user: list ){
            if(user.getUsuario().equals(entity.getUsuario())){
                throw new BusinessLogicException("Un usuario con ese Nombre de usuario ya existe");
            }
            if(user.getCorreo().equals(entity.getCorreo()))
            {throw new BusinessLogicException("El correo ya existe para un usuario");}
            if(user.getNumeroTarjeta().equals(entity.getNumeroTarjeta()))
            {throw new BusinessLogicException("Existe un usuario con el número de tarjeta ingresado");}
        }
        return persistence.create(entity);
    }
    
    
    public UsuarioEntity updateUsuario(UsuarioEntity entity) throws BusinessLogicException
    {
        List<UsuarioEntity> list = getUsuarios();
        for(UsuarioEntity user: list ){
            if(user.getUsuario().equals(entity.getUsuario()))
            {throw new BusinessLogicException("Un usuario con ese Nombre de usuario ya existe");}
            if(user.getCorreo().equals(entity.getCorreo()))
            {throw new BusinessLogicException("El correo ya existe para un usuario");}
            if(user.getNumeroTarjeta().equals(entity.getNumeroTarjeta()))
            {throw new BusinessLogicException("Existe un usuario con el número de tarjeta ingresado");}
        }
        return persistence.update(entity);
    }
}
