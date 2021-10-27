package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class Compte {
	
	@Id @GeneratedValue
	private long code ;
	private double solde ;
	private Date datecreation;
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Date getDatecreation() {
		return datecreation;
	}
	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}
	public Compte(long code, double solde, Date datecreation) {
		super();
		this.code = code;
		this.solde = solde;
		this.datecreation = datecreation;
	}
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
