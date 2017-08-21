package cn.teacheredu.entity;


/**
 * @author 
 */
public class ShortcutEntity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -50853740216462050L;

	private Integer id;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 菜单ID
     */
    private Integer menuId;


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

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}