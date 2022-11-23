/*------------------------------Cliente----------------------------------------------*/
/*Cliente : cedula, contrasenia, correo, nombre, estado , imagen perfil*/
insert into cliente values (1004, "LwroT5dPnyQxBZIP3EBSMTs1xQvu5LOecJllMgzSi/trfrGcWFbUO0b5i3AZRcdI", "perdomov.j07@gmail.com", "Juan", 1);
insert into cliente values (1005, "/cT8yW2NO22XscSn2ajaNOtR85I0EoBlqxwt3D/B0JgOhvnDnMFbzQWBU5SEvGST", "perdomo@gmail.com", "Jose", 1);
insert into cliente values (1006, "/cT8yW2NO22XscSn2ajaNOtR85I0EoBlqxwt3D/B0JgOhvnDnMFbzQWBU5SEvGST", "diego09@gmail.com", "Diego", 0);
insert into cliente values (1007, "LwroT5dPnyQxBZIP3EBSMTs1xQvu5LOecJllMgzSi/trfrGcWFbUO0b5i3AZRcdI", "pipe@gmail.com", "Felipe", 1);
insert into cliente values (1008, "LwroT5dPnyQxBZIP3EBSMTs1xQvu5LOecJllMgzSi/trfrGcWFbUO0b5i3AZRcdI", "alejandro@gmail.com", "Alejandro", 0);



/*------------------------------Cliente_Telefonos----------------------------------------------*/
/*Cliente_Telefonos : cliente_cedula , telefono*/
insert into cliente_telefonos values (1004, "3117556502");
insert into cliente_telefonos values (1004, "3148279730");
insert into cliente_telefonos values (1007, "3228484581");
insert into cliente_telefonos values (1006, "3123456789");

/*------------------------------Ciudad----------------------------------------------*/
/*Ciudad: codPostal , nombre */
insert into ciudad values (1, "Armenia");
insert into ciudad values (2, "Medellin");
insert into ciudad values (3, "Santa Marta");
insert into ciudad values (4, "Barranquilla");
insert into ciudad values (5, "Cali");

/*------------------------------Administrador----------------------------------------------*/
/*Administrador: cedula, contrasenia, correo, nombre */
insert into administrador values (1090, "9876", "lopezjose@gmail.com", "Jose");

/*------------------------------Admistrador_Teatro----------------------------------------------*/
/*Administrador teatro : cedula, contrasenia, correo, nombre*/
insert into administrador_teatro values (1012, "3346", "julian09@gmail.com", "Julian");
insert into administrador_teatro values (1013, "1236", "pedro1234@gmail.com", "Pedro");
insert into administrador_teatro values (1014, "8766", "alberto@gmail.com", "Alberto");
insert into administrador_teatro values (1015, "0987", "maria@gmail.com", "Maria");
insert into administrador_teatro values (1016, "0987", "ana@gmail.com", "ana");

/*------------------------------Teatro----------------------------------------------*/
/*Teatro: nit, direccion, nombre, adminCedula, adminTeatroCedula, codigoCiudad */
insert into teatro values (1, "Cl. 19 Nte. #13-9", "UniCine", 1090, 1012, 1);
insert into teatro values (2, "Cra. 100 #5-169", "UniCine", 1090, 1013, 2);
insert into teatro values (3, "Cra. 14 #6-02", "UniCine", 1090, 1014, 3);
insert into teatro values (4, "Cra. 6 #3-180", "UniCine", 1090, 1015, 3);
insert into teatro values (5, "Cra. 66B #34A-76", "UniCine", 1090, 1016, 3);

/*------------------------------Pelicula----------------------------------------------*/
/*Pelicula: codigo, estado, nombre, reparto, sinopsis, trailer*/
insert into pelicula values (1, "CARTELERA", "Black Adam", "Dwayne Johnson, Sarah Shahi, Pierce Brosnan.", "Casi 5.000 años después de haber sido dotado de los poderes omnipotentes de los antiguos dioses -y encarcelado con la misma rapidez-, Black Adam (Johnson) es liberado de su tumba terrenal, listo para desatar su forma única de justicia en el mundo moderno.", "https://www.youtube.com/watch?v=hcD9I3ckqnA");
insert into pelicula values (2, "CARTELERA", "Wakanda por Siempre", "Angela Bassett, Letitia Wright, Winston Duke, Danai Gurira, Florence Kasumba, Lupita Nyong''o, Martin Freeman, Tenoch Huerta, Dominique Thorne, Michaela Coel, Mabel Cadena y Alex Livanalli.", "Mientras los habitantes de Wakanda se esfuerzan por embarcarse en un nuevo capítulo, los héroes deben unirse con la ayuda de War Dog Nakia (Lupita Nyong''o) y Everett Ross (Martin Freeman) y forjar un nuevo camino para el reino de Wakanda.", "https://www.youtube.com/watch?v=INvxJD4FzLk");
insert into pelicula values (3, "CARTELERA",  "Lilo, Lilo, Cocodrilo", "Javier Barden (Hector P. Valenti), Shawn Mendez (Lyle), Sal Viscou, Don DiPetta.", "Josh Primm, de 12 años, le teme a muchas cosas y le cuesta hacer amigos. Cuando Josh descubre a Lyle, un cocodrilo que baila y canta en su ático, los dos forman un vínculo inusual mientras Lyle le muestra la magia de Nueva York a su nuevo amigo. Mientras cada miembro de la familia encuentra una relación única con Lyle, los Primm se transforman y adoptan un nuevo sentido del amor y la aventura.", "https://www.youtube.com/watch?v=JZckRZLzmU4");
insert into pelicula values (4, "PROXIMAMENTE", "Mar de Sangre", "Holly Earl, Jack Trueman, Catherine Hannay, Malachi Pullar-Latchman, Thomas Flynn, Joshua Takacs, Manuel Cauchi, Milo McDowell. ", "Un grupo de amigos roban un par de motos acuáticas pero terminan en un terrible accidente. En el mar y con un herido, tratarán de encontrar el camino de regreso a casa y de salvarse de la amenaza de los depredadores más temidos del océano. ", "https://www.youtube.com/watch?v=xgpsXRk-SrM");
insert into pelicula values (5, "PROXIMAMENTE", "Un Mundo Extraño", "Jake Gyllenhaal", "UN MUNDO EXTRAÑO, la nueva película de Walt Disney Animation Studios, presenta un viaje de acción y aventura que transcurre en una tierra inexplorada y traicionera, donde criaturas fantásticas aguardan a los legendarios Clade, una familia de exploradores cuyas diferencias amenazan con derribar su última misión que es, por lejos, la más decisiva.", "https://www.youtube.com/watch?v=LvIMiuYG6KQ");
/*insert into pelicula values (6, 1, "url-imagen", "La huerfana", "Julia Stiles, Isabelle Fuhrman, Matthew Finlan ", "La desquiciada Leena Klammer organiza una brillante fuga de un manicomio de Estonia y viaja a Estados Unidos robando la identidad de la hija desaparecida de una familia adinerada.", "url-video");*/


/*Pelicula_imagenes : codigo, imagenes, imagenes_key*/

insert into pelicula_imagenes values(1,"http://res.cloudinary.com/dz6jlo2kt/image/upload/v1669221581/unicine/peliculas/zgsgtjjgg5wrh0qjkpnd.png","unicine/peliculas/zgsgtjjgg5wrh0qjkpnd");
insert into pelicula_imagenes values(2,"http://res.cloudinary.com/dz6jlo2kt/image/upload/v1668697247/unicine/peliculas/cd4qjtrdum4r4s9g75z6.jpg","unicine/peliculas/cd4qjtrdum4r4s9g75z6");
insert into pelicula_imagenes values(3,"http://res.cloudinary.com/dz6jlo2kt/image/upload/v1668697247/unicine/peliculas/w83aurgj0pwrxjycefz9.jpg","unicine/peliculas/w83aurgj0pwrxjycefz9");
insert into pelicula_imagenes values(4,"http://res.cloudinary.com/dz6jlo2kt/image/upload/v1668697247/unicine/peliculas/emwdut2tttwxhvctlhx4.jpg","unicine/peliculas/emwdut2tttwxhvctlhx4");
insert into pelicula_imagenes values(5,"http://res.cloudinary.com/dz6jlo2kt/image/upload/v1668697247/unicine/peliculas/yldnfmkp55frqkatlt0k.jpg","unicine/peliculas/yldnfmkp55frqkatlt0k");


/*------------------------------Genero---------------------------------------------------*/
insert into pelicula_genero values (1, "ACCION");
insert into pelicula_genero values (1, "COMEDIA");
insert into pelicula_genero values (3, "ANIMADA");
insert into pelicula_genero values (4, "DRAMA");
insert into pelicula_genero values (5, "ANIMADA");
insert into pelicula_genero values (2, "ACCION");
insert into pelicula_genero values (2, "DRAMA");


/*------------------------------Calificacion----------------------------------------------*/
/*Calificacion : codigo, puntuacion, cedCliente, codPelicula*/
/*
insert into calificacion values (1, 4, 1004, 1);
insert into calificacion values (2, 2, 1004, 5);
insert into calificacion values (3, 1, 1006, 2);
insert into calificacion values (4, 1, 1007, 5);
insert into calificacion values (5, 5, 1004, 5);

 */

/*------------------------------Cupon----------------------------------------------*/
/*Cupon : codigo, criterio, descripcion, fechaVencimiento, valorDescuento*/
insert into cupon values (1, "Navidad", "Cupon por fiestas navidenias", "2022-10-10", 0);
insert into cupon values (2, "Halloween", "Cupon por mes de halloween", "2022-11-11", 0);
insert into cupon values (3, "Aniversario", "Cupon de aniversario de unicine", "2022-12-02", 0);
insert into cupon values (4, "Primer Registro", "Cupon por realizar primer registro", "2022-10-08", 0);
insert into cupon values (5, "Primera compra", "Cupon por realizar primera compra", "2022-11-01", 0);

/*------------------------------Horario----------------------------------------------*/
/*Horario: codigo, fechafinal, fechainicial, hora*/
insert into horario values (1, "2022-10-01", "2022-11-02", "20:30");
insert into horario values (2, "2022-09-29", "2022-11-01", "14:30");
insert into horario values (3, "2022-11-01", "2022-12-02", "18:00");
insert into horario values (4, "2022-10-21", "2022-11-22", "22:20");
insert into horario values (5, "2022-11-22", "2022-12-23", "17:30");

/*------------------------------Horario_Dia ------------------------------------------*/
insert into horario_dia values (1, "LUNES");
insert into horario_dia values (1, "MARTES");
insert into horario_dia values (2, "MARTES");
insert into horario_dia values (3, "MIERCOLES");
insert into horario_dia values (5, "VIERNES");
insert into horario_dia values (4, "SABADO");
insert into horario_dia values (4, "DOMINGO");

/*------------------------------PQRS----------------------------------------------*/
/*PQRS: codigo, asunto, descripcion, cedCliente*/
/*
insert into pqrs values (1, "Error eleccion de medio de pago", "Elegi erroneamente el medio de pago en el proceso de la compra", 1004);
insert into pqrs values (2, "Informacion cupon", "Informacion para redimir cupones", 1004);
insert into pqrs values (3, "Informacion confiteria", "Informacion de nuevos productos de la confiteria", 1004);
insert into pqrs values (4, "Error de seleccion de asientos", "Seleccione erroneamente los asientos", 1007);
insert into pqrs values (5, "Informacion", "Informacion de mis cupones", 1007);

 */
/*------------------------------Distribucion_Sillas----------------------------------------------*/
/*Distribuciion_ sillas: codigo, columnas, esquema, filas, total_sillas*/
insert into distribucion_sillas values (1, 10, "rute", 10, 100);
insert into distribucion_sillas values (2, 10, "ruta", 10, 100);
insert into distribucion_sillas values (3, 11, "ruta", 11, 121);
insert into distribucion_sillas values (4, 9, "ruta", 9, 81);
insert into distribucion_sillas values (5, 8, "ruta", 14, 112);

/*------------------------------Sala----------------------------------------------*/
/*Sala: numero_sala, nombre, distriSillasCodigo, teatroNit*/
insert into sala values (1, "sala 1", 1, 1);
insert into sala values (2, "sala 2", 2, 2);
insert into sala values (3, "sala 3 gold", 3, 3);
insert into sala values (4, "sala 4", 4, 4);
insert into sala values (5, "sala 5", 5, 5);

/*------------------------------CuponClinete----------------------------------------------*/
/*CuponCliente: codgio, estado, cedCliente, codCupon*/
/*
insert into cupon_cliente values (1, 0, 1004, 1);
insert into cupon_cliente values (2, 1, 1005, 2);
insert into cupon_cliente values (3, 1, 1006, 3);
insert into cupon_cliente values (4, 1, 1004, 4);
insert into cupon_cliente values (5, 0, 1008, 5);
 */
/*------------------------------Confiteria----------------------------------------------*/
/*Confiteria: codProducto, nombre, precio , tipo confiteria*/
insert into confiteria values (1, "CocaCola", 10000,'Bebidas');
insert into confiteria values (2, "Combo Mayor", 45000, 'Combos');
insert into confiteria values (3, "Crispetas 250gr", 25000,'Crispetas');
insert into confiteria values (4, "Chocolatina", 3000,'Dulceria');
insert into confiteria values (5, "Perro sencillo", 12000,'Hot_Dogs');

/*Confiteria_imagenes: codigo, imagenes, imagenes_key*/
insert into confiteria_imagenes values (1,"http://res.cloudinary.com/dz6jlo2kt/image/upload/v1668661079/unicine/confiterias/afvs9x38nrxx4ewtanbk.jpg","unicine/confiterias/afvs9x38nrxx4ewtanbk");
insert into confiteria_imagenes values (2,"http://res.cloudinary.com/dz6jlo2kt/image/upload/v1668661079/unicine/confiterias/ymcxwf7gzlx9vt21oj10.jpg","unicine/confiterias/ymcxwf7gzlx9vt21oj10");
insert into confiteria_imagenes values (3,"http://res.cloudinary.com/dz6jlo2kt/image/upload/v1668661079/unicine/confiterias/jtrltpt2v82cjlokq9we.jpg","unicine/confiterias/jtrltpt2v82cjlokq9we");
insert into confiteria_imagenes values (4,"http://res.cloudinary.com/dz6jlo2kt/image/upload/v1668661079/unicine/confiterias/lnu1uo20x4k646ofxjci.jpg","unicine/confiterias/lnu1uo20x4k646ofxjci");
insert into confiteria_imagenes values (5,"http://res.cloudinary.com/dz6jlo2kt/image/upload/v1668661079/unicine/confiterias/xxxryxhvl67bl8sxqwjg.jpg","unicine/confiterias/xxxryxhvl67bl8sxqwjg");

/*------------------------------Funcion----------------------------------------------*/
/*Funcion: codigo, precio, horaioCod, codPelicula, sala_numero_sala*/
insert into funcion values (1, 6.500, 1, 1, 1);
insert into funcion values (2, 6.500, 2, 2, 2);
insert into funcion values (3, 6.500, 3, 3, 3);
insert into funcion values (4, 6.500, 4, 4, 4);
insert into funcion values (5, 7.500, 5, 5, 5);

/*------------------------------Compra----------------------------------------------*/
/*Compra: codgio, fechaCompra, medioPago, numBoletas, valorTotal, cedCliente, codCupon, codFuncion */
/*
insert into compra values (1, "2022-10-14", 'PSE', 2, 45.000, 1004, 1, 1);
insert into compra values (2, "2022-11-22", 'VISA', 3, 45.000, 1005, null, 1);
insert into compra values (3, "2022-10-30", 'PSE', 4, 50.000, 1005, 3, 1);
insert into compra values (4, "2022-10-01", 'MASTERCARD', 2, 65.000, 1007, null, 2);
insert into compra values (5, "2022-10-24", 'VISA', 2, 25.000, 1004, 5, 3);

insert into compra values (6, "2022-11-5", 'VISA', 2, 25.000, 1005, 5, 2);
insert into compra values (7, "2022-11-10",'MASTERCARD', 2, 25.000, 1005, 5, 3);
insert into compra values (8, "2022-12-24", 'VISA', 2, 25.000, 1005, 5, 4);
insert into compra values (9, "2022-12-28", 'PSE', 2, 15.000, 1005, 5, 5);
*/
 */

/*------------------------------Entrada----------------------------------------------*/
/*Entrada: codigo, columna. fila, precio, codCompra*/
/*
insert into entrada values (1, 3, 10, 6.000, 1);
insert into entrada values (2, 6, 12, 6.000, 2);
insert into entrada values (3, 9, 19, 6.000, 2);
insert into entrada values (4, 1, 10, 6.000, 4);
insert into entrada values (5, 14, 20, 6.000, 1);

 */

/*------------------------------CompraConfiteria--------------------------------------*/


/*CompraConfiteria: codigo, precio, unidades, codCompra, confitCodProducto*/
/*
insert into compra_confiteria values (1, 45.000, 2, 2, 2);
insert into compra_confiteria values (2, 10.000, 3, 2, 1);
insert into compra_confiteria values (3, 25.000, 2, 2, 3);
insert into compra_confiteria values (4, 3.000, 4, 2, 4);
insert into compra_confiteria values (5, 12.000, 2, 2, 5);

 */
