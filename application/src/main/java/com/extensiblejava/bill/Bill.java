package com.extensiblejava.bill;

import com.extensiblejava.audit.*;
import com.extensiblejava.financial.*;
import java.math.*;
// import com.extensiblejava.bill.data.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cust_id")
	private Integer custId;

    @Column(name = "amount")
	private BigDecimal amount;
	
    @Column(name = "audited_amount")
	private BigDecimal auditedAmount;
	
    @Column(name = "paid_amount")
	private BigDecimal paidAmount;

    public Bill() {}

	/*
    public Bill(String name, String email) {
        this.name = name;
        this.email = email;
    }
	*/

	

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getCustId() {
        return custId;
    }	
	
    public void setCustId(Integer custId) {
        this.custId = custId;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }	

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public BigDecimal getAuditedAmount() {
        return auditedAmount;
    }	

    public void setAuditedAmount(BigDecimal auditedAmount) {
        this.auditedAmount = auditedAmount;
    }
    
    public BigDecimal getPaidAmount() {
        return paidAmount;
    }	

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }
    
	
	// ********************************************************************************* //
	
	public String getStatus() {
		if (this.paidAmount.compareTo(BigDecimal.ZERO) == 0 ) {
			return "PAID";
		} else if (this.paidAmount.compareTo(BigDecimal.ZERO) != 0) {
			return "AUDITED";
		} else {
			return "NEW";
		}
	}

	public void audit() {
		AuditFacade auditor = new AuditFacade();
		setAuditedAmount(auditor.audit(this));
	}

	public void pay() {
		if (this.paidAmount.compareTo(BigDecimal.ZERO) == 0 ) {
			Payment payer = new Payment();
			setPaidAmount(payer.generateDraft(this));
		}
	}


}