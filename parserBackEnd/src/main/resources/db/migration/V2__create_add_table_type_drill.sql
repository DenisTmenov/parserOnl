CREATE TABLE Type_Drill (
  id int NOT null AUTO_INCREMENT, 
  name VARCHAR(255) NOT null UNIQUE,
  
  CONSTRAINT pk_Type_Drill_id PRIMARY KEY (id)
) ;

INSERT INTO Type_Drill (`id`, `name`) VALUES (1, 'Дрель алмазного сверления');
INSERT INTO Type_Drill (`id`, `name`) VALUES (2, 'Безударная дрель');
INSERT INTO Type_Drill (`id`, `name`) VALUES (3, 'Ударная дрель');
INSERT INTO Type_Drill (`id`, `name`) VALUES (4, 'Угловая дрель');
INSERT INTO Type_Drill (`id`, `name`) VALUES (5, 'Дрель-шуруповерт');
