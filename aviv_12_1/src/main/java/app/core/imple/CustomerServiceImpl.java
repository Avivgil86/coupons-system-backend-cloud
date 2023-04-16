package app.core.imple;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entity.Category;
import app.core.entity.Coupon;
import app.core.entity.Customer;
import app.core.exeptions.CouponSystemExceptions;
import app.core.security.ClientType;
import app.core.security.Information;
import app.core.security.LoginResponse;
import app.core.service.ClientService;
import app.core.service.CustomerService;
import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl extends ClientService implements CustomerService {


	
	@Override
	public LoginResponse login(String email, String password) throws CouponSystemExceptions {

		Customer customerFromDb = customerRepos.findByEmailIgnoreCaseAndPassword(email, password)
				.orElseThrow(() -> new CouponSystemExceptions("email or password not valid"));
		Information information = new Information(customerFromDb.getId(), email, LocalDateTime.now().plusHours(24),
				ClientType.CUSTOMER);
		UUID token = tokenManager.addToken(information);
		return new LoginResponse(token, email, customerFromDb.getId(), customerFromDb.getFirstName(),
				ClientType.CUSTOMER);
	}

	/*
	 * @param coupon
	 * 
	 * @throws CouponSystemExceptions if customer not find with this id
	 */
	@Override
	public void purchaseCoupon(UUID token, int couponId) throws CouponSystemExceptions {
		int customerId = tokenManager.getCustomerid(token);
		Coupon couponFromData = couponRepos.findById(couponId)
				.orElseThrow(() -> new CouponSystemExceptions("Coupon with this id not found "));
		Customer customerFromData = customerRepos.findById(customerId)
				.orElseThrow(() -> new CouponSystemExceptions("Customer with this id not found "));
		if (customerFromData.getCoupons().contains(couponFromData)) {
			throw new CouponSystemExceptions("this coupon already purchased");
		}
		if (couponFromData.getEndDate().isBefore(LocalDate.now())) {
			throw new CouponSystemExceptions("this coupon is expired");
		}
		if (couponFromData.getAmount() == 0) {
			throw new CouponSystemExceptions("this coupon amount is 0");
		}
		couponFromData.setAmount(couponFromData.getAmount() - 1);
		couponRepos.addPurchaseCoupon(customerId, couponFromData.getId());
		couponRepos.saveAndFlush(couponFromData);
	}

	/*
	 * @return all coupons customer from date
	 * 
	 * @throws CouponSystemExceptions if this customer doesn't exists
	 */
	@Override
	public List<Coupon> getAllCouponsCustomer(UUID token) throws CouponSystemExceptions {
		int customerId = tokenManager.getCustomerid(token);

		return couponRepos.findPurchaseCouponsByCustomerId(customerId);
	}

	/*
	 * @param category
	 * 
	 * @return customer all coupons By Category
	 * 
	 * @throws CouponSystemExceptions if this customr doesnt exists
	 */
	@Override
	public List<Coupon> getAllCouponsCustomerByCategory(UUID token, Category category) throws CouponSystemExceptions {
		int customerId = tokenManager.getCustomerid(token);

		return couponRepos.findByCustomerIdAndCategory(category.name(), customerId);

	}

	/*
	 * @param price
	 * 
	 * @return customer all coupons By Price
	 * 
	 * @throws CouponSystemExceptions if this customer doesnt exists
	 */
	@Override
	public List<Coupon> getAllCouponsCustomerByPrice(UUID token, int price) throws CouponSystemExceptions {
		int customerId = tokenManager.getCustomerid(token);

		return couponRepos.findByCustomerIdAndPrice(price, customerId);

	}

	/*
	 * @return customer
	 * 
	 * @throws CouponSystemExceptions if this customer doesn't exists
	 */
	@Override
	public Customer getCustomerDetails(UUID token) throws CouponSystemExceptions {
		int customerId = tokenManager.getCustomerid(token);
		Customer customer = customerRepos.findById(customerId)
				.orElseThrow(() -> new CouponSystemExceptions("this customer doesnt exists"));
		return customer;
	}

	@Override
	public List<Coupon> getAllCoupons() {
		return couponRepos.findAll();
	}

}
