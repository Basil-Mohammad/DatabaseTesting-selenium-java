package CrudMethodTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BasicsCrudMethods {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs;

	@BeforeTest
	public void preTest() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "12345");
	}

	@Test(priority = 1)
	public void AddNewData() throws SQLException {

		stmt = con.createStatement();

		String InsertQuery = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city,country )"
				+ "VALUES (77, 'ABC', 'abedalraheem', 'saqqa', '123-456-7890', 'Amman', 'Amman' ,'jordan')";

		int insertedRow = stmt.executeUpdate(InsertQuery);

		if (insertedRow > 0) {
			System.out.println("data has been added or customer has been added");
		} else {
			System.out.println("sorry the data was not inserted");
		}
	}

	@Test(priority = 2)
	public void UpdateCustomerNumber() throws SQLException {
		stmt = con.createStatement();
		String UpdateQuerySt = "UPDATE customers SET city = 'Irbid' WHERE customerNumber = 77";

		int updatedRow = stmt.executeUpdate(UpdateQuerySt);

		if (updatedRow > 0) {
			System.out.println("user has been updated");
		} else {
			System.out.println("nothing updated");
		}
	}

	@Test(priority = 3)
	public void SelectOneCustomer() throws SQLException {
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM customers where customerNumber=77");

		// Process and print data from the ResultSet
		while (rs.next()) {
			int customerNumber = rs.getInt("customerNumber");
			String customerName = rs.getString("customerName");
			String contactLastName = rs.getString("contactLastName");
			String contactFirstName = rs.getString("contactFirstName");
			String CityName = rs.getString("city");
//
			
		
//			System.out.println("Customer Number: " + customerNumber);
//			System.out.println("Customer Name: " + customerName);
//			System.out.println("Contact Last Name: " + contactLastName);
//			System.out.println("Contact First Name: " + contactFirstName);

			if (CityName.equals("Irbid")) {
			    System.out.println("the update process is correct, the city now is Irbid");
			} else {
			    System.out.println("sorry, something is wrong");
			}

		}

	}

	@Test(priority = 4)
	public void DeleteUser() throws SQLException {
		stmt = con.createStatement();

		String DeleteCommand = "DELETE FROM CUSTOMERS WHERE customerNumber = 77";

		int deletedRow = stmt.executeUpdate(DeleteCommand);

		if (deletedRow > 0) {
			System.out.println("user has been deleted succesfully");
		} else {

			System.out.println("sorry this user has not been deleted");
		}
	}

	@AfterTest
	public void postTest() {

	}
}