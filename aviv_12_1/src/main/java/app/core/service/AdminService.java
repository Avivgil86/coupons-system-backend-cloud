package app.core.service;

import java.util.List;
import java.util.UUID;

import app.core.entity.Company;
import app.core.entity.Customer;
import app.core.exeptions.CouponSystemExceptions;

public interface AdminService {
	void addCompany(UUID token,Company company) throws CouponSystemExceptions;

	void updateCompany(UUID token,Company company) throws CouponSystemExceptions;

	void deleteCompany(UUID token,int companyId) throws CouponSystemExceptions;

	List<Company> getAllCompanies(UUID token)throws CouponSystemExceptions;

	Company getCompanyById(UUID token,int companyId) throws CouponSystemExceptions;

	Customer addCustomer(UUID token,Customer customer) throws CouponSystemExceptions;

	void updateCustomer(UUID token,Customer customer) throws CouponSystemExceptions;

	void deleteCustomer(UUID token,int customerId) throws CouponSystemExceptions;

	List<Customer> getAllCustomers(UUID token)throws CouponSystemExceptions;

	Customer getOneCustomerById(UUID token,int customerId) throws CouponSystemExceptions;



}
