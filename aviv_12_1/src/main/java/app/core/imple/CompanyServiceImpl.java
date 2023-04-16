package app.core.imple;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entity.Category;
import app.core.entity.Company;
import app.core.entity.Coupon;
import app.core.exeptions.CouponSystemExceptions;
import app.core.security.ClientType;
import app.core.security.Information;
import app.core.security.LoginResponse;
import app.core.service.ClientService;
import app.core.service.CompanyService;

@Service
@Transactional
public class CompanyServiceImpl extends ClientService implements CompanyService {

	@Override
	public LoginResponse login(String email, String password) throws CouponSystemExceptions {
		Company companyFromDb = companyRepos.findByEmailIgnoreCaseAndPassword(email, password)
				.orElseThrow(() -> new CouponSystemExceptions("email or password not valid"));
		Information information = new Information(companyFromDb.getId(), email, LocalDateTime.now().plusHours(24),
				ClientType.COMPANY);
		UUID token = tokenManager.addToken(information);
		return new LoginResponse(token, email, companyFromDb.getId(), companyFromDb.getName(), ClientType.COMPANY);
	}

	/*
	 * @param coupon
	 * 
	 * 
	 * @throws CouponSystemExceptions if coupons with this title exists
	 */
	@Override
	public void addCoupon(UUID token, Coupon coupon) throws CouponSystemExceptions {
		int companyId = tokenManager.getCompanyId(token);
		if (couponRepos.existsCouponsByTitleAndCompanyId(coupon.getTitle(), companyId)) {
			throw new CouponSystemExceptions(" coupons with this title exists");
		} else {
			Company companyFromDb = companyRepos.findById(companyId)
					.orElseThrow(() -> new CouponSystemExceptions("Company not found"));
			coupon.setCompany(companyFromDb);
			couponRepos.save(coupon);
		}
	}

	/*
	 * @param coupon
	 * 
	 * @return
	 * 
	 * @throws CouponSystemExceptions if coupons with this id doesn't exists
	 */
	@Override
	public void updateCoupon(UUID token, Coupon coupon) throws CouponSystemExceptions {
		int companyId = tokenManager.getCompanyId(token);
		Company companyFromDb = companyRepos.findById(companyId).orElseThrow(() -> new CouponSystemExceptions("Company not found"));
		if (couponRepos.existsCouponsByTitleAndCompanyId(coupon.getTitle(), companyId)) {
			throw new CouponSystemExceptions(" coupons with this title exists");
		}
		Coupon couponToUpdate = couponRepos.findById(coupon.getId())
				.orElseThrow(() -> new CouponSystemExceptions("coupons with this id doesnt exists"));
		couponToUpdate.setCompany(companyFromDb);
		couponToUpdate.setAmount(coupon.getAmount());
		couponToUpdate.setCategory(coupon.getCategory());
		couponToUpdate.setDescription(coupon.getDescription());
		couponToUpdate.setEndDate(coupon.getEndDate());
		couponToUpdate.setStartDate(coupon.getStartDate());
		couponToUpdate.setImage(coupon.getImage());
		couponToUpdate.setTitle(coupon.getTitle());
		couponToUpdate.setPrice(coupon.getPrice());
		couponRepos.saveAndFlush(couponToUpdate);
	}

	/*
	 * @param couponId
	 * 
	 * 
	 * @throws CouponSystemExceptions if this coupon doesn't exists
	 */
	@Override
	public void deleteCoupon(UUID token, int couponId) throws CouponSystemExceptions {
		tokenManager.getCompanyId(token);

		if (!couponRepos.existsById(couponId)) {
			throw new CouponSystemExceptions("this coupon doesnt exists");
		}
		couponRepos.deleteAllPurchaseCouponsById(couponId);
		couponRepos.deleteCouponsById(couponId);

	}

	/*
	 * 
	 * @return all company coupons
	 * 
	 */
	@Override
	public List<Coupon> getAllcouponsCompany(UUID token) throws CouponSystemExceptions {
		int companyId = tokenManager.getCompanyId(token);
		return couponRepos.findAllCompanyCouponsByCompanyId(companyId);
	}

	/*
	 * @param category
	 * 
	 * @return all company coupons by category
	 * 
	 */
	@Override
	public List<Coupon> getAllcouponsCompanyByCategory(UUID token, Category category) throws CouponSystemExceptions {
		int companyId = tokenManager.getCompanyId(token);
		return couponRepos.findByCompanyIdAndCategory(companyId, category);
	}

	/*
	 * @param price
	 * 
	 * @return all company coupons by price
	 * 
	 */
	@Override
	public List<Coupon> getAllcouponsCompanyByMaxPrice(UUID token, int price) throws CouponSystemExceptions {
		int companyId = tokenManager.getCompanyId(token);
		return couponRepos.findAllCompanyCouponsByPrice(price, companyId);
	}

	/*
	 * @return company
	 * 
	 * @throws CouponSystemExceptions if this company doesn't exist
	 */
	@Override
	public Company getCompanyDetails(UUID token) throws CouponSystemExceptions {
		int companyId = tokenManager.getCompanyId(token);
		Company company = companyRepos.findById(companyId)
				.orElseThrow(() -> new CouponSystemExceptions("this company doesnt exist"));
		return company;
	}

}
