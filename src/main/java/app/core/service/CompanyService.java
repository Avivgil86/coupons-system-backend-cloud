package app.core.service;

import java.util.List;
import java.util.UUID;

import app.core.entity.Category;
import app.core.entity.Company;
import app.core.entity.Coupon;
import app.core.exeptions.CouponSystemExceptions;

public interface CompanyService {
	void addCoupon(UUID token,Coupon coupon) throws CouponSystemExceptions;

	void updateCoupon(UUID token,Coupon coupon) throws CouponSystemExceptions;

	void deleteCoupon(UUID token,int couponId)  throws CouponSystemExceptions;

	List<Coupon> getAllcouponsCompany(UUID token)  throws CouponSystemExceptions;

	List<Coupon> getAllcouponsCompanyByCategory(UUID token,Category category) throws CouponSystemExceptions;

	List<Coupon> getAllcouponsCompanyByMaxPrice(UUID token,int price) throws CouponSystemExceptions;


	Company getCompanyDetails(UUID token) throws CouponSystemExceptions;


}
