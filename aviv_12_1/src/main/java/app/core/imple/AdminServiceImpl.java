package app.core.imple;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entity.Company;
import app.core.entity.Coupon;
import app.core.entity.Customer;
import app.core.exeptions.CouponSystemExceptions;
import app.core.security.ClientType;
import app.core.security.Information;
import app.core.security.LoginResponse;
import app.core.service.AdminService;
import app.core.service.ClientService;

@Service
@Transactional
public class AdminServiceImpl extends ClientService implements AdminService {

	/*
	 * @param email,password
	 * 
	 * @return true
	 * 
	 * @throws CouponSystemExceptions if admin email or password not valid
	 */
	@Override
	public LoginResponse login(String email, String password) throws CouponSystemExceptions {
		if ((email.equalsIgnoreCase("admin@admin.com") && password.equals("admin")) == false) {
			throw new CouponSystemExceptions("admin email or password not valid");
		}
		Information information = new Information(-1, email, LocalDateTime.now().plusHours(24),
				ClientType.ADMINISTRATOR);
		UUID token = tokenManager.addToken(information);
		return new LoginResponse(token, email, -1, "Admin", ClientType.ADMINISTRATOR);
	}

	/*
	 * @param company
	 * 
	 * @throws CouponSystemExceptions if the company already exist by name or email
	 */
	@Override
	public void addCompany(UUID token,Company company) throws CouponSystemExceptions {
		tokenManager.getAdminId(token);
		if (companyRepos.existsByName(company.getName())) {
			throw new CouponSystemExceptions("Already exist by name");
		}
		if (companyRepos.existsByEmail(company.getEmail())) {
			throw new CouponSystemExceptions("Already exist by email");
		}
		companyRepos.save(company);
	}

	/*
	 * @param company
	 * 
	 * @throws CouponSystemExceptions if this company doesn't exists
	 */
	@Override
	public void updateCompany(UUID token,Company company) throws CouponSystemExceptions {
		tokenManager.getAdminId(token);
		Company companyToUpdate = companyRepos.findById(company.getId())
				.orElseThrow(() -> new CouponSystemExceptions("this company doesnt exists"));
		if (!company.getName().equalsIgnoreCase(companyToUpdate.getName())) {
			throw new CouponSystemExceptions("Cannot update company name");
		}
		if (companyRepos.existsByEmail(company.getEmail())) {
			throw new CouponSystemExceptions("Already exist by email");
		}
		companyToUpdate.setEmail(company.getEmail());
		companyToUpdate.setPassword(company.getPassword());
		companyRepos.saveAndFlush(companyToUpdate);
	}

	/*
	 * @param companyId
	 */
	private void deletePurchaseCompany(int companyId) throws CouponSystemExceptions {
		List<Coupon> couponsToDelete = couponRepos.findAllCompanyCouponsByCompanyId(companyId);
		for (Coupon coupon : couponsToDelete) {
			couponRepos.deleteAllPurchaseCouponsById(coupon.getId());
		}
	}

	/*
	 * @param companyId
	 * 
	 * @throws CouponSystemExceptions if this company doesn't exists
	 */
	@Override
	public void deleteCompany(UUID token,int companyId) throws CouponSystemExceptions {
		tokenManager.getAdminId(token);

		if (companyRepos.existsById(companyId)) {
			deletePurchaseCompany(companyId);
			companyRepos.deleteById(companyId);
		} else {
			throw new CouponSystemExceptions("this company doesnt exists");

		}
	}

	/*
	 * @return all the companies
	 */
	@Override
	public List<Company> getAllCompanies(UUID token) throws CouponSystemExceptions {
		tokenManager.getAdminId(token);
		return companyRepos.findAll();
	}

	/*
	 * @param companyId
	 * 
	 * @return company by id
	 */
	@Override
	public Company getCompanyById(UUID token,int companyId) throws CouponSystemExceptions {
		tokenManager.getAdminId(token);

		return companyRepos.findById(companyId).orElseThrow(() -> new CouponSystemExceptions("company not exists"));
	}

	/*
	 * @param customer
	 * 
	 * @throws CouponSystemExceptions if the customer is alreadry exists
	 */
	@Override
	public Customer addCustomer(UUID token,Customer customer) throws CouponSystemExceptions {
		tokenManager.getAdminId(token);

		if (!customerRepos.existsByEmail(customer.getEmail())) {
			return customerRepos.save(customer);
		} else {
			throw new CouponSystemExceptions("this Email is alreadry exists");

		}
	}

	/*
	 * @param customer
	 * 
	 * @throws CouponSystemExceptions if the customer not exists
	 */
	@Override
	public void updateCustomer(UUID token,Customer customer) throws CouponSystemExceptions {
		tokenManager.getAdminId(token);
		Customer customerToUpdate = customerRepos.findById(customer.getId())
				.orElseThrow(() -> new CouponSystemExceptions(" this customer doesnt exists"));
		if (customerRepos.existsByEmail(customer.getEmail()) ) {
			throw new CouponSystemExceptions(" this email is already exists");
		}
		customerToUpdate.setEmail(customer.getEmail());
		customerToUpdate.setFirstName(customer.getFirstName());
		customerToUpdate.setLastName(customer.getLastName());
		customerToUpdate.setPassword(customer.getPassword());
		customerRepos.saveAndFlush(customerToUpdate);
	}

	/*
	 * @param customerId
	 * 
	 * @throws CouponSystemExceptions if the customer not exists
	 */
	@Override
	public void deleteCustomer(UUID token,int customerId) throws CouponSystemExceptions {
		tokenManager.getAdminId(token);
		customerRepos.findById(customerId).orElseThrow(() -> new CouponSystemExceptions("this customer doesnt exists"));
		couponRepos.deleteAllPurchaseCustomerCouponsById(customerId);
		customerRepos.deleteById(customerId);
	}

	/*
	 * @return all customers
	 */
	@Override
	public List<Customer> getAllCustomers(UUID token) throws CouponSystemExceptions{
		tokenManager.getAdminId(token);
		return customerRepos.findAll();
	}

	/*
	 * @param customerId
	 * 
	 * @return customer
	 * 
	 * @throws CouponSystemExceptions if the specified customer id not exists
	 */
	@Override
	public Customer getOneCustomerById(UUID token,int customerId) throws CouponSystemExceptions {
		tokenManager.getAdminId(token);

		Customer customer = customerRepos.findById(customerId)
				.orElseThrow(() -> new CouponSystemExceptions("this customer doesnt exists"));
		return customer;
	}

}
