package servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
public class AddServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
		//   不用过滤器时的登录判断
//			HttpSession session = request.getSession();
//			if (session.getAttribute("user") == null) {
//				try {
//					response.sendRedirect("user?type=showLogin");
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			} else {
			// String path = request.getServletContext().getRealPath("/") + "/pic";
			// System.out.println(path);
		
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("add")) {
					add(request, response);
				}

			}
//			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
//			Employee emp = new Employee();
//			String name = request.getParameter("name");
//			String sex = request.getParameter("sex");
//			int age = Integer.parseInt(request.getParameter("age"));
//			Integer depId=null;
//			if(!"".equals(request.getParameter("depId")))
//			
//			{ depId = Integer.parseInt(request.getParameter("depId"));
//			}
//			emp.setName(name);
//			emp.setAge(age);
//			emp.setSex(sex);
//			Department dep = new Department();
//			dep.setId(depId);
//			emp.setDep(dep);
//
//			EmployeeDao empDao = new EmployeeDao();
//			boolean flag = empDao.add(emp);
//			response.sendRedirect("emp");
			String path = "h:/tu/";
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
		
			Employee emp = new Employee();
			Department dep = new Department();
			EmployeeDao empDao = new EmployeeDao();
			Integer depId=null;
			for (int i = 0; i < items.size(); i++) {
				
				
				FileItem item = items.get(i);
				if (item.getFieldName().equals("myFile")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					File savedFile = new File(path, uuid.toString() + houzhui);
					item.write(savedFile);
					emp.setPic(uuid.toString() + houzhui);

				} else if (item.getFieldName().equals("name")) {
					String name = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
					emp.setName(name);
				}else if (item.getFieldName().equals("sex")) {
					String sex = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
					emp.setSex(sex);
				}else if (item.getFieldName().equals("age")) {
					String age1 = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
					int age = Integer.parseInt(age1);//
					emp.setAge(age);
				}else if (item.getFieldName().equals("depId")) {
					String depId1 = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
					 depId=Integer.parseInt(depId1);
					 dep.setId(depId);
					 emp.setDep(dep);
				}
			
			}
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


	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);

	}

	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd- HH:mm:");
		String str = format.format(date);

		System.out.println(str);

	}
}
