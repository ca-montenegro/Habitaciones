package co.edu.uniandes.csw.habitaciones.test.logic;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
import co.edu.uniandes.csw.habitaciones.ejbs.ViviendaLogic;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.ViviendaPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)

/**
 *
 * @author c.penaloza
 */
public class ViviendaLogicTest {
    
    public static final String DEPLOY = "Prueba";
    
    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(ViviendaEntity.class.getPackage())
                .addPackage(ViviendaLogic.class.getPackage())
                .addPackage(ViviendaPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * @generated
     */
    @Inject
    private ViviendaLogic viviendaLogic;
    
    /**
     * @generated
     */
    @PersistenceContext
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
     * Prueba para crear un Vivienda.
     *
     * @generated
     */
    @Test
    public void createViviendaTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        ViviendaEntity entity = factory.manufacturePojo(ViviendaEntity.class);
        ViviendaEntity result = viviendaLogic.createVivienda(entity);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getCapacidad(), entity.getCapacidad());
        Assert.assertEquals(result.getCiudad(), entity.getCiudad());
        Assert.assertEquals(result.getDireccion(), entity.getDireccion());
        Assert.assertEquals(result.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(result.getDireccion(), entity.getDireccion());
        Assert.assertEquals(result.getDescripcion(), entity.getDescripcion());
    }
    
    /**
     * Prueba para consultar la lista de Viviendas.
     *
     * @generated
     */
    @Test
    public void getViviendasTest() {
        List<ViviendaEntity> list = viviendaLogic.getViviendas();
        Assert.assertEquals(data.size(), list.size());
        for (ViviendaEntity entity : list) {
            boolean found = false;
            for (ViviendaEntity storedEntity : data) {
                if (entity.getIdVivienda().equals(storedEntity.getIdVivienda())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Vivienda.
     *
     * @generated
     */
    @Test
    public void getViviendaTest() throws BusinessLogicException {
        ViviendaEntity entity = data.get(0);
        ViviendaEntity resultEntity = viviendaLogic.getVivienda(entity.getIdVivienda());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getCapacidad(), resultEntity.getCapacidad());
        Assert.assertEquals(entity.getCiudad(), resultEntity.getCiudad());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
        Assert.assertEquals(entity.getDireccion(), resultEntity.getDireccion());
    }
    
    /**
     * Prueba para eliminar un Vivienda.
     *
     * @generated
     */
    @Test
    public void deleteViviendaTest() throws BusinessLogicException {
        ViviendaEntity entity = data.get(0);
        viviendaLogic.deleteVivienda(entity.getIdVivienda());
        ViviendaEntity deleted = em.find(ViviendaEntity.class, entity.getIdVivienda());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Vivienda.
     *
     * @generated
     */
    @Test
    public void updateViviendaTest() throws BusinessLogicException {
        ViviendaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ViviendaEntity pojoEntity = factory.manufacturePojo(ViviendaEntity.class);
        pojoEntity.setIdVivienda(entity.getIdVivienda());
        
        viviendaLogic.updateVivienda(pojoEntity);
        
        ViviendaEntity resp = em.find(ViviendaEntity.class, entity.getIdVivienda());
        
        Assert.assertEquals(pojoEntity.getCapacidad(), resp.getCapacidad());
        Assert.assertEquals(pojoEntity.getDireccion(), resp.getDireccion());
        Assert.assertEquals(pojoEntity.getValorDiario(), resp.getValorDiario());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
    }
    
}
