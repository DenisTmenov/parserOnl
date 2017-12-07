package com.denis.parserbackend.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Drill")
public class Drill implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String model_name;
	// @Column(name = "name", table = "brand")
	private int brand_id;
	private double price;
	// @Column(name = "type_name", table = "type_drill")
	private int type_id;
	// @Column(name = "type_modes_of_operation", table =
	// "type_modes_of_operation_drill")
	private int type_modes_of_operation_id;
	private String power;
	// @Column(name = "type_name", table = "type_instrument")
	private int power_supply_id;
	// @Column(name = "type_name", table = "type_cartridge_drill")
	private int cartridge_id;
	private String rotational_speed;
	private String torque;
	private String diameter_of_cartridge;
	private String maximum_drilling_diameter_metal;
	private String maximum_drilling_diameter_wood;
	private String weight;
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public int getType_modes_of_operation_id() {
		return type_modes_of_operation_id;
	}

	public void setType_modes_of_operation_id(int type_modes_of_operation_id) {
		this.type_modes_of_operation_id = type_modes_of_operation_id;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public int getPower_supply_id() {
		return power_supply_id;
	}

	public void setPower_supply_id(int power_supply_id) {
		this.power_supply_id = power_supply_id;
	}

	public int getCartridge_id() {
		return cartridge_id;
	}

	public void setCartridge_id(int cartridge_id) {
		this.cartridge_id = cartridge_id;
	}

	public String getRotational_speed() {
		return rotational_speed;
	}

	public void setRotational_speed(String rotational_speed) {
		this.rotational_speed = rotational_speed;
	}

	public String getTorque() {
		return torque;
	}

	public void setTorque(String torque) {
		this.torque = torque;
	}

	public String getDiameter_of_cartridge() {
		return diameter_of_cartridge;
	}

	public void setDiameter_of_cartridge(String diameter_of_cartridge) {
		this.diameter_of_cartridge = diameter_of_cartridge;
	}

	public String getMaximum_drilling_diameter_metal() {
		return maximum_drilling_diameter_metal;
	}

	public void setMaximum_drilling_diameter_metal(String maximum_drilling_diameter_metal) {
		this.maximum_drilling_diameter_metal = maximum_drilling_diameter_metal;
	}

	public String getMaximum_drilling_diameter_wood() {
		return maximum_drilling_diameter_wood;
	}

	public void setMaximum_drilling_diameter_wood(String maximum_drilling_diameter_wood) {
		this.maximum_drilling_diameter_wood = maximum_drilling_diameter_wood;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + brand_id;
		result = prime * result + cartridge_id;
		result = prime * result + ((diameter_of_cartridge == null) ? 0 : diameter_of_cartridge.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((maximum_drilling_diameter_metal == null) ? 0 : maximum_drilling_diameter_metal.hashCode());
		result = prime * result
				+ ((maximum_drilling_diameter_wood == null) ? 0 : maximum_drilling_diameter_wood.hashCode());
		result = prime * result + ((model_name == null) ? 0 : model_name.hashCode());
		result = prime * result + ((power == null) ? 0 : power.hashCode());
		result = prime * result + power_supply_id;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((rotational_speed == null) ? 0 : rotational_speed.hashCode());
		result = prime * result + ((torque == null) ? 0 : torque.hashCode());
		result = prime * result + type_id;
		result = prime * result + type_modes_of_operation_id;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Drill other = (Drill) obj;
		if (brand_id != other.brand_id)
			return false;
		if (cartridge_id != other.cartridge_id)
			return false;
		if (diameter_of_cartridge == null) {
			if (other.diameter_of_cartridge != null)
				return false;
		} else if (!diameter_of_cartridge.equals(other.diameter_of_cartridge))
			return false;
		if (id != other.id)
			return false;
		if (maximum_drilling_diameter_metal == null) {
			if (other.maximum_drilling_diameter_metal != null)
				return false;
		} else if (!maximum_drilling_diameter_metal.equals(other.maximum_drilling_diameter_metal))
			return false;
		if (maximum_drilling_diameter_wood == null) {
			if (other.maximum_drilling_diameter_wood != null)
				return false;
		} else if (!maximum_drilling_diameter_wood.equals(other.maximum_drilling_diameter_wood))
			return false;
		if (model_name == null) {
			if (other.model_name != null)
				return false;
		} else if (!model_name.equals(other.model_name))
			return false;
		if (power == null) {
			if (other.power != null)
				return false;
		} else if (!power.equals(other.power))
			return false;
		if (power_supply_id != other.power_supply_id)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (rotational_speed == null) {
			if (other.rotational_speed != null)
				return false;
		} else if (!rotational_speed.equals(other.rotational_speed))
			return false;
		if (torque == null) {
			if (other.torque != null)
				return false;
		} else if (!torque.equals(other.torque))
			return false;
		if (type_id != other.type_id)
			return false;
		if (type_modes_of_operation_id != other.type_modes_of_operation_id)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Drill [id=" + id + ", model_name=" + model_name + ", brand_id=" + brand_id + ", price=" + price
				+ ", type_id=" + type_id + ", type_modes_of_operation_id=" + type_modes_of_operation_id + ", power="
				+ power + ", power_supply_id=" + power_supply_id + ", cartridge_id=" + cartridge_id
				+ ", rotational_speed=" + rotational_speed + ", torque=" + torque + ", diameter_of_cartridge="
				+ diameter_of_cartridge + ", maximum_drilling_diameter_metal=" + maximum_drilling_diameter_metal
				+ ", maximum_drilling_diameter_wood=" + maximum_drilling_diameter_wood + ", weight=" + weight + ", url="
				+ url + "]";
	}

}
