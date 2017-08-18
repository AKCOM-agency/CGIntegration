package agency.akcom.cgi;

import java.util.UUID;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Account {
	@Id
	public Long id;

	public String name;
	@Index
	public BillingStatus billingStatus = BillingStatus.FREE;
	public String email;
	public String companyName;
	public String token;

	public Account() {
		name = UUID.randomUUID().toString() + " account";
	}

}
