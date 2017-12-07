package com.denis.parserbackend.dto;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Drill_Images")
public class DrillImage implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private byte[] img;
	private int id_product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + id_product;
		result = prime * result + Arrays.hashCode(img);
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
		DrillImage other = (DrillImage) obj;
		if (id != other.id)
			return false;
		if (id_product != other.id_product)
			return false;
		if (!Arrays.equals(img, other.img))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DrillImg [id=" + id + ", img=" + Arrays.toString(img) + ", id_product=" + id_product + "]";
	}

}
