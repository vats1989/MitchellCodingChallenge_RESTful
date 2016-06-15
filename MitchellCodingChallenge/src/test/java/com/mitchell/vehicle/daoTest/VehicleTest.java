package com.mitchell.vehicle.daoTest;

import org.testng.annotations.Test;

import com.mitchell.vehicle.dao.VehicleDao;
import com.mitchell.vehicle.daoImpl.VehicleDaoImpl;
import com.mitchell.vehicle.model.Vehicle;

@Test(groups = "VehicleTest")
public class VehicleTest {

	VehicleDao vehicleDao = new VehicleDaoImpl();
	
	@Test
	public void getVehicles() {
		assert vehicleDao.getVehicles().size() > 0;
	}
	
	@Test
	public void getVehicle()	{
		int id = 20;
		assert vehicleDao.getVehicle(id) != null;
	}
	
	@Test
	public void addVehicle()	{
		Vehicle v = new Vehicle(2020, "Audi", "A4");
		assert vehicleDao.saveVehicle(v) == true;
	}
	
	@Test
	public void getVehicleByProperty()	{
		assert vehicleDao.getVehicleByProperty("year", "2020").size() > 0;
	}
	
	/*
	@Test
	public void removeVehicle()	{
		assert vehicleDao.removeVehicle(21) == 1;
	}
	*/
}