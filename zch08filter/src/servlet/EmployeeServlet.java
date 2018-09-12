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

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;
import entity.Employee;

import net.sf.json.JSONArray;
import util.Constant;
import util.Pagination;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;



import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class EmployeeServlet extends HttpServlet {
	private static final String path = "WEB-INF/employee/";

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

		} 
		else if ("showadd2".equals(type)) {
			showadd2(request, response);

		}else if ("add".equals(type)) {
			add(request, response);

		} else if ("showupdate".equals(type)) {
			showupdate(request, response);

		} else if ("update".equals(type)) {
			update(request, response);

		} else if ("delete".equals(type)) {
			delete(request, response);
		} else if ("deletebatch".equals(type)) {
			deletebatch(request, response);
		} else if ("updatebatch1".equals(type)) {
			updatebatch1(request, response);
		} else if ("showupdatebatch1".equals(type)) {
			showupdatebatch1(request, response);
		} else if ("showupdatebatch2".equals(type)) {
			showupdatebatch2(request, response);
		} else if ("updatebatch2".equals(type)) {
			updatebatch2(request, response);
		} else if ("updatebatch3".equals(type)) {
			updatebatch3(request, response);
		}else if ("upload".equals(type)) {
			upload(request, response);
		}else if ("add2".equals(type)) {
			add2(request, response);
		}

	}
//	}
	private void search(HttpServletRequest request, HttpServletResponse response) {
		EmployeeDao empDao = new EmployeeDao();
		DepartmentDao depDao = new DepartmentDao();
		Employee condition = new Employee();

		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
String pic=request.getParameter("pic");
		int age = -1;
		if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
			age = Integer.parseInt(request.getParameter("age"));

		}
		int depId = -1;
		if (request.getParameter("depId") != null && !"".equals(request.getParameter("depId"))) {
			depId = Integer.parseInt(request.getParameter("depId"));

		}
		condition.setPic(pic);
		condition.setName(name);
		condition.setAge(age);
		condition.setSex(sex);
		Department dep = new Department();
		dep.setId(depId);
		condition.setDep(dep);
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}

		int count = empDao.searchCount(condition);
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);

		List<Employee> list = empDao.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		List<Department> depList = depDao.search();
		request.setAttribute("depList", depList);
		request.setAttribute("p", p);

		request.setAttribute("c", condition);
		request.setAttribute("list", list);

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

	private void updatebatch3(HttpServletRequest request, HttpServletResponse response) {

		try {
			String emps = request.getParameter("emps");
			JSONArray jsonArry = JSONArray.fromObject(emps);
			List<Employee> list = (List<Employee>) JSONArray.toCollection(jsonArry, Employee.class);
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.updatebatch2(list);
			response.sendRedirect("emp");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updatebatch2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String emps = request.getParameter("emps");
			String[] array = emps.split(";");
			List<Employee> list = new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				String[] temp = array[i].split(",");
				Employee emp = new Employee();
				emp.setId(Integer.parseInt(temp[0]));
				emp.setName(temp[1]);
				emp.setSex(temp[2]);
				emp.setAge(Integer.parseInt(temp[3]));
				list.add(emp);
			}
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.updatebatch2(list);
			response.sendRedirect("emp");

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

	private void showupdatebatch1(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ids = request.getParameter("ids");
			EmployeeDao empDao = new EmployeeDao();
			List<Employee> list = empDao.search(ids);
			request.setAttribute("emp", list.get(0));
			request.setAttribute("ids", ids);
			request.getRequestDispatcher(path + "updatebatch1.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showupdatebatch2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ids = request.getParameter("ids");
			EmployeeDao empDao = new EmployeeDao();
			List<Employee> list = empDao.search(ids);
			request.setAttribute("list", list);

			request.getRequestDispatcher(path + "updatebatch2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			Employee emp = new Employee();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			Integer depId=null;
			if(!"".equals(request.getParameter("depId")))
				
			{ depId = Integer.parseInt(request.getParameter("depId"));
			}
			
			
			emp.setId(id);
			emp.setName(name);
			emp.setAge(age);
			emp.setSex(sex);

			Department dep = new Department();
			dep.setId(depId);
			emp.setDep(dep);
			
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.update(emp);
			response.sendRedirect("emp");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updatebatch1(HttpServletRequest request, HttpServletResponse response) {
		try {
			Employee emp = new Employee();
			String ids = request.getParameter("ids");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));

			emp.setName(name);
			emp.setAge(age);
			emp.setSex(sex);

			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.updatebatch1(ids, emp);
			response.sendRedirect("emp");

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

	private void deletebatch(HttpServletRequest request, HttpServletResponse response) {
		try {

			String ids = request.getParameter("ids");
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.deletebatch(ids);
			response.sendRedirect("emp");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {

			String path = "h:/tu/";
			String picName="";
			String name="";
			String sex="";
			String age="";
			String depId="";
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
		
			
			
			for (int i = 0; i < items.size(); i++) {
				
				
				FileItem item = items.get(i);
				if (item.getFieldName().equals("myFile")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					picName=uuid.toString() + houzhui;
					File savedFile = new File(path, picName);
					item.write(savedFile);
					

				} else if (item.getFieldName().equals("name")) {
					 name = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
					
				}else if (item.getFieldName().equals("sex")) {
					 sex = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
					
				}else if (item.getFieldName().equals("age")) {
					age = new String(item.getString());
					
				}else if (item.getFieldName().equals("depId")) {
					depId = new String(item.getString());
					
				
				}
			
			}
			
			Employee emp = new Employee();
			Department dep = new Department();
			
			if(!"".equals(depId))
				
				{ dep.setId(Integer.parseInt(depId));
				}
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(Integer.parseInt(age));
			emp.setPic(picName);
			emp.setDep(dep);
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.add(emp);
			
			response.sendRedirect("emp");
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	
	public void add2(HttpServletRequest request, HttpServletResponse response) {
		try {
			Employee emp = new Employee();
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			Integer depId=null;
			if(!"".equals(request.getParameter("depId")))
			
			{ depId = Integer.parseInt(request.getParameter("depId"));
			}
			String pic = request.getParameter("picture");
			emp.setName(name);
			emp.setAge(age);
			emp.setSex(sex);
			Department dep = new Department();
			dep.setId(depId);
			emp.setDep(dep);
emp.setPic(pic);
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.add(emp);
			response.sendRedirect("emp");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		try {

			String path = "h:/tu/";
			
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
		
			
			String picName="";
			for (int i = 0; i < items.size(); i++) {
				
				
				FileItem item = items.get(i);
				if (item.getFieldName().equals("myFile")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					picName=uuid.toString() + houzhui;
					File savedFile = new File(path, picName);
					item.write(savedFile);
					

				} 
			}
			PrintWriter out=response.getWriter();
			out.print(picName);
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
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
	public void showadd2(HttpServletRequest request, HttpServletResponse response) {
		try {
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher(path + "add2.jsp").forward(request, response);
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
