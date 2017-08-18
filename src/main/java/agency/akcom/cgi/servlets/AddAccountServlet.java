package agency.akcom.cgi.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

import agency.akcom.cgi.Account;

public class AddAccountServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Account account = new Account();

		ObjectifyService.ofy().save().entity(account).now();

		resp.sendRedirect("/index.jsp");
	}
}
