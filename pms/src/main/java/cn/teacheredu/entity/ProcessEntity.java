package cn.teacheredu.entity;

import java.util.Date;

/**
 * @author 
 */
public class ProcessEntity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9080266225562347546L;

	/**
     * 流程ID
     */
    private Integer id;

    /**
     * 流程标题
     */
    private String title;

    /**
     * 流程对应的项目ID
     */
    private Integer projectId;

    /**
     * 流程类型（保留字段）
     */
    private Integer type;

    /**
     * 发起人
     */
    private Integer createUserId;

    /**
     * 发起人姓名
     */
    private String createUserName;
    
    /**
     * 发起时间
     */
    private Date startTime;

    /**
     * 流程对应实体的表名
     */
    private String tableName;

    /**
     * 流程对应实体的ID
     */
    private Integer objectId;

    /**
     * 流程期限，单位：天，保留字段
     */
    private Integer totalTime;

    /**
     * 上一个已完成的步骤的处理人
     */
    private Integer lastStepUserId;

    /**
     * 上一个步骤完成的时间
     */
    private Date lastStepTime;

    /**
     * 当前处理人
     */
    private Integer currStepUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 流程状态 0 草稿 1 已撤销 2 被退回 3 进行中 4 正常结束
     */
    private Integer status;

    
    /**
     * 当前流程对应步骤
     */
    private Integer stepId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getLastStepUserId() {
        return lastStepUserId;
    }

    public void setLastStepUserId(Integer lastStepUserId) {
        this.lastStepUserId = lastStepUserId;
    }

    public Date getLastStepTime() {
        return lastStepTime;
    }

    public void setLastStepTime(Date lastStepTime) {
        this.lastStepTime = lastStepTime;
    }

    public Integer getCurrStepUserId() {
        return currStepUserId;
    }

    public void setCurrStepUserId(Integer currStepUserId) {
        this.currStepUserId = currStepUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Integer getStepId() {
		return stepId;
	}

	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}
}