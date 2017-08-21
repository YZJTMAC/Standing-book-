package cn.teacheredu.service;

import java.util.List;

import cn.teacheredu.entity.AttachmentEntity;
import cn.teacheredu.vo.AttachmentQueryVo;

public interface AttachmentService {

	/**
	 * 通过id获取附件
	 * @param attachmentId
	 * @return
	 * @throws Exception
	 */
	AttachmentEntity getAttachmentById(Integer attachmentId) throws Exception;
	
	/**
	 * 获取分页的附件表
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List<AttachmentEntity> getAttachmentByVo(AttachmentQueryVo vo) throws Exception;
	
	/**
	 * 插入附件
	 * @param attachmentEntity
	 * @return
	 * @throws Exception
	 */
	int insertAttachment(AttachmentEntity attachmentEntity) throws Exception;
	
	long getCountByVo(AttachmentQueryVo vo) throws Exception;
	
	int updateAttachment(AttachmentEntity attachmentEntity) throws Exception;
	
	int deleteAttachmentById(Integer attachmentId) throws Exception;
	
	/**
	 * 为之前上传过的附件增加所属的表单信息
	 * 也就是说把附件跟表单对应联系起来
	 * @param attachIds  要操作的附件IDS
	 * @param tableName  关联的table name
	 * @param objectId 关联的table里的id
	 * @throws Exception
	 */
	void updateAttachmentByIdAndName(Integer[] attachIds,String tableName,Integer objectId) throws Exception;
	
	/**
	 * 获取流程的附件信息
	 * @param id  流程ID
	 * @return
	 * @throws Exception
	 */
	List<AttachmentEntity> getAttachmentByProcessId(Integer id) throws Exception;
	
}
