package com.mitchell.vehicle.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="vehicle")
public class Vehicle{
	
	/*
	 * We can also define below variable with @XmlElement annotations.
	 * In that case we don't need to give setter methods. 
	 */
	
	private int Id;
	private int Year;
	private String Make;
	private String Model;
	
	public Vehicle() {}
	
	public Vehicle(int year, String make, String model)	{
		this.Year = year;
		this.Make = make;
		this.Model = model;
	}
	
	public Vehicle(int id, int year, String make, String model)	{
		this.Id = id;
		this.Year = year;
		this.Make = make;
		this.Model = model;
	}

	public int getId() {
		return Id;
	}

	@XmlElement
	public void setId(int id) {
		Id = id;
	}

	public int getYear() {
		return Year;
	}

	@XmlElement
	public void setYear(int year) {
		Year = year;
	}

	public String getMake() {
		return Make;
	}

	@XmlElement
	public void setMake(String make) {
		Make = make;
	}

	public String getModel() {
		return Model;
	}

	@XmlElement
	public void setModel(String model) {
		Model = model;
	}
}