package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.SystemLogEntity;
import cn.teacheredu.vo.SystemLogQueryVo;



public interface SystemLogService {

	SystemLogEntity getSystemLogById(Integer logId) throws Exception;
	
	List<SystemLogEntity> getSystemLogVo(SystemLogQueryVo vo) throws Exception;
	
	int insertSystemLogEntity(SystemLogEntity systemLogtEntity) throws Exception;
	
	long getCountByVo(SystemLogQueryVo vo) throws Exception;
	
	int updateSystemLog(SystemLogEntity systemLogtEntity) throws Exception;
	
	/**
	 * 插入一条系统日志
	 * @param type 类型 0 select 1 save 2 update 3 detele
	 * @param content 日志内容
	 * @param uid 用户ID 
	 * @param date 时间
	 * @return 插入结果
	 * @throws Exception
	 */
	boolean saveSystemLog(int type, String content, int uid) throws Exception;
	
}
