package com.mbms.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="COUPON")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Coupon implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;

	@Basic(optional = false)
	@Column(nullable = false, name="title")
	private String title;

	@Basic(optional = false)
	@Column(nullable = false, name="startDate")
	private Date startDate;


	@Basic(optional = false)
	@Column(nullable = false, name="endDate")
	private Date endDate;

	@Basic(optional = false)
	@Column(nullable = false, name="amount")
	private int amount;

	@Basic(optional = false)
	@Column(nullable = false, name="message")
	private String message;

	@Basic(optional = false)
	@Column(nullable = false, name="price")
	private double price;

	@Basic(optional = false)
	@Column(nullable = false, name="image")
	private String image;

	@Basic(optional = false)
	@Column(nullable = false, name="type")
	@Enumerated(EnumType.STRING)
	private CouponCaregory type;
	
	@ManyToOne
	@JsonIgnore
	@Valid
	private Company company;
	@ManyToMany(mappedBy = "coupons")
	@Valid
	private List<Customer> customers;
	
}