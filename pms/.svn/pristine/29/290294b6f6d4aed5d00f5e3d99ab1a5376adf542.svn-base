package cn.teacheredu.service;

import java.math.BigDecimal;
import java.util.List;

import cn.teacheredu.entity.FundsPayEntity;

public interface FundsPayService {
	
	/**
	 * 增加经费付款信息
	 * @param fundId , payAmount
	 * @throws Exception
	 */
	void insertFundsPay(FundsPayEntity fundsPay) throws Exception;
	
	
	/**
	 * 经费付款信息查询
	 * @param fundId
	 * @return List<FundsPayEntity>
	 * @throws Exception
	 */
	List<FundsPayEntity> getFundPay(Integer id) throws Exception;
	
	/**
	 * 付款表，付款总额查询
	 * @param fundId
	 * @return BigDecimal
	 * @throws Exception
	 */
	double sumPayByFundId(Integer fundId) throws Exception;
	
}
