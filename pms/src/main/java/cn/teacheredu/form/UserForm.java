package cn.teacheredu.form;

import java.io.Serializable;

/**
 * 用于修改个人信息、密码、手机号
 */
public class UserForm implements Serializable {
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
     * 新密码
     */
    private String newPwd;
    
    /**
     * 新密码
     */
    private String againPwd;

	/**
	 * 验证码
	 */
	private String validCode;

    /**
     * 手机号码
     */
    private String mobile;

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

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getAgainPwd() {
		return againPwd;
	}

	public void setAgainPwd(String againPwd) {
		this.againPwd = againPwd;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    
}