package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.SerialnumEntity;
import cn.teacheredu.vo.SerialnumQueryVo;


public interface SerialnumService {

	SerialnumEntity getSerialnumById(Integer id) throws Exception;
	
	List<SerialnumEntity> getSerialnumByVo(SerialnumQueryVo vo) throws Exception;
	
	int insertSerialnumEntity(SerialnumEntity serialnumEntity) throws Exception;
	
	int updateSerialnum(SerialnumEntity serialnumEntity) throws Exception;
	
	int deteleSerialnumById(Integer id) throws Exception;
	
	long getCountByVo(SerialnumQueryVo vo) throws Exception;
	
}
