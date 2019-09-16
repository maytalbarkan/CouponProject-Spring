package com.mbms.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="CUSTOMER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Basic(optional = false)
	@Column(nullable = false, name="customerName")
	private String customerName;

	@Basic(optional = false)
	@Column(nullable = false, name="password")
	private String password;
	
	@OneToMany
	private List<Coupon> coupons;
	

}