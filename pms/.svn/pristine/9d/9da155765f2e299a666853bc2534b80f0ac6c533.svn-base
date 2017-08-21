package cn.teacheredu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.teacheredu.entity.OrganizationEntity;
import cn.teacheredu.entity.ProcessEntity;
import cn.teacheredu.service.OrganizationService;
import cn.teacheredu.service.ProcessHistoryService;
import cn.teacheredu.utils.R;

/**
 * 一些公共的查询
 * @author Zhaojie
 *
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {
	
	
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private ProcessHistoryService processHistoryService;
	
	
	@RequestMapping(value = "/getOrgByParentId", method = RequestMethod.POST)
	public @ResponseBody R getOrganizationByParentId(@RequestBody OrganizationEntity org, HttpServletRequest request,HttpServletResponse response) throws Exception{
		Integer parentId = org.getParentId();
		if (parentId == null) {
			return R.ok().put("list", new ArrayList<List<OrganizationEntity>>());
		}
		return R.ok().put("list", this.organizationService.getListByParentId(parentId));
	}
	
	@RequestMapping(value = "/getProcessLogById", method = RequestMethod.POST)
	public @ResponseBody R getProcessLogById(@RequestBody ProcessEntity process, HttpServletRequest request,HttpServletResponse response) throws Exception{
		if (process.getId() == null) {
			return R.error("未查询到流程的相关信息，请联系管理员");
		}
		return R.ok().put("list",this.processHistoryService.getProcessLogById(process.getId()));
	}
	
}
