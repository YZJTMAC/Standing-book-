package cn.teacheredu.vo;

public class ProjectChangeQueryVo extends BaseQueryVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1693944769944153469L;

	private Integer id;

    /**
     * 提交人
     */
    private Integer userId;

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 流程ID
     */
    private Integer processId;

    /**
     * 被修改的列名
     */
    private String columnName;

    /**
     * 修改后的内容
     */
    private String columnValue;

    /**
     * 修改原因
     */
    private String reason;

    /**
     * 修改原因
     */
    private String note;

    public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
