package cn.teacheredu.entity;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import cn.teacheredu.controller.covert.CustomJsonDateDeserializer;

/**
 * @author zzj
 */
public class UserEntity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7474624296453706318L;

	private Integer id;

    /**
     * 用户名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 部门ID
     */
    private Integer dmId;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 用户姓名
     */
    private String realname;

    /**
     * 1 男 2 女
     */
    private String sex;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 用户头像图片地址
     */
    private String pic;

    /**
     * 备注
     */
    private String note;

    /**
     * 创建时间
     */
    private Date genTime;

    /**
     * 最近一次的修改时间
     */
    private Date updateTime;

    /**
     * 是否已禁用 0 正常 1 已禁用
     */
    private Integer available;

    /**
     * 禁用时间
     */
    private Date deleteTime;

    
    /**
     * 所属部门Ids
     */
    private List<Integer> dmIdList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDmId() {
        return dmId;
    }

    public void setDmId(Integer dmId) {
        this.dmId = dmId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getGenTime() {
        return genTime;
    }
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

	public List<Integer> getDmIdList() {
		return dmIdList;
	}

	public void setDmIdList(List<Integer> dmIdList) {
		this.dmIdList = dmIdList;
	}
}