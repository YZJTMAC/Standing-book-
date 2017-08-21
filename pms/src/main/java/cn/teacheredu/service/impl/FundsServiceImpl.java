package cn.teacheredu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.FundsEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.mapping.FundsMapper;
import cn.teacheredu.mapping.ProcessMapper;
import cn.teacheredu.service.FundsService;
import cn.teacheredu.utils.MyMathUtil;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.FundsQueryVo;
import cn.teacheredu.vo.ProcessEntityQueryVo;

@Service
public class FundsServiceImpl implements FundsService {

	@Autowired
	private FundsMapper fundsMapper;
	@Autowired
	private ProcessMapper processMapper;
	
	@Override
	public FundsEntity getFundsById(Integer fundsId) throws Exception {
		return this.fundsMapper.selectByPrimaryKey(fundsId);
	}

	@Override
	public List<FundsEntity> getFundsByVo(FundsQueryVo vo) throws Exception {
		return this.fundsMapper.selectByExample(vo);
	}

	@Override
	public int insertFundsEntity(FundsEntity fundsEntity) throws Exception {
		this.fundsMapper.insertSelective(fundsEntity);
		return fundsEntity.getId();
	}

	@Override
	public long getCountByVo(FundsQueryVo vo) throws Exception {
		return this.fundsMapper.countByExample(vo);
	}

	@Override
	public int updateFunds(FundsEntity fundsEntity) throws Exception {
		return this.fundsMapper.updateByPrimaryKeySelective(fundsEntity);
	}

	@Override
	public int deteleFundsById(Integer fundsId) throws Exception {
		return this.fundsMapper.deleteByPrimaryKey(fundsId);
	}

	@Override
	public PageUtils<FundsEntity> getFundsList(QueryTermsForm form) throws Exception {
		PageUtils<FundsEntity>  pageUtil= null;
		if (form.getUserId() == null){
			pageUtil = new PageUtils<FundsEntity>(new ArrayList<FundsEntity>(), 0, form.getPageSize(), form.getCurrPage());
		}else{
			FundsQueryVo vo=new FundsQueryVo();
			vo.setOrderProperty("id");
			vo.setOrderType(SystemConst.SQL_ORDERTYPE_DESC);
			vo.setPaged(true);
			vo.setPageSize(form.getPageSize());
			vo.setCurPage(form.getCurrPage());
			vo.setStartPosition((form.getCurrPage() - 1) * form.getPageSize());
			vo.setProjectId(form.getProjectId());
			vo.setAvailable(0);
			if (form.getType() == 1 ) { //通过申请日期查询
				vo.setFromDate(form.getStartDate());
				vo.setToDate(form.getEndDate());
				vo.setCreateTime(new Date());;//这时间没有什么用，就是为了区别查询条件是哪个
			} else if (form.getType() == 2 && StringUtils.isNotBlank(form.getValue())) { //通过费用类型查询
				vo.setType((byte)Integer.parseInt(form.getValue()));
			} else if(form.getType() == 3 && StringUtils.isNotBlank(form.getValue())){//收款人
				vo.setRecName(form.getValue().trim());  
			}
			List<FundsEntity> list=this.getFundsByVo(vo);
			
			//查 funds对应的process
			ProcessEntityQueryVo vo2 = new ProcessEntityQueryVo();
			vo2.setProjectId(form.getProjectId());
			vo2.setTableName(SystemConst.TABLE_FUNDS);
			for (FundsEntity fundsEntity : list) { 
				vo2.setObjectId(fundsEntity.getId());
				List<ProcessEntity> process = this.processMapper.selectByExample(vo2);
				if (process.size() > 0) {
					Map<String, Object> extInfo = new HashMap<String, Object>();
					extInfo.put("process", process.get(0));
					fundsEntity.setExtInfo(extInfo);
				}
			}
			
			
			long total = this.getCountByVo(vo);
			pageUtil = new PageUtils<FundsEntity>(list, total, form.getPageSize(), form.getCurrPage());
		}
		return pageUtil;
	}

	@Override
	public BigDecimal getTotalWonPayByProject(Integer projectId) throws Exception {
		BigDecimal result = BigDecimal.ZERO;
		if (projectId != null) {
			FundsQueryVo vo = new FundsQueryVo();
			vo.setProjectId(projectId);
			vo.setWonPay(Byte.valueOf("1"));
			List<FundsEntity> fundsEntities = this.getFundsByVo(vo);
			for (FundsEntity fundsEntity : fundsEntities) {
				result = MyMathUtil.add(result, fundsEntity.getApplyAmount());
			}
		}
		return result;
	}
	
	@Override
	public BigDecimal getTotalFundsByProject(Integer projectId) throws Exception {
		BigDecimal result = BigDecimal.ZERO;
		if (projectId != null) {
			FundsQueryVo vo = new FundsQueryVo();
			vo.setProjectId(projectId);
			vo.setAvailable(0);
			List<FundsEntity> fundsEntities = this.getFundsByVo(vo);
			for (FundsEntity fundsEntity : fundsEntities) {
				result = MyMathUtil.add(result, fundsEntity.getApplyAmount());
			}
		}
		return result;
	}

	@Override
	public void editAvailableById(Integer id) throws Exception {
		if (id == null) {
			return;
		}
		this.fundsMapper.editAvailableById(id);
	}

	

	@Override
	public void editWonPayById(Integer id, String status) throws Exception {
		if (id == null||"".equals(status)) {
			return;
		}
		this.fundsMapper.editWonPayById(id, status);
	}
}
