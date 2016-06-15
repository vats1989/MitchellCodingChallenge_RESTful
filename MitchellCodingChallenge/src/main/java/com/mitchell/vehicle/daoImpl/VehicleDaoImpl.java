package com.mitchell.vehicle.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mitchell.vehicle.dao.VehicleDao;
import com.mitchell.vehicle.db.Database;
import com.mitchell.vehicle.model.Vehicle;

public class VehicleDaoImpl implements VehicleDao {

	Connection conn = null;
	PreparedStatement pre = null;
	
	@Override
	public Vehicle getVehicle(int id) {
		
		conn = Database.getConnection();
		String query = "SELECT * FROM VEHICLE WHERE id = ?";
		Vehicle vehicle = null;
		
		try {
			pre = conn.prepareStatement(query);
			pre.setInt(1, id);
			ResultSet result = pre.executeQuery();
			
			while(result.next())
				vehicle = new Vehicle(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally	{
				try{
					pre.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return vehicle;
	}

	@Override
	public List<Vehicle> getVehicles() {
		
		conn = Database.getConnection();
		String query = "SELECT * FROM VEHICLE";
		Vehicle vehicle = null;
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		try {
			pre = conn.prepareStatement(query);
			ResultSet result = pre.executeQuery();
			
			while(result.next())	{
				vehicle = new Vehicle(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4));
				vehicles.add(vehicle);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally	{
				try{
					pre.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return vehicles;
	}

	@Override
	public boolean saveVehicle(Vehicle v) {
		
		conn = Database.getConnection();
		String query = "INSERT INTO VEHICLE(YEAR, MAKE, MODEL) VALUES (?,?,?)";
		
		try{
			pre = conn.prepareStatement(query);
			pre.setInt(1, v.getYear());
			pre.setString(2, v.getMake());
			pre.setString(3, v.getModel());
			pre.execute();
		} catch(Exception e)	{
			e.printStackTrace();
			return false;
		} finally	{
			try{
				pre.close();
				conn.close();
			} catch(Exception e)	{
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean updateVehicle(Vehicle v) {
		conn = Database.getConnection();
		String query = "UPDATE VEHICLE "
					 + "SET YEAR = ?,"
					 + "MAKE = ?,"
					 + "MODEL = ?"
					 + "WHERE ID = ?";
		try {
			pre = conn.prepareStatement(query);
			pre.setInt(1, v.getYear());
			pre.setString(2, v.getMake());
			pre.setString(3, v.getModel());
			pre.setInt(4, v.getId());
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
				pre.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public int removeVehicle(int id) {
		int result = 0;
		conn = Database.getConnection();
		String query = "DELETE FROM VEHICLE WHERE ID = ?";
		try{	
			pre = conn.prepareStatement(query);
			pre.setInt(1, id);
			result = pre.executeUpdate();
		} catch(Exception e)	{
			e.printStackTrace();
		} finally {
			try{
				conn.close();
				pre.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public List<Vehicle> getVehicleByProperty(String property, String value) {
		String query;
		boolean flag = false;
		if(property.toLowerCase().equals("make"))
			query = "SELECT * FROM VEHICLE WHERE LOWER(make) = ?";
		else if(property.toLowerCase().equals("model"))
			query = "SELECT * FROM VEHICLE WHERE LOWER(model) = ?";
		else if(property.toLowerCase().equals("year"))	{
			query = "SELECT * FROM VEHICLE WHERE year = ?";
			flag = true;
		}
		else
			return null;
		
		conn = Database.getConnection();
		Vehicle vehicle = null;
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		try {
			pre = conn.prepareStatement(query);
			if(!flag)
				pre.setString(1, value.toLowerCase());
			else
				pre.setInt(1, Integer.parseInt(value));
			ResultSet result = pre.executeQuery();
			
			while(result.next())	{
				vehicle = new Vehicle(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4));
				vehicles.add(vehicle);
			}
		} catch (SQLException e)	{
			e.printStackTrace();
		} finally	{
			try {
				conn.close();
				pre.close();
			} catch(Exception e)	{
				e.printStackTrace();
			}
		}
		return vehicles;
	}
}