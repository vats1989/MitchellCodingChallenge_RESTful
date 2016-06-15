package com.mitchell.vehicle.dao;

import java.util.List;

import com.mitchell.vehicle.model.Vehicle;

public interface VehicleDao {
	
	Vehicle getVehicle(int id);
	
	List<Vehicle> getVehicles();
	
	boolean saveVehicle(Vehicle v);
	
	boolean updateVehicle(Vehicle v);
	
	int removeVehicle(int id);

	List<Vehicle> getVehicleByProperty(String property, String value);
}