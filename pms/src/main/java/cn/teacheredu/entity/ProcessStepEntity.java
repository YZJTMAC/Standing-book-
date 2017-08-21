package cn.teacheredu.entity;

/**
 * 流程步骤实体
 */
public class ProcessStepEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3843980144561419091L;

	/**
     * 流程步骤ID
     */
    private Integer id;
 
    /**
     * 流程定义ID
     */
    private Integer defineId;
    
    /**
     * 流程名称
     */
    private String stepName;
    
    /**
     * 当前步骤对应部门
     */
    private Integer dmId;
    
    /**
     * 当前步骤对应部门处理人
     */
    private Integer userId;
    
    /**
     * 下一个步骤
     */
    private Integer nextStepId;
    
    /**
     * 当前步骤类型 
     */
    private Integer type;

    /**
     * 步骤开始标识   1起始节点
     */
    private String beginStatus;
    
    /**
     * 步骤结束标识   1结束节点
     */
    private String endStatus;
    
    /**
     * 当前步骤说明     1办事处、分公司、子公司处理人    2分管部门处理人     3商务部处理人     4总经理办公室处理人    5教务部处理人   6财务部处理人
     */
    private Integer stepNote;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDefineId() {
		return defineId;
	}

	public void setDefineId(Integer defineId) {
		this.defineId = defineId;
	}

	public Integer getDmId() {
		return dmId;
	}

	public void setDmId(Integer dmId) {
		this.dmId = dmId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStepNote() {
		return stepNote;
	}

	public void setStepNote(Integer stepNote) {
		this.stepNote = stepNote;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getNextStepId() {
		return nextStepId;
	}

	public void setNextStepId(Integer nextStepId) {
		this.nextStepId = nextStepId;
	}

	public String getBeginStatus() {
		return beginStatus;
	}

	public void setBeginStatus(String beginStatus) {
		this.beginStatus = beginStatus;
	}

	public String getEndStatus() {
		return endStatus;
	}

	public void setEndStatus(String endStatus) {
		this.endStatus = endStatus;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
}
