package cn.teacheredu.entity;

import java.math.BigDecimal;

public class CostYearSumEntity extends BaseEntity {

	private Integer costYear;
	private BigDecimal paymentYearMoney;
	private BigDecimal incomeYearMoney;
	private BigDecimal fundYearMoney;
	private BigDecimal budgetYearMoney;
	private BigDecimal transferYearMoney;
	private BigDecimal invoiceYearMoney;
	
	public Integer getCostYear() {
		return costYear;
	}
	public void setCostYear(Integer costYear) {
		this.costYear = costYear;
	}
	public BigDecimal getPaymentYearMoney() {
		return paymentYearMoney;
	}
	public void setPaymentYearMoney(BigDecimal paymentYearMoney) {
		this.paymentYearMoney = paymentYearMoney;
	}
	public BigDecimal getIncomeYearMoney() {
		return incomeYearMoney;
	}
	public void setIncomeYearMoney(BigDecimal incomeYearMoney) {
		this.incomeYearMoney = incomeYearMoney;
	}
	public BigDecimal getFundYearMoney() {
		return fundYearMoney;
	}
	public void setFundYearMoney(BigDecimal fundYearMoney) {
		this.fundYearMoney = fundYearMoney;
	}
	public BigDecimal getBudgetYearMoney() {
		return budgetYearMoney;
	}
	public void setBudgetYearMoney(BigDecimal budgetYearMoney) {
		this.budgetYearMoney = budgetYearMoney;
	}
	public BigDecimal getTransferYearMoney() {
		return transferYearMoney;
	}
	public void setTransferYearMoney(BigDecimal transferYearMoney) {
		this.transferYearMoney = transferYearMoney;
	}
	public BigDecimal getInvoiceYearMoney() {
		return invoiceYearMoney;
	}
	public void setInvoiceYearMoney(BigDecimal invoiceYearMoney) {
		this.invoiceYearMoney = invoiceYearMoney;
	}
}
