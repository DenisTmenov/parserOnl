CREATE TABLE Type_Instrument (
  id int NOT null AUTO_INCREMENT, 
  name VARCHAR(255) NOT null UNIQUE,
  
  CONSTRAINT pk_Type_Instrument_id PRIMARY KEY (id)
) ;


INSERT INTO Type_Instrument (`id`, `name`) VALUES (1, 'Аккумуляторная батарея');
INSERT INTO Type_Instrument (`id`, `name`) VALUES (2, 'Сеть 220V');