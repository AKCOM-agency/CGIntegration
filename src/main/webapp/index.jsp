<%@page import="agency.akcom.cgi.BillingStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ page import="agency.akcom.cgi.Account"%>
<%@ page import="com.googlecode.objectify.ObjectifyService"%>

<%@ page import="java.util.List"%>
<html>
<head>
<!-- link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/ -->
</head>

<body>
	<H1>Accounts</H1>
	<form action="/add" method="post">
		<div>
			<input type="submit" value="Add Account" />
		</div>

	</form>

	<%
		List<Account> accounts = ObjectifyService.ofy().load().type(Account.class).list();// .order("billingStatus")       

		if (!accounts.isEmpty()) {
	%>
	<table>
		<%
			// Look at all of our greetings
				for (Account account : accounts) {
					pageContext.setAttribute("id", account.id);
					pageContext.setAttribute("name", account.name);
					pageContext.setAttribute("billing_status", account.billingStatus);
					//....
		%>
		<tr>
			<td><form action="/del" method="post">
					<input type="submit" value="X" /> <input type="hidden" name="id"
						value="${id}" />
				</form></td>
			<td>${name}</td>
			<td>${billing_status}</td>
			<td>
				<%
					if (account.billingStatus == BillingStatus.FREE) {
				%>
				<form action="/subscribe" method="post">
					<input type="submit" value="Subscribe" /> <input type="hidden"
						name="id" value="${id}" />
				</form> <%
 	}
 %>
			</td>
		</tr>
		<%
			}
			}
		%>
	</table>




</body>
</html>