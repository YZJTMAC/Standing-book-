package cn.teacheredu.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.teacheredu.entity.PaymentInvoiceEntity;

public class InvoiceForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2976867512616288072L;
	
	private Integer id;

    /**
     * 申请人
     */
    private Integer uid;

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 类型 1：已到款开发票  2：未到款开发票
     */
    private Byte type;

    /**
     * 收件人姓名
     */
    private String postName;

    /**
     * 收件人电话
     */
    private String postMobile;

    /**
     * 收件人地址
     */
    private String postAddr;

    /**
     * 收件人公司
     */
    private String postCompany;

    /**
     * 提前开发票的情况下承诺汇款日期
     */
    private Date advancePaydate;

    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 对应的PaymentInvoiceEntity集合
     */
    private List<PaymentInvoiceEntity> paymentInvoiceList;
    
    /**
     * 备注
     */
    private String note;

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

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPostMobile() {
		return postMobile;
	}

	public void setPostMobile(String postMobile) {
		this.postMobile = postMobile;
	}

	public String getPostAddr() {
		return postAddr;
	}

	public void setPostAddr(String postAddr) {
		this.postAddr = postAddr;
	}

	public String getPostCompany() {
		return postCompany;
	}

	public void setPostCompany(String postCompany) {
		this.postCompany = postCompany;
	}

	public Date getAdvancePaydate() {
		return advancePaydate;
	}

	public void setAdvancePaydate(Date advancePaydate) {
		this.advancePaydate = advancePaydate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<PaymentInvoiceEntity> getPaymentInvoiceList() {
		return paymentInvoiceList;
	}

	public void setPaymentInvoiceList(List<PaymentInvoiceEntity> paymentInvoiceList) {
		this.paymentInvoiceList = paymentInvoiceList;
	}
    
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
    
    
    
}