package com.employeemanagement.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.employeemanagement.model.Employee;


public class EmployeeDAO {
	private String jdbcURL = "jdbc:mysql://localhost:"
			+ "3306/ems?serverTimezone=UTC";
	private String jdbcUserName = "root";
	private String jdbcPassword = "";
	
	private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO "
			+ "tblemployee (FirstName, LastName, UserName,"
			+ " Password, ContactNo) VALUES (?,?,?,?,?);";
	private static final String SELECT_ALL_EMPLOYEE = "SELECT * FROM tblemployee";
	private static final String DELETE_EMPLOYEE_SQL = "DELETE FROM tblemployee WHERE Id=?";
	private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM tblemployee WHERE Id=?";
	private static final String UPDATE_EMPLOYEE_SQL = "UPDATE tblemployee SET "
			+ "FirstName=?, LastName=?, "
			+ "UserName=?, Password=?, ContactNo=? WHERE Id=?";
	
	//mysql database connection
	protected Connection getConnection()
	{
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,
					jdbcUserName,jdbcPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//Insert Employee
	public void InsertEmployee(Employee objEmployee)
	{
		try(Connection connection = getConnection())
		{
			PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE_SQL);
			ps.setString(1, objEmployee.getFirstname());
			ps.setString(2, objEmployee.getLastname());
			ps.setString(3, objEmployee.getUsername());
			ps.setString(4, objEmployee.getPassword());
			ps.setString(5, objEmployee.getContactno());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Update Employee
	public boolean UpdateEmployee(Employee objEmployee)
	{
		boolean rowUpdate = false;
		try(Connection connection = getConnection())
		{
			PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);
			ps.setString(1, objEmployee.getFirstname());
			ps.setString(2, objEmployee.getLastname());
			ps.setString(3, objEmployee.getUsername());
			ps.setString(4, objEmployee.getPassword());
			ps.setString(5, objEmployee.getContactno());
			ps.setInt(6, objEmployee.getId());
			
			rowUpdate = ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowUpdate;
	}
	//Get All Employee
	public List<Employee> GetAllEmployee()
	{
		List<Employee> EmployeeList = new ArrayList<>();
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_ALL_EMPLOYEE);)
		{
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int Id = rs.getInt("Id");
				String FirstName = rs.getString("FirstName");
				String LastName = rs.getString("LastName");
				String UserName = rs.getString("UserName");
				String Password = rs.getString("Password");
				String ContactNo = rs.getString("ContactNo");
				
				EmployeeList.add(new Employee(Id, FirstName, LastName,
						UserName, Password, ContactNo));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EmployeeList;
	}
	
	//Delete Employee
	public boolean DeleteEmployee(int Id)
	{
		boolean rowDeleted = false;
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.
						prepareStatement(DELETE_EMPLOYEE_SQL);)
		{
			ps.setInt(1, Id);
			rowDeleted = ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowDeleted;
	}
	
	//Get Employee By ID
	public Employee GetEmployeeByID(int Id)
	{
		Employee objEmployee = null;
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);)
		{
			ps.setInt(1, Id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String FirstName = rs.getString("FirstName");
				String LastName = rs.getString("LastName");
				String UserName = rs.getString("UserName");
				String Password = rs.getString("Password");
				String ContactNo = rs.getString("ContactNo");
				objEmployee = new Employee(Id, FirstName, 
						LastName, UserName, Password, ContactNo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objEmployee;
	}
}
