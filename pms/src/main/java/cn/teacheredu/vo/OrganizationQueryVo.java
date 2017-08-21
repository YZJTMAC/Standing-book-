package cn.teacheredu.vo;

public class OrganizationQueryVo extends BaseQueryVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7823658838106024735L;

	private Integer id;

    /**
     * 上级ID 省的上级为0
     */
    private Integer parentId;

    /**
     * 省市县名称
     */
    private String name;

    /**
     * 地区编码
     */
    private String code;

    /**
     * 文件序号
     */
    private Byte num;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getNum() {
        return num;
    }

    public void setNum(Byte num) {
        this.num = num;
    }
}
