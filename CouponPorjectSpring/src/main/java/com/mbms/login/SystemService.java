package com.mbms.login;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mbms.exceptions.CouponSystemException;
import com.mbms.model.Company;
import com.mbms.model.Coupon;
import com.mbms.model.Customer;
import com.mbms.model.Log;
import com.mbms.repository.CompanyRepository;
import com.mbms.repository.CouponRepository;
import com.mbms.repository.CustomerRepository;
import com.mbms.repository.LogRepository;
import com.mbms.service.AdminService;
import com.mbms.service.AdminServiceImpl;
import com.mbms.service.CompanyService;
import com.mbms.service.CompanyServiceImpl;
import com.mbms.service.CustomerService;
import com.mbms.service.CustomerServiceImpl;





/**
 * This class will have an actions related to the system like Log in, and delete expired coupons.
 */

@Service
public class SystemService {

	@Autowired
	private AdminServiceImpl adminService;

	@Autowired
	private CompanyServiceImpl companyService;
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CustomerServiceImpl customerService;
	@Autowired
	private CustomerRepository customerRepository;

	
	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private ApplicationContext context;
	

	public CouponClientFacade login (String name, String password, LoginType clientType) throws CouponSystemException {

		switch (clientType) {

		case ADMIN:

			if (name.equals("admin")&&password.equals("1234")) {
				adminService = context.getBean(AdminServiceImpl.class);
				return adminService;

			}

		case COMPANY:

			Company comp = companyRepository.findByCompanyNameAndPassword(name, password);

			if (comp!=null) {

				CompanyServiceImpl company = context.getBean(CompanyServiceImpl.class);

				comp.setId(comp.getId());

				return companyService;

			}

		case CUSTOMER:

			Customer cust = customerRepository.findByCustomerNameAndPassword(name, password);

			if (cust!=null) {

				CustomerServiceImpl customer = context.getBean(CustomerServiceImpl.class);

				cust.setId(cust.getId());

				return customerService;

			}

		}

		throw new CouponSystemException("Login Falied! Invalid User or Password!");

	}


	/**
	 * The function will be activated when the system is activated or every 24 hours
	 * in which the system operates. The function will get all coupons from the DB and will 
	 * expired coupons.
	 */

	@Scheduled(fixedRateString = "${coupon.project.remove.daily.coupon.every.day}")
	@Transactional

	public void removeInvalidCoupon() {

		List<Coupon> allCoupons = couponRepository.findByEndDateBefore(new Date());

		for (Coupon coupon : allCoupons) {

			Log log = new Log(new Date(), "FROM  SYSTEM SERVER", "eclipse",

					"remove daily coupon remove the coupon " + coupon.getTitle(), true);

			logRepository.save(log);

			couponRepository.delete(coupon);

		}

	}

}