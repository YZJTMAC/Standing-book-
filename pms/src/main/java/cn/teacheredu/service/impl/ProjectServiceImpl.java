package cn.teacheredu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.ProjectEntity;
import cn.teacheredu.form.QueryTermsForm;
import cn.teacheredu.mapping.ProjectMapper;
import cn.teacheredu.service.OrganizationService;
import cn.teacheredu.service.ProjectService;
import cn.teacheredu.service.UserService;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.ProjectEntityQueryVo;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;
	
	@Override
	public ProjectEntity getProjectById(Integer projectId) throws Exception {
		return this.projectMapper.selectByPrimaryKey(projectId);
	}

	@Override
	public List<ProjectEntity> getProjectByVo(ProjectEntityQueryVo vo) throws Exception {
		return this.projectMapper.selectByExample(vo);
	}

	@Override
	public int insertProjectEntity(ProjectEntity projectEntity) throws Exception {
		this.projectMapper.insertSelective(projectEntity);
		return projectEntity.getId();
	}

	@Override
	public long getCountByVo(ProjectEntityQueryVo vo) throws Exception {
		return this.projectMapper.countByExample(vo);
	}

	@Override
	public int updateProject(ProjectEntity projectEntity) throws Exception {
		return this.projectMapper.updateByPrimaryKeySelective(projectEntity);
	}

	@Override
	public int deteleProjectById(Integer projectId) throws Exception {
		return this.projectMapper.deleteByPrimaryKey(projectId);
	}

	@Override
	public PageUtils<ProjectEntity> getMyProjectForIndexPage(Integer userId, List<DepartmentEntity> dmList,List<String> companyList, Integer type) throws Exception {
		int pageSize = 5,curPage = 1;//只查5条数据
		ProjectEntityQueryVo vo = new ProjectEntityQueryVo();
		vo.setOrderProperty("id");//按发起时间倒序
		vo.setOrderType(SystemConst.SQL_ORDERTYPE_DESC);
		vo.setPaged(true);
		vo.setPageSize(pageSize);
		vo.setCurPage(curPage);
		vo.setStartPosition((curPage - 1) * pageSize);
		vo.setStatus((byte) -1);
		PageUtils<ProjectEntity> pageUtil = null;
		List<ProjectEntity> list = new ArrayList<ProjectEntity>();
		long total = 0l;
		switch (type) {
			case 1: //查询所有的项目
				list = this.projectMapper.selectByExample(vo);
				total = this.projectMapper.countByExample(vo);
				break;
			case 2: //查询所在省份的所有项目，省份从部门实体中找。
				List<Integer> provinceList = new ArrayList<Integer>();
				for (DepartmentEntity dm : dmList) {
					if (dm.getProvince() != null && dm.getProvince() > 0) {//部门是可能没有所属省份的
						provinceList.add(dm.getProvince());
					}
				}
				if(provinceList.size() != 0){ //此用户所在部门必须设置部门所属省份信息
					if (provinceList.size() > 1) { //此用户属于多个部门，并且部门所属多个省，查询他所在这些部门所在省份的所有项目
						vo.setProvincialList(provinceList);
					} else if (provinceList.size() == 1) { //此用户可能属于多个部门，可能属于1个部门，(比如：一个人同时属于教务部和广西办事处，这个省份list就只有1个)但后台设置的部门所属省份综合起来只有1个。那就查询这个省份的所有项目。
						vo.setProvincial(provinceList.get(0));
					}
					list = this.projectMapper.selectByExample(vo);
					total = this.projectMapper.countByExample(vo);
				}
				break;
			case 3: //查询自己发起的
				vo.setUserId(userId);
				list = this.projectMapper.selectByExample(vo);
				total = this.projectMapper.countByExample(vo);
				break;
			case 4: //查询某个分管大区的项目 （一般都是为了分管领导用的）
				for (DepartmentEntity dm : dmList) {
					if (dm.getProvince() != null && dm.getProvince() < 0) {//找到一个所属省份ID为负数的，具体可以看数据库初始数据
						vo.setChargeArea(dm.getProvince());
						break;
					}
				}
				if (vo.getChargeArea() != null) { //如果上面那个循环没有找到大区ID,那可能分管大区没有设置所属省份，联系管理员设置吧！
					list = this.projectMapper.selectByExample(vo);
					total = this.projectMapper.countByExample(vo);
				}
				break;
			case 5: //查询指定公司的项目 （一般都是为了副总和财务用的）
				if (companyList.size() != 0) { 
					if (companyList.size() == 1) {
						vo.setCollectMoneyCompany(companyList.get(0));
					} else {
						vo.setCollectMoneyCompanyList(companyList);
					}
				}
				list = this.projectMapper.selectByExample(vo);
				total = this.projectMapper.countByExample(vo);
				break;
			default:
				System.err.println("[ProjectServiceImpl]:getMyProjectForIndexPage 调用错误，type不正确。");
				break;
		}
		this.userService.dealList(list, "userId");
		pageUtil = new PageUtils<ProjectEntity>(list, total, pageSize, curPage);
		return pageUtil;
	}

	@Override
	public PageUtils<ProjectEntity> getProjectListByForm(QueryTermsForm form) throws Exception {
		PageUtils<ProjectEntity> pageUtil = null;
		if (form.getDmId() == null) {
			System.err.println("[ProjectServiceImpl]:getProjectListByFrom param error");
			pageUtil = new PageUtils<ProjectEntity>(new ArrayList<ProjectEntity>(), 0, form.getPageSize(), form.getCurrPage());
		} else {
			ProjectEntityQueryVo vo = new ProjectEntityQueryVo();
			vo.setOrderProperty("id");
			vo.setOrderType(SystemConst.SQL_ORDERTYPE_DESC);
			vo.setPaged(true);
			vo.setPageSize(form.getPageSize());
			vo.setCurPage(form.getCurrPage());
			vo.setStartPosition((form.getCurrPage() - 1) * form.getPageSize());
			vo.setStatus((byte) -1);
			//基础条件
			if (form.getUserId() != null) { //查询自己发起的项目
				vo.setUserId(form.getUserId());
			} else {
				Integer prov = form.getDmId();
				if (prov != -8) {
					if (prov < 0) { //按照分管大区查询项目
						vo.setChargeArea(prov);
					} else { //按照省份查询项目
						vo.setProvincial(prov);
					}
				}
				List<String> companyList = form.getCompanyList();
				if (companyList.size() != 0) {
					if (companyList.size() > 1) {
						vo.setCollectMoneyCompanyList(companyList);
					} else {
						vo.setCollectMoneyCompany(companyList.get(0));
					}
				}
			}
			if (StringUtils.isNotBlank(form.getUserName())) {
				vo.setNeedJoin(true);
				vo.setUserName(form.getUserName());
			}
			//其他条件
			if (form.getType() == 1 && StringUtils.isNotBlank(form.getValue())) { //通过项目编号查询
				vo.setSerialNumber(form.getValue().trim());
			} else if (form.getType() == 2 && StringUtils.isNotBlank(form.getValue())) { //通过项目名称查询
				vo.setName(form.getValue().trim());
			} else if (form.getType() == 3) { //项目类型
				vo.setType(form.getProType());
			} else if (form.getType() == 4 && form.getStatus() != null) { //项目状态
				vo.setStatus(form.getStatus());
			} else if (form.getType() == 5) { //培训起始时间
				vo.setFromDate(form.getStartDate());
				vo.setToDate(form.getEndDate());
				vo.setStartDate(new Date());//这时间没有什么用，就是为了区别查询条件是哪个
			} else if (form.getType() == 6) { //培训结束时间
				vo.setFromDate(form.getStartDate());
				vo.setToDate(form.getEndDate());
				vo.setEndDate(new Date());//这时间没有什么用，就是为了区别查询条件是哪个
			}
			
			List<ProjectEntity> list = this.getProjectByVo(vo);
			long total = this.getCountByVo(vo);
			this.userService.dealList(list, "userId");
			for (ProjectEntity projectEntity : list) {
				Map<String, Object> map = projectEntity.getExtInfo();
				if (map == null) {
					map = new HashMap<String, Object>();
				}
				map.put(SystemConst.PROV_NAME, this.organizationService.getNameById(projectEntity.getProvincial()));
				projectEntity.setExtInfo(map);
			}
			pageUtil = new PageUtils<ProjectEntity>(list, total, form.getPageSize(), form.getCurrPage());
			
		}
		
		return pageUtil;
	}

	@Override
	public List<ProjectEntity> getProjectByProvince(Integer province) throws Exception {
		if (province == null) {
			return new ArrayList<ProjectEntity>();
		}
		ProjectEntityQueryVo vo = new ProjectEntityQueryVo();
		vo.setOrderProperty("id");
		vo.setOrderType(SystemConst.SQL_ORDERTYPE_DESC);
		vo.setStatus((byte) -1);
		vo.setProvincial(province);
		
		return this.getProjectByVo(vo);
	}

	@Override
	public List<ProjectEntity> selectProjectForEnd(ProjectEntityQueryVo vo) throws Exception {
		return this.projectMapper.selectProjectForEnd(vo);
	}

	@Override
	public void editProjectStatus(Byte status, Integer projectId) throws Exception {
		if (projectId == null || status == null) {
			return;
		}
		ProjectEntity project = this.getProjectById(projectId);
		if (project != null) {
			project.setStatus(status);
			this.updateProject(project);
		}
	}

}
