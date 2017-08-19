package agency.akcom.cgi.servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.rusticisoftware.cheddargetter.client.CGCustomer;
import com.rusticisoftware.cheddargetter.client.CGException;
import com.rusticisoftware.cheddargetter.client.CGService;

import agency.akcom.cgi.Account;
import agency.akcom.cgi.BillingStatus;

public class SubscribeAccountServlet extends HttpServlet {

	private static final String USER_NAME = "akolchin@gmail.com";
	private static final String PASSWORD = "testcgi";
	private static final String PRODUCT_CODE = "TEST_PRODUCT_8";

	private static final String FREE_SUBSCRIPTION_PLAN_CODE = "FREE";

	private static final String SUCCESS_MESSAGE = "Last subscribe operation successfully completed";

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String errorMsg = null;
		try {
			Objectify ofy = ObjectifyService.ofy();

			Long id = Long.decode(req.getParameter("id"));
			Account account = ofy.load().type(Account.class).id(id).now();


			// Create a new customer in the product with productCode=MY_PRODUCT_CODE and
			// subscribe the customer to a pricing plan

			CGCustomer cgCustomer = null;
			try {
				CGService cgService = new CGService(USER_NAME, PASSWORD, PRODUCT_CODE);
				cgCustomer = cgService.createNewCustomer(Long.toString(account.id), account.name + "---", account.name,
						account.email, account.companyName, FREE_SUBSCRIPTION_PLAN_CODE,
						// we assume to subscribe to FREE plan, so no credit card information needed.
						null, null, null, null, null, null, null);

			} catch (CGException e) {
				errorMsg = "" + e.toString();
			}

			if (cgCustomer != null) {
				// account.token = cgCustomer.getGatewayToken();
				account.billingStatus = BillingStatus.PAID;
			}

			ofy.save().entity(account).now();

		} catch (Exception e) {
			// TODO: report to page
		}

		resp.sendRedirect(
				"/index.jsp?message=" + (errorMsg != null ? URLEncoder.encode(errorMsg, "UTF-8") : SUCCESS_MESSAGE));
	}
}
