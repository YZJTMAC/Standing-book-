package cn.teacheredu.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import cn.teacheredu.entity.BaseEntity;
import cn.teacheredu.entity.ProjectYearSummaryEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.utils.SystemConst;

public class ProjectSummariesQueryVo extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4816422159585149057L;

	private Integer id;

    private Integer projectId;

    private String projectCompany;

    private Byte projectType;

    private Integer projectYear;

    private Integer projectProvincialId;
    
    private String projectProvincial;

    private String projectSerialNumber;

    private String projectName;

    private String projectCooperName;
    

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date projectStartDate;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date projectEndDate;

    private Integer projectTrainPeriod;//projectStartDate-projectEndDate，满15天算一个月，不满一个月算一个月

    private BigDecimal projectCharge;

    private Integer projectExpectedNum;

    private Integer projectLiveNum;

    /**
     * 预计项目总额： 单价*报名人数
     */
    private BigDecimal predictTotalAmount;
    /**
     * 预计项目总额（最大值）
     */
    private BigDecimal predictTotalAmount_min;
    /**
     * 预计项目总额（最小值）
     */
    private BigDecimal predictTotalAmount_max;
    /**
     * 实际项目总额：a=（预计项目总额-前期经费）b=到款金额 a >b ？ a  ： b	。
     * （字段计算公式，问题是否兼容系统中项目完结表的实际项目总额字段计算）
     */
//    private BigDecimal predictRealTotalAmount;
    private BigDecimal projectRealTotalAmount;
    /**
     * 项目实际到款总额（最小值）
     */
    private BigDecimal projectRealTotalAmount_min;
    /**
     * 项目实际到款总额（最大值）
     */
    private BigDecimal projectRealTotalAmount_max;
//    private Byte predictStatus;
    private Byte projectStatus;
    
//    private String protocolName;
    private String projectProtocolName;

    /**
     * 实际到款总额，数据库中直接查出来的
     */
    private BigDecimal realPaymentAmount;

    /**
     * 预计后期到款总额：报名人数*单价-前期经费-已到款金额 	（同预计项目总额的问题）
     */
    private BigDecimal predictLaterPayment;
    /**
     * 预计后期到款金额（最小值）
     */
    private BigDecimal predictLaterPayment_min;
    /**
     * 预计后期到款金额（最大值）
     */
    private BigDecimal predictLaterPayment_max;
	/**
	 * 应收账款：已结转收入金额-已到款金额
	 */
    private BigDecimal predictReceiveAmount;
    /**
     * 应收账款（最小值）
     */
    private BigDecimal predictReceiveAmount_min;
    /**
     * 应收账款（最大值）
     */
    private BigDecimal predictReceiveAmount_max;
    /**
     * 已结转收入
     */
    private BigDecimal realIncomeAmount;
    /**
     * 已结转收入（最小值）
     */
    private BigDecimal realIncomeAmount_min;
    /**
     * 已结转收入（最大值）
     */
    private BigDecimal realIncomeAmount_max;
    /**
     * 前期后期经费比例，注意%，数据库中字段存储的值没有百分号
     */
    private BigDecimal proFundProportion;

    private BigDecimal laterFundProportion;

    /**
     * 前期后期经费金额：预计项目总额*前期后期比例
     */
    private BigDecimal proFundAmount;
    /**
     * 前期应付经费总额（最小值）
     */
    private BigDecimal proFundAmount_min;
    /**
     * 前期应付经费总额（最大值）
     */
    private BigDecimal proFundAmount_max;
    
    private BigDecimal laterFundAmount;
    /**
     * 后期应付经费总额（最小值）
     */
    private BigDecimal laterFundAmount_min;
    /**
     * 后期应付经费总额（最大值）
     */
    private BigDecimal laterFundAmount_max;
    
    private BigDecimal realFundAmount;
    /**
     * 已支付经费金额（最小值）
     */
    private BigDecimal realFundAmount_min;
    /**
     * 已支付经费金额（最大值）
     */
    private BigDecimal realFundAmount_max;
    /**
     * 尚未支付经费：后期经费比例金额-已支付经费合计
     */
    private BigDecimal nopayFundAmount;
    /**
     * 尚未支付经费(最小值)
     */
    private BigDecimal nopayFundAmount_min;
    /**
     * 尚未支付经费(最大值)
     */
    private BigDecimal nopayFundAmount_max;
    
    private BigDecimal predictBudget;
    
    private BigDecimal realBudgetAmount;
    
    /**
     * 结转成本总金额
     */
    private BigDecimal realCostAmount;

    private BigDecimal realInvoiceAmount;
    
    /**
     * 未开发票金额：已到款合计-已开发票合计
     */
    private BigDecimal noInvoiceAmount;

    /**
     * 经费支付异常：已到款合计金额*后期经费比例-已支付经费金额
     */
    private BigDecimal fundError;

    private Byte projectFormat;

    private Integer projectChargeArea;
    
    private String url;
    
    private String note;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date incomeFirstTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date incomeLastTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date overFirstTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date overLastTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date paymentFirstTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date paymentLastTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fundsFirstTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fundsLastTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date invoiceFirstTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date invoiceLastTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date budgetFirstTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date budgetLastTime;


	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date summaryFirstTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date summaryLastTime;
    
	
	/**
	 * 权限字段和分页字段
	 */
	private int StartPosition;
	
	private List<String> companyListM;
	
	private Integer chargeArea;
	
	private Integer provincial;

	private Integer currPage = 1;
	
	private Integer pageSize = SystemConst.DEFAULT_PAGESIZE;
	
	/**
	 * 查询记录数量的标记：-1的时候代表统计查询出来的数量
	 */
	private Integer totalFlag=0;
	
	
	/**
	 * 查询条件 下拉框类型
	 */
	private Integer type = 0;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	
	
	/**
	 * 发起人、开票人、申请人等姓名
	 */
	private String userName;
	
	/**
	 * 部门ID
	 */
	private Integer dmId;
	
	private BigDecimal minAmount;
	
	private BigDecimal maxAmount;
	
	
    public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartPosition() {
		return StartPosition;
	}

	public void setStartPosition(int startPosition) {
		StartPosition = startPosition;
	}

	public List<String> getCompanyListM() {
		return companyListM;
	}

	public void setCompanyListM(List<String> companyListM) {
		this.companyListM = companyListM;
	}

	public Integer getChargeArea() {
		return chargeArea;
	}

	public void setChargeArea(Integer chargeArea) {
		this.chargeArea = chargeArea;
	}

	public Integer getProvincial() {
		return provincial;
	}

	public void setProvincial(Integer provincial) {
		this.provincial = provincial;
	}

	private List<ProjectYearSummaryEntity> projectYearSummarys;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectCompany() {
        return projectCompany;
    }

    public void setProjectCompany(String projectCompany) {
        this.projectCompany = projectCompany == null ? null : projectCompany.trim();
    }

    public Byte getProjectType() {
        return projectType;
    }

    public void setProjectType(Byte projectType) {
        this.projectType = projectType;
    }

    public Integer getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(Integer projectYear) {
        this.projectYear = projectYear;
    }
    
    public String getProjectProvincial() {
		return projectProvincial;
	}

	public void setProjectProvincial(String projectProvincial) {
		this.projectProvincial = projectProvincial;
	}

	public String getProjectSerialNumber() {
        return projectSerialNumber;
    }

    public void setProjectSerialNumber(String projectSerialNumber) {
        this.projectSerialNumber = projectSerialNumber == null ? null : projectSerialNumber.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectCooperName() {
        return projectCooperName;
    }

    public void setProjectCooperName(String projectCooperName) {
        this.projectCooperName = projectCooperName == null ? null : projectCooperName.trim();
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public Integer getProjectTrainPeriod() {
        return projectTrainPeriod;
    }

    public void setProjectTrainPeriod(Integer projectTrainPeriod) {
        this.projectTrainPeriod = projectTrainPeriod;
    }

    public BigDecimal getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(BigDecimal projectCharge) {
        this.projectCharge = projectCharge;
    }

    public Integer getProjectExpectedNum() {
        return projectExpectedNum;
    }

    public void setProjectExpectedNum(Integer projectExpectedNum) {
        this.projectExpectedNum = projectExpectedNum;
    }

    public Integer getProjectLiveNum() {
        return projectLiveNum;
    }

    public void setProjectLiveNum(Integer projectLiveNum) {
        this.projectLiveNum = projectLiveNum;
    }

    public BigDecimal getPredictTotalAmount() {
        return predictTotalAmount;
    }

    public void setPredictTotalAmount(BigDecimal predictTotalAmount) {
        this.predictTotalAmount = predictTotalAmount;
    }

    public BigDecimal getProjectRealTotalAmount() {
		return projectRealTotalAmount;
	}

	public void setProjectRealTotalAmount(BigDecimal projectRealTotalAmount) {
		this.projectRealTotalAmount = projectRealTotalAmount;
	}
    public Byte getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(Byte projectStatus) {
		this.projectStatus = projectStatus;
	}

    public String getProjectProtocolName() {
		return projectProtocolName;
	}

	public void setProjectProtocolName(String projectProtocolName) {
		this.projectProtocolName = projectProtocolName;
	}

	public BigDecimal getRealPaymentAmount() {
        return realPaymentAmount;
    }

    public void setRealPaymentAmount(BigDecimal realPaymentAmount) {
        this.realPaymentAmount = realPaymentAmount;
    }

    public BigDecimal getPredictLaterPayment() {
        return predictLaterPayment;
    }

    public void setPredictLaterPayment(BigDecimal predictLaterPayment) {
        this.predictLaterPayment = predictLaterPayment;
    }

    public BigDecimal getPredictReceiveAmount() {
        return predictReceiveAmount;
    }

    public void setPredictReceiveAmount(BigDecimal predictReceiveAmount) {
        this.predictReceiveAmount = predictReceiveAmount;
    }

    public BigDecimal getRealIncomeAmount() {
        return realIncomeAmount;
    }

    public void setRealIncomeAmount(BigDecimal realIncomeAmount) {
        this.realIncomeAmount = realIncomeAmount;
    }

    public BigDecimal getProFundProportion() {
        return proFundProportion;
    }

    public void setProFundProportion(BigDecimal proFundProportion) {
        this.proFundProportion = proFundProportion;
    }

    public BigDecimal getLaterFundProportion() {
        return laterFundProportion;
    }

    public void setLaterFundProportion(BigDecimal laterFundProportion) {
        this.laterFundProportion = laterFundProportion;
    }

    public BigDecimal getProFundAmount() {
        return proFundAmount;
    }

    public void setProFundAmount(BigDecimal proFundAmount) {
        this.proFundAmount = proFundAmount;
    }

    public BigDecimal getLaterFundAmount() {
        return laterFundAmount;
    }

    public void setLaterFundAmount(BigDecimal laterFundAmount) {
        this.laterFundAmount = laterFundAmount;
    }

    public BigDecimal getRealFundAmount() {
        return realFundAmount;
    }

    public void setRealFundAmount(BigDecimal realFundAmount) {
        this.realFundAmount = realFundAmount;
    }

    public BigDecimal getNopayFundAmount() {
        return nopayFundAmount;
    }

    public void setNopayFundAmount(BigDecimal nopayFundAmount) {
        this.nopayFundAmount = nopayFundAmount;
    }

    public BigDecimal getPredictBudget() {
        return predictBudget;
    }

    public void setPredictBudget(BigDecimal predictBudget) {
        this.predictBudget = predictBudget;
    }

    public BigDecimal getRealBudgetAmount() {
        return realBudgetAmount;
    }

    public void setRealBudgetAmount(BigDecimal realBudgetAmount) {
        this.realBudgetAmount = realBudgetAmount;
    }

    public BigDecimal getRealCostAmount() {
        return realCostAmount;
    }

    public void setRealCostAmount(BigDecimal realCostAmount) {
        this.realCostAmount = realCostAmount;
    }

    public BigDecimal getRealInvoiceAmount() {
        return realInvoiceAmount;
    }

    public void setRealInvoiceAmount(BigDecimal realInvoiceAmount) {
        this.realInvoiceAmount = realInvoiceAmount;
    }

    public BigDecimal getNoInvoiceAmount() {
        return noInvoiceAmount;
    }

    public void setNoInvoiceAmount(BigDecimal noInvoiceAmount) {
        this.noInvoiceAmount = noInvoiceAmount;
    }

    public BigDecimal getFundError() {
        return fundError;
    }

    public void setFundError(BigDecimal fundError) {
        this.fundError = fundError;
    }

    public Byte getProjectFormat() {
        return projectFormat;
    }

    public void setProjectFormat(Byte projectFormat) {
        this.projectFormat = projectFormat;
    }

    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getIncomeFirstTime() {
        return incomeFirstTime;
    }

    public void setIncomeFirstTime(Date incomeFirstTime) {
        this.incomeFirstTime = incomeFirstTime;
    }

    public Date getIncomeLastTime() {
        return incomeLastTime;
    }

    public void setIncomeLastTime(Date incomeLastTime) {
        this.incomeLastTime = incomeLastTime;
    }

    public Date getOverFirstTime() {
        return overFirstTime;
    }

    public void setOverFirstTime(Date overFirstTime) {
        this.overFirstTime = overFirstTime;
    }

    public Date getOverLastTime() {
        return overLastTime;
    }

    public void setOverLastTime(Date overLastTime) {
        this.overLastTime = overLastTime;
    }

    public Date getPaymentFirstTime() {
        return paymentFirstTime;
    }

    public void setPaymentFirstTime(Date paymentFirstTime) {
        this.paymentFirstTime = paymentFirstTime;
    }

    public Date getPaymentLastTime() {
        return paymentLastTime;
    }

    public void setPaymentLastTime(Date paymentLastTime) {
        this.paymentLastTime = paymentLastTime;
    }

    public Date getFundsFirstTime() {
        return fundsFirstTime;
    }

    public void setFundsFirstTime(Date fundsFirstTime) {
        this.fundsFirstTime = fundsFirstTime;
    }

    public Date getFundsLastTime() {
        return fundsLastTime;
    }

    public void setFundsLastTime(Date fundsLastTime) {
        this.fundsLastTime = fundsLastTime;
    }

    public Date getInvoiceFirstTime() {
        return invoiceFirstTime;
    }

    public void setInvoiceFirstTime(Date invoiceFirstTime) {
        this.invoiceFirstTime = invoiceFirstTime;
    }

    public Date getInvoiceLastTime() {
        return invoiceLastTime;
    }

    public void setInvoiceLastTime(Date invoiceLastTime) {
        this.invoiceLastTime = invoiceLastTime;
    }

    public Date getBudgetFirstTime() {
        return budgetFirstTime;
    }

    public void setBudgetFirstTime(Date budgetFirstTime) {
        this.budgetFirstTime = budgetFirstTime;
    }

    public Date getBudgetLastTime() {
        return budgetLastTime;
    }

    public void setBudgetLastTime(Date budgetLastTime) {
        this.budgetLastTime = budgetLastTime;
    }

	public Date getSummaryFirstTime() {
		return summaryFirstTime;
	}

	public void setSummaryFirstTime(Date summaryFirstTime) {
		this.summaryFirstTime = summaryFirstTime;
	}

	public Date getSummaryLastTime() {
		return summaryLastTime;
	}

	public void setSummaryLastTime(Date summaryLastTime) {
		this.summaryLastTime = summaryLastTime;
	}

	public List<ProjectYearSummaryEntity> getProjectYearSummarys() {
		return projectYearSummarys;
	}

	public void setProjectYearSummarys(
			List<ProjectYearSummaryEntity> projectYearSummarys) {
		this.projectYearSummarys = projectYearSummarys;
	}

	public Integer getTotalFlag() {
		return totalFlag;
	}

	public void setTotalFlag(Integer totalFlag) {
		this.totalFlag = totalFlag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getProjectChargeArea() {
		return projectChargeArea;
	}

	public void setProjectChargeArea(Integer projectChargeArea) {
		this.projectChargeArea = projectChargeArea;
	}

	public Integer getProjectProvincialId() {
		return projectProvincialId;
	}

	public void setProjectProvincialId(Integer projectProvincialId) {
		this.projectProvincialId = projectProvincialId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public BigDecimal getPredictLaterPayment_min() {
		return predictLaterPayment_min;
	}

	public void setPredictLaterPayment_min(BigDecimal predictLaterPayment_min) {
		this.predictLaterPayment_min = predictLaterPayment_min;
	}

	public BigDecimal getPredictLaterPayment_max() {
		return predictLaterPayment_max;
	}

	public void setPredictLaterPayment_max(BigDecimal predictLaterPayment_max) {
		this.predictLaterPayment_max = predictLaterPayment_max;
	}

	public BigDecimal getPredictTotalAmount_min() {
		return predictTotalAmount_min;
	}

	public void setPredictTotalAmount_min(BigDecimal predictTotalAmount_min) {
		this.predictTotalAmount_min = predictTotalAmount_min;
	}

	public BigDecimal getPredictTotalAmount_max() {
		return predictTotalAmount_max;
	}

	public void setPredictTotalAmount_max(BigDecimal predictTotalAmount_max) {
		this.predictTotalAmount_max = predictTotalAmount_max;
	}

	public BigDecimal getProjectRealTotalAmount_min() {
		return projectRealTotalAmount_min;
	}

	public void setProjectRealTotalAmount_min(BigDecimal projectRealTotalAmount_min) {
		this.projectRealTotalAmount_min = projectRealTotalAmount_min;
	}

	public BigDecimal getProjectRealTotalAmount_max() {
		return projectRealTotalAmount_max;
	}

	public void setProjectRealTotalAmount_max(BigDecimal projectRealTotalAmount_max) {
		this.projectRealTotalAmount_max = projectRealTotalAmount_max;
	}

	public BigDecimal getPredictReceiveAmount_min() {
		return predictReceiveAmount_min;
	}

	public void setPredictReceiveAmount_min(BigDecimal predictReceiveAmount_min) {
		this.predictReceiveAmount_min = predictReceiveAmount_min;
	}

	public BigDecimal getPredictReceiveAmount_max() {
		return predictReceiveAmount_max;
	}

	public void setPredictReceiveAmount_max(BigDecimal predictReceiveAmount_max) {
		this.predictReceiveAmount_max = predictReceiveAmount_max;
	}

	public BigDecimal getRealIncomeAmount_min() {
		return realIncomeAmount_min;
	}

	public void setRealIncomeAmount_min(BigDecimal realIncomeAmount_min) {
		this.realIncomeAmount_min = realIncomeAmount_min;
	}

	public BigDecimal getRealIncomeAmount_max() {
		return realIncomeAmount_max;
	}

	public void setRealIncomeAmount_max(BigDecimal realIncomeAmount_max) {
		this.realIncomeAmount_max = realIncomeAmount_max;
	}

	public BigDecimal getProFundAmount_min() {
		return proFundAmount_min;
	}

	public void setProFundAmount_min(BigDecimal proFundAmount_min) {
		this.proFundAmount_min = proFundAmount_min;
	}

	public BigDecimal getProFundAmount_max() {
		return proFundAmount_max;
	}

	public void setProFundAmount_max(BigDecimal proFundAmount_max) {
		this.proFundAmount_max = proFundAmount_max;
	}

	public BigDecimal getLaterFundAmount_min() {
		return laterFundAmount_min;
	}

	public void setLaterFundAmount_min(BigDecimal laterFundAmount_min) {
		this.laterFundAmount_min = laterFundAmount_min;
	}

	public BigDecimal getLaterFundAmount_max() {
		return laterFundAmount_max;
	}

	public void setLaterFundAmount_max(BigDecimal laterFundAmount_max) {
		this.laterFundAmount_max = laterFundAmount_max;
	}

	public BigDecimal getRealFundAmount_min() {
		return realFundAmount_min;
	}

	public void setRealFundAmount_min(BigDecimal realFundAmount_min) {
		this.realFundAmount_min = realFundAmount_min;
	}

	public BigDecimal getRealFundAmount_max() {
		return realFundAmount_max;
	}

	public void setRealFundAmount_max(BigDecimal realFundAmount_max) {
		this.realFundAmount_max = realFundAmount_max;
	}

	public BigDecimal getNopayFundAmount_min() {
		return nopayFundAmount_min;
	}

	public void setNopayFundAmount_min(BigDecimal nopayFundAmount_min) {
		this.nopayFundAmount_min = nopayFundAmount_min;
	}

	public BigDecimal getNopayFundAmount_max() {
		return nopayFundAmount_max;
	}

	public void setNopayFundAmount_max(BigDecimal nopayFundAmount_max) {
		this.nopayFundAmount_max = nopayFundAmount_max;
	}

	
}