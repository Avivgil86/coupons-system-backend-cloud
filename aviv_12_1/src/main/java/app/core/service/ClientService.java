package app.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.exeptions.CouponSystemExceptions;
import app.core.repos.CompanyRepos;
import app.core.repos.CouponRepos;
import app.core.repos.CustomerRepos;
import app.core.security.LoginResponse;
import app.core.security.TokenManager;

@Service
public abstract class ClientService {

	@Autowired
	protected CompanyRepos companyRepos;
	@Autowired
	protected CustomerRepos customerRepos;
	@Autowired
	protected CouponRepos couponRepos;
	@Autowired
	protected TokenManager tokenManager;

	public abstract LoginResponse login(String email, String password) throws CouponSystemExceptions;

}
