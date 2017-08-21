package cn.teacheredu.vo;

public class ProcessDefineQueryVo extends BaseQueryVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8271365630960492069L;

	/**
     * 流程定义ID
     */
    private Integer id;
 
    /**
     * 流程定义名称
     */
    private String defineName;
    
    /**
     * 流程对应实体的表名
     */
    private String tableName;
    
    /**
     * 流程期限，单位：小时
     */
    private Integer limitTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDefineName() {
		return defineName;
	}

	public void setDefineName(String defineName) {
		this.defineName = defineName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Integer limitTime) {
		this.limitTime = limitTime;
	}

    
}
