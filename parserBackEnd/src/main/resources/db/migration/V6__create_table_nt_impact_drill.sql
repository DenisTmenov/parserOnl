CREATE TABLE Drill (
  id int NOT NULL AUTO_INCREMENT,
  model_name VARCHAR(255) NOT NULL,
  brand_id INT,
  price DOUBLE,
  type_id INT,
  type_modes_of_operation_id INT,
  power VARCHAR(255),
  power_supply_id INT ,
  cartridge_id INT ,
  rotational_speed VARCHAR(255),
  torque VARCHAR(255),
  diameter_of_cartridge VARCHAR(255),
  maximum_Drilling_diameter_metal VARCHAR(255),
  maximum_Drilling_diameter_wood VARCHAR(255),
  weight VARCHAR(255),
  url TEXT,

  CONSTRAINT fk_Drill_id  PRIMARY KEY (id, model_name, brand_id),
  
  CONSTRAINT fk_Drill_brand_id  FOREIGN KEY (brand_id) REFERENCES Brand (id),
  CONSTRAINT fk_Drill_type_id  FOREIGN KEY (type_id) REFERENCES Type_Drill (id),
  CONSTRAINT fk_Drill_type_modes_of_operation_id  FOREIGN KEY (type_modes_of_operation_id) REFERENCES Type_Modes_Of_Operation_Drill (id),
  CONSTRAINT fk_Drill_power_supply_id  FOREIGN KEY (power_supply_id) REFERENCES Type_Instrument (id),
  CONSTRAINT fk_Drill_cartridge_id  FOREIGN KEY (cartridge_id) REFERENCES Type_Cartridge_Drill (id)
) ;