create database if not exists donkey_race;

use donkey_race;

create table if not exists dueños(
	ID int primary key auto_increment,
	Nombre varchar(100),
	Cedula varchar(100) unique
);

create table if not exists burros(
	ID int primary key auto_increment,
	Nombre varchar(100) not null,
	Edad varchar(2) not null,
	Raza varchar(50) not null,
	ID_Dueño int,
	foreign key (ID_Dueño) references dueños(ID)
);

create table if not exists participantes(
	ID int primary key auto_increment,
	Nombre varchar(100) not null,
	Cedula varchar(100) not null unique,
	SaldoDisponible decimal(10, 2) not null default 0
);

create table if not exists competencias(
	ID int primary key auto_increment,
	Fecha date not null,
	Lugar varchar(100) not null,
    Estado ENUM("Programada", "Finalizada", "Cancelada") default "Programada",
    ID_Ganador int,
    foreign key (ID_Ganador) references burros(ID)
);


create table if not exists competencias_burros (
	ID int primary key auto_increment,
    ID_Burro int,
    ID_Competencia int,
    foreign key (ID_Burro) references burros(ID),
    foreign key (ID_Competencia) references competencias(ID)
);

create table if not exists apuestas(
    ID int primary key auto_increment,
    ID_Participante int,
    ID_Burro int,
    ID_Competencia int,
    MontoApostado decimal(10, 2) not null,
    foreign key (ID_Participante) references participantes(ID),
    foreign key (ID_Burro) references burros(ID),
    foreign key (ID_Competencia) references competencias(ID)
);
