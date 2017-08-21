package cn.teacheredu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.FundsPayEntity;
import cn.teacheredu.mapping.FundsPayMapper;
import cn.teacheredu.service.FundsPayService;

@Service
public class FundsPayServiceImpl implements FundsPayService {
	
	@Autowired
	private FundsPayMapper fundsPayMapper;

	@Override
	public void insertFundsPay(FundsPayEntity fundsPay) throws Exception {
		fundsPayMapper.insert(fundsPay);
	}

	@Override
	public List<FundsPayEntity> getFundPay(Integer id) throws Exception {
		List<FundsPayEntity> fundsPays = fundsPayMapper.queryByFundId(id);
		return fundsPays;
	}

	@Override
	public double sumPayByFundId(Integer fundId) throws Exception {
		double sumPay = fundsPayMapper.sumPayByFundId(fundId);
		return sumPay;
	}


}
