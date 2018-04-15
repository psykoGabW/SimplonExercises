package co.simplon.gwi.server_exercice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class StaticContentServer {
	public static void main(String[] args)
    {
        System.setProperty("org.eclipse.jetty.LEVEL","WARN");

        Server server = new Server(9092);
         // The filesystem paths we will map
        // String homePath = System.getProperty("user.home");
        // String homePath = "file:///D:/GWI/CoursSimplon/GitHubGWI/";
        String homePath = System.getenv("WEBSITE_HOME");
        
        String pwdPath = System.getProperty("user.dir");
        
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setResourceBase(pwdPath);
        context.setContextPath("/");
        server.setHandler(context);

        ServletHolder holderDynamic = new ServletHolder("dynamic", MySessionServlet.class);
        context.addServlet(holderDynamic, "/dynamic/*");

        ServletHolder holderHome = new ServletHolder("static-home", DefaultServlet.class);
        holderHome.setInitParameter("resourceBase",homePath);
        holderHome.setInitParameter("dirAllowed","true");
        holderHome.setInitParameter("pathInfoOnly","true");
        context.addServlet(holderHome,"/home/*");

        ServletHolder holderPwd = new ServletHolder("default", DefaultServlet.class);
        holderPwd.setInitParameter("dirAllowed","true");
        context.addServlet(holderPwd,"/");

        try
        {
            server.start();
            server.dump(System.err);
            server.join();
        }
        catch (Throwable t)
        {
            t.printStackTrace(System.err);
        }
    }
}