package cn.teacheredu.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class ProjectSummariesEntity extends BaseEntity {
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
     * 实际项目总额：a=（预计项目总额-前期经费）b=到款金额 a >b ？ a  ： b	。
     * （字段计算公式，问题是否兼容系统中项目完结表的实际项目总额字段计算）
     */
//    private BigDecimal predictRealTotalAmount;
    private BigDecimal projectRealTotalAmount;
    
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
	 * 应收账款：已结转收入金额-已到款金额
	 */
    private BigDecimal predictReceiveAmount;

    /**
     * 已结转收入
     */
    private BigDecimal realIncomeAmount;
   
    /**
     * 12-15年已结转收入
     */
    private BigDecimal realIncomeAmount12To15;

    /**
     * 前期后期经费比例，注意%，数据库中字段存储的值没有百分号
     */
    private BigDecimal proFundProportion;

    private BigDecimal laterFundProportion;

    /**
     * 前期后期经费金额：预计项目总额*前期后期比例
     */
    private BigDecimal proFundAmount;

    private BigDecimal laterFundAmount;

    private BigDecimal realFundAmount;
    
    /**
     * 尚未支付经费：后期经费比例金额-已支付经费合计
     */
    private BigDecimal nopayFundAmount;

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

    private String url;
    
    private String note;
    
    private Integer projectChargeArea;

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
    
    
    private List<ProjectYearSummaryEntity> projectYearSummarys;
    
    private Integer total;
    
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public BigDecimal getRealIncomeAmount12To15() {
		return realIncomeAmount12To15;
	}

	public void setRealIncomeAmount12To15(BigDecimal realIncomeAmount12To15) {
		this.realIncomeAmount12To15 = realIncomeAmount12To15;
	}
}