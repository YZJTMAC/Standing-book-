package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.ShortcutEntity;
import cn.teacheredu.vo.ShortcutQueryVo;

public interface ShortcutService {

	ShortcutEntity getShortcutById(Integer id) throws Exception;
	
	List<ShortcutEntity> getShortcutByVo(ShortcutQueryVo vo) throws Exception;
	
	int insertShortcutEntity(ShortcutEntity shortcutEntity) throws Exception;
	
	long getCountByVo(ShortcutQueryVo vo) throws Exception;
	
	int updateShortcut(ShortcutEntity shortcutEntity) throws Exception;
	
	int deteleShortcutById(Integer id) throws Exception;
	
	List<Integer> getMenuByUid(Integer id) throws Exception;
}
