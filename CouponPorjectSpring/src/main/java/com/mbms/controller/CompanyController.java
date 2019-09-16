package com.mbms.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbms.exceptions.CouponSystemException;
import com.mbms.model.Company;
import com.mbms.model.Coupon;
import com.mbms.repository.CouponRepository;
import com.mbms.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CouponRepository couponRepository;

	@GetMapping("/companyById/{id}")
	public Company companyById(@PathVariable int id) throws CouponSystemException {
		return companyService.getCompany(id);
	}
	
	
	@PostMapping("/insertCoupon/{companyId}")
	public ResponseEntity<Coupon> insertCoupon(@RequestBody Coupon coupon, @RequestBody int companyId) throws CouponSystemException {

	//	CustomLogin customLogin = getService();

		Coupon coupi=companyService.insertCoupon(coupon, companyId);
		ResponseEntity<Coupon> result = new ResponseEntity<Coupon>(coupi,HttpStatus.OK);
		return result;

	}
	
//	@DeleteMapping ("/deleteCoupon/{id}")
//	public ResponseEntity<Coupon> deleteCoupon (@PathVariable("id") int id){
//		companyService.deleteCoupon(id);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		
//	}
	
//	@PostMapping("/updateCoupon")  
//	public ResponseEntity<Coupon> updateCoupon(@RequestBody Coupon coupon, @RequestParam Date endDate, @RequestParam double price) {
//		
//		if (id == coupon.getId()) {
//
//			companyService.updateCoupon(coupon, getService().getTypeId());
//
//			return coupon;
//
//		} else {
//
//			throw new UpdatingException("Coupon", "The id of the coupon and the id in the path incorrect",
//
//					coupon.getId());
//
//		}
//
//	}
//		return null;
//	
//	
//	}
//	



}