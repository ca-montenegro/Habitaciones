// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.ejbs;

import co.edu.uniandes.csw.habitaciones.entities.UsuarioEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ca.montenegro
 */
@Stateless
public class UsuarioLogic {
    
    @Inject
    private UsuarioPersistence persistence;
    
    public List<UsuarioEntity> getUsuarios()
    {
        return persistence.findAll();
    }
    
    public UsuarioEntity getUsuario(Long id)
    {
        return persistence.find(id);
    }
    
    public UsuarioEntity createUsuario(UsuarioEntity entity) throws BusinessLogicException
    {
        List<UsuarioEntity> list = getUsuarios();
        for(UsuarioEntity user: list ){
            if(user.getUsuario().equals(entity.getUsuario()))
                throw new BusinessLogicException("Un usuario con ese Nombre de usuario ya existe");
            if(user.getCorreo().equals(entity.getCorreo()))
                throw new BusinessLogicException("El correo ya existe para un usuario");
            if(user.getNumeroTarjeta()==entity.getNumeroTarjeta())
                throw new BusinessLogicException("Existe un usuario con el número de tarjeta ingresado");
        }
        return persistence.create(entity);
    }
    
    // TODO: revisar las validaciones en caso de actualización
    public UsuarioEntity updateUsuario(UsuarioEntity entity)
    {
        return persistence.update(entity);
    }
}
