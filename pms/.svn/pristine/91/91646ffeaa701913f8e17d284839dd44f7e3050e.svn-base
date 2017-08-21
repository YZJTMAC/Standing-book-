package cn.teacheredu.entity;

import java.util.Date;

/**
 * @author
 */
public class ProcessHistoryEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7382104007766544895L;

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
     * 类型 1 审批 2 查阅 3 编辑
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

	private ProcessEntity processEntity;
	
	/**
	 * 流程历史对应的need_deal_process表ID
	 */
	private Integer needId;
	
	/**
	 * 步骤说明
	 */
	private Integer stepNote;
	
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

	public ProcessEntity getProcessEntity() {
		return processEntity;
	}

	public void setProcessEntity(ProcessEntity processEntity) {
		this.processEntity = processEntity;
	}

	public Integer getNeedId() {
		return needId;
	}

	public void setNeedId(Integer needId) {
		this.needId = needId;
	}

	public Integer getStepNote() {
		return stepNote;
	}

	public void setStepNote(Integer stepNote) {
		this.stepNote = stepNote;
	}
}