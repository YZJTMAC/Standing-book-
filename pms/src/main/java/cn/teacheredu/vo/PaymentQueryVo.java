package cn.teacheredu.vo;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentQueryVo extends BaseQueryVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2492238751065669234L;

	private Integer id;

	/**
	 * 到款信息中的序号
	 */
	private Integer num;

	/**
	 * 到款年份
	 */
	private Integer year;

	/**
	 * 到款月份
	 */
	private Byte month;

	/**
	 * 流水号
	 */
	private String serialNumber;

	/**
	 * 交易时间
	 */
	private Date traTime;

	/**
	 * 汇款人姓名
	 */
	private String remitter;

	/**
	 * 汇款人账号或开户行名称
	 */
	private String remitterAccount;

	/**
	 * 到账银行
	 */
	private String payBank;

	/**
	 * 转账形式
	 */
	private String transferForm;

	/**
	 * 金额
	 */
	private BigDecimal amount;

	/**
	 * 省
	 */
	private Integer province;

	/**
	 * 市
	 */
	private Integer city;

	/**
	 * 县
	 */
	private Integer county;

	/**
	 * 是否提前开票回款 1 是 2 否
	 */
	private Byte advancePay;

	/**
	 * 关联项目ID
	 */
	private Integer projectId;

	/**
	 * 所属公司
	 */
	private String company;
	
	/**
	 * 是否已经开票 1 已开票 2 未开票
	 */
	private Integer hasInvoice;

	/**
	 * 备注
	 */
	private String note;
	
	/**
	 * 业务填写的备注
	 */
	private String noteYw;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Byte getMonth() {
		return month;
	}

	public void setMonth(Byte month) {
		this.month = month;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getTraTime() {
		return traTime;
	}

	public void setTraTime(Date traTime) {
		this.traTime = traTime;
	}

	public String getRemitter() {
		return remitter;
	}

	public void setRemitter(String remitter) {
		this.remitter = remitter;
	}

	public String getRemitterAccount() {
		return remitterAccount;
	}

	public void setRemitterAccount(String remitterAccount) {
		this.remitterAccount = remitterAccount;
	}

	public String getPayBank() {
		return payBank;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}

	public String getTransferForm() {
		return transferForm;
	}

	public void setTransferForm(String transferForm) {
		this.transferForm = transferForm;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getCounty() {
		return county;
	}

	public void setCounty(Integer county) {
		this.county = county;
	}

	public Byte getAdvancePay() {
		return advancePay;
	}

	public void setAdvancePay(Byte advancePay) {
		this.advancePay = advancePay;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNoteYw() {
		return noteYw;
	}

	public void setNoteYw(String noteYw) {
		this.noteYw = noteYw;
	}

	public Integer getHasInvoice() {
		return hasInvoice;
	}

	public void setHasInvoice(Integer hasInvoice) {
		this.hasInvoice = hasInvoice;
	}
}
