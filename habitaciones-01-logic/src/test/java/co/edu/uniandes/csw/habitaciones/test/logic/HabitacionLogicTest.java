/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.logic;

import co.edu.uniandes.csw.habitaciones.ejbs.HabitacionLogic;
import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.HabitacionPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @generated
 */


/**
 *
 * @author l.maya10
 */
@RunWith(Arquillian.class)
public class HabitacionLogicTest {
    
    public static final String DEPLOY = "PruebaHabitacion";
    
    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment(){
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(HabitacionEntity.class.getPackage())
                .addPackage(HabitacionLogic.class.getPackage())
                .addPackage(HabitacionPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml","META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml","beans.xml");
    }
    
    /**
     * @generated
     */
    @Inject
    private HabitacionLogic habitacionLogic;
    
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
        em.createQuery("delete from HabitacionEntity").executeUpdate();
    }
    
    /**
     * @generated
     */
    private List<HabitacionEntity> data = new ArrayList<HabitacionEntity>();
    
     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            HabitacionEntity entity = factory.manufacturePojo(HabitacionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una Habitacion.
     *
     * @generated
     */
    @Test
    public void createHabitacionTest() {
        try{
        PodamFactory factory = new PodamFactoryImpl();
        HabitacionEntity entity = factory.manufacturePojo(HabitacionEntity.class);
        HabitacionEntity result = habitacionLogic.createHabitacion(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getArea(), entity.getArea());
        Assert.assertEquals(result.getCapacidad(), entity.getCapacidad());
        Assert.assertEquals(result.getImagen(), entity.getImagen());
        Assert.assertEquals(result.getDescripcion(), entity.getDescripcion());
        }
        catch(BusinessLogicException ex)
        {   
            Logger.getLogger(HabitacionLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /**
     * Prueba para consultar la lista de Habitaciones
     *
     * @generated
     */
    @Test
    public void getHabitacionesTest() {
        List<HabitacionEntity> list = habitacionLogic.getHabitaciones();
        Assert.assertEquals(data.size(), list.size());
        for (HabitacionEntity entity : list) {
            boolean found = false;
            for (HabitacionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
     /**
     * Prueba para consultar una habitacion.
     *
     * @generated
     */
    @Test
    public void getHabitacionTest() {
        try{
        HabitacionEntity entity = data.get(0);
        HabitacionEntity result = habitacionLogic.getHabitacion(entity.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getArea(), entity.getArea());
        Assert.assertEquals(result.getCapacidad(), entity.getCapacidad());
        Assert.assertEquals(result.getImagen(), entity.getImagen());
        Assert.assertEquals(result.getDescripcion(), entity.getDescripcion());
        }
        catch(BusinessLogicException ex)
        {
            Logger.getLogger(HabitacionLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    /**
     * Prueba para eliminar una habitacion.
     *
     * @generated
     */
    @Test
    public void deleteHabitacionTest() {
        try{
        HabitacionEntity entity = data.get(0);
        habitacionLogic.deleteHabitacion(entity.getId());
        HabitacionEntity deleted = em.find(HabitacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
        }
        catch (BusinessLogicException ex)
        {
            Logger.getLogger(HabitacionLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * rueba para actualizar una Habitacion.
     *
     * @generated
     */
    @Test
    public void updateHabitacionTest() {
        HabitacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        HabitacionEntity pojoEntity = factory.manufacturePojo(HabitacionEntity.class);
        pojoEntity.setId(entity.getId());

        try {
            habitacionLogic.updateHabitacion(pojoEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(HabitacionLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        HabitacionEntity result = em.find(HabitacionEntity.class, entity.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getArea(), pojoEntity.getArea());
        Assert.assertEquals(result.getCapacidad(), pojoEntity.getCapacidad());
        Assert.assertEquals(result.getImagen(), pojoEntity.getImagen());
        Assert.assertEquals(result.getDescripcion(), pojoEntity.getDescripcion());
    }
    
}
