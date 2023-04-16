package app.core.service;

import java.util.List;
import java.util.UUID;

import app.core.entity.Category;
import app.core.entity.Coupon;
import app.core.entity.Customer;
import app.core.exeptions.CouponSystemExceptions;

public interface CustomerService {
	public void purchaseCoupon(UUID token, int couponId) throws CouponSystemExceptions;

	public List<Coupon> getAllCouponsCustomer(UUID token) throws CouponSystemExceptions;

	public List<Coupon> getAllCouponsCustomerByCategory(UUID token, Category category) throws CouponSystemExceptions;

	public List<Coupon> getAllCouponsCustomerByPrice(UUID token, int price) throws CouponSystemExceptions;

	public Customer getCustomerDetails(UUID token) throws CouponSystemExceptions;
	
	public List<Coupon> getAllCoupons();
}
