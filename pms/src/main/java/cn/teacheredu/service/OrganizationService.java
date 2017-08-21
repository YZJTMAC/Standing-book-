package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.OrganizationEntity;
import cn.teacheredu.vo.OrganizationQueryVo;

/**
 * 省市县表
 * 
 * @author teacheredu
 *
 */
public interface OrganizationService {

	OrganizationEntity getOrganizationById(Integer orgId) throws Exception;

	List<OrganizationEntity> getOrganizationByVo(OrganizationQueryVo vo) throws Exception;

	int insertOrganizationEntity(OrganizationEntity orgEntity) throws Exception;

	long getCountByVo(OrganizationQueryVo vo) throws Exception;

	int updateOrganization(OrganizationEntity orgEntity) throws Exception;

	int deteleOrganizationById(Integer orgId) throws Exception;
	
	List<OrganizationEntity> getListByParentId(Integer parentId) throws Exception;

	String getNameById(Integer id) throws Exception;
	
	Integer getIdByName(String name) throws Exception;

	/**
	 * 生成项目名称
	 * @param pId  省
	 * @param cId  市
	 * @param coId 县
	 * @param name 名称
	 * @return
	 * @throws Exception
	 */
	String generateProjecctNameByIds(Integer pId, Integer cId, Integer coId, String name)
			throws Exception;
	
	/**
	 * 生成项目编码
	 * @param pId  省
	 * @param cId	市
	 * @param coId  县
	 * @param type  远程or面授
	 * @param trainObject  培训对象
	 * @return
	 * @throws Exception
	 */
	String generateSerialNameByIds(Integer pId, Integer cId, Integer coId, Byte format, String trainObject)
			throws Exception;
}
