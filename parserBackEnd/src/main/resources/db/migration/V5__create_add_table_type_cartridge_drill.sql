CREATE TABLE Type_Cartridge_Drill (
  id int NOT null AUTO_INCREMENT, 
  name VARCHAR(255) NOT null UNIQUE,
  
  CONSTRAINT pk_Type_Cartridge_Drill_id PRIMARY KEY (id)
) ;


INSERT INTO Type_Cartridge_Drill (`id`, `name`) VALUES (1, 'Быстрозажимной');
INSERT INTO Type_Cartridge_Drill (`id`, `name`) VALUES (2, 'Кулачковый (под ключ)');
INSERT INTO Type_Cartridge_Drill (`id`, `name`) VALUES (3, 'Под биты');
INSERT INTO Type_Cartridge_Drill (`id`, `name`) VALUES (4, 'Квадрат 1"');
INSERT INTO Type_Cartridge_Drill (`id`, `name`) VALUES (5, 'Квадрат 1/2"');
INSERT INTO Type_Cartridge_Drill (`id`, `name`) VALUES (6, 'Квадрат 3/4"');
