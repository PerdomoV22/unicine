/*------------------------------Cliente----------------------------------------------*/
insert into cliente values (1004, "0987", "perdomov.j07@gmail.com", "Juan", 1, "url foto");
insert into cliente values (1005, "1234", "perdomo@gmail.com", "Jose", 1, "url foto");
insert into cliente values (1006, "8907", "diego09@gmail.com", "Diego", 0, "url foto");
insert into cliente values (1007, "3456", "pipe@gmail.com", "Felipe", 1, "url foto");
insert into cliente values (1008, "1029", "alejandro@gmail.com", "Alejandro", 0, "url foto");

/*------------------------------Cliente_Telefonos----------------------------------------------*/
insert into cliente_telefonos values (1004, "3117556502");
insert into cliente_telefonos values (1004, "3148279730");
insert into cliente_telefonos values (1007, "3228484581");
insert into cliente_telefonos values (1006, "3123456789");

/*------------------------------Ciudad----------------------------------------------*/
insert into ciudad values (1, "Armenia");
insert into ciudad values (2, "Medellin");
insert into ciudad values (3, "Santa Marta");
insert into ciudad values (4, "Barranquilla");
insert into ciudad values (5, "Cali");

/*------------------------------Administrador----------------------------------------------*/
insert into administrador values (1090, "9876", "lopezjose@gmail.com", "Jose");

/*------------------------------Admistrador_Teatro----------------------------------------------*/
insert into administrador_teatro values (1012, "3346", "julian09@gmail.com", "Julian");
insert into administrador_teatro values (1013, "1236", "pedro1234@gmail.com", "Pedro");
insert into administrador_teatro values (1014, "8766", "alberto@gmail.com", "Alberto");
insert into administrador_teatro values (1015, "0987", "maria@gmail.com", "Maria");
insert into administrador_teatro values (1016, "0987", "ana@gmail.com", "ana");

/*------------------------------Teatro----------------------------------------------*/
insert into teatro values ("123456789", "Cl. 19 Nte. #13-9", "UniCine", 1090, 1012, 1);
insert into teatro values ("0987", "Cra. 100 #5-169", "UniCine", 1090, 1013, 2);
insert into teatro values ("7643", "Cra. 14 #6-02", "UniCine", 1090, 1014, 3);
insert into teatro values ("1029", "Cra. 6 #3-180", "UniCine", 1090, 1015, 4);
insert into teatro values ("0123", "Cra. 66B #34A-76", "UniCine", 1090, 1016, 5);

/*------------------------------Calificacion----------------------------------------------*/
insert into calificacion values (1, 4, 1004, 5);
insert into calificacion values (2, 2, 1004, 4);
insert into calificacion values (3, 1, 1004, 3);
insert into calificacion values (4, 1, 1004, 2);
insert into calificacion values (5, 5, 1004, 1);

/*------------------------------Pelicula----------------------------------------------*/
insert into pelicula values (1, "pronto", 'terror', "url-imagen", "La huerfana: EL origen", "Julia Stiles, Isabelle Fuhrman, Matthew Finlan ", "La desquiciada Leena Klammer organiza una brillante fuga de un manicomio de Estonia y viaja a Estados Unidos robando la identidad de la hija desaparecida de una familia adinerada.", "url-video");
insert into pelicula values (2, "Cartelera", 'Accion', "url-imagen", "Avatar Relanzamiento", "Sam Worthington, Zoe Saldaña, Stephen Lang, Michelle Rodriguez y Sigourney Weaver.  ", "Vive nuevamente la primera entrega de Avatar (2009) en nuestras salas. AVATAR, ganadora del Premio de la Academia® en 2009", "url-video");
insert into pelicula values (3, "Cartelera", 'Comedia', "url-imagen", "Pasaje al paraiso", "George Clooney, Julia Roberts.", "George Clooney y Julia Roberts se reúnen en la pantalla grande como ex esposos que comparten la misión de evitar que su enamorada hija cometa el mismo error que ellos mismos cometieron una vez.", "url-video");
insert into pelicula values (4, "Cartelera", 'Romance', "url-imagen", "No te preocuoes cariño", "Florence Pugh, Harry Styles, Chris Pine.", "Thriller psicológico centrado en una infeliz ama de casa en la década de 1950 que descubre una inquietante verdad. ", "url-video");
insert into pelicula values (5, "Pronto", 'Terror', "url-imagen", "Fantasma", "Sophie Stevens, Kirstie Steele, Nick Bayly.", "Emma ha sido contratada como cuidadora nocturna del Sr. Cunningham. El que fuera un pilar de la comunidad local tiene demencia y ha sufrido un derrame cerebral, por lo que está solo en su destartalada casa.", "url-video");

/*------------------------------Cupon----------------------------------------------*/
insert into cupon values (1, "", "", "2022-10-10", 0);
insert into cupon values (2, "", "", "2022-11-11", 0);
insert into cupon values (3, "", "", "2022-12-02", 0);
insert into cupon values (4, "", "", "2022-10-08", 0);
insert into cupon values (5, "", "", "2022-11-01", 0);

/*------------------------------Horario----------------------------------------------*/
insert into horario values (1, "LMJV", "2022-10-01", "2022-11-02", "20:30");
insert into horario values (2, "LMMJVSD", "2022-09-29", "2022-11-01", "14:30");
insert into horario values (3, "LMJVS", "2022-11-01", "2022-12-02", "18:00");
insert into horario values (4, "LMMJV", "2022-10-21", "2022-11-22", "22:20");
insert into horario values (5, "LMJVSD", "2022-11-22", "2022-12-23", "17:30");

/*------------------------------PQRS----------------------------------------------*/
insert into pqrs values (1, "Error eleccion de medio de pago", "Elegi erroneamente el medio de pago en el proceso de la compra", 1);
insert into pqrs values (2, "Informacion cupon", "Informacion para redimir cupones", 3);
insert into pqrs values (3, "Informacion confiteria", "Informacion de nuevos productos de la confiteria", 4);
insert into pqrs values (4, "Error de seleccion de asientos", "Seleccione erroneamente los asientos", 5);
insert into pqrs values (5, "Informacion", "Informacion de mis cupones", 2);

/*------------------------------PQRS----------------------------------------------*/
insert into sala values ();



