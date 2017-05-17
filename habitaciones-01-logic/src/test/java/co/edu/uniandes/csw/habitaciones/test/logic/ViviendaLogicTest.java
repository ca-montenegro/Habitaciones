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
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * Crea una lista de viviendas entity
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
    public void createViviendaTest(){
        try {
            PodamFactory factory = new PodamFactoryImpl();
            ViviendaEntity entity = factory.manufacturePojo(ViviendaEntity.class);
            ViviendaEntity result = viviendaLogic.createVivienda(entity);
            Assert.assertNotNull("No deberia ser nulo",result);
            Assert.assertEquals("El resultado no es el esperado",result.getCapacidad(), entity.getCapacidad());
            Assert.assertEquals("El resultado no es el esperado",result.getCiudad(), entity.getCiudad());
            Assert.assertEquals("El resultado no es el esperado",result.getDireccion(), entity.getDireccion());
            Assert.assertEquals("El resultado no es el esperado",result.getDescripcion(), entity.getDescripcion());
            Assert.assertEquals("El resultado no es el esperado",result.getImagen(), entity.getImagen());
            Assert.assertEquals("El resultado no es el esperado",result.getDescripcion(), entity.getDescripcion());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ViviendaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para crear un Vivienda.
     *
     * @generated
     */
    @Test
    public void createViviendaPrecioNegativoTest(){
        try {
            PodamFactory factory = new PodamFactoryImpl();
            ViviendaEntity entity = factory.manufacturePojo(ViviendaEntity.class);
            entity.setValorDiario(-5.0);
            ViviendaEntity result = viviendaLogic.createVivienda(entity);
            Assert.fail("El precio no puede ser negativo");
        } catch (BusinessLogicException ex) {
        }
    }
    
    /**
     * Prueba para crear un Vivienda.
     *
     * @generated
     */
    @Test
    public void createViviendaCapacidadNegativaTest(){
        try {
            PodamFactory factory = new PodamFactoryImpl();
            ViviendaEntity entity = factory.manufacturePojo(ViviendaEntity.class);
            entity.setCapacidad(-2);
            ViviendaEntity result = viviendaLogic.createVivienda(entity);
            Assert.fail("La capacidad no puede ser negativa");
        } catch (BusinessLogicException ex) {
        }
    }
    
    /**
     * Prueba para crear un Vivienda.
     *
     * @generated
     */
    @Test
    public void createViviendaSinDireccionTest(){
        try {
            PodamFactory factory = new PodamFactoryImpl();
            ViviendaEntity entity = factory.manufacturePojo(ViviendaEntity.class);
            entity.setDireccion("");
            ViviendaEntity result = viviendaLogic.createVivienda(entity);
            Assert.fail("La direccion no puede ser vacia");
        } catch (BusinessLogicException ex) {
        }
    }
    
    /**
     * Prueba para consultar la lista de Viviendas.
     * Get vivienda test
     * @generated
     */
    @Test
    public void getViviendasTest() {
        List<ViviendaEntity> list = viviendaLogic.getViviendas();
        Assert.assertEquals("El resultado no es el esperado",data.size(), list.size());
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
     * Excepcion si ocurre un error
     * @generated
     */
    @Test
    public void getViviendaTest(){
        try {
            ViviendaEntity entity = data.get(0);
            ViviendaEntity resultEntity = viviendaLogic.getVivienda(entity.getIdVivienda());
            Assert.assertNotNull(resultEntity);
            Assert.assertEquals("El resultado no es el esperado",entity.getCapacidad(), resultEntity.getCapacidad());
            Assert.assertEquals("El resultado no es el esperado",entity.getCiudad(), resultEntity.getCiudad());
            Assert.assertEquals("El resultado no es el esperado",entity.getDescripcion(), resultEntity.getDescripcion());
            Assert.assertEquals("El resultado no es el esperado",entity.getDireccion(), resultEntity.getDireccion());
            Assert.assertEquals("El resultado no es el esperado",entity.getImagen(), resultEntity.getImagen());
            Assert.assertEquals("El resultado no es el esperado",entity.getValorDiario(), resultEntity.getValorDiario());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ViviendaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Prueba para eliminar un Vivienda.
     *
     * @generated
     */
    @Test
    public void deleteViviendaTest() {
        ViviendaEntity entity = data.get(0);
        try {
            viviendaLogic.deleteVivienda(entity.getIdVivienda());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ViviendaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ViviendaEntity deleted = em.find(ViviendaEntity.class, entity.getIdVivienda());
        Assert.assertNull("deberia ser nulo",deleted);
        
        entity = data.get(1);
        try {
            viviendaLogic.deleteVivienda(entity.getIdVivienda());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ViviendaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        deleted = em.find(ViviendaEntity.class, entity.getIdVivienda());
        Assert.assertNull("deberia ser nulo",deleted);
        
        entity = data.get(2);
        deleted = em.find(ViviendaEntity.class, entity.getIdVivienda());
        Assert.assertNotNull("No deberia ser nulo",deleted);
    }
    
    /**
     * Prueba para actualizar un Vivienda.
     *
     * @generated
     */
    @Test
    public void updateViviendaTest(){
        ViviendaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ViviendaEntity pojoEntity = factory.manufacturePojo(ViviendaEntity.class);
        pojoEntity.setIdVivienda(entity.getIdVivienda());
        
        try {
            viviendaLogic.updateVivienda(pojoEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ViviendaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ViviendaEntity resp = em.find(ViviendaEntity.class, entity.getIdVivienda());
        
        Assert.assertEquals("El resultado no es el esperado",pojoEntity.getCapacidad(), resp.getCapacidad());
        Assert.assertEquals("El resultado no es el esperado",pojoEntity.getDireccion(), resp.getDireccion());
        Assert.assertEquals("El resultado no es el esperado",pojoEntity.getValorDiario(), resp.getValorDiario());
        Assert.assertEquals("El resultado no es el esperado",pojoEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals("El resultado no es el esperado",pojoEntity.getImagen(), resp.getImagen());
        Assert.assertEquals("El resultado no es el esperado",pojoEntity.getCiudad(), resp.getCiudad());
        Assert.assertEquals("El resultado no es el esperado",pojoEntity.getDireccion(), resp.getDireccion());
        
    }
}
