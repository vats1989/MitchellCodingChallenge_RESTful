# Mitchell Coding Challenge RESTful

RESTful Web Service for a Vehicle Entity

Technology stack: Java, Jersey 1.19, Maven 4.0.0, Eclipse Luna, Apache Tomcat v8.0, PostgreSQL, 
				  JDBC, TestNG

Run program:
	1.Copy MitchellCodingChallenge-0.0.1-SNAPSHOT.war from target folder of project into your 
	%CATALINA_HOME%/webapps folder.
	2.Start tomcat server using startup.bat
	3.Enter http://localhost:8080/MitchellCodingChallenge-0.0.1-SNAPSHOT/VehicleService/vehicles
	  in your web browser.

Test web services:
	I used Postman in Chrome to test web services for different requests.
	
	1) GET vehicles - get all vehicles
	->URL - http://localhost:8080/MitchellCodingChallenge/VehicleService/vehicles
	->This will retrieve all vehicles.
	
	2) GET vehicles/{id} - to get vehicle by id
	->URL - http://localhost:8080/MitchellCodingChallenge/VehicleService/vehicles/25
	->This will get vehicle by given id.
	
	3) POST vehicles - to create new vehicle
	->URL - http://localhost:8080/MitchellCodingChallenge/VehicleService/vehicles/2005/honda/civic
	->In post request, we need to pass three parameters as shown in above url:
		year, make, model.
	->It will return success if record created successfully and failure if not.
	
	4) PUT vehicles - to update any vehicle
	->URL - http://localhost:8080/MitchellCodingChallenge/VehicleService/vehicles/22/2030/audi/q3
	-> In put request, we need to pass four parameters in sequence as shown in above url.
		vehicle id, year, make, model
	-> It will return success if record updated successfully and failure if not.
	-> It will give "<result>Given Vehicle Not Present. Can not Update !!!</result>" if requested vehicle record not found.
	
	5) DELETE vehicles/{id} - to delete any vehicle
	->URL - http://localhost:8080/MitchellCodingChallenge/VehicleService/vehicles/22
	->It will return success if requested vehicle record gets deleted and failure if not.
	
	
	Automated Test Cases using TestNG:
		1) Test case to check if there are more than 0 records of vehicle.
		2) Test case to check if vehicle of id 20 is present or not.
		3) Test case to check if vehicle record is getting created or not.
		4) Test case to retrieve vehicle by its property.
	
	OPTIONAL TASK:
	
	1) Add validation to your service:
		-> year must be between 1950 and 2050.
		-> I have added above validation for create and update vehicle services.
		-> If you pass year which is < 1950 or > 2050 then it will return "Invalid Year" error message.
		Sample:
			http://localhost:8080/MitchellCodingChallenge/VehicleService/vehicles/22/2055/audi/q3
	
	2) Add filtering to your service:
		->The GET vehicles route should support filtering vehicles based on one or more vehicle properties.
		->I have added addition route for filtering vehicles based on its property. i.e. make, model, year
		->For that URL form should be:
			http://localhost:8080/MitchellCodingChallenge/VehicleService/vehicles/{property}/{value}
			
		-> Retrieving all vehicles where the Make is 'Toyota'
			http://localhost:8080/MitchellCodingChallenge/VehicleService/vehicles/make/toyota
		-> Retrieving all vehicles where the model is Accord
			http://localhost:8080/MitchellCodingChallenge/VehicleService/vehicles/model/accord
		-> Retriving all vehicles of the year 2020
			http://localhost:8080/MitchellCodingChallenge/VehicleService/vehicles/year/2020
		->If you entered property apart from make, model and year, it will return null.	
			
	Additional Notes:
		->I have used Maven to build this project. Maven makes the build process easy and we can also manage dependency easily.
		->PostgreSQL database used to store all vehicle records.
		->I used TestNG for some automated testing.

	Additional Files:
		MitchellCodingChallenge.sql
		
	Source Files:
		->src/main/java
			->package - com.mitchell.vehicle.dao
				->VehicleDao.java
				->Interface (contract) which provides all methods related to services of Vehicle class.
			->package - com.mitchell.vehicle.daoImpl
				->VehicleDaoImpl.java
				->Vehicle Implementation class which implements above interface and provide actual logic.
			->package - com.mitchell.vehicle.db
				->Database.java
				->Class to register database driver and get database connection object.
			->package - com.mitchell.vehicle.model
				->Vehicle.java
				->Vehicle entity model class.
			->package - com.mitchell.vehicle.restservices
				->VehicleServices.java
				->Rest service class which handles different requests.
		
		->src/test/java
			->package - com.mitchell.vehicle.daoTest
				->VehicleTest.java
				->Test class which provide methods for automated testing.
