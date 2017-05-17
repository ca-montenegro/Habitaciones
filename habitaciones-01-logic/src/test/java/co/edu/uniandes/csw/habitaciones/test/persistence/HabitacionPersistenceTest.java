/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.persistence;

import co.edu.uniandes.csw.habitaciones.entities.HabitacionEntity;
import co.edu.uniandes.csw.habitaciones.persistence.HabitacionPersistence;
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
 * @author l.maya10
 */
@RunWith(Arquillian.class)
public class HabitacionPersistenceTest {
    
   public static final String DEPLOY = "PruebaHabitacionesPersistence";
   
   @Deployment
   public static JavaArchive createDeproyment(){
       return ShrinkWrap.create(JavaArchive.class)
               .addPackage(HabitacionEntity.class.getPackage())
               .addPackage(HabitacionPersistence.class.getPackage())
               .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");               
   }
   
   @Inject 
   private HabitacionPersistence habitacionPersistence;
   
   @PersistenceContext(unitName = "habitacionesPU")
   private EntityManager em;
   
   @Inject
   UserTransaction utx;
   
   @Before
   public void configTest()
   {
       try
       {
           utx.begin();
           clearData();
           insertData();
           utx.commit();
       }
       catch(Exception e)
       {
          e.printStackTrace();
          try
          {
              utx.rollback();
          }
          catch(Exception e1)
          {
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
        PodamFactory factory = new PodamFactoryImpl();
        HabitacionEntity newEntity = factory.manufacturePojo(HabitacionEntity.class);
        HabitacionEntity result = habitacionPersistence.create(newEntity);

        Assert.assertNotNull(result);

        HabitacionEntity entity = em.find(HabitacionEntity.class, result.getId());

        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
        Assert.assertEquals(newEntity.getArea(), entity.getArea());
        Assert.assertEquals(newEntity.getImagen(), entity.getImagen());
        Assert.assertEquals(newEntity.getValorDiario(), entity.getValorDiario());
    }

    /**
     * Prueba para consultar la lista de habitaciones.
     *
     * @generated
     */
    @Test
    public void getHabitacionesTest() {
        List<HabitacionEntity> list = habitacionPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (HabitacionEntity ent : list) {
            boolean found = false;
            for (HabitacionEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
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
        HabitacionEntity entity = data.get(0);
        HabitacionEntity newEntity = habitacionPersistence.find(entity.getId());

        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getCapacidad(), newEntity.getCapacidad());
        Assert.assertEquals(newEntity.getArea(), newEntity.getArea());
        Assert.assertEquals(newEntity.getImagen(), newEntity.getImagen());
        Assert.assertEquals(newEntity.getValorDiario(), newEntity.getValorDiario());
    }

    /**
     * Prueba para eliminar una habitacion.
     *
     * @generated
     */
    @Test
    public void deleteHabitacionTest() {
        HabitacionEntity entity = data.get(0);
        habitacionPersistence.delete(entity.getId());
        HabitacionEntity deleted = em.find(HabitacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una habitacion.
     *
     * @generated
     */
    @Test
    public void updateHabitacionTest() {
        HabitacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        HabitacionEntity newEntity = factory.manufacturePojo(HabitacionEntity.class);
        newEntity.setId(entity.getId());

        habitacionPersistence.update(newEntity);

        HabitacionEntity resp = em.find(HabitacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getCapacidad(), resp.getCapacidad());
        Assert.assertEquals(newEntity.getArea(), resp.getArea());
        Assert.assertEquals(newEntity.getImagen(), resp.getImagen());
        Assert.assertEquals(newEntity.getValorDiario(), resp.getValorDiario());
    }
   
   
   
    
}
