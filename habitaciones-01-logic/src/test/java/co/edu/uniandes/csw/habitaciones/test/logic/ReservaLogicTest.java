/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.habitaciones.test.logic;

import co.edu.uniandes.csw.habitaciones.entities.ReservaEntity;
import co.edu.uniandes.csw.habitaciones.ejbs.ReservaLogic;
import co.edu.uniandes.csw.habitaciones.entities.ViviendaEntity;
import co.edu.uniandes.csw.habitaciones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.habitaciones.persistence.ReservaPersistence;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
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

@RunWith(Arquillian.class)
public class ReservaLogicTest {
    
    public static final String DEPLOY = "PruebaReservaLogic";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaLogic.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private ReservaLogic reservaLogic;

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
        em.createQuery("delete from ReservaEntity").executeUpdate();
    }

    /**
     * Datos usados en las pruebas.
     */
    private List<ReservaEntity> data = new ArrayList<ReservaEntity>();

    private ViviendaEntity vivienda; 
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     * @generated
     */
    private void insertData() {
      
        PodamFactory factory = new PodamFactoryImpl();

        // crea una vivienda para las pruebas
        vivienda = factory.manufacturePojo(ViviendaEntity.class);
        vivienda.setValorDiario(10000.00);
        em.persist(vivienda);
        
        for (int i = 0; i < 3; i++) {   
          
            // crea una reserva (sin asociaciones)
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
            entity.setVivienda(vivienda);
            
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Reserva.
     *
     * @generated
     */
    @Test
    public void createReservaTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        
        // crea una reserva (sin asociaciones)
        ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
        // hace que la fecha final sea mayor a fecha inicial
        entity.setFechaFin( agregaUnDia(entity.getFechaInicio()) );
        // asocia la reserva a la habitación de prueba
        entity.setVivienda(vivienda);
        
        ReservaEntity result = reservaLogic.createReserva(entity);
        Assert.assertNotNull("El mensaje no es el esperado",result);
        double error = 0.00000001;

        Assert.assertEquals("El mensaje no es el esperado", result.getCosto(), entity.getCosto(), error);
        Assert.assertEquals("El mensaje no es el esperado", result.getEstado(),entity.getEstado());
        Assert.assertEquals("El mensaje no es el esperado", result.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals("El mensaje no es el esperado", result.getFechaFin(), entity.getFechaFin());
        Assert.assertEquals("El mensaje no es el esperado", result.getHabitacion(), entity.getHabitacion());
        Assert.assertEquals("El mensaje no es el esperado", result.getMulta(), entity.getMulta());
        Assert.assertEquals("El mensaje no es el esperado", result.getVivienda(), entity.getVivienda());
    }

    /**
     * Prueba para consultar la lista de Reservas.
     *
     * @generated
     */
    @Test
    public void getReservasTest() {
        List<ReservaEntity> list = reservaLogic.getReservas();
        Assert.assertEquals(data.size(), list.size());
        for (ReservaEntity entity : list) {
            boolean found = false;
            for (ReservaEntity storedEntity : data) {
                if (entity.getCodigoReserva().equals(storedEntity.getCodigoReserva())) {
                    found = true;
                }
            }
            Assert.assertTrue("El mensaje no es el esperado",found);
        }
    }

    /**
     * Prueba para consultar un Book.
     *
     * @generated
     */
    @Test
    public void getReservaTest() {
        ReservaEntity entity = data.get(0);
        ReservaEntity resultEntity = reservaLogic.getReserva(entity.getCodigoReserva());
        Assert.assertNotNull(resultEntity);
        double error = 0.00000001;
        Assert.assertEquals("El mensaje no es el esperado",entity.getCosto(), resultEntity.getCosto(), error);
        Assert.assertEquals("El mensaje no es el esperado",entity.getEstado(),resultEntity.getEstado());
        Assert.assertEquals("El mensaje no es el esperado",entity.getFechaInicio(), resultEntity.getFechaInicio());
        Assert.assertEquals("El mensaje no es el esperado",entity.getFechaFin(), resultEntity.getFechaFin());
        Assert.assertEquals("El mensaje no es el esperado",entity.getHabitacion(), resultEntity.getHabitacion());
        Assert.assertEquals("El mensaje no es el esperado",entity.getMulta(), resultEntity.getMulta());
        //Assert.assertEquals(entity.getVivienda(), resultEntity.getVivienda());
    }

    /**
     * Prueba para actualizar un Book.
     *
     * @generated
     */
    @Test
    public void updateBookTest() {
        ReservaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity pojoEntity = factory.manufacturePojo(ReservaEntity.class);
        pojoEntity.setCodigoReserva(entity.getCodigoReserva());

        reservaLogic.updateReserva(pojoEntity);

        ReservaEntity resp = em.find(ReservaEntity.class, entity.getCodigoReserva());
        
        double error = 0.00000001;
        Assert.assertEquals("El mensaje no es el esperado",pojoEntity.getCosto(), resp.getCosto(), error);
        Assert.assertEquals("El mensaje no es el esperado",pojoEntity.getEstado(),resp.getEstado());
        Assert.assertEquals("El mensaje no es el esperado",pojoEntity.getFechaInicio(), resp.getFechaInicio());
        Assert.assertEquals("El mensaje no es el esperado",pojoEntity.getFechaFin(), resp.getFechaFin());
        Assert.assertEquals("El mensaje no es el esperado",pojoEntity.getHabitacion(), resp.getHabitacion());
        Assert.assertEquals("El mensaje no es el esperado",pojoEntity.getMulta(), resp.getMulta());
        Assert.assertEquals("El mensaje no es el esperado",pojoEntity.getVivienda(), resp.getVivienda());
    }
    
    private Date agregaDias(Date date, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, dias); //minus number would decrement the days
        return cal.getTime();      
    }
    
    private Date agregaUnDia(Date date) {
        return agregaDias(date, 1);
    }
    
}