DROP DATABASE IF EXISTS TECLATS;
CREATE DATABASE TECLATS;
use TECLATS;
DROP TABLE IF EXISTS PRODUCTE;
DROP TABLE IF EXISTS CATEGORIES;
DROP TABLE IF EXISTS PROVEIDOR;
DROP TABLE IF EXISTS MATERIAL;
DROP TABLE IF EXISTS FETS_AMB;
DROP TABLE IF EXISTS ORGANITZAT;
DROP TABLE IF EXISTS EQUIVALENTS;

CREATE TABLE PROVEIDOR (
	NIF	VARCHAR (10) PRIMARY KEY,
    NOM CHAR (50),
    LOCALITZACIO VARCHAR (12)
    
    );

CREATE TABLE PRODUCTE (
	CODI INT (8) AUTO_INCREMENT PRIMARY KEY,
    NOM_PRODUCTE VARCHAR (30),
	MARCA VARCHAR(12),
    PREU VARCHAR (6),
    SWITCH VARCHAR (15),
    MIDA VARCHAR (10),
    RGB VARCHAR (2),
    STOCK VARCHAR(8),
    CODI_PORTA VARCHAR (10),
    FOREIGN KEY (CODI_PORTA) REFERENCES PROVEIDOR (NIF)
    
    );

CREATE TABLE EQUIVALENTS (
	CODI_PRODUCTE int (8),
	FOREIGN KEY (CODI_PRODUCTE) REFERENCES PRODUCTE (CODI),
    CODI_PRODUCTE2 int (8),
	FOREIGN KEY (CODI_PRODUCTE2) REFERENCES PRODUCTE (CODI)
    
    );
    
CREATE TABLE CATEGORIES (
	CODI INT (8) AUTO_INCREMENT PRIMARY KEY,
    NOM VARCHAR(13),
    INFORMACIO VARCHAR (60)
    
    );
    
CREATE TABLE ORGANITZAT (
	CODI_PRODUCTE INT (8) NOT NULL,
    FOREIGN KEY (CODI_PRODUCTE) REFERENCES PRODUCTE (CODI),
    CODI_CATEGORIES INT (8) NOT NULL,
    FOREIGN KEY (CODI_CATEGORIES) REFERENCES CATEGORIES (CODI)
    
    );
    
CREATE TABLE MATERIAL (
	CODI VARCHAR (8) PRIMARY KEY

    );
    
CREATE TABLE FETS_AMB(
	CODI_MATERIAL VARCHAR (8) NOT NULL,
	FOREIGN KEY (CODI_MATERIAL) REFERENCES MATERIAL (CODI),
	CODI_PRODUCTE INT (8) NOT NULL,
	FOREIGN KEY (CODI_PRODUCTE) REFERENCES PRODUCTE (CODI)
        
	);
    
#------------------
#Proveidor
#------------------
INSERT INTO PROVEIDOR VALUES ('289273023R','Logitech','Barcelona');
INSERT INTO PROVEIDOR VALUES ('294629273B','Razer','Girona');
INSERT INTO PROVEIDOR VALUES ('992578222H','Corsair','Lleida');
INSERT INTO PROVEIDOR VALUES ('232345676K','Newskill','Vic');
INSERT INTO PROVEIDOR VALUES ('487234345F','SteelSeries','Manresa');
INSERT INTO PROVEIDOR VALUES ('445678909W','HyperX','Tarragona');
INSERT INTO PROVEIDOR VALUES ('232342346U','NGS','Cordoba');
INSERT INTO PROVEIDOR VALUES ('374949499W','Owlotech','Salamanca');
INSERT INTO PROVEIDOR VALUES ('231847747S','Subblim','Sitges');
INSERT INTO PROVEIDOR VALUES ('637462367E','Coolbox','Valencia');
INSERT INTO PROVEIDOR VALUES ('232342423N','Nilox','Girona');
INSERT INTO PROVEIDOR VALUES ('743983794L','Silver','Ponts');
INSERT INTO PROVEIDOR VALUES ('123456789P','Support','Elo Hell');


#-----------------
#Producte
#-----------------
INSERT INTO PRODUCTE VALUES (1,'Huntsman Tournament Edition','Razer',149.99,'Lineal','mitja','si','182','294629273B');#1
INSERT INTO PRODUCTE VALUES (DEFAULT,'G513 Carbon','Logitech',123.99,'GX brown','gran','si','106','289273023R');#2
INSERT INTO PRODUCTE VALUES (DEFAULT,'K63','Corsair',84.70,'MX red','mitja','si','280','992578222H');#3
INSERT INTO PRODUCTE VALUES (DEFAULT,'Suiko','Newskill',149.99,'Kailh','gran','si','348','232345676K');#4
INSERT INTO PRODUCTE VALUES (DEFAULT,'BlackWidow V3','Razer',148.77,'Green','gran','si','178','294629273B');#5
INSERT INTO PRODUCTE VALUES (DEFAULT,'Pyros Ivory','Newskill',59.21,'Red','petit','si','328','232345676K');#6
INSERT INTO PRODUCTE VALUES (DEFAULT,'G512 Carbon','Logitech',99.97,'GX brown','gran','si','128','289273023R');#7
INSERT INTO PRODUCTE VALUES (DEFAULT,'Nilox Mini','Nilox',14.02,'no','petit','no','176','232342423N');#8
INSERT INTO PRODUCTE VALUES (DEFAULT,'SilverHT','Silver',19.55,'no','petit','no','98','743983794L');#9
INSERT INTO PRODUCTE VALUES (DEFAULT,'Coolbox Cooltouch','Coolbox',20.02,'no','normal','no','58','637462367E');#10
INSERT INTO PRODUCTE VALUES (DEFAULT,'Owlotech Combo','Owlotech',16.99,'no','normal','no','202','374949499W');#11
INSERT INTO PRODUCTE VALUES (DEFAULT,'Subblim Pure Compact','Subblim',14.99,'No','petit','no','156','231847747S');#12
INSERT INTO PRODUCTE VALUES (DEFAULT,'Subblim Dynamic Compact','Subblim',10.62,'no','normal','no','165','231847747S');#13
INSERT INTO PRODUCTE VALUES (DEFAULT,'NGS Cocoa','NGS',6.68,'no','gran','no','145','232342346U');#14
INSERT INTO PRODUCTE VALUES (DEFAULT,'Logitech K400','Logitech',27.45,'no','petit','no','154','289273023R');#15



#-----------------
#Equivalents
#-----------------
INSERT INTO EQUIVALENTS VALUES (2,5);#Switches(BR) i marca 
INSERT INTO EQUIVALENTS VALUES (2,6);#Switches(R)
INSERT INTO EQUIVALENTS VALUES (4,5);#Switches(GR)
INSERT INTO EQUIVALENTS VALUES (7,8);#Petit
INSERT INTO EQUIVALENTS VALUES (7,6);#Petit
INSERT INTO EQUIVALENTS VALUES (11,12);#Petit no RGB 
INSERT INTO EQUIVALENTS VALUES (10,12);#No RGB
INSERT INTO EQUIVALENTS VALUES (10,11);#No RGB
INSERT INTO EQUIVALENTS VALUES (13,14);#No RGB i normal
INSERT INTO EQUIVALENTS VALUES (13,10);#No RGB i normal
INSERT INTO EQUIVALENTS VALUES (14,10);#No RGB i normal


#-----------------
#Materials
#-----------------
INSERT INTO MATERIAL VALUES ('ABS'); #MULTIMEDIA
INSERT INTO MATERIAL VALUES ('PBT'); #GMING
INSERT INTO MATERIAL VALUES ('POM'); #GAMING TRANS 
INSERT INTO MATERIAL VALUES ('PPS'); #SLIM


#-----------------
#Fets Amb
#-----------------
INSERT INTO FETS_AMB VALUES ('PBT',1);
INSERT INTO FETS_AMB VALUES ('PBT',2);
INSERT INTO FETS_AMB VALUES ('PBT',3);
INSERT INTO FETS_AMB VALUES ('PBT',4);
INSERT INTO FETS_AMB VALUES ('PBT',5);
INSERT INTO FETS_AMB VALUES ('PBT',6);
INSERT INTO FETS_AMB VALUES ('ABS',7);
INSERT INTO FETS_AMB VALUES ('ABS',8);
INSERT INTO FETS_AMB VALUES ('ABS',9);
INSERT INTO FETS_AMB VALUES ('PPS',10);
INSERT INTO FETS_AMB VALUES ('PPS',11);
INSERT INTO FETS_AMB VALUES ('PPS',12);
INSERT INTO FETS_AMB VALUES ('ABS',13);
INSERT INTO FETS_AMB VALUES ('ABS',14);
INSERT INTO FETS_AMB VALUES ('ABS',15);



#-----------------
#Categories
#-----------------
INSERT INTO CATEGORIES VALUES (1,'Multimedia','Teclats orientats o a la vida cotidiana');#1
INSERT INTO CATEGORIES VALUES (DEFAULT,'Mecanic','Teclats tenen un interruptor independent	per cada tecla');#2
INSERT INTO CATEGORIES VALUES (DEFAULT,'Gaming','Teclats oreientats a l us en videojocs');#3
INSERT INTO CATEGORIES VALUES (DEFAULT,'Mediacenter','Son teclat que introdueixen un touchpad tactil');#4
INSERT INTO CATEGORIES VALUES (DEFAULT,'Slim','Teclats molt fins oreientats a la portabilitat');#5

#-----------------
#Organitzat
#-----------------
INSERT INTO ORGANITZAT VALUES (1,2);
INSERT INTO ORGANITZAT VALUES (1,3);
INSERT INTO ORGANITZAT VALUES (2,2);
INSERT INTO ORGANITZAT VALUES (2,3);
INSERT INTO ORGANITZAT VALUES (3,2);
INSERT INTO ORGANITZAT VALUES (3,3);
INSERT INTO ORGANITZAT VALUES (4,2);
INSERT INTO ORGANITZAT VALUES (4,3);
INSERT INTO ORGANITZAT VALUES (5,2);
INSERT INTO ORGANITZAT VALUES (5,3);
INSERT INTO ORGANITZAT VALUES (6,2);
INSERT INTO ORGANITZAT VALUES (6,3);
INSERT INTO ORGANITZAT VALUES (7,2);
INSERT INTO ORGANITZAT VALUES (7,3);
INSERT INTO ORGANITZAT VALUES (8,4);
INSERT INTO ORGANITZAT VALUES (9,4);
INSERT INTO ORGANITZAT VALUES (10,4);
INSERT INTO ORGANITZAT VALUES (11,5);
INSERT INTO ORGANITZAT VALUES (12,5);
INSERT INTO ORGANITZAT VALUES (13,5);
INSERT INTO ORGANITZAT VALUES (14,1);
INSERT INTO ORGANITZAT VALUES (15,1);


SELECT *
FROM categories;


SELECT *
FROM equivalents;

SELECT *
FROM fets_amb;

SELECT *
FROM producte;

SELECT *
FROM proveidor;
;

SELECT *
FROM material;

SELECT *
FROM material;

Select *
FROM producte;









    
    
    

    


    
    
    
    
    
    
    
    
    
    

	
	
