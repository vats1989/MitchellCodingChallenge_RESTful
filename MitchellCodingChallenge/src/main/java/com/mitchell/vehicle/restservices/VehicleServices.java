package com.mitchell.vehicle.restservices;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.mitchell.vehicle.dao.VehicleDao;
import com.mitchell.vehicle.daoImpl.VehicleDaoImpl;
import com.mitchell.vehicle.model.Vehicle;

@Path("VehicleService")
public class VehicleServices {

	VehicleDao vehicleDao = new VehicleDaoImpl();
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String FAILURE_RESULT="<result>failure</result>";
	private static final String INVALID_YEAR = "<result>Invalid Year</result>";
	private static final String UPDATE_FAILURE = "<result>Given Vehicle Not Present. Can not Update !!!</result>";
		
	@GET
	@Path("/vehicles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vehicle> getVehicles(){
		return vehicleDao.getVehicles();
	}
	
	@GET
	@Path("/vehicles/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Vehicle getVehicle(@PathParam("id") int id){
		return vehicleDao.getVehicle(id);
	}
	

	@GET
	@Path("/vehicles/{property}/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vehicle> getVehicleByProperty(@PathParam("property") String property, 
											  @PathParam("value") String value)	{
		/*
		if(!property.toLowerCase().equals("make") && 
		   !property.toLowerCase().equals("model") && 
		   !property.toLowerCase().equals("year"))
			return null;
		*/
		return vehicleDao.getVehicleByProperty(property, value);
	}
	
	@POST
	@Path("/vehicles/{year}/{make}/{model}")
	@Produces(MediaType.APPLICATION_XML)
	public String addVehicle(@PathParam("year") int year, @PathParam("make") String make, @PathParam("model") String model)	{
		if(year < 1950 || year > 2050)
			return INVALID_YEAR;
		boolean flag = vehicleDao.saveVehicle(new Vehicle(year, make, model));
		if(flag)
			return SUCCESS_RESULT;
		return FAILURE_RESULT;
	}
	
	@PUT
	@Path("/vehicles/{id}/{year}/{make}/{model}")
	@Produces(MediaType.APPLICATION_XML)
	public String updateVehicle(@PathParam("id") int id, @PathParam("year") int year, @PathParam("make") String make, @PathParam("model") String model)	{
		if(year < 1950 || year > 2050)
			return INVALID_YEAR;
		List<Vehicle> list = vehicleDao.getVehicles();
		boolean isPresent = false;
		for(Vehicle v:list)
			if(v.getId() == id)	{
				isPresent = true;
				break;
			}
		if(!isPresent)
			return UPDATE_FAILURE;
		boolean flag = vehicleDao.updateVehicle(new Vehicle(id, year, make, model));
		if(flag)
			return SUCCESS_RESULT;
		return FAILURE_RESULT;
	}
	
	@DELETE
	@Path("/vehicles/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String deleteVehicle(@PathParam("id") int id)	{
		int result = vehicleDao.removeVehicle(id);
		if(result == 1)
			return SUCCESS_RESULT;
		return FAILURE_RESULT;
	}
}