package app.core.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entity.Category;
import app.core.entity.Coupon;
import app.core.entity.Customer;
import app.core.exeptions.CouponSystemExceptions;
import app.core.imple.CustomerServiceImpl;

@RestController
@RequestMapping("api/customer/")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl customerService;

	

	@PostMapping("purchase")
	public void purchaseCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon) {
		try {
			this.customerService.purchaseCoupon(token, coupon.getId());
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("coupons")
	public List<Coupon> getAllCouponsCustomer(@RequestHeader("Authorization") UUID token) {
		try {
			return this.customerService.getAllCouponsCustomer(token);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

		}
	}

	@GetMapping("coupons/category")
	public List<Coupon> getAllCouponsCustomerByCategory(@RequestHeader("Authorization") UUID token,@RequestParam Category category) throws CouponSystemExceptions {
		try {
			return this.customerService.getAllCouponsCustomerByCategory(token,category);

		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

		}
	}

	@GetMapping("coupons/price")
	public List<Coupon> getAllCouponsCustomerByPrice(@RequestHeader("Authorization") UUID token,@RequestParam int price) throws CouponSystemExceptions {
		try {
			return this.customerService.getAllCouponsCustomerByPrice(token,price);

		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("details")
	public Customer getCustomerDetails(@RequestHeader("Authorization") UUID token) throws CouponSystemExceptions {
		try {

			return this.customerService.getCustomerDetails(token);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping("all-coupons")
	public List<Coupon> getAllCoupons(){
		return this.customerService.getAllCoupons();
	}

}