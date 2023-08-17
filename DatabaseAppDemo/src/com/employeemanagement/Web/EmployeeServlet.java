package com.employeemanagement.Web;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employeemanagement.DAO.EmployeeDAO;
import com.employeemanagement.model.Employee;
import java.util.List;

public class EmployeeServlet extends HttpServlet {
	private EmployeeDAO employeeDAO;
	
	public EmployeeServlet()
	{
		employeeDAO = new EmployeeDAO();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/new": 
			ShowNewForm(req, resp);
			break;
		case "/Insert":
			InsertEmployee(req, resp);
			break;
		case "/list":
			listEmployee(req,resp);
			break;
		case "/delete":
			DeleteEmployee(req,resp);
			break;
		case "/edit":
			showEditForm(req,resp);
			break;
		case "/Update":
			UpdateEmployee(req,resp);
			break;
		default:
			listEmployee(req, resp);
			break;
		}
	}

	private void UpdateEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("hdnid"));
		String FirstName = req.getParameter("txtFirstName");
		String LastName = req.getParameter("txtLastName");
		String UserName = req.getParameter("txtUserName");
		String Password = req.getParameter("txtPassword");
		String ContactNo = req.getParameter("txtContactNo");
		Employee objEmployee = new Employee(id, FirstName, LastName,
				UserName, Password, ContactNo);
		employeeDAO.UpdateEmployee(objEmployee);
		resp.sendRedirect("list");
		
	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Employee objEmployee = employeeDAO.GetEmployeeByID(id);
		req.setAttribute("employee", objEmployee);
		RequestDispatcher rd = req.getRequestDispatcher("employee_form.jsp");
		rd.forward(req, resp);
	}

	private void DeleteEmployee(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean IsDeleted = employeeDAO.DeleteEmployee(id);
		if(IsDeleted)
		{
			try {
				resp.sendRedirect("list");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void listEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Employee> listEmployee = employeeDAO.GetAllEmployee();
		req.setAttribute("listEmployee", listEmployee);
		RequestDispatcher rd = req.getRequestDispatcher("employee-list.jsp");
		rd.forward(req, resp);
		
	}

	protected void ShowNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("employee_form.jsp");
		rd.forward(req, resp);
	}
	
	private void InsertEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		String FirstName = req.getParameter("txtFirstName");
		String LastName = req.getParameter("txtLastName");
		String UserName = req.getParameter("txtUserName");
		String Password = req.getParameter("txtPassword");
		String ContactNo = req.getParameter("txtContactNo");
		
		Employee objEmployee = new Employee(FirstName, LastName, UserName, Password, ContactNo);
		employeeDAO.InsertEmployee(objEmployee);
		resp.getWriter().print("Insert Successfully");
	}
}
