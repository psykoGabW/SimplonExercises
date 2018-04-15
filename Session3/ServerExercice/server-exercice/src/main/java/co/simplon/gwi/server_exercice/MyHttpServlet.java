package co.simplon.gwi.server_exercice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String COOKIE_COUNTER_OF_USER_CONNEXION = "MacadamiaCounter";

	public MyHttpServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		resp.setStatus(HttpServletResponse.SC_OK);

		// Get Cookie
		Cookie[] requestCookies = req.getCookies();
		Cookie counterCookie = null;

		if (requestCookies != null) {
			for (Cookie c : requestCookies) {
				if (c.getName().equals(COOKIE_COUNTER_OF_USER_CONNEXION)) {
					counterCookie = c;
				}
			}
		}

		int counter = 1;
		if (counterCookie == null) {
			counterCookie = new Cookie(COOKIE_COUNTER_OF_USER_CONNEXION, "1");
		} else {
			counter = Integer.parseInt(counterCookie.getValue());
			counter++;
			counterCookie.setValue(Integer.toString(counter));
		}

		counterCookie.setDomain("127.0.0.1");
		counterCookie.setPath("/myHttpServlet");
		counterCookie.setComment("Counter cookie");
		counterCookie.setMaxAge(2 * 60 * 60); // Age of 2 hours

		resp.addCookie(counterCookie);

		// Html response writing
		PrintWriter pw = resp.getWriter();
		pw.println("<html>");
		pw.println("<body>");
		pw.println("<p><font color='blue'>Hello You!</font></p>");
		pw.println("<p>");
		pw.println("You've came here : " + counter + " time" + (counter > 1 ? "s" : "") + "<br/>");
		pw.println("</p>");
		pw.println("</body>");
		pw.println("</html>");

	}

}
