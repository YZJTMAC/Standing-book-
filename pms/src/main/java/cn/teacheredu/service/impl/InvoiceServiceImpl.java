package cn.teacheredu.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.InvoiceEntity;

import cn.teacheredu.mapping.InvoiceMapper;
import cn.teacheredu.service.InvoiceService;

import cn.teacheredu.vo.InvoiceQueryVo;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceMapper invoiceMapper;
	
	@Override
	public InvoiceEntity getInvoiceById(Integer invoiceId) throws Exception {
		return this.invoiceMapper.selectByPrimaryKey(invoiceId);
	}

	@Override
	public List<InvoiceEntity> getInvoiceByVo(InvoiceQueryVo vo) throws Exception {
		return this.invoiceMapper.selectByExample(vo);
	}

	@Override
	public int insertInvoiceEntity(InvoiceEntity invoiceEntity) throws Exception {
		this.invoiceMapper.insertSelective(invoiceEntity);
		return invoiceEntity.getId();
	}

	@Override
	public long getCountByVo(InvoiceQueryVo vo) throws Exception {
		return this.invoiceMapper.countByExample(vo);
	}

	@Override
	public int updateInvoice(InvoiceEntity invoiceEntity) throws Exception {
		return this.invoiceMapper.updateByPrimaryKeySelective(invoiceEntity);
	}

	@Override
	public int deteleInvoiceById(Integer invoiceId) throws Exception {
		return this.invoiceMapper.deleteByPrimaryKey(invoiceId);
	}

	
}
