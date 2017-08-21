package cn.teacheredu.form;

import java.io.Serializable;
import java.util.List;

import cn.teacheredu.entity.PaymentEntity;

public class PaymentForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 流程名称
     */
    private String processName;
    
    /**
     * 附件ID
     */
    private Integer[] attachIds;
    
    /**
     * 是否是草稿
     */
    private Boolean shiCaogao;
	
	private List<PaymentEntity> paymentList;

	private String note;
	
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
	
	public List<PaymentEntity> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<PaymentEntity> paymentList) {
		this.paymentList = paymentList;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer[] getAttachIds() {
		return attachIds;
	}

	public void setAttachIds(Integer[] attachIds) {
		this.attachIds = attachIds;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Boolean getShiCaogao() {
		return shiCaogao;
	}

	public void setShiCaogao(Boolean shiCaogao) {
		this.shiCaogao = shiCaogao;
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
