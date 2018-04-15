package co.simplon.gwi.server_exercice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ArrayListServlet extends GenericServlet {

	private static final long serialVersionUID = 1L;
	
	private List<String> servletRequests =  Collections.synchronizedList(new ArrayList<String>());
	//private List<String> servletRequests =  new ArrayList<String>();

	private long requestCounter = 0;
	private final AtomicLong atomicRequestCounter = new AtomicLong();
	

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestCounter ++;
		atomicRequestCounter.incrementAndGet();
		
		servletRequests.add(request.toString());
		response.getWriter().println(
				"Requests received: " + servletRequests.size() + "\n" +
				"First request : " + servletRequests.get(0) + "\n" +
				"Request counter : " + requestCounter + "\n" + 
				"Atomic Requests counter : " + atomicRequestCounter.get());
	}

}
