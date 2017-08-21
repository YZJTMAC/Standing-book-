package cn.teacheredu.mapping;

import cn.teacheredu.entity.ShortcutEntity;
import cn.teacheredu.vo.ShortcutQueryVo;

import java.util.List;

public interface ShortcutMapper {
    long countByExample(ShortcutQueryVo vo);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(ShortcutEntity record);

    List<ShortcutEntity> selectByExample(ShortcutQueryVo vo);

    ShortcutEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShortcutEntity record);
    
    List<Integer> selectMenuByUid(Integer id);

}