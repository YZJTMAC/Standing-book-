package cn.teacheredu.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import cn.teacheredu.controller.covert.CustomJsonDateDeserializer;

public class TestForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8252838770879889128L;

	/**
     * 用户名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 部门ID
     */
    private Integer dmId;

    /**
     * 创建时间
     */
    private Date genTime;
    
    /**
     * 项目名称
     */
    private String name;
    
    /**
     * 项目类型 1 国培 2 地陪
     */
    private Byte type;
    
    /**
     * 元/人
     */
    private BigDecimal chargeStandard;
    
    /**
     * 项目总额
     */
    private BigDecimal totalMoney;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDmId() {
		return dmId;
	}

	public void setDmId(Integer dmId) {
		this.dmId = dmId;
	}

	public Date getGenTime() {
		return genTime;
	}
	
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public BigDecimal getChargeStandard() {
		return chargeStandard;
	}

	public void setChargeStandard(BigDecimal chargeStandard) {
		this.chargeStandard = chargeStandard;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
}
