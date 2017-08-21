package cn.teacheredu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.teacheredu.entity.AttachmentEntity;
import cn.teacheredu.mapping.AttachmentMapper;
import cn.teacheredu.service.AttachmentService;
import cn.teacheredu.vo.AttachmentQueryVo;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Override
	public AttachmentEntity getAttachmentById(Integer attachmentId) throws Exception {
		return this.attachmentMapper.selectByPrimaryKey(attachmentId);
	}

	@Override
	public List<AttachmentEntity> getAttachmentByVo(AttachmentQueryVo vo) throws Exception {
		return this.attachmentMapper.selectByExample(vo);
	}

	@Override
	public int insertAttachment(AttachmentEntity attachmentEntity) throws Exception {
		this.attachmentMapper.insertSelective(attachmentEntity);
		return attachmentEntity.getId();
	}

	@Override
	public long getCountByVo(AttachmentQueryVo vo) throws Exception {
		return this.attachmentMapper.countByExample(vo);
	}

	@Override
	public int updateAttachment(AttachmentEntity attachmentEntity) throws Exception {
		return this.attachmentMapper.updateByPrimaryKeySelective(attachmentEntity);
	}

	@Override
	public int deleteAttachmentById(Integer attachmentId) throws Exception {
		return this.attachmentMapper.deleteByPrimaryKey(attachmentId);
	}

	@Override
	public void updateAttachmentByIdAndName(Integer[] attachIds, String tableName, Integer objectId) throws Exception {
		if (attachIds != null && attachIds.length > 0) {
			for (int i = 0; i < attachIds.length; i++) {
				Integer attachId = attachIds[i];
				AttachmentEntity attachmentEntity = this.getAttachmentById(attachId);
				attachmentEntity.setObjectId(objectId);
				attachmentEntity.setTableName(tableName);
				this.updateAttachment(attachmentEntity);
			}
		}
	}

	@Override
	public List<AttachmentEntity> getAttachmentByProcessId(Integer id) throws Exception {
		if (id == null) {
			return new ArrayList<AttachmentEntity>();
		}
		AttachmentQueryVo vo = new AttachmentQueryVo();
		vo.setObjectId(id);
		List<AttachmentEntity> list = this.getAttachmentByVo(vo);
		
		return list;
	}

}
