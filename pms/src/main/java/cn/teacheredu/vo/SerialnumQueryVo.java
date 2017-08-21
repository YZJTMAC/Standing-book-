package cn.teacheredu.vo;

import java.util.Date;

public class SerialnumQueryVo extends BaseQueryVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6924258374779579145L;

	private Integer id;

    /**
     * 已经生成过的项目编码
     */
    private String serialNum;

    /**
     * 生成日期
     */
    private Date genTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public Date getGenTime() {
        return genTime;
    }

    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }
	
}
