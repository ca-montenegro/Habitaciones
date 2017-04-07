delete from UsuarioEntity;
delete from ReservaEntity;
delete from ViviendaEntity;
delete from HabitacionEntity;
delete from MultaEntity;
delete from UsuarioEntity_ReservaEntity;
delete from UsuarioEntity_ViviendaEntity;


insert into UsuarioEntity (numeroId, DTYPE, contrsenha, correo, direccion, nombre, numeroTarjeta, telefono, tipoId, usuario, puntuacion) values (1,'C', 'hola', 'hola@hola.com', 'calle 1 No 1-1', 'Andres', 18723889, 6876187, 'Cedula', 'andres', '10');
insert into UsuarioEntity (numeroId, DTYPE, contrsenha, correo, direccion, nombre, numeroTarjeta, telefono, tipoId, usuario, puntuacion) values (2,'C', 'hola2', 'hola2@hola.com', 'calle 2 No 1-1', 'David', 18723888, 6876188, 'Cedula', 'david', '10');

insert into ViviendaEntity (idVivienda, capacidad, ciudad, descripcion, imagen, valorDiario) values (1, 5, 'Bogota', 'Casa bonita', 'https://a0.muscache.com/im/pictures/42492006/d656f7da_original.jpg?aki_policy=large', 98273);
insert into ViviendaEntity (idVivienda, capacidad, ciudad, descripcion, imagen, valorDiario) values (2, 5, 'Bogota', 'Casa grande', 'http://www.colorcombos.com/images/colors/003366.png', 98274);
insert into ViviendaEntity (idVivienda, capacidad, ciudad, descripcion, imagen, valorDiario) values (3, 5, 'Bogota', 'Casa fea', 'https://a0.muscache.com/im/pictures/52149945/33130c63_original.jpg?aki_policy=large', 98275);
insert into ViviendaEntity (idVivienda, capacidad, ciudad, descripcion, imagen, valorDiario) values (1, 5, 'Bogota', 'Casa pequeña', 'http://www.colorcombos.com/images/colors/003366.png', 98276);

insert into UsuarioEntity_ViviendaEntity (anfitrionEntity_numeroId, viviendas_idvivienda) values (1, 1);
insert into UsuarioEntity_ViviendaEntity (anfitrionEntity_numeroId, viviendas_idvivienda) values (1, 2);
insert into UsuarioEntity_ViviendaEntity (anfitrionEntity_numeroId, viviendas_idvivienda) values (1, 3);
insert into UsuarioEntity_ViviendaEntity (anfitrionEntity_numeroId, viviendas_idvivienda) values (1, 4);

insert into HabitacionEntity (id, area, capacidad, descripcion, imagen, valorDiario, idVivienda_idvivienda) values (1, 30, 2, 'bonita', 'http://www.casacumbrero.com/images/casa_rural_habitaciones_1_b.jpg', 858, 1)
insert into HabitacionEntity (id, area, capacidad, descripcion, imagen, valorDiario, idVivienda_idvivienda) values (1, 30, 2, 'fea', 'http://www.casaentremuros.com/media/img/habitacion1_casa_entremuros800x600.jpg', 859, 2)
insert into HabitacionEntity (id, area, capacidad, descripcion, imagen, valorDiario, idVivienda_idvivienda) values (1, 30, 2, 'grande', 'https://www.diariofemenino.com/images/galeria/12000/12166_la-habitacion-de-la-casa-de-jude-law-y-sienna-miller.jpg', 852, 3)
insert into HabitacionEntity (id, area, capacidad, descripcion, imagen, valorDiario, idVivienda_idvivienda) values (1, 30, 2, 'mediana', 'http://countryvillaslanzarote.com/wp-content/uploads/2015/05/C-de-Mozaga-habitacion1.jpg', 855, 4)

insert into MultaEntity (codigoMulta, fechaCancelacion, fechaPago) values (1, '','')
insert into MultaEntity (codigoMulta, fechaCancelacion, fechaPago) values (1, '','')
insert into MultaEntity (codigoMulta, fechaCancelacion, fechaPago) values (1, '','')
insert into MultaEntity (codigoMulta, fechaCancelacion, fechaPago) values (1, '07/03/2017','')

insert into ReservaEntity (codigoReserva, costo, estado, fechaInicio, fechaFin, habitacionId, viviendas_idvivienda, multa_codigoMulta) values (1, 89654, 'H', '07/03/2017', '10/03/2017', 1 , 3, 1)
insert into ReservaEntity (codigoReserva, costo, estado, fechaInicio, fechaFin, habitacionId, viviendas_idvivienda, multa_codigoMulta) values (2, 89654, 'P', '08/03/2017', '11/03/2017', 1 , 3, 2)
insert into ReservaEntity (codigoReserva, costo, estado, fechaInicio, fechaFin, habitacionId, viviendas_idvivienda, multa_codigoMulta) values (3, 89654, 'A', '09/03/2017', '12/03/2017', 1 , 3, 3)
insert into ReservaEntity (codigoReserva, costo, estado, fechaInicio, fechaFin, habitacionId, viviendas_idvivienda, multa_codigoMulta) values (4, 89654, 'C', '10/03/2017', '13/03/2017', 1 , 3, 4)

insert into UsuarioEntity_ReservaEntity (UsuarioEntity_numeroId, reservas_codigoReserva) values (1,1)
insert into UsuarioEntity_ReservaEntity (UsuarioEntity_numeroId, reservas_codigoReserva) values (2,2)
insert into UsuarioEntity_ReservaEntity (UsuarioEntity_numeroId, reservas_codigoReserva) values (3,3)
insert into UsuarioEntity_ReservaEntity (UsuarioEntity_numeroId, reservas_codigoReserva) values (4,4)