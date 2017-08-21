package cn.teacheredu.entity;

import java.util.Date;

/**
 * @author zzj
 */
public class LoginLogEntity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8935999063182261574L;

	private Integer id;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 用户名
     */
    private String name;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 短信验证码
     */
    private String verificationCode;

    /**
     * 登录是否成功
     */
    private Integer succeed;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Integer getSucceed() {
        return succeed;
    }

    public void setSucceed(Integer succeed) {
        this.succeed = succeed;
    }
}