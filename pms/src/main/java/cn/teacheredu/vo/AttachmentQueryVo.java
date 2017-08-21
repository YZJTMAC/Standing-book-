package cn.teacheredu.vo;

import java.util.Date;

public class AttachmentQueryVo extends BaseQueryVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1411186599945846544L;
	
	private Integer id;

    /**
     * 附件对应的实体ID
     */
    private Integer objectId;

    /**
     * 附件对应的实体表名
     */
    private String tableName;

    /**
     * 附件类型
     */
    private String filetype;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 附件地址
     */
    private String url;

    /**
     * 创建时间
     */
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
