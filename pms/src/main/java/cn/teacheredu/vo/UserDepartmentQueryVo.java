package cn.teacheredu.vo;

public class UserDepartmentQueryVo extends BaseQueryVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2129916289692443883L;
	
	private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 部门ID
     */
    private Integer dmId;

    /**
     * 备注
     */
    private String note;

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

    public Integer getDmId() {
        return dmId;
    }

    public void setDmId(Integer dmId) {
        this.dmId = dmId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
