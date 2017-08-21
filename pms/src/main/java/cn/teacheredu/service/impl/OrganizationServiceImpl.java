package cn.teacheredu.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.OrganizationEntity;
import cn.teacheredu.entity.SerialnumEntity;
import cn.teacheredu.mapping.OrganizationMapper;
import cn.teacheredu.service.OrganizationService;
import cn.teacheredu.service.SerialnumService;
import cn.teacheredu.utils.SpyMemcachedManager;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.OrganizationQueryVo;
import cn.teacheredu.vo.SerialnumQueryVo;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private SpyMemcachedManager memcachedManager;
	@Autowired
	private SerialnumService serialnumService;
	
	@Override
	public OrganizationEntity getOrganizationById(Integer orgId) throws Exception {
		return this.organizationMapper.selectByPrimaryKey(orgId);
	}

	@Override
	public List<OrganizationEntity> getOrganizationByVo(OrganizationQueryVo vo) throws Exception {
		return this.organizationMapper.selectByExample(vo);
	}

	@Override
	public int insertOrganizationEntity(OrganizationEntity orgEntity) throws Exception {
		this.organizationMapper.insertSelective(orgEntity);
		return orgEntity.getId();
	}

	@Override
	public long getCountByVo(OrganizationQueryVo vo) throws Exception {
		return this.organizationMapper.countByExample(vo);
	}

	@Override
	public int updateOrganization(OrganizationEntity orgEntity) throws Exception {
		return this.organizationMapper.updateByPrimaryKeySelective(orgEntity);
	}

	@Override
	public int deteleOrganizationById(Integer orgId) throws Exception {
		return this.organizationMapper.deleteByPrimaryKey(orgId);
	}

	@Override
	public List<OrganizationEntity> getListByParentId(Integer parentId) throws Exception {
		@SuppressWarnings("unchecked")
		List<OrganizationEntity> list = (List<OrganizationEntity>) memcachedManager.get("pms_organiza_list_parent"+parentId);
		if (list == null) {
			OrganizationQueryVo vo = new OrganizationQueryVo();
			vo.setParentId(parentId);
			vo.setOrderProperty("id");
			vo.setOrderType(SystemConst.SQL_ORDERTYPE_ASC);
			list = this.getOrganizationByVo(vo);
			memcachedManager.set("pms_organiza_list_parent"+parentId,list,600);
		}
		
		return list;
	}
	
	@Override
	public String getNameById(Integer id) throws Exception {
		if (id == null || id == 0) {
			return "";
		}
		OrganizationEntity organizationEntity = this.getOrganizationById(id);
		return organizationEntity == null ? "" : organizationEntity.getName();
	}
	
	@Override
	public String generateSerialNameByIds(Integer pId, Integer cId, Integer coId, Byte format, String trainObject) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(format == 1 ? "N": "F");
		if ("教师培训".equals(trainObject)) {
			sb.append("T");
		} else if ("校长培训".equals(trainObject)) {
			sb.append("P");
		} else if ("学历教育".equals(trainObject)) {
			sb.append("D");
		} else if ("研究生进修班".equals(trainObject)) {
			sb.append("G");
		} else if ("职业教育".equals(trainObject)) {
			sb.append("V");
		} else if ("教师资格认定".equals(trainObject)) {
			sb.append("Q");
		} else {
			sb.append("E");
		}
		sb.append(DateFormatUtils.format(new Date(), "yy"));
		Integer id = null;
		if (coId != null && coId != 0) {
			id = coId;
		} else if (cId != null && cId != 0) {
			id = cId;
		} else {
			id = pId;
		}
		OrganizationEntity org = this.getOrganizationById(id);
		if (org != null) {
			sb.append(org.getCode());
		}
		String serial = sb.toString();
		if (serial.length() != 10) { //到这为止不出意外的话已经是10位了
			throw new Exception("生成项目编码时出错");
		} else {
			//加上文件序号
			SerialnumQueryVo vo = new SerialnumQueryVo();
			vo.setSerialNum(serial);
			long count = this.serialnumService.getCountByVo(vo);
			count += 1;
			if (String.valueOf(count).length() == 1) {
				sb.append("0"+String.valueOf(count));
			} else {
				sb.append(String.valueOf(count));
			}
			SerialnumEntity serialnumEntity = new SerialnumEntity();
			serialnumEntity.setSerialNum(serial);
			serialnumEntity.setGenTime(new Date());
			this.serialnumService.insertSerialnumEntity(serialnumEntity);
		}
		if (sb.toString().length() != 12)  //到这为止不出意外的话已经是12位了
			throw new Exception("生成项目编码时出错");
		return sb.toString();
	}

	@Override
	public String generateProjecctNameByIds(Integer pId, Integer cId, Integer coId, String name) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getNameById(pId));
		String city = this.getNameById(cId);
		if (!"县".equals(city) && !"市辖区".equals(city)) {
			sb.append(city);
		}
		String county = this.getNameById(coId);
		if (!"市辖区".equals(county)) {
			sb.append(county);
		}
		sb.append(name);
		sb.append("项目");
		return sb.toString();
	}

	@Override
	public Integer getIdByName(String name) throws Exception {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		OrganizationQueryVo vo = new OrganizationQueryVo();
		vo.setName(name);
		List<OrganizationEntity> list = this.getOrganizationByVo(vo);
		if (list.size() == 1) {
			return list.get(0).getId();
		}
		return null;
	}
}
