package cn.teacheredu.mapping;

import java.math.BigDecimal;
import java.util.List;

import cn.teacheredu.entity.FundsPayEntity;

public interface FundsPayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FundsPayEntity record);

    int insertSelective(FundsPayEntity record);

    FundsPayEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundsPayEntity record);

    int updateByPrimaryKey(FundsPayEntity record);
    
    List<FundsPayEntity> queryByFundId(Integer fundId);
    
    double sumPayByFundId(Integer fundId);
}