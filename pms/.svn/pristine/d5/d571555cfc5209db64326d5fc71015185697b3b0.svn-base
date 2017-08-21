package cn.teacheredu.controller.admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.teacheredu.controller.config.SysConfig;
import cn.teacheredu.entity.AttachmentEntity;
import cn.teacheredu.service.AttachmentService;
import cn.teacheredu.service.SystemLogService;
import cn.teacheredu.utils.FilePathUtil;
import cn.teacheredu.utils.SystemConst;


@Controller
@RequestMapping(value = "/file")
public class FileUploadController {

	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private SysConfig sysConfig;
	@Autowired
	private AttachmentService attachmentService;
	
	private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@ResponseBody
	@RequestMapping(value="/uploadAjax")
	public Map<String, Object> doUploadFile(@RequestParam("file") MultipartFile uploadFile,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		//Thread.sleep(10000);
		Map<String,Object> map = new HashMap<String,Object>();
		//文件原始名称(上传前的名称，存入数据库)
		String fileName = uploadFile == null ? "" : uploadFile.getOriginalFilename();
		if (!"".equals(fileName)) {
			logger.info("uploadFile-"+fileName);
			//文件类型(后缀)
			String fileType=fileName.substring(fileName.lastIndexOf("."));
			//文件路径(相对路径，建议存入数据库)
			String newFileName = System.currentTimeMillis()+fileType;
			String filePath = new SimpleDateFormat("yy-MM-dd").format(new Date()).toString();
			// 文件磁盘路径
			String path = FilePathUtil.getRealFilePath(sysConfig.uploadFileURL+"/"+filePath);
			//创建目录
			File newFile = new File(path);
			// 如果目录不存在，则创建目录
			if (!newFile.isDirectory())
				newFile.mkdirs();
			//创建目标文件
			File resultFile = new File(FilePathUtil.getRealFilePath(path+"/"+newFileName));
			//写入磁盘
			uploadFile.transferTo(resultFile);
			//返回服务器显示路径
			String servicePath=resultFile.getCanonicalPath();
			System.out.println(servicePath);
			// 保存数据库
			AttachmentEntity attachmentEntity = new AttachmentEntity();
			attachmentEntity.setFiletype(fileType);
			attachmentEntity.setName(fileName);
			attachmentEntity.setUrl(servicePath);
			attachmentEntity.setCreateTime(new Date());
			int a = this.attachmentService.insertAttachment(attachmentEntity);
			if (a > 0) {
				this.systemLogService.saveSystemLog(1, "uploadFile-"+a, (Integer) httpSession.getAttribute(SystemConst.SESSION_LOGIN_USER_ID));
			}
			map.put("result", true);
			map.put("msg", "上传成功");
			map.put("fileName", fileName);
			map.put("attId", a);//附件表ID
		} else {
			map.put("result", false);
			map.put("msg", "没有检测到要上传的文件");
		}
		
		return map;
	}
	
	/**
	 * 导入
	 * 文件也先存到服务器上，再用POI进行解析
	 * @param uploadFile
	 * @param response
	 * @param request
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/importAjax")
	public Map<String, Object> doImportFile(@RequestParam("file") MultipartFile uploadFile,HttpServletResponse response,HttpServletRequest request,HttpSession httpSession) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		//文件原始名称(上传前的名称，存入数据库)
		String fileName = uploadFile == null ? "" : uploadFile.getOriginalFilename();
		if (!"".equals(fileName)) {
			logger.info("importFile-"+fileName);
			//文件类型(后缀)
			String fileType=fileName.substring(fileName.lastIndexOf("."));
			//文件路径(相对路径，建议存入数据库)
			String newFileName = System.currentTimeMillis()+fileType;
			String filePath = new SimpleDateFormat("yy-MM-dd").format(new Date()).toString();
			// 文件磁盘路径
			String path = FilePathUtil.getRealFilePath(sysConfig.importFileURL+"/"+filePath);
			//创建目录
			File newFile = new File(path);
			// 如果目录不存在，则创建目录
			if (!newFile.isDirectory())
				newFile.mkdirs();
			//创建目标文件
			File resultFile = new File(FilePathUtil.getRealFilePath(path+"/"+newFileName));
			//写入磁盘
			uploadFile.transferTo(resultFile);
			//返回服务器显示路径
			String servicePath=resultFile.getCanonicalPath();
			this.systemLogService.saveSystemLog(1, "importFile-"+fileName+",and path:" + servicePath, (Integer) httpSession.getAttribute(SystemConst.SESSION_LOGIN_USER_ID));
//			InputStream in = new FileInputStream(resultFile);
//			System.out.println(in);
//			try {
//				List<List<String>> list = new ImportExcelUtil().getBankListByExcel(in, fileType);
//				System.out.println(list);
//			} catch (Exception e) {
//				map.put("result", false);
//				map.put("msg", e.getMessage());
//				return map;
//			} finally {
//				if (in != null) {
//					in.close();
//				}
//			}
			map.put("filePath", servicePath);
			map.put("result", true);
			map.put("msg", "导入成功");
		} else {
			map.put("result", false);
			map.put("msg", "没有检测到要导入的文件");
		}
		
		return map;
	}
    
	@RequestMapping(value = "/download", method=RequestMethod.GET,produces="application/octet-stream")
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request, HttpServletResponse response, Integer attId, Integer objId,HttpSession httpSession) throws Exception {
    	logger.info("downloadFile-attId=" + attId + ",objId=" + objId + ",userId=" + httpSession.getAttribute(SystemConst.SESSION_LOGIN_USER_ID));
    	if (attId == null || objId == null) {
			throw new Exception();
		}
    	AttachmentEntity attachmentEntity = this.attachmentService.getAttachmentById(attId);
    	if (attachmentEntity == null || !attachmentEntity.getObjectId().equals(objId)) {
			throw new Exception();
		}
    	
    	File file=new File(attachmentEntity.getUrl()); 
        HttpHeaders headers = new HttpHeaders();
        String fileName = new String(attachmentEntity.getName().getBytes("gbk"),"iso-8859-1");
		headers.set("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
    	return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/downloadTemplate", method=RequestMethod.GET,produces="application/octet-stream")
    public ResponseEntity<byte[]> downloadTemplate(HttpServletRequest request, HttpServletResponse response, String path, HttpSession httpSession) throws Exception {
    	logger.info("downloadTemplate-path=" + path + ",userId=" + httpSession.getAttribute(SystemConst.SESSION_LOGIN_USER_ID));
    	if (StringUtils.isBlank(path)) {
			throw new Exception();
		}
    	File file=new File(request.getSession().getServletContext().getRealPath(path));
    	if (!file.exists()) {
    		throw new Exception("文件不存在");
		}
        HttpHeaders headers = new HttpHeaders();
        String name = "到款信息表"+DateFormatUtils.format(new Date(), "yyyy-MM-dd")+".xlsx";
        String fileName = new String(name.getBytes("gbk"),"iso-8859-1");
		headers.set("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
    	return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }
}
