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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entity.Category;
import app.core.entity.Company;
import app.core.entity.Coupon;
import app.core.exeptions.CouponSystemExceptions;
import app.core.imple.CompanyServiceImpl;
import app.core.service.CompanyService;

@RestController
@RequestMapping("api/company/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompanyController implements CompanyService {
	@Autowired
	private CompanyServiceImpl companyService;

	@PostMapping("coupon")
	@Override
	public void addCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon)
			throws CouponSystemExceptions {
		try {
			this.companyService.addCoupon(token, coupon);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping("coupon")
	@Override
	public void updateCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon)
			throws CouponSystemExceptions {
		try {
			this.companyService.updateCoupon(token, coupon);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("coupon/{couponId}")
	@Override
	public void deleteCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId)
			throws CouponSystemExceptions {
		try {
			this.companyService.deleteCoupon(token, couponId);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("coupons")
	@Override
	public List<Coupon> getAllcouponsCompany(@RequestHeader("Authorization") UUID token) {
		try {
			System.out.println(this.companyService.getAllcouponsCompany(token));
			return this.companyService.getAllcouponsCompany(token);

		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("coupons/{category}")
	@Override
	public List<Coupon> getAllcouponsCompanyByCategory(@RequestHeader("Authorization") UUID token,
			@PathVariable Category category) {
		try {
			return this.companyService.getAllcouponsCompanyByCategory(token, category);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("coupons/{price}")
	@Override
	public List<Coupon> getAllcouponsCompanyByMaxPrice(@RequestHeader("Authorization") UUID token,
			@PathVariable int price) {
		try {
			return this.companyService.getAllcouponsCompanyByMaxPrice(token, price);
		} catch (CouponSystemExceptions e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("details")
	@Override
	public Company getCompanyDetails(@RequestHeader("Authorization") UUID token) throws CouponSystemExceptions {
		return this.companyService.getCompanyDetails(token);
	}


}
