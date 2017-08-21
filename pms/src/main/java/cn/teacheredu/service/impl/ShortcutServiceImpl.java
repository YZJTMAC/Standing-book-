package cn.teacheredu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.ShortcutEntity;
import cn.teacheredu.mapping.ShortcutMapper;
import cn.teacheredu.service.ShortcutService;
import cn.teacheredu.vo.ShortcutQueryVo;

@Service
public class ShortcutServiceImpl implements ShortcutService{

	@Autowired
	private ShortcutMapper shortcutMapper;
	
	@Override
	public ShortcutEntity getShortcutById(Integer id) throws Exception {
		return this.shortcutMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ShortcutEntity> getShortcutByVo(ShortcutQueryVo vo) throws Exception {
		return this.shortcutMapper.selectByExample(vo);
	}

	@Override
	public int insertShortcutEntity(ShortcutEntity shortcutEntity) throws Exception {
		this.shortcutMapper.insertSelective(shortcutEntity);
		return shortcutEntity.getId();
	}

	@Override
	public long getCountByVo(ShortcutQueryVo vo) throws Exception {
		return this.shortcutMapper.countByExample(vo);
	}

	@Override
	public int updateShortcut(ShortcutEntity shortcutEntity) throws Exception {
		return this.shortcutMapper.updateByPrimaryKeySelective(shortcutEntity);
	}

	@Override
	public int deteleShortcutById(Integer id) throws Exception {
		return this.shortcutMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Integer> getMenuByUid(Integer id) throws Exception {
		return this.shortcutMapper.selectMenuByUid(id);
	}

}
