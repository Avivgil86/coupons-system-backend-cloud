package app.core.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entity.Company;
import app.core.entity.Customer;
import app.core.exeptions.CouponSystemExceptions;
import app.core.imple.AdminServiceImpl;
import app.core.service.AdminService;

@RestController
@RequestMapping("api/admin/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AdminController implements AdminService {
	@Autowired
	private AdminServiceImpl adminService;


	@PostMapping("company")
	@Override
	public void addCompany(@RequestHeader("Authorization") UUID token,@RequestBody Company company) throws CouponSystemExceptions {
		try {
			this.adminService.addCompany(token, company);

		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping("company")
	@Override
	public void updateCompany(@RequestHeader("Authorization") UUID token,@RequestBody Company company) throws CouponSystemExceptions {
		try {
			this.adminService.updateCompany(token,company);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("company/{companyId}")
	@Override
	public void deleteCompany(@RequestHeader("Authorization") UUID token,@PathVariable int companyId) throws CouponSystemExceptions {
		try {
			this.adminService.deleteCompany(token,companyId);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("companies")
	@Override
	public List<Company> getAllCompanies(@RequestHeader("Authorization") UUID token) {
		try {
			return this.adminService.getAllCompanies(token);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("company/{companyId}")
	@Override
	public Company getCompanyById(@RequestHeader("Authorization") UUID token,@PathVariable int companyId) throws CouponSystemExceptions {
		try {
			return this.adminService.getCompanyById(token,companyId);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PostMapping("customer")
	@Override
	public Customer addCustomer(@RequestHeader("Authorization") UUID token,@RequestBody Customer customer) throws CouponSystemExceptions {
		try {
			return this.adminService.addCustomer(token,customer);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping("customer")
	@Override
	public void updateCustomer(@RequestHeader("Authorization") UUID token,@RequestBody Customer customer) throws CouponSystemExceptions {
		try {
			this.adminService.updateCustomer(token,customer);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("customer/{customerId}")
	@Override
	public void deleteCustomer(@RequestHeader("Authorization") UUID token,@PathVariable int customerId) throws CouponSystemExceptions {
		try {
			this.adminService.deleteCustomer(token,customerId);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("customers")
	@Override
	public List<Customer> getAllCustomers(@RequestHeader("Authorization") UUID token) {
		try {
			return this.adminService.getAllCustomers(token);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("customer/{customerId}")
	@Override
	public Customer getOneCustomerById(@RequestHeader("Authorization") UUID token,@PathVariable int customerId) throws CouponSystemExceptions {
		try {
			return this.adminService.getOneCustomerById(token,customerId);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}



}