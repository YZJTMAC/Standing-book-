package cn.teacheredu.vo;

public class CompanyQueryVo extends BaseQueryVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    /**
     * 公司名称
     */
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
