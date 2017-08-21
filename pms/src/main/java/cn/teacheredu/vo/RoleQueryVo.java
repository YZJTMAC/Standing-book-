package cn.teacheredu.vo;

import java.util.Date;

public class RoleQueryVo extends BaseQueryVo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1704944582502062643L;

	private Integer id;

    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 角色类型，保留字段
     */
    private String roleType;

    /**
     * 父级角色id
     */
    private Integer parentId;

    /**
     * 创建时间
     */
    private Date genTime;

    /**
     * 角色描述
     */
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getGenTime() {
        return genTime;
    }

    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
	
}
