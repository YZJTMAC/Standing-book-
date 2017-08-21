package cn.teacheredu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.SerialnumEntity;
import cn.teacheredu.mapping.SerialnumMapper;
import cn.teacheredu.service.SerialnumService;
import cn.teacheredu.vo.SerialnumQueryVo;

@Service
public class SerialnumServiceImpl implements SerialnumService{

	@Autowired
	private SerialnumMapper serialnumMapper;
	
	
	@Override
	public SerialnumEntity getSerialnumById(Integer id) throws Exception {
		return this.serialnumMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SerialnumEntity> getSerialnumByVo(SerialnumQueryVo vo) throws Exception {
		return this.serialnumMapper.selectByExample(vo);
	}

	@Override
	public int insertSerialnumEntity(SerialnumEntity serialnumEntity) throws Exception {
		this.serialnumMapper.insertSelective(serialnumEntity);
		return serialnumEntity.getId();
	}

	@Override
	public int updateSerialnum(SerialnumEntity serialnumEntity) throws Exception {
		return this.serialnumMapper.updateByPrimaryKeySelective(serialnumEntity);
	}

	@Override
	public int deteleSerialnumById(Integer id) throws Exception {
		return this.serialnumMapper.deleteByPrimaryKey(id);
	}

	@Override
	public long getCountByVo(SerialnumQueryVo vo) throws Exception {
		return this.serialnumMapper.countByExample(vo);
	}
	
}
