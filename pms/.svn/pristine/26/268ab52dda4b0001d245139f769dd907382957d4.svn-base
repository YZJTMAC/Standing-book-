package cn.teacheredu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.CompanyEntity;
import cn.teacheredu.mapping.CompanyMapper;
import cn.teacheredu.service.CompanyService;
import cn.teacheredu.vo.CompanyQueryVo;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyMapper companyMapper;

	@Override
	public CompanyEntity getCompanyById(Integer companyId) throws Exception {
		
		return this.companyMapper.selectByPrimaryKey(companyId);
	}

	@Override
	public List<CompanyEntity> getCompanyByVo(CompanyQueryVo vo) throws Exception {
		return this.companyMapper.selectByExample(vo);
	}

	@Override
	public int insertCompany(CompanyEntity companyEntity) throws Exception {
		this.companyMapper.insertSelective(companyEntity);
		return companyEntity.getId();
	}

	@Override
	public long getCountByVo(CompanyQueryVo vo) throws Exception {
		return this.companyMapper.countByExample(vo);
	}

	@Override
	public int updateCompany(CompanyEntity companyEntity) throws Exception {
		return this.companyMapper.updateByPrimaryKeySelective(companyEntity);
	}

	@Override
	public int deletecompanyIdById(Integer companyId) throws Exception {
		return this.companyMapper.deleteByPrimaryKey(companyId);
	}
	
	

}
