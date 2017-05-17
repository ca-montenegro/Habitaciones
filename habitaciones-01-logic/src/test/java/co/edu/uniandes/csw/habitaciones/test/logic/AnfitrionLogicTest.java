/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.logic;


import co.edu.uniandes.csw.habitaciones.ejbs.AnfitrionLogic;
import co.edu.uniandes.csw.habitaciones.entities.AnfitrionEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
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
import org.jboss.shrinkwrap.api.spec.WebArchive;
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
public class AnfitrionLogicTest {
    
    public static final String DEPLOY = "PruebaAnfitrionLogic";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(AnfitrionEntity.class.getPackage())
                .addPackage(AnfitrionLogic.class.getPackage())
                .addPackage(AnfitrionPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");

    }
    
    /**
     * @generated
     */
    @Inject
    private AnfitrionLogic anfitrionLogic;

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
     * @throws co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException
     * @generated
     */
    @Test
    public void createAnfitrionTest() throws BusinessLogicException {
        
        PodamFactory factory = new PodamFactoryImpl();
        AnfitrionEntity entity = factory.manufacturePojo(AnfitrionEntity.class);
        AnfitrionEntity result = anfitrionLogic.createAnfitrion(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getNombre(), entity.getNombre());
        Assert.assertEquals(result.getTipoID(), entity.getTipoID());
        Assert.assertEquals(result.getUsuario(), entity.getUsuario());
        Assert.assertEquals(result.getImage(), entity.getImage());
        Assert.assertEquals(result.getContrasenha(), entity.getContrasenha());
        Assert.assertEquals(result.getCorreo(), entity.getCorreo());
        Assert.assertEquals(result.getDireccion(), entity.getDireccion());
        Assert.assertEquals(result.getTelefono(), entity.getTelefono());
        Assert.assertEquals(result.getNumeroTarjeta(), entity.getNumeroTarjeta());
        Assert.assertEquals(result.getPuntuacion(), entity.getPuntuacion());
    }

    /**
     * Prueba para consultar la lista de Books.
     *
     * @generated
     */
    @Test
    public void getAnfitrionesTest() {
        List<AnfitrionEntity> list = anfitrionLogic.getAnfitriones();
        Assert.assertEquals(data.size(), list.size());
        for (AnfitrionEntity entity : list) {
            boolean found = false;
            for (AnfitrionEntity storedEntity : data) {
                if (entity.getNumeroID().equals(storedEntity.getNumeroID())) {
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
    public void getAnfitrionTest() throws BusinessLogicException {
        AnfitrionEntity entity = data.get(0);
        AnfitrionEntity resultEntity = anfitrionLogic.getAnfitrion(entity.getNumeroID());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getTipoID(), resultEntity.getTipoID());
        Assert.assertEquals(entity.getUsuario(), resultEntity.getUsuario());
        Assert.assertEquals(entity.getImage(), resultEntity.getImage());
        Assert.assertEquals(entity.getContrasenha(), resultEntity.getContrasenha());
        Assert.assertEquals(entity.getCorreo(), resultEntity.getCorreo());
        Assert.assertEquals(entity.getDireccion(), resultEntity.getDireccion());
        Assert.assertEquals(entity.getTelefono(), resultEntity.getTelefono());
        Assert.assertEquals(entity.getNumeroTarjeta(), resultEntity.getNumeroTarjeta());
        Assert.assertEquals(entity.getPuntuacion(), entity.getPuntuacion());
    }

}
