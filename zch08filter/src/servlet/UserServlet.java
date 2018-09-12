package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.Userdao;
import entity.User;

public class UserServlet extends HttpServlet{
	
public void doGet(HttpServletRequest request,HttpServletResponse response){

	

	String type=request.getParameter("type");
	if(type==null) {
		showLogin(request, response);
		
	}else if("showLogin".equals(type)) {
		showLogin(request,response);
		
	}else if("doLogin".equals(type)) {
		doLogin(request,response);
		
	}else if("doRegister".equals(type)) {
		doRegister(request,response);
		
	}else if("showRegister".equals(type)) {
		showRegister(request,response);
		
	}
	
	}
	

public void showLogin(HttpServletRequest request,HttpServletResponse response) {
	try {
		String name ="";
	Cookie[] cookies=	request.getCookies();
	if(cookies!=null) {
		for(int i=0;i<cookies.length;i++) {
		if("username".equals(cookies[i].getName()))	{
			name=cookies[i].getValue();
			
		}
		}
	}
	request.setAttribute("name", name);
		request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	


}

public void showRegister(HttpServletRequest request,HttpServletResponse response) {
	try {
		
	
		request.getRequestDispatcher("WEB-INF/login/register.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	


}
	public void doLogin(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		try {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String a=(String) session.getAttribute("randomCode");
		String random=request.getParameter("random");
		if(random.equalsIgnoreCase(a)) {
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		Userdao userDao=new Userdao();
		boolean flag=userDao.search(user); 
		if(flag) {
		
		session.setAttribute("user", username);
		Cookie cookie=new Cookie("username", username);
		cookie.setMaxAge(30);
		response.addCookie(cookie);
			response.sendRedirect("index");
		//request.getRequestDispatcher("WEB-INF/index/index.jsp").forward(request,response);
		}else {
			//request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request,response);
			response.sendRedirect("user?type=showLogin");
		}
		}else {
			request.setAttribute("mes", "验证码错误");
			request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
			
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	
}
	public void doRegister(HttpServletRequest request,HttpServletResponse response) {
	Userdao userDao=new Userdao();
		try {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String sePassword=request.getParameter("sepassword");
		if(password!=null&&password.equals(sePassword)) {
			
		
		
		
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		int a=userDao.add(user);
		boolean flag=false;
		if(a>0) {
			 flag=true;
			
		}else {
			flag=false;
		}
		if(flag) {
			response.sendRedirect("user?type=showLogin");
		}else {
			response.sendRedirect("user");
		}
		
		}else {
			request.setAttribute("mes", "注册失败，请重新注册");
			request.getRequestDispatcher("WEB-INF/login/register.jsp").forward(request, response);
			
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
	doGet(request, response);
}
}
