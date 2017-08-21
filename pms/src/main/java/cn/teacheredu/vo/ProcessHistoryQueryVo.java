package cn.teacheredu.vo;

import java.util.Date;

public class ProcessHistoryQueryVo extends BaseQueryVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3723068107562710256L;

	private Integer id;

	/**
	 * 流程ID
	 */
	private Integer processId;

	/**
	 * 操作人
	 */
	private Integer userId;

	/**
     * 类型 0 查阅 1 办事处审批 2 分管领导审批 3 总裁审批 4 商务分配
     */
    private Byte type;
    
	/**
	 * 处理结果
	 */
	private String dealResult;

	/**
	 * 处理意见
	 */
	private String dealOpinion;

	/**
	 * 处理时间
	 */
	private Date dealTime;

	/**
	 * 备注
	 */
	private String note;

	private String processTitle;
	
	private String processCreateUserName;
	
	private Date processCreateTime;
	
	private Boolean needJoin = false;
	
	/**
	 * 步骤说明
	 */
	private Integer stepNote;
	
	public Boolean getNeedJoin() {
		return needJoin;
	}

	public void setNeedJoin(Boolean needJoin) {
		this.needJoin = needJoin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
	public String getDealResult() {
		return dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}

	public String getDealOpinion() {
		return dealOpinion;
	}

	public void setDealOpinion(String dealOpinion) {
		this.dealOpinion = dealOpinion;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getProcessCreateTime() {
		return processCreateTime;
	}

	public void setProcessCreateTime(Date processCreateTime) {
		this.processCreateTime = processCreateTime;
	}

	public String getProcessTitle() {
		return processTitle;
	}

	public void setProcessTitle(String processTitle) {
		this.processTitle = processTitle;
	}

	public String getProcessCreateUserName() {
		return processCreateUserName;
	}

	public void setProcessCreateUserName(String processCreateUserName) {
		this.processCreateUserName = processCreateUserName;
	}

	public Integer getStepNote() {
		return stepNote;
	}

	public void setStepNote(Integer stepNote) {
		this.stepNote = stepNote;
	}
}
