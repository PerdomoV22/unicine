/*------------------------------Cliente----------------------------------------------*/
/*Cliente : cedula, contrasenia, correo, nombre, estado , imagen perfil*/
insert into cliente values (1004, "0987", "perdomov.j07@gmail.com", "Juan", 1, "url foto");
insert into cliente values (1005, "1234", "perdomo@gmail.com", "Jose", 1, "url foto");
insert into cliente values (1006, "8907", "diego09@gmail.com", "Diego", 0, "url foto");
insert into cliente values (1007, "3456", "pipe@gmail.com", "Felipe", 1, "url foto");
insert into cliente values (1008, "1029", "alejandro@gmail.com", "Alejandro", 0, "url foto");

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
/*Pelicula: codigo, estado, genero, imagen, nombre, reparto, sinopsis, trailer*/
insert into pelicula values (1, 1, "url-imagen", "La huerfana: EL origen", "Julia Stiles, Isabelle Fuhrman, Matthew Finlan ", "La desquiciada Leena Klammer organiza una brillante fuga de un manicomio de Estonia y viaja a Estados Unidos robando la identidad de la hija desaparecida de una familia adinerada.", "url-video");
insert into pelicula values (2, 1, "url-imagen", "Avatar Relanzamiento", "Sam Worthington, Zoe Saldaña, Stephen Lang, Michelle Rodriguez y Sigourney Weaver.  ", "Vive nuevamente la primera entrega de Avatar (2009) en nuestras salas. AVATAR, ganadora del Premio de la Academia® en 2009", "url-video");
insert into pelicula values (3, 1, "url-imagen", "Pasaje al paraiso", "George Clooney, Julia Roberts.", "George Clooney y Julia Roberts se reúnen en la pantalla grande como ex esposos que comparten la misión de evitar que su enamorada hija cometa el mismo error que ellos mismos cometieron una vez.", "url-video");
insert into pelicula values (4, 1, "url-imagen", "No te preocuoes carinho", "Florence Pugh, Harry Styles, Chris Pine.", "Thriller psicológico centrado en una infeliz ama de casa en la década de 1950 que descubre una inquietante verdad. ", "url-video");
insert into pelicula values (5, 0, "url-imagen", "Fantasma", "Sophie Stevens, Kirstie Steele, Nick Bayly.", "Emma ha sido contratada como cuidadora nocturna del Sr. Cunningham. El que fuera un pilar de la comunidad local tiene demencia y ha sufrido un derrame cerebral, por lo que está solo en su destartalada casa.", "url-video");
insert into pelicula values (6, 1, "url-imagen", "La huerfana", "Julia Stiles, Isabelle Fuhrman, Matthew Finlan ", "La desquiciada Leena Klammer organiza una brillante fuga de un manicomio de Estonia y viaja a Estados Unidos robando la identidad de la hija desaparecida de una familia adinerada.", "url-video");

/*------------------------------Calificacion----------------------------------------------*/
/*Calificacion : codigo, puntuacion, cedCliente, codPelicula*/
insert into calificacion values (1, 4, 1004, 1);
insert into calificacion values (2, 2, 1004, 2);
insert into calificacion values (3, 1, 1006, 2);
insert into calificacion values (4, 1, 1007, 4);
insert into calificacion values (5, 5, 1004, 4);

/*------------------------------Cupon----------------------------------------------*/
/*Cupon : codigo, criterio, descripcion, fechaVencimiento, valorDescuento*/
insert into cupon values (1, "", "", "2022-10-10", 0);
insert into cupon values (2, "", "", "2022-11-11", 0);
insert into cupon values (3, "", "", "2022-12-02", 0);
insert into cupon values (4, "", "", "2022-10-08", 0);
insert into cupon values (5, "", "", "2022-11-01", 0);

/*------------------------------Horario----------------------------------------------*/
/*Horario: codigo, dia, fechafinal, fechainicial, hora*/
insert into horario values (1, "LMJV", "2022-10-01", "2022-11-02", "20:30");
insert into horario values (2, "LMMJVSD", "2022-09-29", "2022-11-01", "14:30");
insert into horario values (3, "LMJVS", "2022-11-01", "2022-12-02", "18:00");
insert into horario values (4, "LMMJV", "2022-10-21", "2022-11-22", "22:20");
insert into horario values (5, "LMJVSD", "2022-11-22", "2022-12-23", "17:30");

/*------------------------------PQRS----------------------------------------------*/
/*PQRS: codigo, asunto, descripcion, cedCliente*/
insert into pqrs values (1, "Error eleccion de medio de pago", "Elegi erroneamente el medio de pago en el proceso de la compra", 1004);
insert into pqrs values (2, "Informacion cupon", "Informacion para redimir cupones", 1005);
insert into pqrs values (3, "Informacion confiteria", "Informacion de nuevos productos de la confiteria", 1006);
insert into pqrs values (4, "Error de seleccion de asientos", "Seleccione erroneamente los asientos", 1007);
insert into pqrs values (5, "Informacion", "Informacion de mis cupones", 1007);

/*------------------------------Distribucion_Sillas----------------------------------------------*/
/*Distribuciion_ sillas: codigo, columnas, esquema, filas, total_sillas*/
insert into distribucion_sillas values (1, 14, "", 14, 180);
insert into distribucion_sillas values (2, 14, "", 14, 190);
insert into distribucion_sillas values (3, 14, "", 14, 170);
insert into distribucion_sillas values (4, 14, "", 14, 180);
insert into distribucion_sillas values (5, 14, "", 14, 160);

/*------------------------------Sala----------------------------------------------*/
/*Sala: numero_sala, nombre, distriSillasCodigo, teatroNit*/
insert into sala values (1, "sala 1", 1, 1);
insert into sala values (2, "sala 2", 2, 2);
insert into sala values (3, "sala 3", 3, 3);
insert into sala values (4, "sala 4", 4, 4);
insert into sala values (5, "sala 5", 5, 5);

/*------------------------------CuponClinete----------------------------------------------*/
/*CuponCliente: codgio, estado, cedCliente, codCupon*/
insert into cupon_cliente values (1, 0, 1004, 1);
insert into cupon_cliente values (2, 1, 1005, 2);
insert into cupon_cliente values (3, 1, 1006, 3);
insert into cupon_cliente values (4, 1, 1004, 4);
insert into cupon_cliente values (5, 0, 1008, 5);

/*------------------------------Confiteria----------------------------------------------*/
/*Confiteria: codProducto, categoria, imagen,  nombre, precio*/
insert into confiteria values (1, 'Bebidas', "fotobebida","CocaCola", 10000);
insert into confiteria values (2, 'Combos', "fotocombos" , "Combo Mayor", 45000);
insert into confiteria values (3, 'Crispetas', "fotocrispetas","Crispetas 250gr", 25000);
insert into confiteria values (4, 'Dulceria', "fotodulceria", "Chocolatina", 3000);
insert into confiteria values (5, 'Hot_Dogs',  "fotoHotdogs","Perro sencillo", 12000);

/*------------------------------Funcion----------------------------------------------*/
/*Funcion: codigo, precio, horaioCod, codPelicula, sala_numero_sala*/
insert into funcion values (1, 6.500, 1, 1, 1);
insert into funcion values (2, 8.500, 2, 2, 2);
insert into funcion values (3, 7.500, 3, 3, 3);
insert into funcion values (4, 6.500, 4, 4, 4);
insert into funcion values (5, 7.500, 5, 5, 5);

/*------------------------------Compra----------------------------------------------*/
/*Compra: codgio, fechaCompra, medioPago, numBoletas, valorTotal, cedCliente, codCupon, codFuncion */
insert into compra values (1, "2022-10-14", 'PSE', 2, 45.000, 1004, 1, 1);
insert into compra values (2, "2022-11-22", 'VISA', 3, 45.000, 1005, null, 1);
insert into compra values (3, "2022-10-30", 'PSE', 4, 50.000, 1005, 3, 1);
insert into compra values (4, "2022-10-01", 'MASTERCARD', 2, 65.000, 1007, null, 4);
insert into compra values (5, "2022-10-24", 'VISA', 2, 25.000, 1004, 5, 5);

/*------------------------------Entrada----------------------------------------------*/
/*Entrada: codigo, columna. fila, precio, codCompra*/
insert into entrada values (1, 3, 10, 6.000, 1);
insert into entrada values (2, 6, 12, 6.000, 2);
insert into entrada values (3, 9, 19, 6.000, 1);
insert into entrada values (4, 1, 10, 6.000, 4);
insert into entrada values (5, 14, 20, 6.000, 1);

/*------------------------------CompraConfiteria--------------------------------------*/
/*CompraConfiteria: codigo, precio, unidades, codCompra, confitCodProducto*/
insert into compra_confiteria values (1, 45.000, 2, 2, 2);
insert into compra_confiteria values (2, 10.000, 3, 2, 1);
insert into compra_confiteria values (3, 25.000, 2, 3, 3);
insert into compra_confiteria values (4, 3.000, 4, 3, 4);
insert into compra_confiteria values (5, 12.000, 2, 3, 5);