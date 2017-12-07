CREATE TABLE Type_Modes_Of_Operation_Drill (
  id int NOT null AUTO_INCREMENT, 
  name VARCHAR(255) NOT null UNIQUE,
  
  CONSTRAINT pk_Type_Modes_Of_Operation_Drill_id PRIMARY KEY (id)
) ;


INSERT INTO Type_Modes_Of_Operation_Drill (`id`, `name`) VALUES (1, 'Cверление');
INSERT INTO Type_Modes_Of_Operation_Drill (`id`, `name`) VALUES (2, 'Cверление с ударом, cверление');
