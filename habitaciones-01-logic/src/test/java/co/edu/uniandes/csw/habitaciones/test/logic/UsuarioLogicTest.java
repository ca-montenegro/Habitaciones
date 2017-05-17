/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.logic;

import co.edu.uniandes.csw.habitaciones.ejbs.UsuarioLogic;
import co.edu.uniandes.csw.habitaciones.entities.UsuarioEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author CamiloMontenegro
 */
@RunWith(Arquillian.class)
public class UsuarioLogicTest {
 
     public static final String DEPLOY = "Prueba1";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");

    }

    /**
     * @generated
     */
    @Inject
    private UsuarioLogic usuarioLogic;

    /**
     * @generated
     */
    @PersistenceContext(unitName = "habitacionesPU")
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Book.
     *
     * @throws co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException
     * @generated
     */
    @Test
    public void testCreateUsuario() throws BusinessLogicException {
        
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity result = usuarioLogic.createUsuario(entity);
        Assert.assertNotNull("No debe ser nulo",result);
        Assert.assertEquals("Debe coincidir: ",result.getNombre(), entity.getNombre());
        Assert.assertEquals("Debe coincidir: ",result.getTipoID(), entity.getTipoID());
        Assert.assertEquals("Debe coincidir: ",result.getUsuario(), entity.getUsuario());
        Assert.assertEquals("Debe coincidir: ",result.getImage(), entity.getImage());
        Assert.assertEquals("Debe coincidir: ",result.getContrasenha(), entity.getContrasenha());
        Assert.assertEquals("Debe coincidir: ",result.getCorreo(), entity.getCorreo());
        Assert.assertEquals("Debe coincidir: ",result.getDireccion(), entity.getDireccion());
        Assert.assertEquals("Debe coincidir: ",result.getTelefono(), entity.getTelefono());
        Assert.assertEquals("Debe coincidir: ",result.getNumeroTarjeta(), entity.getNumeroTarjeta());
    }

    /**
     * Prueba para consultar la lista de Books.
     *
     * @generated
     */
    @Test
    public void testGetUsuarios() {
        List<UsuarioEntity> list = usuarioLogic.getUsuarios();
        Assert.assertEquals("Debe coincidir: ",data.size(), list.size());
        for (UsuarioEntity entity : list) {
            boolean found = false;
            for (UsuarioEntity storedEntity : data) {
                if (entity.getNumeroID().equals(storedEntity.getNumeroID())) {
                    found = true;
                }
            }
            Assert.assertTrue("debe ser true: ",found);
        }
    }

    /**
     * Prueba para consultar un Book.
     *
     * @generated
     */
    @Test
    public void getUsuarioTest() throws BusinessLogicException {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity resultEntity = usuarioLogic.getUsuario(entity.getNumeroID());
        Assert.assertNotNull("No debe ser null, ",resultEntity);
        Assert.assertEquals("Debe coincidir: ",entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals("Debe coincidir: ",entity.getTipoID(), resultEntity.getTipoID());
        Assert.assertEquals("Debe coincidir: ",entity.getUsuario(), resultEntity.getUsuario());
        Assert.assertEquals("Debe coincidir: ",entity.getImage(), resultEntity.getImage());
        Assert.assertEquals("Debe coincidir: ",entity.getContrasenha(), resultEntity.getContrasenha());
        Assert.assertEquals("Debe coincidir: ",entity.getCorreo(), resultEntity.getCorreo());
        Assert.assertEquals("Debe coincidir: ",entity.getDireccion(), resultEntity.getDireccion());
        Assert.assertEquals("Debe coincidir: ",entity.getTelefono(), resultEntity.getTelefono());
        Assert.assertEquals("Debe coincidir: ",entity.getNumeroTarjeta(), resultEntity.getNumeroTarjeta());
    }


    /**
     * Prueba para actualizar un Book.
     *
     * @generated
     */
    @Test
    public void testUpdateUsuario() throws BusinessLogicException {
        UsuarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity pojoEntity = factory.manufacturePojo(UsuarioEntity.class);
        pojoEntity.setNumeroID(entity.getNumeroID());

        usuarioLogic.updateUsuario(pojoEntity);

        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getNumeroID());
        Assert.assertEquals("Debe coincidir: ",pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals("Debe coincidir: ",pojoEntity.getTipoID(), resp.getTipoID());
        Assert.assertEquals("Debe coincidir: ",pojoEntity.getUsuario(), resp.getUsuario());
        Assert.assertEquals("Debe coincidir: ",pojoEntity.getImage(), resp.getImage());
        Assert.assertEquals("Debe coincidir: ",pojoEntity.getContrasenha(), resp.getContrasenha());
        Assert.assertEquals("Debe coincidir: ",pojoEntity.getCorreo(), resp.getCorreo());
        Assert.assertEquals("Debe coincidir: ",pojoEntity.getDireccion(), resp.getDireccion());
        Assert.assertEquals("Debe coincidir: ",pojoEntity.getTelefono(), resp.getTelefono());
        Assert.assertEquals("Debe coincidir: ",pojoEntity.getNumeroTarjeta(), resp.getNumeroTarjeta());
    }
}
