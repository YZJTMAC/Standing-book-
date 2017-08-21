package cn.teacheredu.service;

import java.math.BigDecimal;
import java.util.List;

import cn.teacheredu.entity.FundsEntity;
import cn.teacheredu.entity.FundsPayEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.vo.FundsQueryVo;

/**
 * 经费申请表
 * @author jiajinlong
 *
 */
public interface FundsService {

	FundsEntity getFundsById(Integer fundsId) throws Exception;
	
	List<FundsEntity> getFundsByVo(FundsQueryVo vo) throws Exception;
	
	int insertFundsEntity(FundsEntity fundsEntity) throws Exception;
	
	long getCountByVo(FundsQueryVo vo) throws Exception;
	
	int updateFunds(FundsEntity fundsEntity) throws Exception;
	
	int deteleFundsById(Integer fundsId) throws Exception;
	
	/**
	 * 根据form查询经费集合（分页）
	 * @param queryForm
	 * @throws Exception
	 */
	PageUtils<FundsEntity> getFundsList(QueryTermsForm queryForm) throws Exception;
	
	/**
	 * 查询项目已支付的经费的总和
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	BigDecimal getTotalWonPayByProject(Integer projectId) throws Exception;

	/**
	 * 查询项目已支付的和正在申请的经费的总和
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	BigDecimal getTotalFundsByProject(Integer projectId) throws Exception;
	
	/**
	 * 流程被退回时，把申请记录置为废弃的。这样上面那个方法查的金额就正确了
	 * @param id
	 * @throws Exception
	 */
	void editAvailableById(Integer id) throws Exception;
	
	
	/**
	 * 修改经费状态
	 * @param fundId , statu
	 * @throws Exception
	 */
	void editWonPayById(Integer id,String statu) throws Exception;
}
