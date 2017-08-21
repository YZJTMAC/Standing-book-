package cn.teacheredu.service.batch.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.entity.ProjectSummariesEntity;
import cn.teacheredu.entity.ProjectYearSummaryEntity;
import cn.teacheredu.mapping.ProcessMapper;
import cn.teacheredu.mapping.ProjectMapper;
import cn.teacheredu.mapping.ProjectSummariesMapper;
import cn.teacheredu.mapping.ProjectYearSummaryMapper;
import cn.teacheredu.service.batch.ProjectSummariesBatch;
import cn.teacheredu.utils.MyMathUtil;

@Service
public class ProjectSummariesBatchImpl implements ProjectSummariesBatch {

	@Autowired
	private ProjectSummariesMapper projectSummariesMapper;
	@Autowired
	private ProjectYearSummaryMapper projectYearSummaryMapper;
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private ProcessMapper processMapper;

	private Calendar c = Calendar.getInstance();

	public boolean summariesBatch(Date summariesStartDate) throws Exception {
		/**
		 * 查得项目(summariesStartDate为空查询所有项目，summariesStartDate不为空查询指定时间至今的项目)
		 */
		List<ProjectEntity> projects = this.summariesProject(summariesStartDate);

		/**
		 * 如果是新的项目，向汇总表和汇总年度表添加数据
		 */
		this.haveSummaried(projects);

		/**
		 * 便利项目表
		 * 更新汇总年度表
		 * 更新汇总总表
		 */
		for (ProjectEntity project : projects) {
			this.updateYearSummary(project.getId());
			this.updateSummaries(project.getId());
		}
		
		return true;
	}

	public List<ProjectEntity> summariesProject(Date summariesStartDate) {
		List<ProjectEntity> Projects = new ArrayList<ProjectEntity>();
		try {
			Projects = projectMapper.summariesProject(summariesStartDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Projects;
	}

	
	public boolean haveSummaried(List<ProjectEntity> projects) throws Exception {
		try {
			/**
			 * 便利所有项目
			 */
			for (ProjectEntity project : projects) {//逐个查得
				List<ProjectSummariesEntity> projectSummaries = projectSummariesMapper.selectByProjectId(project.getId());

				if (projectSummaries.size() == 0) {//如果这是新的项目，在汇总表中还不存在
					ProjectSummariesEntity projectSummary = new ProjectSummariesEntity();
					projectSummary.setProjectId(project.getId());//添加项目id
					projectSummary.setSummaryFirstTime(new Date());//添加添加日期
					projectSummariesMapper.insertSelective(projectSummary);

					c.setTime(new Date());
					int nowYear = c.get(Calendar.YEAR);
					for (int year = 2012; year <= nowYear; year++) {//汇总年度信息（匆匆2012年 至 今），每年增加一条数据
						ProjectYearSummaryEntity projectYearSummary = new ProjectYearSummaryEntity();
						projectYearSummary.setProjectId(project.getId());
						projectYearSummary.setYear(year);
						List<ProjectYearSummaryEntity> selectYearSummaryValue = projectYearSummaryMapper.selectYearSummaryValue(project.getId(), year);
						if (selectYearSummaryValue.size() == 0) {
							projectYearSummaryMapper.insertSelective(projectYearSummary);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean updateYearSummary(Integer projectId) {
		c.setTime(new Date());
		int nowYear = c.get(Calendar.YEAR);
		for (int year = 2012; year <= nowYear; year++) {
			List<ProjectYearSummaryEntity> yearSummary = projectYearSummaryMapper
					.selectYearSummaryValue(projectId, year);
					
			if (yearSummary.size() > 0) {
				projectYearSummaryMapper.updateByPrimaryKey(yearSummary.get(0));
			}
		}
		return true;
	}

	public boolean updateSummaries(Integer projectId) {
		try{
			ProjectSummariesEntity projectSummaries = projectMapper.summaryById(projectId);
			// 项目培训周期（满15天才算一个月，不满一个月算一个月）
			Date startDate = projectSummaries.getProjectStartDate();
			Date endDate = projectSummaries.getProjectEndDate();
			c.setTime(startDate);
			int startYear = c.get(Calendar.YEAR);
			int startMonth = c.get(Calendar.MONTH) + 1;
			int startDay = c.get(Calendar.DAY_OF_MONTH);
			c.setTime(endDate);
			int endYear = c.get(Calendar.YEAR);
			int endMonth = c.get(Calendar.MONTH) + 1;
			int endDay = c.get(Calendar.DAY_OF_MONTH);
			int courseLength = (endYear - startYear) * 12 + endMonth - startMonth;
			if (startDay - endDay > 15)
				courseLength--;
			if (courseLength <= 0)
				courseLength = 1;
			projectSummaries.setProjectTrainPeriod(courseLength);
	
			// 前期后期经费(计算方法：项目总额*经费比例)
			BigDecimal proFundAmount = MyMathUtil.div(MyMathUtil.mul(projectSummaries.getPredictTotalAmount(),projectSummaries.getProFundProportion()), new BigDecimal(100),2);
			BigDecimal laterFundAmount = MyMathUtil.div(MyMathUtil.mul(projectSummaries.getPredictTotalAmount(),projectSummaries.getLaterFundProportion()),new BigDecimal(100), 2);
			projectSummaries.setProFundAmount(proFundAmount);
			projectSummaries.setLaterFundAmount(laterFundAmount);
	
			// 实际项目总额
			BigDecimal a = MyMathUtil.sub(projectSummaries.getPredictTotalAmount(),projectSummaries.getProFundAmount());
			BigDecimal b = projectSummaries.getRealPaymentAmount();
			BigDecimal projectRealTotalAmount;
			if(a == null && b != null){
				projectRealTotalAmount = b;
			}else if(b == null && a != null){
				projectRealTotalAmount = a;
			}else if(b != null && a != null){
				projectRealTotalAmount = a.doubleValue() > b.doubleValue() ? a: b;
			}else{
				projectRealTotalAmount = new BigDecimal(0);
			}
			projectSummaries.setProjectRealTotalAmount(projectRealTotalAmount);
	
			// 预计后期到款总额：报名人数*单价-前期经费-已到款金额 （同预计项目总额的问题）
			BigDecimal predictLaterPayment = MyMathUtil.sub(MyMathUtil.sub(projectSummaries.getPredictTotalAmount(),projectSummaries.getProFundAmount()), projectSummaries.getRealPaymentAmount());
			projectSummaries.setPredictLaterPayment(predictLaterPayment);
	
			// 应收账款：已结转收入金额-已到款金额
			BigDecimal predictReceiveAmount = MyMathUtil.sub(projectSummaries.getRealIncomeAmount(),projectSummaries.getRealPaymentAmount());
			projectSummaries.setPredictReceiveAmount(predictReceiveAmount);
	
			// 尚未支付经费：后期经费比例金额-已支付经费合计
			BigDecimal nopayFundAmount = MyMathUtil.sub(projectSummaries.getLaterFundAmount(),projectSummaries.getRealFundAmount());
			projectSummaries.setNopayFundAmount(nopayFundAmount);
	
			// 未开发票金额：已到款合计-已开发票合计
			BigDecimal noInvoiceAmount = MyMathUtil.sub(projectSummaries.getRealPaymentAmount(),projectSummaries.getRealInvoiceAmount());
			projectSummaries.setNoInvoiceAmount(noInvoiceAmount);
	
			// 经费支付异常：已到款合计金额*后期经费比例-已支付经费金额
			BigDecimal fundError = MyMathUtil.sub(MyMathUtil.div(MyMathUtil.mul(projectSummaries.getRealPaymentAmount(),projectSummaries.getLaterFundProportion()), new BigDecimal(100), 2), projectSummaries.getRealFundAmount());
			projectSummaries.setFundError(fundError);

			projectSummaries.setSummaryLastTime(new Date());
	
			projectSummariesMapper.updateByPrimaryKeySelective(projectSummaries);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
		}

	@Override
	public void summariesBatch2() throws Exception {
		/**
		 * 查得项目(获得涉及变更的项目)
		 */
		List<ProjectEntity> projects = this.summariesProject2();

		/**
		 * 如果是新的项目，向汇总表和汇总年度表添加数据
		 */
		this.haveSummaried(projects);

		/**
		 * 便利项目表
		 * 更新汇总年度表
		 * 更新汇总总表
		 */
		for (ProjectEntity project : projects) {
			this.updateYearSummary(project.getId());
			this.updateSummaries(project.getId());
		}
		//对process的update_summary字段设为1
		processMapper.updateUpdateSummary();
//		return true;
	}
	
	public List<ProjectEntity> summariesProject2() {
		List<ProjectEntity> Projects = new ArrayList<ProjectEntity>();
		try {
			Projects = projectMapper.summariesProject2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Projects;
	}
}
