CREATE TABLE Drill_Images (
  id int NOT null AUTO_INCREMENT, 
  img BLOB NOT null,
  id_product int NOT null,
  
  CONSTRAINT pk_DrillImg_id PRIMARY KEY (id)
) ;


