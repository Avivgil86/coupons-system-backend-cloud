package app.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.exeptions.CouponSystemExceptions;
import app.core.imple.AdminServiceImpl;
import app.core.imple.CompanyServiceImpl;
import app.core.imple.CustomerServiceImpl;

@Service
public class LoginManager {

	@Autowired
	private AdminServiceImpl adminService;
	@Autowired
	private CompanyServiceImpl companyService;
	@Autowired
	private CustomerServiceImpl customerService;

	/*
	 * @param email, password, clinetType
	 * 
	 * @return company or customer or admin
	 * 
	 * @throws CouponSystemExceptions if the email or password not match;
	 */
	public LoginResponse login(String email, String password, ClientType clientType) throws CouponSystemExceptions {
		LoginResponse loginResponse = null;
		switch (clientType) {
		case ADMINISTRATOR: {
			loginResponse = adminService.login(email, password);
			break;
		}
		case COMPANY: {
			loginResponse = companyService.login(email, password);
			break;
		}
		case CUSTOMER: {
			loginResponse = customerService.login(email, password);
			break;
		}

		}
		return loginResponse;

	}
}
