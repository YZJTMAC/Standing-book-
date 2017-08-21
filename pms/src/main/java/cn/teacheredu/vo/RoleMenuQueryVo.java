package cn.teacheredu.vo;

public class RoleMenuQueryVo extends BaseQueryVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2292065974352554994L;

	private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 权限类型
     */
    private Integer pmType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getPmType() {
		return pmType;
	}

	public void setPmType(Integer pmType) {
		this.pmType = pmType;
	}
    
    

	
}
