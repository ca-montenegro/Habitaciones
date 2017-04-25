/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.persistence;

import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import co.edu.uniandes.csw.habitaciones.persistence.ViviendaPersistence;
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
 *Clase de pruebas para la persistencia del recurso Vivienda
 * @author c.penaloza
 */
@RunWith(Arquillian.class)
public class ViviendaPersistenceTest {
    
    public static final String DEPLOY = "Prueba";
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ViviendaEntity.class.getPackage())
                .addPackage(ViviendaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
        /**
     * @generated
     */
    @Inject
    private ViviendaPersistence viviendaPersistence;

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
        em.createQuery("delete from ViviendaEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<ViviendaEntity> data = new ArrayList<ViviendaEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            ViviendaEntity entity = factory.manufacturePojo(ViviendaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Vivienda.
     *
     * @generated
     */
    @Test
    public void createViviendaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ViviendaEntity newEntity = factory.manufacturePojo(ViviendaEntity.class);
        ViviendaEntity result = viviendaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ViviendaEntity entity = em.find(ViviendaEntity.class, result.getIdVivienda());

        Assert.assertEquals(newEntity.getCapacidad(), entity.getCapacidad());
        Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
        Assert.assertEquals(newEntity.getDireccion(), entity.getDireccion());
        Assert.assertEquals(newEntity.getImagen(), entity.getImagen());
        Assert.assertEquals(newEntity.getNumeroHabitaciones(), entity.getNumeroHabitaciones());
        Assert.assertEquals(newEntity.getValorDiario(), entity.getValorDiario());
    }

    /**
     * Prueba para consultar la lista de Viviendas.
     *
     * @generated
     */
    @Test
    public void getViviendasTest() {
        List<ViviendaEntity> list = viviendaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ViviendaEntity ent : list) {
            boolean found = false;
            for (ViviendaEntity entity : data) {
                if (ent.getIdVivienda().equals(entity.getIdVivienda())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Vivienda.
     *
     * @generated
     */
    @Test
    public void getViviendaTest() {
        ViviendaEntity entity = data.get(0);
        ViviendaEntity newEntity = viviendaPersistence.find(entity.getIdVivienda());

        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getCapacidad(), newEntity.getCapacidad());
        Assert.assertEquals(newEntity.getCiudad(), newEntity.getCiudad());
        Assert.assertEquals(newEntity.getDireccion(), newEntity.getDireccion());
        Assert.assertEquals(newEntity.getImagen(), newEntity.getImagen());
        Assert.assertEquals(newEntity.getNumeroHabitaciones(), newEntity.getNumeroHabitaciones());
        Assert.assertEquals(newEntity.getValorDiario(), newEntity.getValorDiario());
    }

    /**
     * Prueba para eliminar una Vivienda.
     *
     * @generated
     */
    @Test
    public void deleteViviendaTest() {
        ViviendaEntity entity = data.get(0);
        viviendaPersistence.delete(entity.getIdVivienda());
        ViviendaEntity deleted = em.find(ViviendaEntity.class, entity.getIdVivienda());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Vivienda.
     *
     * @generated
     */
    @Test
    public void updateViviendaTest() {
        ViviendaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ViviendaEntity newEntity = factory.manufacturePojo(ViviendaEntity.class);
        newEntity.setIdVivienda(entity.getIdVivienda());

        viviendaPersistence.update(newEntity);

        ViviendaEntity resp = em.find(ViviendaEntity.class, entity.getIdVivienda());

        Assert.assertEquals(newEntity.getCapacidad(), resp.getCapacidad());
        Assert.assertEquals(newEntity.getCiudad(), resp.getCiudad());
        Assert.assertEquals(newEntity.getDireccion(), resp.getDireccion());
        Assert.assertEquals(newEntity.getImagen(), resp.getImagen());
        Assert.assertEquals(newEntity.getNumeroHabitaciones(), resp.getNumeroHabitaciones());
        Assert.assertEquals(newEntity.getValorDiario(), resp.getValorDiario());
    }
    
}
