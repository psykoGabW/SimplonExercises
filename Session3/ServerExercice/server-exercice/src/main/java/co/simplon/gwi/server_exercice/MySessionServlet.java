package co.simplon.gwi.server_exercice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MySessionServlet extends HttpServlet {

	private static final String COUNTER_ATTRIBUTE_NAME = "userCounter";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MySessionServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		resp.setStatus(HttpServletResponse.SC_OK);
		
		HttpSession httpSession = req.getSession();
		Integer counter = (Integer) httpSession.getAttribute(COUNTER_ATTRIBUTE_NAME);

		if (counter == null) {
			counter = 1;
		} else {
			counter++;
		}

		httpSession.setAttribute(COUNTER_ATTRIBUTE_NAME, counter);
		
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<body>");
		pw.println("<p><font color='blue'>Hello You!</font></p>");
		pw.println("<p>");
		pw.print("You've came here : ");
		pw.print("<font color='teal'>");
		pw.print( counter + " time" + (counter > 1 ? "s" : ""));
		pw.print("</font>");
		pw.print("<br/>");
		pw.println("</p>");
		pw.println("</body>");
		pw.println("</html>");
		
	}

}
