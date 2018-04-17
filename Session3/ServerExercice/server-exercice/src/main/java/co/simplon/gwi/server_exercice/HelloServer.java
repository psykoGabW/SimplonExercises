package co.simplon.gwi.server_exercice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class HelloServer {

	public static void main(String args[]) throws Exception {
		Server server = new Server(9092);
				
		ServletHandler handler = new ServletHandler();
		server.setHandler(handler);
		handler.addServletWithMapping(HelloGenericServlet.class, "/HelloWorld");
		handler.addServletWithMapping(StatefulServlet.class, "/statefulServlet");
		handler.addServletWithMapping(ArrayListServlet.class, "/arrayListServlet");
		handler.addServletWithMapping(DataBaseCounterServlet.class, "/dataBaseCounterServlet");
		handler.addServletWithMapping(MyHttpServlet.class, "/myHttpServlet");
		// handler.addServletWithMapping(MySessionServlet.class,"/mySessionServlet"); // Check StaticContentServer

		server.start(); // Create an execution thread. Each time a server receive a request, it creates a new execution thread.
		server.join();	// Synchronize main thread to the execution thread so that server doesn't stop.
	}
}
