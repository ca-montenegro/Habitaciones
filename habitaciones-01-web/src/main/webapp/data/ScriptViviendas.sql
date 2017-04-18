delete from UsuarioEntity_ReservaEntity;
delete from ReservaEntity;
delete from MultaEntity;
delete from UsuarioEntity_ViviendaEntity;
delete from HabitacionEntity;
delete from ViviendaEntity;
delete from UsuarioEntity;

insert into UsuarioEntity (numeroId, DTYPE, contrasenha, correo, direccion, nombre, numeroTarjeta, telefono, tipoId, usuario, puntuacion) values (1,'C', 'hola', 'hola@hola.com', 'calle 1 No 1-1', 'Andres', 18723889, 6876187, 'Cedula', 'andres', 10);
insert into UsuarioEntity (numeroId, DTYPE, contrasenha, correo, direccion, nombre, numeroTarjeta, telefono, tipoId, usuario, puntuacion) values (2,'C', 'hola2', 'hola2@hola.com', 'calle 2 No 1-1', 'David', 18723888, 6876188, 'Cedula', 'david', 10);

insert into ViviendaEntity (idVivienda, capacidad, ciudad, descripcion, direccion, imagen, valorDiario, anfitrion_numeroId) values (1, 6, 'Bogota', 'Casa bonita', 'Calle 200', 'https://a0.muscache.com/im/pictures/42492006/d656f7da_original.jpg?aki_policy=large', 273,1);
insert into ViviendaEntity (idVivienda, capacidad, ciudad, descripcion, direccion, imagen, valorDiario, anfitrion_numeroId) values (2, 8, 'Cali', 'Casa grande', 'Calle 15', 'https://a0.muscache.com/im/pictures/25735497/948807b4_original.jpg?aki_policy=large', 458,2);
insert into ViviendaEntity (idVivienda, capacidad, ciudad, descripcion, direccion, imagen, valorDiario, anfitrion_numeroId) values (3, 5, 'Medellin', 'Casa fea', 'Calle 45', 'https://a0.muscache.com/im/pictures/52149945/33130c63_original.jpg?aki_policy=large', 430,1);
insert into ViviendaEntity (idVivienda, capacidad, ciudad, descripcion, direccion, imagen, valorDiario, anfitrion_numeroId) values (4, 5, 'Villa de Leyva', 'Casa pequena', 'Calle 70', 'https://a0.muscache.com/im/pictures/21162888/5a0f1151_original.jpg?aki_policy=large', 350,2);
insert into ViviendaEntity (idVivienda, capacidad, ciudad, descripcion, direccion, imagen, valorDiario, anfitrion_numeroId) values (5, 6, 'Cartagena', 'Casa de playa', 'Calle 1', 'https://a0.muscache.com/im/pictures/15960725/b8a9e9e0_original.jpg?aki_policy=large', 550,1);
insert into ViviendaEntity (idVivienda, capacidad, ciudad, descripcion, direccion, imagen, valorDiario, anfitrion_numeroId) values (6, 7, 'Santa Marta', 'Casa divertida', 'Calle 18', 'https://a0.muscache.com/im/pictures/16304758/0ec38e53_original.jpg?aki_policy=large', 470,2);


insert into HabitacionEntity (id, area, capacidad, descripcion, imagen, valorDiario, vivienda_idvivienda) values (1, 30, 2, 'bonita', 'http://www.casacumbrero.com/images/casa_rural_habitaciones_1_b.jpg', 858, 1);
insert into HabitacionEntity (id, area, capacidad, descripcion, imagen, valorDiario, vivienda_idvivienda) values (2, 31, 3, 'fea', 'http://www.casaentremuros.com/media/img/habitacion1_casa_entremuros800x600.jpg', 859, 2);
insert into HabitacionEntity (id, area, capacidad, descripcion, imagen, valorDiario, vivienda_idvivienda) values (3, 32, 4, 'grande', 'https://www.diariofemenino.com/images/galeria/12000/12166_la-habitacion-de-la-casa-de-jude-law-y-sienna-miller.jpg', 852, 3);
insert into HabitacionEntity (id, area, capacidad, descripcion, imagen, valorDiario, vivienda_idvivienda) values (4, 33, 5, 'mediana', 'http://countryvillaslanzarote.com/wp-content/uploads/2015/05/C-de-Mozaga-habitacion1.jpg', 855, 3);

insert into UsuarioEntity_ViviendaEntity (anfitrionEntity_numeroId, viviendas_idvivienda) values (2, 1);
insert into UsuarioEntity_ViviendaEntity (anfitrionEntity_numeroId, viviendas_idvivienda) values (2, 2);
insert into UsuarioEntity_ViviendaEntity (anfitrionEntity_numeroId, viviendas_idvivienda) values (2, 3);
insert into UsuarioEntity_ViviendaEntity (anfitrionEntity_numeroId, viviendas_idvivienda) values (2, 4);

insert into MultaEntity (codigoMulta, fechaCancelacion, fechaPago) values (1, null,null);
insert into MultaEntity (codigoMulta, fechaCancelacion, fechaPago) values (2, null,null);
insert into MultaEntity (codigoMulta, fechaCancelacion, fechaPago) values (3, null,null);
insert into MultaEntity (codigoMulta, fechaCancelacion, fechaPago) values (4, '2017-03-07 00:00:00',null);

insert into ReservaEntity (codigoReserva, costo, estado, fechaInicio, fechaFin, habitacion_Id, vivienda_idvivienda, multa_codigoMulta) values (1, 89654, 'H', '2017-03-07 00:00:00', '2017-03-10 00:00:00', 1 , 1, 1);
insert into ReservaEntity (codigoReserva, costo, estado, fechaInicio, fechaFin, habitacion_Id, vivienda_idvivienda, multa_codigoMulta) values (2, 89654, 'P', '2017-03-08 00:00:00', '2017-03-11 00:00:00', 1 , 2, 2);
insert into ReservaEntity (codigoReserva, costo, estado, fechaInicio, fechaFin, habitacion_Id, vivienda_idvivienda, multa_codigoMulta) values (3, 89654, 'A', '2017-03-09 00:00:00', '2017-03-12 00:00:00', 1 , 3, 3);
insert into ReservaEntity (codigoReserva, costo, estado, fechaInicio, fechaFin, habitacion_Id, vivienda_idvivienda, multa_codigoMulta) values (4, 89654, 'C', '2017-03-10 00:00:00', '2017-03-13 00:00:00', 1 , 4, 4);

insert into UsuarioEntity_ReservaEntity (UsuarioEntity_numeroId, reservas_codigoReserva) values (1,1);
insert into UsuarioEntity_ReservaEntity (UsuarioEntity_numeroId, reservas_codigoReserva) values (2,2);
insert into UsuarioEntity_ReservaEntity (UsuarioEntity_numeroId, reservas_codigoReserva) values (1,3);
insert into UsuarioEntity_ReservaEntity (UsuarioEntity_numeroId, reservas_codigoReserva) values (2,4);