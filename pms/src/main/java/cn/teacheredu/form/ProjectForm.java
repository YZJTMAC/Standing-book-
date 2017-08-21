package cn.teacheredu.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProjectForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2976867512616288072L;

	private Integer id;

    /**
     * 提交人
     */
    private Integer userId;

    /**
     * 项目名称
     */
    private String name;
    
    /**
     * 项目编号
     */
    private String serialNumber;
    
    /**
     * 项目类型 1 国培 2 地陪
     */
    private Byte type;

    /**
     * 培训形式  1 在线 2 面授
     */
    private Byte format;

    /**
     * 项目所在省份
     */
    private Integer provincial;

    /**
     * 项目所在城市
     */
    private Integer city;

    /**
     * 项目所属县城
     */
    private Integer county;

    /**
     * 项目所属分管大区代码 -1 分管一区 -2 分管二区 -3 分管三区
     */
    private Integer chargeArea;
    
    /**
     * 培训对象
     */
    private String trainObject;

    /**
     * 培训平台
     */
    private String trainPlatform;

    /**
     * 培训起始日期
     */
    private Date startDate;

    /**
     * 培训结束日期
     */
    private Date endDate;

    /**
     * 报名人数
     */
    private Integer expectedNum;

    /**
     * 元/人
     */
    private BigDecimal chargeStandard;

    /**
     * 学时
     */
    private Integer studyTime;

    /**
     * 预估人数
     */
    private Integer realNum;

    /**
     * 合作单位名称
     */
    private String cooperName;
    
    /**
     * 第三方合作单位
     */
    private String thirdCooperName;

    /**
     * 合作单位地址
     */
    private String cooperAddr;

    /**
     * 合作单位负责人姓名
     */
    private String cooperHeadNameFirst;

    /**
     * 合作单位负责人电话
     */
    private String cooperHeadMobileFirst;

    /**
     * 合作单位负责人职务
     */
    private String cooperHeadJobFirst;

    /**
     * 合作单位负责人邮箱
     */
    private String cooperHeadEmailFirst;

    /**
     * 合作单位负责人姓名
     */
    private String cooperHeadNameSecond;

    /**
     * 合作单位负责人电话
     */
    private String cooperHeadMobileSecond;

    /**
     * 合作单位负责人职务
     */
    private String cooperHeadJobSecond;

    /**
     * 合作单位负责人邮箱
     */
    private String cooperHeadEmailSecond;

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
     * 项目协议书名称
     */
    private String protocolName;
    
    /**
     * 签署日期
     */
    private Date protocolTime;

    /**
     * 到款单位名称
     */
    private String collectMoneyCompany;

    /**
     * 预计到款日期
     */
    private Date collectMoneyDate;

    /**
     * 项目总额
     */
    private BigDecimal totalMoney;

    /**
     * 填表日期
     */
    private Date writeTime;

    /**
     * 提交时间
     */
    private Date genTime;

    /**
     * 项目状态
     */
    private Byte status;

    /**
     * 项目主页URL
     */
    private String url;

    /**
     * 备注
     */
    private String note;

    /**
	 * 专家费用
	 */
	private BigDecimal expertCostBudget;

	/**
	 * 专家费用计算依据
	 */
	private String expertCostBased;

	/**
	 * 专家费用说明
	 */
	private String expertCostExplain;

	/**
	 * 差旅及交通费用
	 */
	private BigDecimal transportCostBudget;

	/**
	 * 差旅及交通计算依据
	 */
	private String transportCostBased;

	/**
	 * 差旅及交通费用说明
	 */
	private String transportCostExplain;

	/**
	 * 食宿费用
	 */
	private BigDecimal accomCostBudget;

	/**
	 * 食宿计算依据
	 */
	private String accomCostBased;

	/**
	 * 食宿费用说明
	 */
	private String accomCostExplain;

	/**
	 * 招待及礼品费用
	 */
	private BigDecimal feteCostBudget;

	/**
	 * 招待及礼品计算依据
	 */
	private String feteCostBased;

	/**
	 * 招待及礼品费用说明
	 */
	private String feteCostExplain;

	/**
	 * 办公及资料费用
	 */
	private BigDecimal officeCostBudget;

	/**
	 * 办公及资料计算依据
	 */
	private String officeCostBased;

	/**
	 * 办公及资料费用说明
	 */
	private String officeCostExplain;

	/**
	 * 场租费
	 */
	private BigDecimal rentalCostBudget;

	/**
	 * 场租费计算依据
	 */
	private String rentalCostBased;

	/**
	 * 场租费说明
	 */
	private String rentalCostExplain;

	/**
	 * 考察费
	 */
	private BigDecimal investCostBudget;

	/**
	 * 考察费计算依据
	 */
	private String investCostBased;

	/**
	 * 考察费说明
	 */
	private String investCostExplain;

	/**
	 * 其它杂费
	 */
	private BigDecimal otherCostBudget;

	/**
	 * 其他杂费计算依据
	 */
	private String otherCostBased;

	/**
	 * 其他杂费说明
	 */
	private String otherCostExplain;

	/**
	 * 专家劳务
	 */
	private BigDecimal expertLabourBudget;

	/**
	 * 专家劳务计算依据
	 */
	private String expertLabourBased;

	/**
	 * 专家劳务说明
	 */
	private String expertLabourExplain;

	/**
	 * 辅导员劳务
	 */
	private BigDecimal counsellorLabourBudget;

	/**
	 * 辅导员劳务计算依据
	 */
	private String counsellorLabourBased;

	/**
	 * 辅导员劳务说明
	 */
	private String counsellorLabourExplain;
	
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getFormat() {
        return format;
    }

    public void setFormat(Byte format) {
        this.format = format;
    }

    public Integer getProvincial() {
        return provincial;
    }

    public void setProvincial(Integer provincial) {
        this.provincial = provincial;
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
    
    public String getTrainObject() {
        return trainObject;
    }

    public void setTrainObject(String trainObject) {
        this.trainObject = trainObject;
    }

    public String getTrainPlatform() {
        return trainPlatform;
    }

    public void setTrainPlatform(String trainPlatform) {
        this.trainPlatform = trainPlatform;
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

    public Integer getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Integer studyTime) {
        this.studyTime = studyTime;
    }

    public Integer getRealNum() {
        return realNum;
    }

    public void setRealNum(Integer realNum) {
        this.realNum = realNum;
    }

    public String getCooperName() {
        return cooperName;
    }

    public void setCooperName(String cooperName) {
        this.cooperName = cooperName;
    }

    public String getCooperAddr() {
        return cooperAddr;
    }

    public void setCooperAddr(String cooperAddr) {
        this.cooperAddr = cooperAddr;
    }

    public String getCooperHeadNameFirst() {
        return cooperHeadNameFirst;
    }

    public void setCooperHeadNameFirst(String cooperHeadNameFirst) {
        this.cooperHeadNameFirst = cooperHeadNameFirst;
    }

    public String getCooperHeadMobileFirst() {
        return cooperHeadMobileFirst;
    }

    public void setCooperHeadMobileFirst(String cooperHeadMobileFirst) {
        this.cooperHeadMobileFirst = cooperHeadMobileFirst;
    }

    public String getCooperHeadJobFirst() {
        return cooperHeadJobFirst;
    }

    public void setCooperHeadJobFirst(String cooperHeadJobFirst) {
        this.cooperHeadJobFirst = cooperHeadJobFirst;
    }

    public String getCooperHeadEmailFirst() {
        return cooperHeadEmailFirst;
    }

    public void setCooperHeadEmailFirst(String cooperHeadEmailFirst) {
        this.cooperHeadEmailFirst = cooperHeadEmailFirst;
    }

    public String getCooperHeadNameSecond() {
        return cooperHeadNameSecond;
    }

    public void setCooperHeadNameSecond(String cooperHeadNameSecond) {
        this.cooperHeadNameSecond = cooperHeadNameSecond;
    }

    public String getCooperHeadMobileSecond() {
        return cooperHeadMobileSecond;
    }

    public void setCooperHeadMobileSecond(String cooperHeadMobileSecond) {
        this.cooperHeadMobileSecond = cooperHeadMobileSecond;
    }

    public String getCooperHeadJobSecond() {
        return cooperHeadJobSecond;
    }

    public void setCooperHeadJobSecond(String cooperHeadJobSecond) {
        this.cooperHeadJobSecond = cooperHeadJobSecond;
    }

    public String getCooperHeadEmailSecond() {
        return cooperHeadEmailSecond;
    }

    public void setCooperHeadEmailSecond(String cooperHeadEmailSecond) {
        this.cooperHeadEmailSecond = cooperHeadEmailSecond;
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

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public Date getProtocolTime() {
        return protocolTime;
    }

    public void setProtocolTime(Date protocolTime) {
        this.protocolTime = protocolTime;
    }
    
    public String getCollectMoneyCompany() {
        return collectMoneyCompany;
    }

    public void setCollectMoneyCompany(String collectMoneyCompany) {
        this.collectMoneyCompany = collectMoneyCompany;
    }

    public Date getCollectMoneyDate() {
        return collectMoneyDate;
    }

    public void setCollectMoneyDate(Date collectMoneyDate) {
        this.collectMoneyDate = collectMoneyDate;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(Date writeTime) {
        this.writeTime = writeTime;
    }

    public Date getGenTime() {
        return genTime;
    }

    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

	public Integer getChargeArea() {
		return chargeArea;
	}

	public void setChargeArea(Integer chargeArea) {
		this.chargeArea = chargeArea;
	}
	public BigDecimal getExpertCostBudget() {
		return expertCostBudget;
	}

	public void setExpertCostBudget(BigDecimal expertCostBudget) {
		this.expertCostBudget = expertCostBudget;
	}

	public String getExpertCostBased() {
		return expertCostBased;
	}

	public void setExpertCostBased(String expertCostBased) {
		this.expertCostBased = expertCostBased;
	}

	public String getExpertCostExplain() {
		return expertCostExplain;
	}

	public void setExpertCostExplain(String expertCostExplain) {
		this.expertCostExplain = expertCostExplain;
	}

	public BigDecimal getTransportCostBudget() {
		return transportCostBudget;
	}

	public void setTransportCostBudget(BigDecimal transportCostBudget) {
		this.transportCostBudget = transportCostBudget;
	}

	public String getTransportCostBased() {
		return transportCostBased;
	}

	public void setTransportCostBased(String transportCostBased) {
		this.transportCostBased = transportCostBased;
	}

	public String getTransportCostExplain() {
		return transportCostExplain;
	}

	public void setTransportCostExplain(String transportCostExplain) {
		this.transportCostExplain = transportCostExplain;
	}

	public BigDecimal getAccomCostBudget() {
		return accomCostBudget;
	}

	public void setAccomCostBudget(BigDecimal accomCostBudget) {
		this.accomCostBudget = accomCostBudget;
	}

	public String getAccomCostBased() {
		return accomCostBased;
	}

	public void setAccomCostBased(String accomCostBased) {
		this.accomCostBased = accomCostBased;
	}

	public String getAccomCostExplain() {
		return accomCostExplain;
	}

	public void setAccomCostExplain(String accomCostExplain) {
		this.accomCostExplain = accomCostExplain;
	}

	public BigDecimal getFeteCostBudget() {
		return feteCostBudget;
	}

	public void setFeteCostBudget(BigDecimal feteCostBudget) {
		this.feteCostBudget = feteCostBudget;
	}

	public String getFeteCostBased() {
		return feteCostBased;
	}

	public void setFeteCostBased(String feteCostBased) {
		this.feteCostBased = feteCostBased;
	}

	public String getFeteCostExplain() {
		return feteCostExplain;
	}

	public void setFeteCostExplain(String feteCostExplain) {
		this.feteCostExplain = feteCostExplain;
	}

	public BigDecimal getOfficeCostBudget() {
		return officeCostBudget;
	}

	public void setOfficeCostBudget(BigDecimal officeCostBudget) {
		this.officeCostBudget = officeCostBudget;
	}

	public String getOfficeCostBased() {
		return officeCostBased;
	}

	public void setOfficeCostBased(String officeCostBased) {
		this.officeCostBased = officeCostBased;
	}

	public String getOfficeCostExplain() {
		return officeCostExplain;
	}

	public void setOfficeCostExplain(String officeCostExplain) {
		this.officeCostExplain = officeCostExplain;
	}

	public BigDecimal getRentalCostBudget() {
		return rentalCostBudget;
	}

	public void setRentalCostBudget(BigDecimal rentalCostBudget) {
		this.rentalCostBudget = rentalCostBudget;
	}

	public String getRentalCostBased() {
		return rentalCostBased;
	}

	public void setRentalCostBased(String rentalCostBased) {
		this.rentalCostBased = rentalCostBased;
	}

	public String getRentalCostExplain() {
		return rentalCostExplain;
	}

	public void setRentalCostExplain(String rentalCostExplain) {
		this.rentalCostExplain = rentalCostExplain;
	}

	public BigDecimal getInvestCostBudget() {
		return investCostBudget;
	}

	public void setInvestCostBudget(BigDecimal investCostBudget) {
		this.investCostBudget = investCostBudget;
	}

	public String getInvestCostBased() {
		return investCostBased;
	}

	public void setInvestCostBased(String investCostBased) {
		this.investCostBased = investCostBased;
	}

	public String getInvestCostExplain() {
		return investCostExplain;
	}

	public void setInvestCostExplain(String investCostExplain) {
		this.investCostExplain = investCostExplain;
	}

	public BigDecimal getOtherCostBudget() {
		return otherCostBudget;
	}

	public void setOtherCostBudget(BigDecimal otherCostBudget) {
		this.otherCostBudget = otherCostBudget;
	}

	public String getOtherCostBased() {
		return otherCostBased;
	}

	public void setOtherCostBased(String otherCostBased) {
		this.otherCostBased = otherCostBased;
	}

	public String getOtherCostExplain() {
		return otherCostExplain;
	}

	public void setOtherCostExplain(String otherCostExplain) {
		this.otherCostExplain = otherCostExplain;
	}

	public BigDecimal getExpertLabourBudget() {
		return expertLabourBudget;
	}

	public void setExpertLabourBudget(BigDecimal expertLabourBudget) {
		this.expertLabourBudget = expertLabourBudget;
	}

	public String getExpertLabourBased() {
		return expertLabourBased;
	}

	public void setExpertLabourBased(String expertLabourBased) {
		this.expertLabourBased = expertLabourBased;
	}

	public String getExpertLabourExplain() {
		return expertLabourExplain;
	}

	public void setExpertLabourExplain(String expertLabourExplain) {
		this.expertLabourExplain = expertLabourExplain;
	}

	public BigDecimal getCounsellorLabourBudget() {
		return counsellorLabourBudget;
	}

	public void setCounsellorLabourBudget(BigDecimal counsellorLabourBudget) {
		this.counsellorLabourBudget = counsellorLabourBudget;
	}

	public String getCounsellorLabourBased() {
		return counsellorLabourBased;
	}

	public void setCounsellorLabourBased(String counsellorLabourBased) {
		this.counsellorLabourBased = counsellorLabourBased;
	}

	public String getCounsellorLabourExplain() {
		return counsellorLabourExplain;
	}

	public void setCounsellorLabourExplain(String counsellorLabourExplain) {
		this.counsellorLabourExplain = counsellorLabourExplain;
	}

	public String getThirdCooperName() {
		return thirdCooperName;
	}

	public void setThirdCooperName(String thirdCooperName) {
		this.thirdCooperName = thirdCooperName;
	}

}
