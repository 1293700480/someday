package listener;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import util.MyWebSocket;




public class CountListener implements HttpSessionListener,ServletContextListener{

	@Override
	public void sessionCreated(HttpSessionEvent event) {
//		ServletContext application=event.getSession().getServletContext();
//		System.out.println("session创建");
//		int num=0;
//		if(application.getAttribute("num")!=null) {
//			num=(Integer)application.getAttribute("num");
//		}
//		num++;
//		application.setAttribute("num",num);
		HttpSession session=event.getSession();
	
		ServletContext application=session.getServletContext();
int num=0;
System.out.println("session建立");
if(application.getAttribute("num")!=null) {
	num=(Integer)application.getAttribute("num");
}
num++;
application.setAttribute("num",num);
MyWebSocket.sendMessageAll(String.valueOf(num) );
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session=event.getSession();
		ServletContext application=session.getServletContext();
int num=0;
if(application.getAttribute("num")!=null) {
	num=(Integer)application.getAttribute("num");
}
num--;
application.setAttribute("num",num);
MyWebSocket.sendMessageAll(String.valueOf(num) );

System.out.println("session销毁");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
