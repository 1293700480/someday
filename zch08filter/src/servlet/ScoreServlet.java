package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dep2ProDao;
import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.ProjectDao;
import dao.ScoreDao;
import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import util.Constant;
import util.Pagination;

public class ScoreServlet extends HttpServlet {
	private static final String path = "WEB-INF/score/";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//   不用过滤器时的登录判断
//		HttpSession session = request.getSession();
//		if (session.getAttribute("user") == null) {
//			try {
//				response.sendRedirect("user?type=showLogin");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		} else {
		String type = request.getParameter("type");
		if (type == null) {
			search(request, response);

		} else if ("showadd".equals(type)) {
			showadd(request, response);

		} else if ("add".equals(type)) {
			add(request, response);

		} else if ("showupdate".equals(type)) {
			showupdate(request, response);

		} else if ("input".equals(type)) {
			input(request, response);

		} else if ("delete".equals(type)) {
			delete(request, response);
		} 

	}
//	}
	private void search(HttpServletRequest request, HttpServletResponse response) {
		EmployeeDao empDao = new EmployeeDao();
		DepartmentDao depDao = new DepartmentDao();
		ProjectDao proDao=new ProjectDao();
		Score condition = new Score();
		ScoreDao scoDao=new ScoreDao();
		String name = request.getParameter("name");
		
		String depName="";
		if (request.getParameter("depName") != null && !"".equals(request.getParameter("depName"))) {
			depName = request.getParameter("depName");
		}
		String proName = "";
		if (request.getParameter("proName") != null && !"".equals(request.getParameter("proName"))) {
			proName =request.getParameter("proName");
		}
		int value = -1;
		if (request.getParameter("value") != null && !"".equals(request.getParameter("value"))) {
			value = Integer.parseInt(request.getParameter("value"));
		}
		
		
		condition.setValue(value);
       Employee emp=new Employee();
		
		
		Project pro=new Project();
		pro.setName(proName);
		condition.setPro(pro);
		
		Department dep = new Department();
		dep.setName(depName);
		emp.setDep(dep);
		
		emp.setName(name);
	
		condition.setEmp(emp);
		
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}

		int count = scoDao.searchCount(condition);
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Score> scoList = scoDao.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		List<Employee> list = empDao.search();
		List<Department> depList = depDao.search();

		List<Project> proList = proDao.search();
	
		request.setAttribute("depList", depList);
		request.setAttribute("p", p);
		request.setAttribute("scoList", scoList);
		request.setAttribute("c", condition);
		request.setAttribute("list", list);
		request.setAttribute("proList", proList);
		try {
			request.getRequestDispatcher(path + "list.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void showupdate(HttpServletRequest request, HttpServletResponse response) {

		try {
			int id = Integer.parseInt(request.getParameter("id"));

			EmployeeDao empDao = new EmployeeDao();
			Employee emp = empDao.search(id);
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			
			
			request.setAttribute("emp", emp);

			request.getRequestDispatcher(path + "update.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private void input(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			int id=Integer.parseInt(request.getParameter("id"));
			int scoValue = Integer.parseInt(request.getParameter("scoValue"));
			
		
			ScoreDao scoDao = new ScoreDao();
			Score sco = new Score();
			
			sco.setValue(scoValue);
			boolean flag=false;
			if(id==0){
				Project pro=new Project();
				Employee emp=new Employee();
				int empId = Integer.parseInt(request.getParameter("empId"));
				int proId = Integer.parseInt(request.getParameter("proId"));
				emp.setId(empId);
				sco.setEmp(emp);
				
			pro.setId(proId);
			sco.setPro(pro);
	id=scoDao.add(sco);
		if(id>0) {
			
			flag=true;
		}
		sco.setId(id);
			}else {
				sco.setId(id);
			flag=	scoDao.update(sco);
			}
			Score sco1=scoDao.search(id);
			if(flag) {
				
		JSON json=	JSONObject.fromObject(sco1);
		out.print(json);
			}else {
				out.print(false);
				
			}
			
			
		
			
		
		
			
			
			
//			if(!"".equals(request.getParameter("depId")))
//				
//			{ depId = Integer.parseInt(request.getParameter("depId"));
//			}
//			
			
			
		
		

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		try {

			int id = Integer.parseInt(request.getParameter("id"));
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.delete(id);
			response.sendRedirect("emp");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			Employee emp = new Employee();
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			Integer depId=null;
			if(!"".equals(request.getParameter("depId")))
			
			{ depId = Integer.parseInt(request.getParameter("depId"));
			}
			emp.setName(name);
			emp.setAge(age);
			emp.setSex(sex);
			Department dep = new Department();
			dep.setId(depId);
			emp.setDep(dep);

			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.add(emp);
			response.sendRedirect("emp");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showadd(HttpServletRequest request, HttpServletResponse response) {
		try {
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher(path + "add.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public void show(HttpServletRequest request, HttpServletResponse response) {
	// try {
	//
	// EmployeeDao empDao = new EmployeeDao();
	// int ye = 1;
	// if (request.getParameter("ye") != null) {
	// ye = Integer.parseInt(request.getParameter("ye"));
	// }
	//
	// int count = empDao.searchCount();
	// Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE,
	// Constant.EMP_NUM_OF_PAGE);
	//
	// List<Employee> list = empDao.search(p.getBegin(), Constant.EMP_NUM_IN_PAGE);
	// request.setAttribute("p", p);
	// request.setAttribute("list", list);
	// request.getRequestDispatcher(path+"list.jsp").forward(request, response);
	// } catch (ServletException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
