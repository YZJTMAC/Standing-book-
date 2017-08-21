package cn.teacheredu.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProjectEndForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5172798500228290873L;

	private Integer id;

	/**
	 * 业务人员ID
	 */
	private Integer uid;

	/**
	 * 项目ID
	 */
	private Integer projectId;
	
	/**
	 * 实际上线人数
	 */
	private Integer realNum;
	
	/**
	 * 应缴费人数
	 */
	private Integer payCount;

	/**
	 * 实际项目总额
	 */
	private BigDecimal realTotalMoney;

	/**
	 * 实际已到款总额
	 */
	private BigDecimal realTotalPay;

	/**
	 * 实际已开票金额
	 */
	private BigDecimal realTotalInvoice;
	
	/**
	 * 收费人数
	 */
	private Integer chargeCount;

	/**
	 * 应收款金额
	 */
	private BigDecimal laterPay;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 对应的流程id
	 */
	private Integer processId;
	
	/**
	 * 未到款金额
	 */
	private BigDecimal notTotalPay;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public BigDecimal getRealTotalMoney() {
		return realTotalMoney;
	}

	public void setRealTotalMoney(BigDecimal realTotalMoney) {
		this.realTotalMoney = realTotalMoney;
	}

	public BigDecimal getRealTotalPay() {
		return realTotalPay;
	}

	public void setRealTotalPay(BigDecimal realTotalPay) {
		this.realTotalPay = realTotalPay;
	}

	public BigDecimal getRealTotalInvoice() {
		return realTotalInvoice;
	}

	public void setRealTotalInvoice(BigDecimal realTotalInvoice) {
		this.realTotalInvoice = realTotalInvoice;
	}

	public Integer getChargeCount() {
		return chargeCount;
	}

	public void setChargeCount(Integer chargeCount) {
		this.chargeCount = chargeCount;
	}

	public BigDecimal getLaterPay() {
		return laterPay;
	}

	public void setLaterPay(BigDecimal laterPay) {
		this.laterPay = laterPay;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public BigDecimal getNotTotalPay() {
		return notTotalPay;
	}

	public void setNotTotalPay(BigDecimal notTotalPay) {
		this.notTotalPay = notTotalPay;
	}

	public Integer getRealNum() {
		return realNum;
	}

	public void setRealNum(Integer realNum) {
		this.realNum = realNum;
	}

	public Integer getPayCount() {
		return payCount;
	}

	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}
	
	

}
