package cn.teacheredu.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.teacheredu.entity.CompanyEntity;
import cn.teacheredu.entity.DepartmentEntity;
import cn.teacheredu.entity.OrganizationEntity;
import cn.teacheredu.entity.ProcessStepEntity;
import cn.teacheredu.entity.UserEntity;
import cn.teacheredu.service.CompanyService;
import cn.teacheredu.service.DepartmentService;
import cn.teacheredu.service.OrganizationService;
import cn.teacheredu.service.ProcessStepService;
import cn.teacheredu.service.SystemLogService;
import cn.teacheredu.service.UserService;
import cn.teacheredu.utils.CommonUtils;
import cn.teacheredu.utils.PageUtils;
import cn.teacheredu.utils.R;
import cn.teacheredu.utils.SystemConst;
import cn.teacheredu.vo.CompanyQueryVo;
import cn.teacheredu.vo.DepartmentQueryVo;

@Controller
@RequestMapping(value = "/admin")
public class DepartmentController {

	private static Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProcessStepService processStepService;
	
	@RequestMapping("/main")
	public String indexPage(ModelMap modelMap ,HttpServletRequest request) {
		return "admin/main";
	}
	
	
	@RequestMapping("/department")
	public String departmentPage(ModelMap modelMap ,HttpServletRequest request) {
		return "admin/department";
	}
	@ResponseBody
	@RequestMapping(value="/department/list")
	public Map<String, Object> doDepartmentPageList(Integer page, Integer limit,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		DepartmentQueryVo vo = new DepartmentQueryVo();
		vo.setCurPage(page);
		vo.setStartPosition((page - 1) * limit);
		vo.setPageSize(limit);
		vo.setPaged(true);
		List<DepartmentEntity> list = this.departmentService.getDepartmentByVo(vo);
		long total = this.departmentService.getCountByVo(vo);
		//查询主管姓名、上级部门、所属省份名称
		for (DepartmentEntity dm : list) {
			String directors = dm.getDirectorId();
			StringBuffer stringBuffer = new  StringBuffer();
			if (directors != null && !"".equals(directors)) {
				String[] directorArray = directors.split(",");
				for (int j = 0; j < directorArray.length; j++) {
					String string = directorArray[j];
					UserEntity user = this.userService.getUserEntityById(Integer.parseInt(string));
					if (user != null)
					stringBuffer.append("-" + user.getRealname() + " ");
				}
			}
			Map<String, Object> extInfo = new HashMap<String, Object>();
			extInfo.put("directors", stringBuffer.toString());
			Integer parentId = dm.getParentId();
			if (parentId != 0) {
				DepartmentEntity dmEntity = this.departmentService.getDepartmentById(parentId);
				if(dmEntity != null)
					extInfo.put("pdname", dmEntity.getDmName());
			}
			Integer province = dm.getProvince();
			if (province != null) {
				OrganizationEntity organizationEntity = this.organizationService.getOrganizationById(province);
				if (organizationEntity != null) 
					extInfo.put("province",	organizationEntity.getName());
			}
			dm.setExtInfo(extInfo);
		}
		PageUtils<DepartmentEntity> pageUtil = new PageUtils<DepartmentEntity>(list, total, limit, page);
		map.put("page", pageUtil);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/department/list2")
	public Map<String, Object> doDepartmentList(HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		DepartmentQueryVo vo = new DepartmentQueryVo();
		vo.setPaged(false);
		List<DepartmentEntity> list = this.departmentService.getDepartmentByVo(vo);
		String flag = request.getParameter("flag");
		if (flag != null && "1".equals(flag)) { //部门列表里添加一个 无
			DepartmentEntity dm = new DepartmentEntity();
			dm.setId(0);
			dm.setDmName("无");
			dm.setDescription("只是部门管理页面用到这个东西");
			dm.setGenTime(new Date());
			list.add(dm);
		}
		map.put("list", list);
		return map;
	}
	
	//部门所属省份下拉框的内容
	@RequestMapping("/department/getProvince")
	public @ResponseBody R getProvinceByParentId(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	List<OrganizationEntity> list = this.organizationService.getListByParentId(0); 
    	OrganizationEntity o = new OrganizationEntity();
    	o.setId(0);
    	o.setName("---无---");
    	list.add(0, o);
		return R.ok().put("list", list);
	}
	//部门所属公司下拉框的内容
	@RequestMapping("/department/getCompanys")
	public @ResponseBody R getAllCompany(ModelMap model,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	List<CompanyEntity> list = this.companyService.getCompanyByVo(new CompanyQueryVo()); 
    	CompanyEntity o = new CompanyEntity();
    	o.setId(0);
    	o.setName("-----------无-----------");
    	list.add(0, o);
		return R.ok().put("list", list);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/department/selectOne")
	public Map<String, Object> doSelectDepartmentById(Integer id,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		logger.info("/department/selectOne/"+id);
		Map<String,Object> map = new HashMap<String,Object>();
		if (id != null) {
			DepartmentEntity dm = this.departmentService.getDepartmentById(id);
			map.put("dm", dm);//部门信息
//			UserQueryVo vo = new UserQueryVo();
//			vo.setDmId(id);
//			vo.setPaged(false);
			List<UserEntity> userList = this.userService.getUserListByDmId(id);
			//部门主管处理
			List<Integer> userIdList = new ArrayList<Integer>();
			dm.setUserIdList(userIdList);
			String directors = dm.getDirectorId();
			if (directors != null && !"".equals(directors)) {  //修改页面的回显
				String[] directorArray = directors.split(",");
				for (String directorId : directorArray) {
					userIdList.add(Integer.parseInt(directorId));
				}
			}
			map.put("userList", userList);//部门下所有的人员
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value="/department/delete" ,method=RequestMethod.POST)
	public Map<String, Object> doDeleteDepartmentById(@RequestBody int[] ids,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if (ids == null || ids.length == 0) {
			map.put("result", false);
			map.put("msg", "删除失败!请勾选要删除的记录");
		} else {
			boolean flag = true;
			for (int id : ids) {
				List<UserEntity> userList = this.userService.getUserListByDmId(id);
				if (userList != null && userList.size() > 0) {
					map.put("result", false);
					map.put("msg", "删除失败!请先删除部门下的人员");
					flag = false;
					break;
				}
			}
			
			//是否参与流程审批
			boolean mark = true;
			for (int id : ids) {
				List<ProcessStepEntity> stepList = this.processStepService.getProcessStepListByDmId(id);
				if (stepList != null && stepList.size() > 0) {
					map.put("result", false);
					map.put("msg", "删除失败!部门参与流程审批，请联系管理员处理。");
					mark = false;
					break;
				}
			}
			
			if (flag && mark) {
				int uid = (Integer) httpSession.getAttribute(SystemConst.SESSION_LOGIN_USER_ID);
				for (int id : ids) {
					int a = this.departmentService.deteleDepartmentById(id);
					if (a > 0) {
						this.systemLogService.saveSystemLog(3, "department_"+id, uid);
					} 
				}
				map.put("result", true);
			}
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value="/department/save",method=RequestMethod.POST)
	public Map<String, Object> doSaveDepartment(@RequestBody DepartmentEntity department,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		if (StringUtils.isBlank(department.getDmName())) {
			map.put("result", false);
			map.put("msg", "部门名称不能为空");
		} else {
			int uid = (Integer) httpSession.getAttribute(SystemConst.SESSION_LOGIN_USER_ID);
			department.setGenTime(new Date());
			int a = this.departmentService.insertDepartmentEntity(department);
			if (a > 0) {
				this.systemLogService.saveSystemLog(1, "department_"+department.toString(), uid);
			}
			map.put("result", true);
		}
		return map;
	}
	@ResponseBody
	@RequestMapping(value="/department/update",method=RequestMethod.POST)
	public Map<String, Object> doUpdateDepartment(@RequestBody DepartmentEntity department, HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		if (StringUtils.isBlank(department.getDmName())) {
			map.put("result", false);
			map.put("msg", "部门名称不能为空");
		} else {
			if (department.getId() == null || department.getId() == 0) {
				map.put("result", false);
				map.put("msg", "修改失败");
			} else {
				int uid = (Integer) httpSession.getAttribute(SystemConst.SESSION_LOGIN_USER_ID);
				if (department.getUserIdList().size() > 1) { //暂时屏蔽一个部门多个主管的情况
					map.put("result", false);
					map.put("msg", "抱歉，现在一个部门暂时不能设置多个主管");
					return map;
				}
				String  directors = CommonUtils.listToString(department.getUserIdList()); //部门主管
				department.setDirectorId(directors);
				int a = this.departmentService.updateDepartment(department);
				if (a > 0) {
					this.systemLogService.saveSystemLog(2, "department_"+department.toString(), uid);
				}
				map.put("result", true);
			}
		}
		return map;
	}
	
	
	
}
