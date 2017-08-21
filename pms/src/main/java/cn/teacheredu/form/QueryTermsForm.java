package cn.teacheredu.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import cn.teacheredu.utils.SystemConst;

/**
 * 用来封装列表查询时的分页信息和查询条件等请求参数(get方式)
 * @author Zhaojie
 *
 */
public class QueryTermsForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询人的ID
	 */
	private Integer userId;
	
	/**
	 * 对应的项目id
	 */
	private Integer projectId;
	/**
	 * 项目标号
	 */
	private String projectNo;
	/**
	 * 查询条件 下拉框类型
	 */
	private Integer type = 0;
	
	/**
	 * 对应的value
	 */
	private String value;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	
	private Integer currPage = 1;
	
	private Integer pageSize = SystemConst.DEFAULT_PAGESIZE;
	
	/**
	 * 发起人、开票人、申请人等姓名
	 */
	private String userName;
	
	/**
	 * 部门ID
	 */
	private Integer dmId;
	
	/**
	 * 项目类型
	 */
	private Byte proType;
	/**
	 * 项目状态
	 */
	private Byte status;
	
	private String company;

	private Integer proYear;
	

	private Byte trainType;
	
	private Integer province;
	
	private BigDecimal minAmount;
	
	private BigDecimal maxAmount;
	/*
	 * 是否有协议：1有  2无
	 */
	private Integer protocol;
	
	private List<String> companyList;
	/**
	 * 是否执行项目汇总导出操作
	 */
	private boolean isExcel;
	/**
	 * 项目名称（尼玛这项目之前谁写的啊，逻辑乱七八糟，表也乱七八糟，冗余字段太多了，后面的开发者真的要吐了QAQ）
	 */
	private String projectName;
	/**
	 * 省份名字
	 */
	private String provinceName;
	/**
	 * 协议名
	 */
	private String protocolName;
	/**
	 * 预计后期到款金额(最小值)
	 */
	private BigDecimal estimatedAmountLater_min;
	/**
	 * 预计后期到款金额（最大值）
	 */
	private BigDecimal estimatedAmountLater_max;
	/**
	 * 预计项目总额(最小值)
	 */
	private BigDecimal estimatedAmount_min;
	/**
	 * 预计项目总额（最大值）
	 */
	private BigDecimal estimatedAmount_max;
	/**
	 * 项目到款总额(最小值)
	 */
	private BigDecimal projectDueAmount_min;
	/**
	 * 项目到款总额(最大值)
	 */
	private BigDecimal projectDueAmount_max;
	/**
	 * 应收账款(最小值)
	 */
	private BigDecimal receivableAccounts_min;
	/**
	 * 应收账款（最大值）
	 */
	private BigDecimal receivableAccounts_max;
	/**
	 * 已转收入(最小值)
	 */
	private BigDecimal transferredIncome_min;
	/**
	 * 已转收入（最大值）
	 */
	private BigDecimal transferredIncome_max;
	/**
	 * 前期应付经费总额（最小值）
	 */
	private BigDecimal payableFundsPeriod_min;
	/**
	 * 前期应付经费总额（最大值）
	 */
	private BigDecimal payableFundsPeriod_max;
	/**
	 * 后期应付经费总额（最小值）
	 */
	private BigDecimal payableFundsLater_min;
	/**
	 * 后期应付经费总额（最大值）
	 */
	private BigDecimal payableFundsLater_max;
	/**
	 * 已支付经费金额（最小值）
	 */
	private BigDecimal completePayableFunds_min;
	/**
	 * 已支付经费金额（最大值）
	 */
	private BigDecimal completePayableFunds_max;
	/**
	 * 未付经费金额（最小值）
	 */
	private BigDecimal uncompletePayableFunds_min;
	/**
	 * 未付经费金额（最大值）
	 */
	private BigDecimal uncompletePayableFunds_max;
	
	public int getProtocol() {
		return protocol;
	}

	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}
	
	public Integer getProYear() {
		return proYear;
	}

	public void setProYear(Integer proYear) {
		this.proYear = proYear;
	}
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getDmId() {
		return dmId;
	}

	public void setDmId(Integer dmId) {
		this.dmId = dmId;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getProType() {
		return proType;
	}

	public void setProType(Byte proType) {
		this.proType = proType;
	}

	public List<String> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<String> companyList) {
		this.companyList = companyList;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public Byte getTrainType() {
		return trainType;
	}

	public void setTrainType(Byte trainType) {
		this.trainType = trainType;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	
	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isExcel() {
		return isExcel;
	}

	public void setExcel(boolean isExcel) {
		this.isExcel = isExcel;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public BigDecimal getEstimatedAmountLater_min() {
		return estimatedAmountLater_min;
	}

	public void setEstimatedAmountLater_min(BigDecimal estimatedAmountLater_min) {
		this.estimatedAmountLater_min = estimatedAmountLater_min;
	}

	public BigDecimal getEstimatedAmountLater_max() {
		return estimatedAmountLater_max;
	}

	public void setEstimatedAmountLater_max(BigDecimal estimatedAmountLater_max) {
		this.estimatedAmountLater_max = estimatedAmountLater_max;
	}

	public BigDecimal getEstimatedAmount_min() {
		return estimatedAmount_min;
	}

	public void setEstimatedAmount_min(BigDecimal estimatedAmount_min) {
		this.estimatedAmount_min = estimatedAmount_min;
	}

	public BigDecimal getEstimatedAmount_max() {
		return estimatedAmount_max;
	}

	public void setEstimatedAmount_max(BigDecimal estimatedAmount_max) {
		this.estimatedAmount_max = estimatedAmount_max;
	}

	public BigDecimal getProjectDueAmount_min() {
		return projectDueAmount_min;
	}

	public void setProjectDueAmount_min(BigDecimal projectDueAmount_min) {
		this.projectDueAmount_min = projectDueAmount_min;
	}

	public BigDecimal getProjectDueAmount_max() {
		return projectDueAmount_max;
	}

	public void setProjectDueAmount_max(BigDecimal projectDueAmount_max) {
		this.projectDueAmount_max = projectDueAmount_max;
	}

	public BigDecimal getReceivableAccounts_min() {
		return receivableAccounts_min;
	}

	public void setReceivableAccounts_min(BigDecimal receivableAccounts_min) {
		this.receivableAccounts_min = receivableAccounts_min;
	}

	public BigDecimal getReceivableAccounts_max() {
		return receivableAccounts_max;
	}

	public void setReceivableAccounts_max(BigDecimal receivableAccounts_max) {
		this.receivableAccounts_max = receivableAccounts_max;
	}

	public BigDecimal getTransferredIncome_min() {
		return transferredIncome_min;
	}

	public void setTransferredIncome_min(BigDecimal transferredIncome_min) {
		this.transferredIncome_min = transferredIncome_min;
	}

	public BigDecimal getTransferredIncome_max() {
		return transferredIncome_max;
	}

	public void setTransferredIncome_max(BigDecimal transferredIncome_max) {
		this.transferredIncome_max = transferredIncome_max;
	}

	public BigDecimal getPayableFundsPeriod_min() {
		return payableFundsPeriod_min;
	}

	public void setPayableFundsPeriod_min(BigDecimal payableFundsPeriod_min) {
		this.payableFundsPeriod_min = payableFundsPeriod_min;
	}

	public BigDecimal getPayableFundsPeriod_max() {
		return payableFundsPeriod_max;
	}

	public void setPayableFundsPeriod_max(BigDecimal payableFundsPeriod_max) {
		this.payableFundsPeriod_max = payableFundsPeriod_max;
	}

	public BigDecimal getPayableFundsLater_min() {
		return payableFundsLater_min;
	}

	public void setPayableFundsLater_min(BigDecimal payableFundsLater_min) {
		this.payableFundsLater_min = payableFundsLater_min;
	}

	public BigDecimal getPayableFundsLater_max() {
		return payableFundsLater_max;
	}

	public void setPayableFundsLater_max(BigDecimal payableFundsLater_max) {
		this.payableFundsLater_max = payableFundsLater_max;
	}

	public BigDecimal getCompletePayableFunds_min() {
		return completePayableFunds_min;
	}

	public void setCompletePayableFunds_min(BigDecimal completePayableFunds_min) {
		this.completePayableFunds_min = completePayableFunds_min;
	}

	public BigDecimal getCompletePayableFunds_max() {
		return completePayableFunds_max;
	}

	public void setCompletePayableFunds_max(BigDecimal completePayableFunds_max) {
		this.completePayableFunds_max = completePayableFunds_max;
	}

	public BigDecimal getUncompletePayableFunds_min() {
		return uncompletePayableFunds_min;
	}

	public void setUncompletePayableFunds_min(BigDecimal uncompletePayableFunds_min) {
		this.uncompletePayableFunds_min = uncompletePayableFunds_min;
	}

	public BigDecimal getUncompletePayableFunds_max() {
		return uncompletePayableFunds_max;
	}

	public void setUncompletePayableFunds_max(BigDecimal uncompletePayableFunds_max) {
		this.uncompletePayableFunds_max = uncompletePayableFunds_max;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProtocolName() {
		return protocolName;
	}

	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}

	
}
