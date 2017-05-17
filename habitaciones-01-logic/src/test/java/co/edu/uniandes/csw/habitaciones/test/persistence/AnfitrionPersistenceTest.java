/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.persistence;

import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.persistence.AnfitrionPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author josedanielcardenasrincon
 */
@RunWith(Arquillian.class)
public class AnfitrionPersistenceTest {
    
    public static final String DEPLOY = "PruebaAnfitrionPersistence";

    
    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AnfitrionEntity.class.getPackage())
                .addPackage(AnfitrionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    /**
     * @generated
     */
    @Inject
    private AnfitrionPersistence persistence;

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
        em.createQuery("delete from UsuarioEntity where DTYPE='c' OR DTYPE='AnfitrionEntity'").executeUpdate();
    }
    
     /**
     * @generated
     */
    private List<AnfitrionEntity> data = new ArrayList<AnfitrionEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            AnfitrionEntity entity = factory.manufacturePojo(AnfitrionEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un Book.
     *
     * @generated
     */
    @Test
    public void createAnfitrionTest() {
        PodamFactory factory = new PodamFactoryImpl();
        AnfitrionEntity newEntity = factory.manufacturePojo(AnfitrionEntity.class);
        AnfitrionEntity result = persistence.create(newEntity);

        Assert.assertNotNull(result);

        AnfitrionEntity entity = em.find(AnfitrionEntity.class, result.getNumeroID());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getTipoID(), entity.getTipoID());
        Assert.assertEquals(newEntity.getUsuario(), entity.getUsuario());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
        Assert.assertEquals(newEntity.getContrasenha(), entity.getContrasenha());
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(newEntity.getDireccion(), entity.getDireccion());
        Assert.assertEquals(newEntity.getTelefono(), entity.getTelefono());
        Assert.assertEquals(newEntity.getNumeroTarjeta(), entity.getNumeroTarjeta());
        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion());
    }

    /**
     * Prueba para consultar la lista de Books.
     *
     * @generated
     */
    @Test
    public void getAnfitrionTest() {
        List<AnfitrionEntity> list = persistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (AnfitrionEntity ent : list) {
            boolean found = false;
            for (AnfitrionEntity entity : data) {
                if (ent.getNumeroID().equals(entity.getNumeroID())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Book.
     *
     * @generated
     */
    @Test
    public void getAnfitrionesTest() {
        AnfitrionEntity entity = data.get(0);
        AnfitrionEntity newEntity = persistence.find(entity.getNumeroID());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getTipoID(), entity.getTipoID());
        Assert.assertEquals(newEntity.getUsuario(), entity.getUsuario());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
        Assert.assertEquals(newEntity.getContrasenha(), entity.getContrasenha());
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(newEntity.getDireccion(), entity.getDireccion());
        Assert.assertEquals(newEntity.getTelefono(), entity.getTelefono());
        Assert.assertEquals(newEntity.getNumeroTarjeta(), entity.getNumeroTarjeta());
        Assert.assertEquals(newEntity.getPuntuacion(), entity.getPuntuacion());
    }

    /**
     * Prueba para actualizar un Book.
     *
     * @generated
     */
    @Test
        public void updateAnfitrionTest() {
        AnfitrionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AnfitrionEntity newEntity = factory.manufacturePojo(AnfitrionEntity.class);
        newEntity.setNumeroID(entity.getNumeroID());

        persistence.update(newEntity);

        AnfitrionEntity resp = em.find(AnfitrionEntity.class, entity.getNumeroID());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getTipoID(), resp.getTipoID());
        Assert.assertEquals(newEntity.getUsuario(), resp.getUsuario());
        Assert.assertEquals(newEntity.getImage(), resp.getImage());
        Assert.assertEquals(newEntity.getContrasenha(), resp.getContrasenha());
        Assert.assertEquals(newEntity.getCorreo(), resp.getCorreo());
        Assert.assertEquals(newEntity.getDireccion(), resp.getDireccion());
        Assert.assertEquals(newEntity.getTelefono(), resp.getTelefono());
        Assert.assertEquals(newEntity.getNumeroTarjeta(), resp.getNumeroTarjeta());
        Assert.assertEquals(newEntity.getPuntuacion(), resp.getPuntuacion());
        
    }
}
