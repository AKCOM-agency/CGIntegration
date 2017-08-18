package agency.akcom.cgi.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import agency.akcom.cgi.Account;
import agency.akcom.cgi.BillingStatus;

public class SubscribeAccountServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			Objectify ofy = ObjectifyService.ofy();
			
			Long id = Long.decode(req.getParameter("id"));
			Account account = ofy.load().type(Account.class).id(id).now();
			
			//TODO: implement call to CG API
			account.billingStatus = BillingStatus.PAID;
			
			ofy.save().entity(account).now();

		} catch (Exception e) {
			// TODO: report to page
		}

		resp.sendRedirect("/index.jsp");
	}
}
