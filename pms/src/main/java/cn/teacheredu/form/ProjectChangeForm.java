package cn.teacheredu.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 信息变更表form
 * 
 * @author jiajinlong
 *
 */
public class ProjectChangeForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 报名人数
	 */
	private Integer expectedNum;

	/**
	 * 元/人
	 */
	private BigDecimal chargeStandard;
	
	/**
	 * 前期经费省级所占比例
	 */
	private String proFundProvincial;

	/**
	 * 前期经费市级所占比例
	 */
	private String proFundCity;

	/**
	 * 前期经费县级所占比例
	 */
	private String proFundCounty;

	/**
	 * 前期经费其他所占比例
	 */
	private String proFundOther;

	/**
	 * 后期经费省级所占比例
	 */
	private String laterFundProvincial;

	/**
	 * 后期经费市级所占比例
	 */
	private String laterFundCity;

	/**
	 * 后期经费县级所占比例
	 */
	private String laterFundCounty;

	/**
	 * 后期经费其他所占比例
	 */
	private String laterFundOther;
	
	/**
	 * 培训起始日期
	 */
	private Date startDate;

	/**
	 * 培训结束日期
	 */
	private Date endDate;
	
	/**
	 * 到款单位名称
	 */
	private String collectMoneyCompany;
	
	/**
	 * 报名人（校）数的变更原因
	 */
	private String expectedNumReason;

	/**
	 * 元/人的变更原因
	 */
	private String chargeStandardReason;
	
	/**
	 * 前期经费省级所占比例的变更原因
	 */
	private String proFundProvincialReason;

	/**
	 * 前期经费市级所占比例的变更原因
	 */
	private String proFundCityReason;

	/**
	 * 前期经费县级所占比例的变更原因
	 */
	private String proFundCountyReason;

	/**
	 * 前期经费其他所占比例的变更原因
	 */
	private String proFundOtherReason;

	/**
	 * 后期经费省级所占比例的变更原因
	 */
	private String laterFundProvincialReason;

	/**
	 * 后期经费市级所占比例的变更原因
	 */
	private String laterFundCityReason;

	/**
	 * 后期经费县级所占比例的变更原因
	 */
	private String laterFundCountyReason;

	/**
	 * 后期经费其他所占比例的变更原因
	 */
	private String laterFundOtherReason;
	
	/**
	 * 培训起始日期的变更原因
	 */
	private String startDateReason;

	/**
	 * 培训结束日期的变更原因
	 */
	private String endDateReason;
	
	/**
	 * 到款单位名称的变更原因
	 */
	private String collectMoneyCompanyReason;
	
	/**
	 * 备注
	 */
	private String note;
	
	private CommonForm common;

	private Integer projectId;
	
	private Integer userId;
	
	private String userName;
	
	private Integer currStepUserId;
	
	/**
	 * 审批流程类型
	 */
	private Integer type;
	
	/**
     * 对应步骤ID
     */
	private Integer stepId;

	public Integer getExpectedNum() {
		return expectedNum;
	}

	public void setExpectedNum(Integer expectedNum) {
		this.expectedNum = expectedNum;
	}

	public BigDecimal getChargeStandard() {
		return chargeStandard;
	}

	public void setChargeStandard(BigDecimal chargeStandard) {
		this.chargeStandard = chargeStandard;
	}

	public String getProFundProvincial() {
		return proFundProvincial;
	}

	public void setProFundProvincial(String proFundProvincial) {
		this.proFundProvincial = proFundProvincial;
	}

	public String getProFundCity() {
		return proFundCity;
	}

	public void setProFundCity(String proFundCity) {
		this.proFundCity = proFundCity;
	}

	public String getProFundCounty() {
		return proFundCounty;
	}

	public void setProFundCounty(String proFundCounty) {
		this.proFundCounty = proFundCounty;
	}

	public String getProFundOther() {
		return proFundOther;
	}

	public void setProFundOther(String proFundOther) {
		this.proFundOther = proFundOther;
	}

	public String getLaterFundProvincial() {
		return laterFundProvincial;
	}

	public void setLaterFundProvincial(String laterFundProvincial) {
		this.laterFundProvincial = laterFundProvincial;
	}

	public String getLaterFundCity() {
		return laterFundCity;
	}

	public void setLaterFundCity(String laterFundCity) {
		this.laterFundCity = laterFundCity;
	}

	public String getLaterFundCounty() {
		return laterFundCounty;
	}

	public void setLaterFundCounty(String laterFundCounty) {
		this.laterFundCounty = laterFundCounty;
	}

	public String getLaterFundOther() {
		return laterFundOther;
	}

	public void setLaterFundOther(String laterFundOther) {
		this.laterFundOther = laterFundOther;
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

	public String getCollectMoneyCompany() {
		return collectMoneyCompany;
	}

	public void setCollectMoneyCompany(String collectMoneyCompany) {
		this.collectMoneyCompany = collectMoneyCompany;
	}

	public String getExpectedNumReason() {
		return expectedNumReason;
	}

	public void setExpectedNumReason(String expectedNumReason) {
		this.expectedNumReason = expectedNumReason;
	}

	public String getChargeStandardReason() {
		return chargeStandardReason;
	}

	public void setChargeStandardReason(String chargeStandardReason) {
		this.chargeStandardReason = chargeStandardReason;
	}

	public String getProFundProvincialReason() {
		return proFundProvincialReason;
	}

	public void setProFundProvincialReason(String proFundProvincialReason) {
		this.proFundProvincialReason = proFundProvincialReason;
	}

	public String getProFundCityReason() {
		return proFundCityReason;
	}

	public void setProFundCityReason(String proFundCityReason) {
		this.proFundCityReason = proFundCityReason;
	}

	public String getProFundCountyReason() {
		return proFundCountyReason;
	}

	public void setProFundCountyReason(String proFundCountyReason) {
		this.proFundCountyReason = proFundCountyReason;
	}

	public String getProFundOtherReason() {
		return proFundOtherReason;
	}

	public void setProFundOtherReason(String proFundOtherReason) {
		this.proFundOtherReason = proFundOtherReason;
	}

	public String getLaterFundProvincialReason() {
		return laterFundProvincialReason;
	}

	public void setLaterFundProvincialReason(String laterFundProvincialReason) {
		this.laterFundProvincialReason = laterFundProvincialReason;
	}

	public String getLaterFundCityReason() {
		return laterFundCityReason;
	}

	public void setLaterFundCityReason(String laterFundCityReason) {
		this.laterFundCityReason = laterFundCityReason;
	}

	public String getLaterFundCountyReason() {
		return laterFundCountyReason;
	}

	public void setLaterFundCountyReason(String laterFundCountyReason) {
		this.laterFundCountyReason = laterFundCountyReason;
	}

	public String getLaterFundOtherReason() {
		return laterFundOtherReason;
	}

	public void setLaterFundOtherReason(String laterFundOtherReason) {
		this.laterFundOtherReason = laterFundOtherReason;
	}

	public String getStartDateReason() {
		return startDateReason;
	}

	public void setStartDateReason(String startDateReason) {
		this.startDateReason = startDateReason;
	}

	public String getEndDateReason() {
		return endDateReason;
	}

	public void setEndDateReason(String endDateReason) {
		this.endDateReason = endDateReason;
	}

	public String getCollectMoneyCompanyReason() {
		return collectMoneyCompanyReason;
	}

	public void setCollectMoneyCompanyReason(String collectMoneyCompanyReason) {
		this.collectMoneyCompanyReason = collectMoneyCompanyReason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public CommonForm getCommon() {
		return common;
	}

	public void setCommon(CommonForm common) {
		this.common = common;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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

	public Integer getCurrStepUserId() {
		return currStepUserId;
	}

	public void setCurrStepUserId(Integer currStepUserId) {
		this.currStepUserId = currStepUserId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStepId() {
		return stepId;
	}

	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}
	
}
