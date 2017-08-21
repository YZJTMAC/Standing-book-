package cn.teacheredu.entity;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import cn.teacheredu.controller.covert.CustomJsonDateDeserializer;

/**
 * @author zzj
 */
public class DepartmentEntity extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3049543039379139250L;

	private Integer id;

    /**
     * 上级部门ID
     */
    private Integer parentId;

    /**
     * 部门主管ID
     */
    private String directorId;

    /**
     * 部门名称
     */
    private String dmName;

    /**
     * 部门类型，保留字段
     */
    private String dmType;

    /**
     * 部门级别，保留字段
     */
    private String level;
    
    /**
     * 部门所属省份，非办事处、子公司的部门为空即可
     */
    private Integer province;
    
    /**
     * 部门描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date genTime;

    /**
     * 部门人员
     */
    private List<Integer> userIdList;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDirectorId() {
        return directorId;
    }

    public void setDirectorId(String directorId) {
        this.directorId = directorId;
    }

    public String getDmName() {
        return dmName;
    }

    public void setDmName(String dmName) {
        this.dmName = dmName;
    }

    public String getDmType() {
        return dmType;
    }

    public void setDmType(String dmType) {
        this.dmType = dmType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getGenTime() {
        return genTime;
    }
    
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

	public List<Integer> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<Integer> userIdList) {
		this.userIdList = userIdList;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}
    
    
}