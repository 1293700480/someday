package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dep2ProDao;
import dao.DepartmentDao;
import dao.ProjectDao;
import entity.Department;
import entity.Project;

public class Dep2ProServlet extends HttpServlet {
	private static final String path = "WEB-INF/dep2pro/";

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

		}  else if ("add".equals(type)) {
			add(request, response);

		} else if ("delete".equals(type)) {
			delete(request, response);
		} else if ("m2".equals(type)) {
			search2(request, response);

		}else if ("add2".equals(type)) {
			add2(request, response);

		}else if ("delete2".equals(type)) {
			delete2(request, response);

		}else if ("m3".equals(type)) {
			search3(request, response);

		}else if ("add3".equals(type)) {
			add3(request, response);

		}else if ("delete3".equals(type)) {
			delete3(request, response);

		}

	}
//	}
	private void search3(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId = Integer.parseInt(request.getParameter("depId"));
			Dep2ProDao pdDao = new Dep2ProDao();
			DepartmentDao depDao=new DepartmentDao();
			Department dep=depDao.search(depId);
			
			
			List<Project> list = pdDao.searchByDepartment(depId);
			List<Project> noList = pdDao.searchByNotDepartment(depId);
			
			request.setAttribute("dep", dep);
			request.setAttribute("list", list);
			request.setAttribute("noList", noList);
			request.getRequestDispatcher(path + "list3.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId = Integer.parseInt(request.getParameter("depId"));
			Dep2ProDao pdDao = new Dep2ProDao();
			DepartmentDao depDao=new DepartmentDao();
			Department dep=depDao.search(depId);
			
			
			List<Project> list = pdDao.searchByDepartment(depId);
			List<Project> noList = pdDao.searchByNotDepartment(depId);
			
			request.setAttribute("dep", dep);
			request.setAttribute("list", list);
			request.setAttribute("noList", noList);
			request.getRequestDispatcher(path + "list.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void search2(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId = Integer.parseInt(request.getParameter("depId"));
			Dep2ProDao pdDao = new Dep2ProDao();
			DepartmentDao depDao=new DepartmentDao();
			Department dep=depDao.search(depId);
			
			
			List<Project> list = pdDao.searchByDepartment(depId);
			List<Project> noList = pdDao.searchByNotDepartment(depId);
			
			request.setAttribute("dep", dep);
			request.setAttribute("list", list);
			request.setAttribute("noList", noList);
			request.getRequestDispatcher(path + "list2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		try {

			int depId = Integer.parseInt(request.getParameter("depId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			
			Dep2ProDao pdDao = new Dep2ProDao();
			pdDao.delete(depId, proId);
			
			response.sendRedirect("d2p?depId="+depId);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void delete2(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out=response.getWriter();
			int depId = Integer.parseInt(request.getParameter("depId"));
			String str = request.getParameter("proIdList");
			String[]proIds=str.split(",");
			Dep2ProDao pdDao = new Dep2ProDao();
			boolean flag=pdDao.delete(depId, proIds);
			out.print(flag);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void delete3(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out=response.getWriter();
			int depId = Integer.parseInt(request.getParameter("depId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			
			Dep2ProDao pdDao = new Dep2ProDao();
			boolean flag= pdDao.delete(depId, proId);
			out.print(flag);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId = Integer.parseInt(request.getParameter("depId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			
			Dep2ProDao pdDao = new Dep2ProDao();
			pdDao.add(depId, proId);
			
			response.sendRedirect("d2p?depId="+depId);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void add2(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out=response.getWriter();
			int depId = Integer.parseInt(request.getParameter("depId"));
			String str = request.getParameter("proIdList");
			String[]proIds=str.split(",");
			Dep2ProDao pdDao = new Dep2ProDao();
			boolean flag=pdDao.add(depId, proIds);
			out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void add3(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out=response.getWriter();
			int depId = Integer.parseInt(request.getParameter("depId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			
			Dep2ProDao pdDao = new Dep2ProDao();
			boolean flag=pdDao.add(depId, proId);
			out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
