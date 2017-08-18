package agency.akcom.cgi.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

import agency.akcom.cgi.Account;

public class DeleteAccountServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			Long id = Long.decode(req.getParameter("id"));
			ObjectifyService.ofy().delete().type(Account.class).id(id).now();
		} catch (Exception e) {
			// TODO: report to page
		}

		resp.sendRedirect("/index.jsp");
	}
}
