package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProjectDao;
import entity.Project;
import util.Constant;
import util.Pagination;

public class ProjectServlet extends HttpServlet{
	private static final String path = "WEB-INF/project/";
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

		} else if ("update".equals(type)) {
			update(request, response);

		} else if ("delete".equals(type)) {
			delete(request, response);
		} 
	
		
	}
	//}
	

	private void search(HttpServletRequest request, HttpServletResponse response) {
		Project condition = new Project();
		String name = request.getParameter("name");
		
	
		int empCount = -1;
		if (request.getParameter("empCount") != null && !"".equals(request.getParameter("empCount"))) {
			empCount = Integer.parseInt(request.getParameter("empCount"));

		}
		
		
		condition.setName(name);
	
		ProjectDao proDao = new ProjectDao();
	
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}

		int count = proDao.searchCount(condition);
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);

		List<Project>list=proDao.search(condition,p.getBegin(),Constant.EMP_NUM_IN_PAGE);	
		request.setAttribute("p", p);
		
		request.setAttribute("c", condition);
		request.setAttribute("list", list);

		try {
			request.getRequestDispatcher(path+"list.jsp").forward(request, response);
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

			ProjectDao proDao = new ProjectDao();
			Project pro = proDao.search(id);
			request.setAttribute("pro", pro);

			request.getRequestDispatcher(path+"update.jsp").forward(request, response);
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
			Project pro = new Project();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			
			pro.setId(id);
			pro.setName(name);
		

			ProjectDao empDao = new ProjectDao();
			boolean flag = empDao.update(pro);
			response.sendRedirect("pro");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		try {

			int id = Integer.parseInt(request.getParameter("id"));
			ProjectDao proDao = new ProjectDao();
			boolean flag = proDao.delete(id);
			response.sendRedirect("pro");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			Project pro = new Project();
			String name = request.getParameter("name");
			pro.setName(name);
		

			ProjectDao proDao = new ProjectDao();
			boolean flag = proDao.add(pro);
			response.sendRedirect("pro");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showadd(HttpServletRequest request, HttpServletResponse response) {
		try {

			request.getRequestDispatcher(path+"add.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	public void show(HttpServletRequest request, HttpServletResponse response) {
//		try {
//
//			EmployeeDao empDao = new EmployeeDao();
//			int ye = 1;
//			if (request.getParameter("ye") != null) {
//				ye = Integer.parseInt(request.getParameter("ye"));
//			}
//
//			int count = empDao.searchCount();
//			Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
//
//			List<Employee> list = empDao.search(p.getBegin(), Constant.EMP_NUM_IN_PAGE);
//			request.setAttribute("p", p);
//			request.setAttribute("list", list);
//			request.getRequestDispatcher(path+"list.jsp").forward(request, response);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
